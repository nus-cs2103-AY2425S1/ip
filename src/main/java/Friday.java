import java.util.Scanner;
import java.util.ArrayList;

public class Friday {

    private final ArrayList<Task> items = new ArrayList<>();

    private void horizontalLine() {
        System.out.println("\t____________________________________________________________");
    }

    private void welcomeMessage() {
        horizontalLine();
        System.out.println("\tWelcome Back! I'm Friday.");
        System.out.println("\tWhat can I do for you today?");
        System.out.println("\tTo view the list of commands that I support, type 'help' for more information.");
        horizontalLine();
    }

    private void help() {
        System.out.println("\tHere are the list of commands that I support");
        System.out.println("\thelp - List of commands supported by me, Friday.");
        System.out.println("\tlist - View all entries to the current list of things" +
                " you have asked me to take note of.");
        System.out.println("\tmark <integer> - Mark an entry in the list as a completed task.");
        System.out.println("\tunmark <integer> - Unmark an entry in the list as a completed task.");
        System.out.println("\ttodo <string> - Remember a TODO Task for you to revisit again.");
        System.out.println("\tdeadline <string> /by <when> - Remember a Deadline Task for" +
                " you to complete by the deadline.");
        System.out.println("\tevent <string> /from <string> /to <string> - Remember an Event Task" +
                " from when it begins to when it ends.");
        System.out.println("\tdelete <integer> - Delete an entry from your current list.");
        System.out.println("\tbye - Exits this app and says Good Bye to Friday :)");
    }

    private Command parseCommand(String input) {
        String[] inputs = input.split(" ");
        try {
            return Command.valueOf(inputs[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.INVALID;
        }
    }

    private void readInput() {
        Scanner sc = new Scanner(System.in);
        String input;
        Task currTask;
        while (true) {
            input = sc.nextLine();
            horizontalLine();

            String[] inputs = input.split(" ");
            Command command = parseCommand(input);

            try {
                switch (command) {
                    case BYE:
                        System.out.println("\tGood Bye. Hope to see you again soon!"); // Level 1
                        return;
                    case HELP:
                        if (inputs.length > 1) {
                            throw new FridayException("Invalid input. 'help' command does not take any arguments.");
                        }
                        help();
                        break;
                    case LIST:
                        if (inputs.length > 1) {
                            throw new FridayException("Invalid input. 'list' command does not take any arguments.");
                        }
                        System.out.println("\tHere are the tasks in your list:");
                        for (int i = 0; i < items.size(); i++) {
                            System.out.println("\t" + (i + 1) + "." + items.get(i).toString());
                        }
                        break;
                    case MARK:
                    case UNMARK:
                        if (inputs.length != 2) {
                            throw new FridayException("Invalid input. 'mark' and 'unmark' commands require exactly" +
                                    " one argument.\n\tusage: mark <integer> || unmark <integer>");
                        }
                        markUnmark(inputs);
                        break;
                    case TODO:
                        if (inputs.length < 2) {
                            throw new FridayException("Invalid input. 'todo' command requires a description." +
                                    "\n\tusage: todo <string>");
                        }
                        currTask = new Todo(input.substring(5));
                        addTask(currTask);
                        break;
                    case DEADLINE:
                        if (inputs.length == 1) {
                            throw new FridayException("Invalid input. 'deadline' command requires a" +
                                    " description and a deadline.\n\tusage: deadline <string> /by <string>");
                        }
                        String[] deadlineInputs = input.substring(9).split(" /by ");
                        if (deadlineInputs.length != 2) {
                            throw new FridayException("Invalid input. 'deadline' command requires a" +
                                    " description and deadline\n\tusage: deadline <string> /by <string>.");
                        }
                        currTask = new Deadline(deadlineInputs[0], deadlineInputs[1]);
                        addTask(currTask);
                        break;
                    case EVENT:
                        if (inputs.length == 1) {
                            throw new FridayException("Invalid input. 'event' command requires a description," +
                                    " start, and end time.\n\tusage: event <string> /from <string> /to <string>");
                        }
                        String[] eventInputs = input.substring(6).split(" /from | /to ");
                        if (eventInputs.length != 3) {
                            throw new FridayException("Invalid input. 'event' command requires a description," +
                                    " start, and end time.\n\tusage: event <string> /from <string> /to <string>.");
                        }
                        currTask = new Event(eventInputs[0], eventInputs[1], eventInputs[2]);
                        addTask(currTask);
                        break;
                    case DELETE:
                        if (inputs.length != 2) {
                            throw new FridayException("Invalid input. 'delete' command requires exactly one argument." +
                                    "\n\tusage: delete <integer>");
                        }
                        deleteTask(inputs);
                        break;
                    default:
                        throw new FridayException("Invalid input. Please ensure that this command is supported by me" +
                                " and you have utilized the right syntax.\n\tCheck 'help' for more information.");
                }
            } catch (FridayException e) {
                System.out.println("\t" + e.getMessage());
            } finally {
                horizontalLine();
            }
        }
    }

    private void addTask(Task task) {
        items.add(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + items.size() + " tasks in the list.");
    }

    private void deleteTask(String[] inputs) throws FridayException {
        if (!inputs[1].chars().allMatch(Character::isDigit))
            throw new FridayException("Invalid input. usage: delete <index>\n\tWhat would you like to delete?");
        int target = Integer.parseInt(inputs[1]);
        if (target > items.size() || target <= 0)
            throw new FridayException("Invalid input. It appears you are attempting to" +
                    " access something that does not exist yet.");
        Task task = items.get(target - 1);
        items.remove(task);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + items.size() + " tasks in the list.");
    }

    private void markUnmark(String[] inputs) throws FridayException {
        if (!inputs[1].chars().allMatch(Character::isDigit))
            throw new FridayException("Invalid input. Where would you like to " + inputs[0] + "?");
        int target = Integer.parseInt(inputs[1]);
        if (target > items.size() || target <= 0)
            throw new FridayException("Invalid input. It appears you are attempting to" +
                    " access something that does not exist yet.");
        if (inputs[0].equals("mark")) {
            items.get(target - 1).markAsDone();
        } else {
            items.get(target - 1).unmarkAsDone();
        }
    }

    private void initialize() {
        welcomeMessage();
        readInput();
    }

    public static void main(String[] args) {
        Friday bot = new Friday();
        bot.initialize();
    }
}
