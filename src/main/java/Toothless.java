import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Toothless represents a simple chat application.
 */
public class Toothless {

    private String toothlessLogo =
            " _____            _   _     _\n" +
            "|_   _|___   ___ | |_| |__ | | ___  ___ ___\n" +
            "  | |/ _ \\ / _ \\| __| '_ \\| |/ _ \\/ __/ __|\n" +
            "  | | (_) | (_) | |_| | | | |  __/\\__ \\__ \\\n" +
            "  |_|\\___/ \\___/ \\__|_| |_|_|\\___||___/___/\n";
    private String divider = "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n";
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Task> list;

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
                "See you soon!\n\n" + divider);
    }

    /**
     * Splits the task into two parts.
     * @param task The task to be split.
     * @return An array of strings containing the split task.
     */
    public String[] splitFirst(String task) {
        String[] result = task.split(" ", 2);
        return result;
    }

    /**
     * Prints the tasks on the task list.
     */
    public void printTask() {
        System.out.println("Toothless:\nHere are the tasks on the quest board:\n\n" +
                "|-------------Quest Board -----------------|\n");

        for(int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i).toString());
        }

        System.out.println("\n|-----------------------------------------|\n\n" + divider);
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
              currentTask.toString() + "\n\n" + divider);
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
                currentTask.toString() + "\n\n" + divider);
    }
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
                        "Please enter a valid command. :)\n\n" + divider);
        }

        System.out.println("Toothless:\nYour task [" + description + "] added to the quest board!\n" +
                "Now there is " + list.size() + " quests in your quest board.\n\n" + divider);

    }
    /**
     * Executes the commands given by the user.
     * @param input The command given by the user.
     * @throws ToothlessExceptions If the command is invalid.
     */
    public void commands(String input) throws ToothlessExceptions {
        String[] splitInput = input.split(" ", 2);
        String command = splitInput[0];
        String description = splitInput.length < 2? "" : splitInput[1];

        switch(command) {
            case "todo" :
            case "deadline" :
            case "event":
                addTask(command, description);
                break;
            case "list":
                printTask();
                break;
            case "mark":
                if(description.isEmpty()) {
                    throw new MissingIndex("mark", "mark <index>");
                }
                int markIndex = Integer.parseInt(description);
                if(markIndex > list.size() || markIndex < 1) {
                    throw new ToothlessExceptions("The index is out of range! Please enter a valid index.\n\n" +
                            divider);
                }
                markDone(markIndex);
                break;
            case "unmark":
                if(description.isEmpty()) {
                    throw new MissingIndex("unmark", "unmark <index>");
                }
                int unmarkIndex = Integer.parseInt(description);
                if(unmarkIndex > list.size() || unmarkIndex < 1) {
                    throw new ToothlessExceptions("The index is out of range! Please enter a valid index.\n\n" +
                            divider);
                }
                markUndone(unmarkIndex);
                break;
            default:
                throw new ToothlessExceptions("I don't understand that command.\n" +
                        "Please enter a valid command. :)\n\n" + divider);
        }
    }

    /**
     * Begins the chat application.
     * The user can add tasks to the task list.
     */
    public void beginChat() {
        System.out.println("Welcome to the dragon's den!\n" + toothlessLogo);
        System.out.println("Toothless:\n" +
                "Greetings, Dragon Rider!\n\n" +
                "I'm Toothless, your friendly dragon companion.\n" +
                "What adventure shall we embark on today?\n\n" + divider);

        while(true) {
            System.out.println("You :");
            String userInput = sc.nextLine();
            System.out.println("\n" + divider);

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