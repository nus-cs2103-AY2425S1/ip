package bestie;

import java.time.format.DateTimeParseException;

import bestie.command.AddCommand;
import bestie.command.Command;
import bestie.command.DeleteCommand;
import bestie.command.ExitCommand;
import bestie.command.FindCommand;
import bestie.command.InvalidCommand;
import bestie.command.ListCommand;
import bestie.command.MarkCommand;
import bestie.command.UnmarkCommand;
import bestie.task.Deadline;
import bestie.task.Event;
import bestie.task.Todo;


/**
 * Creates an instance of the parser to understand user input and executes command.
 */
public class Parser {
    /**
     * Parses user input to understand command and execute the corresponding command.
     *
     * @param userInput user input through CLI
     * @return command object corresponding to user's command input
     */
    public static Command parse(String userInput) {
        String[] parts = userInput.split(" ");
        String commandWord = parts[0]; // first word only!!!

        switch(commandWord) {
        case("bye"):
            return new ExitCommand();

        case("list"):
            return new ListCommand(); // display list of all tasks

        case("mark"):
            try {
                return new MarkCommand(Integer.parseInt(parts[1]) - 1);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please indicate the index of the task you want to mark as complete");
            }
            break;

        case("unmark"):
            try {
                return new UnmarkCommand(Integer.parseInt(parts[1]) - 1);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please indicate the index of the task you want to unmark.");
            }
            break;

        case("find"):
            return new FindCommand(parts[1]);

        case("delete"):
            return new DeleteCommand(Integer.parseInt(parts[1]) - 1);

        case("todo"):
            try {
                return new AddCommand(new Todo(userInput.substring(5)));
            } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
                System.out.println("The description of a todo cannot be empty. Please input your todo again!");
            }
            break;

        case("deadline"):
            try {
                String[] partsOfDeadline = userInput.split(" /by ");
                String description = partsOfDeadline[0].substring(9);
                String deadline = partsOfDeadline[1];
                return new AddCommand(new Deadline(description, deadline));
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                System.out.println("You did not input the deadline in a valid format.");
                System.out.println("Please follow the format \"deadline (name of task) /by (deadline)\"");

            } catch (DateTimeParseException e) {
                System.out.println("You did not input the date and time in the correct format.");
                System.out.println("Please stick to the correct format: YYYY-MM-DD HHMM");
            }
            break;

        case("event"):
            try {
                String[] partsOfEvent = userInput.split("/");
                String description = partsOfEvent[0].substring(6).trim();
                String start = partsOfEvent[1].substring(5).trim();
                String end = partsOfEvent[2].substring(3).trim();
                return new AddCommand(new Event(description, start, end));
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                System.out.println("You did not input the event in a valid format.");
                System.out.println("Please follow the format \"event (name of event) /from (start time) "
                        + "/to (end time)\"");
            } catch (DateTimeParseException e) {
                System.out.println("You did not input the date and time in the correct format.");
                System.out.println("Please stick to the correct format: YYYY-MM-DD HHMM");
            }
            break;

        default:
            return new InvalidCommand();
        }

        return null;

    }


}
