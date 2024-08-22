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
            String response = taskManager.listTasks();
            messageBuilder.sendMessage(response);
        } else if (!input.equalsIgnoreCase("bye")) {
            String response = taskManager.addTask(input);
            messageBuilder.sendMessage(response);
        }
    }
}
