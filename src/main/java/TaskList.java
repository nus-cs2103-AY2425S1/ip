import java.util.ArrayList;
import java.util.List;
// ArrayList implementation, auto-adjust the indexing after deletion
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

    public Task deleteTask(int index) {
        Task deleted = this.items.get(index);
        this.items.remove(index);
        this.size = this.size - 1;
        return deleted;
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
