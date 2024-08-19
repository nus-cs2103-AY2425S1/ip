import java.util.Scanner;

public class Luna {

    public static void main(String[] args) {
        String greetings = "Hello! I'm Luna\n" +
                "What can I do for you?";
        System.out.println(greetings);

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskNum = 0;

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                String exit = "Bye! Hope to see you again soon!";
                System.out.println(exit);
                break;
            }

            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskNum; i++) {
                    String taskStr = String.format("%d.%s",
                            i + 1, tasks[i].toString());
                    System.out.println(taskStr);
                }
            } else {
                String[] str = input.split(" ");

                if (str[0].equals("mark")) {
                    int i = Integer.parseInt(str[1]) - 1;
                    tasks[i].markAsDone();

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks[i].toString());
                } else if (str[0].equals("unmark")) {
                    int i = Integer.parseInt(str[1]) - 1;
                    tasks[i].unmark();

                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks[i].toString());
                } else {
                    tasks[taskNum] = new Task(input);
                    taskNum++;
                    System.out.println("added: " + input);
                }
            }
        }

        scanner.close();

    }
}
