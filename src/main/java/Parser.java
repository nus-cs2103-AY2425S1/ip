import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {
    public Command parse(String input, Ui ui) throws InvalidCommandException {
        try {
            if (input.startsWith("list")) {
                if (input.equals("list")) {
                    return new ListCommand();
                } else {
                    return new MatchListCommand();
                }
            }
            if (input.startsWith("mark")) {
                String arg = input.substring("mark".length()).trim();
                if (arg.isEmpty()) {
                    throw new EmptyFieldException();
                }
                return new MarkCommand(Integer.parseInt(arg) - 1);
            }

            if (input.startsWith("unmark")) {
                String arg = input.substring("unmark".length()).trim();
                if (arg.isEmpty()) {
                    throw new EmptyFieldException();
                }
                return new UnmarkCommand(Integer.parseInt(arg) - 1);
            }

            if (input.equals("I need help.")) {
                return new HelpCommand();
            }

            if (input.startsWith("delete")) {
                String arg = input.substring("delete".length()).trim();
                if (arg.isEmpty()) {
                    throw new EmptyFieldException();
                }
                return new DeleteCommand(Integer.parseInt(arg) - 1);
            }


            if (input.startsWith("deadline") || input.startsWith("todo") || input.startsWith("event")) {
                Task task = null;
                if (input.startsWith("deadline")) {
                    String by = input.substring(input.indexOf("/by") + "/by".length()).trim();
                    String description = input.substring(
                            "deadline".length(),
                            input.indexOf("/by")).trim();
                    if (by.isEmpty() || description.isEmpty()) {
                        throw new EmptyFieldException();
                    }
                    task = new Deadline(description, parseDatetime(by));
                } else if (input.startsWith("todo")) {
                    String description = input.substring(
                            "todo".length()).trim();
                    if (description.isEmpty()) {
                        throw new EmptyFieldException();
                    }
                    task = new ToDo(description);
                } else if (input.startsWith("event")) {
                    String from = input.substring(
                            input.indexOf("/from") + "/from".length(),
                            input.indexOf("/to")).trim();
                    String to = input.substring(input.indexOf("/to") + "/to".length()).trim();
                    String description = input.substring(
                            "event".length(),
                            input.indexOf("/from")).trim();
                    if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        throw new EmptyFieldException();
                    }
                    task = new Event(description, parseDatetime(from), parseDatetime(to));

                }
                return new AddCommand(task);
            }
            throw new InvalidCommandException();

        } catch(NumberFormatException e) {
            ui.show("Seems like at least one of the arguments to this command was\n" +
                    "not a number when it should have been.");
            ui.advise();
        } catch(StringIndexOutOfBoundsException e) {
            ui.show("Seems like the command keyed wasn't appropriately used. You may have\n" +
                    "given insufficient information. Also check that the order in which\n" +
                    "the information was given is correct.");
            ui.advise();
        } catch(EmptyFieldException e) {
            ui.show("Field(s) may not be blank.");
            ui.advise();
        } catch(DateTimeParseException e) {
            ui.show("Sorry, I only accept datetime inputs of yyyy-MM-dd HHmm");
        }
        return new ExitCommand();

    }

    private LocalDateTime parseDatetime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(input, formatter);
    }
}
