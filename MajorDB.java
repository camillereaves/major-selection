
/** MAJORDB CLASS ------------------------------------------------------------------------------------
 * This is the core of the Major Selection tool. This class takes in a database given a relative
 * address of an Excel workbook. It retrieves the information from this database, and creates a
 * HashSet of Department objects containing all departments included in the given database.
 *
 * #getNumDepartments   Gets the number of departments in the database.
 * #getDepartments      Gets a clone of the database HashSet.
 * #getDepartmentByName Searches for a department by name.
 * #getDepartmentById   Searches for a department by ID.
 *
 * Extended by MajorSelection.java
 */

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.HashSet;


public class MajorDB {
    //Variable containing the system path of the current directory (used in File creation)
    private static final String dir = System.getProperty("user.dir");

    //HashSet containing all the departments in the database
    protected static HashSet<Department> departments;

    /** MAJORDB CONSTRUCTOR --------------------------------------------------------------------------
     * @param DB_loc This is the relative path of database as a String.                             */
    public MajorDB(String DB_loc) {
        try{ DatabaseIn(DB_loc); }                   //If the database can't be found/throws exception
        catch (IOException e){ e.printStackTrace(); }//catch exception and output stack trace
    }

    /** DATABASE IN ----------------------------------------------------------------------------------
     * Takes the database and reads it into a HashSet of Department objects.
     * @exception IOException If file can't be read in due to input error, throws exception.
     * @param DB_loc This is the relative address of the database                                   */
    private static void DatabaseIn(String DB_loc) throws IOException {

        //create workbook from excel file
        XSSFWorkbook workbook = importFile(DB_loc);

        /* Gets each sheet in the workbook */
        XSSFSheet dept_info = workbook.getSheetAt(0);       //Department Info
        XSSFSheet col = workbook.getSheetAt(1);             //Colleges
        XSSFSheet dept_timeGrad = workbook.getSheetAt(2);   //Time to Graduate
        XSSFSheet dept_Earn = workbook.getSheetAt(3);       //Department Potential Earnings
        XSSFSheet dept_jobSat = workbook.getSheetAt(4);     //Reported Job Satisfaction
        XSSFSheet col_Earn = workbook.getSheetAt(5);        //College Potential Earnings

        //Number of departments and number of colleges in the university
        int numDept = dept_info.getPhysicalNumberOfRows();
        int numCol = col.getPhysicalNumberOfRows();

        departments = new HashSet<Department>();

        /* CREATE DEPARTMENTS ------------------------------------------------------------------------
         *  Runs through DepartmentInfo sheet and creates all the departments with their name, ID,
         *  and chairperson.                                                                        */
        for (int r = 1; r < numDept; r++){
                XSSFRow row = dept_info.getRow(r);

                //Department information
                String name = row.getCell(0).toString();
                String univID = row.getCell(1).toString();
                String chair = row.getCell(2).toString();

                //Creates and adds the departments to the HashSet
                Department temp = new Department(name, univID, chair);
                departments.add(temp);
        }

        /* ASSIGNS COLLEGES ------------------------------------------------------------------------
         *  Runs through Colleges sheet and sorts all departments into their respective colleges. */
        for (int r = 1; r < numCol; r++){
            XSSFRow row = col.getRow(r);
            String colName = row.getCell(0).toString();

            //Each college has up to 5 departments, runs through each of these values
            for (int c = 1; c <= 5; c++){
                String deptID = row.getCell(c).toString();
                //Cleans deptID value
                if(deptID.endsWith(".0")){    deptID = deptID.substring(0, deptID.length() - 2); }

                //If the department ID is part of the college, adds the college to the department
                for (Department d : departments) {
                    if (d.getId().equalsIgnoreCase(deptID)) { d.setCollege(colName);    }
                }
            }
        }

        /* TIME TO GRADUATE ------------------------------------------------------------------------
         *  Runs through TimeToGraduate sheet and adds time to graduate for the years 2017, 2018,
         *  and 2019 to each department.                                                          */
        for (int r = 1; r < numDept; r++){
            XSSFRow row = dept_timeGrad.getRow(r);
            String dept = row.getCell(0).toString();

            //Graduation values
            String grad2017 = row.getCell(1).toString();
            String grad2018 = row.getCell(2).toString();
            String grad2019 = row.getCell(3).toString();

            //Sets time to graduate for 2017, 2018, 2019
            for (Department d : departments) {
                if (d.getName().equalsIgnoreCase(dept)) {
                    d.setTimeToGraduate(2017, grad2017);
                    d.setTimeToGraduate(2018, grad2018);
                    d.setTimeToGraduate(2019, grad2019);
                }
            }
        }

        /* POTENTIAL EARNINGS -----------------------------------------------------------------------
         *  Runs through DepartmentPotentialEarnings sheet and adds potential earnings for the years
         *  2017, 2018, and 2019 to each department.                                               */
        for (int r = 1; r < numDept; r++){
            XSSFRow row = dept_Earn.getRow(r);
            String dept = row.getCell(0).toString();

            //Potential earnings values
            String earn2017 = row.getCell(1).toString();
            String earn2018 = row.getCell(2).toString();
            String earn2019 = row.getCell(3).toString();

            //Sets potential earnings for 2017, 2018, 2019
            for (Department d : departments) {
                if (d.getName().equalsIgnoreCase(dept)) {
                    d.setPotentialEarnings(2017, earn2017);
                    d.setPotentialEarnings(2018, earn2018);
                    d.setPotentialEarnings(2019, earn2019);
                }
            }
        }

        /* JOB SATISFACTION ----------------------------------------------------------------------------
         *  Runs through ReportedJobSatisfaction sheet and adds job satisfaction for the years 2017,
         *  2018, and 2019 to each department.                                                         */
        for (int r = 1; r < numDept; r++){
            XSSFRow row = dept_jobSat.getRow(r);
            String dept = row.getCell(0).toString();

            //Job satisfaction values
            String sat2017 = row.getCell(1).toString();
            String sat2018 = row.getCell(2).toString();
            String sat2019 = row.getCell(3).toString();

            //Sets job satisfaction for 2017, 2018, 2019
            for (Department d : departments) {
                if (d.getName().equalsIgnoreCase(dept)) {
                    d.setJobSatisfaction(2017, sat2017);
                    d.setJobSatisfaction(2018, sat2018);
                    d.setJobSatisfaction(2019, sat2019);
                }
            }
        }
    } //end DatabaseIn

