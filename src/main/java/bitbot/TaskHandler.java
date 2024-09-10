package bitbot;

import static bitbot.Parser.BY_TIME_MISSING;
import static bitbot.Parser.FROM_TO_TIME_MISSING;
import static bitbot.Parser.TODO_DESCRIPTION_MISSING;
import static bitbot.Parser.buildTaskMessage;
import static bitbot.Parser.extractDescription;
import static bitbot.Parser.extractTimeDetail;
import static bitbot.Parser.handleErrorForNoFurtherInput;
import static bitbot.Parser.handleErrorForWrongLength;
import static bitbot.Parser.parseDate;
import static bitbot.Parser.parseDateTime;
import static bitbot.Parser.parseTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * This is the TaskHandling class which deals with the different types of tasks present.
 */
public class TaskHandler {

    /**
     * Creates a new Event
     *
     * @param description the description of the event
     * @param fromTime the beginning time of the event
     * @param toTime the ending time of the event
     * @return a Task
     */
    private static Task createEvent(String description, String fromTime, String toTime) {
        LocalDateTime startDateTime = parseDateTime(fromTime);
        LocalDateTime endDateTime = parseDateTime(toTime);

        if (startDateTime != null && endDateTime != null) {
            return new Events(description, startDateTime, endDateTime);
        }

        LocalTime startTime = parseTime(fromTime);
        LocalTime endTime = parseTime(toTime);

        if (startTime != null && endTime != null) {
            return new Events(description, startTime, endTime);
        }

        LocalDate startDate = parseDate(fromTime);
        LocalDate endDate = parseDate(toTime);

        if (startDate != null && endDate != null) {
            return new Events(description, startDate, endDate);
        }

        return new Events(description, fromTime.trim(), toTime.trim());
    }

    /**
     * Creates a new Deadline
     *
     * @param description the description of the deadline
     * @param byTimeInput the time by which the deadline should end
     * @return a Task
     */
    private static Task createDeadline(String description, String byTimeInput) {
        LocalDateTime byDateTime = parseDateTime(byTimeInput);

        if (byDateTime != null) {
            return new Deadline(description, byDateTime);
        }

        LocalTime byTime = parseTime(byTimeInput);

        if (byTime != null) {
            return new Deadline(description, byTime);
        }

        LocalDate byDate = parseDate(byTimeInput);

        if (byDate != null) {
            return new Deadline(description, byDate);
        }

        return new Deadline(description, byTimeInput.trim());

    }
    /**
     * Handles the event task. It takes in the input
     * and extract out the description, the "from" time and the "to" time.
     *
     * @param arrayList the list of tasks
     * @param partsOfInput the String[] of the split input
     * @param task This helps to determine if I need to add "task" or "tasks"
     * @return A String that stores the output
     * @throws BitBotException if input is invalid
     */
    public static String handleEvent(ArrayList<Task> arrayList,
                                     String[] partsOfInput, String task) throws BitBotException {

        handleErrorForWrongLength(FROM_TO_TIME_MISSING, partsOfInput);

        String description = extractDescription(partsOfInput, "/from");
        String fromTime = extractTimeDetail(partsOfInput, "/from", "/to");
        String toTime = extractTimeDetail(partsOfInput, "/to", null);

        Task event = createEvent(description, fromTime, toTime);
        arrayList.add(event);
        return buildTaskMessage(event, arrayList.size(), task);
    }
    /**
     * Handles the deadline task by helping to extract the "by" time
     *
     * @param arrayList the list of tasks
     * @param partsOfInput the String[] of the split input
     * @param task This helps to determine if I need to add "task" or "tasks"
     * @return A String that stores the output
     * @throws BitBotException if input is invalid
     */

    public static String handleDeadline(ArrayList<Task> arrayList,
                                       String[] partsOfInput, String task) throws BitBotException {

        handleErrorForWrongLength(BY_TIME_MISSING, partsOfInput);

        String description = extractDescription(partsOfInput, "/by");
        String byTime = extractTimeDetail(partsOfInput, "/by", null);

        Task deadline = createDeadline(description, byTime);
        arrayList.add(deadline);
        return buildTaskMessage(deadline, arrayList.size(), task);
    }

