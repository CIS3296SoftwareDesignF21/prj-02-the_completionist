import java.io.*;
import java.util.*;

public class Calendar {
    private ArrayList<Task>[] jan, feb, mar, april, may, june, jul, aug, sept, oct, nov, dec;
    private static Calendar instance = null;

    // public static void main(String[] args){
    //   Calendar cal = new Calendar();

    //     if(cal.addTask(new Task("do Homework", "11/10/2021", "high", "true")) == -1){
    //         System.out.println("adding task failed");
    //     }
    //     else{
    //         System.out.println("test added successfully");
    //     }

    //     cal.addTask(new Task("go to grocery store", "11/1/2021", "low", "true"));
    //     cal.addTask(new Task("go to the mall", "11/2/2021", "medium", "true"));

    //     System.out.println(cal.nov[0].toString());
    //     System.out.println("\n\n\n");

    // }

    //singleton
    private Calendar(){

        jan = new ArrayList[31];
        feb = new ArrayList[31];
        mar = new ArrayList[31];
        april = new ArrayList[31];
        may = new ArrayList[31];
        june = new ArrayList[31];
        jul = new ArrayList[31];
        aug = new ArrayList[31];
        sept = new ArrayList[31];
        oct = new ArrayList[31];
        nov = new ArrayList[31];
        dec = new ArrayList[31];

        for(int i = 0; i < 31; i++){
            jan[i] = new ArrayList<Task>();
            feb[i] = new ArrayList<Task>();
            mar[i] = new ArrayList<Task>();
            april[i] = new ArrayList<Task>();
            may[i] = new ArrayList<Task>();
            june[i] = new ArrayList<Task>();
            jul[i] = new ArrayList<Task>();
            aug[i] = new ArrayList<Task>();
            sept[i] = new ArrayList<Task>();
            oct[i] = new ArrayList<Task>();
            nov[i] = new ArrayList<Task>();
            dec[i] = new ArrayList<Task>();

        }
            
    }

    public static Calendar getInstance(){
        if(instance == null){
            instance = new Calendar();
        }
        return instance;
    }
    //adds the task to the arraylist

