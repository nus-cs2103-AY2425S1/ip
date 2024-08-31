import java.util.ArrayList;

/**
 * Text UI of the application.
 */
public class Ui {
    private static String line = "____________________________________________________________";
    private static String welcomeMessage = "Hi there! Did you miss me?\n" +
            "Wherever you are and whenever you need,\n" +
            "Elysia will always meet your expectations.";
    private static String exitMessage = "Alright, this time we really have to say goodbye.\n" +
            "Goodbye, Mei!";


    public void showWelcomeMessage() {
        System.out.println(line);
        System.out.println(welcomeMessage);
        System.out.println(line);
    }

    /**
     * Displays added message
     * @param task
     */
    public void handleAddedMessage(ArrayList<Task> arrayLists, Task task) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + arrayLists.size() + " tasks in the list.");
        System.out.println(line);
    }

    public void showExitMessage() {
        System.out.println(line);
        System.out.println(exitMessage);
        System.out.println(line);
    }

    public void showFailMessage() {
        System.out.println("Oh my! I'm so sorry,\n" +
                "but it seems I'm not sure what that means.\n" +
                "Let's figure it out together, shall we?");
    }

    public void printList(ArrayList<Task> arrayLists) {
        int n = arrayLists.size();
        System.out.println("Here are the tasks in your list: ");
        for (int i = 1; i <= n; i++) {
            Task curr = arrayLists.get(i - 1);
            System.out.println(i + "." + curr.toString());
        }
    }

    public void showMarkAsDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showUnmarkAsDoneMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void showEmptyDescriptionMessage(String taskType) {
        System.out.println(line);
        System.out.println("Oopsie! It looks like the description for this " +
                taskType +
                " is missing.\n" +
                "How about we add a little something to it?");
        System.out.println(line);
    }

    public void showDeleteTaskMessage(ArrayList<Task> arrayLists, Task task) {
        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + arrayLists.size() + " tasks in the list.");
        System.out.println(line);
    }

}