    /**
     * Handles the todo by extracting the description.
     *
     * @param arrayList the list of tasks
     * @param textPart the description of the input
     * @param partsOfInput the String[] of the split input
     * @param sb A StringBuilder to build the event description
     * @param task This helps to determine if I need to add "task" or "tasks"
     * @return A string that stores the output
     * @throws BitBotException if input is invalid
     */
    public static String handleTodo(ArrayList<Task> arrayList, String textPart,
                                   String[] partsOfInput, StringBuilder sb, String task) throws BitBotException {

        handleErrorForWrongLength(TODO_DESCRIPTION_MISSING, partsOfInput);

        for (int i = 1; i < partsOfInput.length; i++) {
            sb.append(partsOfInput[i]).append(" ");
        }
        textPart = sb.toString().trim();
        Task toDos = new Todo(textPart);
        arrayList.add(toDos);
        return buildTaskMessage(toDos, arrayList.size(), task);
    }
    /**
     * Handles the display of the list in order
     *
     * @param arrayList the list of tasks
     * @return A String that stores the tasks
     */
    public static String handleList(ArrayList<Task> arrayList) {

        StringBuilder result = new StringBuilder();
        result.append("          ____________________________________\n          Here are the tasks in your list:\n");
        for (int i = 1; i < arrayList.size() + 1; i++) {
            result.append("          " + i + ". " + arrayList.get(i - 1).finalString() + "\n");
        }
        result.append("          ____________________________________\n");
        return result.toString();
    }

    /**
     * Finds the related tasks and returns a list of tasks for the user to see.
     *
     * @param arrayList the list of tasks
     * @param wordsToSearch this is a variable argument of Strings that the user can input.
     * @throws BitBotException when the user does not key in any text after "find"
     */
    public static String handleFind(ArrayList<Task> arrayList, String... wordsToSearch) throws BitBotException {
        handleErrorForNoFurtherInput(wordsToSearch);

        String textToBeFound;
        StringBuilder sb1 = new StringBuilder();
        StringBuilder result = new StringBuilder();
        ArrayList<Task> similarWordList = new ArrayList<>();

        for (String eachWord : wordsToSearch) {
            if (!sb1.isEmpty()) {
                sb1.append(" ");
            }
            sb1.append(eachWord);
        }

        textToBeFound = sb1.toString();

        for (Task indivTask : arrayList) {
            if (indivTask.taskDescription.toLowerCase().contains(textToBeFound.toLowerCase())) {
                similarWordList.add(indivTask);
            }
        }

        if (similarWordList.isEmpty()) {
            result.append("          ____________________________________\n");
            result.append("          There are no matching tasks in the list.\n");
            result.append("          Please try another keyword.\n");
            result.append("          ____________________________________\n");
        } else {
            result.append("          ____________________________________\n");
            result.append("          Here are the matching tasks in your list:\n");
            for (int i = 1; i < similarWordList.size() + 1; i++) {
                result.append("          " + i + ". " + similarWordList.get(i - 1).finalString() + "\n");
            }
            result.append("          ____________________________________\n");
        }
        return result.toString();
    }

    /**
     * Handles the tagging and tags corresponding to the user's input
     *
     * @param arrayList the list of tasks
     * @param input the String[] of the split input
     * @return a String
     * @throws BitBotException when the user keys in no text after tag
     */
    public static String handleTag(ArrayList<Task> arrayList, String[] input) throws BitBotException {
        handleErrorForNoFurtherInput(input);
        try {
            int index = Integer.parseInt(input[1]);

            if (index < 1 || index > arrayList.size()) {
                throw new BitBotException("OOPS!! The index you want to tag has to be "
                        + "within the size of the list.");
            }

            String tag = input[2];
            if (!tag.startsWith("#")) {
                throw new BitBotException("OOPS!! Tags should start with #");
            }

            tag = tag.substring(1);

            Task task = arrayList.get(index - 1);
            task.markAsTagged(tag);

            return "          ____________________________________\n          "
                    + "Nice! I've tagged this task:\n"
                    + "             " + arrayList.get(index - 1).finalString()
                    + "\n"
                    + "             with #" + tag
                    + "\n"
                    + "          ____________________________________\n";

        } catch (NumberFormatException e) {
            throw new BitBotException("OOPS!! Invalid number, key in a proper positive number!!");
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new BitBotException("OOPS!! Exceeding the range of the list.\n"
                    + "          Please key in a number within the size of the list");
        }

    }

