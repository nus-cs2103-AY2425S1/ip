import java.util.ArrayList;
import java.util.Scanner;

public class Blitz{
    public static void main(String[] args) {
        System.out.println("---------------\n" +
                "Hello! I'm BLITZ \n" +
                "What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> inputHistory = new ArrayList<>();

        while (true) {
            String userInput = scanner.nextLine();
            String strippedInput = userInput.trim().toLowerCase();
            if (strippedInput.equals("bye")) {
                System.out.println("----------------\n" +
                        "Till we meet again, GOODBYE!");
                break;
            } else {
                actionBasedOnInput(inputHistory, strippedInput);
            }
        }
    }

    public static void actionBasedOnInput(ArrayList<Task> inputHistory, String strippedInput) {

        if (strippedInput.equals("list")) {
            displayList(inputHistory);
            }
        }else {
            inputHistory.add(new Task(strippedInput));
            System.out.println("----------------\n" +
                    "added: " + strippedInput + "\n" +
                    "----------------\n");
        }
    }

    public static void changeTaskStatus(String action, Task task) {
        System.out.println("---------------");
        if (action.equals("mark")) {
            task.changeStatus(true);
            System.out.println("GOOD RIDDANCE! Finally, this task is done:\n" +
                    task);
        } else {
            task.changeStatus(false);
            System.out.println("Alright, this task is not done yet faster finish leh:\n" +
                    task);
        }
        System.out.println("---------------");
    }

    public static void displayList(ArrayList<Task> inputHistory) {
        System.out.println("---------------");
        inputHistory.forEach(task -> System.out.println((inputHistory.indexOf(task) + 1) +
                ". " +
                task));
        System.out.println("---------------\n");
    }
}
