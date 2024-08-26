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
        if (scanner.hasNextLine()) {
            String command = scanner.next();
            Command cmd = Command.valueOf(command.toUpperCase());
            String input = scanner.nextLine();
            switch (cmd) {
                case BYE:
                    System.out.println("Byeeee~ nice chatting with you! See you next time, Dawn 🌙 out");
                    break;
                case LIST:
                    System.out.println("listing all tasks...");
                    for (int i = 0; i < counter; i++) {
                        System.out.printf("%d. %s  \n", i + 1, tasks.get(i).getDesc());
                    }
                    respond();
                    break;
                case MARK: case UNMARK:
                    mark(command, input);
                    respond();
                    break;
                case DELETE:
                    delete(input);
                    respond();
                    break;
                case TODO: case DEADLINE: case EVENT:
                    addTask(command, input);
                    respond();
                    break;
                default:
                    throw new DawnException("Am I supposed to know what that means? Try something else\n");
            }
        }
    }

    private static void mark(String cmd, String index) { // mark the tasks accordingly
        int ind = 0;
        try {
            ind = Integer.parseInt(index);
        } catch (NumberFormatException e){
            System.out.println("Please specify the index of the task to be marked! ");
        }

        if (cmd.equals("unmark")) {
            tasks.get(ind).markAsNotDone();
        } else {
            tasks.get(ind).markAsDone();
        }
        System.out.println(tasks.get(ind).getDesc());
    }

    private static void addTask(String cmd, String input) throws DawnException{
        Task t;
        String[] s = input.split("/");
        int indexOfSpace = s[0].indexOf(" ");
        if (s[0].split(" ").length <= 1) { // splitting the front command part
            if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {
                throw new DawnException("You might be missing the task description, please check again\n");
            } else {
                throw new DawnException("Am I supposed to know what that means? Try something else\n");
            }
        }
        s[0] = s[0].substring(indexOfSpace + 1); // remove the command word e.g. todo
        if (cmd.equals("todo")) {
            t = new ToDo(s[0]);
        } else if (cmd.equals("deadline")) {
            if (s.length < 2) {
                throw new DawnException("Make sure you include both the task description and the deadline!\n");
            }
            t = new Deadline(s[0], s[1]);
        } else {
            if (s.length < 3) {
                throw new DawnException("Make sure you include the task description, start, and end times for " +
                        "your event!\n");
            }
            t = new Event(s[0], s[1], s[2]);
        }
        tasks.add(t);
        counter++;
        System.out.println("  Gotcha! I've added this task: \n" + t.getDesc());
        System.out.printf("  Now you have %d task(s) in the list \n", counter);
    }

    private static void delete(String input) throws DawnException{
        String[] s = input.split(" ");
        if (s.length < 2) {
            throw new DawnException("Remember to specify the index of the task to delete!\n");
        } else {
            int ind = Integer.parseInt(s[1]) - 1;
            Task temp = tasks.get(ind);
            System.out.println("  OK! I have removed this task for you: \n" + temp.getDesc());
            tasks.remove(ind);
            counter--;
            System.out.printf("  Now you have %d task(s) in the list \n", counter);
        }
    }
}
