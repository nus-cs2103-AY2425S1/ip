import java.util.Scanner;

public class Moody {
    public static void main(String[] args) {
        String spacer = "____________________________________________________________\n";
        String indent = "    ";

        System.out.println(spacer
                + "Hello! I'm Moody!\nWhat can I do for you?\n"
                + spacer);

        Scanner scanner = new Scanner(System.in);
        String userInput;
        Task[] userTasks = new Task[100];
        int taskCount = 0;

        while (true) {
            userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(spacer
                        + "Bye. Hope to see you again soon!\n"
                        + spacer);
                break;
            } else if (userInput.equals("list")) {
                System.out.print(spacer);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + userTasks[i]);
                }
                System.out.println(spacer);
            } else if (userInput.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                userTasks[taskNumber].markAsDone();
                System.out.println(spacer
                        + "Nice! I've marked this task as done:");
                System.out.println(indent
                        + userTasks[taskNumber].toString());
                System.out.println(spacer);
            } else if (userInput.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                userTasks[taskNumber].markAsNotDone();
                System.out.println(spacer
                        + "OK, I've marked this task as not done yet:");
                System.out.println(indent
                        + userTasks[taskNumber].toString());
                System.out.println(spacer);
            } else {
                Task task = new Task(userInput);
                userTasks[taskCount] = task;
                taskCount++;
                System.out.println(spacer
                        + "added: "
                        + userInput
                        + "\n"
                        + spacer);
            }
        }
    }
}
