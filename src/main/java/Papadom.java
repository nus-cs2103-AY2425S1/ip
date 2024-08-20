import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class Papadom {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    public static void printList() {
        String finalList = "____________________________________________________________\n" + " Here are the tasks in your list:\n";
        for (int i = 0; i < Papadom.tasks.size(); i++) {
            Task task = Papadom.tasks.get(i);
            if (task == null) break;
            finalList += " " + (i + 1) + "." + task.toString() + "\n";
        }
        finalList += "____________________________________________________________";
        System.out.println(finalList);
    }
    public static void addToList(Task task) throws NoTaskException {
        if (task.description == "") {
            throw new NoTaskException();
        }
        tasks.add(task);
        String response = "____________________________________________________________\n"
                + " Got it. I've added this task:\n  " + task.toString() + "\n"
                + " Now you have " + (Papadom.tasks.size()) + " tasks in the list.\n"
                + "____________________________________________________________";
        System.out.println(response);
    }
    public static void markTask(String text) {
        int taskIndex = Integer.parseInt(text.split(" ")[1]) - 1;
        Task task = Papadom.tasks.get(taskIndex);
        task.markAsDone();
        System.out.println("____________________________________________________________");
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("  " + task);
        System.out.println("____________________________________________________________");
    }
    private static void unmarkTask(String text) {
        int taskIndex = Integer.parseInt(text.split(" ")[1]) - 1;
        Task task = Papadom.tasks.get(taskIndex);
        task.unmark();
        System.out.println("____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        System.out.println("____________________________________________________________");
    }
    private static void addDeadline(String details) throws NoTaskException, NoDateException {
        String[] parts = details.split(" /by ");
        if (parts[0] == "") throw new NoTaskException();
        else if (parts.length == 1) throw new NoDateException();
        addToList(new Deadline(parts[0], parts[1]));
    }
    private static void addEvent(String details) throws NoTaskException, NoDateException {
        String[] parts = details.split(" /from | /to ");
        if (parts[0] == "") throw new NoTaskException();
        else if (parts.length <= 2) throw new NoDateException();
        addToList(new Event(parts[0], parts[1], parts[2]));
    }
    public static void main(String[] args) {

        String logo = "____________________________________________________________\n"
                + " Hello! I'm Papadom\n"
                + " What can I do for you?\n"
                + "____________________________________________________________";

        String exitMessage = "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";

        System.out.println(logo);

        while (true) {
            try {
                String text = scanner.nextLine();
                if (Objects.equals(text, "list")) {
                    printList();
                } else if (Objects.equals(text, "bye")) {
                    break;
                } else if (text.startsWith("mark ")) {
                    markTask(text);
                } else if (text.startsWith("unmark ")) {
                    unmarkTask(text);
                } else if (text.startsWith("todo ")) {
                    addToList(new Todo(text.substring(5)));
                } else if (text.startsWith("deadline ")) {
                    addDeadline(text.substring(9));
                } else if (text.startsWith("event ")) {
                    addEvent(text.substring(6));
                } else {
                    throw new UnknownCommandException();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(exitMessage);
    }
}
