import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This is an abstract class to define a task object.
 */
public abstract class Task {
    
    /** Description of the task */
    protected String description;
    /** Determine if the task is marked or not */
    protected boolean isChecked;
    /** Date time format for printing LocalDateTime objects */
    public static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    /**
     * Creates a Task object with the given description.
     */
    Task(String description) {
        this.description = description;
        isChecked = false;
    }

    /**
     * Creates a task object based on its task type.
     * <p>
     * This is a factory method used to create a task based
     * on its corrosponding given task type.
     * @param taskDetails A list of task information used to create the task object.
     * @return A task object of its given task type.
     */
    public static Task createTask(String[] taskDetails) {
        Task task = null;
        switch (taskDetails[0]) {
        case "TODO":
            task = new ToDoTask(taskDetails[1]);
            break;
            
        case "DEADLINE":
            task = new DeadlineTask(taskDetails[1], LocalDateTime.parse(taskDetails[3], Task.DATE_FORMAT));
            break;
            
        case "EVENT":
            task = new EventTask(taskDetails[1], LocalDateTime.parse(taskDetails[3], Task.DATE_FORMAT), LocalDateTime.parse(taskDetails[4], Task.DATE_FORMAT));
            break;
        }
        return task;
    }

    /**
     * Updates the task status to be marked.
     * <p>
     * The function checks if the task is already marked. If it is, 
     * it will print a message notifying the user that the task has already marked.
     */
    public void mark() {

        // Check if the task is already marked or not
        if (!isChecked) {
            this.isChecked = true;
            System.out.println("Nice! I've marked this task as done:");
            
        } else {
            System.out.println("This task is already marked as done!");
        }

        // Print the task
        System.out.println(this.toString());
    }

    /**
     * Updates the task to be unmarked.
     * <p>
     * The function checks if the task is already unmarked. If it is, 
     * it will print a message notifying the user that the task has already unmarked.
     */
    public void unmark() {

        // Check if the task is already unmarked
        if (isChecked) {
            this.isChecked = false;
            System.out.println("OK, I've marked this task as not done yet:");
            
        } else {
            System.out.println("This task is already unmarked!");
        }

        // Print the task
        System.out.println(this.toString());
    }

    /**
     * Converts a task into a csv format.
     * <p>
     * The task object will be converted into a string format where its information
     * is seperated by a comma.
     * 
     * @return A string representation of the task to be saved into a csv file.
     */
    public String toCSVFormat() {
        
        return this.description + "," + this.isChecked;
    }

    @Override
    public String toString() {
        if (isChecked) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
