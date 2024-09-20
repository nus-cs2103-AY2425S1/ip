package colby;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Colby {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public Colby(String file) throws FileNotFoundException {
        ui = new Ui();
        storage = new Storage(file);
        taskList = storage.buildList();
    }

    /**
     * Returns a string based on the input that the user enters, shows an unknown command message if
     * input is not one of the available commands
     * @param input the string that the user inputs into the text box
     * @return string of what happens once the respective command is executed
     * @throws IOException
     */
        public String getResponse(String input) throws IOException {
            String command = Parser.parseCommand(input);
            try {
                if (command.equalsIgnoreCase("hello")) {
                    return ui.showWelcomeMessage();
                } else if (command.equalsIgnoreCase("bye")) {
                    return ui.showGoodbyeMessage();
                } else if (command.equalsIgnoreCase("list")) {
                    return "Here's all the tasks you have to do: \n" + storage.returnFileContents();
                } else if (command.startsWith("mark")) {
                    int index = Parser.parseIndex(input);
                    taskList.markTaskAsDone(index);
                    storage.writeToFile(taskList.getTasks());
                    return ui.showTaskMarked(taskList.getTask(index));
                } else if (command.startsWith("unmark")) {
                    int index = Parser.parseIndex(input);
                    taskList.markTaskAsUndone(index);
                    storage.writeToFile(taskList.getTasks());
                    return ui.showTaskUnmarked(taskList.getTask(index));
                } else if (command.startsWith("todo")) {
                    Task task = Parser.parseToDoTask(input);
                    taskList.addTask(task);
                    storage.appendToFile(task.toString() + "\n");
                    return ui.showTaskAdded(task, taskList.size());
                } else if (command.startsWith("deadline")) {
                    Task task = Parser.parseDeadlineTask(input);
                    taskList.addTask(task);
                    storage.appendToFile(task.toString() + "\n");
                    return ui.showTaskAdded(task, taskList.size());
                } else if (command.startsWith("event")) {
                    Task task = Parser.parseEventTask(input);
                    taskList.addTask(task);
                    storage.appendToFile(task.toString() + "\n");
                    return ui.showTaskAdded(task, taskList.size());
                } else if (command.startsWith("delete")) {
                    int index = Parser.parseIndex(input);
                    Task task = taskList.deleteTask(index);
                    storage.writeToFile(taskList.getTasks());
                    return ui.showTaskDeleted(task);
                } else if (command.startsWith("find")) {
                    return taskList.printMatchingTasks(input.split(" ", 2)[1]);
                } else {
                    throw new ColbyException("Sorry!! I'm not sure how to add that to the list for you, " +
                            "try specifying the type of task!");
                }
            } catch (ColbyException e) {
                String message = e.getMessage();
                return message;
            }
        }
}