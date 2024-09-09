package bitbot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * This is the TaskHandling class which deals with the different types of tasks present.
 */
public class TaskHandler {
    /**
     * Handles the event task. It takes in the input
     * and extract out the description, the "from" time and the "to" time.
     *
     * @param arrayList the list of tasks
     * @param textPart the description of the input
     * @param partsOfInput the String[] of the split input
     * @param indexFrom the integer to store the index of the word "/from"
     * @param from A StringBuilder to build the "from" time
     * @param to A StringBuilder to build the "to" time
     * @param sb A StringBuilder to build the event description
     * @param task This helps to determine if I need to add "task" or "tasks"
     * @return A String that stores the output
     * @throws BitBotException if input is invalid
     */
    public static String handleEvent(ArrayList<Task> arrayList, String textPart,
                                    String[] partsOfInput, int indexFrom, StringBuilder from,
                                    StringBuilder to, StringBuilder sb, String task) throws BitBotException {
        if (partsOfInput.length < 2) {
            throw new BitBotException("OOPS!!! The description and timings of an event should not be empty.\n"
                    + "          Please add a description to the event you wish to add to the list.\n"
                    + "          For example: \"event return book /from Mon 4pm /to 6pm\"\n"
                    + "          Or \"event return book /from 29-04-2021 18:00 /to 29-04-2021 18:30\"");

        }
        for (int i = 0; i < partsOfInput.length; i++) {
            /* if the value == "/from", then I am storing the value of the index
             * in indexFrom. Then I am running another for loop to check
             * the location where the /to is present. In the middle,
             * I am adding the strings needed for from using the StringBuilder.
             * The same goes for /to. Once I find the /to, I will iterate
             * till the end of the list and then use the to StringBuilder to
             * build the string required.
             */
            if (partsOfInput[i].equals("/from")) {
                indexFrom = i;
                for (int j = i + 1; j < partsOfInput.length; j++) {
                    if (partsOfInput[j].equals("/to")) {
                        break;
                    }
                    if (!from.isEmpty()) {
                        from.append(" ");
                    }
                    from.append(partsOfInput[j]);
                }
            } else if (partsOfInput[i].equals("/to")) {
                for (int j = i + 1; j < partsOfInput.length; j++) {
                    if (!to.isEmpty()) {
                        to.append(" ");
                    }
                    to.append(partsOfInput[j]);
                }
            }
        }
        // finally I am getting the required part for the description using the indexFrom stored earlier.
        for (int i = 1; i < indexFrom; i++) {
            sb.append(partsOfInput[i]).append(" ");
        }
        textPart = sb.toString().trim();

        // this is if the user keys in a time after the date.
        LocalDateTime beginningDateTime = null;
        LocalDateTime endingDateTime = null;
        // this is if the user only keys in the date.
        LocalDate beginningDate = null;
        LocalDate endingDate = null;
        // this is if the user only keys in the time.
        LocalTime beginningTime = null;
        LocalTime endingTime = null;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        Task event;

        try {
            beginningDateTime = LocalDateTime.parse(from.toString().trim(), dateTimeFormatter);
            endingDateTime = LocalDateTime.parse(to.toString().trim(), dateTimeFormatter);
        } catch (DateTimeParseException e) {
            try {
                beginningTime = LocalTime.parse(from.toString().trim(), timeFormatter);
                endingTime = LocalTime.parse(to.toString(), timeFormatter);
            } catch (DateTimeParseException error) {
                try {
                    beginningDate = LocalDate.parse(from.toString().trim(), dateFormatter);
                    endingDate = LocalDate.parse(to.toString().trim(), dateFormatter);
                } catch (DateTimeParseException dateTimeParseException) {
                    // do nothing
                }
            }
        }

        assert (beginningDateTime != null || beginningTime != null || beginningDate != null)
                : "Invalid start date/time input.";
        assert (endingDateTime != null || endingTime != null || endingDate != null)
                : "Invalid end date/time input";

        if (beginningDateTime != null) {
            event = new Events(textPart, beginningDateTime, endingDateTime);
        } else if (beginningTime != null) {
            event = new Events(textPart, beginningTime, endingTime);
        } else if (beginningDate != null) {
            event = new Events(textPart, beginningDate, endingDate);
        } else {
            // this is if the user does not key in a specific date / time and only
            // keys in values such as "today" or "tomorrow"
            event = new Events(textPart, from.toString().trim(), to.toString().trim());
        }
        arrayList.add(event);
        return "          ____________________________________\n          Got it. I've added this task:\n"
                + "             " + event.finalString() + "\n"
                + "          Now you have " + arrayList.size() + " " + task + " in the list.\n"
                + "          ____________________________________";
    }

