import java.util.Scanner;
import java.util.ArrayList;

public class Winner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("""
                --------------------------------------
                Hello! I am Winner.
                What can I do for you today?
                --------------------------------------""".indent(10));

        while (true) {
            String input = scanner.nextLine();

            if (input.matches("(?i)list")) {
                int counter = 1;
                System.out.println(" ".repeat(10) + "-".repeat(38));
                for (Task i:tasks) {
                    System.out.println(" ".repeat(10) + counter + ". " + i.taskToString());
                    counter++;
                }
                System.out.println(" ".repeat(10) + "-".repeat(38));

            } else if (input.matches("(?i)mark \\d+")) {
                int taskNumber = scanner.nextInt();
                Task task = tasks.get(taskNumber - 1);
                task.markDone();
                System.out.println(("-".repeat(38) +
                        task.markDoneToString() +
                        "-".repeat(38)).indent(10));

            } else if (input.matches("(?i)bye")) {
                System.out.println("""
                        --------------------------------------
                        Hope I have been of service!
                        Thank you and see you again soon :D
                        --------------------------------------""".indent(10));
                break;

            } else {
                tasks.add(new Task(input));
                System.out.println("--------------------------------------".indent(10) +
                        ("added: " + input).indent(10) +
                        "--------------------------------------".indent(10));
            }
        }
        scanner.close();
    }
}