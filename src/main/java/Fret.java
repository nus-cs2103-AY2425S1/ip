import java.util.Scanner;
import java.util.Random;

import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fret {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("[0-9]+");
    private static final Pattern TODO_PATTERN = Pattern.compile("todo (.+)");
    private static final Pattern DEADLINE_PATTERN = Pattern.compile("deadline (.+?) /by (.+)");
    private static final Pattern EVENT_PATTERN = Pattern.compile("event (.+?) /from (.+) /to (.+)");
    private static final Random RNG = new Random();

    private static final String[] ADD_TASK_PREFIXES = new String[] {
        "\tYou got it! Adding task:\n\t",
        "\tYou got it! Adding task:\n\t",
        "\tYou got it! Adding task:\n\t",
        "\tYou sure wanna do that?\n\t",
        "\tAlright! Adding task:\n\t",
        "\tAlright! Adding task:\n\t",
        "\tAlright! Adding task:\n\t",
        "\tOn it! Task added:\n\t",
        "\tOn it! Task added:\n\t",
        "\tWhatever you say! *nervous laughter*:\n\t",
        "\tHmmmm..... Done. Task added:\n\t",
        "\tWorking..... Done. Task added:\n\t",
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
        "\tBooooo\n",
        "\tAw man. Task marked as incomplete\n",
        "\tAw man. Task marked as incomplete\n",
        "\tOh well. Marking task as incomplete\n",
        "\tOh well. Marking task as incomplete\n",
        "\tDamn. Thought we had that.\n",
        "\tAw man. Thought we had that.\n",
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
    private static String taskListToString(ArrayList<Task> tasks, int numTasks) {
        if (numTasks == 0) {
            return "\tempty";
        }

        String[] tempTasks = new String[numTasks];

        for (int i = 1; i <= numTasks; i++) {
            tempTasks[i - 1] = "\t" + i + ". " + tasks.get(i - 1).toString();
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
        ArrayList<Task> tasks = new ArrayList<>();
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
            userInput = input.nextLine().toLowerCase(); // get user input and make it small letters

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
                            tasks.get(taskNum - 1).markAsCompleted();
                            printBotOutputString(
                                generateRandomPrefix(TASK_COMPLETED_PREFIXES) + taskListToString(tasks, numTasks)
                            );
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
                            tasks.get(taskNum - 1).markAsNotCompleted();
                            printBotOutputString(
                                generateRandomPrefix(TASK_UNCOMPLETED_PREFIXES) + taskListToString(tasks, numTasks));
                        } catch (NullPointerException e) {
                            printBotOutputString("\tOops! You don't have those many tasks!");
                        }
                    } else {
                        printBotOutputString("\tUhhh hang on what did you want to unmark?");
                    }
                } 
                else if (userInput.startsWith("todo")) {
                    // add add user input to tasklist as todo
                    Matcher taskMatcher = TODO_PATTERN.matcher(userInput);

                    if (taskMatcher.find()) {
                        Todo task = new Todo(taskMatcher.group(1));
                        tasks.add(task);
                        numTasks++;
                        printBotOutputString(generateRandomPrefix(ADD_TASK_PREFIXES) + task.toString());
                    } else {
                        printBotOutputString("\tHang on it looks like you haven't given me any task to add!");
                    }
                } else if (userInput.startsWith("deadline")) {
                    // add user input as deadline task, after parsing out the date and task
                    Matcher taskMatcher = DEADLINE_PATTERN.matcher(userInput);

                    if (taskMatcher.find()) {
                        Deadline task = new Deadline(taskMatcher.group(1), taskMatcher.group(2));
                        tasks.add(task);
                        numTasks++;
                        printBotOutputString(generateRandomPrefix(ADD_TASK_PREFIXES) + task.toString());
                    } else {
                        printBotOutputString("\tOops! You haven't given me any task and deadline to add!");
                    }
                } else if (userInput.startsWith("event")) {
                    // add user input as event task, after parsing out the dates and task
                    Matcher taskMatcher = EVENT_PATTERN.matcher(userInput);

                    if (taskMatcher.find()) {
                        Event task = new Event(
                            taskMatcher.group(1),
                            taskMatcher.group(2),
                            taskMatcher.group(3)
                        );
                        tasks.add(task);
                        numTasks++;
                        printBotOutputString(generateRandomPrefix(ADD_TASK_PREFIXES) + task.toString());
                    } else {
                        printBotOutputString("\tHold on! You haven't given me any task and timings to add!");
                    }
                } else {
                    printBotOutputString("\tUhhh I did not get that so I'm just gonna say yes!");
                }
            }
        } while (!userInput.equals("bye"));

        // once user enters "bye", leave the loop and exit the program

        input.close();

        printBotOutputString("\tOh well, it was fun while it lasted. Goodbye!");
    }
}
