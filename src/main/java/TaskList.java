import java.util.ArrayList;

/**
* This class provides functionality to keep track of tasks.
* It can add, remove and update task status.
*/
public class TaskList {

    /** List to store tasks */
    private ArrayList<Task> toDo;

    /**
     * Constructor to create a TaskList object.
     */
    TaskList () {
        this.toDo = new ArrayList<Task>();
    }

    /**
     * Updates the task staus by marking or unmarking tasks.
     * 
     * @param idx Index of the task inside the task list.
     * @param command To state weather to mark or unmark the task.
     * @throws InvalidIndexException If the index is < 0 or if it is >= the size of the task list.
     */
    public void updateTask(int idx, String command) throws InvalidIndexException{

        // Check if the index if out of bounds
        if (idx < 0 || idx >= toDo.size()) {
            throw new InvalidIndexException(idx);
        }

        // Run the correct function base on the command given by the user
        if (command.equals("mark")) {
            this.toDo.get(idx).mark();
        } else {
            this.toDo.get(idx).unmark();
        }
    }
    
    /**
     * Creates a task object and adds it into the list.
     * 
     * @param taskName Description of the task.
     * @param taskType The type of the task.
     * @param startDate The start date of the task.
     * @param endDate The end date of the task.
     */
    public void addTask(String taskName, String taskType, String startDate, String endDate) {

        // Create a new task object based on he task type
        Task newTask;
        if (taskType.equals("TODO")) {
            newTask = new ToDoTask(taskName);
        } else if (taskType.equals("DEADLINE")) {
            newTask = new DeadlineTask(taskName, endDate);
        } else {
            newTask = new EventTask(taskName, startDate, endDate);
        }

        // Add the new task into the list
        this.toDo.add(newTask);

        // Print a comfirmation message for the user
        System.out.println("Alright I have added this task into the list: \n" + newTask.toString()
                        + "\nYou now have " + this.toDo.size() + " tasks in your list right now!");
    }

    /**
     * Deletes the task from the list.
     * 
     * @param idx Index of the task inside the task list.
     * @throws InvalidIndexException If the index is < 0 or if it is >= the size of the task list.
     */
    public void deleteTask(int idx) throws InvalidIndexException{

        // Check if the index if out of bounds
        if (idx < 0 || idx >= toDo.size()) {
            throw new InvalidIndexException(idx);
        }

        // Remove the task from the list
        Task removedTask = this.toDo.remove(idx);

        // Print a fomrifmation message for the user
        System.out.println("Alright I have removed this task into the list: \n" + removedTask.toString()
                        + "\nYou now have " + this.toDo.size() + " tasks in your list right now!");
    }

    @Override
    public String toString() {
        if (this.toDo.size() == 0) {
            return "The list is empty, why not add something!";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.toDo.size(); i++) {
                if (i == 0) {
                    sb.append((i + 1) + ". " + this.toDo.get(i).toString());
                } else {
                    sb.append("\n" + (i + 1) + ". " + this.toDo.get(i).toString());
                }
            }
            return sb.toString();
        }
    }

}
