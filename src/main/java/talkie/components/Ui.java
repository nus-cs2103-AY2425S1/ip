import talkie.exception.TalkieException;
import talkie.task.Task;
import talkie.task.TaskList;

import java.util.Scanner;

public class Ui {

    public enum MessageType {
        HORIZONTAL_LINE("-------------------------------------------------------------------"),
        WELCOME_MESSAGE(HORIZONTAL_LINE.message + "\n"
                + "Hello! I'm Talkie, your friendly ChatBot.\n"
                + "What can I do for you?\n"
                + HORIZONTAL_LINE.message + "\n"),
        BYE_MESSAGE(HORIZONTAL_LINE.message + "\n"
                + "Bye. Hope to see you again soon!\n"
                + HORIZONTAL_LINE.message + "\n");

        private final String message;

        MessageType(String message) {
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }
    }

    private Scanner input = new Scanner(System.in);

    public String readCommand() {
        return this.input.nextLine();
    }

    public void closeInput() {
        this.input.close();
    }

    public void welcomeMessage() {
        System.out.println(MessageType.WELCOME_MESSAGE.getMessage());
    }

    public void byeMessage() {
        System.out.println(MessageType.BYE_MESSAGE.getMessage());
    }

    public void wrongDateTimeFormatMessage() {
        System.out.println(MessageType.HORIZONTAL_LINE.getMessage() + "\n"
                + "Please enter the time in the format of <yyyy-MM-dd HHmm>!\n"
                + MessageType.HORIZONTAL_LINE.getMessage() + "\n");
    }

    public void addMessage(Task t, int taskListSize) {
        String taskWord = (taskListSize > 1) ? "tasks" : "task";
        String finalMessage = MessageType.HORIZONTAL_LINE.getMessage() + "\n"
                + "Got it. I've added this task:\n"
                + "  " + t + "\n"
                + "Now you have " + taskListSize + " " + taskWord + " in the list.\n"
                + MessageType.HORIZONTAL_LINE.getMessage() + "\n";
        System.out.println(finalMessage);
    }

    public void deleteMessage(Task t, int taskListSize) {
        String taskWord = (taskListSize > 1) ? "tasks" : "task";
        String doneMessage = MessageType.HORIZONTAL_LINE.getMessage() + "\n"
                + "Noted! I've removed this task:\n"
                + "  " + t + "\n"
                + "Now you have " + taskListSize + " " + taskWord + " in the list.\n"
                + MessageType.HORIZONTAL_LINE.getMessage() + "\n";
        System.out.println(doneMessage);
    }

    public void listTasks(TaskList tasks) {
        String listMessage = "";
        for (int i = 1; i <= tasks.size(); i++) {
            Task currTask = tasks.getTask(i );
            String description = (i) + ". " + currTask + "\n";
            listMessage += description;
        }

        String finalMessage = MessageType.HORIZONTAL_LINE.getMessage() + "\n"
                + "Here are the tasks in your list:\n"
                +  listMessage
                + MessageType.HORIZONTAL_LINE.getMessage() + "\n";
        System.out.println(finalMessage);
    }

    public void markMessage(Task task) {
        String doneMessage = MessageType.HORIZONTAL_LINE.getMessage() + "\n"
                + "Nice! I've marked this task as done:\n"
                + " " + task + "\n"
                + MessageType.HORIZONTAL_LINE.getMessage() + "\n";
        System.out.println(doneMessage);
    }

    public void unMarkMessage(Task task) {
        String undoneMessage = MessageType.HORIZONTAL_LINE.getMessage() + "\n"
                + "OK, I've marked this task as not done yet:\n"
                + " " + task + "\n"
                + MessageType.HORIZONTAL_LINE.getMessage() + "\n";
        System.out.println(undoneMessage);
    }

    public void showTalkieException(TalkieException e) {
        System.out.println(String.format("%s\n", e));
    }
}
