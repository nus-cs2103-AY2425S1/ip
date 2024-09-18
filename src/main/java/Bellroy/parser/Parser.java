package Bellroy.parser;

import Bellroy.storage.Storage;
import Bellroy.task.TaskList;
import Bellroy.GUI.Ui;
import Bellroy.task.Event;
import Bellroy.task.Task;
import Bellroy.task.Todo;
import Bellroy.task.Deadline;
import javafx.application.Platform;

import java.io.IOException;
import java.util.List;

public class Parser {

    public String parse(String userInput, TaskList taskList, Ui ui, Storage storage) {
        String[] input;
        if (userInput.startsWith("find ") || userInput.startsWith("tag ") || userInput.startsWith("filter ")) {
            input = userInput.split(" ", 2);
        } else {
            input = userInput.split(" /", 2);
        }

        String type = input[0].split(" ")[0].toLowerCase();
        String description = input[0].substring(type.length());
        try {
            switch (type) {
                case("bye"):
                    Platform.exit();
                    return Ui.byeMessage();
                case("list"):
                    return Ui.printTaskList(taskList);
                case("mark"):
                    int position = Integer.parseInt(userInput.split(" ")[1]);
                    taskList.get(position - 1).markDone();
                    storage.save(taskList);
                    return Ui.markedDone(taskList.get(position - 1));
                case("unmark"):
                    int pos = Integer.parseInt(userInput.split(" ")[1]);
                    taskList.get(pos - 1).undo();
                    storage.save(taskList);
                    return Ui.markedUndone(taskList.get(pos - 1));
                case("todo"):
                    Task todo = new Todo(description);
                    taskList.addTask(todo);
                    storage.save(taskList);
                    return Ui.taskAddedMessage(todo, taskList.size());
                case("deadline"):
                    String dueDate = input[1].split(" ", 2)[1].trim();
                    Task deadline = new Deadline(description, dueDate);
                    taskList.addTask(deadline);
                    storage.save(taskList);
                    return Ui.taskAddedMessage(deadline, taskList.size());
                case("event"):
                    String startTime = input[1].split(" /", 2)[0].split(" ", 2)[1].trim();
                    String endTime = input[1].split(" /", 2)[1].split(" ", 2)[1].trim();
                    Task event = new Event(description, startTime, endTime);
                    taskList.addTask(event);
                    storage.save(taskList);
                    return Ui.taskAddedMessage(event,taskList.size());
                case("delete"):
                    int target = Integer.parseInt(userInput.split(" ")[1]);
                    Task taskToDelete = taskList.get(target - 1);
                    taskList.removeTask(target - 1);
                    storage.save(taskList);
                    return Ui.taskDeleted(taskToDelete, taskList.size());
                case("find"):
                    String keyword = input[1].trim();
                    TaskList output = taskList.findTask(keyword);
                    return Ui.findTask(output);
                case("tag"):
                    String[] tagInput = input[1].split(" ", 2);
                    int tagPos = Integer.parseInt(tagInput[0]);
                    String association = tagInput[1];
                    taskList.get(tagPos - 1).setAssociation(association);
                    return Ui.associationMessage(taskList.get(tagPos - 1), association);
                case("filter"):
                    String taskAssociation = input[1].trim();
                    TaskList result = taskList.filterAssociation(taskAssociation);
                    return Ui.filterTask(result);
                default:
                    return ("ERROR: Invalid Input!");
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
