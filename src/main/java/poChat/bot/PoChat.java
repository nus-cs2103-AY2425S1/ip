package poChat;

import poChat.exceptions.TaskDescriptionEmptyException;

class PoChat {
    private TaskList taskList;

    public PoChat() {
        this.taskList = new TaskList();
        sayHello();
    }

    public void load(ChatData chatData) {
        this.taskList = chatData.toTaskList();
    }

    public void save(ChatData chatData) {
        chatData.save(this.taskList.toList());
    }

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