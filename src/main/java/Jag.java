import java.util.Scanner;
import java.util.ArrayList;

public class Jag {
    public static void main(String[] args) {
        // Variables
        Scanner scanner = new Scanner(System.in);
        String greetings = """
                ------------------
                Hello! I'm Jag
                What can I do for you?
                ------------------""";
        String bye = """
                ------------------
                Bye. Hope to see you again soon!
                ------------------""";
        ArrayList<Task> tasks = new ArrayList<>();

        // Greeting and receiving first input
        System.out.println(greetings);
        String answer = scanner.nextLine();

        // Chatbot
        while (!answer.equals("bye")) {

            // Feature for displaying list
            if (answer.equals("list")) {
                System.out.println("------------");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.println((i + 1) + ". " + task.getStatusIcon() + task.getDescription());
                }
                System.out.println("------------");
                answer = scanner.nextLine();
                continue;
            }

            // Checking if mark or unmark
            if (answer.length() >= 4) {
                String mark = answer.substring(0, 4);
                String unmark = answer.substring(0, 6);
                char marker = answer.charAt(answer.length() - 1);
                int index = 0;

                // Convert index character to a string
                if (Character.isDigit(marker)) {
                    index = Integer.parseInt(Character.toString(marker));
                }

                if (mark.equals("mark")) {
                    Task task = tasks.get(index-1);
                    System.out.println("----------");
                    System.out.println("Nice! I've marked this task as done:");
                    task.setStatus(true);
                    System.out.println(task.getStatusIcon() + task.getDescription());
                    System.out.println("----------");
                    answer = scanner.nextLine();
                    continue;
                }
                if (unmark.equals("unmark")) {
                    Task task = tasks.get(index-1);
                    System.out.println("----------");
                    System.out.println("OK, I've marked this task as not done yet:");
                    task.setStatus(false);
                    System.out.println(task.getStatusIcon() + task.getDescription());
                    System.out.println("----------");
                    answer = scanner.nextLine();
                    continue;
                }

            }


            // Creating a task
            System.out.println("------------------");
            System.out.println("added: " + answer);
            System.out.println("------------------");
            tasks.add(new Task(answer));
            answer = scanner.nextLine();
        }

        // Goodbye message
        System.out.printf(bye);
        scanner.close();

    }
}