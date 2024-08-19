import java.util.ArrayList;

class PoChat {
    private final ArrayList<Task> listTextsEntered;

    public PoChat() {
        this.listTextsEntered = new ArrayList<>();
    }

    public void sayHello() {
        String introMessage = "Hello! I'm PoChat, the chatbot in your pocket.\nWhat can I do for you?";
        System.out.println(introMessage);
    }

    public void sayGoodbye() {
        String introMessage = "Bye. Hope to see you again soon!";
        System.out.println(introMessage);
    }

    public void addToListAndReply(String textInput) {
        this.listTextsEntered.add(new Task(textInput));
        System.out.println("added: " + textInput);
    }

    public void replyWithListOfTextsEntered() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.listTextsEntered.size(); i++) {
            System.out.println((i + 1) + ". " + this.listTextsEntered.get(i));
        }
    }

    public void markTaskDone(int index) {
        Task task = this.listTextsEntered.get(index);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void unmarkTaskDone(int index) {
        Task task = this.listTextsEntered.get(index);
        task.unmarkAsDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }
}