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

    public static void markDone(String rest) {
        int taskNumber = Integer.parseInt(rest.trim());
        list[taskNumber - 1].markAsDone();
    }

    public static void markUndone(String rest) {
        int taskNumber = Integer.parseInt(rest.trim());
        list[taskNumber - 1].markAsUndone();
    }
}
