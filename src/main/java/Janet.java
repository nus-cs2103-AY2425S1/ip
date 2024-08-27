import java.util.ArrayList;

public class Janet {
    private final TaskList tasks;

    Janet() {
        this.tasks = new TaskList();
    }

    Janet(ArrayList<Task> listOfTasks) {
        this.tasks = new TaskList(listOfTasks);
    }


    /**
     * @return the listOfTasks ArrayList
     */
    public ArrayList<Task> getListOfTasks() {
        return tasks.getListOfTasks();
    }

}
