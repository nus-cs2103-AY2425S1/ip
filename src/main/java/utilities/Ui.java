package utilities;
import java.util.Scanner;

import command.Command;
import command.ExitCommand;
import exception.FormatException;
import exception.NoInputException;
import task.Task;
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
     * Method to display the list of tasks.
     */
    public void displayList() {
        String lString = "";
        int index = 1;
        for (Task task : taskList) {
            lString += String.valueOf(index) + "."  + task.toString();
            if (index == taskList.size()) {
                break;
            }
            lString += "\n";
            index++;
        }
        lString = Parser.addHorizontalLinesAndIndentation(lString);
        System.out.println(lString);
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

    /**
     * Method to start the interaction with the user and only exit when the user 
     * inputs "bye".
     */
    public void startInteraction() {
        Scanner sc = new Scanner(System.in);
        printWelcomeMessage();

        while (true) {
            String dialog = sc.nextLine();
            Command command = interactWithUser(dialog);
            if (command instanceof ExitCommand) {
                break;
            }
        }
        sc.close();
    }

}
