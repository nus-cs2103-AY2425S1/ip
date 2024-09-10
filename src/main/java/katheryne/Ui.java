package katheryne;

import java.util.Scanner;

/**
 * Ui Class is to handle the interaction with user and response generation
 */
public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String getLine() {
        return sc.nextLine();
    }

    public String getCommand(String str) {
        return Parser.getCommandWord(str);
    }

    public String getGreeting() {
        return Message.MESSAGE_GREETING;
    }

    public String getBye() {
        return Message.MESSAGE_GOODBYE;
    }

    public String getListMessage() {
        return Message.MESSAGE_LIST;
    }

    public String getMarkMessage(Task t) {
        return String.format(Message.MESSAGE_MARK, t.toString());
    }

    public String getUnmarkMessage(Task t) {
        return String.format(Message.MESSAGE_UNMARK, t.toString());
    }

    public String getAddMessage(Task t, TaskList l) {
        return String.format(Message.MESSAGE_ADD, t.toString(), l.getTaskList().size());
    }

    public String getDeleteMessage(Task t, TaskList l) {
        return String.format(Message.MESSAGE_DELETE, t.toString(), l.getTaskList().size());
    }

    public String getDivide() {
        return Message.DIVIDE;
    }

    public void showLoadingError() {
        System.out.println(Message.MESSAGE_LOADING_ERROR);
    }

    public String getDateFormatErrorMessage() {
        return Message.MESSAGE_DATE_FORMAT_ERROR;
    }

    public String getFindResult(TaskList result) {
        if (result.getTaskList().isEmpty()) {
            return Message.MESSAGE_TASK_NOT_FOUND;
        } else {
            return Message.MESSAGE_FIND_RESULT + '\n' + result.toString();
        }
    }


}
