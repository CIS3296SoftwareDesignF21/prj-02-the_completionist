import java.util.Calendar;

public class task {
    private String name;
    private String date;
    private int priority;
    private String flag;

    public task(){ }

    public task(String name, String date, int priority, String flag){
        this.name = name;
        this.date = date;
        this.priority = priority;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
