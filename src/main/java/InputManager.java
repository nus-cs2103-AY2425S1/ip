public class InputManager {
    private TaskManager taskManager;
    private MessageBuilder messageBuilder;

    public InputManager(TaskManager taskManager, MessageBuilder messageBuilder) {
        this.taskManager = taskManager;
        this.messageBuilder = messageBuilder;
    }

    public void handleInput(String input) {
        input = input.trim();
        if (input.startsWith("todo ")) {
            messageBuilder.sendMessage(taskManager.addTask("todo", input.substring(5)));
        } else if (input.startsWith("deadline ")) {
            String[] parts = input.substring(9).split(" /by ");
            messageBuilder.sendMessage(taskManager.addTask("deadline", parts[0], parts[1]));
        } else if (input.startsWith("event ")) {
            String[] parts = input.substring(6).split(" /from | /to ");
            messageBuilder.sendMessage(taskManager.addTask("event", parts[0], parts[1], parts[2]));
        } else if (input.startsWith("mark ")) {
            handleMarking(input.substring(5), true);
        } else if (input.startsWith("unmark ")) {
            handleMarking(input.substring(7), false);
        } else if (input.equalsIgnoreCase("list")) {
            messageBuilder.sendMessage(taskManager.listTasks());
        } else {
            messageBuilder.sendMessage("Unknown command or task type.");
        }
    }

    private void handleMarking(String numberString, boolean isDone) {
        try {
            int taskNumber = Integer.parseInt(numberString);
            messageBuilder.sendMessage(taskManager.markTask(taskNumber, isDone));
        } catch (NumberFormatException e) {
            messageBuilder.sendMessage("Invalid task number format!!!!");
        }
    }
}