    /**
     * Takes in the description, the "from" and "to" string from the file
     * and converts them into an Event.
     *
     * @param description the string that tells the user what the task is about
     * @param from the time when the task is supposed to start
     * @param to the time when the task is supposed to be completed
     * @return an Event
     */
    public static Task handleEventFromFile(String description, String from, String to) {
        Task event = new Events(description, from, to);
        return event;
    }
    /**
     * Handles the deadline task by helping to extract the "by" time
     *
     * @param arrayList the list of tasks
     * @param textPart the description of the input
     * @param partsOfInput the String[] of the split input
     * @param indexBy the integer to store the index of the word "/by"
     * @param by A StringBuilder to build the "by" time
     * @param sb A StringBuilder to build the event description
     * @param task This helps to determine if I need to add "task" or "tasks"
     * @return A String that stores the output
     * @throws BitBotException if input is invalid
     */

    public static String handleDeadline(ArrayList<Task> arrayList, String textPart,
                                       String[] partsOfInput, int indexBy, StringBuilder by,
                                       StringBuilder sb, String task) throws BitBotException {
        if (partsOfInput.length < 2) {
            throw new BitBotException("OOPS!!! You need to add the \"by\" details.\n"
                    + "          For example: deadline homework /by Aug 6th");
        }
        for (int i = 0; i < partsOfInput.length; i++) {
            if (partsOfInput[i].equals("/by")) {
                indexBy = i;
                for (int j = i + 1; j < partsOfInput.length; j++) {
                    if (!by.isEmpty()) {
                        by.append(" ");
                    }
                    by.append(partsOfInput[j]);
                }
            }

        }
        for (int i = 1; i < indexBy; i++) {
            sb.append(partsOfInput[i]).append(" ");
        }
        textPart = sb.toString().trim();

        // this is if the user keys in a time after the date.
        LocalDateTime deadlineDateAndTime = null;
        // this is if the user only keys in the date.
        LocalDate deadlineDate = null;
        // this is if the user only keys in the time.
        LocalTime deadlineTime = null;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        Task deadline;

        try {
            deadlineDateAndTime = LocalDateTime.parse(by.toString().trim(), dateTimeFormatter);
        } catch (DateTimeParseException e) {
            try {
                deadlineTime = LocalTime.parse(by.toString().trim(), timeFormatter);
            } catch (DateTimeParseException error) {
                try {
                    deadlineDate = LocalDate.parse(by.toString().trim(), dateFormatter);
                } catch (DateTimeParseException dateTimeParseException) {
                    // do nothing
                }
            }
        }


        if (deadlineDateAndTime != null) {
            deadline = new Deadline(textPart, deadlineDateAndTime);
        } else if (deadlineTime != null) {
            deadline = new Deadline(textPart, deadlineTime);
        } else if (deadlineDate != null) {
            deadline = new Deadline(textPart, deadlineDate);
        } else {
            // this is if the user does not key in a specific date / time and only
            // keys in values such as "today" or "tomorrow"
            deadline = new Deadline(textPart, by.toString().trim());
        }
        arrayList.add(deadline);
        return "          ____________________________________\n          Got it. I've added this task:\n"
                + "             " + deadline.finalString() + "\n"
                + "          Now you have " + arrayList.size() + " " + task + " in the list.\n"
                + "          ____________________________________";
    }

    /**
     * Takes in the description and the "by" string from the file
     * and converts them into a Deadline.
     *
     * @param description the string that tells the user what the task is about
     * @param by the time by when the task is supposed to be completed.
     * @return a Deadline
     */
    public static Task handleDeadlineFromFile(String description, String by) {
        Task deadline = new Deadline(description, by);
        return deadline;
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
        if (partsOfInput.length < 2) {
            throw new BitBotException("OOPS!!! Need to add a description for a todo activity\n "
                    + "         For example: todo borrow book");
        }
        for (int i = 1; i < partsOfInput.length; i++) {
            sb.append(partsOfInput[i]).append(" ");
        }
        textPart = sb.toString().trim();
        Task toDos = new Todo(textPart);
        arrayList.add(toDos);
        return "          ____________________________________\n          Got it. I've added this task:\n"
                + "             " + toDos.finalString() + "\n"
                + "          Now you have " + arrayList.size() + " " + task + " in the list.\n"
                + "          ____________________________________\n";
    }

    /**
     * Takes in the description and converts it into a Todo.
     * @param description the string that tells the user what the task is about
     * @return a ToDos
     */
    public static Task handleTodoFromFile(String description) {
        Task todo = new Todo(description);
        return todo;
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
        if (wordsToSearch == null || wordsToSearch.length == 0) {
            throw new BitBotException("OOPS!! Add a string of words you want to find.\n"
                    + "          Please do not leave it blank.");
        }

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
    public static int checkAndThrowException(String[] partsOfInput, ArrayList<Task> arrayList) throws BitBotException {
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

