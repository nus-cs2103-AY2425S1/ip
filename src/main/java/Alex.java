import java.util.ArrayList;
import java.util.Scanner;

public class Alex {

    public static final String byeMessage = "Bye. Hope to see you again soon!";
    public static final String LINE = "----------------------------------------------------";
    ArrayList<Task> list = new ArrayList<>();
    public void handleList() {
        System.out.println(LINE + "\n" +
                "Here are the tasks in your list: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
        System.out.println(LINE);
        scan();
    }
    public void handleMark(String input) {
        int index = Integer.parseInt(input.substring(5)) - 1;
        if (index >= 0 && index < list.size()) {
            Task task = list.get(index);
            System.out.println(LINE);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done: \n" +
                    task.toString());
            System.out.println(LINE);
        } else {
            System.out.println(LINE);
            System.out.println("It seems that task does not exist. Please try again.");
            System.out.println(LINE);
        }
        scan();
    }
    public void handleUnmark(String input) {
        int index = Integer.parseInt(input.substring(7)) - 1;
        if (index >= 0 && index < list.size()) {
            Task task = list.get(index);
            System.out.println(LINE);
            task.unmark();
            System.out.println("OK, I've marked this task as not done yet: \n" +
                    task.toString());
            System.out.println(LINE);
        } else {
            System.out.println(LINE);
            System.out.println("It seems that task does not exist. Please try again.");
            System.out.println(LINE);
        }
        scan();
    }
    public void handleTodo(String input) {
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            System.out.println("You missed out some details. Try again");
        } else {
            Todo todo = new Todo(description);
            list.add(todo);
            System.out.println(LINE);
            System.out.println("Got it. I've added this task: \n" + todo.toString()
                    + "\n Now you have " + list.size() + " tasks in the list.");
            System.out.println(LINE);
        }
        scan();
    }
    public void handleDeadline(String input) {
        String description = input.substring(8).trim();
        if (description.isEmpty()) {
            System.out.println("You missed out the details of the deadline task. Try again");
        } else {
            String[] details = description.split("/");
            String task = details[0].trim();
            String dueDate = details[1].substring(2).trim();
            if (task.isEmpty() || dueDate.isEmpty()) {
                System.out.println("You missed out some details. Try again");
            } else {
                Deadline deadline = new Deadline(task, dueDate);
                list.add(deadline);
                System.out.println(LINE);
                System.out.println("Got it. I've added this task: \n" + deadline.toString()
                        + "\n Now you have " + list.size() + " tasks in the list.");
                System.out.println(LINE);
            }
        }
        scan();
    }
    public void handleEvent(String input) {
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            System.out.println("You missed out the details of the event task. Try again");
        } else {
            String[] details = description.split("/");
            String task = details[0].trim();
            String startDate = details[1].substring(4).trim();
            String endDate = details[2].substring(2).trim();
            if (task.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
                System.out.println("You missed out some details. Try again");
            } else {
                Event event = new Event(task, startDate, endDate);
                list.add(event);
                System.out.println(LINE);
                System.out.println("Got it. I've added this task: \n" + event.toString()
                        + "\n Now you have " + list.size() + " tasks in the list.");
                System.out.println(LINE);
            }
        }
        scan();
    }
    public void handleDelete(String input) {
        int index = Integer.parseInt(input.substring(7)) - 1;
        if (index >= 0 && index < list.size()) {
            Task task = list.get(index);
            System.out.println(LINE);
            list.remove(index);
            System.out.println("OK, I've deleted this task: \n" + task.toString() +
                    "\n Now you have " + list.size() + " tasks left in the list.");
            System.out.println(LINE);
        } else {
            System.out.println(LINE);
            System.out.println("It seems that task does not exist. Please try again.");
            System.out.println(LINE);
        }
        scan();
    }
    public void scan() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase("bye")) {
            System.out.println(LINE);
            System.out.println(byeMessage);
            System.out.println(LINE);
        }
        else if (userInput.equalsIgnoreCase("list")) {
            handleList();
        } else if (userInput.startsWith("mark")) {
            handleMark(userInput);
        } else if (userInput.startsWith("unmark")) {
            handleUnmark(userInput);
        } else if (userInput.startsWith("todo")) {
            handleTodo(userInput);
        } else if (userInput.startsWith("deadline")) {
            handleDeadline(userInput);
        } else if (userInput.startsWith("event")) {
            handleEvent(userInput);
        } else if (userInput.startsWith("delete")) {
            handleDelete(userInput);
        } else {
            System.out.println(LINE);
            System.out.println("Sorry, I don't understand that command. Did you make a typo?");
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
