import java.util.Scanner;

public class Fred {
    static String line = "____________________________________________________________";
    static String name = "Fred";
    static Task[] taskList = new Task[100];
    static int taskListIndex = 0;
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
            } else {
                addToTaskList(input);
            }
        }
    }

    private static void addToTaskList(String input) {
        if (taskListIndex < taskList.length) {
            taskList[taskListIndex] = new Task(input);
            System.out.println(line);
            System.out.println("added: " + input);
            System.out.println(line);
            taskListIndex++;
        } else {
            System.out.println(line);
            System.out.println("Unable to add to task list");
            System.out.println(line);
        }
    }

    private static void printTaskList() {
        int index = 1;
        System.out.println(line);
        while (taskList[index - 1] != null) {
            System.out.println(String.format("%d.[%s] %s", index, taskList[index - 1].getStatusIcon(), taskList[index - 1].getDescription()));
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
                "   [%s] %s", taskList[index].getStatusIcon(), taskList[index].getDescription()));
        System.out.println(line);
    }

    private static void markTaskAsNotDone(int index) {
        taskList[index].markAsNotDone();
        System.out.println(line);
        System.out.println(String.format("OK, I've marked this task as not done yet:\n" +
                "   [%s] %s", taskList[index].getStatusIcon(), taskList[index].getDescription()));
        System.out.println(line);
    }
}
