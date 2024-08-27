import java.util.Scanner;

public class KieTwoForOne {

    private static String[] tasks = new String[100];
    private static int count = 0;
    static String separationLine = "_________________________________________";
    static String chatBotName = "KieTwoForOne";

    public static void addTasks(String task) {
        tasks[count] = task;
        count++;
        System.out.println(String.format("added: %s", task));
        System.out.println(separationLine);
    }

    public static void listTasks() {
        for (int i = 0; tasks[i] != null; i++) {
            System.out.println(String.format("%d. %s", i + 1, tasks[i]));
        }
        System.out.println(separationLine);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(separationLine);
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");
        System.out.println(separationLine);

        while(true) {
            String instruction = scanner.nextLine();
            switch (instruction.toUpperCase()) {
                case "LIST":
                    KieTwoForOne.listTasks();
                    break;
                case "BYE":
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(separationLine);
                    break;
                case "BLAH":
                    System.out.println("blah");
                    System.out.println(separationLine);
                    break;
                default:
                    KieTwoForOne.addTasks(instruction);
                    break;
            }
            if (instruction.equalsIgnoreCase("bye")) {
                break;
            }
        }

        scanner.close();
    }
}
