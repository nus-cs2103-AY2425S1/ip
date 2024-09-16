package com.appleaster.main;

import com.appleaster.command.Command;
import com.appleaster.command.CommandType;
import com.appleaster.exception.AppleasterException;
import com.appleaster.parser.Parser;
import com.appleaster.storage.Storage;
import com.appleaster.task.TaskList;
import com.appleaster.task.Task;

import java.time.LocalDate;

public class Appleaster {
    private final Storage storage;
    private TaskList tasks;

    public Appleaster(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (AppleasterException e) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            return executeCommand(c);
        } catch (AppleasterException e) {
            return "Oops! " + e.getMessage();
        }
    }

    private String executeCommand(Command c) {
        try {
            switch (c.getType()) {
                case LIST:
                    return tasks.getTaskListString();
                case MARK:
                case UNMARK:
                    tasks.markTask(c.getTaskIndex(), c.getType() == CommandType.MARK);
                    return "Task " + (c.getTaskIndex() + 1) + " marked as " + (c.getType() == CommandType.MARK ? "done" : "not done");
                case TODO:
                case DEADLINE:
                case EVENT:
                    tasks.addTask(c.getTask());
                    return "Added: " + c.getTask().toString();
                case DELETE:
                    Task deletedTask = tasks.deleteTask(c.getTaskIndex());
                    return "Deleted: " + deletedTask.toString();
                case DATE:
                    return tasks.getTasksOnDateString(c.getDate());
                case FIND:
                    return tasks.getMatchingTasksString(c.getKeyword());
                case VIEW_SCHEDULE:
                    return viewSchedule(c.getDate());
                case BYE:
                    storage.save(tasks.getTasks());
                    return "Goodbye! Remember, an apple a day keeps the doctor away, but I hope to see you sooner!";
                default:
                    return "I'm sorry, I don't understand that command.";
            }
        } catch (AppleasterException e) {
            return "Oops! " + e.getMessage();
        }
    }

    private String viewSchedule(LocalDate date) {
        return tasks.getScheduleForDateString(date);
    }
}