package com.appleaster.command;
import java.time.LocalDate;

import com.appleaster.task.Task;

/**
 * Represents a command in the Appleaster application.
 * This class encapsulates different types of commands and their associated data.
 */
public class Command {
    private final CommandType type;
    private Task task;
    private int taskIndex;
    private LocalDate date;
    private String keyword;    

    /**
     * Constructs a Command with only a type.
     * 
     * @param type the type of the command
     */
    public Command(CommandType type) {
        this.type = type;
    }

    /**
     * Constructs a Command with a type and a keyword.
     * Typically used for search operations.
     * 
     * @param type the type of the command
     * @param keyword the keyword associated with the command
     */
    public Command(CommandType type, String keyword) {
        this.type = type;
        this.keyword = keyword;
    }    

    /**
     * Constructs a Command with a type and a task.
     * Typically used when adding a new task.
     * 
     * @param type the type of the command
     * @param task the task associated with the command
     */
    public Command(CommandType type, Task task) {
        this.type = type;
        this.task = task;
    }

    /**
     * Constructs a Command with a type and a task index.
     * Typically used for operations on existing tasks (e.g., marking as done, deleting).
     * 
     * @param type the type of the command
     * @param taskIndex the index of the task in the task list
     */
    public Command(CommandType type, int taskIndex) {
        this.type = type;
        this.taskIndex = taskIndex;
    }

    /**
     * Constructs a Command with a type and a date.
     * Typically used for date-based operations (e.g., viewing tasks for a specific date).
     * 
     * @param type the type of the command
     * @param date the date associated with the command
     */
    public Command(CommandType type, LocalDate date) {
        this.type = type;
        this.date = date;
    }

    /**
     * Gets the type of the command.
     * 
     * @return the command type
     */
    public CommandType getType() {
        return type;
    }

    /**
     * Gets the task associated with the command, if any.
     * 
     * @return the task, or null if no task is associated with this command
     */
    public Task getTask() {
        return task;
    }

    /**
     * Gets the task index associated with the command, if any.
     * 
     * @return the task index
     */
    public int getTaskIndex() {
        return taskIndex;
    }

    /**
     * Gets the date associated with the command, if any.
     * 
     * @return the date, or null if no date is associated with this command
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the keyword associated with the command, if any.
     * 
     * @return the keyword, or null if no keyword is associated with this command
     */
    public String getKeyword() {
        return keyword;
    }    
}