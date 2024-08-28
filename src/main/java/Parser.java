import java.time.LocalDate;

enum Commands
{
    bye, list, mark, unmark, todo, deadline, event, delete;
}

public class Parser {

    public static Command parse(String cmd) {
        String[] args = cmd.split(" ");

        switch (Commands.valueOf(args[0])) {
            case bye:
                return new ExitCommand();
            case list:
                return new ListCommand();
            case mark:
                return new MarkCommand(Integer.parseInt(args[1]));
            case unmark:
                return new UnmarkCommand(Integer.parseInt(args[1]));
            case todo:
                try {
                    Task todo = new ToDo(cmd.substring(5));
                    return new AddCommand(todo);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new StrugglingException("OOPS!!! The description of a todo cannot be empty.");
                }
            case deadline:
                int byIndex = cmd.indexOf("/by ");
                String dDescription = cmd.substring(9, byIndex).trim();
                LocalDate dBy = LocalDate.parse(cmd.substring(byIndex + 4));
                Task deadline = new Deadline(dDescription, dBy);
                return new AddCommand(deadline);
            case event:
                int fromIndex = cmd.indexOf("/from ");
                int toIndex = cmd.indexOf("/to ");
                String eDescription = cmd.substring(6, fromIndex).trim();
                String eFrom = cmd.substring(fromIndex + 6, toIndex).trim();
                String eTo = cmd.substring(toIndex + 4);
                Task event = new Event(eDescription, eFrom, eTo);
                return new AddCommand(event);
            case delete:
                return new DeleteCommand(Integer.parseInt(args[1]));
        }

        return new InvalidCommand();
    }


}
