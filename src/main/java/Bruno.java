import java.util.ArrayList;
import java.util.Scanner;

public class Bruno {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> taskList = new ArrayList<>();
        String userResponse;

        printGreetingMessage();

        boolean running = true;
        while (running) {
            userResponse = input.nextLine();
            if (userResponse.equals("bye")) {
                running = false;
                printByeMessage();
            } else if (userResponse.equals("list")) {
                printList(taskList);
            } else {
                addToList(userResponse, taskList);
            }
        }
    }

    public static void printGreetingMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Bruno\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void printByeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void addToList(String task, ArrayList<String> list) {
        list.add(task);
        System.out.println("____________________________________________________________");
        System.out.println("added: " + task);
        System.out.println("____________________________________________________________");
    }

    public static void printList(ArrayList<String> list) {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
        System.out.println("____________________________________________________________");
    }
}
