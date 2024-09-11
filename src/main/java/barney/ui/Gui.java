package barney.ui;

import barney.data.TaskList;
import barney.data.task.Task;

/**
 * Represents the user interface of the application.
 */
public class Gui extends Ui {
    /**
     * Constructs a new GUI object.
     */
    public Gui() {
    }

    /**
     * Abstract method to print a long line separator.
     *
     * @inheritDoc
     */
    @Override
    public String printLongLine() {
        return super.longLineString();
    }

    /**
     * Prints the welcome message.
     *
     * @inheritDoc
     */
    @Override
    public String printWelcome() {
        return super.welcomeString();
    }

    /**
     * Prints the message after loading data.
     *
     * @inheritDoc
     */
    @Override
    public String printLoadData(TaskList tasks) {
        return super.loadDataString(tasks);
    }

    /**
     * Prints the input prompt.
     *
     * @inheritDoc
     */
    @Override
    public String printInput() {
        return super.inputString();
    }

    /**
     * Prints the bye message.
     *
     * @inheritDoc
     */
    @Override
    public String printBye() {
        return super.byeString();
    }

    /**
     * Prints the loading error message.
     *
     * @inheritDoc
     */
    @Override
    public String printLoadingError(String message) {
        return super.loadingErrorString(message);
    }

    /**
     * Prints the chat error message.
     *
     * @inheritDoc
     */
    @Override
    public String printChatError(String message) {
        return super.chatErrorString(message);
    }

    /**
     * Prints the save error message.
     *
     * @inheritDoc
     */
    @Override
    public String printSaveError(String message) {
        return super.saveErrorString(message);
    }

    /**
     * Prints the list of tasks.
     *
     * @inheritDoc
     */
    @Override
    public String printList(TaskList tasks) {
        return super.listString(tasks);
    }

    /**
     * Prints the marked task.
     *
     * @inheritDoc
     */
    @Override
    public String printMarkedTask(Task task) {
        return super.markedTaskString(task);
    }

    /**
     * Prints the unmarked task.
     *
     * @inheritDoc
     */
    @Override
    public String printUnmarkedTask(Task task) {
        return super.unmarkedTaskString(task);
    }

    /**
     * Prints the tagged task.
     *
     * @inheritDoc
     */
    @Override
    public String printTaggedTask(Task task) {
        return super.taggedTaskString(task);
    }

    /**
     * Prints the added task.
     *
     * @inheritDoc
     */
    @Override
    public String printAddedTask(Task task, int size) {
        return super.addedTaskString(task, size);
    }

    /**
     * Prints the deleted task.
     *
     * @inheritDoc
     */
    @Override
    public String printDeleteTask(Task task, int size) {
        return super.deleteTaskString(task, size);
    }

    /**
     * Prints the found tasks.
     *
     * @inheritDoc
     */
    @Override
    public String printMatchingTasks(TaskList tasks) {
        return super.matchingTasksString(tasks);
    }

    /**
     * Prints the invalid message.
     *
     * @inheritDoc
     */
    @Override
    public String printInvalidCommand() {
        return super.invalidCommandString();
    }
}
