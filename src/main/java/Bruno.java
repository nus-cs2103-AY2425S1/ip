import java.util.ArrayList;
import java.util.Scanner;

public class Bruno {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        String userResponse;

        printGreetingMessage();

        boolean running = true;
        while (running) {
            userResponse = input.nextLine();
            String firstWord;

            if (userResponse.contains(" ")) {
                firstWord = userResponse.substring(0, userResponse.indexOf(" "));
            } else {
                firstWord = userResponse;
            }

            if (userResponse.equals("bye")) {
                running = false;
                printByeMessage();
            } else if (userResponse.equals("list")) {
                printList();
            } else if (firstWord.equals("mark")) {
                markTask(userResponse.split(" ")[1]);
            } else if (firstWord.equals("unmark")) {
                unmarkTask(userResponse.split(" ")[1]);
            } else {
                addToList(userResponse);
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

    public static void addToList(String str) {
        Task task = new Task(str);
        taskList.add(task);
        System.out.println("____________________________________________________________");
        System.out.println("added: " + str);
        System.out.println("____________________________________________________________");
    }

    public static void printList() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println((i + 1) + "." + task);
        }
        System.out.println("____________________________________________________________");
    }

    public static void markTask(String num) {
        Task task = taskList.get(Integer.parseInt(num) - 1);
        task.complete();
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:\n" + task);
        System.out.println("____________________________________________________________");
    }

    public static void unmarkTask(String num) {
        Task task = taskList.get(Integer.parseInt(num) - 1);
        task.uncomplete();
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:\n" + task);
        System.out.println("____________________________________________________________");
    }
}
