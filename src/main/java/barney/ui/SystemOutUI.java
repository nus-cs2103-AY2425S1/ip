package barney.ui;

import barney.data.TaskList;
import barney.data.task.Task;

/**
 * Represents the user interface of the application.
 */
public class SystemOutUI extends Ui {

    /**
     * Constructs a new SystemOutUI object.
     */
    public SystemOutUI() {
    }

    /**
     * Method to print a long line separator.
     *
     * @inheritDoc
     */
    @Override
    public String printLongLine() {
        System.out.println(super.longLineString());
        return "";
    }

    /**
     * Prints the welcome message.
     *
     * @inheritDoc
     */
    @Override
    public String printWelcome() {
        System.out.println(super.welcomeString());
        printLongLine();
        return "";
    }

    /**
     * Prints the message after loading data.
     *
     * @inheritDoc
     */
    @Override
    public String printLoadData(TaskList tasks) {
        System.out.println(super.loadDataString(tasks));
        printLongLine();
        return "";
    }

    /**
     * Prints the input prompt.
     *
     * @inheritDoc
     */
    @Override
    public String printInput() {
        System.out.print(super.inputString());
        return "";
    }

    /**
     * Prints the bye message.
     *
     * @inheritDoc
     */
    @Override
    public String printBye() {
        System.out.println(super.byeString());
        printLongLine();
        return "";
    }

    /**
     * Prints the loading error message.
     *
     * @inheritDoc
     */
    @Override
    public String printLoadingError(String message) {
        System.out.println(super.loadingErrorString(message));
        printLongLine();
        return "";
    }

    /**
     * Prints the chat error message.
     *
     * @inheritDoc
     */
    @Override
    public String printChatError(String message) {
        System.out.println(super.chatErrorString(message));
        printLongLine();
        return "";
    }

    /**
     * Prints the save error message.
     *
     * @inheritDoc
     */
    @Override
    public String printSaveError(String errorMessage) {
        System.out.println(super.saveErrorString(errorMessage));
        printLongLine();
        return "";
    }

    /**
     * Prints the list of tasks.
     *
     * @inheritDoc
     */
    @Override
    public String printList(TaskList tasks) {
        System.out.println(super.listString(tasks));
        printLongLine();
        return "";
    }

    /**
     * Prints the marked task.
     *
     * @inheritDoc
     */
    @Override
    public String printMarkedTask(Task task) {
        System.out.println(super.markedTaskString(task));
        printLongLine();
        return "";
    }

    /**
     * Prints the unmarked task.
     *
     * @inheritDoc.
     */
    @Override
    public String printUnmarkedTask(Task task) {
        System.out.println(super.unmarkedTaskString(task));
        printLongLine();
        return "";
    }

    /**
     * Prints the tagged task.
     *
     * @inheritDoc
     */
    @Override
    public String printTaggedTask(Task task) {
        System.out.println(super.taggedTaskString(task));
        printLongLine();
        return "";
    }

    /**
     * Prints the added task.
     *
     * @inheritDoc
     */
    @Override
    public String printAddedTask(Task task, int size) {
        System.out.println(super.addedTaskString(task, size));
        printLongLine();
        return "";
    }

    /**
     * Prints the deleted task.
     *
     * @inheritDoc
     */
    @Override
    public String printDeleteTask(Task task, int size) {
        System.out.println(super.deleteTaskString(task, size));
        printLongLine();
        return "";
    }

    /**
     * Prints the tasks that match the keyword.
     *
     * @inheritDoc
     */
    @Override
    public String printMatchingTasks(TaskList tasks) {
        System.out.println(super.matchingTasksString(tasks));
        printLongLine();
        return "";
    }

    /**
     * Prints the invalid command message.
     *
     * @inheritDoc
     */
    @Override
    public String printInvalidCommand() {
        System.out.println(super.invalidCommandString());
        printLongLine();
        return "";
    }
}
