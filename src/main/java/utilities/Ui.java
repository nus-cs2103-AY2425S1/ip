package utilities;

import command.Command;
import exception.FormatException;
import exception.NoInputException;
import task.TaskList;

/**
 * Ui class is used to interact with the user via CLI.
 */
public class Ui {
    private TaskList taskList;

    /**
     * Constructor for Ui.
     * @param taskList The task list.
     */
    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Method to print the welcome message.
     */
    public void printWelcomeMessage() {
        String hi = "Hello! I'm Foo\n" +
                "What can I do for you?";
        System.out.println(Parser.addHorizontalLinesAndIndentation(hi));
    }

    /**
     * Method to print the goodbye message.
     */
    public void printGoodbyeMessage() {
        System.out.println(Parser
            .addHorizontalLinesAndIndentation(
                "Bye. Hope to see you again soon!"));
    }

    /**
     * Method to show the loading error.
     */
    public void showLoadingError() {
        System.out.println(
            Parser.addHorizontalLinesAndIndentation(
                "Error loading data from file."));
    }

    /**
     * Method to interact with the user and execute the command.
     * @param dialog The user input.
     * @return The command to be executed.
     */
    public Command interactWithUser(String dialog) {
        assert taskList != null : "Task list should not be null";
        Parser parser = new Parser();
        Command command = parser.parseUserInput(dialog, taskList);
        try {
            command.execute();
        } catch (FormatException e) {
            System.out.println(e.getMessage());
        } catch (NoInputException e) {
            System.out.println(e.getMessage());
        }
        return command;
    }

    

}
