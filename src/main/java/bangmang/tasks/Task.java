package bangmang.tasks;

import java.time.LocalDateTime;

import bangmang.exception.InvalidTaskFormatException;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task (String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        /**
         * Returns description
         */
        return this.description;
    }

    public boolean getIsDone() {
        /**
         * Returns isDone status
         */
        return isDone;
    }

    public Task markTask() {
        /** 
         * Marks the task if unmarked & returns task.
         */
        this.isDone = true;
        return this;
    }

    public Task unmarkTask() {
        /** 
         * Unmarks the task if marked & returns task.
         */
        this.isDone = false;
        return this;
    }

    public String toString() {
        /** 
         * Returns the String output
         */
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }

    public static Task readSavedTask(String taskString) throws InvalidTaskFormatException {
        /** 
         * Reads String version of task and returns task output
         */
    
        String[] parts = taskString.split(" \\| ");
        if (parts.length < 3) {
            throw new InvalidTaskFormatException("Invalid task format: " + taskString);
        }
    
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        
        // Check task type and create corresponding Task
        if (taskType.equals("T")) {
            return new Todo(description, isDone);

        } else if (taskType.equals("D")) {
            if (parts.length != 4) {
                throw new InvalidTaskFormatException("Invalid Deadline format: " + taskString);
            }
            LocalDateTime by = LocalDateTime.parse(parts[3]);
            return new Deadline(description, isDone, by);

        } else if (taskType.equals("E")) {
            if (parts.length != 5) {
                throw new InvalidTaskFormatException("Invalid Event format: " + taskString);
            }

            LocalDateTime from = LocalDateTime.parse(parts[3]);
            LocalDateTime to = LocalDateTime.parse(parts[4]);
            return new Event(description, isDone, from, to);

        } else {
            throw new InvalidTaskFormatException("Unknown task type: " + taskType);
        }
    }

    public String writeSavedTask() throws InvalidTaskFormatException {
        /** 
         * Writes String version of task in the format to save
         */

        String spacer = " | ";
        String taskType = null;
        String isDone = this.isDone ? "1" : "0";
        String description = "";
        LocalDateTime by = null;
        LocalDateTime from = null;
        LocalDateTime to = null;

        if (this instanceof Todo) {
            taskType = "T";
            description = this.description;
            return taskType + spacer + isDone + spacer + description;

        } else if (this instanceof Deadline d) {
            taskType = "D";
            description = d.description;
            by = d.by;
            return taskType + spacer + isDone + spacer + description + spacer + by;

        } else if (this instanceof Event e) {
            taskType = "E";
            description = e.description;
            from = e.from;
            to = e.to;
            return taskType + spacer + isDone + spacer + description + spacer + from + spacer + to;
        } else {
            throw new InvalidTaskFormatException("Cannot write task: " + this.toString());
        }
    }
    
}