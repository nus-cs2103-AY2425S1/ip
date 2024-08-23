import java.util.Scanner;
public class Shnoop {
    private boolean completion = false;
    private String mode;
    private Task[] tasks;
    private int arrPointer;
    private String[] quotes;
    public Shnoop() {
    }

    /**
     * Configures class based on desired mode.
     *
     * @param input String to indicate mode.
     */
    public Shnoop(String input) {
        if (input == "todo") {
            mode = "todo";
            tasks = new Task[100];
            arrPointer = 0;
            quotes = new String[] {
                    "You're unforgettable.",
                    "Coded, tanned, fit and ready.",
                    "You're undeniable."
            };
        } else {
            mode = "echo";
        }
    }

    public void addTask(Task task) {
        tasks[arrPointer++] = task;
    }

    /**
     * Determines if a String can be converted into an Integer
     *
     * @param str String to be converted
     * @return True if String is an Integer
     */
    public boolean canBeInteger(String str) {
        if (str == null || str == "") {
            return false;
        }

        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Converts String to Integer if it can be converted.
     *
     * @param str String to be converted
     * @return Integer form of String, otherwise, arbitrary Integer Maximum Value
     */
    public int convertStrToInteger(String str) {
        if (canBeInteger(str)) {
            return Integer.parseInt(str);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    /**
     * Does the required code for a mark or unmark CLI input.
     *
     * @param input Given by the user.
     * @return String indicating the action that was done.
     */
    public String parseInputMark(String input) {
        if (input.length() >= 6 && input.substring(0, 5).equals("mark ")) {
            if (canBeInteger(input.substring(5, input.length()))) {
                boolean result = tasks[convertStrToInteger(input.substring(5, input.length())) - 1].markTask();
                if (result) {
                    System.out.println("✿ Shnoop ✿: Warm, wet and wild! I've marked this task as done: ");
                } else {
                    System.out.println("✿ Shnoop ✿: Daisy dukes! This task was already done my love: ");
                }
                System.out.println(tasks[convertStrToInteger(input.substring(5, input.length())) - 1]
                        .getTaskWithStatus());
                return "mark_task";
            }
        } else if (input.length() >= 8 && input.substring(0, 7).equals("unmark ")) {
            if (canBeInteger(input.substring(7, input.length()))) {
                boolean result = tasks[convertStrToInteger(input.substring(7, input.length())) - 1].unmarkTask();
                if (result) {
                    System.out.println("✿ Shnoop ✿: Melted this popsicle! I've unmarked this task as done: ");
                } else {
                    System.out.println("✿ Shnoop ✿: Daisy dukes! This task was never done my love: ");
                }
                System.out.println(tasks[convertStrToInteger(input.substring(7, input.length())) - 1]
                        .getTaskWithStatus());
                return "mark_task";
            }
        }

        return "not_mark_or_unmark";
    }

    /**
     * Returns a quote from the quote bank.
     *
     * @param idx Should be a changing number.
     * @return Quote from quote bank.
     */
    public String getRandomQuote(int idx) {
        return quotes[idx];
    }

    /**
     * Determines if String indicates a Todo task.
     *
     * @param str String to be checked.
     * @param length Length of string (to avoid repeated str.length() calls).
     * @return True if String starts with "Todo "
     */
    public boolean startsWithTodo(String str, int length) {
        return (length >= 5 && str.toLowerCase().startsWith("todo "));
    }

    /**
     * Determines if String indicates a Event task.
     *
     * @param str String to be checked.
     * @param length Length of string (to avoid repeated str.length() calls).
     * @return True if String starts with "Event "
     */
    public boolean startsWithEvent(String str, int length) {
        return (length >= 6 && str.toLowerCase().startsWith("event "));
    }

    /**
     * Determines if String indicates a Deadline task.
     *
     * @param str String to be checked.
     * @param length Length of string (to avoid repeated str.length() calls).
     * @return True if String starts with "Deadline "
     */
    public boolean startsWithDeadline(String str, int length) {
        return (length >= 9 && str.toLowerCase().startsWith("deadline "));
    }

    public String getTaskType(String str) {
        int length = str.length();
        if (startsWithTodo(str, length)) {
            return "todo_task";
        } else if (startsWithEvent(str, length)) {
            return "event_task";
        } else if (startsWithDeadline(str, length)) {
            return "deadline_task";
        } else {
            return "undefined_task";
        }
    }

    /**
     * Returns the relevant string excluding Todo, Event, or Deadline tag.
     *
     * @param str User input to sieve through.
     * @return String excluding task type.
     */
    public String getTaskDetails(String str) {
        String taskType = getTaskType(str);
        int length = str.length();

        switch (taskType) {
        case ("todo_task"):
            return str.substring(5, length);
        case ("event_task"):
            return str.substring(6, length);
        case ("deadline_task"):
            return str.substring(9, length);
        default:
            return str;
        }
    }

    /**
     * Returns String representing action done.
     * Performs a series of actions depending on input and mode.
     *
     * @param input Input given by user.
     * @return String action code.
     */
    public String parseInput(String input) throws UndefinedTaskException, IncompleteEventOrDeadlineException,
            EmptyDescriptionException, UnmarkableArrayException {
        switch (mode) {

        // For Level-1 echo mode.
        case "echo":
            switch (input) {
            case "bye":
                System.out.println("\n✿ Shnoop ✿: I'll check ya later, cause you represent. Don't worry we got it on lock. ♡");
                completion = true;
                return "exit";
            default:
                System.out.println("\n✿ Shnoop ✿: " + input);
                return "echo";
            }

        // For Level-2 Add, List mode.
        case "todo":

            // For mark specific
            try {
                String parseInputMarkResult = parseInputMark(input);
                if (parseInputMarkResult != "not_mark_or_unmark") {
                    return parseInputMarkResult; // Exit if it is mark or unmark command
                }
            } catch (NullPointerException | ArrayIndexOutOfBoundsException npe) {
                throw new UnmarkableArrayException();
            }

            // Other than mark or unmark
            switch (input) {

            case "bye":
                System.out.println("\n✿ Shnoop ✿: I'll check ya later, cause you represent. Don't worry we got it on lock. ♡");
                completion = true;
                return "exit";

            case "list":
                System.out.println("✿ Shnoop ✿: Find, fresh, fierce and ready.");
                for (int i = 0; i < tasks.length; i ++) {
                    if (tasks[i] == null) {
                        break;
                    }
                    System.out.println((i + 1) + ". " + tasks[i].toString());
                }
                return "list";

            default:
                String x = getRandomQuote(arrPointer % 3);

                String taskType = getTaskType(input);
                String taskDesc = getTaskDetails(input);
                Task newTask;

                try {
                    switch (taskType) {
                    case ("todo_task"):
                        newTask = new Todo(taskDesc);
                        break;
                    case ("event_task"):
                        newTask = new Event(taskDesc);
                        break;
                    case ("deadline_task"):
                        newTask = new Deadline(taskDesc);
                        break;
                    default:
                        throw new UndefinedTaskException();
                    }

                    addTask(newTask);
                    System.out.println("✿ Shnoop ✿: " + x + " I'll add that in for ya. \nTask Added: " + newTask);
                    System.out.println("✿ Shnoop ✿: You've got " + arrPointer + " doggy-dogs on the stereo.");
                    return "add_task";
                } catch (EmptyDescriptionException e) {
                    throw new EmptyDescriptionException();
                } catch (IncompleteEventOrDeadlineException f) {
                    throw new IncompleteEventOrDeadlineException();
                } catch (UndefinedTaskException g) {
                    throw new UndefinedTaskException();
                }

            }

        // If mode is NIL, indicate bug
        default:
            return "empty_input_bug";
        }


    }

    /**
     * Prints out introductory speech at start of application.
     */
    public void startIntroSpeech() {
        System.out.println("\n ... Greetings loved one ʚ♡ɞ Let's take a journey ... \n\n\n ✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-"
                + "✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿ \n"
                + " ✿ It's Shnoop, my dawg. I'm all up on ya. Whatchu need? ✿ \n"
                + " ✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿ \n");
    }

    public boolean isCompleted() {
        return completion;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        Shnoop shnoop = new Shnoop("todo");

        shnoop.startIntroSpeech();

        while (!shnoop.isCompleted()) {
            input = scanner.nextLine();
            try {
                String result = shnoop.parseInput(input);
            } catch (UndefinedTaskException | IncompleteEventOrDeadlineException | EmptyDescriptionException
                    | UnmarkableArrayException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
