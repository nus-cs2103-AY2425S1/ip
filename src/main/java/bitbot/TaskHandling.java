package bitbot;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskHandling {
    /**
     * Handles the event task. It takes in the input
     * and extract out the description, the "from" time and the "to" time.
     * @param arrayList the list of tasks
     * @param textPart the description of the input
     * @param partsOfInput the String[] of the split input
     * @param indexFrom the integer to store the index of the word "/from"
     * @param from A StringBuilder to build the "from" time
     * @param to A StringBuilder to build the "to" time
     * @param sb A StringBuilder to build the event description
     * @param task This helps to determine if I need to add "task" or "tasks"
     * @throws BitBotException if input is invalid
     */
    public static void handleEvent (ArrayList<Task> arrayList, String textPart, String[] partsOfInput, int indexFrom, StringBuilder from, StringBuilder to, StringBuilder sb, String task) throws BitBotException {
        if (partsOfInput.length < 2) {
            throw new BitBotException("OOPS!!! The description and timings of an event should not be empty.\n          Please add a description to the event you wish to add to the list.\n" +
                    "          For example: \"event return book /from Mon 4pm /to 6pm\"\n" +
                    "          Or \"event return book /from 29-04-2021 18:00 /to 29-04-2021 18:30\"");

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
        LocalDateTime eventDateAndTime = null, eventDateAndTime1 = null;
        // this is if the user only keys in the date.
        LocalDate eventDate = null, eventDate1 = null;
        // this is if the user only keys in the time.
        LocalTime eventTime = null, eventTime1 = null;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        Task event;

        try {
            eventDateAndTime = LocalDateTime.parse(from.toString().trim(), dateTimeFormatter);
            eventDateAndTime1 = LocalDateTime.parse(to.toString().trim(), dateTimeFormatter);
        } catch (DateTimeParseException e) {
            try {
                eventTime = LocalTime.parse(from.toString().trim(), timeFormatter);
                eventTime1 = LocalTime.parse(to.toString(), timeFormatter);
            } catch (DateTimeParseException error) {
                try {
                    eventDate = LocalDate.parse(from.toString().trim(), dateFormatter);
                    eventDate1 = LocalDate.parse(to.toString().trim(), dateFormatter);
                } catch (DateTimeParseException dateTimeParseException) {
                    // do nothing
                }
            }
        }


        if (eventDateAndTime != null) {
            event = new Events(textPart, eventDateAndTime, eventDateAndTime1);
        } else if (eventTime != null) {
            event = new Events(textPart, eventTime, eventTime1);
        } else if (eventDate != null){
            event = new Events(textPart, eventDate, eventDate1);
        } else {
            // this is if the user does not key in a specific date / time and only
            // keys in values such as "today" or "tomorrow"
            event = new Events(textPart, from.toString().trim(), to.toString().trim());
        }
        arrayList.add(event);
        System.out.println("          ____________________________________\n          Got it. I've added this task:\n"
                + "             " + event.finalString() + "\n"
                + "          Now you have " + arrayList.size() + " " + task + " in the list.\n"
                + "          ____________________________________");
    }

    public static Task handleEventFromFile (String description, String from, String to) {
        Task event = new Events(description, from, to);
        return event;
    }
    /**
     * Handles the deadline task by helping to extract the "by" time
     * @param arrayList the list of tasks
     * @param textPart the description of the input
     * @param partsOfInput the String[] of the split input
     * @param indexBy the integer to store the index of the word "/by"
     * @param by A StringBuilder to build the "by" time
     * @param sb A StringBuilder to build the event description
     * @param task This helps to determine if I need to add "task" or "tasks"
     * @throws BitBotException if input is invalid
     */

    public static void handleDeadline (ArrayList<Task> arrayList, String textPart, String[] partsOfInput, int indexBy, StringBuilder by, StringBuilder sb, String task) throws BitBotException {
        if (partsOfInput.length < 2) {
            throw new BitBotException("OOPS!!! You need to add the \"by\" details.\n" +
                    "          For example: deadline homework /by Aug 6th");
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
        } else if (deadlineDate != null){
            deadline = new Deadline(textPart, deadlineDate);
        } else {
            // this is if the user does not key in a specific date / time and only
            // keys in values such as "today" or "tomorrow"
            deadline = new Deadline(textPart, by.toString().trim());
        }
        arrayList.add(deadline);
        System.out.println("          ____________________________________\n          Got it. I've added this task:\n"
                + "             " + deadline.finalString() + "\n"
                + "          Now you have " + arrayList.size() + " " + task + " in the list.\n"
                + "          ____________________________________");
    }

    public static Task handleDeadlineFromFile (String description, String by) {
        Task deadline = new Deadline(description, by);
        return deadline;
    }

    /**
     * Handles the todo by extracting the description.
     * @param arrayList the list of tasks
     * @param textPart the description of the input
     * @param partsOfInput the String[] of the split input
     * @param sb A StringBuilder to build the event description
     * @param task This helps to determine if I need to add "task" or "tasks"
     * @throws BitBotException if input is invalid
     */
    public static void handleTodo (ArrayList<Task> arrayList, String textPart, String[] partsOfInput, StringBuilder sb, String task) throws BitBotException {
        if (partsOfInput.length < 2) {
            throw new BitBotException("OOPS!!! Need to add a description for a todo activity\n " +
                    "         For example: todo borrow book");
        }
        for (int i = 1; i < partsOfInput.length; i++) {
            sb.append(partsOfInput[i]).append(" ");
        }
        textPart = sb.toString().trim();
        Task toDos = new ToDos(textPart);
        arrayList.add(toDos);
        System.out.println("          ____________________________________\n          Got it. I've added this task:\n"
                + "             " + toDos.finalString() + "\n"
                + "          Now you have " + arrayList.size() + " " + task + " in the list.\n"
                + "          ____________________________________\n");
    }

    public static Task handleTodoFromFile (String description) {
        Task todo = new ToDos(description);
        return todo;
    }
    /**
     * Handles the displaying of the list in order
     * @param arrayList the list of tasks
     */
    public static void handleList (ArrayList<Task> arrayList) {
        System.out.println("          ____________________________________\n          Here are the tasks in your list:");
        for (int i = 1; i < arrayList.size() + 1; i++) {
            System.out.println("          " + i + ". " + arrayList.get(i - 1).finalString());
        }
        System.out.println("          ____________________________________\n");
    }

    public static void handleFind (String[] partsOfInput, ArrayList<Task> arrayList) throws BitBotException {
        if (partsOfInput.length < 2) {
            throw new BitBotException("OOPS!! Add a string of words you want to find.\n" +
                    "          Please do not leave it blank.");
        }
        String textToBeFound;
        StringBuilder sb1 = new StringBuilder();
        ArrayList<Task> similarWordList = new ArrayList<>();

        for (int i = 1; i < partsOfInput.length; i++) {
            if (!sb1.isEmpty()) {
                sb1.append(" ");
            }
            sb1.append(partsOfInput[i]);
        }

        textToBeFound = sb1.toString();

        for (Task indivTask : arrayList) {
            if (indivTask.taskDescription.toLowerCase().contains(textToBeFound.toLowerCase())) {
                similarWordList.add(indivTask);
            }
        }

        if (similarWordList.isEmpty()) {
            System.out.println("          ____________________________________");
            System.out.println("          There are no matching tasks in the list.");
            System.out.println("          Please try another keyword.");
            System.out.println("          ____________________________________");
        } else {
            System.out.println("          ____________________________________");
            System.out.println("          Here are the matching tasks in your list:");
            for (int i = 1; i < similarWordList.size() + 1; i++) {
                System.out.println("          " + i + ". " + similarWordList.get(i - 1).finalString());
            }
            System.out.println("          ____________________________________");
        }
    }
}
