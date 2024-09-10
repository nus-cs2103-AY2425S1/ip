package chobo;

import java.util.ArrayList;

/**
 * The type Parser.
 */
public class Parser {
    /**
     * Parse boolean.
     *
     * @param fullCommand the full command
     * @param taskList    the task list
     * @param ui          the ui
     * @param storage     the storage
     * @return the boolean
     * @throws InputException the input exception
     */
    public static String parse(String fullCommand, TaskList taskList, Ui ui, Storage storage) throws InputException {
        String[] inputs = fullCommand.split(" ", 2);
        String action = inputs[0];

        switch (action) {
        case "bye":
            return "bye";
        case "list":
            return ui.printTaskList(taskList.getTasks());
        case "mark":
        case "unmark":
        case "delete":
            if (inputs.length < 2) {
                throw new InputException("id");
            }
            int taskId = Integer.parseInt(inputs[1].trim()) - 1;
            if (taskId < 0 || taskId >= taskList.getTotalTask()) {
                throw new InputException("id");
            }
            if (action.equals("mark")) {
                taskList.getTask(taskId).mark();
                storage.saveTasks(taskList.getTasks());
                return ui.printTaskMarked(taskList.getTask(taskId));
            } else if (action.equals("unmark")) {
                taskList.getTask(taskId).unmark();
                storage.saveTasks(taskList.getTasks());
                return ui.printTaskUnmarked(taskList.getTask(taskId));
            } else {
                Task removedTask = taskList.getTask(taskId);
                taskList.deleteTask(taskId);
                storage.saveTasks(taskList.getTasks());
                return ui.printTaskDeleted(removedTask, taskList.getTotalTask());
            }
        case "todo":
            if (inputs.length < 2) {
                throw new InputException("todo");
            }
            Task todo = new ToDo(inputs[1].trim(), false);
            taskList.addTask(todo);
            storage.saveTasks(taskList.getTasks());
            return ui.printTaskAdded(todo, taskList.getTotalTask());
        case "deadline":
            if (inputs.length < 2) {
                throw new InputException("deadline");
            }
            String[] deadlineParts = inputs[1].split("/by", 2);
            if (deadlineParts.length < 2) {
                throw new InputException("deadline");
            }
            Task deadline = new Deadline(deadlineParts[0].trim(), false, deadlineParts[1].trim());
            taskList.addTask(deadline);
            storage.saveTasks(taskList.getTasks());
            return ui.printTaskAdded(deadline, taskList.getTotalTask());
        case "event":
            if (inputs.length < 2) {
                throw new InputException("event");
            }
            String[] eventParts = inputs[1].split("/from", 2);
            if (eventParts.length < 2) {
                throw new InputException("event");
            }
            String[] dates = eventParts[1].split("/to", 2);
            if (dates.length < 2) {
                throw new InputException("event");
            }
            Task event = new Event(eventParts[0].trim(), false, dates[0].trim(), dates[1].trim());
            taskList.addTask(event);
            storage.saveTasks(taskList.getTasks());
            return ui.printTaskAdded(event, taskList.getTotalTask());
        case "find":
            if (inputs.length < 2) {
                throw new InputException("find");
            }
            String keyword = inputs[1].trim();
            ArrayList<Task> matchedTasks = new ArrayList<>();

            for (Task task : taskList.getTasks()) {
                if (task.getName().contains(keyword)) {
                    matchedTasks.add(task);
                }
            }
            return ui.printMatchedTasks(matchedTasks);

        default:
            throw new InputException("Invalid");
        }
    }
}
