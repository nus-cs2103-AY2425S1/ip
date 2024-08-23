import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConverSage {
    public static void main(String[] args) {
        String logo = "  ____                          ____                   \n" +
                " / ___|___  _ ____   _____ _ __/ ___|  __ _  __ _  ___ \n" +
                "| |   / _ \\| '_ \\ \\ / / _ \\ '__\\___ \\ / _` |/ _` |/ _ \\\n" +
                "| |__| (_) | | | \\ V /  __/ |   ___) | (_| | (_| |  __/\n" +
                " \\____\\___/|_| |_|\\_/ \\___|_|  |____/ \\__,_|\\__, |\\___|\n" +
                "                                            |___/      ";
        String horizontalLine = "____________________________________________________________";
        System.out.println(logo);
        System.out.println("Greetings, I'm your ConverSage.");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        Scanner scanner = new Scanner(System.in);
        String input;

        List<Task> taskList = new ArrayList<>();

        while (true) {
            input = scanner.nextLine();
            System.out.println(horizontalLine);

            if (input.equalsIgnoreCase("list")) {
                for (int i = 1; i <= taskList.size(); i++) {
                    System.out.println(i + ". " + taskList.get(i-1));
                }
                System.out.println(horizontalLine);
            } else if (input.equalsIgnoreCase("bye")) {
                System.out.println("Goodbye. We shall meet again soon.");
                System.out.println(horizontalLine);
                break;
            } else if (input.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                Task currTask = taskList.get(taskIndex);
                String doneTask = currTask.markAsDone();
                System.out.println("Good job. I've marked this task as done.");
                System.out.println(doneTask);
                System.out.println(horizontalLine);

            } else if (input.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                Task currTask = taskList.get(taskIndex);
                String undoneTask = currTask.markAsUndone();
                System.out.println("I've marked this task as not done yet, get to it quickly.");
                System.out.println(undoneTask);
                System.out.println(horizontalLine);

            } else {
                System.out.println("added: " + input);
                System.out.println(horizontalLine);
                taskList.add(new Task(input));
            }

        }
    }
}
