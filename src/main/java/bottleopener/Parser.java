package bottleopener;

/**
 * The {@code BottleOpener.Parser} class is responsible for interpreting and executing user commands
 * in the BottleOpener.BottleOpener chatbot. It processes the user's input, determines the appropriate
 * action, and interacts with the task list and user interface to provide feedback.
 */
public class Parser {
    private boolean isExitCalled;
    private final String[] userInput;
    private final String instruction;
    private final Tasklist tasklist;
    private final Ui ui;

    /**
     * Constructs a {@code BottleOpener.Parser} object with the given user input, task list, and user interface.
     *
     * @param inp The raw input provided by the user.
     * @param tasklist The current list of tasks.
     * @param ui The user interface for displaying messages.
     */
    public Parser(String inp, Tasklist tasklist, Ui ui) {
        this.isExitCalled = false;
        this.userInput = inp.split(" ", 2);
        this.instruction = this.userInput[0].toLowerCase();
        this.tasklist = tasklist;
        this.ui = ui;
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
     * "list", "mark", "unmark", "delete", and task creation commands like "todo", "deadline", and "event".
     */
    public void execute() {
        switch (this.instruction) {
        case "bye":
            this.isExitCalled = true;
            System.out.println(ui.showGoodbye());
            break;
        case "list":
            String output = tasklist.showTasklist();
            System.out.println(ui.wrapSpacer(output));
            break;
        case "mark":
            try {
                int num = Integer.parseInt(this.userInput[1]);
                this.tasklist.markTask(num);
                System.out.println(ui.showMark());
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println(ui.showMissingInfoError());
            } catch (IndexOutOfBoundsException e) {
                System.out.println(ui.showAppropriateNumberError());
            }
            break;
        case "unmark":
            try {
                int num = Integer.parseInt(this.userInput[1]);
                this.tasklist.unmarkTask(num);
                System.out.println(ui.showUnmark());
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println(ui.showMissingInfoError());
            } catch (IndexOutOfBoundsException e) {
                System.out.println(ui.showAppropriateNumberError());
            }
            break;
        case "delete":
            try {
                int num = Integer.parseInt(this.userInput[1]);
                this.tasklist.deleteTask(num);
                System.out.println(ui.showDelete());
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println(ui.showMissingInfoError());
            } catch (IndexOutOfBoundsException e) {
                System.out.println(ui.showAppropriateNumberError());
            }
            break;
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
                System.out.println(ui.wrapSpacer(ui.showFoundTasks() + findOutput));
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println(ui.showMissingInfoError());
            }
            break;
        default:
            try {
                String des = this.userInput[1];
                switch (this.instruction) {
                case "todo":
                    Task newTodo = new ToDo(des);
                    this.tasklist.addTask(newTodo);
                    System.out.println(ui.wrapSpacer(String.format("added: %s%n", newTodo)));
                    break;
                case "deadline":
                    try {
                        String[] activity = des.split(" /by ", 2);
                        String action = activity[0].trim();
                        String due = activity[1].trim();
                        Task newDeadline = new Deadline(action, due);
                        this.tasklist.addTask(newDeadline);
                        System.out.println(ui.wrapSpacer(String.format("added: %s%n", newDeadline)));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(ui.showCommandFormatError());
                    } catch (IllegalArgumentException e) {
                        System.out.println(ui.showInvalidDateFormatError());
                    }
                    break;
                case "event":
                    try {
                        String[] activity = des.split(" /from | /to ", 3);
                        String action = activity[0].trim();
                        String start = activity[1].trim();
                        String end = activity[2].trim();
                        Task newEvent = new Event(action, start, end);
                        this.tasklist.addTask(newEvent);
                        System.out.println(ui.wrapSpacer(String.format("added: %s%n", newEvent)));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(ui.showCommandFormatError());
                    } catch (IllegalArgumentException e) {
                        System.out.println(ui.showInvalidDateFormatError());
                    }
                    break;
                default:
                    System.out.println(ui.showCommandFormatError());
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(ui.showMissingInfoError());
            }
        }
    }

}
