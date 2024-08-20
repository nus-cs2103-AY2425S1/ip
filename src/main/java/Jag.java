import java.util.Scanner;
import java.util.ArrayList;

public class Jag {
    public static void main(String[] args) throws AExceptions {
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
        String dashed = "----------";

        // Greeting and receiving first input
        System.out.println(greetings);
        String answer = scanner.nextLine();

        // Chatbot
        while (!answer.equals("bye")) {

            // Feature for displaying list
            if (answer.equals("list")) {
                System.out.println(dashed);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.println((i + 1) + ". " + task.toString());
                }
                System.out.println(dashed);
                answer = scanner.nextLine();
                continue;
            }

            // Checking for mark
            if (answer.length() >= 4) {
                String mark = answer.substring(0, 4);
                char marker = answer.charAt(answer.length() - 1);
                int index = 0;

                // Convert index character to a string
                if (Character.isDigit(marker)) {
                    index = Integer.parseInt(Character.toString(marker));
                }

                // Feature for mark
                if (mark.equals("mark")) {
                    Task task = tasks.get(index-1);

                    System.out.println(dashed);
                    System.out.println("Nice! I've marked this task as done:");
                    task.setStatus(true);
                    System.out.println(task.toString());
                    System.out.println(dashed);

                    answer = scanner.nextLine();
                    continue;
                }

            }

            // Checking for unmark
            if (answer.length() >= 6) {
                String unmark = answer.substring(0, 6);
                char marker = answer.charAt(answer.length() - 1);
                int index = 0;

                // Convert index character to a string
                if (Character.isDigit(marker)) {
                    index = Integer.parseInt(Character.toString(marker));
                }
                // Feature for unmark
                if (unmark.equals("unmark")) {
                    Task task = tasks.get(index-1);

                    System.out.println(dashed);
                    System.out.println("OK, I've marked this task as not done yet:");
                    task.setStatus(false);
                    System.out.println(task.toString());
                    System.out.println(dashed);

                    answer = scanner.nextLine();
                    continue;
                }
            }


            // Creating todos
            if (answer.length() >= 4) {
                String todo = answer.substring(0, 4);
                if (todo.equals("todo")) {
                    // Exception handling
                    if (answer.length() == 4) {
                        System.out.println(dashed);
                        AExceptions ex = new AExceptions("The description of a todo cannot be empty.");
                        System.out.println(ex.getErrorMessage());
                        System.out.println(dashed);
                        answer = scanner.nextLine();
                        continue;
                    }

                    String[] split = answer.split("todo");
                    String description = split[1].trim();
                    Todo newTodo = new Todo(description);
                    tasks.add(newTodo);

                    System.out.println(dashed);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(newTodo.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list");
                    System.out.println(dashed);

                    answer = scanner.nextLine();
                    continue;
                }
            }

            // Creating Deadlines
            if (answer.length() >= 8) {
                String deadline = answer.substring(0, 8);
                if (deadline.equals("deadline")) {
                    String[] split = answer.split("/by");
                    String description = split[0].replaceFirst("deadline", "").trim();
                    String by = split[1].trim();
                    Deadline newDeadline = new Deadline(description, by);
                    tasks.add(newDeadline);

                    System.out.println(dashed);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newDeadline.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list");
                    System.out.println(dashed);

                    answer = scanner.nextLine();
                    continue;
                }
            }

            // Creating Events
            if (answer.length() >= 5) {
                String event = answer.substring(0, 5);
                if (event.equals("event")) {
                    String[] split = answer.split("/from | /to");
                    String description = split[0].replaceFirst("event", "").trim();
                    String from = split[1].trim();
                    String to = split[2].trim();
                    Event newEvent = new Event(description, from, to);
                    tasks.add(newEvent);

                    System.out.println(dashed);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newEvent.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list");
                    System.out.println(dashed);

                    answer = scanner.nextLine();
                    continue;
                }
            }

            // Wrong command
            else {
                System.out.println(dashed);
                AExceptions ex = new AExceptions("I'm sorry, but I don't know what that means :-(");
                System.out.println(ex.getErrorMessage());
                System.out.println(dashed);
                answer = scanner.nextLine();
                continue;
            }

        }

        // Goodbye message
        System.out.printf(bye);
        scanner.close();

    }
}