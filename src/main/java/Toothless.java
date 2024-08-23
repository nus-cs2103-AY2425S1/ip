import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Toothless represents a simple chat application.
 */
public class Toothless {

    private String toothlessLogo =
            " _____            _   _     _           \n" +
            "|_   _|___   ___ | |_| |__ | | ___  ___ ___ \n" +
            "  | |/ _ \\ / _ \\| __| '_ \\| |/ _ \\/ __/ __|\n" +
            "  | | (_) | (_) | |_| | | | |  __/\\__ \\__ \\\n" +
            "  |_|\\___/ \\___/ \\__|_| |_|_|\\___||___/___/\n";
    private String divider = "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n";
    private Scanner sc = new Scanner(System.in);
    private ArrayList<String> list;

    /**
     * Constructor for Toothless.
     * Initializes the task list.
     */
    private Toothless() {
        list = new ArrayList<>();
    }

    /**
     * Prints a goodbye message.
     */
    public void printGoodbyeMessage() {
        System.out.println("Toothless: \n" +
                "Until next time, dragon rider! \n" +
                "Toothless the Night Fury, signing off. \n" +
                "See you soon! \n\n" + divider);
    }

    /**
     * Adds a task to the task list.
     * @param input The task to be added.
     */
    public void addTask(String input) {
        list.add(input);
        System.out.println("Toothless: \n Your task [" + input + "] added to the quest board!\n\n" + divider);
    }

    /**
     * Prints the tasks on the task list.
     */
    public void printTask() {
        System.out.println("Toothless: \nHere are the tasks on the quest board:\n\n" +
                "|-------------Quest Board -----------------|\n");

        for(int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }

        System.out.println("\n|-----------------------------------------|\n\n" + divider);
    }
    /**
     * Begins the chat application.
     * The user can add tasks to the task list.
     */
    public void beginChat() {
        System.out.println("Welcome to the dragon's den!\n" + toothlessLogo);
        System.out.println("Toothless: \n" +
                "Greetings, Dragon Rider! \n\n" +
                "I'm Toothless, your friendly dragon companion. \n" +
                "What adventure shall we embark on today? \n\n" + divider);

        while(true) {
            System.out.println("You : ");
            String userInput = sc.nextLine();
            System.out.println("\n" + divider);
            if (userInput.equals("bye")) {
                printGoodbyeMessage();
                break;
            } else if (userInput.equals("list")) {
                printTask();
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