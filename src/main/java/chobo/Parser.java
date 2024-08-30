package chobo;

public class Parser {
    public static boolean parse(String fullCommand, TaskList taskList, Ui ui, Storage storage) throws InputException {
        String[] input = fullCommand.split(" ", 2);
        String action = input[0];

        switch (action) {
            case "bye":
                return true;

            case "list":
                ui.printTaskList(taskList.getTasks());
                break;

            case "mark":
            case "unmark":
            case "delete":
                if (input.length < 2) {
                    throw new InputException("id");
                }
                int taskId = Integer.parseInt(input[1].trim()) - 1;
                if (taskId < 0 || taskId >= taskList.getTotalTask()) {
                    throw new InputException("id");
                }
                if (action.equals("mark")) {
                    taskList.getTask(taskId).mark();
                    ui.showTaskMarked(taskList.getTask(taskId));
                } else if (action.equals("unmark")) {
                    taskList.getTask(taskId).unmark();
                    ui.showTaskUnmarked(taskList.getTask(taskId));
                } else {
                    Task removedTask = taskList.getTask(taskId);
                    taskList.deleteTask(taskId);
                    ui.printTaskDeleted(removedTask, taskList.getTotalTask());
                }
                storage.saveTasks(taskList.getTasks());
                break;

            case "todo":
                if (input.length < 2) {
                    throw new InputException("todo");
                }
                Task todo = new ToDo(input[1].trim(), false);
                taskList.addTask(todo);
                ui.printTaskAdded(todo, taskList.getTotalTask());
                storage.saveTasks(taskList.getTasks());
                break;

            case "deadline":
                if (input.length < 2) {
                    throw new InputException("deadline");
                }
                String[] deadlineParts = input[1].split("/by", 2);
                if (deadlineParts.length < 2) {
                    throw new InputException("deadline");
                }
                Task deadline = new Deadline(deadlineParts[0].trim(), false, deadlineParts[1].trim());
                taskList.addTask(deadline);
                ui.printTaskAdded(deadline, taskList.getTotalTask());
                storage.saveTasks(taskList.getTasks());
                break;

            case "event":
                if (input.length < 2) {
                    throw new InputException("event");
                }
                String[] eventParts = input[1].split("/from", 2);
                if (eventParts.length < 2) {
                    throw new InputException("event");
                }
                String[] dates = eventParts[1].split("/to", 2);
                if (dates.length < 2) {
                    throw new InputException("event");
                }
                Task event = new Event(eventParts[0].trim(), false, dates[0].trim(), dates[1].trim());
                taskList.addTask(event);
                ui.printTaskAdded(event, taskList.getTotalTask());
                storage.saveTasks(taskList.getTasks());
                break;

            default:
                throw new InputException("Invalid");
        }
        return false;
    }
}
