import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class Papadom {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    public static void output(String message) {
        System.out.println("____________________________________________________________\n"
                + message
                + "\n____________________________________________________________");
    }
    public static String printList() {
        String finalList = " Here are the tasks in your list:";
        for (int i = 0; i < Papadom.tasks.size(); i++) {
            Task task = Papadom.tasks.get(i);
            if (task == null) break;
            finalList += "\n " + (i + 1) + "." + task.toString();
        }
        return finalList;
    }
    public static String addToList(Task task) throws NoTaskException {
        if (task.description == "") {
            throw new NoTaskException();
        }
        tasks.add(task);
        String response = " Got it. I've added this task:\n  " + task.toString() + "\n"
                + " Now you have " + (Papadom.tasks.size()) + " tasks in the list.";
        return response;
    }
    public static String markTask(String text) {
        int taskIndex = Integer.parseInt(text.split(" ")[1]) - 1;
        Task task = Papadom.tasks.get(taskIndex);
        task.markAsDone();
        return " Nice! I've marked this task as done:\n  " + task;
    }
    private static String unmarkTask(String text) {
        int taskIndex = Integer.parseInt(text.split(" ")[1]) - 1;
        Task task = Papadom.tasks.get(taskIndex);
        task.unmark();
        return " OK, I've marked this task as not done yet:\n  " + task;
    }
    private static String addDeadline(String details) throws NoTaskException, NoDateException {
        String[] parts = details.split(" /by ");
        if (parts[0] == "") throw new NoTaskException();
        else if (parts.length == 1) throw new NoDateException();
        return addToList(new Deadline(parts[0], parts[1]));
    }
    private static String addEvent(String details) throws NoTaskException, NoDateException {
        String[] parts = details.split(" /from | /to ");
        if (parts[0] == "") throw new NoTaskException();
        else if (parts.length <= 2) throw new NoDateException();
        return addToList(new Event(parts[0], parts[1], parts[2]));
    }
    public static void main(String[] args) {
        Papadom.output(" Hello! I'm Papadom\n"
                + " What can I do for you?");

        while (true) {
            try {
                String text = scanner.nextLine();
                if (Objects.equals(text, "list")) {
                    output(printList());
                } else if (Objects.equals(text, "bye")) {
                    break;
                } else if (text.startsWith("mark ")) {
                    output(markTask(text));
                } else if (text.startsWith("unmark ")) {
                    output(unmarkTask(text));
                } else if (text.startsWith("todo ")) {
                    output(addToList(new Todo(text.substring(5))));
                } else if (text.startsWith("deadline ")) {
                    output(addDeadline(text.substring(9)));
                } else if (text.startsWith("event ")) {
                    output(addEvent(text.substring(6)));
                } else {
                    throw new UnknownCommandException();
                }
            } catch (Exception e) {
                output(e.getMessage());
            }
        }
        output(" Bye. Hope to see you again soon!");
    }
}
