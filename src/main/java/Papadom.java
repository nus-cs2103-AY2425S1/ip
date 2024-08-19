import java.util.Objects;
import java.util.Scanner;
public class Papadom {
    public static String printList(Task[] list) {
        String finalList = "Here are the tasks in your list:\n";
        int current = 1;
        for (Task task : list) {
            if (task == null) break;
            finalList += current++ + ". " + task.getDescription() + "\n";
        }
        return finalList;
    }
    public static void main(String[] args) {

        String logo = "____________________________________________________________\n"
                + "Hello! I'm Papadom\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";

        Task[] list = new Task[100];
        int current = 0;

        String exitMessage = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";

        System.out.println(logo);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine();
            if (Objects.equals(text, "list")) {
                String currentList = printList(list);
                System.out.println(currentList);
                continue;
            } else if (Objects.equals(text, "bye")) {
                break;
            } else if (text.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(text.split(" ")[1]) - 1;
                Task currentTask = list[taskIndex];
                currentTask.markAsDone();
                System.out.println("Nice! I've Marked this task as done:\n " + currentTask.getDescription());
            } else if (text.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(text.split(" ")[1]) - 1;
                Task currentTask = list[taskIndex];
                currentTask.unmark();
                System.out.println("OK, I've marked this task as not done yet:\n " + currentTask.getDescription());
            } else {
                list[current++] = new Task(text);
                String response = "____________________________________________________________\n"
                        + " added: " + text + "\n"
                        + "____________________________________________________________";
                System.out.println(response);
            }
        }
        System.out.println(exitMessage);
    }
}
