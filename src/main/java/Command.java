import java.io.IOException;

public abstract class Command {
    protected boolean isExit;

    protected Command() {
        this.isExit = false;
    }

    public abstract void execute(TaskList tasks, Ui ui, SaveManager saveManager);

    public static class ByeCommand extends Command {
        public ByeCommand() {
            this.isExit = true;
        }
        
        @Override
        public void execute(TaskList tasks, Ui ui, SaveManager saveManager) {
            try {
                saveManager.writeToSave(tasks);
            } catch (IOException e) {
                System.out.println("Failed to save: " + e.getMessage());
            }
            ui.goodbye();
        }
    }

    public static class ListCommand extends Command {
        public ListCommand() {
            super();
        }

        @Override
        public void execute(TaskList tasks, Ui ui, SaveManager saveManager) {
            ui.list(tasks);
        }
    }

    public static class MarkCommand extends Command {
        private boolean isMarkCorrect;
        private int index;

        public MarkCommand(boolean isMarkCorrect, int index) {
            super();
            this.isMarkCorrect = isMarkCorrect;
            this.index = index;
        }

        public void execute(TaskList tasks, Ui ui, SaveManager saveManager) {
            try {
                if (isMarkCorrect) {
                    tasks.completeTaskAt(index);
                    ui.mark(tasks, index);
                } else {
                    tasks.uncompleteTaskAt(index);
                    ui.unmark(tasks, index);
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("caught");
                ui.taskDoesNotExist();
            }
        }

    }

    public static class DeleteCommand extends Command {
        private int index;
        
        public DeleteCommand(int index) {
            super();
            this.index = index;
        }

        public void execute(TaskList tasks, Ui ui, SaveManager saveManager) {
            try {
                Task task = tasks.removeTaskAt(index);
                ui.delete(task);
            } catch (IndexOutOfBoundsException e) {
                ui.taskDoesNotExist();
            }
        }
    }

    public static class AddTaskCommand extends Command {
        private Task task;

        public AddTaskCommand(Task task) {
            super();
            this.task = task;
        }

        public void execute(TaskList tasks, Ui ui, SaveManager saveManager) {
            tasks.addTask(task);
            ui.taskAdded(task, tasks);
        }

    }
}
