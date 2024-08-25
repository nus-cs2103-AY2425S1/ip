import java.util.ArrayList;
import java.util.Scanner;

public class YappingBot {
    // Text strings
    private static final String BOT_NAME = "YappingBot";
    private static final String GREETING_TEXT = String.format(
            "Hello! I'm %s\nWhat can I do for you?",
            BOT_NAME
    );
    private static final String HELP_TEXT = "Available commands: list, mark, unmark, todo, event, deadline.";
    private static final String UNKNOWN_COMMAND_TEXT_1s = "I'm sorry, I do not understand what '%s' is!\n" + HELP_TEXT;
    private static final String LIST_TEXT = "Here are the tasks in your list:";
    private static final String ADDED_TEXT = "Got it. I've added this task:";
    private static final String TODO_USAGE =
            "Here is the usage for the instruction 'todo':\n" +
            "\n    todo TASK_NAME\n\n" +
            "where TASK_NAME is the name of this todo task to add";
    private static final String DEADLINE_USAGE =
            "Here is the usage for the instruction 'deadline':\n" +
            "\n    deadline TASK_NAME /by DEADLINE\n\n" +
            "where TASK_NAME is the name of this deadline task to add\n" +
            "      DEADLINE  is the deadline for this task";
    private static final String EVENT_USAGE =
            "Here is the usage for the instruction 'event':\n" +
            "\n    event TASK_NAME /from START_DATE /to END_DATE\n\n" +
            "where TASK_NAME  is the name of this event task to add\n" +
            "      START_DATE is the start time/date for this event\n" +
            "      END_DATE   is the end time/date for this event";
    private static final String TASK_PRINT_TEXT_3s = "[%s][%s] %s";
    private static final String LIST_SUMMARY_TEXT_1d = "Now you have %d tasks in the list.";
    private static final String SELECT_TASK_NOT_INT_TEXT_1s = "I'm sorry, I do not understand which item '%s' refers to!";
    private static final String SELECT_TASK_MISSING_TEXT_1d = "I'm sorry, but task number %d does not exist!";
    private static final String MARKED_TASK_AS_DONE_TEXT = "Nice! I've marked this task as done:";
    private static final String MARK_INSTRUCTION_USAGE =
            "Here is the usage for the instruction 'unmark':\n" +
            "\n    mark TASK_NUMBER\n\n" +
            "where TASK_NUMBER is the task number in the task list";
    private static final String UNMARKED_TASK_AS_DONE_TEXT = "OK, I've marked this task as not done:";
    private static final String UNMARK_INSTRUCTION_USAGE =
            "Here is the usage for the instruction 'unmark':\n" +
            "\n    unmark TASK_NUMBER\n\n" +
            "where TASK_NUMBER is the task number in the task list";
    private static final String EXIT_TEXT = "Bye. Hope to see you again soon!";
    // End of text strings

    // class properties
    private static ArrayList<Task> userList;
    // end of class properties

    // class methods
    private static String quoteSinglelineText(String line) {
        if (line.trim().isEmpty()) {
            return "\n |";
        } else {
            return String.format("\n |  %s\n", line);
        }
    }
    private static void quoteSinglelineText(String line, StringBuilder sb) {
        if (line.trim().isEmpty()) {
            sb.append("\n |");
        } else {
            sb.append("\n |  ");
            sb.append(line);
        }
    }
    private static String quoteMultilineText(String text) {
        // annotates text with pipe to denote speech from bot
        String[] lines = text.split("\n");
        StringBuilder sb = new StringBuilder();
        for (String l : lines) {
            quoteSinglelineText(l, sb);
        }
        sb.append("\n"); // pad the end with another newline
        return sb.toString();
    }
    private static void printUserList() {
        if (userList.isEmpty()) {
            System.out.println(quoteSinglelineText("List is empty!"));
            return;
        }

        StringBuilder sb = new StringBuilder();
        quoteSinglelineText(LIST_TEXT, sb);
        for (int i = 0; i < userList.size(); i++) {
            Task t = userList.get(i);
            quoteSinglelineText(
                    String.format(
                            "%2d.%s",
                            i+1,
                            String.format(
                                    TASK_PRINT_TEXT_3s,
                                    t.getTaskTypeSymbol(),
                                    t.getTaskDoneCheckmark(),
                                    t
                            )
                    ),
                    sb
            );
        }
        sb.append("\n");
        System.out.println(sb);
    }
    private static int parseTaskNumberSelected(String userInputSlice) {
        int i = -1;
        try {
            i = Integer.parseInt(userInputSlice) - 1;
        } catch (NumberFormatException ex) {
            System.out.println(quoteSinglelineText(String.format(SELECT_TASK_NOT_INT_TEXT_1s, userInputSlice)));
            return i;
        }

        // OOB
        if (i < 0 || i >= userList.size()) {
            System.out.println(quoteSinglelineText(String.format(SELECT_TASK_MISSING_TEXT_1d, i+1)));
            i = -1;
        }

        return i;
    }
    private static void changeTaskListStatus(int i, boolean isTaskDone) {
        Task t = userList.get(i);
        t.setTaskDone(isTaskDone);
        StringBuilder sb = new StringBuilder();
        if (isTaskDone) {
            quoteSinglelineText(MARKED_TASK_AS_DONE_TEXT, sb);
        } else {
            quoteSinglelineText(UNMARKED_TASK_AS_DONE_TEXT, sb);
        }
        quoteSinglelineText(
                String.format(
                        TASK_PRINT_TEXT_3s,
                        t.getTaskTypeSymbol(),
                        t.getTaskDoneCheckmark(),
                        t
                ),
                sb
        );
        sb.append("\n");
        System.out.println(sb);
    }

