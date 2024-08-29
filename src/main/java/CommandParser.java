import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class CommandParser {
    private CancelGPT chatbot;

    public CommandParser(CancelGPT chatbot) {
        this.chatbot = chatbot;
    }

    public void parseAndHandle(String command) {
        try {
            if (command.equals(Command.LIST.toString())) {
                this.chatbot.displayTasksList();
            } else if (command.startsWith(Command.DELETE.toString())) {
                int taskNumber = parseDeleteTaskCommand(command);
                this.chatbot.deleteTask(taskNumber);
            } else if (command.startsWith(Command.MARK.toString())) {
                int taskNumber = parseMarkTaskCommand(command);
                this.chatbot.markTask(taskNumber);
            } else if (command.startsWith(Command.UNMARK.toString())) {
                int taskNumber = parseUnmarkTaskCommand(command);
                this.chatbot.unmarkTask(taskNumber);
            } else if (command.startsWith(Command.TODO.toString())) {
                Task toDoTask = parseToDoTaskCreationCommand(command);
                this.chatbot.handleAddingTask(toDoTask);
            } else if (command.startsWith(Command.DEADLINE.toString())) {
                Task deadlineTask = parseDeadlineTaskCreationCommand(command);
                this.chatbot.handleAddingTask(deadlineTask);
            } else if (command.startsWith(Command.EVENT.toString())) {
                Task eventTask = parseEventTaskCreationCommand(command);
                this.chatbot.handleAddingTask(eventTask);
            } else {
                throw new UnknownInput();
            }
        } catch (MarkTaskInputException | UnmarkTaskInputException | InvalidTask | TaskDoesNotExist | UnknownInput | DeleteTaskInputException e) {
            System.out.println(e.getMessage());
        }
    }

    public int parseDeleteTaskCommand(String command) throws DeleteTaskInputException {
        String[] commandArray = command.split(" ");
        if (commandArray.length != 2) {
            throw new DeleteTaskInputException();
        }

        try {
            return Integer.parseInt(commandArray[1]);
        } catch (NumberFormatException e) {
            throw new DeleteTaskInputException();
        }
    }
    public int parseMarkTaskCommand(String command) throws MarkTaskInputException {
        String[] commandArray = command.split(" ");
        if (commandArray.length != 2) {
            throw new MarkTaskInputException();
        }

        try {
            return Integer.parseInt(commandArray[1]);
        } catch (NumberFormatException e) {
            throw new MarkTaskInputException();
        }
    }

    public int parseUnmarkTaskCommand(String command) throws UnmarkTaskInputException {
        String[] commandArray = command.split(" ");
        if (commandArray.length != 2) {
            throw new UnmarkTaskInputException();
        }

        try {
            return Integer.parseInt(commandArray[1]);
        } catch (NumberFormatException e) {
            throw new UnmarkTaskInputException();
        }
    }

    public Task parseToDoTaskCreationCommand(String command) throws InvalidTask {
        String[] commandArray = command.split(" ");
        String[] taskDescriptionArr = Arrays.copyOfRange(commandArray, 1, commandArray.length);
        String taskDescription = String.join(" ", taskDescriptionArr);
        if (taskDescription.isEmpty()) {
            throw new InvalidTask("Missing description for ToDo task");
        }
        return new ToDo(taskDescription);
    }

    public Task parseDeadlineTaskCreationCommand(String command) throws InvalidTask {
        String[] commandArray = command.split(" ");

        int byIndex = Arrays.asList(commandArray).indexOf("/by");
        if (byIndex == -1) {
            throw new InvalidTask("Missing `by` for Deadline task");
        }

        String[] taskDescriptionArr = Arrays.copyOfRange(commandArray, 1, byIndex);
        String taskDescription = String.join(" ", taskDescriptionArr);
        if (taskDescription.isEmpty()) {
            throw new InvalidTask("Missing description for Deadline task");
        }

        String[] byDateArr = Arrays.copyOfRange(commandArray, byIndex + 1, commandArray.length);
        String byDate = String.join(" ", byDateArr);
        if (byDate.isEmpty()) {
            throw new InvalidTask("Missing by date for Deadline task");
        }

        Task deadlineTask;

        try {
            deadlineTask = new Deadline(taskDescription, LocalDateTimeHandler.parseLocalDateTime(byDate));
        } catch (DateTimeParseException e) {
            throw new InvalidTask("Invalid deadline date");
        }
        return deadlineTask;
    }

    public Task parseEventTaskCreationCommand(String command) throws InvalidTask {
        String[] commandArray = command.split(" ");

        int fromIndex = Arrays.asList(commandArray).indexOf("/from");
        if (fromIndex == -1) {
            throw new InvalidTask("Missing `from` for Event task");
        }

        String[] taskDescriptionArr = Arrays.copyOfRange(commandArray, 1, fromIndex);
        String taskDescription = String.join(" ", taskDescriptionArr);
        if (taskDescription.isEmpty()) {
            throw new InvalidTask("Missing description for Event task");
        }


        int toIndex = Arrays.asList(commandArray).indexOf("/to");
        if (toIndex == -1) {
            throw new InvalidTask("Missing `to` for Event task");
        }

        String[] fromDateArr = Arrays.copyOfRange(commandArray, fromIndex + 1, toIndex);
        String fromDate = String.join(" ", fromDateArr);
        if (fromDate.isEmpty()) {
            throw new InvalidTask("Missing from date for Event task");
        }

        String[] toDateArr = Arrays.copyOfRange(commandArray, toIndex + 1, commandArray.length);
        String toDate = String.join(" ", toDateArr);
        if (toDate.isEmpty()) {
            throw new InvalidTask("Missing to date for Event task");
        }

        Task eventTask;
        try {
            eventTask = new Event(taskDescription, LocalDateTimeHandler.parseLocalDateTime(fromDate),
                    LocalDateTimeHandler.parseLocalDateTime(toDate));
        } catch (DateTimeParseException e) {
            throw new InvalidTask("Invalid event date(s)");
        }
        return eventTask;
    }
}
