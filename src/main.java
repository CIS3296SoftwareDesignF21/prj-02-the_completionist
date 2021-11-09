import java.io.FileNotFoundException;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        Calendar cal = new Calendar();
        cal.addTask();
        cal.printCalendar();
    }
}
