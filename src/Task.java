import java.util.Calendar;

public class Task {
    private String name;
    private String date;
    private int priority;
    private String flag;
    private int index;
    private Task next;

    public Task(String name, String date, int priority, String flag){
        this.name = name;
        this.date = date;
        this.priority = priority;
        this.flag = flag;
        this.next = null;
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
