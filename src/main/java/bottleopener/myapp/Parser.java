package bottleopener.myapp;

import bottleopener.task.Deadline;
import bottleopener.task.Event;
import bottleopener.task.Task;
import bottleopener.task.Tasklist;
import bottleopener.task.ToDo;
import bottleopener.ui.Ui;

/**
 * The {@code Parser} class is responsible for interpreting and executing user commands
 * in the BottleOpener chatbot. It processes the user's input, determines the appropriate
 * action, and interacts with the task list and user interface to provide feedback.
 */
public class Parser {
    private boolean isExitCalled;
    private final String[] userInput;
    private final String instruction;
    private final Tasklist tasklist;

    /**
     * Constructs a {@code Parser} object with the given user input, task list, and user interface.
     *
     * @param inp The raw input provided by the user.
     * @param tasklist The current list of tasks.
     * @param ui The user interface for displaying messages.
     */
    public Parser(String userInput, Tasklist tasklist) {
        assert !userInput.trim().isEmpty() : "Input cannot be empty";
        assert tasklist != null : "Tasklist cannot be null";

        this.isExitCalled = false;
        this.userInput = userInput.split(" ", 2);
        this.instruction = this.userInput[0].toLowerCase();
        this.tasklist = tasklist;

        assert !this.instruction.isEmpty() : "Instruction cannot be empty";
    }

    /**
     * Checks whether the command to exit has been called.
     *
     * @return {@code true} if the exit command has been issued, otherwise {@code false}.
     */
    public boolean checkExit() {
        return this.isExitCalled;
    }

    /**
     * Executes the command based on the parsed user input. It handles commands such as "bye",
     * "list", "mark", "unmark", "delete", "find" and task creation commands like "todo", "deadline", and "event".
     */
    public String execute() {
        switch (this.instruction) {
        case "bye":
            this.isExitCalled = true;
            return Ui.showGoodbye();

        case "list":
            String output = tasklist.showTasklist();
            return Ui.wrapSpacer(output);

        case "mark":
            try {
                int num = Integer.parseInt(this.userInput[1]);
                this.tasklist.markTask(num);
                return Ui.showMark();
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                return Ui.showMissingInfoError();
            } catch (IndexOutOfBoundsException e) {
                return Ui.showAppropriateNumberError();
            }

        case "unmark":
            try {
                int num = Integer.parseInt(this.userInput[1]);
                this.tasklist.unmarkTask(num);
                return Ui.showUnmark();
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                return Ui.showMissingInfoError();
            } catch (IndexOutOfBoundsException e) {
                return Ui.showAppropriateNumberError();
            }

        case "delete":
            try {
                int num = Integer.parseInt(this.userInput[1]);
                this.tasklist.deleteTask(num);
                return Ui.showDelete();
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                return Ui.showMissingInfoError();
            } catch (IndexOutOfBoundsException e) {
                return Ui.showAppropriateNumberError();
            }

        case "find":
            try {
                String keyword = this.userInput[1].trim().toLowerCase();
                String findOutput = "";
                int count = 1;
                for (int i = 0; i < tasklist.getSize(); i++) {
                    Task curr = tasklist.getTask(i);
                    String taskDescription = curr.getDescription().trim().toLowerCase();
                    if (taskDescription.contains(keyword)) {
                        findOutput = findOutput + String.format("%d. %s%n", count, curr);
                        count++;
                    }
                }
                return Ui.wrapSpacer(Ui.showFoundTasks() + findOutput);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                return Ui.showMissingInfoError();
            }

        default:
            try {
                String taskDescription = this.userInput[1];
                switch (this.instruction) {
                case "todo":
                    Task newTodo = new ToDo(taskDescription);
                    this.tasklist.addTask(newTodo);
                    return Ui.wrapSpacer(String.format("added: %s%n", newTodo));

                case "deadline":
                    try {
                        String[] activity = taskDescription.split(" /by ", 2);
                        String action = activity[0].trim();
                        String taskDue = activity[1].trim();
                        Task newDeadline = new Deadline(action, taskDue);
                        this.tasklist.addTask(newDeadline);
                        return Ui.wrapSpacer(String.format("added: %s%n", newDeadline));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        return Ui.showCommandFormatError();
                    } catch (IllegalArgumentException e) {
                        return Ui.showInvalidDateFormatError();
                    }

                case "event":
                    try {
                        String[] activity = taskDescription.split(" /from | /to ", 3);
                        String action = activity[0].trim();
                        String taskStart = activity[1].trim();
                        String taskEnd = activity[2].trim();
                        Task newEvent = new Event(action, taskStart, taskEnd);
                        this.tasklist.addTask(newEvent);
                        return Ui.wrapSpacer(String.format("added: %s%n", newEvent));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        return Ui.showCommandFormatError();
                    } catch (IllegalArgumentException e) {
                        return Ui.showInvalidDateFormatError();
                    }

                default:
                    return Ui.showCommandFormatError();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return Ui.showMissingInfoError();
            }
        }
    }

}