    /** FINDS AND IMPORTS THE EXCEL WORKBOOK --------------------------------------------------------
     * @param DB_loc This is the relative address of the database
     * @exception FileNotFoundException If File can't be found, throws exception.
     * @return XSSFWorkbook containing database                                                    */
    private static XSSFWorkbook importFile(String DB_loc) throws FileNotFoundException, IOException {
        //Convert relative path of database to absolute path and creates a File object out of it
        File f = new File(dir + "\\src\\" + DB_loc);
        FileInputStream fis = new FileInputStream(f);

        //Reads in database from the file to a XSSFWorkbook object and returns the workbook
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        return workbook;
    }

    /** GET THE NUMBER OF DEPARTMENTS ---------------------------------------------------------------
     * @return Number of departments as integer                                                    */
    public int getNumDepartments(){ return departments.size(); }


    /** GET DEPARTMENTS -----------------------------------------------------------------------------
     * @return cloned HashSet of Department objects containing current departments                 */
    public HashSet<Department> getDepartments(){
        HashSet<Department> cloned_set = new HashSet<Department>();
        cloned_set = (HashSet<Department>)departments.clone();
        return cloned_set;
    }

    /** GET DEPARTMENT BY NAME ----------------------------------------------------------------------
     * @param departmentName string containing name of department
     * @return Department object for the searched department                                       */
    public Department getDepartmentByName(String departmentName){
        Department department = new Department();
        for (Department d : departments) {
            if (d.getName().equalsIgnoreCase(departmentName)) {
                department = d;
                break;
            }
        }
        return department;
    }

    /** GET DEPARTMENT BY ID ------------------------------------------------------------------------
     * @param departmentID string containing ID of department
     * @return Department object for the searched department                                       */
    public Department getDepartmentByID(String departmentID){
        Department department = new Department();
        for (Department d : departments) {
            if (d.getId().equalsIgnoreCase(departmentID)) {
                department = d;
                break;
            }
        }
        return department;
    }
}
