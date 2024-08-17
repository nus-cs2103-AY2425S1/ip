import java.util.Scanner;
import java.util.ArrayList;

public class PX {
    private static String name = "PX";

    private static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    private static void PXSays(String arg) {
        System.out.println("    " + arg);
    }

    private static void printList(ArrayList<Task> list) {
        printLine();
        PXSays("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            PXSays(index + ". " + list.get(i).getStatusIcon() + " " + list.get(i));
        }
        printLine();
    }

    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();

        printLine();
        PXSays("Hello! I'm " + name);
        PXSays("What can I do for you?");
        printLine();
        Scanner sc = new Scanner(System.in);

        while (true) {
            PXSays("");
            String input = sc.nextLine();
            if (input.equals("bye")) {
                printLine();
                PXSays("Bye. Hope to see you again soon!");
                printLine();
                break;
            } else if (input.equals("list")) {
                printList(list);
            } else if (input.startsWith("mark")) {
                printLine();
                String[] arr = input.split(" ");
                Task t = list.get(Integer.parseInt(arr[1]) - 1);
                t.toggleIsDone();
                PXSays("Nice! I've marked this task as done:");
                PXSays(t.getStatusIcon() + " " + t);
                printLine();
            } else if (input.startsWith("unmark")) {
                printLine();
                String[] arr = input.split(" ");
                Task t = list.get(Integer.parseInt(arr[1]) - 1);
                list.get(Integer.parseInt(arr[1]) - 1).toggleIsDone();
                PXSays("OK, I've marked this task as not done yet:");
                PXSays(t.getStatusIcon() + " " + t);
                printLine();
            }

            else {
                Task t = new Task(input);
                printLine();
                list.add(t);
                PXSays("added: " + input);
                printLine();
            }
        }

        sc.close();
    }
}
