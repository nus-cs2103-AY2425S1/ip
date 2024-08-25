import java.util.ArrayList;
import java.util.Scanner;

class Ui {
    private static final String separator =
            "_______________________________________________________\n";
    void showGreeting() {
        System.out.println(separator
                + "Hello! I'm the Rizzler.\n"
                + "What can I do for you today?\n"
                + separator);
    }

    void showGoodbye() {
        System.out.println(separator
                + "Bye! Rizz you later!\n"
                + separator);
    }

    String readCommand() {
        Scanner sc = new Scanner(System.in);
        String fullCommand = sc.nextLine();
        return fullCommand;
    }

    void showAdditionToList(Task task, int size) {
        System.out.println(separator
                + "Gotcha! I've added the new task for you:\n"
                + task + "\n"
                + "Now you have " + size + " tasks in the list.\n"
                + separator);
    }
    void showRemovalFromList(Task task, int size) {
        System.out.println(separator
                + "I have removed this task for you:\n"
                + task + "\n"
                + "Now you have " + size + " tasks in the list.\n"
                + separator);
    }

    void showList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(separator
                    + "No tasks here yet\n"
                    + separator);
        } else {
            System.out.println(separator
                    + "Here are the tasks in your list");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(String.valueOf(i + 1) + ". " + tasks.get(i));
            }
            System.out.println(separator);
        }
    }

    void showMarking(Task task) {
        System.out.println(separator
                + "Hell yeah! You finished your task:\n"
                + task + "\n"
                + separator);
    }

    void showUnmarking(Task task) {
        System.out.println(separator
                + "Womp womp. Better do it later:\n"
                + task + "\n"
                + separator);
    }

    void showLoadingError() {
        System.out.println(separator
                + "Oops, something went wrong while loading."
                + separator);
    }

    void showError(String message) {
        System.out.println(separator
                + message + "\n"
                + separator);
    }
}
