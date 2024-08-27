import java.util.ArrayList;

/**
* This class provides functionality to keep track of tasks.
* It can add, remove and update task status.
*/
public class TaskList {

    /** Track how many tasks are in the list */
    private int length;
    /** List to store tasks */
    private ArrayList<Task> toDoList;

    /**
     * Constructor to create a TaskList object.
     */
    TaskList () {
        this.toDoList = new ArrayList<Task>();
        length = 0;
    }

    /**
     * Retrieves the list of tasks stored by Quack.
     * @return A list of tasks.
     */
    public ArrayList<Task> getToDoList() {
        return this.toDoList;
    }

    /**
     * Retrieves the list of tasks stored by Quack.
     * @return A list of tasks.
     */
    public int getLength() {
        return this.length;
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
     * @param index Index of the task inside the task list.
     * @param command To state weather to mark or unmark the task.
     * @throws InvalidIndexException If the index is < 0 or if it is >= the size of the task list.
     */
    public void updateTask(String index, String command) throws InvalidIndexException{

        try {
            int idx = Integer.parseInt(index) - 1;
            // Check if the index if out of bounds
            if (idx < 0 || idx >= toDoList.size()) {
                throw new InvalidIndexException(index);
            }

            // Run the correct function base on the command given by the user
            if (command.equals("mark")) {
                this.toDoList.get(idx).mark();
            } else {
                this.toDoList.get(idx).unmark();
            }
        } catch (NumberFormatException numError) {
            throw new InvalidIndexException(index);
        }
    }

    public void addTask(Task task) {
        this.toDoList.add(task);
        length = this.toDoList.size();
    }

    /**
     * Deletes the task from the list.
     * <p>
     * If the index input is out of bounds it will throw a invalid index exception.
     * 
     * @param index Index of the task inside the task list.
     * @throws InvalidIndexException If the index is < 0 or if it is >= the size of the task list.
     */
    public void deleteTask(String index) throws InvalidIndexException{

        try {
            int idx = Integer.parseInt(index) - 1;
            // Check if the index if out of bounds
            if (idx < 0 || idx >= toDoList.size()) {
                throw new InvalidIndexException(index);
            }
    
            // Remove the task from the list
            Task removedTask = this.toDoList.remove(idx);
            length = this.toDoList.size();
    
            // Print a fomrifmation message for the user
            System.out.println("Alright I have removed this task into the list: \n" + removedTask.toString()
                            + "\nYou now have " + this.toDoList.size() + " tasks in your list right now!");
        } catch (NumberFormatException numError) {
            throw new InvalidIndexException(index);
        }
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
