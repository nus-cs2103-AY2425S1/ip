package milo.ui;

import java.util.Scanner;

import milo.tasks.TaskTypes;

/**
 * Milo Ui system
 * sets how Milo interacts with user
 * as well as passing on user input to Parser
 */
public class Ui {
    private final Scanner myScanner = new Scanner(System.in);

    /**
     * Milo greets user
     */
    public String greetUser() {
        String cat0 = """
                  ╱|、
                (˚ˎ 。7 \s
                 |、˜〵         \s
                 じしˍ,)ノ
                """;
        return "Hello! I'm Milo.\nWhat can I do for you?\n" + cat0;
    }

    /**
     * Milo gets user input
     */
    public String getUserInput() {
        return myScanner.nextLine();
    }

    /**
     * Milo says bye to user
     */
    public String byeUser() {
        String cat1 = """
                 ∧,,,∧
                ( ̳• · •̳)
                /    づ♡
                """;
        return "Bye. Hope to see you again soon!\n" + cat1;
    }



    /**
     * Milo prints error message
     *
     * @param taskType type of the task that caused the error
     * @param desc description of the error
     */
    public String printError(TaskTypes.TaskType taskType, String desc) {
        String oops = "OOPS!!! ";
        StringBuilder response = new StringBuilder();
        switch (taskType) {
        case TODO, EVENT, DEADLINE:
            response.append(oops).append(desc).append("\n");
            break;
        case INVALID:
            String invalidMessage = oops + "I'm sorry, but I don't know what that means ;-;\n";
            response.append(invalidMessage);
            break;
        case DATE:
            String invalidDateMessage = oops + "Date is not properly formatted.\nIt should be formatted as: YYYY-MM-DD\n";
            response.append(invalidDateMessage);
            break;
        default:
            String defaultMessage = oops + "I'm sorry, I don't know what that means ;-;\n";
            response.append(defaultMessage);
        }
        return response.toString();
    }
}
