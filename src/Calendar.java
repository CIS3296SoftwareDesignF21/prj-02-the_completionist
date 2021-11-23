import java.io.*;
import java.util.*;

public class Calendar {
    private List<List<Task>> jan, feb, mar, april, may, june, jul, aug, sept, oct, nov, dec;

    public Calendar(){
        jan = new ArrayList<List<Task>>(30);
        feb = new ArrayList<List<Task>>(29);
        mar = new ArrayList<List<Task>>(31);
        april = new ArrayList<List<Task>>(30);
        may = new ArrayList<List<Task>>(31);
        june = new ArrayList<List<Task>>(30);
        jul = new ArrayList<List<Task>>(31);
        aug = new ArrayList<List<Task>>(31);
        sept = new ArrayList<List<Task>>(30);
        oct = new ArrayList<List<Task>>(31);
        nov = new ArrayList<List<Task>>(30);
        dec = new ArrayList<List<Task>>(31); 
    }

    //adds the task to the arraylist
    public int addTask(Task newTask){

        String month = monthFinder(newTask.getDate());
        int date = dateFinder(newTask.getDate());

        if(month.equals("jan")){
            jan.get(date).add(newTask);
        }
        else if(month.equals("feb")){
            feb.get(date).add(newTask);
        }
        else if(month.equals("mar")){
            mar.get(date).add(newTask);
        }
        else if(month.equals("april")){
            april.get(date).add(newTask);
        }
        else if(month.equals("may")){
            may.get(date).add(newTask);
        }
        else if(month.equals("june")){
            june.get(date).add(newTask);
        }
        else if(month.equals("jul")){
            jul.get(date).add(newTask);
        }
        else if(month.equals("aug")){
            aug.get(date).add(newTask);
        }
        else if(month.equals("sept")){
            sept.get(date).add(newTask);
        }
        else if(month.equals("oct")){
            oct.get(date).add(newTask);
        }
        else if(month.equals("nov")){
            nov.get(date).add(newTask);
        }
        else if(month.equals("dec")){
            dec.get(date).add(newTask);
        }
        else{
            return -1;
        }
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

    //saves the task to correct month text file
    private void writeToFile(String taskName, String dueDate, String priority, String flag){

        String month = monthFinder(dueDate);

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

    //reads the saved tasks from the files and adds them to the array.
    public void readFiles(){
        String taskName, dueDate, priority, flag, month;
        taskName = null;
        dueDate = priority = flag = null;


        for(int i = 1; i <= 12; i++){
            month = monthToString(i);
            try {
                File fileObj = new File(month+".txt");
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
                myScanner.close();
                Task newTask = new Task(taskName, dueDate, Integer.parseInt(priority), flag);
                addTask(newTask);
                
            }
            catch (FileNotFoundException e) {
                System.out.println("Error");
                e.printStackTrace();
            }
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

    public void printCalendar() throws FileNotFoundException {
        Scanner input = new Scanner(new File("nov.txt"));

        while (input.hasNextLine()) {
            System.out.println(input.nextLine());
        }
    }
    
}
