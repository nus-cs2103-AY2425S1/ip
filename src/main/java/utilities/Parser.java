package utilities;
import java.util.Scanner;

import command.Command;
import command.ExitCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import command.DeleteCommand;
import command.TaskCommand;
import command.FindCommand;
import exception.FormatException;
import exception.NoInputException;
import task.TaskList;

/**
 * Parser class is used to parse user input and execute commands.
 */
public class Parser {
    /**
     * Static method to add horizontal lines and indentation to the dialog.
     * @param dialog The dialog to be formatted.
     * @return The formatted dialog.
     */
    public static String addHorizontalLinesAndIndentation(String dialog) {
        StringBuilder res = new StringBuilder("    ____________________________________________________________\n");
        Scanner sc = new Scanner(dialog);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            res.append("      ").append(line.trim()).append("\n"); // Ensure consistent indentation
        }
        res.append("    ____________________________________________________________");
        sc.close();
        return res.toString();
    }

    /**
     * Method to parse user input returning executable commands.
     * @param dialog The user input.
     * @param taskList The task list.
     * @return The command indicated by the user dialog.
     * @throws FormatException If the format of the user input is not suitable.
     * @throws NoInputException If no input is given.
     */

    public Command parseUserInput(String dialog, TaskList taskList) throws FormatException, NoInputException {
        if (dialog.isEmpty()) {
            throw new NoInputException();
        }
        if (dialog.equals("bye")) {
            return new ExitCommand();
        } else if (dialog.equals("list")) {
            return new ListCommand(taskList);
        } else if (dialog.startsWith("mark")) {
            try {
                int index = Integer.parseInt(dialog.substring(5));
                return new MarkCommand(index, taskList);
            } catch (NumberFormatException e) {
                throw new FormatException("mark");
            }
        } else if (dialog.startsWith("unmark")) {
            try {
                int index = Integer.parseInt(dialog.substring(7));
                return new UnmarkCommand(index, taskList);
            } catch (NumberFormatException e) {
                throw new FormatException("unmark");
            }
        } else if (dialog.startsWith("delete")) {
            try {
                int index = Integer.parseInt(dialog.substring(7));
                return new DeleteCommand(index, taskList);
            } catch (NumberFormatException e) {
                throw new FormatException("delete");
            }
        } else if (dialog.startsWith("find")) {
            String keyword = dialog.substring(5);
            return new FindCommand(keyword, taskList);
        } else {
            return new TaskCommand(dialog, taskList);
        }
    }

    /**
     * Method to get the next command from the user.
     * @param sc The scanner to read the user input.
     * @param taskList The task list.
     * @return The command to be executed.
     */
    public Command getNextCommand(Scanner sc, TaskList taskList) {
        try {
            String input = sc.nextLine();
            return parseUserInput(input, taskList);
        } catch (FormatException e) {
            System.out.println("FormatException: " + e.getMessage());
            return null;
        } catch (NoInputException e) {
            System.out.println("NoInputException: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
}
