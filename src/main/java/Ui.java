public class Ui {
    private String name;
    private TaskList taskList;

    public Ui(String name, TaskList taskList) {
        this.name = name;
        this.taskList = taskList;
    }
    
    public void printBanner() {
        System.out.println("Hello! I'm " + this.name + "!");
        System.out.println("What can I do for you?");
    }

    public void printLineBreak() {
        System.out.println("____________________________________________________________");
    }

    public void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printTask(int i) {
        System.out.println("  " + taskList.taskAtIndexToString(i));
    }

    public void printTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.noOfTasks(); i++) {
            System.out.println((i + 1) + ". " + taskList.taskAtIndexToString(i));
        }
    }

    public void printMarkMessage(int i) {
        System.out.println("Nice! I've marked this task as done:");
        this.printTask(i);
    }

    public void printUnmarkMessage(int i) {
        System.out.println("OK, I've marked this task as not done yet:");
        this.printTask(i);
    }
    
    public void printDeleteMessage(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskList.noOfTasks() + " tasks in the list.");
    }

    public void printAddMessage() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList.taskAtIndexToString(taskList.noOfTasks() - 1));
        System.out.println("Now you have " + taskList.noOfTasks() + " tasks in the list.");
    }
}
