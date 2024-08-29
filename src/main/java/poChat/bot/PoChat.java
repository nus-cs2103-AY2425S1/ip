package poChat.bot;

import poChat.exceptions.TaskDescriptionEmptyException;

class PoChat {
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

    /**
     * This method takes in the input from the user as a <code>String</code>
     * and passes it to the Parser for processing.
     *
     * @param textInput the String input from the user
     * @return true if the input is bye and the chat is over, otherwise return false
     * which means the conversation continues
     */

    public boolean parse(String textInput) {
        try {
            return Parser.of(this.taskList).parse(textInput);
        } catch (TaskDescriptionEmptyException e) {
            System.out.println("Task description cannot be empty!! Please try again");
            return false;
        }
    }

    private void sayHello() {
        System.out.println("Hello! I'm PoChat, the chatbot in your pocket.\n" +
                "What can I do for you?");
    }

}