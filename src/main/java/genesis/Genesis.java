package genesis;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a generic task.
 */


class Task {

    protected String description;
    protected boolean isComplete;

    protected String input;

    /**
     * Constructs a Task with the specified description and input.
     *
     * @param description The description of the task.
     * @param input The input used to create the task.
     */

    public Task (String description, String input) {
        this.description = description;
        this.isComplete = false;
        this.input = input;
    }
    /**
     * Returns the original input used to create the task.
     *
     * @return The input string of the task.
     */
    public String getInput() {
        return this.input;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        String marked;
        if (isComplete) {
            marked = "[X] ";
        } else {
            marked = "[ ] ";
        }
        return marked + this.description;
    }
    /**
     * Marks the task as complete.
     */
    public void mark() {
        this.isComplete = true;
    }

    /**
     * Unmarks the task as incomplete.
     */
    public void unmark() {
        this.isComplete = false;
    }
}
/**
 * Represents a Todo task.
 */
class Todo extends Task {
    /**
     * Constructs a Todo with the specified description and input.
     *
     * @param description The description of the todo task.
     * @param input The input used to create the todo task.
     */
    public Todo (String description, String input) {
        super(description, input);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return The string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
/**
 * Represents a Deadline task with a specific due date.
 */
class Deadline extends Task {
    protected LocalDate deadline;
    /**
     * Constructs a Deadline task with the specified description, deadline, and input.
     *
     * @param description The description of the deadline task.
     * @param deadline The due date of the deadline task.
     * @param input The input used to create the deadline task.
     */
    public Deadline (String description, LocalDate deadline, String input) {
        super(description, input);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

/**
 * Represents an Event task with a specific start and end time.
 */
class Event extends Task {
    protected LocalDate startTime;
    protected LocalDate endTime;
    /**
     * Constructs an Event task with the specified description, start time, end time, and input.
     *
     * @param description The description of the event task.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     * @param input The input used to create the event task.
     */
    public Event (String description, LocalDate startTime, LocalDate endTime, String input) {
        super(description, input);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    /**
     * Returns a string representation of the event task.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + this.endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
/**
 * Manages a list of tasks.
 */
class TaskManager {
    protected ArrayList<Task> tasks;

    /**
     * Constructs a TaskManager.
     */
    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }


    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     * @param isSilent If true, suppress output messages.
     */
    public void addTask(Task task, boolean isSilent) {
        tasks.add(task);
        if (!isSilent) {
            System.out.println("Got it. I've added this task:\n" + task.toString() + "\nNow you have " + tasks.size()
                    + " tasks in the list.");
        }
    }
    /**
     * Lists all the tasks in the task list.
     */

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks in the list.");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
    /**
     * Marks the task with the given index.
     *
     * @param index Index of the task to mark.
     */
    public void markTask(int index) {
        if (isValidIndex(index)) {
            tasks.get(index).mark();
            System.out.println("Nice! I've marked this task as done:\n" + tasks.get(index).description);
        }
    }
    /**
     * Unmarks the task with the given index.
     *
     * @param index Index of the task to unmark.
     */
    public void unmarkTask(int index) {
        if (isValidIndex(index)) {
            tasks.get(index).unmark();
            System.out.println("Ok. I've marked this task as not done yet:\n" + tasks.get(index).description);
        }
    }
    /**
     * Deletes the task with the given index.
     *
     * @param index Index of the task to delete.
     */
    public void deleteTask(int index) {
        if (isValidIndex(index)) {
            Task removedTask = tasks.remove(index);
            System.out.println("Noted. I have removed the following task: \n" + removedTask.toString() +
                    "\nNow you have " + tasks.size() + " tasks in the list.");
        }
    }

    /**
     * Checks if index given is valid.
     *
     * @param index Index of the task to check.
     */

    private boolean isValidIndex(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println("No such task exists!");
            return false;
        }
        return true;
    }
}
/**
 * Handles loading tasks from and writing tasks to a file.
 */
class Storage {
    protected TaskManager taskManager;
    protected CommandParser commandParser;

    /**
     * Constructs a Storage object with the specified TaskManager.
     *
     * @param taskManager The TaskManager associated with this storage.
     */

    public Storage(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Loads tasks from a file and populates the TaskManager.
     *
     * @param commandParser The command parser used to handle commands.
     * @param ui The UI component to handle user inputs.
     */
    public void loadTasks(CommandParser commandParser, Ui ui) {
        File f = new File("data/tasks.txt");
        if (f.exists()) {
            try (Scanner s = new Scanner(f)) {
                while (s.hasNext()) {
                    ui.handleInput(s.nextLine(), true);
                }

            } catch (IOException e) {
                System.out.println("Error loading tasks from file: " + e.getMessage());
            }
        } else {
            System.out.println("Error. Data file does not exist!");
        }
    }

    /**
     * Writes the current list of tasks to a file.
     */
    public void writeTasks() {
        try (FileWriter fw = new FileWriter("data/tasks.txt")) {
            ArrayList<Task> tasks = this.taskManager.tasks;
            for (Task task: tasks) {
                fw.write(task.getInput() + System.lineSeparator());
            }
            fw.close();

        } catch (IOException e) {
            System.out.println("File does not exist!");
        }

    }
}
/**
 * Handles user input and interaction with the task management system.
 */
class Ui {
    protected TaskManager taskManager;
    protected CommandParser parser;

    /**
     * Constructs a Ui with the specified TaskManager and CommandParser.
     *
     * @param taskManager The TaskManager to manage tasks.
     * @param commandParser The CommandParser to handle commands.
     */
    public Ui (TaskManager taskManager, CommandParser commandParser) {
        this.taskManager = taskManager;
        this.parser = commandParser;
    }
    /**
     * Processes user input and delegates to the appropriate command handler.
     *
     * @param input The user input to process.
     * @param isSilent If true, suppress output messages.
     */
    public void handleInput(String input, boolean isSilent) {
        if (input.equalsIgnoreCase("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);
        } else if (input.equalsIgnoreCase("list")) {
            taskManager.listTasks();
        } else if (input.startsWith("mark ")) {
            this.parser.handleMark(input);
        } else if (input.startsWith("unmark ")) {
            this.parser.handleUnmark(input);
        } else if (input.startsWith("delete ")) {
            this.parser.handleDelete(input);
        } else if (input.startsWith("deadline ")) {
            this.parser.handleDeadline(input, isSilent);
        } else if (input.startsWith("todo ")) {
            this.parser.handleTodo(input, isSilent);
        } else if (input.startsWith("event ")) {
            this.parser.handleEvent(input, isSilent);
        } else if (input.startsWith("find ")) {
            this.parser.handleFind(input);
        } else {
            System.out.println("Sorry, I am not sure what task this is! Please enter a valid task.");
        }
    }



}
/**
 * Parses and executes user commands for task management.
 */
class CommandParser {
    protected TaskManager taskManager;
    protected Storage storage;
    /**
     * Constructs a CommandParser with the specified TaskManager and Storage.
     *
     * @param taskManager The TaskManager to manage tasks.
     * @param storage The Storage to handle task persistence.
     */
    public CommandParser(TaskManager taskManager, Storage storage) {
        this.taskManager = taskManager;
        this.storage = storage;
    }

    /**
     * Marks a task as complete based on user input.
     *
     * @param input The user input containing the task index to mark.
     */
    public void handleMark(String input) {
        try {
            int index = Integer.parseInt(input.substring(5)) - 1;
            taskManager.markTask(index);
            this.storage.writeTasks();
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number!");
        }
    }

    /**
     * Unmarks a task as incomplete based on user input.
     *
     * @param input The user input containing the task index to unmark.
     */
    public void handleUnmark(String input) {
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            taskManager.unmarkTask(index);
            this.storage.writeTasks();
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number!");
        }
    }

    /**
     * Deletes a task based on user input.
     *
     * @param input The user input containing the task index to delete.
     */
    public void handleDelete(String input) {
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            taskManager.deleteTask(index);
            this.storage.writeTasks();
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number!");
        }
    }

    public void handleFind(String input) {
        String search = input.replaceFirst("find ","").trim();
        System.out.println("Here are the matching tasks in your list: ");
        int counter = 1;
        for (Task task : taskManager.tasks) {
            if (task.toString().contains(search)) {
                System.out.print(counter + ". ");
                System.out.println(task.toString());
                counter++;
            }
        }
    }
    /**
     * Adds a deadline task based on user input.
     *
     * @param input The user input containing the task description and deadline.
     * @param isSilent If true, suppress output messages.
     */
    public void handleDeadline(String input, boolean isSilent) {
        if (!input.contains("/by ")) {
            System.out.println("You need a deadline to add this task!");
            return;
        }
        String[] parts = input.split("/by ");
        String taskName = parts[0].replaceFirst("deadline ", "").trim();
        if (taskName.isEmpty()) {
            System.out.println("You need a task description!");
            return;
        }
        String initial = parts[1].trim();
        LocalDate deadline = LocalDate.parse(initial);
        Deadline deadlineTask = new Deadline(taskName, deadline, input);
        taskManager.addTask(deadlineTask, isSilent);
        this.storage.writeTasks();
    }

    /**
     * Adds a todo task based on user input.
     *
     * @param input The user input containing the task description.
     * @param isSilent If true, suppress output messages.
     */
    public void handleTodo(String input, boolean isSilent) {
        String taskName = input.replaceFirst("todo ", "").trim();
        if (taskName.isEmpty()) {
            System.out.println("You need a task description!");
            return;
        }
        Todo todoTask = new Todo(taskName, input);
        taskManager.addTask(todoTask, isSilent);
        this.storage.writeTasks();
    }

    /**
     * Adds an event task based on user input.
     *
     * @param input The user input containing the task description, start, and end time.
     * @param isSilent If true, suppress output messages.
     */
    public void handleEvent(String input, boolean isSilent) {
        if (!input.contains("/from ") || !input.contains("/to ")) {
            System.out.println("You need a starting and ending date to add this task!");
            return;
        }
        String[] parts = input.split("/from ");
        String[] dateParts = parts[1].split("/to ");
        String taskName = parts[0].replaceFirst("event ", "").trim();
        if (taskName.isEmpty()) {
            System.out.println("You need a task description!");
            return;
        }
        String initialStartDate = dateParts[0].trim();
        String initialEndDate = dateParts[1].trim();
        LocalDate startDate = LocalDate.parse(initialStartDate);
        LocalDate endDate = LocalDate.parse(initialEndDate);
        Event eventTask = new Event(taskName, startDate, endDate, input);
        taskManager.addTask(eventTask, isSilent);
        this.storage.writeTasks();
    }
}

/**
 * The main class for running the Genesis task management application.
 */
public class Genesis {

    /**
     * The entry point of the Genesis application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        System.out.println("Hello! I'm Genesis!\nWhat can I do for you?\n");

        TaskManager taskManager = new TaskManager();
        Storage storage = new Storage(taskManager);
        CommandParser parser = new CommandParser(taskManager, storage);
        Ui ui = new Ui(taskManager, parser);
        storage.loadTasks(parser, ui);


        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            ui.handleInput(input, false);
        }
    }
}

