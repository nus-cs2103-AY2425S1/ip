import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> items;
    private int size;

    TaskList(){
        items = new ArrayList<>();
        size = 0;
    }

    public void addTask(Task newTask) {
        items.add(newTask);
        size += 1;
    }

    public int getSize(){
        return this.size;
    }


    public String markTask(int index){
        Task task = items.get(index);
        task.markDone();
        return task.toString();
    }

    public String unmarkTask(int index){
        Task task = items.get(index);
        task.unmark();
        return task.toString();
    }



    public void printActions(){
        for (int i = 1; i <= size; i++){
            System.out.printf("%,d. %s%n", i ,items.get(i-1).toString());
        }
    }




}
