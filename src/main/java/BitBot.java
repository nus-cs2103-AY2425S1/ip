import javax.crypto.spec.PSource;
import java.util.*;


/**
 * The class Task represents the different tasks that can be done.
 * It is the parent class of Todo, Deadline, Event classes.
 * Adapted from the partial solution in the question
 */
class Task {
    protected String taskDescription;
    protected boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public String finalString() {
        return isDone
                ? "[X] " + taskDescription
                : "[ ] " + taskDescription;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

}

/**
 * Adapted from the partial solution in the question
 */
class Deadline extends Task {

    protected String by;

    public Deadline (String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String finalString() {
        return "[D]" + super.finalString() + " (by: " + by + ")";
    }
}

class ToDos extends Task {

    public ToDos (String description) {
        super(description);
    }

    @Override
    public String finalString() {
        return "[T]" + super.finalString();
    }
}

class Events extends Task {

    protected String from, to;

    public Events (String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String finalString() {
        return "[E]" + super.finalString() + " (from: " + from + " to: " + to + ")";
    }
}

public class BitBot {

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
                    "          For example: \"event return book /from Mon 4pm /to 6pm\"");

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

        Task event = new Events(textPart, from.toString().trim(), to.toString().trim());
        arrayList.add(event);
        System.out.println("          ____________________________________\n          Got it. I've added this task:\n"
                + "             " + event.finalString() + "\n"
                + "          Now you have " + arrayList.size() + " " + task + " in the list.\n"
                + "          ____________________________________");
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

        Task deadline = new Deadline(textPart, by.toString().trim());
        arrayList.add(deadline);
        System.out.println("          ____________________________________\n          Got it. I've added this task:\n"
                + "             " + deadline.finalString() + "\n"
                + "          Now you have " + arrayList.size() + " " + task + " in the list.\n"
                + "          ____________________________________");
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

        public static void main(String[] args) throws BitBotException {

        ArrayList<Task> arrayList = new ArrayList<>();

        String inputData;
        String intro = "          ____________________________________\n          Hello! I'm BitBot\n          What can I do for you?\n          ____________________________________";
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
                    } else if (Objects.equals(keyWord, "bye")) {
                        //do nothing and move on to switch case below.
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
            }
        }

        System.out.println(conclusion);


    }
}