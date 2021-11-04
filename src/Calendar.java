import java.io.*;
import java.util.*;

public class Calendar {
    private Task[] jan, feb, mar, april, may, june, jul, aug, sept, oct, nov, dec;

    public Calendar(){
        jan = new Task[31];
        feb = new Task[28];
        mar = new Task[31];
        may = new Task[31];
        jul = new Task[31];
        aug = new Task[31];
        oct = new Task[31];
        dec = new Task[31];
        april = new Task[30];
        june = new Task[30];
        nov = new Task[30]; 
    }

    public int addTask(){
    
        Scanner myScanner = new Scanner(System.in);

        System.out.println("Enter task name: ");
        String taskName =  myScanner.nextLine();

        System.out.println("Enter due date (MM/DD/YYYY): ");
        String dueDate = myScanner.nextLine();

        System.out.println("Enter priority: ");
        String priority = myScanner.nextLine();
        
        writeToFile(taskName, dueDate, priority);

        myScanner.close();

        return 1;
    }

    //finds what month the user enter to pull up the right month text file.
    //the first element will always be the month, assuming American date format
    private String monthFinder(String input){

        String[] parsedString = input.split("/");
        int month = Integer.parseInt(parsedString[0]);

        return monthToString(month);

    }

    //saves the task to correct month text file.
    private void writeToFile(String taskName, String dueDate, String priority){

        String month = monthFinder(dueDate);

        try {
            FileWriter myWriter = new FileWriter( month + ".txt", true);
            myWriter.write("{\nName: " + taskName + "\n");
            myWriter.write("Date: " + dueDate + "\n");
            myWriter.write("Priority: " + priority + "\n");
            myWriter.write("}");
            myWriter.close();

        } catch (IOException e) {
            System.out.println("Error writing to file");
            e.printStackTrace();
        }


    }

    public void readFiles(){
        try {
            File fileObj = new File("jan.txt");
            Scanner myScanner = new Scanner(fileObj);

            while(myScanner.hasNextLine()){
                String line = myScanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            //TODO: handle exception
            System.out.println("Error");
            e.printStackTrace();
        }
      
    }
    
    //This will convert the numerical form the month to word form of the month, to find the correct file.
    private String monthToString(int input){

        switch(input){
            case 1:
                return "jan";
            case 2:
                return "feb";
            case 3:
                return "mar";
            case 4:
                return "april";
            case 5:
                return "may";
            case 6:
                return "june";
            case 7:
                return "jul";
            case 8:
                return "aug";
            case 9:
                return "sept";
            case 10:
                return "oct";
            case 11:
                return "nov";
            case 12:
                return "dec";
            default:
                System.out.println("Invalid month");
                return "error";
        }
            
    }
    
}
