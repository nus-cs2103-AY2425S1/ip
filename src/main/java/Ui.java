public class Ui {

    public Ui() {
    }

    public void printLongLine() {
        System.out.println("____________________________________________________________");
    }

    public void printWelcome() {
        System.out.println("Hello, I am Barney <RAWR>, what can I do for you?");
        printLongLine();
    }

    public void printLoadData(TaskList tasks) {
        System.out.println("Data loaded successfully!");
        System.out.println("You have " + tasks.size() + " tasks in the list.");
        printLongLine();
    }

    public void printInput() {
        System.out.print(">>> ");
    }

    public void printBye() {
        System.out.println("Goodbye, I am Barney <RAWR>, see you next time!");
        printLongLine();
    }

    // Errors
    public void printLoadingError(String errorMessage) {
        System.out.println("Error when loading data from file: " + errorMessage);
        System.out.println("Loading with empty task list");
        printLongLine();
    }

    public void printChatError(String errorMessage) {
        System.out.println("Error when running: " + errorMessage);
        printLongLine();
    }

    public void printSaveError(String errorMessage) {
        System.out.println("Saving data to file: " + errorMessage);
        printLongLine();
    }

    // Commands
    public void printList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(tasks.toStringList());
        printLongLine();
    }

    public void printMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        printLongLine();
    }

    public void printUnmarked(Task task) {
        System.out.println("OK, I've unmarked this task as not done yet:");
        System.out.println(task.toString());
        printLongLine();
    }

    public void printAddedTask(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", size));
        printLongLine();
    }

    public void printDeleteTask(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", size));
        printLongLine();
    }

    public void printInvalidCommand() {
        System.out.println("Invalid command, please try again!");
        printLongLine();
    }
}
