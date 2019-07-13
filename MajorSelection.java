
/** MAJOR SELECTION TOOL --------------------------------------------------------------------------------
 * The MajorSelection tool can be run to get information about current departments at the university.
 * Extends MajorDB to display information based on user input.
 */

import java.util.Scanner;

public class MajorSelection extends MajorDB {

    public MajorSelection(String location) {
        super(location);
    }

     //PRINT MAJOR SELECTION INTERFACE ----------------------------------
       //The application outputs to the screen the number of departments,
       //along with relevant information. After that, it asks the counselor
       //if they want to continue or exit. In the former case, it repeats
       //the output again.
     public void printSelection(){
        Scanner in = new Scanner(System.in);
        boolean flag = false;

        while(!flag){
            System.out.println("NUMBER OF DEPARTMENTS IN THE UNIVERSITY: " + departments.size() + "\n");

            for(Department d:departments){
                System.out.println("DEPARTMENT NAME: " + d.getName());
                System.out.println("\t* University ID: " + d.getId());
                System.out.println("\t* Department Chair: " + d.getChair());
                System.out.println("\t* Associated College: " + d.getCollege());
                System.out.println("\t* Average Years to Earn Degree: " + d.averageYearsToGrad());
                System.out.println("\t* Most Recent Potential Earnings: $" + d.getPotentialEarnings("2019"));
            }

            System.out.println("DO YOU WISH TO CONTINUE? (Y/N) ");
            String answer = in.nextLine();

            if(!answer.equalsIgnoreCase("YES") && !answer.equalsIgnoreCase("Y")){
                flag = true;
            }
            else{
                System.out.println("-------------------------------------------------\n");
            }
        }

        System.out.println("System exited.");
    }

    //MAIN CLASS --- Entry point for the code when you run from the console
    public static void main(String[] args){
        MajorSelection DB = new MajorSelection("DB/CollegeDatabase.xlsx");
        DB.printSelection();
    }
}
