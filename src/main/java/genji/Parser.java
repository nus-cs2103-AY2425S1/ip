package genji;

import genji.command.Command;
import genji.command.AddCommand;
import genji.command.DateCommand;
import genji.command.DeleteCommand;
import genji.command.ExitCommand;
import genji.command.FindCommand;
import genji.command.ListCommand;
import genji.command.MarkCommand;
import genji.command.UnmarkCommand;
import genji.task.ToDo;
import genji.task.Deadline;
import genji.task.Event;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * A class to parse user's commands
 */
public class Parser {

    /**
     *Parses user's command into different types of commands
     * Creates the task if the command ask to
     * @param command user's input
     * @return The corresponding command according to user's input
     * @throws GenjiException When user's command is invalid
     */
    public static Command parse(String command) throws GenjiException {
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            if (command.length() > 4) {
                throw new GenjiException("Please don't add things after \"list\"");
            } else {
                return new ListCommand();
            }
        } else if (command.startsWith("mark")) {
            if (command.length() < 6) {
                throw new GenjiException("No descriptions detected " +
                        "or did not enter space, try again");
            } else {
                try {
                    int index = Integer.parseInt(command.substring(5)) - 1;
                    return new MarkCommand(index);
                } catch (NumberFormatException n) {
                    throw new GenjiException("Please input a integer after \"mark\"");
                }
            }
        } else if (command.startsWith("unmark")) {
            if (command.length() < 8) {
                throw new GenjiException("No descriptions detected " +
                        "or did not enter space, try again");
            } else {
                try {
                    int index = Integer.parseInt(command.substring(7)) - 1;
                    return new UnmarkCommand(index);
                } catch (NumberFormatException n) {
                    throw new GenjiException("Please input a integer after \"unmark\"");
                }
            }
        } else if (command.startsWith("todo")) {
            if (command.length() < 6) {
                throw new GenjiException("No descriptions detected " +
                        "or did not enter space, try again");
            } else {
                ToDo td = new ToDo(command.substring(5));
                return new AddCommand(td);
            }
        } else if (command.startsWith("deadline")) {
            if (command.length() < 10) {
                throw new GenjiException("No descriptions detected, try again");
            } else {
                try {
                    String name = command.substring(9, command.lastIndexOf(" /"));
                    LocalDateTime time = LocalDateTime.parse(command.substring(command.lastIndexOf(" /") + 5));
                    Deadline ddl = new Deadline(name, time);
                    return new AddCommand(ddl);
                } catch (StringIndexOutOfBoundsException s) {
                    throw new GenjiException("Due date not provided or not in the proper form");
                } catch (DateTimeParseException d) {
                    throw new GenjiException("Please provide date time in this format\"yyyy-mm-ddThh:mm\"");
                }
            }
        } else if (command.startsWith("event")) {
            if (command.length() < 7) {
                throw new GenjiException("No descriptions detected, try again");
            } else {
                try {
                    String name = command.substring(6, command.lastIndexOf(" /from"));
                    LocalDateTime from = LocalDateTime.parse(command.substring(command.lastIndexOf(" /from") + 7,
                            command.lastIndexOf(" /to")));
                    LocalDateTime to = LocalDateTime.parse(command.substring(command.lastIndexOf(" /to") + 5));
                    Event evt = new Event(name, from, to);
                    return new AddCommand(evt);
                } catch (StringIndexOutOfBoundsException s) {
                    throw new GenjiException("Time period not provided or not in the proper form");
                } catch (DateTimeParseException d) {
                    throw new GenjiException("Please provide date time in this format\"yyyy-mm-ddThh:mm\"");
                }
            }
        } else if (command.startsWith("delete")) {
            if (command.length() < 8) {
                throw new GenjiException("No descriptions detected " +
                        "or did not enter space, try again");
            } else {
                try {
                    int index = Integer.parseInt(command.substring(7)) - 1;
                    return new DeleteCommand(index);
                } catch (NumberFormatException n) {
                    throw new GenjiException("Please input a integer after \"delete\"");
                }
            }
        } else if (command.startsWith("date")) {
            if (command.length() < 6) {
                throw new GenjiException("No descriptions detected, try again");
            } else {
                try {
                    String date = command.substring(5);
                    return new DateCommand(date);
                } catch (DateTimeParseException d) {
                    throw new GenjiException("Please provide date time in this format\"yyyy-mm-dd\"");
                }
            }
        } else if (command.startsWith("find")) {
            if (command.length() < 6) {
                throw new GenjiException("No descriptions detected, try again");
            } else {
                String taskDescription = command.substring(5);
                return new FindCommand(taskDescription);
            }
        }
        throw new GenjiException("Invalid command, try to start with \"todo\" \"deadline\"" +
                " \"event\", type \"list\" \"date\" \"find\", or type \"bye\" to end");
    }
}
