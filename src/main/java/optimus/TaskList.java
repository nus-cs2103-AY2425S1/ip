package optimus;

import java.util.ArrayList;

// Let ChatGPT check and suggest comments and JavaDocs according to CS2103T style guide
/**
 * Represents the list of tasks and provides methods to manipulate the task list.
 */
public class TaskList {
    private ArrayList<Task> taskList; // Stores the list of tasks

    /**
     * Constructs a new empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param taskList The list of tasks to initialize with.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.taskList;
    }

    /**
     * Marks a task as done by its index in the list.
     * Provides a user-friendly message if the index is invalid.
     *
     * @param index The index of the task to mark as done.
     * @throws OptimusException If the index is out of bounds.
     */
    public void markTaskAsDone(int index) throws OptimusException{
        // Check if index is within the valid range
        // Asked ChatGPT to advise on error checking
        if (index < 0 || index >= taskList.size()) {
            throw new OptimusException("Sorry, you only have up to task number " + taskList.size() + ".");
        }
        // Mark the task as done
        Task task = taskList.get(index);
        task.isDone = true;
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Deletes a task by its index in the list.
     * Provides a user-friendly message if the index is invalid.
     *
     * @param taskIndex The index of the task to delete.
     * @throws OptimusException If the index is out of bounds.
     */
    public void delete(int taskIndex) throws OptimusException{
        // Check if taskIndex is within the valid range
        // Asked ChatGPT to advise on error checking
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new OptimusException("Sorry, you only have up to task number " + taskList.size() + ".");
        }
        // Remove the task from the list
        System.out.println("Noted, I've removed this task:");
        System.out.println(taskList.get(taskIndex));
        taskList.remove(taskIndex);
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
    }

    /**
     * Adds a task to the taskList based on user input.
     * optimus.Task can be of type Todo, Deadline, or Event.
     *
     * @param userInput The input string containing task details.
     * @throws OptimusException If the input format is invalid.
     */
    public void addTask(String userInput) throws OptimusException {
        Task task;
        // Check and create task based on input type (todo, deadline, or event)
        // Used ChatGPT to find how to extract relevant words from user input
        if (userInput.startsWith("todo")) {
            if (userInput.length() <= 5) { // Check if description is provided
                throw new OptimusException("The description of a todo cannot be empty >:(");
            }
            String description = userInput.substring(5).trim();
            task = new Todo(description);
        } else if (userInput.startsWith("deadline")) {
            if (userInput.length() <= 9) { // Check if description is provided
                throw new OptimusException("The description of a deadline cannot be empty >:(");
            }
            String[] parts = userInput.substring(9).split("/by");
            if (parts.length < 2) { // Ensure input is valid
                throw new OptimusException("Invalid input. Use this format: deadline return book /by 2019-12-02");
            }
            String description = parts[0].trim();
            String by = parts[1].trim();
            task = new Deadline(description, by);
        } else if (userInput.startsWith("event")) {
            if (userInput.length() <= 6) { // Check if description is provided
                throw new OptimusException("The description of an event cannot be empty >:(");
            }
            String[] parts = userInput.substring(6).split ("/from|/to");
            if (parts.length < 3) { // Ensure input is valid
                throw new OptimusException("Invalid input. Use this format: event project meeting /from 2019-12-02 /to 2019-12-03");
            }
            String description = parts[0].trim();
            String from = parts[1].trim();
            String to = parts[2].trim();
            task = new Event(description, from, to);
        } else {
            throw new OptimusException("Sorry, you need to start your input with either todo, deadline, or event.\n" +
                    "For example:\n" +
                    "todo borrow book\n" +
                    "deadline return book /by 2019-12-02\n" +
                    "event project meeting /from 2019-12-02 /to 2019-12-03");
        }
        // Add the created task to the task list
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}
