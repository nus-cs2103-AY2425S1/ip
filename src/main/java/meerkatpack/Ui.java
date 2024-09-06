package meerkatpack;

public class Ui {

    private String LINES = "____________________________________________________________";

    public void printList(TaskList taskList) {
        System.out.println(LINES);
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < taskList.getSize() + 1; i++) {
            System.out.println(i + "." + taskList.getTaskList().get(i-1).toString());
        }
        System.out.println(LINES);
    }

    public void printTaskCreationMessage(Task thisTask, TaskList taskList) {
        System.out.println(LINES + "\nGot it. I've added this task:\n  " + thisTask + "\nNow you have "
                + taskList.getSize() + " tasks in the list\n" + LINES);
    }

    public void printTaskMarkedMessage(Task thisTask) {
        System.out.println(LINES + "\nNice! I've marked this task as done:\n" + thisTask + "\n" + LINES);
    }

    public void printTaskUnmarkedMessage(Task thisTask) {
        System.out.println(LINES + "\nOK, I've marked this task as not done yet:\n" + thisTask + "\n" + LINES);
    }

    public void printTaskNonMarkableMessage() {
        System.out.println(LINES + "\nThis task does not exist! Unable to mark.\n" + LINES);
    }

    public void printNeedMoreInfoTodoMessage() {
        System.out.println(LINES + "\nbruh. i need more info to create your todo task.\n" + LINES);
    }

    public void printNeedMoreInfoDeadlineMessage() {
        System.out.println(LINES + "\nbruh. i need more info to create your deadline task.\n" + LINES);
    }

    public void printNeedMoreInfoEventMessage() {
        System.out.println(LINES + "\nbruh. i need more info to create your event task.\n" + LINES);
    }

    public void printGreetingMessage() {
        System.out.println(LINES + "\n" + """
                Hello! I'm a meerkat
                What can I do for you?
                """ + LINES);
    }

    public void printGoodbyeMessage() {
        System.out.println(LINES + "\n" + """
                Goodnight, sleep tight, Hope I don't ever see you again!
                """ + LINES);
    }

    public void printTaskNonUnmarkableMessage() {
        System.out.println(LINES + "\nThis task does not exist! Unable to unmark.\n" + LINES);
    }

    public void printDeleteMessage(Task thisTask) {
        System.out.println(LINES + "\nroger that sir, I've removed this task:\n" + thisTask + "\n" + LINES);
    }

    public void printUndeletableMessage() {
        System.out.println(LINES + "\nThis task does not exist! Unable to delete.\n" + LINES);
    }

    public void printNoIdeaMessage() {
        System.out.println(LINES + "\ni have nooo idea what you are sayin\n" + LINES);
    }

    public void printFileNonexistentMessage() {
        System.out.println(LINES + "\nsave file does not exist\n" + LINES);
    }

    public void printErrorWritingFileMessage() {
        System.out.println(LINES + "\nerror in writing to file\n" + LINES);
    }

}
