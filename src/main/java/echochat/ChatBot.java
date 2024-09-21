package echochat;

import exceptions.IndexOutOfBoundsError;

public class ChatBot {
    private Ui ui;
    private Parser parser;
    private TaskList taskList;

    public ChatBot(String name) {
        this.ui = new Ui(name);
        this.parser = new Parser();
        this.taskList = new TaskList();
        this.taskList.loadTasksFromFile();
        ui.greet();
    }

    /**
     * Checks if there is a duplicate task in tasklist.
     *
     * @param task
     * @return true if there is duplicate task, else false.
     */
    public boolean isDuplicate(Task task) {
        for (Task existingTask : taskList.getTaskList()) {
            if (task.equals(existingTask)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Provides response to user based on input.
     *
     * @param input User input.
     * @return Chatbot's response.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);

            if (command == null) {
                ui.showError("Invalid input.");
            }

            switch (command.getType()) {
            case EXIT:
                return ui.goodbye();
            case LIST:
                return ui.showTaskList(taskList.getTaskList());
            case MARK:
                if (command.getIndex() - 1 < 0 || command.getIndex() - 1 >= taskList.getLength()) {
                    throw new IndexOutOfBoundsError();
                }
                Task taskToMark = taskList.getTaskList().get(command.getIndex() - 1);
                taskToMark.markDone();
                return ui.showTaskMarked(taskToMark);
            case UNMARK:
                if (command.getIndex() - 1 < 0 || command.getIndex() - 1 >= taskList.getLength()) {
                    throw new IndexOutOfBoundsError();
                }
                Task taskToUnmark = taskList.getTaskList().get(command.getIndex() - 1);
                taskToUnmark.markUndone();
                return ui.showTaskUnmarked(taskToUnmark);
            case DELETE:
                Task removedTask = taskList.delete(command.getIndex());
                return ui.showTaskDeleted(removedTask, taskList.getTaskList().size());
            case FIND:
                return ui.showFoundTasks(command.getDescription(), taskList.getTaskList());
            case TODO:
            case DEADLINE:
            case EVENT:
                Task newTask = command.getTask();
                if (isDuplicate(newTask)) {
                    return ui.showDuplicate();
                }
                taskList.addToList(newTask);
                return ui.showTaskAdded(newTask, taskList.getTaskList().size());
            case UNKNOWN:
                return ui.showError("Unknown command.");
            default:
                return ui.showError("Unknown command.");

            }
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }
}
