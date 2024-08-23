import exceptions.*;

public class InputManager {
    private TaskManager taskManager;
    private MessageBuilder messageBuilder;

    public InputManager(TaskManager taskManager, MessageBuilder messageBuilder) {
        this.taskManager = taskManager;
        this.messageBuilder = messageBuilder;
    }

    public void handleInput(String input) {
        input = input.trim();
        if (input.isEmpty()) {
            messageBuilder.sendMessage(new EmptyInputException().getMessage());
            return;
        }

        String command = input.split("\\s+")[0];
        String args = input.substring(command.length()).trim();

        try {
            switch (command.toLowerCase()) {
                case "todo":
                    handleTodo(args);
                    break;
                case "deadline":
                    handleDeadline(args);
                    break;
                case "event":
                    handleEvent(args);
                    break;
                case "delete":
                    handleDelete(args);
                    break;
                case "mark":
                    handleMarking(args, true);
                    break;
                case "unmark":
                    handleMarking(args, false);
                    break;
                case "list":
                    messageBuilder.sendMessage(taskManager.listTasks());
                    break;
                default:
                    throw new UnknownCommandException();
            }
        } catch (PukeException e) {
            messageBuilder.sendMessage(e.getMessage());
        }
    }

    private void handleTodo(String input) throws EmptyDescriptionException {
        if (input.isEmpty()) {
            throw new EmptyDescriptionException("todo");
        }
        messageBuilder.sendMessage(taskManager.addTask("todo", input));
    }

    private void handleDeadline(String input) throws EmptyDescriptionException, MissingTimeException {
        if (input.isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }
        String[] parts = input.split(" /by ");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingTimeException();
        }
        messageBuilder.sendMessage(taskManager.addTask("deadline", parts[0].trim(), parts[1].trim()));
    }

    private void handleEvent(String input) throws EmptyDescriptionException, MissingEventTimeException {
        if (input.isEmpty()) {
            throw new EmptyDescriptionException("event");
        }
        String[] parts = input.split(" /from | /to ");
        if (parts.length < 3 || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            throw new MissingEventTimeException();
        }
        messageBuilder.sendMessage(taskManager.addTask("event", parts[0].trim(), parts[1].trim(), parts[2].trim()));
    }

    private void handleDelete(String numberString) throws PukeException {
        if (numberString.isEmpty()) {
            throw new EmptyInputException("OOPS!!! You must specify a task number to delete!!");
        }
        try {
            int taskNumber = Integer.parseInt(numberString);
            if (taskNumber < 1 || taskNumber > taskManager.getTaskCount()) {
                throw new TaskNumberOutOfBoundsException(taskNumber);
            }
            messageBuilder.sendMessage(taskManager.deleteTask(taskNumber));
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberFormatException();
        }
    }

    private void handleMarking(String numberString, boolean isDone) throws PukeException {
        if (numberString.isEmpty()) {
            throw new MissingTaskNumberException(isDone);
        }
        try {
            int taskNumber = Integer.parseInt(numberString);
            if (taskNumber < 1 || taskNumber > taskManager.getTaskCount()) {
                throw new TaskNumberOutOfBoundsException(taskNumber);
            }
            messageBuilder.sendMessage(taskManager.markTask(taskNumber, isDone));
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberFormatException();
        }
    }
}