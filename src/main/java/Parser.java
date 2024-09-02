import java.io.IOException;


public class Parser {
    public enum TaskType {
        bye, list, mark, unmark, todo, deadline, event, delete
    }

    public static Command parse(String fullCommand) throws InvalidCommandException {
        String[] commandParts = fullCommand.split(" ", 2);
        String commandWord = commandParts[0];
        String commandArgs = commandParts.length > 1 ? commandParts[1] : "";

        TaskType taskType;
        try {
            taskType = TaskType.valueOf(commandWord);
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("I'm not sure what you mean. Here are the commands I understand: todo, deadline, event, list, mark, unmark, bye");
        }

        try {
            switch (taskType) {
                case todo:
                    return new AddCommand(new Todo(commandArgs));
                case deadline:
                    try {
                        return new AddCommand(new Deadline(commandArgs));
                    } catch (InvalidDateTimeFormatException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                case event:
                    try {
                        return new AddCommand(new Event(commandArgs));
                    } catch (InvalidDateTimeFormatException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                case delete:
                    return new DeleteCommand(Integer.parseInt(commandArgs));
                case mark:
                    return new MarkCommand(Integer.parseInt(commandArgs));
                case unmark:
                    return new UnmarkCommand(Integer.parseInt(commandArgs));
                case list:
                    return new ListCommand();
                case bye:
                    return new ExitCommand();
                default:
                    return null;
            }
        } catch (InvalidDescriptionException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("An unexpected error occurred");
            return null;
        }
    }
}


