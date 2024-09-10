package bitbot;

import static bitbot.TaskHandler.handleDeadline;
import static bitbot.TaskHandler.handleDelete;
import static bitbot.TaskHandler.handleEvent;
import static bitbot.TaskHandler.handleList;
import static bitbot.TaskHandler.handleMark;
import static bitbot.TaskHandler.handleTag;
import static bitbot.TaskHandler.handleTodo;
import static bitbot.TaskHandler.handleUnmark;
import static bitbot.TaskHandler.handleUntag;

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

        // this is to help check if the size is more than 1 or not
        // so that I can use the correct term (either "task" or "tasks")
        String task = !(arrayList.size() > 0) ? "task" : "tasks";

        try {
            String[] partsOfInput = input.split(" ");

            if (partsOfInput.length > 0) {
                keyWord = partsOfInput[0];

                switch (keyWord) {
                case "mark":
                    numberPart = TaskHandler.checkAndThrowExceptionForMarkUnmarkDelete(partsOfInput, arrayList);
                    finalResponse = handleMark(arrayList, numberPart);
                    break;
                case "unmark":
                    numberPart = TaskHandler.checkAndThrowExceptionForMarkUnmarkDelete(partsOfInput, arrayList);
                    finalResponse = handleUnmark(arrayList, numberPart);
                    break;
                case "delete":
                    numberPart = TaskHandler.checkAndThrowExceptionForMarkUnmarkDelete(partsOfInput, arrayList);
                    finalResponse = handleDelete(arrayList, numberPart, task);
                    break;
                case "find":
                    finalResponse = TaskHandler.handleFind(arrayList,
                            Arrays.copyOfRange(partsOfInput, 1, partsOfInput.length));
                    break;
                case "event":
                    finalResponse = handleEvent(arrayList, partsOfInput, task);
                    break;
                case "deadline":
                    finalResponse = handleDeadline(arrayList, partsOfInput, task);
                    break;
                case "todo":
                    finalResponse = handleTodo(arrayList, textPart, partsOfInput, sb, task);
                    break;
                case "list":
                    finalResponse = handleList(arrayList);
                    break;
                case "tag":
                    finalResponse = handleTag(arrayList, partsOfInput);
                    break;
                case "untag":
                    numberPart = TaskHandler.checkAndThrowExceptionForMarkUnmarkDelete(partsOfInput, arrayList);
                    finalResponse = handleUntag(arrayList, partsOfInput, numberPart);
                    break;
                case "bye":
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
                            + "tag\n          "
                            + "untag\n          "
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
