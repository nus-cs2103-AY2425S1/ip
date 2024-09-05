package ui;

import commandparser.CommandParser;
import taskmanager.TaskManager;

/**
 * Handles user input and interaction with the task management system.
 */
public class Ui {
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
    public String handleInput(String input, boolean isSilent) {
        if (input.equalsIgnoreCase("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);
        } else if (input.equalsIgnoreCase("list")) {
            return taskManager.listTasks();
        } else if (input.startsWith("mark ")) {
            return this.parser.handleMark(input);
        } else if (input.startsWith("unmark ")) {
            return this.parser.handleUnmark(input);
        } else if (input.startsWith("delete ")) {
            return this.parser.handleDelete(input);
        } else if (input.startsWith("deadline ")) {
            return this.parser.handleDeadline(input, isSilent);
        } else if (input.startsWith("todo ")) {
            return this.parser.handleTodo(input, isSilent);
        } else if (input.startsWith("event ")) {
            return this.parser.handleEvent(input, isSilent);
        } else if (input.startsWith("find ")) {
            return this.parser.handleFind(input);
        }
        return "Sorry, I am not sure what task this is! Please enter a valid task.";
    }



}
