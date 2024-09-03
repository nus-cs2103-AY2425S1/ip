package bitbot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * The application class to run the file.
 */
public class Application {

    private static final UI ui = new UI();

    private static final String PATH_TO_FILE = "./data/Bitbot.txt";

    /**
     * Runs the programme
     *
     * @throws FileNotFoundException if the file is not found.
     */
    public static void run() throws FileNotFoundException {
        ArrayList<Task> arrayList = Storage.readTasksFromFile(PATH_TO_FILE);

        String inputData;

        //printing out the intro
        System.out.println(ui.getIntro());
        //creating a scanner
        Scanner sc = new Scanner(System.in);

        while (true) {
            StringBuilder from = new StringBuilder();
            StringBuilder to = new StringBuilder();
            StringBuilder by = new StringBuilder();
            String keyWord = null;
            String textPart = null;
            StringBuilder sb = new StringBuilder();

            int numberPart = -1;
            // to store the index when /from is present so that
            // it aids me in getting the description
            int indexFrom = -1;
            // to store the index when /by is present so that
            // it aids be in getting the description
            int indexBy = -1;

            // reading each line
            inputData = sc.nextLine();

            // this is to help check if the size is more than 1 or not
            // so that I can use the correct term (either "task" or "tasks")
            String task = "";
            if (arrayList.size() > 0) {
                task = "tasks";
            } else {
                task = "task";
            }

            try {
                String[] partsOfInput = inputData.split(" ");

                /* this is to check if there is any number in the input.
                 * I am splitting the input into parts, and then
                 * I am doing a check. If the last element is an integer,
                 * I will go ahead and save it as an integer. If not,
                 * it will be made into a string using StringBuilder.
                 * The .trim() is used to remove the trailing spaces.
                 */
                if (partsOfInput.length > 0) {
                    keyWord = partsOfInput[0];

                    switch (keyWord) {
                    case "mark":
                        numberPart = TaskHandling.checkAndThrowException(partsOfInput, arrayList);

                        arrayList.get(numberPart - 1).markAsDone();
                        System.out.println("          ____________________________________\n          "
                                + "Nice! I've marked this task as done:");
                        System.out.println("             " + arrayList.get(numberPart - 1).finalString());
                        System.out.println("          ____________________________________\n");
                        break;
                    case "unmark":
                        numberPart = TaskHandling.checkAndThrowException(partsOfInput, arrayList);

                        arrayList.get(numberPart - 1).markAsUndone();
                        System.out.println("          ____________________________________\n          "
                                + "OK, I've marked this task as not done yet:");
                        System.out.println("             " + arrayList.get(numberPart - 1).finalString());
                        System.out.println("          ____________________________________\n");
                        break;
                    case "delete":
                        numberPart = TaskHandling.checkAndThrowException(partsOfInput, arrayList);

                        Task task1 = arrayList.remove(numberPart - 1);
                        System.out.println("          ____________________________________\n          "
                                + "Noted. I've removed this task:\n"
                                + "             " + task1.finalString() + "\n"
                                + "          Now you have " + arrayList.size() + " " + task + " in the list.\n"
                                + "          ____________________________________");
                        break;
                    case "find":
                        TaskHandling.handleFind(partsOfInput, arrayList);
                        break;
                    case "event":
                        // this is to check if the keyword is "event".
                        // if so, get the different parts accurately.
                        TaskHandling.handleEvent(arrayList, textPart, partsOfInput, indexFrom, from, to, sb, task);
                        break;
                    case "deadline":
                        // the same concept as above for the keyword "deadline"
                        TaskHandling.handleDeadline(arrayList, textPart, partsOfInput, indexBy, by, sb, task);
                        break;
                    case "todo":
                        // the same concept as above for the keyword "todo"
                        TaskHandling.handleTodo(arrayList, textPart, partsOfInput, sb, task);
                        break;
                    case "list":
                        // the same concept as above for the keyword "list"
                        TaskHandling.handleList(arrayList);
                        break;
                    case "bye":
                        // once the user types in bye,
                        // add all the tasks in the list into BitBot.txt
                        // and then do nothing else.
                        Storage.saveTasksToFile(arrayList);
                        break;
                    default:
                        // if it does not fall in any of this keyword,
                        // throw an error saying there is no such keyword.
                        throw new BitBotException("OOPS!!! I do not know what this keyword is!\n"
                                + "          Please key in only one of these:\n          "
                                + "\n          "
                                + "mark \n          "
                                + "unmark \n          "
                                + "todo \n          "
                                + "deadline \n          "
                                + "event\n          "
                                + "list\n          "
                                + "delete\n          "
                                + "bye\n          "
                                + "find\n          "
                                + "\n          "
                                + "Please key in in this format:\n          "
                                + "todo ... / deadline ... ");
                    }

                } else {
                    textPart = inputData.trim();
                    keyWord = textPart;
                }

                // if the user keys in "bye" break out of the while loop and print out the conclusion.
                if (Objects.equals(inputData, "bye")) {
                    break;
                }
            } catch (BitBotException e) {
                System.out.println("          ____________________________________");
                System.out.println("          " + e.getMessage());
                System.out.println("          ____________________________________");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());;
            }
        }
        System.out.println(ui.getConclusion());

    }

}
