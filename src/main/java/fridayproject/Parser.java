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
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /*
     * Parses the user input and executes the corresponding command.
     * @param input The user input.
     */
    public String parseCommand(String inputString) {
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

    private String markTask(String input) throws FridayException, IOException {
        if (input.length() < 6) {
            throw new FridayException("I'm sorry, but I don't know what that means :(((\n" 
                + "Please enter a valid task number.");
        }
        int taskNumber = Integer.parseInt(input.substring(5)) - 1;
        taskList.markTaskAsDone(taskNumber);
        storage.saveTasksToFile(taskList.getTasks());
        return "Nice! I've marked this task as done:\n  " + taskList.getTask(taskNumber).getTypeIcon() 
            + taskList.getTask(taskNumber).toString();
    }

    private String unmarkTask(String input) throws FridayException, IOException {
        if (input.length() < 8) {
            throw new FridayException("I'm sorry, but I don't know what that means :(((\n" 
                + "Please enter a valid task number.");
        }
        int taskNumber = Integer.parseInt(input.substring(7)) - 1;
        taskList.markTaskAsUndone(taskNumber);
        storage.saveTasksToFile(taskList.getTasks());
        return "OK, I've marked this task as not done yet:\n  " 
            + taskList.getTask(taskNumber).getTypeIcon() 
            + taskList.getTask(taskNumber).toString();
    }

    private String deleteTask(String input) throws FridayException, IOException {
        if (input.length() < 8) {
            throw new FridayException("I'm sorry, but I don't know what that means :(((\n" 
                + "Please enter a valid task number.");
        }
        int taskNumber = Integer.parseInt(input.substring(7)) - 1;
        if (taskNumber >= 0 && taskNumber < taskList.size() && taskList.getTask(taskNumber) != null) {
            Tasks deletedTask = taskList.deleteTask(taskNumber);
            storage.saveTasksToFile(taskList.getTasks());
            return "Noted. I've removed this task:\n  " + deletedTask.getTypeIcon() + deletedTask.toString() 
                + "\nNow you have " + taskList.size() + " tasks in the list.";
        }
        return "false";
    }

    private String addTodo(String input) throws FridayException, IOException {
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

    private String addDeadline(String input) throws FridayException, IOException {
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

    private String addEvent(String input) throws FridayException, IOException {
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

    private String findWord(String input) throws FridayException, IOException {
        String keyword = input.substring(5).trim();
        if (keyword.isEmpty()) {
            throw new FridayException("The keyword for the find command cannot be empty.\n" 
                + "Please enter a valid keyword.");
        }
        return taskList.findTasks(keyword);
    }
}
