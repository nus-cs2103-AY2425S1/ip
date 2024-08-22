import java.util.Objects;
import java.util.Scanner;

public class Mediell {
    public static void main(String[] args) {
        printLine();
        System.out.println("Hello! I'm Mediell!");
        System.out.println("What can I do for you? :)");
        printLine();
        Scanner scanner = new Scanner(System.in);
        String message = "";
        TaskList items = new TaskList();
        while (true) {
            try {
                System.out.println("");
                message = scanner.nextLine();
                if (Objects.equals(message, "bye")) {
                    break;
                } else if (Objects.equals(message, "list")) {
                    items.displayList();
                } else if (message.startsWith("mark")) {
                    int index = Integer.parseInt(message.split(" ", 2)[1]) - 1;
                    items.markItem(index);
                } else if (message.startsWith("unmark")) {
                    int index = Integer.parseInt(message.split(" ", 2)[1]) - 1;
                    items.unMarkItem(index);
                } else if (message.startsWith("delete")) {
                    int index = Integer.parseInt(message.split(" ", 2)[1]) - 1;
                    items.deleteTask(index);
                } else if (message.startsWith("todo")) {
                    String task = message.split(" ", 2)[1];
                    items.addToDo(task);
                } else if (message.startsWith("event")) {
                    String task = message.split(" ", 2)[1];
                    items.addEvent(task);
                } else if (message.startsWith("deadline")) {
                    String task = message.split(" ", 2)[1];
                    items.addDeadline(task);
                } else {
                    System.out.println("Sorry :( I'm confused at what I have to do");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                // if out of range likely because not enough inputs
                System.out.println("OOPS!! Not enough inputs were provided");
            }
            printLine();
        }

        System.out.println("Bye. Hope to see you again soon! :(");
        printLine();
    }

    private static void printLine() {
        System.out.println("-------------------------------------------");
    }
}
