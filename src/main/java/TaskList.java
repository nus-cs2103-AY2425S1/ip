import java.util.ArrayList;
import java.time.LocalDateTime;

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
     * <p>
     * Based on the task type, it will create the corrosponding task object.
     * 
     * @param taskName Description of the task.
     * @param taskType The type of the task.
     * @param startDate The start date of the task.
     * @param endDate The end date of the task.
     * @throws InvalidIndexException If the date is in the past or start date >= end date.
     */
    public void addTask(String taskName, String taskType, LocalDateTime startDate, LocalDateTime endDate) throws InvalidDateTimeException{

        // Create a new task object based on he task type
        Task newTask;
        if (taskType.equals("TODO")) {
            newTask = new ToDoTask(taskName);
        } else if (taskType.equals("DEADLINE")) {
            if (endDate.isBefore(LocalDateTime.now())){
                throw new InvalidDateTimeException("Oops I cannot add the task because the due date is in the past!");
            }
            newTask = new DeadlineTask(taskName, endDate);
        } else {
            if (startDate.isAfter(endDate)) {
                throw new InvalidDateTimeException("Oops I cannot add the task because the end date entered comes before the start date!");
            } else if (endDate.isBefore(LocalDateTime.now()) || startDate.isBefore(LocalDateTime.now())){
                throw new InvalidDateTimeException("Oops I cannot add the task because the start/end date is in the past!");
            }
            newTask = new EventTask(taskName, startDate, endDate);
        }

        // Add the new task into the list
        this.toDo.add(newTask);

        // Print a comfirmation message for the user
        System.out.println("Alright I have added this task into the list: \n" + newTask.toString()
                        + "\nYou now have " + this.toDo.size() + " tasks in your list right now!");
    }

    /**
     * Creates a task object and adds it into the list.
     * <p>
     * This function creates tasks based on what is saved inside the csv save file.
     * 
     * @param taskName Description of the task.
     * @param taskType The type of the task.
     * @param startDate The start date of the task.
     * @param endDate The end date of the task.
     * @param isMarked Indicate weather the task is marked or not.
     */
    public void addTask(String taskName, String taskType, LocalDateTime startDate, LocalDateTime endDate, boolean isMarked){

        // Create a new task object based on he task type
        Task newTask;
        if (taskType.equals("TODO")) {
            newTask = new ToDoTask(taskName);
        } else if (taskType.equals("DEADLINE")) {
            newTask = new DeadlineTask(taskName, endDate);
        } else {
            newTask = new EventTask(taskName, startDate, endDate);
        }

        if (isMarked) {
            newTask.mark();
        }

        // Add the new task into the list
        this.toDo.add(newTask);
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
        if (idx < 0 || idx >= toDo.size()) {
            throw new InvalidIndexException(idx);
        }

        // Remove the task from the list
        Task removedTask = this.toDo.remove(idx);

        // Print a fomrifmation message for the user
        System.out.println("Alright I have removed this task into the list: \n" + removedTask.toString()
                        + "\nYou now have " + this.toDo.size() + " tasks in your list right now!");
    }

    /**
     * Converts tasks to a csv format for saving.
     * 
     * @returns An array of tasks in their csv format.
     */
    public ArrayList<String> convertTasksToCSVFormat () {

        ArrayList<String> savedData = new ArrayList<String>();

        for (Task t : this.toDo) {
            String taskInCSVFormat = t.toCSVFormat();
            savedData.add(taskInCSVFormat);
        }

        return savedData;
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
