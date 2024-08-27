import javax.crypto.spec.PSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


public class BitBot {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");


    private static final String PATH_TO_FILE = "./data/Bitbot.txt";

    private static void ensureFileExists() throws IOException {
        File file = new File(PATH_TO_FILE);

        if (!file.exists()) {
            // creating the relevant directory
            // USAGE OF HELP FROM AI:
            /*
                Prompt given: How to create a new directory if the user's system does not have the
                directory initially?

                Answer from ChatGPT:
                "To create a new directory if it doesn't already exist on the user's system,
                you can use the mkdir() or mkdirs() methods from the File class in Java.
                Here’s how you can do it:

                Using mkdir() and mkdirs()
                mkdir(): Creates the directory if it doesn’t exist.
                         It will not create any required but non-existent parent directories.
                mkdirs(): Creates the directory and any necessary but non-existent parent directories."
             */
            file.getParentFile().mkdirs();
            // creating the new file.
            file.createNewFile();
        }
    }

    private static void saveTasksToFile(ArrayList<Task> tasks) throws IOException {
        ensureFileExists();
        FileWriter fileWriter = new FileWriter(PATH_TO_FILE);
        for (Task task : tasks) {
            fileWriter.write(task.toFileFormat() + System.lineSeparator());
        }

        fileWriter.close();
    }

