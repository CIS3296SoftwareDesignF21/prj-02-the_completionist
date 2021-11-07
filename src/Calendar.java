import java.io.*;
import java.util.*;

public class Calendar {
    private Task[] jan, feb, mar, april, may, june, jul, aug, sept, oct, nov, dec;
    private LinkedList[] test = new LinkedList[30];

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

        String month;
        int date;
    
        Scanner myScanner = new Scanner(System.in);

        System.out.println("Enter task name: ");
        String taskName =  myScanner.nextLine();

        System.out.println("Enter due date (MM/DD/YYYY): ");
        String dueDate = myScanner.nextLine();

        System.out.println("Enter priority: ");
        String priority = myScanner.nextLine();

        System.out.println("Enter Flag (true/false): ");
        String flag = myScanner.nextLine();
        
        writeToFile(taskName, dueDate, priority, flag);
        saveToArray(taskName, dueDate, priority, flag);

        myScanner.close();

        return 1;
    }

    private void saveToArray(String taskName, String dueDate, String priority, String flag){

        String month = monthFinder(dueDate);
        int date = dateFinder(dueDate);

        if(month.equals("jan")){
            Task newTask = new Task(taskName, dueDate, Integer.parseInt(priority), flag);
            jan[date] = newTask;
        }
        else if(month.equals("feb")){
            Task newTask = new Task(taskName, dueDate, Integer.parseInt(priority), flag);
            feb[date] = newTask;
    
        }
        else if(month.equals("mar")){
            Task newTask = new Task(taskName, dueDate, Integer.parseInt(priority), flag);
            mar[date] = newTask;
    
        }
        else if(month.equals("april")){
            Task newTask = new Task(taskName, dueDate, Integer.parseInt(priority), flag);
            april[date] = newTask;
        }
        else if(month.equals("may")){
            Task newTask = new Task(taskName, dueDate, Integer.parseInt(priority), flag);
            may[date] = newTask;
    
        }
        else if(month.equals("june")){
            Task newTask = new Task(taskName, dueDate, Integer.parseInt(priority), flag);
            june[date] = newTask;
    
        }
        else if(month.equals("jul")){
            Task newTask = new Task(taskName, dueDate, Integer.parseInt(priority), flag);
            jul[date] = newTask;
    
        }
        else if(month.equals("aug")){
            Task newTask = new Task(taskName, dueDate, Integer.parseInt(priority), flag);
            aug[date] = newTask;
    
        }
        else if(month.equals("sept")){
            Task newTask = new Task(taskName, dueDate, Integer.parseInt(priority), flag);
            sept[date] = newTask;
    
        }
        else if(month.equals("oct")){
            Task newTask = new Task(taskName, dueDate, Integer.parseInt(priority), flag);
            oct[date] = newTask;
    
        }
        else if(month.equals("nov")){
            Task newTask = new Task(taskName, dueDate, Integer.parseInt(priority), flag);
            nov[date] = newTask;
    
        }
        else if(month.equals("dec")){
            Task newTask = new Task(taskName, dueDate, Integer.parseInt(priority), flag);
            dec[date] = newTask;
    
        }
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

        try {
            FileWriter myWriter = new FileWriter( month + ".txt", true);
            myWriter.write("{\nName: " + taskName + "\n");
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
            File fileObj = new File("jan.txt");
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
            Task newTask = new Task(taskName, dueDate, Integer.parseInt(priority), flag);
        }
        catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
      
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
