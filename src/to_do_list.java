import java.util.*;

public class to_do_list {
    private ArrayList<Task> to_do_list;

    public to_do_list(){
        this.to_do_list = new ArrayList<>();
    }

    public void add_task(Task task){
        to_do_list.add(task);
    }

    public void remove_task(Task task){
        to_do_list.remove(task);

    }

    public void print(){
        for(int i = 0; i < to_do_list.size(); i++){
            System.out.println(to_do_list.get(i).toString());
            System.out.println();
        }
    }

}