    // returns true on success, false on failure
    @SuppressWarnings({"BooleanMethodIsAlwaysInverted", "ConstantValue"}) //inversion -> confusion
    private static boolean addTaskToList(String[] userInputSpliced, TaskTypes taskTypes) {
        if (userInputSpliced.length <= 1) {
            return false;
        }
        Task newTask;
        String taskName = null;
        String command = null;
        StringBuilder sb = new StringBuilder();
        switch (taskTypes) {
            case TODO:
                // pattern: ^[COMMAND] ( titles )$
                for (String s : userInputSpliced) {
                    if (command == null) {
                        command = s;
                    } else {
                        sb.append(s);
                        sb.append(" ");
                    }
                }
                taskName = sb.toString();
                newTask = new Todo(taskName.trim(), false);
                break;
            case DEADLINE:
                // pattern: ^[COMMAND] (titles) /by (date)$
                String deadline;
                for (String s : userInputSpliced) {
                    if (command == null) {
                        command = s;
                        continue;
                    } else if (taskName == null && s.equals("/by")) {
                        taskName = sb.toString();
                        sb = new StringBuilder();
                        continue;
                    }
                    sb.append(s);
                    sb.append(" ");
                }
                deadline = sb.toString();
                if (deadline.isEmpty() || taskName == null) {
                    return false;
                }
                newTask = new Deadline(taskName.trim(), false, deadline.trim());
                break;
            case EVENT:
                // pattern: ^[COMMAND] (titles) /from (date) /to ([date])$
                String fromTime = null;
                String toTime = null;
                // todo: regexify this to use capture groups
                for (String s : userInputSpliced) {
                    if (command == null) {
                        command = s;
                        continue;
                    } else if (taskName == null && (s.equals("/from") || s.equals("/to"))) {
                        taskName = sb.toString();
                        sb = new StringBuilder();
                        continue;
                    } else {
                        if (fromTime == null && s.equals("/to")) {
                            fromTime = sb.toString();
                            sb = new StringBuilder();
                            continue;
                        } else if (toTime == null && s.equals("/from")) {
                            toTime = sb.toString();
                            sb = new StringBuilder();
                            continue;
                        }
                    }
                    sb.append(s);
                    sb.append(" ");
                }
                if (toTime == null) {
                    toTime = sb.toString();
                } else if (fromTime == null) {
                    fromTime = sb.toString();
                }
                if (fromTime == null || toTime == null || taskName == null) {
                    return false;
                }
                newTask = new Event(taskName.trim(), false, fromTime.trim(), toTime.trim());
                break;
            default:
                return false;
        }
        userList.add(newTask);
        sb = new StringBuilder();
        quoteSinglelineText(ADDED_TEXT, sb);
        quoteSinglelineText(
                String.format(TASK_PRINT_TEXT_3s, 
                        newTask.getTaskTypeSymbol(),
                        newTask.getTaskDoneCheckmark(),
                        newTask),
                sb
        );
        quoteSinglelineText(String.format(LIST_SUMMARY_TEXT_1d, userList.size()), sb);
        System.out.println(sb);
        return true;
    }
    // end of class methods

    public static void main(String[] args) {
        // initialization
        Scanner userInputScanner = new Scanner(System.in);
        userList = new ArrayList<>();

        // start
        System.out.println(quoteMultilineText(GREETING_TEXT));

        programmeLoop: // to break out of loop
        while (userInputScanner.hasNextLine()) {
           String userInput = userInputScanner.nextLine();
           String[] userInputSlices = userInput.split(" ");
            int taskListIndexPtr; // task list pointer
            switch (userInputSlices[0].toLowerCase().trim()) {
               case "bye":
                   break programmeLoop;
               case "list":
                   printUserList();
                   break;
               case "mark":
                   taskListIndexPtr = parseTaskNumberSelected(userInputSlices[1]);
                   if (taskListIndexPtr < 0) {
                       System.out.println(quoteMultilineText(MARK_INSTRUCTION_USAGE));
                   } else {
                       changeTaskListStatus(taskListIndexPtr, true);
                   }
                   break;
               case "unmark":
                   taskListIndexPtr = parseTaskNumberSelected(userInputSlices[1]);
                   if (taskListIndexPtr < 0) {
                       System.out.println(quoteMultilineText(UNMARK_INSTRUCTION_USAGE));
                   } else {
                       changeTaskListStatus(taskListIndexPtr, false);
                   }
                   break;
                case "todo":
                    if (!addTaskToList(userInputSlices, TaskTypes.TODO)) {
                        System.out.println(quoteMultilineText(TODO_USAGE));
                    }
                    break;
                case "event":
                    if(!addTaskToList(userInputSlices, TaskTypes.EVENT)) {
                        System.out.println(quoteMultilineText(EVENT_USAGE));
                    }
                    break;
                case "deadline":
                    if(!addTaskToList(userInputSlices, TaskTypes.DEADLINE)) {
                        System.out.println(quoteMultilineText(DEADLINE_USAGE));
                    }
                    break;
                case "":
                    System.out.println(quoteSinglelineText(HELP_TEXT));
                    break; // sanity break
                default:
                    System.out.println(quoteMultilineText(
                            String.format(UNKNOWN_COMMAND_TEXT_1s, userInput)
                    ));
                    break; // sanity break
           }
        }

        // exit
        System.out.println(quoteMultilineText(EXIT_TEXT));
    }
}
