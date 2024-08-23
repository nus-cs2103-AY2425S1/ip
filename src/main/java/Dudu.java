import java.util.ArrayList;
import java.util.Scanner;
import java.lang.StringBuilder;

public class Dudu {
    public static void main(String[] args) {
        String welcomeMessage = LineWrapper.wrap("Hello! I'm dudu\n"
                                                + "What can I do for you?");
        String goodbyeMessage = LineWrapper.wrap("Bye. Hope to see you again soon!");

        System.out.println(welcomeMessage);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine().toLowerCase();
                if (input.equals("list")) {
                    if (tasks.isEmpty()) {
                        System.out.println(LineWrapper.wrap("No tasks"));
                    } else {
                        StringBuilder output = new StringBuilder("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            output.append("\n" + (i + 1) + ". " + tasks.get(i));
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
                    if (pos < 0 || pos >= tasks.size()) {
                        throw new IllegalArgumentException("Please input a valid count");
                    }
                    tasks.get(pos).markCompleted();
                    String output = LineWrapper.wrap(String.format("Nice! I've marked this task as done:\n    %s", tasks.get(pos)));
                    System.out.println(output);
                } else if (input.matches("unmark\\s\\d+")) {
                    int pos = Integer.parseInt(input.replaceAll("\\D+", "")) - 1;
                    if (pos < 0 || pos >= tasks.size()) {
                        throw new IllegalArgumentException("Please input a valid count");
                    }
                    tasks.get(pos).markUncompleted();
                    String output = LineWrapper.wrap(String.format("OK, I've marked this task as not done yet:\n    %s", tasks.get(pos)));
                    System.out.println(output);
                } else if (input.matches("^delete\\s\\d+")) {
                    int pos = Integer.parseInt(input.replaceAll("\\D+", "")) - 1;
                    if (pos < 0 || pos >= tasks.size()) {
                        throw new IllegalArgumentException("Please input a valid count");
                    }
                    tasks.remove(pos);
                    String output = LineWrapper.wrap(String.format(" Noted. I've removed this task:\n    %s", tasks.get(pos)));
                    System.out.println(output);
                } else if (input.matches("^todo.*")) {
                    String description = getDetails(input, "todo");
                    ToDo task = new ToDo(description);
                    tasks.add(task);
                    String output = LineWrapper.wrap(String.format("Got it. I've added this task:\n    %s\nNow you have %d tasks in the list.", task, tasks.size()));
                    System.out.println(output);
                } else if (input.matches("^deadline.*")) {
                    String[] details = getDetails(input, "deadline").split(" /by ");
                    if (details.length <= 1 || details[1].trim().isEmpty()) {
                        throw new MissingDescriptionException("Missing by time");
                    }
                    if (details[0].trim().isEmpty()) {
                        throw new MissingDescriptionException("Missing description after deadline");
                    }
                    Deadline task = new Deadline(details[0], details[1]);
                    tasks.add(task);
                    String output = LineWrapper.wrap(String.format("Got it. I've added this task:\n    %s\nNow you have %d tasks in the list.", task, tasks.size()));
                    System.out.println(output);
                } else if (input.matches("^event.*")) {
                    String[] details = getDetails(input, "event").split("/from");
                    if (details[0].trim().isEmpty()) {
                        throw new MissingDescriptionException("Missing description");
                    }
                    String description = details[0];
                    if (details.length == 1) {
                        throw new MissingDateTimeException("Missing by time");
                    }
                    String[] date = details[1].split(" /to ");
                    if (date.length <= 1 || date[0].trim().isEmpty() || date[1].trim().isEmpty()) {
                        throw new MissingDateTimeException("Missing to or from time");
                    }
                    Event task = new Event(description, date[0], date[1]);
                    tasks.add(task);
                    String output = LineWrapper.wrap(String.format("Got it. I've added this task:\n    %s\nNow you have %d tasks in the list.", task, tasks.size()));
                    System.out.println(output);
                } else {
                    String output = LineWrapper.wrap("Please use \"help\" to get the list of commands");
                    System.out.println(output);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            } catch (MissingDescriptionException e) {
                System.out.println(e);
            } catch (MissingDateTimeException e) {
                System.out.println(e);
            }
        }

        System.out.println(goodbyeMessage);
    }

    public static String getDetails(String input, String task) throws MissingDescriptionException {
        String[] details = input.split(task, 2);
        if (details.length <= 1 || details[1].trim().isEmpty()) {
            throw new MissingDescriptionException(String.format("Please key in a description after %s", task));
        }
        return details[1];
    }
}
