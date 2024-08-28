import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Reminderebot {
    // note: needs support for handling multi-line inputs
    private static final String name = "Reminderebot";
    private static final String topBuffer = "____________________________________________________________\n";
    private static final String bottomBuffer = "____________________________________________________________";
    private Storage storage;
    private TaskList tasklist;


    private static final String greetingText = topBuffer +
            " Hello! I'm [" + name + "]\n" +
            " What can I do for you?\n" +
            topBuffer;

    private static final String goodbyeText =
            " Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        String filePath = "./data/Reminderebot.txt";
        Reminderebot reminderebot = new Reminderebot(filePath);
        reminderebot.greeting();
        reminderebot.chat();
    }

    public Reminderebot(String filePath) {
        this.storage =  new Storage(filePath);
        this.tasklist = new TaskList(new ArrayList<Task>());    // in case file not found
        try {
            this.tasklist = new TaskList(storage.readFileContents());
        } catch (ReminderebotException e) {
            System.out.println(e);
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
        this.storage.saveData(this.tasklist);
        System.out.println(goodbyeText);
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
                    if (i > tasklist.index || i < 1) { // however index is out of bounds
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
                    if (i > tasklist.index || i < 1) { // however index is out of bounds
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
                    if (i > tasklist.index || i < 1) { // however index is out of bounds
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

    public void chat() {
        Scanner scan = new Scanner(System.in);
        Scanner scan2;
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
                checkInput(input);
                System.out.println(topBuffer);
                switch (Commands.valueOf(command)) {
                    case BYE:
                        goodbye();
                        break;
                    case LIST:
                        tasklist.printTasks();
                        break;
                    case MARK:
                        idx = scan2.nextInt();
                        tasklist.markTask(idx);
                        System.out.println("Nice! I've marked this task as done:\n" +
                                tasklist.tasks.get(idx - 1));
                        break;
                    case UNMARK:
                        idx = scan2.nextInt();
                        tasklist.unmarkTask(idx);
                        System.out.println("OK, I've marked this task as not done yet:\n" +
                                tasklist.tasks.get(idx - 1));
                        break;
                    case DELETE:
                        idx = scan2.nextInt();
                        Task task = tasklist.deleteTask(idx);
                        System.out.println("OK, I've removed this task:\n" +
                                task.toString() +
                                "\nNow you have " + tasklist.index + " tasks in the list.");
                        break;
                    case TODO:
                        str = scan2.nextLine();
                        ToDo todo = new ToDo(str);
                        tasklist.addTask(todo);
                        System.out.println("Got it. I've added this task:\n" +
                                todo.toString() +
                                "\nNow you have " + tasklist.index + " tasks in the list."
                        );
                        break;
                    case DEADLINE:
                        str = scan2.nextLine();
                        String[] dlInfo = str.split("/by ");
                        Deadline deadline = new Deadline(dlInfo[0], dlInfo[1]);
                        tasklist.addTask(deadline);
                        System.out.println("Got it. I've added this task:\n" +
                                deadline.toString() +
                                "\nNow you have " + tasklist.index + " tasks in the list."
                        );
                        break;
                    case EVENT:
                        str = scan2.nextLine();
                        String[] eventInfo = str.split("/from ");
                        String[] eventTiming = eventInfo[1].split("/to ");
                        Event event = new Event(eventInfo[0], eventTiming[0], eventTiming[1]);
                        tasklist.addTask(event);
                        System.out.println("Got it. I've added this task:\n" +
                                event.toString() +
                                "\nNow you have " + tasklist.index + " tasks in the list."
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
}