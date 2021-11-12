import java.io.*;
import java.util.*;

public class Calendar {
    private ArrayList[] jan, feb, mar, april, may, june, jul, aug, sept, oct, nov, dec;

    public Calendar(){
        jan = new ArrayList[31];
        feb = new ArrayList[28];
        mar = new ArrayList[31];
        may = new ArrayList[31];
        jul = new ArrayList[31];
        aug = new ArrayList[31];
        oct = new ArrayList[31];
        dec = new ArrayList[31];
        april = new ArrayList[30];
        june = new ArrayList[30];
        nov = new ArrayList[30]; 
    }

    public int addTask(String taskName, String dueDate, String priority){
                
        writeToFile(taskName, dueDate, priority, "True");
        
        return 1;
    }
 
    private int dateFinder(String input){
        String[] parsedString = input.split("/");
        return Integer.parseInt(parsedString[1]);
    }

    //finds what month the user enter to pull up the right month text file.
    //the first element will always be the month, assuming American date format
    private String monthFinder(String input){

        String[] parsedString = input.split("/");
        int month = Integer.parseInt(parsedString[0]);

        return monthToString(month);

    }

    //saves the task to correct month text file.
    private void writeToFile(String taskName, String dueDate, String priority, String flag){

        String month = monthFinder(dueDate);
        int date = dateFinder(dueDate);

        try {
            FileWriter myWriter = new FileWriter( month + ".txt", true);
            myWriter.write("\n{\nName: " + taskName + "\n");
            myWriter.write("Date: " + dueDate + "\n");
            myWriter.write("Priority: " + priority + "\n");
            myWriter.write("Flag: " + flag + "\n");
            myWriter.write("}\n");
            myWriter.close();

        } catch (IOException e) {
            System.out.println("Error writing to file");
            e.printStackTrace();
        }

    }

    public void readFiles(){
        String taskName, dueDate, priority, flag;
        taskName = null;
        dueDate = priority = flag = null;

        try {
            File fileObj = new File("nov.txt");
            Scanner myScanner = new Scanner(fileObj);

            while(myScanner.hasNextLine()){
                
                String line = myScanner.nextLine();
                System.out.println("Current Line: "  + line);
                
                if(line.equals("{") || line.equals("}")){

                    continue;
                }
                else{
                    System.out.println("Inside else statement");
                    String[] stringArr = line.split(":");

                    if(stringArr[0].equals("Name")){
                        taskName = stringArr[1];
                        continue;
                    }
                    else if(stringArr[0].equals("Date")){
                        dueDate = stringArr[1];
                        continue;
                    }
                    else if(stringArr[0].equals("Priority")){
                        priority = stringArr[1];
                        continue;
                    }
                    else {
                        flag = stringArr[1];
                        continue;
                    }

                }
            }
            int date = dateFinder(dueDate);
            myScanner.close();
            //jan[date].add( new Task(taskName, dueDate, Integer.parseInt(priority), flag));
        }
        catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
      /*
        try {
            File fileObj = new File("feb.txt");
            Scanner myScanner = new Scanner(fileObj);

            while(myScanner.hasNextLine()){
                
                String line = myScanner.nextLine();
                if(line.equals("{\n") || line.equals("}\n")){
                    continue;
                }
                else{
                    String[] stringArr = line.split(":");
                    if(stringArr[0].equals("Name")){
                        taskName = stringArr[1];
                        continue;
                    }
                    else if(stringArr[0].equals("Date")){
                        dueDate = stringArr[1];
                        continue;
                    }
                    else if(stringArr[0].equals("Priority")){
                        priority = stringArr[1];
                        continue;
                    }
                    else{
                        flag = stringArr[1];
                        continue;
                    }

                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        */
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

    public void printCalendar() throws FileNotFoundException {
        Scanner input = new Scanner(new File("nov.txt"));

        while (input.hasNextLine()) {
            System.out.println(input.nextLine());
        }
    }
    
}
