package meerkatpack;

public class Ui {

    private String lines = "____________________________________________________________";
    private static String greeting = """
                Hello! I'm a meerkat
                What can I do for you?
                """;
    private static String bye = """
                Goodnight, sleep tight, Hope I don't ever see you again!
                """;

    public void printList(TaskList taskList) {
        System.out.println(lines);
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < taskList.getSize() + 1; i++) {
            System.out.println(i + "." + taskList.getTaskList().get(i-1).toString());
        }
        System.out.println(lines);
    }

    public void printTaskCreationMessage(Task thisTask, TaskList taskList) {
        System.out.println(lines + "\nGot it. I've added this task:\n  " + thisTask + "\nNow you have " + taskList.getSize() + " tasks in the list\n" + lines);
    }

    public void printTaskMarkedMessage(Task thisTask) {
        System.out.println(lines + "\nNice! I've marked this task as done:\n" + thisTask + "\n" + lines);
    }

    public void printTaskUnmarkedMessage(Task thisTask) {
        System.out.println(lines + "\nOK, I've marked this task as not done yet:\n" + thisTask + "\n" + lines);
    }

    public void printTaskNonMarkableMessage() {
        System.out.println(lines + "\nThis task does not exist! Unable to mark.\n" + lines);
    }

    public void printNeedMoreInfoTodoMessage() {
        System.out.println(lines + "\nbruh. i need more info to create your todo task.\n" + lines);
    }

    public void printNeedMoreInfoDeadlineMessage() {
        System.out.println(lines + "\nbruh. i need more info to create your deadline task.\n" + lines);
    }

    public void printNeedMoreInfoEventMessage() {
        System.out.println(lines + "\nbruh. i need more info to create your event task.\n" + lines);
    }

    public void printGreetingMessage() {
        System.out.println(lines + "\n" + greeting + lines);
    }

    public void printGoodbyeMessage() {
        System.out.println(lines + "\n" + bye + lines);
    }

    public void printTaskNonUnmarkableMessage() {
        System.out.println(lines + "\nThis task does not exist! Unable to unmark.\n" + lines);
    }

    public void printDeleteMessage(Task thisTask) {
        System.out.println(lines + "\nroger that sir, I've removed this task:\n" + thisTask + "\n" + lines);
    }

    public void printUndeletableMessage() {
        System.out.println(lines + "\nThis task does not exist! Unable to delete.\n" + lines);
    }

    public void printNoIdeaMessage() {
        System.out.println(lines + "\ni have nooo idea what you are sayin\n" + lines);
    }

    public void printFileNonexistentMessage() {
        System.out.println(lines + "\nsave file does not exist\n" + lines);
    }

    public void printErrorWritingFileMessage() {
        System.out.println(lines + "\nerror in writing to file\n" + lines);
    }

}
