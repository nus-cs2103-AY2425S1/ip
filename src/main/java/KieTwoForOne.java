import java.util.Scanner;

public class KieTwoForOne {

    private static Task[] tasks = new Task[100];
    private static int count = 0;
    static String separationLine = "_________________________________________";
    static String chatBotName = "KieTwoForOne";

    public enum Instructions {
        LIST, BLAH, MARK, UNMARK, BYE, TODO, EVENT, DEADLINE
    }

    public static void addTasks(Task newTask) {
        int sum = 0;
        tasks[count] = newTask;
        count++;
        for (int i = 0; tasks[i] != null; i++) {
            sum++;
        }
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + newTask);
        System.out.println(String.format("Now you have %d tasks in the list", sum));
        System.out.println(separationLine);
    }

    public static void listTasks() {
        for (int i = 0; tasks[i] != null; i++) {
            System.out.println(String.format("%d. %s", i + 1, tasks[i].toString()));
        }
        System.out.println(separationLine);
    }

    public static void markTask(int position) {
        Task markedTask = tasks[position - 1];
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("   " + markedTask.markTask());
        System.out.println(separationLine);
    }

    public static void unmarkTask(int position) {
        Task unmarkedTask = tasks[position - 1];
        System.out.println("Ok. I've marked this task as not done yet:");
        System.out.println("   " + unmarkedTask.unmarkTask());
        System.out.println(separationLine);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(separationLine);
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");
        System.out.println(separationLine);

        while(true) {
            String input = scanner.nextLine();
            String[] instruction = input.split(" ", 2);
            String[] taskDetails = new String[0];
            if (instruction.length > 1) {
                taskDetails = instruction[1].split(" /", 0);
            }

            switch (Instructions.valueOf(instruction[0].toUpperCase())) {
                case LIST:
                    KieTwoForOne.listTasks();
                    break;
                case BYE:
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(separationLine);
                    break;
                case BLAH:
                    System.out.println("blah");
                    System.out.println(separationLine);
                    break;
                case MARK:
                    KieTwoForOne.markTask(Integer.valueOf(instruction[1]));
                    break;
                case UNMARK:
                    KieTwoForOne.unmarkTask(Integer.valueOf(instruction[1]));
                    break;
                case TODO:
                    KieTwoForOne.addTasks(new Todo(instruction[1]));
                    break;
                case EVENT:
                    KieTwoForOne.addTasks(new Event(taskDetails[0], taskDetails[1], taskDetails[2]));
                    break;
                case DEADLINE:
                    KieTwoForOne.addTasks(new Deadline(taskDetails[0], taskDetails[1]));
                    break;
                default:
                    break;
            }
            if (instruction[0].equalsIgnoreCase("bye")) {
                break;
            }
        }

        scanner.close();
    }
}
