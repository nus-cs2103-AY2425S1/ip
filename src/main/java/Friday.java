import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Friday {
    private final ArrayList<Task> items = new ArrayList<>();
    private int index;
    private void horizontalLine() {
        System.out.println("\t____________________________________________________________");
    }
    private void welcomeMessage() { // Level 0
        horizontalLine();
        System.out.println("\tWelcome Back! I'm Friday.");
        System.out.println("\tWhat can I do for you today?");
        System.out.println("\tTo view the list of commands that I support, type 'help' for more information.");
        horizontalLine();
    }
    private void help() {
        System.out.println("\tHere are the list of commands that I support");
        System.out.println("\thelp - List of commands supported by me, Friday.");
        System.out.println("\tlist - View all entries to the current list of things you have asked me to take note of.");
        System.out.println("\tmark <index> - Mark an entry in the list as a completed task.");
        System.out.println("\tunmark <index> - Unmark an entry in the list as a completed task.");
        System.out.println("\ttodo <description> - Remember a TODO Task for you to revisit again.");
        System.out.println("\tdeadline <description> /by <when> - Remember a Deadline Task for you to complete by the deadline.");
        System.out.println("\tevent <description> /from <when> /to <when> - Remember an Event Task from when it begins to when it ends.");
        System.out.println("\tdelete <index> - Delete an entry from your current list.");
        System.out.println("\tbye - Exits this app and says Good Bye to Friday :)");
    }
    private void readInput() {
        Scanner sc = new Scanner(System.in);
        String input;
        String[] inputs;
        Task currTask;
        while (true) {
            input = sc.nextLine();
            horizontalLine();
            if (Objects.equals(input, "bye")) break; // Level 1
            if (Objects.equals(input, "help")) {
                help();
                horizontalLine();
            }
            else if (Objects.equals(input, "list")) { // Level 2
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < items.size(); i++) {
                    System.out.println("\t" + (i + 1) + "." + items.get(i).toString());
                }
                horizontalLine();
            } else {
                inputs = input.split(" ");
                try {
                    if (inputs.length == 1)
                        throw new FridayException("Invalid input. Please ensure that this command is supported by me and you have utilized the right syntax.\n\tCheck 'help' for more information."); // Commands that are not recognized or incorrect usage of commands
                    if (Objects.equals(inputs[0], "mark") || Objects.equals(inputs[0], "unmark")) { // Level 3
                        markUnmark(inputs);
                        horizontalLine();
                        continue;
                    } else if (Objects.equals(inputs[0], "todo")) { // Level 4, Improvement from Level 2
                        currTask = new Todo(input.substring(5));
                    } else if (Objects.equals(inputs[0], "deadline")) { // Level 4
                        inputs = input.substring(9).split(" /by ");
                        if (inputs.length == 1) throw new FridayException("Invalid input. usage: deadline <description> /by <when>.");
                        currTask = new Deadline(inputs[0], inputs[1]);
                    } else if (Objects.equals(inputs[0], "event")) { // Level 4
                        inputs = input.substring(6).split(" /from | /to ");
                        if (inputs.length <= 2) throw new FridayException("Invalid input. usage: event <description> /from <when> /to <when>.");
                        currTask = new Event(inputs[0], inputs[1], inputs[2]);
                    } else if (Objects.equals(inputs[0], "delete")) { // Level 6
                        if (!inputs[1].chars().allMatch(Character::isDigit)) throw new FridayException("Invalid input. usage: delete <index>\n\tWhat would you like to delete?"); // Deleting a non-digit entry
                        int target = Integer.parseInt(inputs[1]);
                        if (target > items.size() || target <= 0) throw new FridayException("Invalid input. It appears you are attempting to access something that does not exist yet."); // OutOfBoundsException
                        currTask = items.get(Integer.parseInt(inputs[1]) - 1);
                        items.remove(currTask);
                        System.out.println("\tNoted. I've removed this task:");
                        System.out.println("\t  " + currTask);
                        System.out.println("\tNow you have " + items.size() + " tasks in the list.");
                        horizontalLine();
                        continue;
                    } else {
                        throw new FridayException("Invalid input. Please ensure that this command is supported by me and you have utilized the right syntax.\n\tCheck 'help' for more information."); // Commands that are not recognized or incorrect usage of commands
                    }
                    items.add(currTask);
                    System.out.println("\tGot it. I've added this task:");
                    System.out.println("\t  " + currTask);
                    System.out.println("\tNow you have " + items.size() + " tasks in the list.");
                    horizontalLine();
                } catch (FridayException e) {
                    System.out.println("\t" + e.getMessage());
                    horizontalLine();
                }
            }
        }
    }
    private void markUnmark(String[] inputs) throws FridayException {
        if (!inputs[1].chars().allMatch(Character::isDigit)) throw new FridayException("Invalid input. Where would you like to " + inputs[0] + "?"); // Marking a non-digit
        int target = Integer.parseInt(inputs[1]);
        if (target > items.size() || target <= 0) throw new FridayException("Invalid input. It appears you are attempting to access something that does not exist yet."); // OutOfBoundsException
        if (Objects.equals(inputs[0], "mark")) {
            items.get(target - 1).markAsDone();
        } else {
            items.get(target - 1).unmarkAsDone();
        }
    }

    private void exit() {
        System.out.println("\tGood Bye. Hope to see you again soon!");
        horizontalLine();
    }
    private void initialize() {
        welcomeMessage();
        readInput();
        exit();
    }
    public static void main(String[] args) {
        Friday bot = new Friday();
        bot.initialize();
    }
}
