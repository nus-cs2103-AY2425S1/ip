import javax.sound.sampled.Line;
import java.util.Scanner;
import java.lang.StringBuilder;

public class Dudu {
    public static void main(String[] args) {
        String welcomeMessage = LineWrapper.wrap("Hello! I'm dudu\n"
                                                + "What can I do for you?");
        String goodbyeMessage = LineWrapper.wrap("Bye. Hope to see you again soon!");

        System.out.println(welcomeMessage);

        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int count = 0;

        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine().toLowerCase();
                if (input.equals("list")) {
                    if (count == 0) {
                        System.out.println(LineWrapper.wrap("No tasks"));
                    } else {
                        StringBuilder output = new StringBuilder("Here are the tasks in your list:");
                        for (int i = 0; i < count; i++) {
                            output.append("\n" + (i + 1) + ". " + tasks[i]);
                        }
                        System.out.println(LineWrapper.wrap(output.toString()));
                    }
                } else if (input.equals("bye")) {
                    break;
                } else if (input.equals("help")) {
                    String output = LineWrapper.wrap("hehe not done");
                    System.out.println(output);
                } else if (input.matches("mark\\s\\d+")) {
                    int pos = Integer.parseInt(input.replaceAll("\\D+", "")) - 1;
                    tasks[pos].markCompleted();
                    String output = LineWrapper.wrap(String.format("Nice! I've marked this task as done:\n    %s", tasks[pos]));
                    System.out.println(output);
                } else if (input.matches("unmark\\s\\d+")) {
                    int pos = Integer.parseInt(input.replaceAll("\\D+", "")) - 1;
                    tasks[pos].markUncompleted();
                    String output = LineWrapper.wrap(String.format("OK, I've marked this task as not done yet:\n    %s", tasks[pos]));
                    System.out.println(output);
                } else if (input.matches("^todo.*")) {
                    String description = getDetails(input, "todo");
                    ToDo task = new ToDo(description);
                    tasks[count] = task;
                    count++;
                    String output = LineWrapper.wrap(String.format("Got it. I've added this task:\n    %s\nNow you have %d tasks in the list.", task, count));
                    System.out.println(output);
                } else if (input.matches("^deadline\\s/by\\s.+")) {
                    String[] details = getDetails(input, "deadline").split(" /by ");
                    if (details[0].trim().isEmpty()) {
                        throw new MissingDescriptionException("Missing description");
                    }
                    if (details[1].trim().isEmpty()) {
                        throw new MissingDateTimeException("Missing by time");
                    }
                    Deadline task = new Deadline(details[0], details[1]);
                    tasks[count] = task;
                    count++;
                    String output = LineWrapper.wrap(String.format("Got it. I've added this task:\n    %s\nNow you have %d tasks in the list.", task, count));
                    System.out.println(output);
                } else if (input.matches("^event\\s/from\\s.+\\sto\\s.+")) {
                    String details = getDetails(input, "event");
                    String description = details.split("/from")[0].trim();
                    if (description.isEmpty()) {
                        throw new MissingDescriptionException("Missing description");
                    }
                    String[] date = details.split("/from ")[1].split(" /to ");
                    if (date[0].trim().isEmpty() || date[1].trim().isEmpty()) {
                        throw new MissingDateTimeException("Missing by or from time");
                    }
                    Event task = new Event(description, date[0], date[1]);
                    tasks[count] = task;
                    count++;
                    String output = LineWrapper.wrap(String.format("Got it. I've added this task:\n    %s\nNow you have %d tasks in the list.", task, count));
                    System.out.println(output);
                } else {
                    String output = LineWrapper.wrap("Please use \"help\" to get the list of commands");
                    System.out.println(output);
                }
            } catch (MissingDescriptionException e) {
                System.out.println(e);
            } catch (MissingDateTimeException e) {
                System.out.println(e);
            }
        }

        System.out.println(goodbyeMessage);
    }

    public static String getDetails(String input, String task) throws MissingDescriptionException {
        String[] details = input.split(task + " ");
        if (details.length <= 1) {
            throw new MissingDescriptionException(String.format("Please key in a description after %s", task));
        }
        return details[1];
    }
}
