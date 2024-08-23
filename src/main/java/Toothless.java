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

    public String[] splitFirst(String task) {
        String[] result = task.split(" ", 2);
        return result;
    }
    /**
     * Adds a task to the task list.
     * @param input The task to be added.
     */
    public void addTask(String input) {
        String splitDescription[] = splitFirst(input);
        String taskType = splitDescription[0];
        String description = splitDescription[1];

        if(taskType.equals("todo")) {
            list.add(new ToDos(description));
        } else if(taskType.equals("deadline")) {
            String[] splitDeadline = description.split("/by");
            list.add(new Deadline(splitDeadline[0], splitDeadline[1]));
        } else if(taskType.equals("event")) {
            String[] splitEvent = description.split("/from");
            String[] splitTiming = splitEvent[1].split("/to");
            list.add(new Events(splitEvent[0], splitTiming[0], splitTiming[1]));
        }

        System.out.println("Toothless:\nYour task [" + input + "] added to the quest board!\n" +
                "Now there is " + list.size() + " quests in your quest board.\n\n" + divider);
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
        System.out.println("Toothless:\nGood job! You had completed this quest!:\n" +
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
        System.out.println("Toothless:\nOops! Quest is back in play! :\n" +
                currentTask.toString() + "\n\n" + divider);
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
            } else if (userInput.equals("list")) {
                printTask();
            } else if (userInput.startsWith("mark")) {
                String[] splitNum = userInput.split(" ");
                int index = Integer.parseInt(splitNum[1]);
                markDone(index);
            } else if (userInput.startsWith("unmark")) {
                String[] splitNum = userInput.split(" ");
                int index = Integer.parseInt(splitNum[1]);
                markUndone(index);
            } else {
                addTask(userInput);
            }
        }

    }

    public static void main(String[] args) {
        Toothless toothless = new Toothless();
        toothless.beginChat();
    }
}