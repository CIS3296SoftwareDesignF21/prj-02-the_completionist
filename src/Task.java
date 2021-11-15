import java.util.Calendar;

public class Task {
    private String name;
    private String date;
    private int priority;
    private String tag;
    

    public Task(){ }

    public Task(String name, String date, int priority, String tag){
        this.name = name;
        this.date = date;
        this.priority = priority;
        this.tag = tag;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String toString(){
        return "Task Name: " + name + "\nTask Date: " + date + "\nTask Priority: " + priority + "\nTask Tag: " + tag;
    }
}
