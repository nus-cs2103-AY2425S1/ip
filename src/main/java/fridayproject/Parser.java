package fridayproject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * Represents a parser that parses the user input and executes the corresponding command.
 * A parser has a task list, storage and ui.
 */
public class Parser {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /*
     * Constructor for a parser.
     * @param taskList The task list of the parser.
     * @param storage The storage of the parser.
     * @param ui The ui of the parser.
     * Example: Parser parser = new Parser(taskList, storage, ui);
     */
    public Parser(TaskList taskList, Storage storage, Ui ui) {

        // Assertions to ensure that the task list, storage and ui are not null
        assert taskList != null : "Task list should not be null";
        assert storage != null : "Storage should not be null";
        assert ui != null : "Ui should not be null";

        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /*
     * Parses the user input and executes the corresponding command.
     * @param input The user input.
     */
    public String parseCommand(String inputString) {

        // Assertions to ensure that the input is not null
        assert inputString != null : "Input should not be null";

        try {
            if (inputString.equals("hello")) {
                return ui.displayWelcome(); // Display the welcome message
            } else if (inputString.equals("bye")) {
                return ui.displayFarewell(); // Exit the program
            } else if (inputString.equals("list")) {
                return listTasks();  // Show the list of tasks
            } else if (inputString.startsWith("mark ")) {
                return markTask(inputString); // Mark a task as done
            } else if (inputString.startsWith("unmark ")) {
                return unmarkTask(inputString); // Mark a task as not done
            } else if (inputString.startsWith("delete ")) {
                return deleteTask(inputString); // Delete a task
            } else if (inputString.startsWith("todo ")) {
                return addTodo(inputString); // Add a todo task
            } else if (inputString.startsWith("deadline ")) {
                return addDeadline(inputString); // Add a deadline task
            } else if (inputString.startsWith("event ")) {
                return addEvent(inputString); // Add an event task
            } else if (inputString.startsWith("find ")) {
                return findWord(inputString); // Find tasks with a keyword in the description
            } else {
                return ui.displayUnknownCommandError(); // Display an error message for unknown commands
            }
        } catch (FridayException | IOException e) {
            return ui.displayError(e.getMessage());
        }
    }


    /*
     * Lists the tasks in the task list.
     * @return The list of tasks.
     * Example: listTasks();
     */
    private String listTasks() {
        if (taskList.size() == 0) {
            return "Your task list is empty.";
        }
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append((i + 1) + ". " + taskList.getTask(i).getTypeIcon() + taskList.getTask(i) + "\n");
        }
        return sb.toString().trim();
    }

    /*
     * Marks a task as done.
     * @param input The user input.
     * @return The message indicating that the task has been marked as done.
     * @throws FridayException If the task number is invalid.
     * @throws IOException If an I/O error occurs.
     * Example: markTask("mark 1");
     */
    private String markTask(String input) throws FridayException, IOException {
        assert input != null : "Input should not be null";

        if (input.length() < 6) {
            throw new FridayException("I'm sorry, but I don't know what that means :(((\n" 
                + "Please enter a valid task number.");
        }
        int taskNumber = Integer.parseInt(input.substring(5)) - 1;
        assert taskNumber >= 0 && taskNumber < taskList.size() : "Task number should not be out of range";

        taskList.markTaskAsDone(taskNumber);
        storage.saveTasksToFile(taskList.getTasks());
        return "Nice! I've marked this task as done:\n  " + taskList.getTask(taskNumber).getTypeIcon() 
            + taskList.getTask(taskNumber).toString();
    }

    /*
     * Marks a task as not done.
     * @param input The user input.
     * @return The message indicating that the task has been marked as not done.
     * @throws FridayException If the task number is invalid.
     * @throws IOException If an I/O error occurs.
     * Example: unmarkTask("unmark 1");
     */
    private String unmarkTask(String input) throws FridayException, IOException {
        assert input != null : "Input should not be null";
        
        if (input.length() < 8) {
            throw new FridayException("I'm sorry, but I don't know what that means :(((\n" 
                + "Please enter a valid task number.");
        }
        int taskNumber = Integer.parseInt(input.substring(7)) - 1;
        assert taskNumber >= 0 && taskNumber < taskList.size() : "Task number should not be out of range";

        taskList.markTaskAsUndone(taskNumber);
        storage.saveTasksToFile(taskList.getTasks());
        return "OK, I've marked this task as not done yet:\n  " 
            + taskList.getTask(taskNumber).getTypeIcon() 
            + taskList.getTask(taskNumber).toString();
    }

