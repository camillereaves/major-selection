
/** DEPARTMENT OBJECT ---------------------------------------------------------------------------------
 * This object contains an individual Department, including all records and relevant information.
 *
 * Contains the name, ID, and chair of the Department, as well as the college it is part of.
 * Houses HashMaps of the potential earnings by year, time to graduate by year, and job satisfaction
 * rate by year.
 */

import java.util.HashMap;

public class Department {
    private String departmentName;
    private String departmentID;
    private String departmentChair;
    private String college;

    private HashMap<Integer, Integer> potentialEarnings = new HashMap<Integer, Integer>();
    private HashMap<Integer, Integer> timeToGraduate = new HashMap<Integer, Integer>();
    private HashMap<Integer, Integer> jobSatisfaction = new HashMap<Integer, Integer>();

    /** EMPTY CONSTRUCTOR ------------------------------------------------------------------------- */
    public Department(){    }

    /** DEPARTMENT CONSTRUCTOR ----------------------------------------------------------------------
     * @param name  Name of the department.
     * @param ID    University assigned ID of the department.
     * @param chair Name of the department chairperson                                             */
    public Department(String name, String ID, String chair){
        setName(name);
        setId(ID);
        setChair(chair);
    }

//DEPARTMENT NAME
    /** SET NAME -----------------------------------------------------------------------------------
     * @param name  Name of the department                                                        */
    public void setName(String name){   departmentName = name; }

    /** GET NAME -----------------------------------------------------------------------------------
     * @return  department name as String                                                         */
    public String getName() {
        return departmentName;
    }

//DEPARTMENT ID
    /** SET ID -------------------------------------------------------------------------------------
     * @param id    ID of the department, given as a String.                                      */
    public void setId(String id){
        //Cleans up input - if given in double form with ".0" as the end, cleans this off the input
        if(id.endsWith(".0")){    departmentID = id.substring(0, id.length() - 2); }
        else{   departmentChair = id;   }
    }

    /** GET ID -------------------------------------------------------------------------------------
     * @return  department ID as String                                                           */
    public String getId(){
        return departmentID;
    }

//DEPARTMENT COLLEGE
    /** SET COLLEGE --------------------------------------------------------------------------------
     * @param col Name of the college that the department is in.                                  */
    public void setCollege(String col){
        college = col;
    }

    /** GET COLLEGE --------------------------------------------------------------------------------
     * @return College that the department is in as a String.                                     */
    public String getCollege(){
        return college;
    }

//DEPARTMENT CHAIRPERSON
    /** SET CHAIR -----------------------------------------------------------------------------------
     * @param chair Name of the chairperson of the college.                                        */
    public void setChair(String chair){     departmentChair = chair;    }

    /** GET NAME -----------------------------------------------------------------------------------
     * @return  Name of the department's chairperson as a String                                  */
    public String getChair(){   return departmentChair; }

//POTENTIAL EARNINGS BY YEAR
    /** SET POTENTIAL EARNINGS ---------------------------------------------------------------------
     * @param year  Year of the earnings
     * @param earn  Amount earned that year                                                       */
    public void setPotentialEarnings(int year, String earn) {
        //Cleans up input - if given in double form with ".0" as the end, cleans this off the input
        if (earn.endsWith(".0")) {  earn = earn.substring(0, earn.length() - 2); }

        //Add year/earnings pair to the potential earnings HashMap, casting the earnings as an int
        potentialEarnings.put(year, Integer.parseInt(earn));
    }

    /** GET POTENTIAL EARNINGS ---------------------------------------------------------------------
     * @param year  Year of the earnings being searched for
     * @return  Potential earnings for given year as an integer                                   */
    public int getPotentialEarnings(String year){
        return potentialEarnings.get(Integer.parseInt(year));
    }

//TIME TO GRADUATE
    /** SET TIME TO GRADUATE -----------------------------------------------------------------------
     * @param year  Year of the record
     * @param time  Time to graduate based on that year                                           */
    public void setTimeToGraduate(int year, String time) {
        //Cleans up input - if given in double form with ".0" as the end, cleans this off the input
        if (time.endsWith(".0")) {  time = time.substring(0, time.length() - 2); }

        //Add year/time pair to the time to graduate HashMap, casting the time as an int
        timeToGraduate.put(year, Integer.parseInt(time));
    }

    /** GET TIME TO GRADUATE -----------------------------------------------------------------------
     * @param year  Year of the record being searched for
     * @return  Time to graduate for given year as an integer                                     */
    public int getTimeToGraduate(String year){
        return timeToGraduate.get(Integer.parseInt(year));
    }

    /** AVERAGE TIME UNTIL GRADUATION --------------------------------------------------------------
     * @return  The average of all "time to graduation" records.                                  */
    public int averageYearsToGrad(){
        int total = 0;
        int count = 0;

        for(int i = 0; i < timeToGraduate.size(); i++){
            //Adds the time to graduate record for each year from 2017 to the present to the total
            total += timeToGraduate.get(2017 + i);
            count++;    // Number of years
        }

        return total / count;
    }

//JOB SATISFACTION BY YEAR
    /** SET JOB SATISFACTION -----------------------------------------------------------------------
     * @param year  Year of the job satisfaction
     * @param sat  Job satisfaction that year                                                     */
    public void setJobSatisfaction(int year, String sat) {
        //Cleans up input - if given in double form with ".0" as the end, cleans this off the input
        if (sat.endsWith(".0")) {  sat = sat.substring(0, sat.length() - 2); }

        //Add year/satisfaction pair to the job satisfaction HashMap, casting the satisfaction as an int
        jobSatisfaction.put(year, Integer.parseInt(sat));
    }

    /** GET JOB SATISFACTION -----------------------------------------------------------------------
     * @param year  Year of the job satisfaction record being searched for
     * @return  Job satisfaction for given year as an integer                                   */
    public int getJobSatisfaction(String year){
        return jobSatisfaction.get(Integer.parseInt(year));
    }


}
