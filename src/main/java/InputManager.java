public class InputManager {
    private TaskManager taskManager;
    private MessageBuilder messageBuilder;

    public InputManager(TaskManager taskManager, MessageBuilder messageBuilder) {
        this.taskManager = taskManager;
        this.messageBuilder = messageBuilder;
    }

    public void handleInput(String input) {
        input = input.trim();
        if (input.equalsIgnoreCase("list")) {
            messageBuilder.sendMessage(taskManager.listTasks());
        } else if (input.startsWith("mark ")) {
            handleMarking(input.substring(5), true);
        } else if (input.startsWith("unmark ")) {
            handleMarking(input.substring(7), false);
        } else if (!input.equalsIgnoreCase("bye")) {
            messageBuilder.sendMessage(taskManager.addTask(input));
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
