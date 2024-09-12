package ui;

import exceptions.AliceException;
import parser.Parser;
import storage.TaskList;
import tasks.Task;

/**
 * Responsible for printing output to the terminal, and reading input from the terminal.
 * Encapsulates a parser and a scanner for reading user input.
 */
public class Ui {
    private final Parser parser;

    /**
     * Initialises a Ui object.
     *
     * @param list the list to start the parser.
     */
    public Ui(TaskList list) {
        parser = new Parser(list, this);
    }

    /**
     * Returns the welcome message.
     */
    public String showWelcomeMessage() {
        return "Hello! I am Alice!\nWhat can I do for you?";
    }

    /**
     * Returns the exit message
     */
    public String showExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns the response of Alice.
     *
     * @param input the user's input.
     * @return the response.
     */
    public String handleUserInput(String input) {
        try {
            return parser.parse(input);
        } catch (AliceException e) {
            return e.toString();
        }
    }

    /**
     * Returns the custom message when task is marked or unmarked.
     *
     * @param task the task which was handled.
     * @param command "mark" or "unmark".
     * @return the custom message.
     */
    public String getHandleTaskMessage(Task task, String command) {
        if (command.equals("mark")) {
            return "Noted! I've marked this task as done: \n" + task;
        } else {
            return "Ok, I've marked this task as not done yet: \n" + task;
        }
    }

    /**
     * Returns the custom message when task is deleted or added.
     *
     * @param task the task that was handled.
     * @param command "delete" or "todo" or "deadline" or "event".
     * @param size number of tasks in the list.
     * @return the custom message.
     */
    public String getHandleTaskMessage(Task task, String command, int size) {
        StringBuilder s = new StringBuilder();
        if (command.equals("delete")) {
            s.append("Noted. I've removed this task: \n");
        } else {
            s.append("Got it. I've added this task: \n");
        }
        s.append(task).append("\n");
        s.append("Now you have ").append(size).append(" tasks in the list");
        return s.toString();
    }

    /**
     * Returns a string of all the tasks in the list.
     *
     * @param size number of tasks in the list.
     * @param tasks the string representation of tasks by priority.
     * @return tasks in the list.
     */
    public String getListedTasks(int size, String ... tasks) {
        return "Here are the tasks in your list: \n" +
                "HIGH PRIORITY: \n" + tasks[0] + "\n\n" +
                "MEDIUM PRIORITY: \n" + tasks[1] + "\n\n" +
                "LOW PRIORITY: \n" + tasks[2] + "\n\n" +
                "Now you have a total of " + size + " tasks in the list";
    }

    /**
     * Returns the filtered tasks.
     *
     * @param tasks String of tasks to be displayed.
     * @return the filtered tasks.
     */
    public String getFilteredTasks(String tasks) {
        return "Here are the matching tasks in your list: \n" + tasks;
    }
}
