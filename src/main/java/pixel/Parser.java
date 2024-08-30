package pixel;

/**
 * Class that deals with the control flow upon obtaining user command
 */
public class Parser {

    /**
     * Constructor method of Parser
     */
    public Parser() {
    }

    /**
     * Act based on command given from the user's input
     *
     * @param command command obtained from Ui
     * @param tasks TaskList for processing of tasks
     * @param storage Storage for processing of files
     */
    public static void parse(String command, TaskList tasks, Storage storage) {
        if (command.equals("list")) {
            Ui.printList(tasks);
        } else if (command.startsWith("todo")) {
            String description = command.replace("todo", "").trim();
            if (description.isEmpty()) {
                Ui.handleEmptyDescriptionException();
            } else {
                ToDo newToDo = new ToDo(description);
                tasks.addTask(newToDo);
                storage.updateFile(tasks);
                Ui.printAddConfirmation(tasks);
            }
        } else if (command.startsWith("deadline")) {
            String[] stringArray = command.split("/", 0);
            String description = stringArray[0].replace("deadline", "").trim();
            String by = stringArray[1].replace("by", "").trim();
            Deadline newDeadline = new Deadline(description, by);
            tasks.addTask(newDeadline);
            storage.updateFile(tasks);
            Ui.printAddConfirmation(tasks);
        } else if (command.startsWith("event")) {
            String[] stringArray = command.split("/", 0);
            String description = stringArray[0].replace("event", "").trim();
            String from = stringArray[1].replace("from", "").trim();
            String to = stringArray[2].replace("to", "").trim();
            Event newEvent = new Event(description, from, to);
            tasks.addTask(newEvent);
            storage.updateFile(tasks);
            Ui.printAddConfirmation(tasks);
        } else if (command.startsWith("mark")) {
            String[] stringArray = command.split(" ", 0);
            Task currentTask = tasks.getTask(Integer.parseInt(stringArray[1]) - 1);
            currentTask.markAsDone();
            storage.updateFile(tasks);
            Ui.printMarkConfirmation(currentTask);
        } else if (command.startsWith("unmark")) {
            String[] stringArray = command.split(" ", 0);
            Task currentTask = tasks.getTask(Integer.parseInt(stringArray[1]) - 1);
            currentTask.markAsUndone();
            storage.updateFile(tasks);
            Ui.printUnmarkConfirmation(currentTask);
        } else if (command.startsWith("delete")) {
            String[] stringArray = command.split(" ", 0);
            Integer index = Integer.parseInt(stringArray[1]) - 1;
            Task removedTask = tasks.getTask(index);
            tasks.deleteTask(index);
            storage.updateFile(tasks);
            Ui.printDeleteConfirmation(removedTask, tasks);
        } else if (command.equals("bye")) {
            Ui.printExit();
            System.exit(0);
        } else {
            Ui.handleInvalidCommandException();
        }
    }
}
