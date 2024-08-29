import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> tasks;
    
    // Public constructor for TaskList
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /*
     * adds task to the tasklist
     * @param Task task
     * @return void
     */
    public void addTask(Task task) throws Meowception {
        try {
            tasks.add(task);
        } catch (Exception e) {
            throw new Meowception("");
        }
    } 

    /*
     * removes tasks at specified index
     * @param int index
     * @return void
     */
    public void deleteTask(int index) throws Meowception {
        if (index < 0 || index >= tasks.size()) {
            throw new Meowception("404");
        }
        tasks.remove(index);
    }


}
