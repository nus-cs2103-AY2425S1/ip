import java.util.ArrayList;
import java.util.Scanner;

public class YappingBot {
    // Text strings
    private static final String BOT_NAME = "YappingBot";
    private static final String GREETING_TEXT = String.format(
            "Hello! I'm %s\nWhat can I do for you?",
            BOT_NAME
    );
    private static final String LIST_TEXT = "Here are the tasks in your list:";
    private static final String ADDED_TEXT = "Got it. I've added this task:";
    private static final String TASK_PRINT_TEXT_3s = "[%s][%s] %s";
    private static final String LIST_SUMMARY_TEXT_1d = "Now you have %d tasks in the list.";
    private static final String SELECT_TASK_NOT_INT_TEXT_1s = "I'm sorry, I do not understand which item '%s' refers to!";
    private static final String SELECT_TASK_MISSING_TEXT_1d = "I'm sorry, but task number %d does not exist!";
    private static final String MARKED_TASK_AS_DONE_TEXT = "Nice! I've marked this task as done:";
    private static final String UNMARKED_TASK_AS_DONE_TEXT = "OK, I've marked this task as not done:";
    private static final String EXIT_TEXT = "Bye. Hope to see you again soon!";
    // End of text strings

    // class properties
    private static ArrayList<Task> userList;
    private static Scanner userInputScanner;
    // end of class properties

    // class methods
    private static String quoteSinglelineText(String line) {
       return String.format("\n |  %s\n", line);
    }
    private static void quoteSinglelineText(String line, StringBuilder sb) {
        sb.append("\n |  ");
        sb.append(line);
    }
    private static void quoteSinglelineText(String[] line, StringBuilder sb) {
        sb.append("\n |  ");
        for (String l : line) {
            sb.append(l);
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
                                    t.toString()
                            )
                    ),
                    sb
            );
        }
        sb.append("\n");
        System.out.println(sb.toString());
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
                        t.toString()
                ),
                sb
        );
        sb.append("\n");
        System.out.println(sb.toString());
    }
    private static boolean addTaskToList(String[] userInputSpliced, TaskTypes taskTypes) {
        // returns true on success, false on failure
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
                    }
                }
                taskName = sb.toString();
                newTask = new Todo(taskName, false);
                break;
            case DEADLINE:
                // pattern: ^[COMMAND] (titles) /by (date)$
                String deadline = null;
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
                }
                deadline = sb.toString();
                if (deadline == null || taskName == null) {
                    return false;
                }
                newTask = new Deadline(taskName, false, deadline);
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
                }
                if (toTime == null) {
                    toTime = sb.toString();
                } else if (fromTime == null) {
                    fromTime = sb.toString();
                }
                if (fromTime == null || toTime == null || taskName == null) {
                    return false;
                }
                newTask = new Event(taskName, false, fromTime, toTime);
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
                        newTask.toString()),
                sb
        );
        quoteSinglelineText(String.format(LIST_SUMMARY_TEXT_1d, userList.size()), sb);
        System.out.println(sb.toString());
        return true;
    }
    // end of class methods

    public static void main(String[] args) {
        // initialization
        userInputScanner = new Scanner(System.in);
        userList = new ArrayList<>();

        // start
        System.out.println(quoteMultilineText(GREETING_TEXT));

        programmeLoop: // to break out of loop
        while (true) {
           String userInput = userInputScanner.nextLine();
           String[] userInputSlices = userInput.split(" ");
            int taskListIndexPtr = -1; // task list pointer
            switch (userInputSlices[0].toLowerCase()) {
               case "bye":
                   break programmeLoop;
               case "list":
                   printUserList();
                   break;
               case "mark":
                   taskListIndexPtr = parseTaskNumberSelected(userInputSlices[1]);
                   if (taskListIndexPtr < 0) {
                       // System.out.println(quoteSinglelineText(MARK_INSTRUCTION_USAGE));
                   } else {
                       changeTaskListStatus(taskListIndexPtr, true);
                   }
                   break;
               case "unmark":
                   taskListIndexPtr = parseTaskNumberSelected(userInputSlices[1]);
                   if (taskListIndexPtr < 0) {
                       // System.out.println(quoteSinglelineText(UNMARK_INSTRUCTION_USAGE));
                   } else {
                       changeTaskListStatus(taskListIndexPtr, false);
                   }
                   break;
                case "todo":
                    if (!addTaskToList(userInputSlices, TaskTypes.TODO)) {
                        // todo: usage
                    }
                    break;
                case "event":
                    if(!addTaskToList(userInputSlices, TaskTypes.EVENT)) {
                        // todo: usage
                    }
                    break;
                case "deadline":
                    if(!addTaskToList(userInputSlices, TaskTypes.DEADLINE)) {
                        // todo: usage
                    }
                    break;
                default:
                    break; // sanity break
           }
        }

        // exit
        System.out.println(quoteMultilineText(EXIT_TEXT));
    }
}
