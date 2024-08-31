package park.parser;

import park.exceptions.ParkException;
import park.commands.AddCommand;
import park.commands.Command;
import park.commands.DeleteCommand;
import park.commands.ExitCommand;
import park.commands.ListCommand;
import park.commands.MarkCommand;
import park.commands.UnmarkCommand;
import park.task.Deadline;
import park.task.Event;
import park.task.Task;
import park.task.ToDo;

import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Returns a Command based on user input.
     *
     * @param userInput User input.
     * @return Command object to be executed.
     * @throws ParkException If user input is not in the correct format.
     */
    public static Command parse(String userInput) throws ParkException {
        if (userInput.equals("bye")) {
            return new ExitCommand();
        } else if (userInput.equals("list")) {
            return new ListCommand();
        } else if (userInput.startsWith("mark")) {
            try {
                String strIndex = userInput.substring(5);
                int index = Integer.parseInt(strIndex) - 1;
                return new MarkCommand(index);
            } catch (IndexOutOfBoundsException e) {
                throw new ParkException("missing index");
            } catch (NumberFormatException e) {
                throw new ParkException("invalid index");
            }
        } else if (userInput.startsWith("unmark")) {
            try {
                String strIndex = userInput.substring(7);
                int index = Integer.parseInt(strIndex) - 1;
                return new UnmarkCommand(index);
            } catch (IndexOutOfBoundsException e) {
                throw new ParkException("missing index");
            } catch (NumberFormatException e) {
                throw new ParkException("invalid index");
            }
        } else if (userInput.startsWith("delete")) {
            try {
                String strIndex = userInput.substring(7);
                int index = Integer.parseInt(strIndex) - 1;
                return new DeleteCommand(index);
            } catch (IndexOutOfBoundsException e) {
                throw new ParkException("missing index");
            } catch (NumberFormatException e) {
                throw new ParkException("invalid index");
            }
        } else if (userInput.startsWith("todo")) {
            try {
                String charAfterCommand = getChar(userInput, 4);
                if (!charAfterCommand.equals(" ")) {
                    throw new ParkException("invalid input");
                }
                String desc = userInput.substring(5);
                if (desc.isEmpty()) {
                    throw new ParkException("please provide a description");
                }
                Task t = new ToDo(desc);
                return new AddCommand(t);
            } catch (IndexOutOfBoundsException e) {
                throw new ParkException("please provide a description");
            }
        } else if (userInput.startsWith("deadline")) {
            try {
                String charAfterCommand = getChar(userInput, 8);
                if (!charAfterCommand.equals(" ")) {
                    throw new ParkException("invalid input");
                }
                String[] str = userInput.split(" /by ");
                String desc = str[0].substring(9);
                String by = str[1];
                if (desc.isEmpty() || by.isEmpty()) {
                    throw new ParkException("please provide a description and/or deadline");
                }
                Task t = new Deadline(desc, by);
                return new AddCommand(t);
            } catch (IndexOutOfBoundsException e) {
                throw new ParkException("please use the format: desc /by deadline");
            } catch (DateTimeParseException e) {
                throw new ParkException("please input DateTime in format: yyyy-MM-dd HHmm");
            }
        } else if (userInput.startsWith("event")) {
            try {
                String charAfterCommand = getChar(userInput, 5);
                if (!charAfterCommand.equals(" ")) {
                    throw new ParkException("invalid input");
                }
                String[] str = userInput.split(" /");
                String desc = str[0].substring(6);
                String start = str[1].substring(5);
                String end = str[2].substring(3);
                if (desc.isEmpty() || start.isEmpty() || end.isEmpty()) {
                    throw new ParkException("please provide desc, start, end");
                }
                Task t = new Event(desc, start, end);
                return new AddCommand(t);
            } catch (IndexOutOfBoundsException e) {
                throw new ParkException("please use the format: desc /from start /to end");
            } catch (DateTimeParseException e) {
                throw new ParkException("please input DateTime in format: yyyy-MM-dd HHmm");
            }
        } else {
            throw new ParkException("invalid input");
        }
    }

    private static String getChar(String str, int i) {
        return Character.toString(str.charAt(i));
    }
}
