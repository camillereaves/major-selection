import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MajorDBTest {

    MajorDB db = null;
    //You may change this line's value, AND THIS LINE ONLY
    static final String MAJOR_DB = "DB/CollegeDatabase.xlsx";

    @Before
    public void setUp() throws Exception{
        db = new MajorDB(MAJOR_DB);
    }

    @After
    public void tearDown() throws Exception {
        db = null;
    }

    @Test
    public void testGetNumDepartments(){
        int numDepartments = db.getNumDepartments();
        assertEquals(18, numDepartments);
    }

    @Test
    public void testGetDepartments1(){
        HashSet<Department> departments = null;
        departments = db.getDepartments();
        assertEquals(18, departments.size());
    }

    @Test
    public void testGetDepartments2() {
        HashSet<Department> departments = null;
        departments = db.getDepartments();
        boolean found = false;
        for (Department d : departments) {
            if (d.getName().compareTo("Criminal Justice") == 0
                    && d.getId().compareTo("88") == 0) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testGetDepartmentsByName1() {
        Department department = null;
        department = db.getDepartmentByName("Software Engineering");
        assertTrue(department.getChair().compareTo("Dr. Sumanth Yenduri ") == 0);
    }

    @Test
    public void testGetDepartmentsByName2() {
        Department department = null;
        department = db.getDepartmentByName("Geography");
        assertEquals(55544, department.getPotentialEarnings("2018"));
    }

    @Test
    public void testGetDepartmentsByName3() {
        Department department = null;
        department = db.getDepartmentByName("Music Education");
        assertEquals(3, department.getTimeToGraduate("2017"));
    }

    @Test
    public void testGetDepartmentsByName4() {
        Department department = null;
        department = db.getDepartmentByName("Digital Animation");
        assertEquals(68, department.getJobSatisfaction("2019")); //CORRECTED
    }

    @Test
    public void testGetDepartmentsByID() {
        Department department = db.getDepartmentByID("89");
        assertTrue(department.getName().compareTo("Art History") == 0);
    }
}

