import java.util.Scanner;

public class Him {

    private static final HimList list = new HimList();

    private static void greet() {
        System.out.println("Him: Hello! I'm Him\n     What can I do for you?\n");
    }

    private static void complete(int index) {
        try {
            list.complete(index);
            System.out.println("\nHim: LET'S GOOOOO! " + list.taskAt(index) + " has been completed!\n");
        } catch (Task.AlreadyCompletedException | HimList.TaskDoesNotExistException e) {
            System.out.println("\nHim: " + e.getMessage() + "\n");
        }
    }

    private static void exit() {
        System.out.println("\nHim: Bye. Hope to see you again soon!\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();
        System.out.print("User: ");
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("\nHim: Sure! Here's your list!\n\n" + list);
            } else if (input.startsWith("mark")) {
                complete(Integer.parseInt(input.substring(5)) - 1);
            } else {
                list.add(input);
                System.out.println("\nHim: added \"" + input + "\" to list\n");
            }
            System.out.print("User: ");
            input = scanner.nextLine();
        }
        exit();
    }
}
