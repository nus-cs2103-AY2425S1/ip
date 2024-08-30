package nah.command;

import nah.data.Task;
import nah.exceptions.NahException;
import nah.tasklist.TaskList;
import nah.ui.UI;
import nah.storage.Storage;
public abstract class Command {
    /**
     * return true if this is exit command
     * @return
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Execute command with corresponding Nah.TaskList, Nah.UI and Nah.Storage
     * @param task
     * @param ui
     * @param storage
     */
    public abstract void execute(TaskList task, UI ui, Storage storage) throws NahException;

    public static class CleanCommand extends Command {
        /**
         * Process the command
         * @param tasks
         * @param ui
         * @param storage
         * @throws NahException
         */
        @Override
        public void execute(TaskList tasks, UI ui, Storage storage) throws NahException {
            ui.show(" Got it. I've cleaned the storage. Now you have no tasks in the list.\n");
            storage.clean();
        }

    }
    public static class FindCommand extends Command {
        private String keyWord;
        public FindCommand(String keyWord) {
            this.keyWord = keyWord;
        }

        /**
         * Process the command
         * @param tasks
         * @param ui
         * @param storage
         * @throws NahException
         */
        @Override
        public void execute(TaskList tasks, UI ui, Storage storage) throws NahException {
            ui.show(tasks.find(keyWord));
        }

    }
    public static class AddCommand extends Command {
        private Task task;
        public AddCommand(Task task) {
            this.task = task;
        }

        /**
         * Process the command
         * @param tasks
         * @param ui
         * @param storage
         * @throws NahException
         */
        @Override
        public void execute(TaskList tasks, UI ui, Storage storage) throws NahException {
            tasks.add(this.task);
            ui.show(" Got it. I've added this task:\n"
                    + "   " + this.task.toString() + "\n"
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
         * Process the command
         * @param tasks
         * @param ui
         * @param storage
         * @throws NahException
         */
        @Override
        public void execute(TaskList tasks, UI ui, Storage storage) throws NahException {
            ui.show(tasks.delete(this.idx));
            storage.rewrite(tasks.brief());
        }
    }

    public static class DueOnCommand extends Command {
        private String time;
        public DueOnCommand(String time) {
            this.time = time;
        }

        /**
         * Process the command
         * @param tasks
         * @param ui
         * @param storage
         */
        @Override
        public void execute(TaskList tasks, UI ui, Storage storage) {
            ui.show(tasks.dueOn(this.time));
        }
    }

    public static class ExitCommand extends Command {
        @Override
        public boolean isExit() {
            return true;
        }

        /**
         * Process the command
         * @param task
         * @param ui
         * @param storage
         */
        @Override
        public void execute(TaskList task, UI ui, Storage storage) {
            ui.exit();
        }

    }

    public static class ListCommand extends Command {
        /**
         * Process the command
         * @param tasks
         * @param ui
         * @param storage
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
         * Process the command
         * @param tasks
         * @param ui
         * @param storage
         * @throws NahException
         */
        @Override
        public void execute(TaskList tasks, UI ui, Storage storage) throws NahException {
            ui.show(tasks.mark(this.idx));
            storage.rewrite(tasks.brief());
        }
    }

    public static class UnknownCommand extends Command {
        /**
         * Process the command
         * @param tasks
         * @param ui
         * @param storage
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

        /** Process the command
         * @param tasks
         * @param ui
         * @param storage
         * @throws NahException
         */
        @Override
        public void execute(TaskList tasks, UI ui, Storage storage) throws NahException {
            ui.show(tasks.unMark(this.idx));
            storage.rewrite(tasks.brief());
        }
    }
}