    /*
     * Deletes a task.
     * @param input The user input.
     * @return The message indicating that the task has been deleted.
     * @throws FridayException If the task number is invalid.
     * @throws IOException If an I/O error occurs.
     * Example: deleteTask("delete 1");
     */
    private String deleteTask(String input) throws FridayException, IOException {
        assert input != null : "Input should not be null";

        if (input.length() < 8) {
            throw new FridayException("I'm sorry, but I don't know what that means :(((\n" 
                + "Please enter a valid task number.");
        }
        int taskNumber = Integer.parseInt(input.substring(7)) - 1;
        assert taskNumber >= 0 && taskNumber < taskList.size() : "Task number should not be out of range";

        if (taskNumber >= 0 && taskNumber < taskList.size() && taskList.getTask(taskNumber) != null) {
            Tasks deletedTask = taskList.deleteTask(taskNumber);
            storage.saveTasksToFile(taskList.getTasks());
            return "Noted. I've removed this task:\n  " + deletedTask.getTypeIcon() + deletedTask.toString() 
                + "\nNow you have " + taskList.size() + " tasks in the list.";
        }
        return "false";
    }

    /*
     * Adds a todo task.
     * @param input The user input.
     * @return The message indicating that the todo task has been added.
     * @throws FridayException If the task description is invalid.
     * @throws IOException If an I/O error occurs.
     * Example: addTodo("todo read book");
     */
    private String addTodo(String input) throws FridayException, IOException {
        assert input != null : "Input should not be null";

        if (input.length() < 6) {
            throw new FridayException("I'm sorry, but I don't know what that means :(((\n" 
                + "Please enter a valid task description.");
        }
        Tasks todo = new Todo(input.substring(5).trim());
        taskList.addTask(todo);
        storage.saveTasksToFile(taskList.getTasks());
        return "Got it. I've added this task:\n  " + todo.getTypeIcon() 
            + todo.toString() + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /*
     * Adds a deadline task.
     * @param input The user input.
     * @return The message indicating that the deadline task has been added.
     * @throws FridayException If the task description or date is invalid.
     * @throws IOException If an I/O error occurs.
     * Example: addDeadline("deadline return book /by 2021-09-30");
     */
    private String addDeadline(String input) throws FridayException, IOException {
        assert input != null : "Input should not be null";

        if (input.length() < 10) {
            throw new FridayException("I'm sorry, but I don't know what that means :(((\n" 
                + "Please enter a valid task description.");
        }
        String remainingInput = input.substring(input.indexOf(" ") + 1);
        String[] deadlineParts = remainingInput.split(" /by ");

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(deadlineParts[1], formatter);
            Tasks deadline = new Deadline(deadlineParts[0] , date.toString());
            taskList.addTask(deadline);
            storage.saveTasksToFile(taskList.getTasks());
            return "Got it. I've added this task:\n  " + deadline.getTypeIcon() 
                + deadline.toString() + "\nNow you have " + taskList.size() + " tasks in the list.";
        } catch (Exception e) {
            throw new FridayException("Invalid date format! Please enter in yyyy-MM-dd.");
        }
    }

    /*
     * Adds an event task.
     * @param input The user input.
     * @return The message indicating that the event task has been added.
     * @throws FridayException If the task description or date is invalid.
     * @throws IOException If an I/O error occurs.
     * Example: addEvent("event project meeting /at 2021-09-30 14:00 to 16:00");
     */
    private String addEvent(String input) throws FridayException, IOException {
        assert input != null : "Input should not be null";

        if (input.length() < 7) {
            throw new FridayException("I'm sorry, but I don't know what that means :(((\n" 
                + "Please enter a valid task description.");
        }
        String remainingInput = input.substring(input.indexOf(" ") + 1);
        String[] eventParts = remainingInput.split(" /at ");
        String[] startEnd = eventParts[1].split(" to ");
        Tasks event = new Event(eventParts[0], startEnd[0], startEnd[1]);
        taskList.addTask(event);
        storage.saveTasksToFile(taskList.getTasks());
        return "Got it. I've added this task:\n  " + event.getTypeIcon() 
            + event.toString() + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /*
     * Finds tasks that contain a specific keyword.
     * @param input The user input.
     * @return The list of tasks that contain the keyword.
     * @throws FridayException If the keyword is invalid.
     * @throws IOException If an I/O error occurs.
     * Example: findWord("find book");
     */
    private String findWord(String input) throws FridayException, IOException {
        assert input != null : "Input should not be null";
        
        String keyword = input.substring(5).trim();
        if (keyword.isEmpty()) {
            throw new FridayException("The keyword for the find command cannot be empty.\n" 
                + "Please enter a valid keyword.");
        }
        return taskList.findTasks(keyword);
    }
}