    public int addTask(Task newTask){

        String month = monthFinder(newTask.getDate());
        int date = dateFinder(newTask.getDate());

        if(month.equals("jan")){
            jan[date-1].add(newTask);
        }
        else if(month.equals("feb")){
            feb[date-1].add(newTask);
        }
        else if(month.equals("mar")){
            mar[date-1].add(newTask);
        }
        else if(month.equals("april")){
            april[date-1].add(newTask);
        }
        else if(month.equals("may")){
            may[date-1].add(newTask);
        }
        else if(month.equals("june")){
            june[date-1].add(newTask);
        }
        else if(month.equals("jul")){
            jul[date-1].add(newTask);
        }
        else if(month.equals("aug")){
            aug[date-1].add(newTask);
        }
        else if(month.equals("sept")){
            sept[date-1].add(newTask);
        }
        else if(month.equals("oct")){
            oct[date-1].add(newTask);
        }
        else if(month.equals("nov")){
            nov[date-1].add(newTask);
        }
        else if(month.equals("dec")){
            dec[date-1].add(newTask);
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
    private void writeToFile(){

        //month
        for(int i =1; i <= 12; i++){
            String month = monthToString(i);
            if(month.equals("jan")){

                try{
                    FileWriter myWriter = new FileWriter( month + ".txt", true);
                    //day
                    for(int j = 0; j < jan.length; j++){
                        for(int k = 0; k < jan[j].size(); k++){
                            String taskName = jan[j].get(k).getName();
                            String dueDate = jan[j].get(k).getDate();
                            String priority = jan[j].get(k).getPriority();
                            String tag = jan[j].get(k).getTag();
                            myWriter.write("\n{\nName: " + taskName + "\n");
                            myWriter.write("Date: " + dueDate + "\n");
                            myWriter.write("Priority: " + priority + "\n");
                            myWriter.write("Tag: " + tag + "\n");
                            myWriter.write("}\n");
                            myWriter.close();
    
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error writing to file");
                    e.printStackTrace();
                }
            }
            else if(month.equals("feb")){

                try{
                    FileWriter myWriter = new FileWriter( month + ".txt", true);
                    //day
                    for(int j = 0; j < feb.length; j++){
                        for(int k = 0; k < feb[j].size(); k++){
                            String taskName = feb[j].get(k).getName();
                            String dueDate = feb[j].get(k).getDate();
                            String priority = feb[j].get(k).getPriority();
                            String tag = feb[j].get(k).getTag();
                            myWriter.write("\n{\nName: " + taskName + "\n");
                            myWriter.write("Date: " + dueDate + "\n");
                            myWriter.write("Priority: " + priority + "\n");
                            myWriter.write("Tag: " + tag + "\n");
                            myWriter.write("}\n");
                            myWriter.close();
    
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error writing to file");
                    e.printStackTrace();
                }
            }

            else if(month.equals("mar")){

                try{
                    FileWriter myWriter = new FileWriter( month + ".txt", true);
                    //day
                    for(int j = 0; j < mar.length; j++){
                        for(int k = 0; k < mar[j].size(); k++){
                            String taskName = mar[j].get(k).getName();
                            String dueDate = mar[j].get(k).getDate();
                            String priority = mar[j].get(k).getPriority();
                            String tag = mar[j].get(k).getTag();
                            myWriter.write("\n{\nName: " + taskName + "\n");
                            myWriter.write("Date: " + dueDate + "\n");
                            myWriter.write("Priority: " + priority + "\n");
                            myWriter.write("Tag: " + tag + "\n");
                            myWriter.write("}\n");
                            myWriter.close();
    
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error writing to file");
                    e.printStackTrace();
                }
            }

            else if(month.equals("april")){

                try{
                    FileWriter myWriter = new FileWriter( month + ".txt", true);
                    //day
                    for(int j = 0; j < april.length; j++){
                        for(int k = 0; k < april[j].size(); k++){
                            String taskName = april[j].get(k).getName();
                            String dueDate = april[j].get(k).getDate();
                            String priority = april[j].get(k).getPriority();
                            String tag = april[j].get(k).getTag();
                            myWriter.write("\n{\nName: " + taskName + "\n");
                            myWriter.write("Date: " + dueDate + "\n");
                            myWriter.write("Priority: " + priority + "\n");
                            myWriter.write("Tag: " + tag + "\n");
                            myWriter.write("}\n");
                            myWriter.close();
    
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error writing to file");
                    e.printStackTrace();
                }
            }
            else if(month.equals("may")){

                try{
                    FileWriter myWriter = new FileWriter( month + ".txt", true);
                    //day
                    for(int j = 0; j < may.length; j++){
                        for(int k = 0; k < may[j].size(); k++){
                            String taskName = may[j].get(k).getName();
                            String dueDate = may[j].get(k).getDate();
                            String priority = may[j].get(k).getPriority();
                            String tag = may[j].get(k).getTag();
                            myWriter.write("\n{\nName: " + taskName + "\n");
                            myWriter.write("Date: " + dueDate + "\n");
                            myWriter.write("Priority: " + priority + "\n");
                            myWriter.write("Tag: " + tag + "\n");
                            myWriter.write("}\n");
                            myWriter.close();
    
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error writing to file");
                    e.printStackTrace();
                }
            }
            else if(month.equals("june")){

                try{
                    FileWriter myWriter = new FileWriter( month + ".txt", true);
                    //day
                    for(int j = 0; j < june.length; j++){
                        for(int k = 0; k < june[j].size(); k++){
                            String taskName = june[j].get(k).getName();
                            String dueDate = june[j].get(k).getDate();
                            String priority = june[j].get(k).getPriority();
                            String tag = feb[j].get(k).getTag();
                            myWriter.write("\n{\nName: " + taskName + "\n");
                            myWriter.write("Date: " + dueDate + "\n");
                            myWriter.write("Priority: " + priority + "\n");
                            myWriter.write("Tag: " + tag + "\n");
                            myWriter.write("}\n");
                            myWriter.close();
    
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error writing to file");
                    e.printStackTrace();
                }
            }
            else if(month.equals("jul")){

                try{
                    FileWriter myWriter = new FileWriter( month + ".txt", true);
                    //day
                    for(int j = 0; j < jul.length; j++){
                        for(int k = 0; k < jul[j].size(); k++){
                            String taskName = jul[j].get(k).getName();
                            String dueDate = jul[j].get(k).getDate();
                            String priority = jul[j].get(k).getPriority();
                            String tag = jul[j].get(k).getTag();
                            myWriter.write("\n{\nName: " + taskName + "\n");
                            myWriter.write("Date: " + dueDate + "\n");
                            myWriter.write("Priority: " + priority + "\n");
                            myWriter.write("Tag: " + tag + "\n");
                            myWriter.write("}\n");
                            myWriter.close();
    
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error writing to file");
                    e.printStackTrace();
                }
            }
            else if(month.equals("aug")){

                try{
                    FileWriter myWriter = new FileWriter( month + ".txt", true);
                    //day
                    for(int j = 0; j < aug.length; j++){
                        for(int k = 0; k < aug[j].size(); k++){
                            String taskName = aug[j].get(k).getName();
                            String dueDate = aug[j].get(k).getDate();
                            String priority = aug[j].get(k).getPriority();
                            String tag = aug[j].get(k).getTag();
                            myWriter.write("\n{\nName: " + taskName + "\n");
                            myWriter.write("Date: " + dueDate + "\n");
                            myWriter.write("Priority: " + priority + "\n");
                            myWriter.write("Tag: " + tag + "\n");
                            myWriter.write("}\n");
                            myWriter.close();
    
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error writing to file");
                    e.printStackTrace();
                }
            }
            else if(month.equals("sept")){

                try{
                    FileWriter myWriter = new FileWriter( month + ".txt", true);
                    //day
                    for(int j = 0; j < sept.length; j++){
                        for(int k = 0; k < sept[j].size(); k++){
                            String taskName = sept[j].get(k).getName();
                            String dueDate = sept[j].get(k).getDate();
                            String priority = sept[j].get(k).getPriority();
                            String tag = feb[j].get(k).getTag();
                            myWriter.write("\n{\nName: " + taskName + "\n");
                            myWriter.write("Date: " + dueDate + "\n");
                            myWriter.write("Priority: " + priority + "\n");
                            myWriter.write("Tag: " + tag + "\n");
                            myWriter.write("}\n");
                            myWriter.close();
    
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error writing to file");
                    e.printStackTrace();
                }
            }
            else if(month.equals("oct")){

                try{
                    FileWriter myWriter = new FileWriter( month + ".txt", true);
                    //day
                    for(int j = 0; j < oct.length; j++){
                        for(int k = 0; k < oct[j].size(); k++){
                            String taskName = oct[j].get(k).getName();
                            String dueDate = oct[j].get(k).getDate();
                            String priority = oct[j].get(k).getPriority();
                            String tag = oct[j].get(k).getTag();
                            myWriter.write("\n{\nName: " + taskName + "\n");
                            myWriter.write("Date: " + dueDate + "\n");
                            myWriter.write("Priority: " + priority + "\n");
                            myWriter.write("Tag: " + tag + "\n");
                            myWriter.write("}\n");
                            myWriter.close();
    
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error writing to file");
                    e.printStackTrace();
                }
            }
            else if(month.equals("nov")){

                try{
                    FileWriter myWriter = new FileWriter( month + ".txt", true);
                    //day
                    for(int j = 0; j < nov.length; j++){
                        for(int k = 0; k < nov[j].size(); k++){
                            String taskName = nov[j].get(k).getName();
                            String dueDate = nov[j].get(k).getDate();
                            String priority = nov[j].get(k).getPriority();
                            String tag = nov[j].get(k).getTag();
                            myWriter.write("\n{\nName: " + taskName + "\n");
                            myWriter.write("Date: " + dueDate + "\n");
                            myWriter.write("Priority: " + priority + "\n");
                            myWriter.write("Tag: " + tag + "\n");
                            myWriter.write("}\n");
                            myWriter.close();
    
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error writing to file");
                    e.printStackTrace();
                }
            }

            else if(month.equals("dec")){

                try{
                    FileWriter myWriter = new FileWriter( month + ".txt", true);
                    //day
                    for(int j = 0; j < dec.length; j++){
                        for(int k = 0; k < dec[j].size(); k++){
                            String taskName = dec[j].get(k).getName();
                            String dueDate = dec[j].get(k).getDate();
                            String priority = dec[j].get(k).getPriority();
                            String tag = dec[j].get(k).getTag();
                            myWriter.write("\n{\nName: " + taskName + "\n");
                            myWriter.write("Date: " + dueDate + "\n");
                            myWriter.write("Priority: " + priority + "\n");
                            myWriter.write("Tag: " + tag + "\n");
                            myWriter.write("}\n");
                            myWriter.close();
    
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error writing to file");
                    e.printStackTrace();
                }
            }

        }     
        
    }

    public ArrayList<Task> readArray(String currentDate){

        int date = dateFinder(currentDate);
        String month = monthFinder(currentDate);
        

        if(month.equals("jan")){
            return jan[date-1];
        }
        else if(month.equals("feb")){
            return feb[date-1];
        }
        else if(month.equals("mar")){
            return mar[date-1];
        }
        else if(month.equals("april")){
            return april[date-1];
        }
        else if(month.equals("may")){
            return may[date-1];
        }
        else if(month.equals("june")){
            return june[date-1];
        }
        else if(month.equals("jul")){
            return jul[date-1];
        }
        else if(month.equals("aug")){
            return aug[date-1];
        }
        else if(month.equals("sept")){
            return sept[date-1];
        }
        else if(month.equals("oct")){
            return oct[date-1];
        }
        else if(month.equals("nov")){
            return nov[date-1];
        }
        else{
            return dec[date-1];
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
                Task newTask = new Task(taskName, dueDate, priority, flag);
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
