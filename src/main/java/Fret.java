import java.util.Scanner;
import java.util.Random;

public class Fret {
    private static final Random rng = new Random();

    private static final String[] addTaskPrefixes = new String[] {
        "\tYou got it! Adding task: ",
        "\tYou got it! Adding task: ",
        "\tYou got it! Adding task: ",
        "\tYou got it! Adding task: ",
        "\tYou sure wanna do that? ",
        "\tAlright! Adding task: ",
        "\tAlright! Adding task: ",
        "\tAlright! Adding task: ",
        "\tAlright! Adding task: ",
        "\tOn it! Task added: ",
        "\tOn it! Task added: ",
        "\tOn it! Task added: ",
        "\tWhatever you say! *nervous laughter*: ",
        "\tHmmmm..... Done. Task added: ",
        "\tHmmmm..... Done. Task added: ",
        "\tHmmmm..... Done. Task added: ",
    };

    /**
     * Randomly selects and returns a prefix for a command from a given list
     * 
     * @param prefixes
     * @return a randomly selected prefix from the given list of prefixes
     */
    private static String generateRandomPrefix(String[] prefixes) {
        return prefixes[rng.nextInt(prefixes.length)];
    }

    /**
     * Creates and returns an enumeration of the tasks added and stored by the user
     * 
     * @param tasks the list of tasks
     * @param numTasks the number of tasks
     * @return an enumeration of the tasks
     */
    private static String taskListToString(String[] tasks, int numTasks) {
        if (numTasks == 0) {
            return "\tempty";
        }

        String[] tempTasks = new String[numTasks];

        for (int i = 1; i <= numTasks; i++) {
            tempTasks[i - 1] = "\t" + i + ". " + tasks[i - 1];
        }

        return String.join("\n", tempTasks);
    }

    public static void main(String[] args) {
        String[] tasks = new String[100];
        int numTasks = 0;

        String logo = "________                ___ \n"
                + "| _____|             ___| |___ \n"
                + "| |___  __   _   ___ |__   __|\n"
                + "| ___|  | |/ /  / _ \\   | |\n"
                + "| |     |   /  <  __/   | |__\n"
                + "|_|     |__|    \\___|   |___|\n";
        
        System.out.println("Initiating...\n" + logo);
        
        System.out.println("\t-----------------------------------------");
        System.out.println("\tPersonal AI Fret, coming online!\n\tHey, how can I be of assistance?");
        System.out.println("\t-----------------------------------------");

        Scanner input = new Scanner(System.in);
        String userInput;

        do {
            userInput = input.nextLine();
            if (!userInput.equals("bye")) {
                if (userInput.equals("list")) {
                    System.out.println("\t-----------------------------------------");
                    System.out.println(taskListToString(tasks, numTasks));
                    System.out.println("\t-----------------------------------------");
                } else {
                    System.out.println("\t-----------------------------------------");
                    System.out.println(generateRandomPrefix(addTaskPrefixes) + userInput);
                    System.out.println("\t-----------------------------------------");

                    tasks[numTasks] = userInput;
                    numTasks++;
                }
            }
        } while (!userInput.equals("bye"));

        input.close();

        System.out.println("\t-----------------------------------------");
        System.out.println("\tOh well, it was fun while it lasted. Goodbye!");
        System.out.println("\t-----------------------------------------");
    }
}
