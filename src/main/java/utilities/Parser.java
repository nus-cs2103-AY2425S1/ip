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
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";

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

        String trimmedDialog = dialog.trim();
        String[] parts = trimmedDialog.split("\\s+", 2);
        String command = parts[0].toLowerCase();

        switch (command) {
            case EXIT_COMMAND:
                return new ExitCommand();
            case LIST_COMMAND:
                return new ListCommand(taskList);
            case MARK_COMMAND:
            case UNMARK_COMMAND:
            case DELETE_COMMAND:
                return handleIndexCommand(command, parts, taskList);
            case FIND_COMMAND:
                return handleFindCommand(parts, taskList);
            default:
                return new TaskCommand(trimmedDialog, taskList);
        }
    }

    private Command handleIndexCommand(String command, String[] parts, TaskList taskList) throws FormatException {
        if (parts.length < 2) {
            throw new FormatException(command);
        }
        try {
            int index = Integer.parseInt(parts[1]);
            switch (command) {
                case MARK_COMMAND:
                    return new MarkCommand(index, taskList);
                case UNMARK_COMMAND:
                    return new UnmarkCommand(index, taskList);
                case DELETE_COMMAND:
                    return new DeleteCommand(index, taskList);
                default:
                    throw new IllegalArgumentException("Unknown index command: " + command);
            }
        } catch (NumberFormatException e) {
            throw new FormatException(command);
        }
    }

    private Command handleFindCommand(String[] parts, TaskList taskList) throws FormatException {
        if (parts.length < 2) {
            throw new FormatException(FIND_COMMAND);
        }
        return new FindCommand(parts[1], taskList);
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
        } catch (FormatException | NoInputException e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("发生未知错误: " + e.getMessage());
            return null;
        }
    }
}
