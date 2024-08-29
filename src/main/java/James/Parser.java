package james;

import java.time.LocalDateTime;

public class Parser {
    private Ui ui;
    private Storage storage;

    public Parser(Ui ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;
    }

    public boolean parseAndExecute(String command, TaskList taskList) throws JamesException {
        String[] words = command.split(" ");
        String action = words[0].toLowerCase();

        switch (action) {
            case "bye":
                ui.showExitMessage();
                return true;

            case "list":
                taskList.printTasks();
                break;

            case "mark":
                int markTaskNum = Integer.parseInt(words[1]);
                taskList.markTask(markTaskNum - 1);
                ui.showMessage("Task marked as done:");
                ui.showMessage(taskList.getTask(markTaskNum - 1).printTask());
                storage.saveTasks(taskList.getTasks());
                break;

            case "unmark":
                int unmarkTaskNum = Integer.parseInt(words[1]);
                taskList.unmarkTask(unmarkTaskNum - 1);
                ui.showMessage("Task marked as not done:");
                ui.showMessage(taskList.getTask(unmarkTaskNum - 1).printTask());
                storage.saveTasks(taskList.getTasks());
                break;

            case "todo":
                String todoDescription = command.substring(4).trim();
                Task todoTask = new Todo(todoDescription, false);
                taskList.addTask(todoTask);
                ui.showMessage("Task added:\n" + todoTask.printTask());
                ui.showMessage(String.format("Now you have %d tasks in the list.", taskList.size()));
                storage.saveTasks(taskList.getTasks());
                break;

            case "deadline":
                String deadlineDescription = command.substring(8, command.lastIndexOf("/by")).trim();
                String deadline = command.substring(command.lastIndexOf("/by") + 4).trim();
                Task deadlineTask = new Deadline(deadlineDescription, false, LocalDateTime.parse(deadline));
                taskList.addTask(deadlineTask);
                ui.showMessage("Task added:\n" + deadlineTask.printTask());
                ui.showMessage(String.format("Now you have %d tasks in the list.", taskList.size()));
                storage.saveTasks(taskList.getTasks());
                break;

            case "event":
                String eventDescription = command.substring(5, command.lastIndexOf("/from")).trim();
                String start = command.substring(command.lastIndexOf("/from") + 6, command.lastIndexOf("/to")).trim();
                String end = command.substring(command.lastIndexOf("/to") + 4).trim();
                Task eventTask = new Event(eventDescription, false, LocalDateTime.parse(start), LocalDateTime.parse(end));
                taskList.addTask(eventTask);
                ui.showMessage("Task added:\n" + eventTask.printTask());
                ui.showMessage(String.format("Now you have %d tasks in the list.", taskList.size()));
                storage.saveTasks(taskList.getTasks());
                break;

            case "delete":
                int deleteTaskNum = Integer.parseInt(words[1]);
                ui.showMessage("Task removed:\n" + taskList.getTask(deleteTaskNum - 1).printTask());
                taskList.deleteTask(deleteTaskNum - 1);
                ui.showMessage(String.format("Now you have %d tasks in the list.", taskList.size()));
                storage.saveTasks(taskList.getTasks());
                break;

            default:
                throw new CommandNotFoundException("Sorry! I don't understand what you mean by (" + command + "). Please try a different command!");
        }
        return false;
    }
}
