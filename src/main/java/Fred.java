import java.util.Scanner;

public class Fred {
    static String line = "____________________________________________________________";
    static String name = "Fred";
    static Task[] taskList = new Task[100];
    static int taskCount = 0;
    public static void main(String[] args) {
        greet();
        //echo();
        getInput();
        sayFarewell();
        exit();
    }

    private static void greet() {
        String greeting = "Hello! I'm " + name + "\n" +
                "What can I do for you?";
        System.out.println(line);
        System.out.println(greeting);
        System.out.println(line);
    }

    private static void sayFarewell() {
        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(line);
        System.out.println(farewell);
        System.out.println(line);
    }

    private static void exit(){
        System.exit(0);
    }

    private static void echo() {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(line);
            System.out.println(input);
            System.out.println(line);
        }
        sayFarewell();
        exit();
    }

    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                printTaskList();
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                markTaskAsDone(index);

            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                markTaskAsNotDone(index);
            } else if (input.startsWith("todo ")) {
                addToTaskList(input, "todo");
            } else if (input.startsWith("deadline ")) {
                addToTaskList(input, "deadline");
            } else if (input.startsWith("event ")) {
                addToTaskList(input, "event");
            }
        }
    }

    private static void addToTaskList(String input, String taskType) {
        String description = null;
        Task task;
        if (taskType.equals("todo")) {
            description = input.substring(5);
            task = new ToDo(description);
        } else if (taskType.equals("deadline")) {
            String[] details = input.substring(9).split(" /");
            description = details[0];
            String by = details[1].substring(3);
            task = new Deadline(description, by);
        } else {
            String[] details = input.substring(6).split(" /");
            description = details[0];
            String from = details[1].substring(5);
            String to = details[2].substring(3);
            task = new Event(description, from, to);
        }
        taskList[taskCount] = task;
        taskCount++;
        System.out.println(line);
        System.out.println(String.format("Got it. I've added this task:\n" +
                "   %s\n" +
                "Now you have %d tasks in the list.", task, taskCount));
        System.out.println(line);
    }

    private static void printTaskList() {
        int index = 1;
        System.out.println(line);
        while (taskList[index - 1] != null) {
            System.out.println(String.format("%d.%s", index, taskList[index - 1]));
            index++;
            if (index > taskList.length) {
                break;
            }
        }
        System.out.println(line);
    }

    private static void markTaskAsDone(int index) {
        taskList[index].markAsDone();
        System.out.println(line);
        System.out.println(String.format("Nice! I've marked this task as done:\n" +
                "   %s", taskList[index]));
        System.out.println(line);
    }

    private static void markTaskAsNotDone(int index) {
        taskList[index].markAsNotDone();
        System.out.println(line);
        System.out.println(String.format("OK, I've marked this task as not done yet:\n" +
                "   %s", taskList[index]));
        System.out.println(line);
    }
}
