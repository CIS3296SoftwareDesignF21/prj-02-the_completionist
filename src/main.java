import java.io.FileNotFoundException;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        Calendar cal = new Calendar();
        cal.addTask();
        cal.printCalendar();
      
        to_do_list toDoList = new to_do_list();
        toDoList.add_task( new Task("do Homework", "11/9/21", 1, "true"));
        toDoList.add_task( new Task("workout", "11/9/21", 3, "true"));
        toDoList.add_task( new Task("meet with project group", "11/9/21", 2, "true"));
        toDoList.print();
    }
}
