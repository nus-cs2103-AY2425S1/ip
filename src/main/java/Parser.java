/**
 * Parses user input
 */
public class Parser {

    private TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    public Command parse(String input) {

        if (input.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (input.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (input.toLowerCase().startsWith("mark ") || input.equalsIgnoreCase("mark")) {
            return new MarkCommand(input);
        } else if (input.toLowerCase().startsWith("unmark ") || input.equalsIgnoreCase("unmark")) {
            return new UnmarkCommand(input);
        } else if (input.toLowerCase().startsWith("delete ") || input.equalsIgnoreCase("delete")) {
            return new DeleteCommand(input);
        } else {
            //Handle Task input
            if (input.toLowerCase().startsWith("todo ")) {
                return new AddTodoCommand(input);
            } else if (input.toLowerCase().startsWith("deadline ")) {
                return new AddDeadlineCommand(input);
            } else if (input.toLowerCase().startsWith("event ")) {
                return new AddEventCommand(input);
            } else {
                System.out.println("Input not recognised. Please input a valid action:");
                System.out.println("todo, event, deadline, mark, unmark, list, bye");
                return null;
            }
        }
    }
}
