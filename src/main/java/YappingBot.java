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
    private static final String SELECT_TASK_NOT_INT_TEXT = "I'm sorry, I do not understand which item '%s' refers to!";
    private static final String SELECT_TASK_MISSING_TEXT = "I'm sorry, but task number %d does not exist!";
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
            System.out.println(quoteSinglelineText("List is empty!\n"));
            return;
        }

        StringBuilder sb = new StringBuilder();
        quoteSinglelineText(LIST_TEXT, sb);
        for (int i = 0; i < userList.size(); i++) {
            Task t = userList.get(i);
            quoteSinglelineText(
                    String.format("%2d.[%s] %s", i+1, t.getTaskDoneCheckmark(), t.getTaskName()),
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
            System.out.println(quoteSinglelineText(String.format(SELECT_TASK_NOT_INT_TEXT, userInputSlice)));
            return i;
        }

        // OOB
        if (i < 0 || i >= userList.size()) {
            System.out.println(quoteSinglelineText(String.format(SELECT_TASK_MISSING_TEXT, i+1)));
            i = -1;
        }

        return i;
    }
    private static void changeTaskListStatus(int i, boolean isTaskDone) {
        userList.get(i).setTaskDone(isTaskDone);
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
               case "":
                   break; // ignore multiple enters
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
               default:
                   userList.add(new Task(userInput, false));
                   System.out.println(quoteMultilineText(String.format("added: %s", userInput)));
                   break; // sanity break
           }
        }

        // exit
        System.out.println(quoteMultilineText(EXIT_TEXT));
    }
}
