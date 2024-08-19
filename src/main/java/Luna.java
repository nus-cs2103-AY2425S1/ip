import java.util.Scanner;

public class Luna {

    public static void main(String[] args) {
        String greetings = "Hello! I'm Luna\n" +
                "What can I do for you?";
        System.out.println(greetings);

        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        int taskNum = 0;

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                String exit = "Bye! Hope to see you again soon!";
                System.out.println(exit);
                break;
            }

            if (input.equals("list")) {
                for (int i = 0; i < taskNum; i++) {
                    String taskStr = String.format("%d. %s", i + 1, tasks[i]);
                    System.out.println(taskStr);
                }
            } else {
                tasks[taskNum] = input;
                taskNum++;
                System.out.println("added: " + input);
            }

        }

        scanner.close();

    }
}
