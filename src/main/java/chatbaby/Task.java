package chatbaby;

import chatbaby.Command;
import chatbaby.Storage;
import chatbaby.Task;
import chatbaby.TaskType;
import chatbaby.TaskList;
import chatbaby.Ui;
import chatbaby.Parser;
import chatbaby.ChatBabyException;
import chatbaby.Deadline;
import chatbaby.Event;
import chatbaby.ToDo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    public String name;
    public boolean isDone;

    public Task(String name) {
        this.name =name;
        this.isDone = false;
    }

    public abstract String toFileText();

    public static Task fromFileText(String fileFormat) throws Exception {
        String[] text = fileFormat.split(" \\| ");
        String taskType = text[0];
        boolean isDone = text[1].equals("1");
        String name = text[2];

        Task task;
        if (taskType.equals("T")) {
            task = new ToDo(name);
        } else if (taskType.equals("D")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
            LocalDateTime deadline = LocalDateTime.parse(text[3], formatter);
            task = new Deadline(name, deadline);
        } else if (taskType.equals("E")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
            LocalDateTime from = LocalDateTime.parse(text[3], formatter);
            LocalDateTime to = LocalDateTime.parse(text[4], formatter);
            task = new Event(name, from, to);
        } else {
            throw new Exception("Invalid task type");
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unMarkAsDone() {
        isDone = false;
    }

    public boolean isOnDate(LocalDate date) {
        if (this instanceof Deadline) {
            Deadline deadlineTask = (Deadline) this;
            if (deadlineTask.getDeadline().toLocalDate().equals(date)) {
                return true;
            }
        } else if (this instanceof Event) {
            Event eventTask = (Event) this;
            if (eventTask.getTo().toLocalDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return isDone ? "[X] " + name : "[ ] " + name;
    }
}
