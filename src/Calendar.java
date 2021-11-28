import java.io.*;
import java.util.*;

public class Calendar {
    private ArrayList<Task>[] jan, feb, mar, april, may, june, jul, aug, sept, oct, nov, dec;

    public static void main(String[] args){
      Calendar cal = new Calendar();

        if(cal.addTask(new Task("do Homework", "11/1/2021", 1, "true")) == -1){
            System.out.println("adding task failed");
        }
        else{
            System.out.println("test added successfully");
        }

        cal.addTask(new Task("go to grocery store", "11/1/2021", 2, "true"));
        cal.addTask(new Task("go to the mall", "11/2/2021", 3, "true"));

        System.out.println(cal.nov[0].toString());
        System.out.println("\n\n\n");
        System.out.println(Arrays.toString(cal.readArray("11/01/2021")));
    }
    public Calendar(){

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
                            int priority = jan[j].get(k).getPriority();
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
        }     
        
    }

    public Object[] readArray(String currentDate){

        int date = dateFinder(currentDate);
        String month = monthFinder(currentDate);
        

        if(month.equals("jan")){
            return jan[date-1].toArray();
        }
        else if(month.equals("feb")){
            return feb[date-1].toArray();
        }
        else if(month.equals("mar")){
            return mar[date-1].toArray();
        }
        else if(month.equals("april")){
            return april[date-1].toArray();
        }
        else if(month.equals("may")){
            return may[date-1].toArray();
        }
        else if(month.equals("june")){
            return june[date-1].toArray();
        }
        else if(month.equals("jul")){
            return jul[date-1].toArray();
        }
        else if(month.equals("aug")){
            return aug[date-1].toArray();
        }
        else if(month.equals("sept")){
            return sept[date-1].toArray();
        }
        else if(month.equals("oct")){
            return oct[date-1].toArray();
        }
        else if(month.equals("nov")){
            return nov[date-1].toArray();
        }
        else{
            return dec[date-1].toArray();
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
