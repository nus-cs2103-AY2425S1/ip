import java.util.ArrayList;

public class Janet {
    private static final String horizontalLine = "____________________________________________________________";
    private final TaskList tasks;
    private int taskIndex;

    Janet() {
        this.tasks = new TaskList();
        this.taskIndex = 0;
    }

    Janet(ArrayList<Task> listOfTasks) {
        this.tasks = new TaskList(listOfTasks);
        this.taskIndex = listOfTasks.size();
    }


    /**
     * @return the listOfTasks ArrayList
     */
    public ArrayList<Task> getListOfTasks() {
        return tasks.getListOfTasks();
    }

    /**
     * Level 2 - Add, list
     * @param task a String representation of the task that is to be added into the listOfTasks.
     * @param taskSymbol a String representation of the task's symbol (T, D, E).
     * @return a String message to indicate successful addition of task into listOfTasks.
     */
    public String addTaskToList(String task, String taskSymbol) {
        Task newTask = new Task(task, taskSymbol);
        this.tasks.addTaskToList(newTask);
        this.taskIndex++;
        return horizontalLine + "\n" + String.format("added: %s\n", task) + horizontalLine;
    }


    /**
     * Level 2 - Add, list
     * @return a String representation (in numbered list format) of the current tasks inside the listOfTasks
     */
    public String showList() {
        String currentList = horizontalLine + "\nHere are the tasks in your list:\n";
        if (taskIndex == 0) {
            // empty listOfTasks
            return currentList + "*** Current list is empty ***\n" + horizontalLine;
        }
        for (int i = 0; i < taskIndex; i++) {
            if (i == taskIndex - 1) {
                currentList += (i+1) + ". " + this.tasks.getListOfTasks().get(i) + "\n" + horizontalLine;
                break;
            }
            currentList += (i+1) + ". " + this.tasks.getListOfTasks().get(i) + "\n";
        }
        return currentList;
    }
}
