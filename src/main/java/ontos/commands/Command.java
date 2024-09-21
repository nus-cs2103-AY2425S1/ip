package ontos.commands;

import java.io.IOException;

import ontos.storage.SaveManager;
import ontos.task.Task;
import ontos.task.TaskList;
import ontos.ui.Ui;

/**
 * Represents an abstract command that can be executed by the application.
 * It serves as the base class for all specific command types that perform various operations on tasks.
 */
public abstract class Command {
    /** Boolean value representing if the command wants to exit. */
    protected boolean isExit;

    /**
     * Constructs a Command with the default state indicating the program should not exit.
     */
    protected Command() {
        this.isExit = false;
    }

    /**
     * Returns whether the command signals the program to exit.
     *
     * @return true if the command signals the program to exit; false otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes the command, performing its specific operation on the task list.
     *
     * @param tasks       The {@code TaskList} to operate on.
     * @param ui          The {@code Ui} used for interacting with the user.
     * @param saveManager The {@code SaveManager} responsible for saving and loading tasks.
     */
    public abstract String execute(TaskList tasks, Ui ui, SaveManager saveManager);

    /**
     * Represents a command that ends the program.
     * It saves the tasks and displays a goodbye message.
     */
    public static class ByeCommand extends Command {
        /**
         * Constructs a ByeCommand, sets the exit flag to true.
         */
        public ByeCommand() {
            this.isExit = true;
        }

        /**
         * Saves the tasks to the save file and displays a goodbye message.
         *
         * @param tasks       The TaskList to save.
         * @param ui          The Ui used for displaying the goodbye message.
         * @param saveManager The SaveManager responsible for saving tasks.
         */
        @Override
        public String execute(TaskList tasks, Ui ui, SaveManager saveManager) {
            try {
                saveManager.writeToSave(tasks);
            } catch (IOException e) {
                System.out.println("Failed to save: " + e.getMessage());
            }
            return ui.goodbye();
        }

        /**
         * Compares this bye command to another object for equality.
         * Used for JUnit tests.
         *
         * @param o The object to compare this bye command with.
         * @return true if the specified object is equal to this bye command; false otherwise.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ByeCommand byeCommand = (ByeCommand) o;
            return byeCommand.isExit == this.isExit;
        }
    }

    /**
     * Represents a command that lists all tasks.
     */
    public static class ListCommand extends Command {
        /**
         * Constructs a ListCommand.
         */
        public ListCommand() {
            super();
        }

        /**
         * Displays the list of tasks to the user.
         *
         * @param tasks       The TaskList containing the tasks to display.
         * @param ui          The Ui used for displaying the tasks.
         * @param saveManager The SaveManager used to save tasks, is not used here.
         */
        @Override
        public String execute(TaskList tasks, Ui ui, SaveManager saveManager) {
            assert tasks != null : "TaskList should not be null";
            return ui.list(tasks);
        }

        /**
         * Compares this list command to another object for equality.
         * Used for JUnit tests.
         *
         * @param o The object to compare this list command with.
         * @return true if the specified object is equal to this list command; false otherwise.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ListCommand listCommand = (ListCommand) o;
            return listCommand.isExit == this.isExit;
        }
    }

    /**
     * Represents a command that marks or unmarks a task as completed.
     */
    public static class MarkCommand extends Command {
        /** Boolean value representing if the command marks or unmarks a task. */
        private boolean isMarkCorrect;
        /** The index for the task to mark (1-based index). */
        private int index;

        /**
         * Constructs a MarkCommand with the specified mark state and task index.
         *
         * @param isMarkCorrect true if the task should be marked as completed; false if it should be unmarked.
         * @param index         The index of the task to mark or unmark.
         */
        public MarkCommand(boolean isMarkCorrect, int index) {
            super();
            this.isMarkCorrect = isMarkCorrect;
            this.index = index;
        }

