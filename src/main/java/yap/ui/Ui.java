package yap.ui;

import yap.task.TaskList;

/**
 * Class that implements the interaction with a user.
 */
public class Ui {
    private TaskList taskList;
    private Command command;

    /**
     * Constructor for the Ui class.
     *
     * @param taskList The tasklist object that is storing all tasks for the program.
     */
    public Ui(TaskList taskList) {
        this.taskList = taskList;
        this.command = new Command(this.taskList);
    }

    /**
     * Reacts to the user's input
     *
     * @param userInput A string containing the user's input
     * @return Returns the string that describes the action taken.
     * @throws InputException Throws an input exception if the user inputs an invalid command
     */
    public String reactToUserInput(String userInput) throws InputException {
        if (userInput.equalsIgnoreCase("bye")) {
            System.out.println("Bye! It was really nice talking to you, see you soon :)");
            return "";
        }

        // Mark functionality
        if (userInput.startsWith("mark")) {
            command.mark(userInput);
            return "";
        }

        // Unmark functionality
        if (userInput.startsWith("unmark")) {
            command.unmark(userInput);
            return "";
        }

        // Delete functionality
        if (userInput.startsWith("delete")) {
            command.delete(userInput);
            return "";
        }

        // If user adds a todo task
        if (userInput.startsWith("todo")) {
            String outputLine = command.todo(userInput);
            return command.todo(userInput);
        }

        // If user adds a deadline task
        if (userInput.startsWith("deadline")) {
            String outputLine = command.deadline(userInput);
            return outputLine;
        }

        // If user adds an event task
        if (userInput.startsWith("event")) {
            String outputLine = command.event(userInput);
            return outputLine;
        }

        // If user adds a fixed duration task
        if (userInput.startsWith("fixedduration")) {
            String outputLine = command.fixedduration(userInput);
            return outputLine;
        }

        // List functionality
        if (userInput.startsWith("list")) {
            String outputLine = command.list();
            return outputLine;
        }

        if (userInput.startsWith("find")) {
            String outputLine = command.find(userInput);
            return outputLine;
        }

        throw new InputException();
    }
}
