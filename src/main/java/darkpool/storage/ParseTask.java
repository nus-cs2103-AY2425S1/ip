package darkpool.storage;

import darkpool.task.Task;
import darkpool.task.Event;
import darkpool.task.Deadline;
import darkpool.task.After;
import darkpool.task.Todo;
import darkpool.DarkpoolException;

class ParseTask {
    static Task parse(String task) throws DarkpoolException {
        String[] taskParts = task.split(" \\| ");
        String type = taskParts[0];
        boolean isDone = taskParts[1].equals("1");
        String description = taskParts[2];
        String from;
        String to;
        String by;

        switch (type) {
        case "E" -> {
            from = taskParts[3];
            to = taskParts[4];
            return new Event(description, from, to, isDone);
        }
        case "D" -> {
            by = taskParts[3];
            return new Deadline(description, by, isDone);
        }
        case "A" -> {
            from = taskParts[3];
            return new After(description, from, isDone);
        }
        case "T" -> {
            return new Todo(description, isDone);
        }
        default -> {
            System.out.println("Unknown task type: " + type);
            return null;
        }
        }
    }
}

