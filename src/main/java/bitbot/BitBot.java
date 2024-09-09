package bitbot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The main class to run the application
 */
public class BitBot {
    private static final String PATH_TO_FILE = "./data/Bitbot.txt";
    private ArrayList<Task> arrayList;

    public BitBot() throws FileNotFoundException {
        arrayList = Storage.readTasksFromFile(PATH_TO_FILE);
    }
    public String getResponse(String input) {
        String finalResponse = null;
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

        // this is to help check if the size is more than 1 or not
        // so that I can use the correct term (either "task" or "tasks")
        String task = !(arrayList.size() > 0) ? "task" : "tasks";

        try {
            String[] partsOfInput = input.split(" ");

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
                    numberPart = TaskHandler.checkAndThrowExceptionForMarkUnmarkDelete(partsOfInput, arrayList);

                    arrayList.get(numberPart - 1).markAsDone();
                    finalResponse = "          ____________________________________\n          "
                            + "Nice! I've marked this task as done:\n"
                            + "             " + arrayList.get(numberPart - 1).finalString()
                            + "\n"
                            + "          ____________________________________\n";
                    break;
                case "unmark":
                    numberPart = TaskHandler.checkAndThrowExceptionForMarkUnmarkDelete(partsOfInput, arrayList);

                    arrayList.get(numberPart - 1).markAsUndone();
                    finalResponse = "          ____________________________________\n          "
                            + "OK, I've marked this task as not done yet:"
                            + "             " + arrayList.get(numberPart - 1).finalString()
                            + "\n"
                            + "          ____________________________________\n";
                    break;
                case "delete":
                    numberPart = TaskHandler.checkAndThrowExceptionForMarkUnmarkDelete(partsOfInput, arrayList);

                    Task task1 = arrayList.remove(numberPart - 1);
                    finalResponse = "          ____________________________________\n          "
                            + "Noted. I've removed this task:\n"
                            + "             " + task1.finalString() + "\n"
                            + "          Now you have " + arrayList.size() + " " + task + " in the list.\n"
                            + "          ____________________________________";
                    break;
                case "find":
                    finalResponse = TaskHandler.handleFind(arrayList,
                            Arrays.copyOfRange(partsOfInput, 1, partsOfInput.length));
                    break;
                case "event":
                    // this is to check if the keyword is "event".
                    // if so, get the different parts accurately.
                    finalResponse = TaskHandler.handleEvent(arrayList, partsOfInput, task);
                    break;
                case "deadline":
                    // the same concept as above for the keyword "deadline"
                    finalResponse = TaskHandler.handleDeadline(arrayList, partsOfInput, task);
                    break;
                case "todo":
                    // the same concept as above for the keyword "todo"
                    finalResponse = TaskHandler.handleTodo(arrayList, textPart, partsOfInput, sb, task);
                    break;
                case "list":
                    // the same concept as above for the keyword "list"
                    finalResponse = TaskHandler.handleList(arrayList);
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
                textPart = input.trim();
                keyWord = textPart;
            }

        } catch (BitBotException e) {
            finalResponse = "          ____________________________________\n"
                    + "          " + e.getMessage() + "\n"
                    + "          ____________________________________";
        } catch (IOException e) {
            finalResponse = "Error: " + e.getMessage();
        }

        return finalResponse;

    }

}
