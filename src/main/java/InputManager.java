public class InputManager {
    private TaskManager taskManager;
    private MessageBuilder messageBuilder;

    public InputManager(TaskManager taskManager, MessageBuilder messageBuilder) {
        this.taskManager = taskManager;
        this.messageBuilder = messageBuilder;
    }

    public void handleInput(String input) {
        input = input.trim();
        try {
            if (input.isEmpty()) {
                throw new PukeException("OOPS!!! You need to enter a command.");
            } else if (input.matches("todo.*")) {
                handleTodo(input);
            } else if (input.matches("deadline.*")) {
                handleDeadline(input);
            } else if (input.matches("event.*")) {
                handleEvent(input);
            } else if (input.matches("mark.*")) {
                handleMarking(input.substring(4).trim(), true);
            } else if (input.matches("unmark.*")) {
                handleMarking(input.substring(6).trim(), false);
            } else if (input.equalsIgnoreCase("list")) {
                messageBuilder.sendMessage(taskManager.listTasks());
            } else {
                throw new PukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (PukeException e) {
            messageBuilder.sendMessage(e.getMessage());
        }
    }
    private void handleTodo(String input) throws PukeException {
        String trimmedInput = input.substring(4).trim();
        if (trimmedInput.isEmpty()) {
            throw new PukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        messageBuilder.sendMessage(taskManager.addTask("todo", trimmedInput));
    }

    private void handleDeadline(String input) throws PukeException {
        String trimmedInput = input.substring(8).trim();
        if (trimmedInput.isEmpty()) {
            throw new PukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] parts = trimmedInput.split(" /by ");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new PukeException("OOPS!!! The deadline must have a specified time.");
        }
        messageBuilder.sendMessage(taskManager.addTask("deadline", parts[0].trim(), parts[1].trim()));
    }

    private void handleEvent(String input) throws PukeException {
        String trimmedInput = input.substring(5).trim();
        if (trimmedInput.isEmpty()) {
            throw new PukeException("OOPS!!! The description of a event cannot be empty.");
        }
        String[] parts = trimmedInput.split(" /from | /to ");
        if (parts.length < 3 || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            throw new PukeException("OOPS!!! An event must have both start and end times specified.");
        }
        messageBuilder.sendMessage(taskManager.addTask("event", parts[0].trim(), parts[1].trim(), parts[2].trim()));
    }

    private void handleMarking(String numberString, boolean isDone) throws PukeException {
        if (numberString.isEmpty()) {
            throw new PukeException("OOPS!!! You must specify a task number to " + (isDone ? "mark" : "unmark") + ".");
        }
        try {
            int taskNumber = Integer.parseInt(numberString);
            if (taskNumber < 1 || taskNumber > taskManager.getTaskCount()) {
                throw new PukeException("OOPS!!! The task number " + taskNumber + " is out of bounds.");
            }
            messageBuilder.sendMessage(taskManager.markTask(taskNumber, isDone));
        } catch (NumberFormatException e) {
            throw new PukeException("Invalid task number format!!!!");
        }
    }
}
