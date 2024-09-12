package meerkatpack;

import javafx.scene.image.Image;
import taskpack.TaskList;
import taskpack.Task;

import java.util.List;

/**
 * Used to show out any statements to display messages.
 */
public class Ui {

    private static final String LINES = "__________________________________________________";
    private final Image meerkatImage = new Image(this.getClass().getResourceAsStream("/images/meerkat.png"));

    /**
     * Returns the meerkat image
     */
    public Image getMeerkatImage() {
        return this.meerkatImage;
    }

    /**
     * Shows lists of tasks in current taskList.
     * @param taskList Provides the taskList with tasks to be shown.
     */
    public String showList(TaskList taskList) {
        StringBuilder result = new StringBuilder();
        result.append(LINES);
        result.append("\nHere are the tasks in your list\n");
        for (int i = 1; i < (taskList.getSize() + 1); i++) {
            result.append(i).append(".").append(taskList.getTaskList().get(i - 1).toString()).append("\n");
        }
        result.append(LINES);
        return result.toString();
    }

    /**
     * Returns message to indicate task created.
     * @param thisTask Used to get task name.
     * @param taskList Used to get taskList size.
     */
    public String showTaskCreationMessage(Task thisTask, TaskList taskList) {
        return (LINES + "\nGot it. I've added this task:\n  " + thisTask + "\nNow you have "
                + taskList.getSize() + " tasks in the list\n" + LINES);
    }

    /**
     * Shows message to indicate task marked.
     * @param thisTask Used to get task name.
     */
    public String showTaskMarkedMessage(Task thisTask) {
        return (LINES + "\nNice! I've marked this task as done:\n" + thisTask + "\n" + LINES);
    }

    /**
     * Shows message to indicate task unmarked.
     *
     * @param thisTask Used to get task name.
     * @return
     */
    public String showTaskUnmarkedMessage(Task thisTask) {
        return (LINES + "\nOK, I've marked this task as not done yet:\n" + thisTask + "\n" + LINES);
    }

    /**
     * Shows message to indicate that task cannot be marked.
     */
    public String showTaskNonMarkableMessage() {
        return (LINES + "\nThis task does not exist! Unable to mark.\n" + LINES);
    }

    /**
     * Shows message to indicate that more information is required to create Todo task.
     */
    public String showNeedMoreInfoTodoMessage() {
        return (LINES + "\nbruh. i need more info to create your todo task.\n" + LINES);
    }

    /**
     * Shows message to indicate that more information is required to create Deadline task.
     */
    public String showNeedMoreInfoDeadlineMessage() {
        return (LINES + "\nbruh. i need more info to create your deadline task.\n" + LINES);
    }

    /**
     * Shows message to indicate that more information is required to create Event task.
     */
    public String showNeedMoreInfoEventMessage() {
        return (LINES + "\nbruh. i need more info to create your event task.\n" + LINES);
    }

    /**
     * Shows message to greet user.
     */
    public String showGreetingMessage() {
        return (LINES + "\n" + """
                Hello! I'm a meerkat
                What can I do for you?
                """ + LINES);
    }

    /**
     * Shows message to bid farewell to user.
     */
    public String showGoodbyeMessage() {
        return (LINES + "\n" + """
                Goodnight, sleep tight, Hope I don't ever see you again!
                """ + LINES);
    }

    /**
     * Shows message to indicate that task cannot be unmarked.
     */
    public String showTaskNonUnmarkableMessage() {
        return (LINES + "\nThis task does not exist! Unable to unmark.\n" + LINES);
    }

    /**
     * Shows message to indicate that task has been deleted.
     */
    public String showDeleteMessage(Task thisTask) {
        return (LINES + "\nroger that sir, I've removed this task:\n" + thisTask + "\n" + LINES);
    }

    /**
     * Shows message to indicate that task cannot be deleted.
     */
    public String showUndeletableMessage() {
        return (LINES + "\nThis task does not exist! Unable to delete.\n" + LINES);
    }

    /**
     * Shows message to indicate that their input cannot be parsed, no keywords.
     */
    public String showNoIdeaMessage() {
        return (LINES + "\ni have nooo idea what you are sayin\n" + LINES);
    }

    /**
     * Shows message to indicate that file to read from does not exist.
     */
    public String showFileNonexistentMessage() {
        return (LINES + "\nsave file does not exist\n" + LINES);
    }

    /**
     * Shows message to indicate that there was an error in writing to file.
     */
    public String showErrorWritingFileMessage() {
        return (LINES + "\nerror in writing to file\n" + LINES);
    }

    /**
     * Shows list of tasks in given param, which is created based on search keyword.
     * @param listOfTasks TaskList that contains matching tasks based on search keyword.
     */
    public String showMatchingTasks(List<Task> listOfTasks) {
        StringBuilder result = new StringBuilder();
        result.append(LINES);
        result.append("\nHere are the matching tasks in your list:\n");
        for (int i = 1; i < (listOfTasks.size() + 1); i++) {
            result.append(i).append(".").append(listOfTasks.get(i - 1).toString()).append("\n");
        }
        result.append(LINES);
        return result.toString();
    }
}
