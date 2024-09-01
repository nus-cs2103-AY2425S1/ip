import java.io.IOException;
import java.util.Scanner;

public class Ui {

    private Scanner userInput;
    private static final String DIVIDER = "____________________________________________________________";

    public Ui() {
        this.userInput = new Scanner(System.in);
        System.out.println("Hello! I am Gallium. What can I do for you?");
    }

    public String readNextLine() {
        return userInput.nextLine();
    }

    public void closeScanner() {
        userInput.close();
    }

    public void printAnyMessage(String message) {
        System.out.println("    " + DIVIDER + "\n    " + message + "\n    " + DIVIDER);
    }

    public void printList(TaskList taskList) {
        String message = "Here are the tasks in your list:";
        System.out.println("    " + DIVIDER + "\n    " + message);
        System.out.println(taskList.toStringIndent());
        System.out.println("\n    " + DIVIDER);
    }

    public void printMarkMessage(boolean isMark, Task task) {
        System.out.println("    " + DIVIDER);
        System.out.println("    " + (isMark ? "Nice! I've marked this task as done: "
                : "OK, I've marked this task as not done yet: ") + "\n" + "    "
                + task.toString());
        System.out.println("    " + DIVIDER + "\n    ");
    }

    public void printAddTodo(Todo todo) {
        System.out.println("    " + DIVIDER + "\n    " + "Got it. I've added this task: \n" + "    "
                + todo.toString()
                + "\n    Now you have " + Task.count + " " + Task.taskCount() + " in the list.\n" + "    "
                + DIVIDER
                + "\n");
    }

    public void printAddDeadline(Deadline deadline) {
        System.out
                .println("    " + DIVIDER + "\n    " + "Got it. I've added this task: \n"
                        + "    " + deadline.toString()
                        + "\n    Now you have " + Task.count + " " + Task.taskCount() + " in the list.\n"
                        + "    "
                        + DIVIDER
                        + "\n");
    }

    public void printAddEvent(Event event) {
        System.out.println("    " + DIVIDER + "\n    " + "Got it. I've added this task: \n" + "    "
                + event.toString()
                + "\n    Now you have " + Task.count + " " + Task.taskCount() + " in the list.\n" + "    "
                + DIVIDER
                + "\n");
    }

    public void printDelete(Task task) {
        System.out.println("    " + DIVIDER);
        System.out.println("    " + "Noted. I've removed this task:" + "\n" + "    "
                + task.toString());
        System.out.println(
                "\n    Now you have " + (Task.count - 1) + " " + Task.taskCountDelete()
                        + " in the list.\n");
        System.out.println("    " + DIVIDER + "\n    ");
    }

    public void printMatchingDate(String tasks) {
        System.out.println("    " + DIVIDER + "\n    Deadlines/Events that match the date: ");
        System.out.println(tasks);
        System.out.println("\n    " + DIVIDER);
    }

    public void showCreateFileError(IOException e) {
        System.out.println("Error creating file:" + e.getMessage());
    }

    public void showLoadingError() {
        String message = "Oh no! Error loading task list :(";
        System.out.println("    " + DIVIDER + "\n    " + message + "\n    " + DIVIDER);
    }

    public void showGalliumException(GalliumException e) {
        System.out.println("    " + DIVIDER + "\n    " + e.getMessage() + "\n    " + DIVIDER + "\n");
    }

    public void showIncompleteDeadline() {
        printAnyMessage("Please put the date of the deadline!!");
    }

    public void showIncompleteEvent() {
        printAnyMessage("Please put the from and to of the event!!");
    }

    public void showWrongIndex() {
        printAnyMessage("Please put a number between 1 and " + (Task.count - 1)
                + "!" + "\n    Now you have " + (Task.count - 1) + " " + Task.taskCountDelete()
                + " in the list.");
    }

    public void showIOException(IOException e) {
        System.out.println("Something went wrong: " + e.getMessage());
    }

    public void printByeMessage() {
        String message = "Bye. Hope to see you again soon!";
        System.out.println("    " + DIVIDER + "\n    " + message + "\n    " + DIVIDER);
    }
}
