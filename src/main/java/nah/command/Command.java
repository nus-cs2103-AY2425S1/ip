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
    private final static String helpLine = " Please type 'Help' followed by one of these keywords to get the " +
            "corresponding command format\n"
            + " 1.Bye : to exit the program\n"
            + " 2.List : to list the tasks in the storage\n"
            + " 3.Clean : to clean the storage\n"
            + " 4.Find : to find the matching tasks\n"
            + " 5.DueOn : to find the uncompleted tasks that before due\n"
            + " 6.Mark : to mark the corresponding task as done\n"
            + " 7.Unmark : to mark the corresponding task as not done\n"
            + " 8.Delete : to delete the task\n"
            + " 9.Todo : to add a todo task\n"
            + " 10.Deadline : to add a deadline task\n"
            + " 11.Event : to add an event task\n";
    private final static String exitFormat = "Bye";
    private final static String listFormat = "List";
    private final static String cleanFormat = "Clean";
    private final static String findFormat = "Find 'one or more words')";
    private final static String markFormat = "Mark 'ordinal number of the task'";
    private final static String ummarkFormat = "Unmark 'ordinal number of the task'";
    private final static String deleteFormat = "Delete 'ordinal number of the task'";
    private final static String dueOnFormat = "DueOn yyyy-mm-dd HHmm";
    private final static String toDoFormat = "Todo 'description'";
    private final static String deadLineFormat = "Deadline 'description' /by yyyy-mm-dd HHmm";
    private final static String eventFormat =
            "Event 'description' /from yyyy-mm-dd HHmm /to yyyy-mm-dd HHmm";
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
            assert storage != null : "Storage cannot be null";
            assert tasks != null : "Tasklist cannot be null";
            storage.clean();
            return tasks.clean();

        }

    }
    /**
     * The FindCommand class is for finding matching data.
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
            assert keyWord != null : "FindCommand can not have null keyWord";
            assert storage != null : "Storage cannot be null";
            assert tasks != null : "Tasklist cannot be null";
            return tasks.find(this.keyWord);
        }

    }
    /**
     * The AddCommand class is for adding new task.
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
            assert storage != null : "Storage cannot be null";
            assert tasks != null : "Tasklist cannot be null";
            assert this.newTask != null : "New task cannot be null";
            tasks.add(this.newTask);
            String response = " Got it. I've added this task:\n"
                    + "   " + this.newTask.toString() + "\n"
                    + " Now you have " + tasks.taskCount() + " tasks in the list.\n";
            storage.rewrite(tasks.brief());
            return response;
        }

    }

    /**
     * The DeleteCommand class is deleting a task.
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
            assert storage != null : "Storage cannot be null";
            assert tasks != null : "Tasklist cannot be null";
            String response = tasks.delete(this.idx);
            storage.rewrite(tasks.brief());
            return response;
        }
    }

    /**
     * The DueOnCommand class is for finding tasks before due.
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
            assert storage != null : "Storage cannot be null";
            assert tasks != null : "Tasklist cannot be null";
            assert due != null : "Due time cannot be null";
            return tasks.dueOn(this.due);
        }
    }

    /**
     * The ExitCommand class is for stopping the program.
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
            assert storage != null : "Storage cannot be null";
            assert tasks != null : "Tasklist cannot be null";
            return " Bye. Hope to see you again soon!\n";
        }

    }

    /**
     * The HelpCommand class is for showing possible command format.
     */
    public static class HelpCommand extends Command {
        private EnumCommand cmd;

        public HelpCommand(EnumCommand cmd) {
            this.cmd = cmd;
        }

        /**
         * Executes the command by showing the format for the corresponding command.
         *
         * @param tasks   the TaskList containing the tasks (not modified in this operation)
         * @param storage the Storage where data is stored (not modified in this operation)
         */
        @Override
        public String execute(TaskList tasks, Storage storage) {
            String response = " Here is the format for this command:\n" + " ";

            switch (this.cmd) {
            case UNKNOWN : return Command.helpLine;
            case LIST : return response + Command.listFormat;
            case BYE : return response + Command.exitFormat;
            case CLEAN : return response + Command.cleanFormat;
            case FIND : return response + Command.findFormat;
            case DUEON : return response + Command.dueOnFormat;
            case MARK : return response + Command.markFormat;
            case UNMARK : return response + Command.markFormat;
            case DELETE : return response + Command.deleteFormat;
            case TODO : return response + Command.toDoFormat;
            case DEADLINE : return response + Command.deadLineFormat;
            case EVENT : return response + Command.eventFormat;
            default: assert false : "Something is wrong with help command";

            }
            return "Something is wrong with help command";
        }

    }

    /**
     * The ListCommand class is for listing all tasks.
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
            assert storage != null : "Storage cannot be null";
            assert tasks != null : "Tasklist cannot be null";
            return tasks.readTask();
        }
    }

    /**
     * The MarkCommand class is for marking a task as done.
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
            assert storage != null : "Storage cannot be null";
            assert tasks != null : "Tasklist cannot be null";
            String response = tasks.mark(this.idx);
            storage.rewrite(tasks.brief());
            return response;
        }
    }

    /**
     * The UnknownCommand class is for a command line that chatBot doesn't understand.
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
            assert storage != null : "Storage cannot be null";
            assert tasks != null : "Tasklist cannot be null";
            return " Nahhhhh!!! Please type help to get possible command type\n";
        }
    }

    /**
     * The UnmarkCommand class is for marking a class as not done.
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
            assert storage != null : "Storage cannot be null";
            assert tasks != null : "Tasklist cannot be null";
            String response = tasks.unMark(this.idx);
            storage.rewrite(tasks.brief());
            return response;
        }
    }
}