    /**
     * Untags the given task
     *
     * @param arrayList the list of tasks
     * @param input the String[] of the split input
     * @param index the index in which the task is meant to be untagged
     * @return a String
     * @throws BitBotException if there is no integer input by the user
     */
    public static String handleUntag(ArrayList<Task> arrayList, String[] input, int index) throws BitBotException {
        checkAndThrowExceptionForMarkUnmarkDelete(input, arrayList);

        arrayList.get(index - 1).markAsNotTagged();
        return "          ____________________________________\n          "
                + "OK, I've untagged this task:\n"
                + "             " + arrayList.get(index - 1).finalString()
                + "\n"
                + "          ____________________________________\n";
    }

    /**
     * Marks a task as done
     *
     * @param arrayList the list of tasks
     * @param index the index in which the task is meant to be marked as done
     * @return a String
     */
    public static String handleMark(ArrayList<Task> arrayList, int index) {
        arrayList.get(index - 1).markAsDone();
        return "          ____________________________________\n          "
                + "Nice! I've marked this task as done:\n"
                + "             " + arrayList.get(index - 1).finalString()
                + "\n"
                + "          ____________________________________\n";
    }

    /**
     * Unmarks a task as done
     *
     * @param arrayList the list of tasks
     * @param index the index in which the task is meant to be unmarked
     * @return a String
     */
    public static String handleUnmark(ArrayList<Task> arrayList, int index) {
        arrayList.get(index - 1).markAsUndone();
        return "          ____________________________________\n          "
                + "OK, I've marked this task as not done yet:"
                + "             " + arrayList.get(index - 1).finalString()
                + "\n"
                + "          ____________________________________\n";
    }

    /**
     * Deletes a task from the list when user does not want it anymore
     *
     * @param arrayList the list of tasks
     * @param index the index in which the task is meant to be deleted
     * @param task whether the word is supposed to be "task" or "tasks"
     * @return a String
     */
    public static String handleDelete(ArrayList<Task> arrayList, int index, String task) {
        Task task1 = arrayList.remove(index - 1);
        return "          ____________________________________\n          "
                + "Noted. I've removed this task:\n"
                + "             " + task1.finalString() + "\n"
                + "          Now you have " + arrayList.size() + " " + task + " in the list.\n"
                + "          ____________________________________";
    }
    /**
     * Checks if the string is a string or whether it is an integer wrapped in a string.
     *
     * @param str the input string
     * @return true if it is a string and false if it is an integer wrapped in a string.
     */
    private static boolean isAString(String str) {
        try {
            Integer.parseInt(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    /**
     * Checks and throws exception if the length does not confirm to the standards required.
     *
     * @param partsOfInput the String[] of the split input
     * @param arrayList the list of tasks
     * @return an int of the numberPart so that it can use it in subsequent calculations.
     * @throws BitBotException when the user does not key in any text after "delete" / "mark" / "unmark"
     */
    public static int checkAndThrowExceptionForMarkUnmarkDelete(String[] partsOfInput,
                                                                ArrayList<Task> arrayList) throws BitBotException {
        // to get the integer number for mark / unmark / delete / find
        int numberPart = -1;

        if (partsOfInput.length < 2) {
            throw new BitBotException("OOPS!! Add the task number to mark/ unmark / delete the event.\n"
                    + "          Please do not leave it blank.");
        }
        if (isAString(partsOfInput[partsOfInput.length - 1])) {
            throw new BitBotException("OOPS!!! The second character should be a number.\n"
                    + "          For example: delete 1");
        }
        if (arrayList.isEmpty()) {
            throw new BitBotException("You cannot mark / unmark / delete anything yet "
                    + "since the list is empty.\n"
                    + "          Add something into the list first!");
        }

        String lastNumber = partsOfInput[partsOfInput.length - 1];
        numberPart = Integer.parseInt(lastNumber);

        if (numberPart - 1 >= arrayList.size()) {
            throw new BitBotException("OOPS!!! You cannot mark / unmark / delete something "
                    + "that is outside the length of the list.\n"
                    + "          There are only " + arrayList.size() + " item(s) in your list so far.");
        }

        return numberPart;
    }

}

