package meerkatpack;

import java.util.List;

/**
 * Used to print out any statements to display messages.
 */
public class Ui {

    private static final String LINES = "____________________________________________________________";

    /**
     * Prints lists of tasks in current taskList.
     * @param taskList Provides the taskList with tasks to be printed.
     */
    public void printList(TaskList taskList) {
        System.out.println(LINES);
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < taskList.getSize() + 1; i++) {
            System.out.println(i + "." + taskList.getTaskList().get(i - 1).toString());
        }
        System.out.println(LINES);
    }

    /**
     * Prints message to indicate task created.
     * @param thisTask Used to get task name.
     * @param taskList Used to get taskList size.
     */
    public void printTaskCreationMessage(Task thisTask, TaskList taskList) {
        System.out.println(LINES + "\nGot it. I've added this task:\n  " + thisTask + "\nNow you have "
                + taskList.getSize() + " tasks in the list\n" + LINES);
    }

    /**
     * Prints message to indicate task marked.
     * @param thisTask Used to get task name.
     */
    public void printTaskMarkedMessage(Task thisTask) {
        System.out.println(LINES + "\nNice! I've marked this task as done:\n" + thisTask + "\n" + LINES);
    }

    /**
     * Prints message to indicate task unmarked.
     * @param thisTask Used to get task name.
     */
    public void printTaskUnmarkedMessage(Task thisTask) {
        System.out.println(LINES + "\nOK, I've marked this task as not done yet:\n" + thisTask + "\n" + LINES);
    }

    /**
     * Prints message to indicate that task cannot be marked.
     */
    public void printTaskNonMarkableMessage() {
        System.out.println(LINES + "\nThis task does not exist! Unable to mark.\n" + LINES);
    }

    /**
     * Prints message to indicate that more information is required to create Todo task.
     */
    public void printNeedMoreInfoTodoMessage() {
        System.out.println(LINES + "\nbruh. i need more info to create your todo task.\n" + LINES);
    }

    /**
     * Prints message to indicate that more information is required to create Deadline task.
     */
    public void printNeedMoreInfoDeadlineMessage() {
        System.out.println(LINES + "\nbruh. i need more info to create your deadline task.\n" + LINES);
    }

    /**
     * Prints message to indicate that more information is required to create Event task.
     */
    public void printNeedMoreInfoEventMessage() {
        System.out.println(LINES + "\nbruh. i need more info to create your event task.\n" + LINES);
    }

    /**
     * Prints message to greet user.
     */
    public void printGreetingMessage() {
        System.out.println(LINES + "\n" + """
                Hello! I'm a meerkat
                What can I do for you?
                """ + LINES);
    }

    /**
     * Prints message to bid farewell to user.
     */
    public void printGoodbyeMessage() {
        System.out.println(LINES + "\n" + """
                Goodnight, sleep tight, Hope I don't ever see you again!
                """ + LINES);
    }

    /**
     * Prints message to indicate that task cannot be unmarked.
     */
    public void printTaskNonUnmarkableMessage() {
        System.out.println(LINES + "\nThis task does not exist! Unable to unmark.\n" + LINES);
    }

    /**
     * Prints message to indicate that task has been deleted.
     */
    public void printDeleteMessage(Task thisTask) {
        System.out.println(LINES + "\nroger that sir, I've removed this task:\n" + thisTask + "\n" + LINES);
    }

    /**
     * Prints message to indicate that task cannot be deleted.
     */
    public void printUndeletableMessage() {
        System.out.println(LINES + "\nThis task does not exist! Unable to delete.\n" + LINES);
    }

    /**
     * Prints message to indicate that their input cannot be parsed, no keywords.
     */
    public void printNoIdeaMessage() {
        System.out.println(LINES + "\ni have nooo idea what you are sayin\n" + LINES);
    }

    /**
     * Prints message to indicate that file to read from does not exist.
     */
    public void printFileNonexistentMessage() {
        System.out.println(LINES + "\nsave file does not exist\n" + LINES);
    }

    /**
     * Prints message to indicate that there was an error in writing to file.
     */
    public void printErrorWritingFileMessage() {
        System.out.println(LINES + "\nerror in writing to file\n" + LINES);
    }

    /**
     * Prints list of tasks in given param, which is created based on search keyword.
     * @param listOfTasks TaskList that contains matching tasks based on search keyword.
     */
    public void printMatchingTasks(List<Task> listOfTasks) {
        System.out.println(LINES);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i < (listOfTasks.size() + 1); i++) {
            System.out.println(i + "." + listOfTasks.get(i - 1).toString());
        }
        System.out.println(LINES);
    }
}
