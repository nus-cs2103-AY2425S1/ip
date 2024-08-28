package commands;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.LlamaException;
import data.TaskList;
import data.Storage;
import data.Task;
import data.Todo;
import data.Deadline;
import data.Event;
import ui.Ui;

public class AddCommand implements Command {
    public enum TaskType{
        TODO,
        DEADLINE,
        EVENT
    }
    private String remaining;
    private TaskType taskType;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public AddCommand(String remaining, TaskType taskType) {
        this.remaining = remaining;
        this.taskType = taskType;
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        if (taskType == TaskType.TODO) {
            if (remaining.isBlank()) {
                throw new LlamaException("Empty Todo Task?!? Might as well ask me to not add it in!");
            }

            Task newTask = new Todo(remaining, false);
            taskList.addTask(newTask , ui);
            storage.save(taskList);
        } else if (taskType == TaskType.DEADLINE) {
            if (remaining.isBlank()) {
                throw new LlamaException("Empty Deadline?!? What's the point of keeping track of nothing?");
            }

            String[] substringArr = remaining.split("/by ");
            LocalDateTime deadline;
            try {
                deadline = LocalDateTime.parse(substringArr[1].trim(), FORMATTER);
            } catch (DateTimeParseException e) {
                throw new LlamaException("Invalid date given, please give in the format " +
                        "`deadline <name>/by yyyy-mm-dd HH:mm'");
            }

            Task newTask = new Deadline(substringArr[0], deadline, false);
            taskList.addTask(newTask, ui);
            storage.save(taskList);
        } else if (taskType == TaskType.EVENT) {
            if (remaining.isBlank()) {
                throw new LlamaException("Empty Event?!? You're planning to go nowhere with no one?");
            }

            String[] substringArr = remaining.split("/");
            String desc = substringArr[0];
            String startTimeStr = substringArr[1].substring(substringArr[1].indexOf(" ") + 1).trim();
            String endTimeStr = substringArr[2].substring(substringArr[2].indexOf(" ") + 1).trim();
            LocalDateTime startTime;
            LocalDateTime endTime;

            try {
                startTime = LocalDateTime.parse(startTimeStr, FORMATTER);
                endTime = LocalDateTime.parse(endTimeStr, FORMATTER);
            } catch (DateTimeParseException e) {
                throw new LlamaException("Invalid date given, please give in the format " +
                        "`event <name> /from yyyy-MM-dd HH:mm /to  yyyy-MM-dd HH:mm'");
            }

            Task newTask = new Event(desc, startTime, endTime, false);
            taskList.addTask(newTask, ui);
            storage.save(taskList);
        }

        return true;
    }
}
