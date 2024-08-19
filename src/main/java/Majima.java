import java.util.Scanner;

public class Majima {
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int task_count = 0;
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
                } else {
                    throw new MajimaException("Uhh, Kiryu-chan? There ain't no sense in whatever ya just said! Regular tasks start with 'todo', tasks with deadlines start with 'deadline' and gotta have a /by date and time, while 'events' need a /from and /to argument.");
                }
            } catch (MajimaException e) {
                System.out.println(LINEGAP);
                System.out.println(e.getMessage());
                System.out.println(LINEGAP);
            } catch (NumberFormatException e) {
                System.out.println(LINEGAP);
                System.out.println("OOPS!!! The number format seems to be incorrect.");
                System.out.println(LINEGAP);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(LINEGAP);
                System.out.println("OOPS!!! The task number seems to be out of bounds.");
                System.out.println(LINEGAP);
            }
        }
        scanner.close();
    }

    private static void exitProgram() {
        System.out.println(LINEGAP);
        System.out.println("Bye bye! Don't keep me waiting fer too long now, ya hear?");
        System.out.println(LINEGAP);
    }

    private static void listTasks() {
        System.out.println(LINEGAP);
        System.out.println("Here's whatcha gotta do, Kiryu-chan!");
        for (int i = 0; i < task_count; i++) {
            System.out.println((i + 1) + ". " + tasks[i].toString());
        }
        System.out.println(LINEGAP);
    }

    private static void markTask(String input) throws MajimaException {
        int numberArgument = Integer.parseInt(input.substring(5).trim()) - 1;
        if (numberArgument >= 0 && numberArgument < task_count) {
            tasks[numberArgument].markAsDone();
            System.out.println(LINEGAP);
            System.out.println("Okay, I've gone ahead and marked that one fer ya.");
            System.out.println("  " + tasks[numberArgument].toString());
            System.out.println(LINEGAP);
        } else {
            throw new MajimaException("Eh? Kiryu-chan, yain't making any sense! Tell me the number of the task ya want changed!");
        }
    }

    private static void unmarkTask(String input) throws MajimaException {
        int numberArgument = Integer.parseInt(input.substring(7).trim()) - 1;
        if (numberArgument >= 0 && numberArgument < task_count) {
            tasks[numberArgument].markAsUndone();
            System.out.println(LINEGAP);
            System.out.println("Okay, I've gone ahead and unmarked that one fer ya.");
            System.out.println("  " + tasks[numberArgument].toString());
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
            tasks[task_count] = new Deadline(description, by);
            task_count++;
            System.out.println(LINEGAP);
            System.out.println("Understood, Kiryu-chan! Adding that task to the list.");
            System.out.println(" " + tasks[task_count - 1].toString());
            System.out.println("Now you've got " + task_count + " tasks need doin'.");
            System.out.println(LINEGAP);
        }
    }

    private static void addTodo(String input) throws MajimaException {
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new MajimaException("Kiryu-chan, you gotta describe what the task is!");
        }
        if (canAddTask()) {
            tasks[task_count] = new Todo(description);
            task_count++;
            System.out.println(LINEGAP);
            System.out.println("Understood, Kiryu-chan! Adding that task to the list.");
            System.out.println(" " + tasks[task_count - 1].toString());
            System.out.println("Now you've got " + task_count + " tasks need doin'.");
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
            tasks[task_count] = new Event(description, from, to);
            task_count++;
            System.out.println(LINEGAP);
            System.out.println("Understood, Kiryu-chan! Adding that task to the list.");
            System.out.println(" " + tasks[task_count - 1].toString());
            System.out.println("Now you've got " + task_count + " tasks need doin'.");
            System.out.println(LINEGAP);
        }
    }

    private static void printInvalidTaskMessage() {
        System.out.println(LINEGAP);
        System.out.println("Eh? Kiryu-chan, yain't making any sense!");
        System.out.println("Tell me the number of the task ya want changed!");
        System.out.println(LINEGAP);
    }

    private static void printMissingArgumentMessage(String argument) {
        System.out.println(LINEGAP);
        System.out.println("Eh? Kiryu-chan, y'aint got no '" + argument + "' argument!");
        System.out.println(LINEGAP);
    }

    private static void handleInvalidCommand() {
        System.out.println(LINEGAP);
        System.out.println("Uhh, Kiryu-chan? There ain't no sense in whatever ya just said!");
        System.out.println("Regular tasks start with 'todo', tasks with deadlines start with");
        System.out.println("'deadline' and gotta have a /by date and time, while 'events' need");
        System.out.println("a /from and /to argument.");
        System.out.println(LINEGAP);
    }

    private static boolean canAddTask() {
        if (task_count >= MAX_TASKS) {
            System.out.println(LINEGAP);
            System.out.println("O-oi, Kiryu-chan! Ya can't expect me to 'member all this crap!");
            System.out.println("This ol' noggin of mine can only fit a hundred tasks at best!");
            System.out.println(LINEGAP);
            return false;
        }
        return true;
    }
}
