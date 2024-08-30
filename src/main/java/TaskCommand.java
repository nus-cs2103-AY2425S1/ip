import java.time.LocalDate;

public class TaskCommand implements Command {
    private final String fullCommand;
    private final String commandType;

    public TaskCommand(String fullCommand, String commandType) {
        this.fullCommand = fullCommand;
        this.commandType = commandType;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        Task task = switch (commandType) {
            case "todo" -> parseToDo(fullCommand);
            case "deadline" -> parseDeadline(fullCommand);
            case "event" -> parseEvent(fullCommand);
            default -> throw new IllegalArgumentException("Invalid command type for tasks: " + commandType);
        };
        list.add(task);
        ui.showAddTaskMessage(task, list);
        storage.saveTasks(list);
    }

    public static Task parseToDo(String fullCommand) {
        try {
            if (fullCommand.equals("todo")) {
                throw new EmptyToDoException("Cannot have empty todo");
            }
            return new ToDo(fullCommand.split(" ", 2)[1]);
        } catch (EmptyToDoException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Task parseDeadline(String fullCommand) {
        String[] commandParts = fullCommand.split("/by");
        LocalDate date = LocalDate.parse(commandParts[1]);
        return new Deadline(commandParts[0].split(" ", 2)[1], date);
    }

    public static Task parseEvent(String fullCommand) {
        String[] commandParts = fullCommand.split("/from|/to");
        return new Event(commandParts[0].split(" ", 2)[1], commandParts[1], commandParts[1]);
    }
}