        /**
         * Marks or unmarks the task at the specified index.
         *
         * @param tasks       The TaskList containing the task to mark or unmark.
         * @param ui          The Ui used for displaying the result.
         * @param saveManager The SaveManager used to save tasks, is not used here.
         */
        public String execute(TaskList tasks, Ui ui, SaveManager saveManager) {
            assert tasks != null : "TaskList cannot be null";
            assert index >= 0 && index < tasks.getSize() : "Index out of bounds for TaskList";

            try {
                if (isMarkCorrect) {
                    tasks.completeTaskAt(index);
                } else {
                    tasks.uncompleteTaskAt(index);
                }
            } catch (IndexOutOfBoundsException e) {
                return ui.taskDoesNotExist();
            }

            try {
                saveManager.writeToSave(tasks);
            } catch (IOException e) {
                System.out.println("Failed to save: " + e.getMessage());
            }

            return isMarkCorrect ? ui.mark(tasks, index) : ui.unmark(tasks, index);
        }
    }

    /**
     * The {@code DeleteCommand} class represents a command that deletes a task.
     */
    public static class DeleteCommand extends Command {
        /** Index of the task to delete (1-based index) */
        private int index;

        /**
         * Constructs a DeleteCommand with the specified task index.
         *
         * @param index The index of the task to delete (1-based index).
         */
        public DeleteCommand(int index) {
            super();
            this.index = index;
        }

        /**
         * Deletes the task at the specified index.
         *
         * @param tasks       The TaskList containing the task to delete.
         * @param ui          The Ui used for displaying the result.
         * @param saveManager The SaveManager used to save tasks, is not used here.
         */
        public String execute(TaskList tasks, Ui ui, SaveManager saveManager) {
            assert tasks != null : "TaskList cannot be null";
            assert index >= 0 && index < tasks.getSize() : "Index out of bounds for TaskList";

            Task task = null;
            try {
                task = tasks.removeTaskAt(index);
            } catch (IndexOutOfBoundsException e) {
                return ui.taskDoesNotExist();
            }

            try {
                saveManager.writeToSave(tasks);
            } catch (IOException e) {
                System.out.println("Failed to save: " + e.getMessage());
            }

            return ui.delete(task);
        }
    }

    /**
     * Represents a command that adds a new task.
     */
    public static class AddTaskCommand extends Command {
        /** Task to be added */
        private Task task;

        /**
         * Constructs an AddTaskCommand with the specified task.
         *
         * @param task The task to add.
         */
        public AddTaskCommand(Task task) {
            super();
            this.task = task;
        }

        /**
         * Adds the specified task to the task list.
         *
         * @param tasks       The TaskList to add the task to.
         * @param ui          The Ui used for displaying the result.
         * @param saveManager The SaveManager used to save tasks, is not used here.
         */
        public String execute(TaskList tasks, Ui ui, SaveManager saveManager) {
            assert tasks != null : "TaskList cannot be null";
            tasks.addTask(task);

            try {
                saveManager.writeToSave(tasks);
            } catch (IOException e) {
                System.out.println("Failed to save: " + e.getMessage());
            }

            return ui.taskAdded(task, tasks);
        }
    }

    /**
     * Represents a command used to find a task.
     */
    public static class FindCommand extends Command {
        /** Search term used to find the task */
        private String searchCriteria;

        /**
         * Constructs an FindCommand with the specified searchCriteria.
         *
         * @param task The task to add.
         */
        public FindCommand(String searchCriteria) {
            this.searchCriteria = searchCriteria;
        }

        /**
         * Searches for the tasks with the specified String.
         *
         * @param tasks       The TaskList to add the task to.
         * @param ui          The Ui used for displaying the result.
         * @param saveManager The SaveManager used to save tasks, is not used here.
         */
        public String execute(TaskList tasks, Ui ui, SaveManager saveManager) {
            assert tasks != null : "TaskList cannot be null";
            String filteredList = tasks.containsString(searchCriteria);
            return ui.findOutput(filteredList);
        }
    }

    /**
     * Represents a command used to display help information.
     */
    public static class HelpCommand extends Command {
        /**
         * Constructs a HelpCommand.
         */
        public HelpCommand() {
            super();
        }

        /**
         * Displays the help information to the user.
         *
         * @param tasks       The TaskList containing the tasks, is not used here.
         * @param ui          The Ui used for displaying the help information.
         * @param saveManager The SaveManager used to save tasks, is not used here.
         */
        public String execute(TaskList tasks, Ui ui, SaveManager saveManager) {
            return ui.help();
        }
    }
}
