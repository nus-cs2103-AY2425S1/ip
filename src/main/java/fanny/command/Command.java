package fanny.command;

import java.time.format.DateTimeFormatter;

import fanny.task.TaskList;
import fanny.ui.Ui;

/**
 * Represents an abstract command that can be executed with
 * more information available.
 */
public abstract class Command {

    /** The formatter used for parsing and formatting date and time. */
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * An abstract method that executes the command
     * with the given task list and user interface.
     *
     * @param list The task list to be modified by the command.
     * @param ui The user interface to interact with the user.
     */
    public abstract void executeCmd(TaskList list, Ui ui);

    /**
     * An abstract method that indicates whether this command
     * should cause the application to exit.
     *
     * @return {@code true} if the command should cause the application to exit,
     *     {@code false} otherwise.
     */
    public abstract boolean shouldExit();
}
