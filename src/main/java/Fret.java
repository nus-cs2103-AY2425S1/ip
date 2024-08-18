import java.util.Scanner;
import java.util.Random;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fret {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("[0-9]+");
    private static final Random RNG = new Random();

    private static final String[] ADD_TASK_PREFIXES = new String[] {
        "\tYou got it! Adding task: ",
        "\tYou got it! Adding task: ",
        "\tYou got it! Adding task: ",
        "\tYou sure wanna do that? ",
        "\tAlright! Adding task: ",
        "\tAlright! Adding task: ",
        "\tAlright! Adding task: ",
        "\tOn it! Task added: ",
        "\tOn it! Task added: ",
        "\tWhatever you say! *nervous laughter*: ",
        "\tHmmmm..... Done. Task added: ",
        "\tWorking..... Done. Task added: ",
    };

    private static final String[] TASK_COMPLETED_PREFIXES = new String[] {
        "\tNice! Task marked as completed\n",
        "\tNice! Task marked as completed\n",
        "\tNice! Task marked as completed\n",
        "\tAlright! Task marked as completed\n",
        "\tPhew! Got that one out of the way!\n",
        "\tPhew! Got that one out of the way!\n",
        "\tWahoo! Task complete!\n",
        "\tWahoo! Task complete!\n",
        "\tDone and done! Should we do it again?\n",
        "\tDone and done! Should we do it again?\n"
    };

    private static final String[] TASK_UNCOMPLETED_PREFIXES = new String[] {
        "\tMarking as incomplete.\n",
        "\tBooooo\n",
        "\tBooooo\n",
        "\tAw man. Task marked as incomplete\n",
        "\tAw man. Task marked as incomplete\n",
        "\tOh well. Marking task as incomplete\n",
        "\tOh well. Marking task as incomplete\n",
        "\tDamn. Thought we had that.\n",
        "\tDamn. Thought we had that.\n",
        "\tDamn. Thought we had that.\n"
    };

    /**
     * Randomly selects and returns a prefix for a command from a given list
     * 
     * @param prefixes
     * @return a randomly selected prefix from the given list of prefixes
     */
    private static String generateRandomPrefix(String[] prefixes) {
        return prefixes[RNG.nextInt(prefixes.length)];
    }

    /**
     * Creates and returns an enumeration of the tasks added and stored by the user
     * 
     * @param tasks the list of tasks
     * @param numTasks the number of tasks
     * @return an enumeration of the tasks
     */
    private static String taskListToString(Task[] tasks, int numTasks) {
        if (numTasks == 0) {
            return "\tempty";
        }

        String[] tempTasks = new String[numTasks];

        for (int i = 1; i <= numTasks; i++) {
            tempTasks[i - 1] = "\t" + i + ". " + tasks[i - 1].toString();
        }

        return String.join("\n", tempTasks);
    }

    /**
     * Prints chatbot output to console with surrounding lines
     * 
     * @param output the output string
     */
    private static void printBotOutputString(String output) {
        System.out.println("\t-----------------------------------------");
        System.out.println(output);
        System.out.println("\t-----------------------------------------");
    }

    public static void main(String[] args) {
        // declare the list of tasks
        Task[] tasks = new Task[100];
        int numTasks = 0;

        String logo = "________                ___ \n"
                + "| _____|             ___| |___ \n"
                + "| |___  __   _   ___ |__   __|\n"
                + "| ___|  | |/ /  / _ \\   | |\n"
                + "| |     |   /  <  __/   | |__\n"
                + "|_|     |__|    \\___|   |___|\n";
        
        System.out.println("Initiating...\n" + logo);

        printBotOutputString("\tPersonal AI Fret, coming online!\n\tHey, how can I be of assistance?");

        Scanner input = new Scanner(System.in);
        String userInput;

        do {
            userInput = input.nextLine(); // get user input

            if (!userInput.equals("bye")) {
                if (userInput.equals("list")) {
                    // if user input is "list", print the tasklist with statuses
                    printBotOutputString(taskListToString(tasks, numTasks));
                } else if (userInput.startsWith("mark")) {
                    // else if user input starts with "mark" then mark task X as completed

                    Matcher taskNumMatch = NUMBER_PATTERN.matcher(userInput);

                    // search for an integer using regex
                    if (taskNumMatch.find()) {
                        int taskNum = Integer.parseInt(taskNumMatch.group());
                        // if integer represents a valid task, set it as complete
                        // otherwise reprompt
                        try {
                            tasks[taskNum - 1].markAsCompleted();
                            printBotOutputString(
                                generateRandomPrefix(TASK_COMPLETED_PREFIXES) + taskListToString(tasks, numTasks));
                        } catch (NullPointerException | IndexOutOfBoundsException e) {
                            printBotOutputString("\tOops! You don't have those many tasks!");
                        }
                    } else {
                        // if no integer found, reprompt for input
                        printBotOutputString("\tUhhh sorry what did you wanna mark again?");
                    }
                } else if (userInput.startsWith("unmark")) {
                    // else if user input starts with "unmark" then mark task X as incomplete

                    Matcher taskNumMatch = NUMBER_PATTERN.matcher(userInput);

                    // repeat same regex and integer validation process
                    if (taskNumMatch.find()) {
                        int taskNum = Integer.parseInt(taskNumMatch.group());
                        try {
                            tasks[taskNum - 1].markAsNotCompleted();
                            printBotOutputString(
                                generateRandomPrefix(TASK_UNCOMPLETED_PREFIXES) + taskListToString(tasks, numTasks));
                        } catch (NullPointerException e) {
                            printBotOutputString("\tOops! You don't have those many tasks!");
                        }
                    } else {
                        printBotOutputString("\tUhhh hang on what did you want to unmark?");
                    }
                } 
                else {
                    // otherwise just add user input to tasklist
                    printBotOutputString(generateRandomPrefix(ADD_TASK_PREFIXES) + userInput);

                    tasks[numTasks] = new Task(userInput);
                    numTasks++;
                }
            }
        } while (!userInput.equals("bye"));

        // once user enters "bye", leave the loop and exit the program
        
        input.close();

        printBotOutputString("\tOh well, it was fun while it lasted. Goodbye!");
    }
}
