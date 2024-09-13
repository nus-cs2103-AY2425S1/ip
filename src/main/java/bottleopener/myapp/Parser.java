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
    private final Ui ui;

    /**
     * Constructs a {@code Parser} object with the given user input, task list, and user interface.
     *
     * @param inp The raw input provided by the user.
     * @param tasklist The current list of tasks.
     * @param ui The user interface for displaying messages.
     */
    public Parser(String userInput, Tasklist tasklist, Ui ui) {
        assert !userInput.trim().isEmpty() : "Input cannot be empty";
        assert tasklist != null : "Tasklist cannot be null";
        assert ui != null : "Ui cannot be null";

        this.isExitCalled = false;
        this.userInput = userInput.split(" ", 2);
        this.instruction = this.userInput[0].toLowerCase();
        this.tasklist = tasklist;
        this.ui = ui;

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
            return ui.showGoodbye();

        case "list":
            String output = tasklist.showTasklist();
            return ui.wrapSpacer(output);

        case "mark":
            try {
                int num = Integer.parseInt(this.userInput[1]);
                this.tasklist.markTask(num);
                return ui.showMark();
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                return ui.showMissingInfoError();
            } catch (IndexOutOfBoundsException e) {
                return ui.showAppropriateNumberError();
            }

        case "unmark":
            try {
                int num = Integer.parseInt(this.userInput[1]);
                this.tasklist.unmarkTask(num);
                return ui.showUnmark();
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                return ui.showMissingInfoError();
            } catch (IndexOutOfBoundsException e) {
                return ui.showAppropriateNumberError();
            }

        case "delete":
            try {
                int num = Integer.parseInt(this.userInput[1]);
                this.tasklist.deleteTask(num);
                return ui.showDelete();
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                return ui.showMissingInfoError();
            } catch (IndexOutOfBoundsException e) {
                return ui.showAppropriateNumberError();
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
                return ui.wrapSpacer(ui.showFoundTasks() + findOutput);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                return ui.showMissingInfoError();
            }

        default:
            try {
                String taskDescription = this.userInput[1];
                switch (this.instruction) {
                case "todo":
                    Task newTodo = new ToDo(taskDescription);
                    this.tasklist.addTask(newTodo);
                    return ui.wrapSpacer(String.format("added: %s%n", newTodo));

                case "deadline":
                    try {
                        String[] activity = taskDescription.split(" /by ", 2);
                        String action = activity[0].trim();
                        String taskDue = activity[1].trim();
                        Task newDeadline = new Deadline(action, taskDue);
                        this.tasklist.addTask(newDeadline);
                        return ui.wrapSpacer(String.format("added: %s%n", newDeadline));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        return ui.showCommandFormatError();
                    } catch (IllegalArgumentException e) {
                        return ui.showInvalidDateFormatError();
                    }

                case "event":
                    try {
                        String[] activity = taskDescription.split(" /from | /to ", 3);
                        String action = activity[0].trim();
                        String taskStart = activity[1].trim();
                        String taskEnd = activity[2].trim();
                        Task newEvent = new Event(action, taskStart, taskEnd);
                        this.tasklist.addTask(newEvent);
                        return ui.wrapSpacer(String.format("added: %s%n", newEvent));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        return ui.showCommandFormatError();
                    } catch (IllegalArgumentException e) {
                        return ui.showInvalidDateFormatError();
                    }

                default:
                    return ui.showCommandFormatError();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return ui.showMissingInfoError();
            }
        }
    }

}
