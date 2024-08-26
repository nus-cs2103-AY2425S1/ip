package main.java;

import main.java.Exceptions.EmptyDescriptionError;
import main.java.Exceptions.InvalidCommandError;

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

    public void run() {
        while (true) {
            try {
                String input = ui.getUserInput();
                Command command = parser.parse(input);

                if (command == null) {
                    ui.showError("Invalid input.");
                    continue;
                }

                switch (command.getType()) {
                    case EXIT:
                        ui.goodbye();
                        return;
                    case LIST:
                        ui.showTaskList(taskList.getTaskList());
                        break;
                    case MARK:
                        Task taskToMark = taskList.getTaskList().get(command.getIndex() - 1);
                        taskToMark.markDone();
                        ui.showTaskMarked(taskToMark);
                        break;
                    case UNMARK:
                        Task taskToUnmark = taskList.getTaskList().get(command.getIndex() - 1);
                        taskToUnmark.markUndone();
                        ui.showTaskUnmarked(taskToUnmark);
                        break;
                    case DELETE:
                        Task removedTask = taskList.delete(command.getIndex());
                        ui.showTaskDeleted(removedTask, taskList.getTaskList().size());
                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        Task newTask = command.createTask();
                        taskList.addToList(newTask);
                        ui.showTaskAdded(newTask, taskList.getTaskList().size());
                        break;
                    default:
                        ui.showError("Unknown command.");
                }

            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
