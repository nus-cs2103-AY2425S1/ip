import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rob {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        String greet = "Hello! I'm Rob\n" +
                "What can I do for you?\n";
        String exit = "Bye. Hope to see you again soon!\n";

        System.out.println(greet);

        while (true) {
            String input = scanner.nextLine();

            // exit
            if (Objects.equals(input, "bye")) {
                System.out.println(exit);
                break;
            } else if (Objects.equals(input, "list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; i++)
                {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________\n");
            } else if (input.startsWith("mark")) {
                int taskNum = findTaskNum(input);
                if (taskNum > 0 && taskNum <= taskCount) {
                    if (tasks[taskNum - 1].isDone) {
                        System.out.println("Already done!");
                    } else {
                        tasks[taskNum - 1].markAsDone();
                        String marked = "____________________________________________________________\n" +
                                "Nice! I've marked this task as done:\n" + tasks[taskNum - 1] + "\n" +
                                "____________________________________________________________\n";
                        System.out.println(marked);
                    }
                } else {
                    System.out.println("Invalid task number... Try another?");
                }
            } else if (input.startsWith("unmark")) {
                int taskNum = findTaskNum(input);

                if (taskNum > 0 && taskNum <= taskCount) {
                    if (!tasks[taskNum - 1].isDone) {
                        System.out.println("Already unmarked!");
                    } else {
                        tasks[taskNum - 1].unmark();
                        String unmarked = "____________________________________________________________\n" +
                                "OK, I've marked this task as not done yet:\n" + tasks[taskNum - 1] + "\n" +
                                "____________________________________________________________\n";
                        System.out.println(unmarked);
                    }
                } else {
                    System.out.println("Invalid task number... Try another?");
                }
            } else {
                tasks[taskCount] = new Task(input);
                taskCount++;

                // echo
                String echo = "____________________________________________________________\n" +
                        "added: " + input + "\n" +
                        "____________________________________________________________\n";
                System.out.println(echo);
            }
        }
        scanner.close();
    }

    // method to get the task number using regex
    private static int findTaskNum(String input) {
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        String lastNum = null;

        while (matcher.find()) {
            lastNum = matcher.group();
        }
        return (lastNum != null) ? Integer.parseInt(lastNum) : -1;
    }

}
