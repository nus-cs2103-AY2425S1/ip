package nah.command;

import nah.data.Task;
import nah.exceptions.NahException;
import nah.tasklist.TaskList;
import nah.ui.UI;
import nah.storage.Storage;
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
     * Executes a command using the provided TaskList, UI, and Storage.
     *
     * @param tasks the TaskList that holds the tasks
     * @param ui the UI used to interact with the user
     * @param storage the Storage used to save and retrieve data from a file
     * @throws NahException if an error occurs during command execution
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws NahException;

    public static class CleanCommand extends Command {
        /**
         * Executes the command by cleaning all data in the TaskList and Storage.
         * The UI will display the corresponding interaction.
         *
         * @param tasks the TaskList containing the tasks to be cleared
         * @param ui the UI used to interact with the user and display the result
         * @param storage the Storage where data will be cleared from the file
         * @throws NahException if an error occurs during the cleaning process
         */
        @Override
        public void execute(TaskList tasks, UI ui, Storage storage) throws NahException {
            ui.show(tasks.clean());
            storage.clean();
        }

    }
    public static class FindCommand extends Command {
        private String keyWord;
        public FindCommand(String keyWord) {
            this.keyWord = keyWord;
        }

        /**
         * Executes the command by finding tasks that match the keyWord in the TaskList.
         * The UI will display the corresponding interaction.
         *
         * @param tasks the TaskList containing the tasks to search through
         * @param ui the UI used to interact with the user and display the search results
         * @param storage the Storage where data is stored (not modified in this operation)
         */

        @Override
        public void execute(TaskList tasks, UI ui, Storage storage) throws NahException {
            ui.show(tasks.find(this.keyWord));
        }

    }
    public static class AddCommand extends Command {
        private Task newTask;
        public AddCommand(Task newTask) {
            this.newTask = newTask;
        }

        /**
         * Executes the command by adding the new task to the TaskList and updating the Storage.
         * The UI will display the corresponding interaction.
         *
         * @param tasks the TaskList where the new task will be added
         * @param ui the UI used to interact with the user and display the addition result
         * @param storage the Storage where the updated task list will be saved
         * @throws NahException if an error occurs while updating the storage
         */
        @Override
        public void execute(TaskList tasks, UI ui, Storage storage) throws NahException {
            tasks.add(this.newTask);
            ui.show(" Got it. I've added this task:\n"
                    + "   " + this.newTask.toString() + "\n"
                    + " Now you have " + tasks.taskCount() + " tasks in the list.\n");
            storage.rewrite(tasks.brief());
        }

    }

    public static class DeleteCommand extends Command {
        private int idx;
        public DeleteCommand(int idx) {
            this.idx = idx;
        }

        /**
         * Executes the command by deleting the task with the specified index in the TaskList
         * and updating the Storage. The UI will display the corresponding interaction.
         *
         * @param tasks the TaskList from which the task will be deleted
         * @param ui the UI used to interact with the user and display the deletion result
         * @param storage the Storage where the updated task list will be saved
         * @throws NahException if an error occurs while updating the storage
         */
        @Override
        public void execute(TaskList tasks, UI ui, Storage storage) throws NahException {
            ui.show(tasks.delete(this.idx));
            storage.rewrite(tasks.brief());
        }
    }

    public static class DueOnCommand extends Command {
        private String due;
        public DueOnCommand(String due) {
            this.due = due;
        }

        /**
         * Executes the command by finding tasks that have an ending time before the specified due date in the TaskList.
         * The UI will display the corresponding interaction.
         *
         * @param tasks the TaskList containing the tasks to be searched
         * @param ui the UI used to interact with the user and display the search results
         * @param storage the Storage where the updated task list will be saved
         *                (not modified in this operation)
         */
        @Override
        public void execute(TaskList tasks, UI ui, Storage storage) {
            ui.show(tasks.dueOn(this.due));
        }
    }

    public static class ExitCommand extends Command {
        @Override
        public boolean isExit() {
            return true;
        }

        /**
         * Executes the command by stopping the program.
         * The UI will display the corresponding interaction.
         *
         * @param tasks   the TaskList containing the tasks (not modified in this operation)
         * @param ui      the UI used to interact with the user and stop the program
         * @param storage the Storage where data is stored (not modified in this operation)
         */
        @Override
        public void execute(TaskList tasks, UI ui, Storage storage) {
            ui.exit();
        }

    }

    public static class ListCommand extends Command {
        /**
         * Executes the command by listing all tasks in the TaskList.
         * The UI will display the corresponding interaction.
         *
         * @param tasks the TaskList containing the tasks
         * @param ui the UI used to interact with the user
         * @param storage the Storage where data is stored
         */
        @Override
        public void execute(TaskList tasks, UI ui, Storage storage) {
            ui.show(tasks.readTask());
        }
    }

    public static class MarkCommand extends Command {
        private int idx;
        public MarkCommand(int idx) {
            this.idx = idx;
        }

        /**
         * Executes the command by marking the task with the specified index in the TaskList
         * as done and updating the storage. The UI will display the corresponding interaction.
         *
         * @param tasks   the TaskList containing the tasks
         * @param ui      the UI used to interact with the user
         * @param storage the Storage where data is stored
         * @throws NahException if an error occurs during the task update or storage rewrite
         */
        @Override
        public void execute(TaskList tasks, UI ui, Storage storage) throws NahException {
            ui.show(tasks.mark(this.idx));
            storage.rewrite(tasks.brief());
        }
    }

    public static class UnknownCommand extends Command {
        /**
         * Executes with UI showing the corresponding interaction.
         *
         * @param tasks the TaskList containing the tasks
         * @param ui the UI used to interact with the user
         * @param storage the Storage where data is stored
         */
        @Override
        public void execute(TaskList tasks, UI ui, Storage storage) {
            ui.unknownLine();
        }
    }

    public static class UnmarkCommand extends Command {
        private int idx;
        public UnmarkCommand(int idx) {
            this.idx = idx;
        }

        /**
         * Executes the command by marking the task with the specified index in the TaskList
         * as not done and updating the storage. The UI will display the corresponding interaction.
         *
         * @param tasks   the TaskList containing the tasks
         * @param ui      the UI used to interact with the user
         * @param storage the Storage where data is stored
         * @throws NahException if an error occurs during the task update or storage rewrite
         */
        @Override
        public void execute(TaskList tasks, UI ui, Storage storage) throws NahException {
            ui.show(tasks.unMark(this.idx));
            storage.rewrite(tasks.brief());
        }
    }
}
