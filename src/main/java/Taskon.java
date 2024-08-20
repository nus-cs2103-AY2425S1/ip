import java.util.ArrayList;
import java.util.Scanner;

public class Taskon {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        greet();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String description = scanner.nextLine();
            if (description.equals("bye")) {
                exit();
                break;
            } else if (description.equals("list")) {
                listItems(tasks);
                System.out.println("\n");
            } else if (description.startsWith("unmark")) {
                try {
                    int index = description.charAt(7) - '0';
                    tasks.get(index - 1).markAsUndone();
                    uncompleted(tasks.get(index - 1));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("You only have " + tasks.size() + " tasks!\n");
                }
            } else if (description.startsWith("mark")) {
                try {
                    int index = description.charAt(5) - '0';
                    tasks.get(index - 1).markAsDone();
                    completed(tasks.get(index - 1));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("You only have " + tasks.size() + " tasks!\n");
                }
            } else {
                try {
                    Task t = getTask(description);
                    tasks.add(t);
                    System.out.println("Got it! I've added this task:\n    " + t + "\nNow you have " +
                            tasks.size() + " tasks in your list.\n");
                } catch (TaskonException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private static Task getTask(String description) throws TaskonException {
        Task t;
        if (description.startsWith("todo ")) {
            if (description.length() < 6) {
                throw new TaskonException("It looks like you forgot to add a description for your todo. Please try again.\n");
            }
            t = new Todo(description.substring(5));
        } else if (description.startsWith("deadline ")) {
            if (!description.contains("/by")) {
                throw new TaskonException("It looks like you forgot to add a due date. Please try again.\n");
            }
            String by = description.substring(description.indexOf("/by") + 4);
            String substring = description.substring(9, description.indexOf("/by"));
            t = new Deadline(substring, by);
        } else if (description.startsWith("event ")) {
            if (!description.contains("/from") || !description.contains("/to")) {
                throw new TaskonException("It looks like you forgot to add a duration for your event. Please try again.\n");
            }
            if (description.indexOf("/from")-1 < 6) {
                throw new TaskonException("It looks like you forgot to add a description for your event. Please try again.\n");
            }
            int start = description.indexOf("/from") + 6;
            int end = description.indexOf("/to") + 4;
            String from = description.substring(start, description.indexOf("/to")-1);
            String to = description.substring(end);
            String substring = description.substring(6, description.indexOf("/from")-1);
            t = new Event(substring, from, to);
        } else {
            throw new TaskonException("Oh no! I don't understand the command: " + description + "\nPlease try again.\n");
        }
        return t;
    }

    public static void greet() {
        String greeting = "Hello! I'm Taskon\nWhat can I do for you?\n";
        System.out.println(greeting);
    }

    public static void exit() {
        String exiting = "Bye. Hope to see you again soon!\n";
        System.out.println(exiting);
    }

    public static void completed(Task task) {
        String complete = "Woohoo! Task complete! \nI've marked this as done:\n";
        System.out.println(complete + task.toString() + "\n");
    }

    public static void uncompleted(Task task) {
        String uncompleted = "Got it! No rush, I've marked it as not done yet:\n";
        System.out.println(uncompleted + task.toString() + "\n");
    }

    public static void listItems(ArrayList<Task> tasks) {
        System.out.println("Here's what we've got on your to-do list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println(i+1 + "." + t.toString());
        }
    }
}
