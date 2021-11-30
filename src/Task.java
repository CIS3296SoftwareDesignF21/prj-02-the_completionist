import java.util.Calendar;

public class Task {
    private String name;
    private String date;
    private String priority;
    private String tag;
    private boolean complete;

    public Task(String name, String date, String priority, String tag){
        this.name = name;
        this.date = date;
        this.priority = priority;
        this.tag = tag;
        this.complete = false;
    }
    public void setComplete(boolean ans){
        this.complete = ans;
    }

    public boolean getComplete(){
        return complete;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
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