    private static ArrayList<Task> readTasksFromFile(String filePath) throws FileNotFoundException {
        ArrayList<Task> listFromFile = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            // adapted from W3.4c (using Scanner) of notes.
            while (scanner.hasNextLine()) {
                String[] partsOfLineFromFile = scanner.nextLine().split("\\|");
                Task task = null;

                // trims the trailing whitespace
                switch (partsOfLineFromFile[0].trim()) {
                case "D":

                    String descriptionOfDeadline = partsOfLineFromFile[2].trim();
                    String deadlineBy = partsOfLineFromFile[3].trim();
                    try {
                        LocalDateTime localDateTime = LocalDateTime.parse(deadlineBy, dateTimeFormatter);
                        task = new Deadline(descriptionOfDeadline, localDateTime);
                    } catch (DateTimeParseException e) {
                        try {
                            LocalDate localDate = LocalDate.parse(deadlineBy, dateFormatter);
                            task = new Deadline(descriptionOfDeadline, localDate);
                        } catch (DateTimeParseException err) {
                            try {
                                LocalTime localTime = LocalTime.parse(deadlineBy, timeFormatter);
                                task = new Deadline(descriptionOfDeadline, localTime);
                            } catch (DateTimeParseException error) {
                                task = new Deadline(descriptionOfDeadline, deadlineBy);
                            }
                        }
                    }
                    break;

                case "T":
                    task = handleTodoFromFile(partsOfLineFromFile[2]);
                    break;

                case "E":
                    String descriptionOfEvent = partsOfLineFromFile[2].trim();
                    String eventFrom = partsOfLineFromFile[3].trim();
                    String eventTo = partsOfLineFromFile[4].trim();
                    try {
                        LocalDateTime localDateTime = LocalDateTime.parse(eventFrom, dateTimeFormatter);
                        LocalDateTime localDateTime1 = LocalDateTime.parse(eventTo, dateTimeFormatter);
                        task = new Events(descriptionOfEvent, localDateTime, localDateTime1);
                    } catch (DateTimeParseException e) {
                        try {
                            LocalDate localDate = LocalDate.parse(eventFrom, dateFormatter);
                            LocalDate localDate1 = LocalDate.parse(eventTo, dateFormatter);
                            task = new Events(descriptionOfEvent, localDate, localDate1);
                        } catch (DateTimeParseException err) {
                            try {
                                LocalTime localTime = LocalTime.parse(eventFrom, timeFormatter);
                                LocalTime localTime1 = LocalTime.parse(eventTo, timeFormatter);
                                task = new Events(descriptionOfEvent, localTime, localTime1);
                            } catch (DateTimeParseException error) {
                                task = new Events(descriptionOfEvent, eventFrom, eventTo);
                            }
                        }
                    }
                    break;
                }

                if (partsOfLineFromFile[1].trim().equals("X")) {
                    task.markAsDone();
                }
                listFromFile.add(task);

            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e.getMessage());
        }
        return listFromFile;

    }


    /**
     * Checks if the string is a string or whether it is an integer wrapped in a string.
     * @param str the input string
     * @return true if it is a string and false if it is an integer wrapped in a string.
     */
    private static boolean isAString (String str) {
        try {
            Integer.parseInt(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

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
    private static void handleEvent (ArrayList<Task> arrayList, String textPart, String[] partsOfInput, int indexFrom, StringBuilder from, StringBuilder to, StringBuilder sb, String task) throws BitBotException {
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

    private static Task handleEventFromFile (String description, String from, String to) {
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

    private static void handleDeadline (ArrayList<Task> arrayList, String textPart, String[] partsOfInput, int indexBy, StringBuilder by, StringBuilder sb, String task) throws BitBotException {
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

    private static Task handleDeadlineFromFile (String description, String by) {
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
    private static void handleTodo (ArrayList<Task> arrayList, String textPart, String[] partsOfInput, StringBuilder sb, String task) throws BitBotException {
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

    private static Task handleTodoFromFile (String description) {
        Task todo = new ToDos(description);
        return todo;
    }
    /**
     * Handles the displaying of the list in order
     * @param arrayList the list of tasks
     */
    private static void handleList (ArrayList<Task> arrayList) {
        System.out.println("          ____________________________________\n          Here are the tasks in your list:");
        for (int i = 1; i < arrayList.size() + 1; i++) {
            System.out.println("          " + i + ". " + arrayList.get(i - 1).finalString());
        }
        System.out.println("          ____________________________________\n");
    }

    public static void main(String[] args) throws BitBotException, FileNotFoundException {

        ArrayList<Task> arrayList = readTasksFromFile(PATH_TO_FILE);

        String inputData;
        String intro = "          ____________________________________\n          Hello! I'm BitBot\n          What can I do for you?\n          ____________________________________\n" +
            "          Please key in only one of these keywords:\n          " +
            "\n          " +
            "mark \n          " +
            "unmark \n          " +
            "todo \n          " +
            "deadline \n          " +
            "event\n          " +
            "list\n          " +
            "delete\n          " +
            "bye\n          " +
            "\n          " +
            "Please key any one of the keywords above in this format:\n          " +
            "todo ... / deadline ...\n          " +
            "\n          " +
            "When keying in the date / time, do so in one of these formats:\n          " +
            "dd-MM-yyyy HH:mm or just dd-MM-yyyy or just HH:mm\n          " +
            "____________________________________";

        String conclusion = "          ____________________________________\n          Bye. Hope to see you again soon!\n          ____________________________________\n";

        //printing out the intro
        System.out.println(intro);
        //creating a scanner
        Scanner sc = new Scanner(System.in);

        while (true) {
            StringBuilder from = new StringBuilder();
            StringBuilder to = new StringBuilder();
            StringBuilder by = new StringBuilder();
            String keyWord = null;
            String textPart = null;
            StringBuilder sb = new StringBuilder();

            // to get the integer number for mark and unmark
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

                    if (Objects.equals(keyWord, "mark") || Objects.equals(keyWord, "unmark") || Objects.equals(keyWord, "delete")) {
                        if (partsOfInput.length < 2) {
                            throw new BitBotException("OOPS!! Add the task number to mark/ unmark / delete the event.\n" +
                                    "          Please do not leave it blank");
                        }
                        if (isAString(partsOfInput[partsOfInput.length - 1])) {
                            throw new BitBotException("OOPS!!! The second character should be a number.\n" +
                                    "          For example: delete 1");
                        }
                        if (arrayList.isEmpty()) {
                            throw new BitBotException("You cannot mark / unmark / delete anything yet since the list is empty.\n" +
                                    "          Add something into the list first!");
                        }

                        String lastPart = partsOfInput[partsOfInput.length - 1];
                        numberPart = Integer.parseInt(lastPart);

                        if (numberPart - 1 >= arrayList.size()) {
                            throw new BitBotException("OOPS!!! You cannot mark / unmark / delete something that is outside the length of the list.\n" +
                                    "          There are only " + arrayList.size() + " item(s) in your list so far.");
                        }

                    }
                    // this is to check if the keyword is "event".
                    // if so, get the different parts accurately.
                    else if (Objects.equals(keyWord, "event")) {
                        handleEvent(arrayList, textPart, partsOfInput, indexFrom, from, to, sb, task);
                    }
                    // the same concept as above for the keyword "deadline"
                    else if (Objects.equals(keyWord, "deadline")) {
                        handleDeadline(arrayList, textPart, partsOfInput, indexBy, by, sb, task);
                    }
                    // the same concept as above for the keyword "todo"
                    else if (Objects.equals(keyWord, "todo")) {
                        handleTodo(arrayList, textPart, partsOfInput, sb, task);
                    }
                    // the same concept as above for the keyword "list"
                    else if (Objects.equals(keyWord, "list")) {
                       handleList(arrayList);
                    }
                    else if (Objects.equals(keyWord, "bye")) {
                        // once the user types in bye,
                        // add all the tasks in the list into BitBot.txt
                        // and then do nothing else.
                        saveTasksToFile(arrayList);

                    }
                    // if it does not fall in any of this keyword,
                    // throw an error saying there is no such keyword.
                    else {
                        throw new BitBotException("OOPS!!! I do not know what this keyword is!\n" +
                                "          Please key in only one of these:\n          " +
                                "\n          " +
                                "mark \n          " +
                                "unmark \n          " +
                                "todo \n          " +
                                "deadline \n          " +
                                "event\n          " +
                                "list\n          " +
                                "delete\n          " +
                                "bye\n          " +
                                "\n          " +
                                "Please key in in this format:\n          " +
                                "todo ... / deadline ... ");
                    }
                } else {
                    textPart = inputData.trim();
                    keyWord = textPart;
                }

                // I am using a switch and case method to deal with the user input
                // this is because the user can key in list / bye / mark / unmark / any other input which
                // the scanner will read.
                switch (keyWord) {
                    case "bye":
                        break;

                    case "mark":
                        arrayList.get(numberPart - 1).markAsDone();
                        System.out.println("          ____________________________________\n          Nice! I've marked this task as done:");
                        System.out.println("             " + arrayList.get(numberPart - 1).finalString());
                        System.out.println("          ____________________________________\n");
                        break;

                    case "unmark":
                        arrayList.get(numberPart - 1).markAsUndone();
                        System.out.println("          ____________________________________\n          OK, I've marked this task as not done yet:");
                        System.out.println("             " + arrayList.get(numberPart - 1).finalString());
                        System.out.println("          ____________________________________\n");
                        break;

                    case "delete":
                        Task task1 = arrayList.remove(numberPart - 1);
                        System.out.println("          ____________________________________\n          Noted. I've removed this task:\n"
                                + "             " + task1.finalString() + "\n"
                                + "          Now you have " + arrayList.size() + " " + task + " in the list.\n"
                                + "          ____________________________________");
                        break;
                }
                // if the user keys in "bye" break out of the while loop and print out the conclusion.
                if (Objects.equals(inputData, "bye")) {
                    break;
                }
            }
            catch (BitBotException e) {
                System.out.println("          ____________________________________");
                System.out.println("          " + e.getMessage());
                System.out.println("          ____________________________________");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());;
            }
        }

        System.out.println(conclusion);


    }
}