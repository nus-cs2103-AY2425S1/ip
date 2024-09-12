package nah.command;

import nah.data.Task;
import nah.exceptions.NahException;
import nah.storage.Storage;
import nah.tasklist.TaskList;
import nah.ui.UI;

/**
 * The Command class serves as an abstract base class for all commands
 * that can be executed in the application. Commands are responsible for
 * interacting with the TaskList, UI, and Storage.
 *
 * <p>Each subclass must implement the method execute, which defines
 * the specific behavior of the command.</p>
 *
 * <p>The Command class also provides a method to check if the command
 * is an exit command.</p>
 *
 * @see TaskList
 * @see UI
 * @see Storage
 */
public abstract class Command {
    /**
     * Returns true this is exit command
     *
     * @return a boolean value
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes a command using the provided TaskList and Storage.
     *
     * @param tasks the TaskList that holds the tasks
     * @param storage the Storage used to save and retrieve data from a file
     * @return the response that will be displayed later
     * @throws NahException if an error occurs during command execution
     */
    public abstract String execute(TaskList tasks, Storage storage) throws NahException;

    /**
     * The CleanCommand class is for cleaning all data.
     */
    public static class CleanCommand extends Command {
        /**
         * Executes the command by cleaning all data in the TaskList Storage.
         *
         * @param tasks the TaskList containing the tasks to be cleared
         * @param storage the Storage where data will be cleared from the file
         * @throws NahException if an error occurs during the cleaning process
         */
        @Override
        public String execute(TaskList tasks, Storage storage) throws NahException {
            storage.clean();
            return tasks.clean();

        }

    }
    /**
     * The CleanCommand class is for finding matching data.
     */
    public static class FindCommand extends Command {
        private String keyWord;
        public FindCommand(String keyWord) {
            this.keyWord = keyWord;
        }

        /**
         * Executes the command by finding tasks that match the keyWord in the TaskList.
         *
         * @param tasks the TaskList containing the tasks to search through
         * @param storage the Storage where data is stored (not modified in this operation)
         */

        @Override
        public String execute(TaskList tasks, Storage storage) throws NahException {
            return tasks.find(this.keyWord);
        }

    }
    /**
     * The CleanCommand class is for adding new task.
     */
    public static class AddCommand extends Command {
        private Task newTask;
        public AddCommand(Task newTask) {
            this.newTask = newTask;
        }


        /**
         * Executes the command by adding the new task to the TaskList and updating the Storage.
         *
         * @param tasks the TaskList where the new task will be added
         * @param storage the Storage where the updated task list will be saved
         * @throws NahException if an error occurs while updating the storage
         */
        @Override
        public String execute(TaskList tasks, Storage storage) throws NahException {
            tasks.add(this.newTask);
            String response = " Got it. I've added this task:\n"
                    + "   " + this.newTask.toString() + "\n"
                    + " Now you have " + tasks.taskCount() + " tasks in the list.\n";
            storage.rewrite(tasks.brief());
            return response;
        }

    }

    /**
     * The CleanCommand class is deleting a task.
     */
    public static class DeleteCommand extends Command {
        private int idx;
        public DeleteCommand(int idx) {
            this.idx = idx;
        }

        /**
         * Executes the command by deleting the task with the specified index in the TaskList
         * and updating the Storage.
         *
         * @param tasks the TaskList from which the task will be deleted
         * @param storage the Storage where the updated task list will be saved
         * @throws NahException if an error occurs while updating the storage
         */
        @Override
        public String execute(TaskList tasks, Storage storage) throws NahException {
            String response = tasks.delete(this.idx);
            storage.rewrite(tasks.brief());
            return response;
        }
    }

    /**
     * The CleanCommand class is for finding tasks before due.
     */
    public static class DueOnCommand extends Command {
        private String due;
        public DueOnCommand(String due) {
            this.due = due;
        }

        /**
         * Executes the command by finding tasks that have an ending time before the specified
         * due date in the TaskList.
         *
         * @param tasks the TaskList containing the tasks to be searched
         * @param storage the Storage where the updated task list will be saved
         *                (not modified in this operation)
         */
        @Override
        public String execute(TaskList tasks, Storage storage) {
            return tasks.dueOn(this.due);
        }
    }

    /**
     * The CleanCommand class is for stopping the program.
     */
    public static class ExitCommand extends Command {
        @Override
        public boolean isExit() {
            return true;
        }

        /**
         * Executes the command by stopping the program.
         *
         * @param tasks   the TaskList containing the tasks (not modified in this operation)
         * @param storage the Storage where data is stored (not modified in this operation)
         */
        @Override
        public String execute(TaskList tasks, Storage storage) {
            return " Bye. Hope to see you again soon!\n";
        }

    }

    /**
     * The CleanCommand class is for listing all tasks.
     */
    public static class ListCommand extends Command {
        /**
         * Executes the command by listing all tasks in the TaskList.
         *
         * @param tasks the TaskList containing the tasks
         * @param storage the Storage where data is stored
         */
        @Override
        public String execute(TaskList tasks, Storage storage) {
            return tasks.readTask();
        }
    }

    /**
     * The CleanCommand class is for marking a task as done.
     */
    public static class MarkCommand extends Command {
        private int idx;
        public MarkCommand(int idx) {
            this.idx = idx;
        }

        /**
         * Executes the command by marking the task with the specified index in the TaskList
         * as done and updating the storage.
         *
         * @param tasks   the TaskList containing the tasks
         * @param storage the Storage where data is stored
         * @throws NahException if an error occurs during the task update or storage rewrite
         */
        @Override
        public String execute(TaskList tasks, Storage storage) throws NahException {
            String response = tasks.mark(this.idx);
            storage.rewrite(tasks.brief());
            return response;
        }
    }

    /**
     * The CleanCommand class is for a command line that chatBot doesn't understand.
     */
    public static class UnknownCommand extends Command {
        /**
         * Executes with UI showing the corresponding interaction.
         *
         * @param tasks the TaskList containing the tasks
         * @param storage the Storage where data is stored
         */
        @Override
        public String execute(TaskList tasks, Storage storage) {
            return " Nahhhhh!!! Please give me a valid command, such as list, mark, todo,...\n";
        }
    }

    /**
     * The CleanCommand class is for marking a class as not done.
     */
    public static class UnmarkCommand extends Command {
        private int idx;
        public UnmarkCommand(int idx) {
            this.idx = idx;
        }

        /**
         * Executes the command by marking the task with the specified index in the TaskList
         * as not done and updating the storage.
         *
         * @param tasks   the TaskList containing the tasks
         * @param storage the Storage where data is stored
         * @throws NahException if an error occurs during the task update or storage rewrite
         */
        @Override
        public String execute(TaskList tasks, Storage storage) throws NahException {
            String response = tasks.unMark(this.idx);
            storage.rewrite(tasks.brief());
            return response;
        }
    }
}
