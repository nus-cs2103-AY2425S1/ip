package pochat.bot;

import pochat.exceptions.TaskDescriptionEmptyException;

public class PoChat {
    private TaskList taskList;

    public PoChat() {
        this.taskList = new TaskList();
        sayHello();
    }

    /**
     * Loads the chat data stored in the history
     * @param chatData: the ChatData data handler to access the history
     */
    public void load(ChatData chatData) {
        this.taskList = chatData.toTaskList();
    }

    /**
     * Saves the current data in the TaskList to the history
     * @param chatData: the ChatData data handler to access the history
     */
    public void save(ChatData chatData) {
        chatData.save(this.taskList.toList());
    }

    private void sayHello() {
        System.out.println("Hello! I'm PoChat, the chatbot in your pocket.\n" +
                "What can I do for you?");
    }

    public String getResponse(String textInput) {
        try {
            return Parser.of(this.taskList).parse(textInput);
        } catch (TaskDescriptionEmptyException e) {
            return "Task description cannot be empty!! Please try again";
        }
    }

}