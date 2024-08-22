import java.util.Scanner;

public class XBot {
    private static Task[] list = new Task[100];
    private static int taskCount = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm XBot\n" + "What can I do for you?");
        String input = scanner.nextLine().trim();
        while(!input.equalsIgnoreCase("bye")) {
            try {
                processInput(input);
            } catch (XBotException e) {
                System.out.println("Oh No!! " + e.getMessage());
            }
            input = scanner.nextLine().trim();
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    public static void processInput(String input) throws XBotException {
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
                if (rest.isEmpty()) {
                    throw new XBotException("The description of the todo cannot be empty!");
                }
                addTodo(rest);
                break;
            case "event":
                if (rest.isEmpty()) {
                    throw new XBotException("The description of the event cannot be empty!");
                }
                addEvent(rest);
                break;
            case "deadline":
                if (rest.isEmpty()) {
                    throw new XBotException("The description of the deadline cannot be empty!");
                }
                addDeadline(rest);
                break;
            default:
                throw new XBotException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void displayTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            int index = i + 1;
            System.out.println(index + ". " + list[i].toString());
        }
    }

    public static void addTodo(String rest) {
        System.out.println("Got it. I've added this task:");
        list[taskCount] = new ToDo(rest);
        System.out.println(list[taskCount].toString());
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
    public static void addDeadline(String rest) throws XBotException {
        String[] parts = rest.split("/by", 2);
        if (parts.length == 2) {
            System.out.println("Got it. I've added this task:");
            String taskDescription = parts[0].trim();
            String deadline = parts[1].trim();
            list[taskCount] = new Deadline(taskDescription, deadline);
            System.out.println(list[taskCount].toString());
            taskCount++;
            System.out.println("Now you have " + taskCount + " tasks in the list.");
        } else {
            throw new XBotException("Invalid input format. Please use the format: 'deadline <task> /by <date>'");
        }
    }

    public static void addEvent(String rest) throws XBotException{
        String[] parts = rest.split("/from", 2);
        if (parts.length == 2) {
            System.out.println("Got it. I've added this task:");
            String taskDescription = parts[0].trim();
            String time = parts[1].trim();
            String[] timeParts = time.split("/to", 2);
            if (timeParts.length == 2) {
                String from = timeParts[0].trim();
                String to = timeParts[1].trim();

                list[taskCount] = new Event(taskDescription, from, to);
                System.out.println(list[taskCount].toString());
                taskCount++;
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            }
        } else {
            throw new XBotException("Invalid input format. Please use the format: 'event <task> /from <start time> /to <end time>'");
        }
    }


    public static void markDone(String rest) throws XBotException {
        try {
            int taskNumber = Integer.parseInt(rest.trim());
            if (taskNumber > 0 && taskNumber <= taskCount) {
                list[taskNumber - 1].markAsDone();
            } else {
                throw new XBotException("This task number do not exist.");
            }
        } catch (NumberFormatException e) {
            throw new XBotException("Invalid task number!");
        }
    }

    public static void markUndone(String rest) throws XBotException {
        try {
            int taskNumber = Integer.parseInt(rest.trim());
            if (taskNumber > 0 && taskNumber <= taskCount) {
                list[taskNumber - 1].markAsUndone();
            } else {
                throw new XBotException("This task number do not exist.");
            }
        } catch (NumberFormatException e) {
            throw new XBotException("Invalid task number!");
        }
    }
}
