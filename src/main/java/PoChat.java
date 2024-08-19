import java.util.ArrayList;

class PoChat {
    private final ArrayList<Task> listTasks;

    public PoChat() {
        this.listTasks = new ArrayList<Task>();
    }

    public void sayHello() {
        String introMessage = "Hello! I'm PoChat, the chatbot in your pocket.\nWhat can I do for you?";
        System.out.println(introMessage);
    }

    public void sayGoodbye() {
        String introMessage = "Bye. Hope to see you again soon!";
        System.out.println(introMessage);
    }

    private void addTaskToList(Task task) {
        this.listTasks.add(task);
        System.out.println("Got it. I've added this task:\n" + task + "\nNow you have "
                + this.getNumTasks() + " tasks in the list.");
    }

    public void addToListAndReply(String textInput) {
        Task task = new ToDo(textInput);
        this.addTaskToList(task);
    }

    public void addToListAndReply(String textInput, String deadline) {
        Task task = new Deadline(textInput, deadline);
        this.addTaskToList(task);
    }

    public void addToListAndReply(String textInput, String startDate, String endDate) {
        Task task = new Event(textInput, startDate, endDate);
        this.addTaskToList(task);
    }

    public void replyWithListOfTextsEntered() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.listTasks.size(); i++) {
            System.out.println((i + 1) + ". " + this.listTasks.get(i));
        }
    }

    public void markTaskDone(int index) {
        Task task = this.listTasks.get(index);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void unmarkTaskDone(int index) {
        Task task = this.listTasks.get(index);
        task.unmarkAsDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void replyToInvalidInput() {
        String errorMessage = "Please enter a valid input and try again!";
        System.out.println(errorMessage);
    }

    private int getNumTasks() {
        return this.listTasks.size();
    }
}