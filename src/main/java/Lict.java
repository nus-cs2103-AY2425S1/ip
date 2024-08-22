import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lict {
    private final static String name = "Lict";
    private final static String horizontal_line = "__________________________________";
    private final static ArrayList<Task> tasks = new ArrayList<>();
    private final static ArrayList<String> actions = new ArrayList<>(List.of("mark", "unmark", "delete"));
    private final static ArrayList<String> eventTypes = new ArrayList<>(List.of("todo", "deadline", "event"));

    private static boolean handle_markings(String input) {
        String[] input_parts = input.split(" ");
        String action = input_parts[0].trim();
        if (!actions.contains(action)) {
            return false;
        }
        if (input_parts.length != 2) {
            System.out.println("Please ensure that your input is in the form of: " +action +" {task number}. For eg. " + String.format("'%s 1'", action));
            return true;
        }
        try {
            int index = Integer.parseInt(input_parts[1].trim()) - 1;
            if (index < 0) {
                System.out.println("Invalid task number. Task numbers should all be positive.");
            } else if (index >= tasks.size()) {
                System.out.println("Invalid task number. There are only " + tasks.size() + " tasks in the list.");
            } else {
                if (action.equals("mark")) {
                    Task t = tasks.get(index);
                    t.changeStatus(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("    " + t);
                } else if (action.equals("unmark")) {
                    Task t = tasks.get(index);
                    t.changeStatus(false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("    " + t);
                } else {
                    Task t = tasks.get(index);
                    tasks.remove(index);
                    Task.deleteTask();
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("    " + t);
                    System.out.println("Now you have " + Task.getTotalTasks() + " tasks in the list.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid integer index. For eg. " + String.format("'%s 1'", action));
        }

        return true;
    }

    public static Task makeTask(String input) throws LictException {
        String[] input_parts = input.split(" ", 2);
        String type = input_parts[0].trim();
        String description;
        if (input_parts.length == 1) {
            description = "";
        } else {
            description = input_parts[1].trim();
        }
        if (!eventTypes.contains(type)) {
            throw new LictException("OOPS!!! I'm sorry, but I don't know what that means. Please only input tasks which start with these words: " + eventTypes);
        }
        if (type.equals("todo")) {
            if (description.isEmpty()) {
                throw new LictException("OOPS!!! The description of a todo cannot be empty. Please ensure that your input is in the format: todo {description}");
            }
            return new Todo(description);
        } else if (type.equals("deadline")) {
            if (description.isEmpty()) {
                throw new LictException("OOPS!!! The description of a deadline cannot be empty. Please ensure that your input is in the format: deadline {description} /by {your deadline}");
            }
            String[] messages = description.split("/by", 2);
            if (messages.length != 2) {
                throw new LictException("OOPS!!! The deadline needs to be indicated. Please ensure that your input is in the format: deadline {description} /by {your deadline}");
            }
            return new Deadline(messages[0].trim(), messages[1].trim());
        } else {
            if (description.isEmpty()) {
                throw new LictException("OOPS!!! The description of an event cannot be empty. Please ensure that your input is in the format: event {description} /from {start} /to {end}");
            }
            String[] messages = description.split("/from", 2);
            if (messages.length != 2) {
                throw new LictException("OOPS!!! The start of the event needs to be indicated. Please ensure that your input is in the format: event {description} /from {start} /to {end}");
            }
            String eventDescription = messages[0].trim();
            String start = messages[1].trim();
            String[] tmp = start.split("/to", 2);
            if (tmp.length != 2) {
                throw new LictException("OOPS!!! The end of the event needs to be indicated. Please ensure that your input is in the format: event {description} /from {start} /to {end}");
            }
            start = tmp[0].trim();
            return new Event(eventDescription, start, tmp[1].trim());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(horizontal_line);
        System.out.println("Hello! I'm "+ name);
        System.out.println("What can I do for you?");
        System.out.println(horizontal_line);

        String input = sc.nextLine().trim();
        while (!input.equals("bye")) {
            System.out.println(horizontal_line);
            if (input.trim().isEmpty()) {
                System.out.println("Please enter a valid command.");
                input = sc.nextLine();
                continue;
            }
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                int counter = 1;
                for (Task task : tasks) {
                    System.out.println(counter + "." + task);
                    counter+=1;
                }
                System.out.println(horizontal_line);
                input = sc.nextLine();
                continue;
            }
            boolean evaluated = handle_markings(input);

            if (!evaluated) {
                try {
                    Task newTask = makeTask(input);
                    tasks.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("    " + newTask);
                    System.out.println("Now you have " + Task.getTotalTasks() + " in the list.");
                } catch (LictException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(horizontal_line);
            input = sc.nextLine();
        }
        sc.close();
        System.out.println(horizontal_line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontal_line);
    }
}
