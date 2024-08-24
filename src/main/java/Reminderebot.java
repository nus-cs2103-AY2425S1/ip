import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Reminderebot {
    // note: needs support for handling multi-line inputs
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private static final String topBuffer = "____________________________________________________________\n";
    private static final String bottomBuffer = "____________________________________________________________";
    static int index = 0;
    private static final String greetingText = topBuffer +
            " Hello! I'm [Reminderebot]\n" +
            " What can I do for you?\n" +
            topBuffer;

    private static final String goodbyeText =
            " Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        Reminderebot reminderebot = new Reminderebot();
        Scanner scan = new Scanner(System.in);
        Scanner scan2;
        reminderebot.greeting();

        while (true) {
            // Seperate the commands from the arguments
            String input = scan.nextLine();
            scan2 = new Scanner(input);
            String command = scan2.next().toUpperCase();
            String str;

            System.out.println("");
            // Check if the command is mark or unmark
            int idx; // used to mark task as done or undone
            // handle invalid input by getting user-input and validate against enum
            try{
                // executes command
                reminderebot.checkInput(input);
                System.out.println(topBuffer);
                switch (Commands.valueOf(command)) {
                    case BYE:
                        reminderebot.goodbye();
                        break;
                    case LIST:
                        reminderebot.printTasks();
                        break;
                    case MARK:
                        idx = scan2.nextInt();
                        reminderebot.markTask(idx);
                        System.out.println("Nice! I've marked this task as done:\n" +
                                reminderebot.tasks.get(idx - 1));
                        break;
                    case UNMARK:
                        idx = scan2.nextInt();
                        reminderebot.unmarkTask(idx);
                        System.out.println("OK, I've marked this task as not done yet:\n" +
                                reminderebot.tasks.get(idx - 1));
                        break;
                    case DELETE:
                        idx = scan2.nextInt();
                        Task task = reminderebot.deleteTask(idx);
                        System.out.println("OK, I've removed this task:\n" +
                                task.toString() +
                                "\nNow you have " + index + " tasks in the list.");
                        break;
                    case TODO:
                        str = scan2.nextLine();
                        ToDo todo = new ToDo(str);
                        reminderebot.addTask(todo);
                        System.out.println("Got it. I've added this task:\n" +
                                todo.toString() +
                                "\nNow you have " + index + " tasks in the list."
                        );
                        break;
                    case DEADLINE:
                        str = scan2.nextLine();
                        String[] dlInfo = str.split("/by ");
                        Deadline deadline = new Deadline(dlInfo[0], dlInfo[1]);
                        reminderebot.addTask(deadline);
                        System.out.println("Got it. I've added this task:\n" +
                                deadline.toString() +
                                "\nNow you have " + index + " tasks in the list."
                        );
                        break;
                    case EVENT:
                        str = scan2.nextLine();
                        String[] eventInfo = str.split("/from ");
                        String[] eventTiming = eventInfo[1].split("/to ");
                        Event event = new Event(eventInfo[0], eventTiming[0], eventTiming[1]);
                        reminderebot.addTask(event);
                        System.out.println("Got it. I've added this task:\n" +
                                event.toString() +
                                "\nNow you have " + index + " tasks in the list."
                        );
                        break;
                }
                if (Commands.valueOf(command).equals(Commands.BYE)) {
                    System.out.println(bottomBuffer);
                    break;
                }
                System.out.println(topBuffer);
            } catch (ReminderebotException e) {
                System.out.println(topBuffer);
                System.out.println(e);
                System.out.println(topBuffer);
            }
        }
    }

    /**
     * Prints greeting message.
     */
    private void greeting() {
        System.out.println(greetingText);
    }

    /**
     * Prints goodbye message.
     */
    private void goodbye() {
        System.out.println(goodbyeText);
    }

    /**
     * Prints all tasks as a tasklist.
     */
    private void printTasks() {
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list:\n");
        for (int i=0; i<index; i++) {
            output.append(i+1).append(".").append(tasks.get(i)).append("\n");
        }
        String taskList = output.toString();
        System.out.println(taskList);
    }

    /**
     * Add a to-do, deadline or event to the tasklist.
     * @param task
     */
    private void addTask(Task task) {
        tasks.add(task);
        index++;
    }

    /**
     * Remove a to-do, deadline or event from the tasklist.
     * @param idx
     * @return removed Task
     */
    private Task deleteTask(int idx) {
        index--;
        return tasks.remove(idx - 1);
    }

    /**
     * Marks task as done.
     * @param idx
     */
    private void markTask(int idx) {
        Task task = tasks.get(idx-1);
        task.markAsDone();
    }

    /**
     * Marks task as undone.
     * @param idx
     */
    private void unmarkTask(int idx) {
        Task task = tasks.get(idx-1);
        task.markAsUndone();
    }

    /**
     * Helper function to get all enum values as a HashSet of Strings
     * @return a hashset of enum values
     */
    public static HashSet<String> getEnums() {
        HashSet<String> values = new HashSet<String>();
        for (Commands c : Commands.values()) {
            values.add(c.name());
        }
        return values;
    }

    /**
     * Checks if the input is valid.
     * @param input
     * @throws ReminderebotException
     */
    private void checkInput(String input) throws ReminderebotException {
        Scanner scan2 = new Scanner(input.trim());
        String command = scan2.next().toUpperCase();
        if (getEnums().contains(command)) { // if the input contains a command
            switch (Commands.valueOf(command)) {
                case MARK: // if the mark command lacks required arguments
                    String[] markInfo = input.split(" ");
                    if (scan2.hasNextInt() && markInfo.length==2) { // mark has necessary requirements
                        int i = scan2.nextInt();
                        if (i > index || i < 1) { // however index is out of bounds
                            throw new ReminderebotException("Item selected to be marked is not in list.\n" +
                                    "Syntax: mark <int>");
                        }
                        // else input is valid
                    } else {
                        throw new ReminderebotException("Mark items by selecting their position.\n" +
                                "Syntax: mark <int>");
                    }
                    break;
                case UNMARK: // if the unmark command lacks required arguments
                    String[] unmarkInfo = input.split(" ");
                    if (scan2.hasNextInt() && unmarkInfo.length==2) { // mark has necessary requirements
                        int i = scan2.nextInt();
                        if (i > index || i < 1) { // however index is out of bounds
                            throw new ReminderebotException("Item selected to be marked is not in list.\n" +
                                    "Syntax: unmark <int>");
                        }
                        // else input is valid
                    } else {
                        throw new ReminderebotException("Unmark items by selecting their position.\n" +
                                "Syntax: unmark <int>");
                    }
                    break;
                case DELETE: // if the delete command lacks required arguments
                    String[] deleteInfo = input.split(" ");
                    if (scan2.hasNextInt() && deleteInfo.length==2) { // mark has necessary requirements
                        int i = scan2.nextInt();
                        if (i > index || i < 1) { // however index is out of bounds
                            throw new ReminderebotException("Item selected to be deleted is not in list.\n" +
                                    "Syntax: delete <int>");
                        }
                        // else input is valid
                    } else {
                        throw new ReminderebotException("Delete items by selecting their position.\n" +
                                "Syntax: delete <int>");
                    }
                    break;
                case TODO: // if the to-do command lacks required arguments
                    String[] todoInfo = input.split(" ");
                    if (todoInfo.length < 2) {
                        throw new ReminderebotException("The description of a todo cannot be empty.\n" +
                                "Syntax: todo <taskname>");
                    }
                    break;
                case DEADLINE: // if the deadline command lacks required arguments
                    String[] dlInfo = input.split(" ");
                    String[] dlBy = input.split("/by ");
                    if (dlInfo.length < 2 || dlBy.length < 2) {
                        throw new ReminderebotException("The description of a deadline cannot be empty.\n" +
                                "Syntax: deadline <taskname> /by <duedate>");
                    }
                    break;
                case EVENT: // if the event command lacks required arguments
                    String[] eventInfo = input.split(" ");
                    String[] eventFrom = input.split("/from ");
                    String[] eventTo = input.split("/to ");
                    if (eventInfo.length < 2 || eventFrom.length < 2 || eventTo.length < 2) {
                        throw new ReminderebotException("The description of a event cannot be empty.\n" +
                                "Syntax: event <name> /from <datetime> /to <datetime>");
                    }
                    break;
            }
        } else { // exception: command not found
            throw new ReminderebotException("I'm sorry, but I don't know what that means. :(\n" +
                    "Please enter a command below:\n" +
                    " bye\n list\n mark <int>\n unmark <int>\n todo <taskname>\n" +
                    " deadline <taskname> /by <duedate>\n event <name> /from <datetime> /to <datetime>"
            );
        }
        // if there are no exceptions, don't do anything
    }
}