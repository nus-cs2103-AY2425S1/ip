import java.util.ArrayList;
import java.util.Scanner;

public class Dawn {
    private static Scanner scanner = new Scanner(System.in);
    enum Command {
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        BYE,
        LIST
    }
    public static void main(String[] args) {
        String divider = "--".repeat(30);

        System.out.println(divider);
        System.out.println("Dawn 🌙 speaking, what can I do for you?");
        try {
            respond();
        } catch (DawnException ex) {
            System.err.print(ex);
        } finally {
            scanner.close();
        }
        System.out.println(divider);
    }
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int counter = 0;

    private static void respond() throws DawnException { //provide responses to the user input
        while (scanner.hasNextLine()) {
            String command = scanner.next();
            Command cmd;
            try {
                cmd = Command.valueOf(command.toUpperCase()); // convert the command input to a corresponding enum constant
            } catch (IllegalArgumentException e) {
                throw new DawnException("Am I supposed to know what that means? Try something else\n");
            }

            String input = scanner.nextLine().trim();
            switch (cmd) {
                case BYE:
                    System.out.println("Byeeee~ nice chatting with you! See you next time, Dawn 🌙 out");
                    return;
                case LIST:
                    System.out.println("listing all tasks...");
                    if (counter <= 1) {
                        System.out.println("There are no tasks in the list... \nPlease feed me some tasks!");
                    } else {
                        for (int i = 0; i < counter; i++) {
                            System.out.printf("%d. %s  \n", i + 1, tasks.get(i).getDesc());
                        }
                    }
                    break;
                case MARK: case UNMARK:
                    mark(command, input);
                    break;
                case DELETE:
                    delete(input);
                    break;
                case TODO: case DEADLINE: case EVENT:
                    addTask(cmd, input);
                    break;
            }
        }
    }

    private static void mark(String cmd, String index) throws DawnException{ // mark the tasks accordingly
        int ind = 0;
        try {
            ind = Integer.parseInt(index);
        } catch (NumberFormatException e){
            throw new DawnException("Please specify the index of the task to be marked!\n");
        }

        if (cmd.equals("unmark")) {
            tasks.get(ind - 1).markAsNotDone(); // account for index starting at 0
        } else {
            tasks.get(ind - 1).markAsDone();
        }
        System.out.println(ind + ". " + tasks.get(ind - 1).getDesc());
    }

    private static void addTask(Command cmd, String input) throws DawnException{
        if (input.isBlank()) {
            throw new DawnException("You might be missing the task description, please check again\n");
        }

        Task t = null;
        String[] s = input.split("/");

        switch (cmd) {
            case TODO:
                t = new ToDo(s[0]);
                break;
            case DEADLINE:
                if (s.length < 2) {
                    throw new DawnException("Make sure you include both the task description and the deadline!\n");
                }
                t = new Deadline(s[0], s[1]);
                break;
            case EVENT:
                if (s.length < 3) {
                    throw new DawnException("Make sure you include the task description, start, and end times for " +
                            "your event!\n");
                }
                t = new Event(s[0], s[1], s[2]);
                break;
        }

        tasks.add(t);
        counter++;
        System.out.println("  Gotcha! I've added this task: \n" + counter + "." + t.getDesc());
        System.out.printf("  Now you have %d task(s) in the list \n", counter);
    }

    private static void delete(String index) throws DawnException {
        int ind = 0;
        try {
            ind = Integer.parseInt(index);
        } catch (NumberFormatException e){
            throw new DawnException("Please specify the index of the task to be deleted!\n");
        }
        Task t = tasks.get(ind - 1); // to account for index starting at 0
        System.out.println("  OK! I have removed this task for you: \n" + ind + "." + t.getDesc());
        tasks.remove(ind - 1);
        counter--;
        System.out.printf("  Now you have %d task(s) in the list \n", counter);
    }
}
