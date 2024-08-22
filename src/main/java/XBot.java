import java.util.Scanner;

public class XBot {
    private static Task[] list = new Task[100];
    private static int taskCount = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm XBot\n" + "What can I do for you?");
        String input = scanner.nextLine().trim();
        while(!input.equalsIgnoreCase("bye")) {
            String[] words = input.split("\\s+", 2);
            String command = words[0].toLowerCase();
            String rest = words.length > 1 ? words[1] : "";
            switch(command) {
                case "list":
                    displayTask();
                    break;
                case "mark":
                    markDone(rest);
                    break;
                case "unmark":
                    markUndone(rest);
                    break;
                case "todo":
                    addTodo(rest);
                    break;
                case "event":
                    addEvent(rest);
                    break;
                case "deadline":
                    addDeadline(rest);
                    break;
                default:
                    if (!input.isEmpty()) {
                        addTask(input);
                    }
            }
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    public static void displayTask() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < taskCount; i++) {
            int index = i + 1;
            System.out.println(index + ". " + list[i].toString());
        }
    }

    public static void addTask(String input) {
        list[taskCount] = new Task(input);
        System.out.println("added: " + input);
        taskCount++;
    }

    public static void addTodo(String rest) {
        System.out.println("Got it. I've added this task:");
        list[taskCount] = new ToDo(rest);
        System.out.println(list[taskCount].toString());
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
    public static void addDeadline(String rest) {
        String[] parts = rest.split("/by", 2);
        System.out.println("Got it. I've added this task:");
        String taskDescription = parts[0].trim();
        String deadline = parts[1].trim();
        list[taskCount] = new Deadline(taskDescription, deadline);
        System.out.println(list[taskCount].toString());
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public static void addEvent(String rest) {
        String[] parts = rest.split("/from", 2);
        System.out.println("Got it. I've added this task:");
        String taskDescription = parts[0].trim();
        String time = parts[1].trim();
        String[] timeParts = time.split("/to", 2);
        String from = timeParts[0].trim();
        String to = timeParts[1].trim();

        list[taskCount] = new Event(taskDescription, from, to);
        System.out.println(list[taskCount].toString());
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }


    public static void markDone(String rest) {
        try {
            int taskNumber = Integer.parseInt(rest.trim());
            if (taskNumber > 0 && taskNumber <= taskCount) {
                list[taskNumber - 1].markAsDone();
            } else {
                System.out.println("Invalid task number!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid task number!");
        }
    }

    public static void markUndone(String rest) {
        try {
            int taskNumber = Integer.parseInt(rest.trim());
            if (taskNumber > 0 && taskNumber <= taskCount) {
                list[taskNumber - 1].markAsUndone();
            } else {
                System.out.println("Invalid task number!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid task number!");
        }
    }
}
