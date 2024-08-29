import java.util.Scanner;
import java.util.ArrayList;

/**
 * Toothless represents a simple chat application.
 */
public class Toothless {

    private final static String TOOTHLESS_LOGO =
            " _____            _   _     _\n" +
            "|_   _|___   ___ | |_| |__ | | ___  ___ ___\n" +
            "  | |/ _ \\ / _ \\| __| '_ \\| |/ _ \\/ __/ __|\n" +
            "  | | (_) | (_) | |_| | | | |  __/\\__ \\__ \\\n" +
            "  |_|\\___/ \\___/ \\__|_| |_|_|\\___||___/___/\n";
    private final static String DIVIDER = "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n";
    private final Scanner sc = new Scanner(System.in);
    private final ArrayList<Task> list;

    /**
     * Constructor for Toothless.
     * Initializes the task list.
     */
    private Toothless() {
        this.list = new ArrayList<>();
    }

    /**
     * Prints a goodbye message.
     */
    public void printGoodbyeMessage() {
        System.out.println("Toothless:\n" +
                "Until next time, dragon rider!\n" +
                "Toothless the Night Fury, signing off.\n" +
                "See you soon!\n\n" + DIVIDER);
    }

    /**
     * Prints the tasks on the task list.
     */
    public void printTask() {
        System.out.println("Toothless:\nHere are the tasks on the quest board:\n\n" +
                "|-------------Quest Board -----------------|\n");

        if (list.isEmpty()) {
            System.out.println("There are no quests on the quest board!");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i + 1 + ". " + list.get(i).toString());
            }
        }

        System.out.println("\n|------------------------------------------|\n\n" + DIVIDER);
    }

    /**
     * Marks a task as done.
     * @param index The index of the task to be marked as done.
     */
    public void markDone(int index) {
        int fixedIndex = index - 1;
        Task currentTask = list.get(fixedIndex);
        currentTask.markAsDone();
        System.out.println("Toothless:\nGood job! You had completed this quest!\n" +
              currentTask + "\n\n" + DIVIDER);
    }

    /**
     * Mark a task as undone.
     * @param index The index of the task to be marked as undone.
     */
    public void markUndone(int index) {
        int fixedIndex = index - 1;
        Task currentTask = list.get(fixedIndex);
        currentTask.markAsUndone();
        System.out.println("Toothless:\nOops! Quest is back in play!\n" +
                currentTask + "\n\n" + DIVIDER);
    }

    /**
     * Adds a task to the task list.
     * @param taskType
     * @param description
     * @throws ToothlessExceptions
     */
    public void addTask(String taskType, String description) throws ToothlessExceptions {
        switch(taskType) {
            case "todo":
                if(description.isEmpty()) {
                    throw new NoDescription("todo", "todo <description>");
                }
                list.add(new ToDos(description));
                break;
            case "deadline":
                if(description.isEmpty()) {
                    throw new NoDescription("deadline", "deadline <description> /by <timing>");
                } else if(!description.contains("/by")) {
                    throw new NoTimeline("deadline", "deadline <description> /by <timing>");
                }
                String[] splitDeadline = description.split("/by");
                list.add(new Deadline(splitDeadline[0], splitDeadline[1]));
                break;
            case "event":
                if(description.isEmpty()) {
                    throw new NoDescription("event", "event <description> /from <start time> /to <end time>");
                } else if(!description.contains("/from") || !description.contains("/to")) {
                    throw new NoTimeline("event", "event <description> /from <start time> /to <end time>");
                }
                break;
            default:
                throw new ToothlessExceptions("I don't understand that command.\n" +
                        "Please enter a valid command. :)\n\n" + DIVIDER);
        }

        System.out.println("Toothless:\nYour task\n\t\t" +
                list.getLast() +
                "\nadded to the quest board!\n\n" +
                "Now there is " + list.size() + " quests in your quest board.\n\n" + DIVIDER);

    }

    public void deleteTask(int index) {
        int fixedIndex = index - 1;
        Task currentTask = list.get(fixedIndex);
        int newTaskSize = list.size() - 1;
        System.out.println("Toothless:\nThe quest\n\t\t" +
                currentTask + "\nis removed from the quest board!\n\n" +
                "Now there is " + newTaskSize + " quests in your quest board.\n\n" + DIVIDER);
        list.remove(fixedIndex);
    }

    /**
     * Executes the commands given by the user.
     * @param input The command given by the user.
     * @throws ToothlessExceptions If the command is invalid.
     */
    public void commands(String input) throws ToothlessExceptions {
        String[] splitInput = input.split(" ", 2);
        CommandType command = CommandType.getCommandType(splitInput[0]);
        String description = splitInput.length < 2? "" : splitInput[1];

        switch(command) {
            case TODO :
            case DEADLINE :
            case EVENT :
                addTask(command.name().toLowerCase(), description);
                break;
            case LIST :
                printTask();
                break;
            case MARK :
                if(description.isEmpty()) {
                    throw new MissingIndex("mark", "mark <index>");
                }
                int markIndex = Integer.parseInt(description);
                if(markIndex > list.size() || markIndex < 1) {
                    throw new ToothlessExceptions("The index is out of range! Please enter a valid index.\n\n" +
                            DIVIDER);
                }
                markDone(markIndex);
                break;
            case UNMARK :
                if(description.isEmpty()) {
                    throw new MissingIndex("unmark", "unmark <index>");
                }
                int unmarkIndex = Integer.parseInt(description);
                if(unmarkIndex > list.size() || unmarkIndex < 1) {
                    throw new ToothlessExceptions("The index is out of range! Please enter a valid index.\n\n" +
                            DIVIDER);
                }
                markUndone(unmarkIndex);
                break;
            case DELETE :
                if(description.isEmpty()) {
                    throw new MissingIndex("delete", "delete <index>");
                }
                int deleteIndex = Integer.parseInt(description);
                if(deleteIndex > list.size() || deleteIndex < 1) {
                    throw new ToothlessExceptions("The index is out of range! Please enter a valid index.\n\n" +
                            DIVIDER);
                }
                deleteTask(deleteIndex);
                break;
            default:
                throw new ToothlessExceptions("I don't understand that command.\n" +
                        "Please enter a valid command. :)\n\n" + DIVIDER);
        }
    }

    /**
     * Begins the chat application.
     * The user can add tasks to the task list.
     */
    public void beginChat() {
        System.out.println("Welcome to the dragon's den!\n" + TOOTHLESS_LOGO);
        System.out.println("Toothless:\n" +
                "Greetings, Dragon Rider!\n\n" +
                "I'm Toothless, your friendly dragon companion.\n" +
                "What adventure shall we embark on today?\n\n" + DIVIDER);

        while(true) {
            System.out.println("You :");
            String userInput = sc.nextLine();
            System.out.println("\n" + DIVIDER);

            if (userInput.equals("bye")) {
                printGoodbyeMessage();
                break;
            }

            try {
                commands(userInput);
            } catch (ToothlessExceptions e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static void main(String[] args) {
        Toothless toothless = new Toothless();
        toothless.beginChat();
    }
}