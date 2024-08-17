import java.util.ArrayList;
import java.util.Scanner;

public class Thanos {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("%d.%s\n", i + 1, task);
        }
    }

    private static void markTask(String[] inputArr) {
        if (inputArr.length > 1) {
            int index = Integer.parseInt(inputArr[1]) - 1;
            if (index >= 0 && index < tasks.size()) {
                Task task = tasks.get(index);
                task.setIsDone(true);
                System.out.printf("Nice! I've marked this task as done:\n%s\n", task);
            }
        }
    }

    private static void unmarkTask(String[] inputArr) {
        if (inputArr.length > 1) {
            int index = Integer.parseInt(inputArr[1]) - 1;
            if (index >= 0 && index < tasks.size()) {
                Task task = tasks.get(index);
                task.setIsDone(false);
                System.out.printf("OK, I've marked this task as not done yet:\n%s\n", task);
            }
        }
    }

    private static void printTaskAdded(Task task) {
        System.out.printf("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.\n", task, tasks.size());
    }

    private static void addTodo(String userInput) {
        String description = userInput.substring(5);
        Todo todo = new Todo(description);
        tasks.add(todo);
        printTaskAdded(todo);
    }

    private static void addDeadline(String userInput) {
        String details = userInput.substring(9);
        String[] detailsArr = details.split(" /by ");
        if (detailsArr.length == 2) {
            Deadline deadline = new Deadline(detailsArr[0], detailsArr[1]);
            tasks.add(deadline);
            printTaskAdded(deadline);
        }
    }

    private static void addEvent(String userInput) {
        String details = userInput.substring(6);
        String[] detailsArr = details.split(" /from ");
        if (detailsArr.length == 2) {
            String description = detailsArr[0];
            String[] fromToArr = detailsArr[1].split(" /to ");
            if (fromToArr.length == 2) {
                Event event = new Event(description, fromToArr[0], fromToArr[1]);
                tasks.add(event);
                printTaskAdded(event);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Thanos!\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            String[] inputArr = userInput.split(" ");
            String command = inputArr[0];
            switch (command) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    listTasks();
                    break;
                case "mark":
                    markTask(inputArr);
                    break;
                case "unmark":
                    unmarkTask(inputArr);
                    break;
                case "todo":
                    addTodo(userInput);
                    break;
                case "deadline":
                    addDeadline(userInput);
                    break;
                case "event":
                    addEvent(userInput);
                    break;
            }
        }
    }
}
