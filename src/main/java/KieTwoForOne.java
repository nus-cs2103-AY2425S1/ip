import java.util.Scanner;

public class KieTwoForOne {

    private static Task[] tasks = new Task[100];
    private static int count = 0;
    static String separationLine = "_________________________________________";
    static String chatBotName = "KieTwoForOne";

    public enum Instructions {
        LIST, BLAH, MARK, UNMARK, BYE, READ, FIND
    }
    public static void addTasks(Task newtask) {
        tasks[count] = newtask;
        count++;
        System.out.println(String.format("added: %s", newtask.toString()));
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
        markedTask.setTrue();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(markedTask);
        System.out.println(separationLine);
    }

    public static void unmarkTask(int position) {
        Task unmarkedTask = tasks[position - 1];
        unmarkedTask.setFalse();
        System.out.println("Ok. I've marked this task as not done yet:");
        System.out.println(unmarkedTask);
        System.out.println(separationLine);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(separationLine);
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");
        System.out.println(separationLine);

        while(true) {
            String input = scanner.nextLine().toUpperCase();
            String[] instruction = input.split(" ", 2);

            switch (Instructions.valueOf(instruction[0])) {
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
                case READ, FIND:
                    KieTwoForOne.addTasks(new Task(String.format("%s %s", instruction[0], instruction[1])));
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
