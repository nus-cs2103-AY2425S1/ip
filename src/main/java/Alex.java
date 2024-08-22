import java.util.ArrayList;
import java.util.Scanner;

public class Alex {

    public static final String byeMessage = "Bye. Hope to see you again soon!";
    public static final String LINE = "----------------------------------------------------";
    ArrayList<Task> list = new ArrayList<>();

    public void scan() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase("bye")) {
            System.out.println(LINE);
            System.out.println(byeMessage);
            System.out.println(LINE);
        }
        else if (userInput.equalsIgnoreCase("list")) {
            System.out.println(LINE + "\n" +
                    "Here are the tasks in your list: ");
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i));
            }
            System.out.println(LINE);
            scan();
        } else if (userInput.startsWith("mark")) {
            int index = Integer.parseInt(userInput.substring(5)) - 1;
            if (index >= 0 && index < list.size()) {
                Task task = list.get(index);
                System.out.println(LINE);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done: \n" +
                        task.toString());
                System.out.println(LINE);
            } else {
                System.out.println(LINE);
                System.out.println("Invalid index. Please try again.");
                System.out.println(LINE);
            }
            scan();
        } else if (userInput.startsWith("unmark")) {
            int index = Integer.parseInt(userInput.substring(7)) - 1;
            if (index >= 0 && index < list.size()) {
                Task task = list.get(index);
                System.out.println(LINE);
                task.unmark();
                System.out.println("OK, I've marked this task as not done yet: \n" +
                        task.toString());
                System.out.println(LINE);
            } else {
                System.out.println(LINE);
                System.out.println("Invalid index. Please try again.");
                System.out.println(LINE);
            }
            scan();
        } else if (userInput.startsWith("todo")) {
            String description = userInput.substring(5);
            Todo todo = new Todo(description);
            list.add(todo);
            System.out.println(LINE);
            System.out.println("Got it. I've added this task: \n" + todo.toString()
            + "\n Now you have " + list.size() + " tasks in the list.");
            System.out.println(LINE);
            scan();
        } else if (userInput.startsWith("deadline")) {
            String description = userInput.substring(9);
            String[] details = description.split("/");
            String task = details[0];
            String dueDate = details[1].substring(3);
            Deadline deadline = new Deadline(task, dueDate);
            list.add(deadline);
            System.out.println(LINE);
            System.out.println("Got it. I've added this task: \n" + deadline.toString()
                    + "\n Now you have " + list.size() + " tasks in the list.");
            System.out.println(LINE);
            scan();
        } else if (userInput.startsWith("event")) {
            String description = userInput.substring(6);
            String[] details = description.split("/");
            String task = details[0];
            String startDate = details[1].substring(5);
            String endDate = details[2].substring(3);
            Event event = new Event(task, startDate, endDate);
            list.add(event);
            System.out.println(LINE);
            System.out.println("Got it. I've added this task: \n" + event.toString()
                    + "\n Now you have " + list.size() + " tasks in the list.");
            System.out.println(LINE);
            scan();
        } else {
            System.out.println(LINE);
            System.out.println("Invalid input. Please try again");
            System.out.println(LINE);
            scan();
        }
    }
    public static void main(String[] args) {

        Alex alex = new Alex();

        System.out.println(LINE);
        System.out.println("Hello! I'm Alex 👋🏼🤖 \n" +
                "What can I do for you? ");
        System.out.println(LINE);

        alex.scan();
    }
}
