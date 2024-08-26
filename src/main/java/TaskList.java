import java.util.ArrayList;
import java.time.LocalDateTime;

/**
* This class provides functionality to keep track of tasks.
* It can add, remove and update task status.
*/
public class TaskList {

    /** List to store tasks */
    private ArrayList<Task> toDoList;

    /**
     * Constructor to create a TaskList object.
     */
    TaskList () {
        this.toDoList = new ArrayList<Task>();
    }

    /**
     * Retrieves the list of tasks stored by Quack.
     * @return A list of tasks.
     */
    public ArrayList<Task> getToDoList() {
        return this.toDoList;
    }

    /**
     * Updates the task staus by marking or unmarking tasks.
     * <p>
     * If the user inputs mark, then it will mark the task.
     * <p>
     * If the user inputs unmark, then it will unmark the task.
     * <p>
     * If the index input is out of bounds it will throw a invalid index exception.
     * 
     * @param idx Index of the task inside the task list.
     * @param command To state weather to mark or unmark the task.
     * @throws InvalidIndexException If the index is < 0 or if it is >= the size of the task list.
     */
    public void updateTask(int idx, String command) throws InvalidIndexException{

        // Check if the index if out of bounds
        if (idx < 0 || idx >= toDoList.size()) {
            throw new InvalidIndexException(idx);
        }

        // Run the correct function base on the command given by the user
        if (command.equals("mark")) {
            this.toDoList.get(idx).mark();
        } else {
            this.toDoList.get(idx).unmark();
        }
    }

    public void addTask(Task task) {
        this.toDoList.add(task);
    }

    /**
     * Deletes the task from the list.
     * <p>
     * If the index input is out of bounds it will throw a invalid index exception.
     * 
     * @param idx Index of the task inside the task list.
     * @throws InvalidIndexException If the index is < 0 or if it is >= the size of the task list.
     */
    public void deleteTask(int idx) throws InvalidIndexException{

        // Check if the index if out of bounds
        if (idx < 0 || idx >= toDoList.size()) {
            throw new InvalidIndexException(idx);
        }

        // Remove the task from the list
        Task removedTask = this.toDoList.remove(idx);

        // Print a fomrifmation message for the user
        System.out.println("Alright I have removed this task into the list: \n" + removedTask.toString()
                        + "\nYou now have " + this.toDoList.size() + " tasks in your list right now!");
    }

    /**
     * Converts tasks to a csv format for saving.
     * 
     * @returns An array of tasks in their csv format.
     */
    public ArrayList<String> convertTasksToCSVFormat () {

        ArrayList<String> savedData = new ArrayList<String>();

        for (Task t : this.toDoList) {
            String taskInCSVFormat = t.toCSVFormat();
            savedData.add(taskInCSVFormat);
        }

        return savedData;
    }


    @Override
    public String toString() {
        if (this.toDoList.size() == 0) {
            return "The list is empty, why not add something!";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.toDoList.size(); i++) {
                if (i == 0) {
                    sb.append((i + 1) + ". " + this.toDoList.get(i).toString());
                } else {
                    sb.append("\n" + (i + 1) + ". " + this.toDoList.get(i).toString());
                }
            }
            return sb.toString();
        }
    }

}
