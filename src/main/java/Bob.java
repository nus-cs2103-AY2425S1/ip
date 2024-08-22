import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bob {
    private static final String DIVIDER = "____________________________________________________________\n";
    private static final String GREETINGS = "Hello! I'm Bob\n"
            + "What can I do for you?\n";
    private static final String EXIT = "Bye. Hope to see you again soon!\n";
    public static void main(String[] args) {

        System.out.print(DIVIDER + GREETINGS + DIVIDER);

        chatBot();

        System.out.print(EXIT + DIVIDER);
    }

    public static void chatBot() {
        Scanner scanner = new Scanner(System.in);
        List<Task> myTasks = new ArrayList<>();
        while (true) {
            String input = scanner.next();
            System.out.print(DIVIDER);
            try {
                switch (input) {
                    case "bye":
                        return;
                    case "list":
                        listTasks(myTasks);
                        break;
                    case "mark":
                        int index = scanner.nextInt() - 1;
                        if (!(index < myTasks.size()) || index < 0) {
                            throw new InvalidTaskNumberException();
                        }
                        markTask(myTasks.get(index));
                        break;
                    case "unmark":
                        int ind = scanner.nextInt() - 1;
                        if (!(ind < myTasks.size()) || ind < 0) {
                            throw new InvalidTaskNumberException();
                        }
                        unmarkTask(myTasks.get(ind));
                        break;
                    case "todo":
                        myTasks.add(newToDo(scanner.nextLine().trim()));
                        System.out.printf(" Now you have %d tasks in the list.%n", myTasks.size());
                        break;
                    case "deadline":
                        myTasks.add(newDeadline(scanner.nextLine().trim()));
                        System.out.printf(" Now you have %d tasks in the list.%n", myTasks.size());
                        break;
                    case "event":
                        myTasks.add(newEvent(scanner.nextLine().trim()));
                        System.out.printf(" Now you have %d tasks in the list.%n", myTasks.size());
                        break;
                    case "delete":
                        int inde = scanner.nextInt() - 1;
                        if (!(inde < myTasks.size()) || inde < 0) {
                            throw new InvalidTaskNumberException();
                        }
                        Task delTask = myTasks.remove(inde);
                        System.out.println(" Noted. I've removed this task:\n " + delTask);
                        System.out.printf(" Now you have %d tasks in the list.%n", myTasks.size());
                        break;
                    default:
                        scanner.nextLine();
                        throw new InvalidInputException();
                }
            } catch (EmptyArgumentException | MissingArgumentException |
                     InvalidTaskNumberException | InvalidInputException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.print(DIVIDER);
            }
        }
    }

    public static Task printAddTask(Task task) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("\t" + task);
        return task;
    }
    public static Task newToDo(String input) throws EmptyArgumentException {
        if (input.isEmpty()) {
            throw new EmptyArgumentException("description", "todo");
        }
        return printAddTask(new ToDos(input));
    }

    public static Task newDeadline(String input) throws EmptyArgumentException, MissingArgumentException {

        if (!input.matches("^\\S{1}.+")) {
            throw new EmptyArgumentException("description", "deadline");
        }
        if (!input.matches("^.*/by.*$")) {
            throw new MissingArgumentException("by", "deadline");
        }
        if (!input.matches("^\\S{1}.+/by.*$")) {
            throw new EmptyArgumentException("description", "deadline");
        }
        if (!input.matches("^\\S{1}.+ /by \\S{1}.+$")) {
            throw new EmptyArgumentException("by", "deadline");
        }

        String[] inputs = input.split("/by", 2);
        return printAddTask(new Deadlines(inputs[0], inputs[1]));
    }

    public static Task newEvent(String input) throws EmptyArgumentException, MissingArgumentException {

        if (!input.matches("^\\S{1}.+")) {
            throw new EmptyArgumentException("description", "event");
        }
        if (!input.matches("^.*/from.*$")) {
            throw new MissingArgumentException("from", "event");
        }
        if (!input.matches("^\\S{1}.+/from.*$")) {
            throw new EmptyArgumentException("description", "event");
        }
        if (!input.matches("^.*/from.*/to.*$")) {
            throw new MissingArgumentException("to", "event");
        }
        if (!input.matches("^\\S{1}.+ /from \\S{1}.+/to.*$")) {
            throw new EmptyArgumentException("from", "event");
        }
        if (!input.matches("^\\S{1}.+ /from \\S{1}.+/to \\S{1}.+$")) {
            throw new EmptyArgumentException("to", "event");
        }

        String[] inputs = input.split("/from", 2);
        String[] dates = inputs[1].split("/to", 2);

        return printAddTask(new EventTask(inputs[0], dates[0], dates[1]));
    }

    public static void markTask(Task task) {
        task.mark();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(" " + task);
    }

    public static void unmarkTask(Task task) {
        task.unmark();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println(" " + task);
    }

    public static void listTasks(List<Task> myTasks) {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 1; i < myTasks.size() + 1; i++) {
            System.out.print(" " + i + "." + myTasks.get(i-1) + "\n");
        }
    }
}

