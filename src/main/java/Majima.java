import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Majima {
    private static final int MAX_TASKS = 100;
    private static List<Task> tasks = new ArrayList<>();
    //Deprecated due to swap to ArrayList
    //private static int task_count = 0;
    private static final String LINEGAP = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(LINEGAP);
        System.out.println("KIIIIIRYU-CHAN! It's ya old pal, Majima!");
        System.out.println("What can I do fer ya?");
        System.out.println(LINEGAP);

        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println(LINEGAP);
                    System.out.println("Bye bye! Don't keep me waiting fer too long now, ya hear?");
                    System.out.println(LINEGAP);
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    listTasks();
                } else if (input.startsWith("mark")) {
                    markTask(input);
                } else if (input.startsWith("unmark")) {
                    unmarkTask(input);
                //THE SPACE AFTER deadline, todo and event are EXTREMELY IMPORTANT AND WILL CAUSE RUNTIME ERROR IF NOT PRESENT
                //DO NOT CHANGE OR WE WILL DIE
                } else if (input.startsWith("deadline ")) {
                    addDeadline(input);
                } else if (input.startsWith("todo ")) {
                    addTodo(input);
                } else if (input.startsWith("event ")) {
                    addEvent(input);
                } else if (input.startsWith("delete ")) {
                    deleteTask(input);
                } else {
                    throw new MajimaException("Uhh, Kiryu-chan? There ain't no sense in whatever ya just said! Regular tasks start with 'todo', tasks with deadlines start with 'deadline' and gotta have a /by date and time, while 'events' need a /from and /to argument.");
                }
            } catch (MajimaException e) {
                System.out.println(LINEGAP);
                System.out.println(e.getMessage());
                System.out.println(LINEGAP);
            } catch (NumberFormatException e) {
                System.out.println(LINEGAP);
                System.out.println("Kiryu-chan? That ain't no number ya gave me!");
                System.out.println(LINEGAP);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(LINEGAP);
                System.out.println("Kiryu-chan? That task number seems to be out of bounds.");
                System.out.println(LINEGAP);
            }
        }
        scanner.close();
    }

    private static void deleteTask(String input) {
        int numberArgument = Integer.parseInt(input.substring(7).trim()) - 1;
        if (numberArgument >= 0 && numberArgument < tasks.size()) {
            Task removedTask = tasks.remove(numberArgument);
            System.out.println(LINEGAP);
            System.out.println("Noted, Kiryu-chan. I'll axe this task fer ya:");
            System.out.println(" " + removedTask.toString());
            System.out.println("Now, you've " + tasks.size() + " tasks need doin'.");
            System.out.println(LINEGAP);
        } else {
            System.out.println(LINEGAP);
            System.out.println("Kiryu! That number ain't even there!");
            System.out.println(LINEGAP);
        }
    }

    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println(LINEGAP);
            System.out.println("おめでとう, Kiryu-chan! There ain't nothing to do left!");
            System.out.println(LINEGAP);
            return;
        }
        System.out.println(LINEGAP);
        System.out.println("Here's whatcha gotta do, Kiryu-chan!");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println(LINEGAP);
    }

    private static void markTask(String input) throws MajimaException {
        int numberArgument = Integer.parseInt(input.substring(5).trim()) - 1;
        if (numberArgument >= 0 && numberArgument < tasks.size()) {
            tasks.get(numberArgument).markAsDone();
            System.out.println(LINEGAP);
            System.out.println("Okay, I've gone ahead and marked that one fer ya.");
            System.out.println("  " + tasks.get(numberArgument).toString());
            System.out.println(LINEGAP);
        } else {
            throw new MajimaException("Eh? Kiryu-chan, yain't making any sense! Tell me the number of the task ya want changed!");
        }
    }

    private static void unmarkTask(String input) throws MajimaException {
        int numberArgument = Integer.parseInt(input.substring(7).trim()) - 1;
        if (numberArgument >= 0 && numberArgument < tasks.size()) {
            tasks.get(numberArgument).markAsUndone();
            System.out.println(LINEGAP);
            System.out.println("Okay, I've gone ahead and unmarked that one fer ya.");
            System.out.println("  " + tasks.get(numberArgument).toString());
            System.out.println(LINEGAP);
        } else {
            throw new MajimaException("Eh? Kiryu-chan, yain't making any sense! Tell me the number of the task ya want changed!");
        }
    }

    private static void addDeadline(String input) throws MajimaException {
        String[] parts = input.split("/by", 2);
        if (parts.length < 2) {
            throw new MajimaException("Eh? Kiryu-chan, yain't got no '/by' argument!");
        }
        String description = parts[0].substring(9).trim();
        if (description.isEmpty()) {
            throw new MajimaException("Kiryu-chan, you gotta describe what the task is!");
        }
        String by = parts[1].trim();
        if (canAddTask()) {
            tasks.add(new Deadline(description, by));
            System.out.println(LINEGAP);
            System.out.println("Understood, Kiryu-chan! Adding that task to the list.");
            System.out.println(" " + tasks.get(tasks.size() - 1).toString());
            System.out.println("Now you've got " + tasks.size() + " tasks need doin'.");
            System.out.println(LINEGAP);
        }
    }

    private static void addTodo(String input) throws MajimaException {
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new MajimaException("Kiryu-chan, you gotta describe what the task is!");
        }
        if (canAddTask()) {
            tasks.add(new Todo(description));
            System.out.println(LINEGAP);
            System.out.println("Understood, Kiryu-chan! Adding that task to the list.");
            System.out.println(" " + tasks.get(tasks.size() - 1).toString());
            System.out.println("Now you've got " + tasks.size() + " tasks need doin'.");
            System.out.println(LINEGAP);
        }
    }

    private static void addEvent(String input) throws MajimaException {
        String[] parts = input.split("/from", 2);
        if (parts.length < 2) {
            throw new MajimaException("Eh? Kiryu-chan, y'aint got no '/from' argument!");
        }
        String description = parts[0].substring(6).trim();
        if (description.isEmpty()) {
            throw new MajimaException("Kiryu-chan, you gotta describe what the task is!");
        }
        String[] dateParts = parts[1].split("/to", 2);
        if (dateParts.length < 2) {
            throw new MajimaException("Eh? Kiryu-chan, y'aint got no '/to' argument!");
        }
        String from = dateParts[0].trim();
        String to = dateParts[1].trim();
        if (canAddTask()) {
            tasks.add(new Event(description, from, to));
            System.out.println(LINEGAP);
            System.out.println("Understood, Kiryu-chan! Adding that task to the list.");
            System.out.println(" " + tasks.get(tasks.size() - 1).toString());
            System.out.println("Now you've got " + tasks.size() + " tasks need doin'.");
            System.out.println(LINEGAP);
        }
    }

    private static boolean canAddTask() {
        if (tasks.size() >= MAX_TASKS) {
            System.out.println(LINEGAP);
            System.out.println("O-oi, Kiryu-chan! Ya can't expect me to 'member all this crap!");
            System.out.println("This ol' noggin of mine can only fit a hundred tasks at best!");
            System.out.println(LINEGAP);
            return false;
        }
        return true;
    }
}
