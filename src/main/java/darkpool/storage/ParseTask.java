package darkpool.storage;

import darkpool.DarkpoolException;
import darkpool.task.After;
import darkpool.task.Deadline;
import darkpool.task.Event;
import darkpool.task.Task;
import darkpool.task.Todo;


/**
 * Parses a task from a string.
 */
class ParseTask {

    /**
     * Parses a task from a string.
     *
     * @param task The string representation of the task.
     * @return The parsed task.
     * @throws DarkpoolException If the task is in an invalid format.
     */
    static Task parse(String task) throws DarkpoolException {
        String[] taskParts = task.split(" \\| ");
        TaskType type = TaskType.fromCode(taskParts[0]);
        boolean isDone = taskParts[1].equals("1");
        String description = taskParts[2];

        switch (type) {
        case EVENT -> {
            String from = taskParts[3];
            String to = taskParts[4];
            return new Event(description, from, to, isDone);
        }
        case DEADLINE -> {
            String by = taskParts[3];
            return new Deadline(description, by, isDone);
        }
        case AFTER -> {
            String from = taskParts[3];
            return new After(description, from, isDone);
        }
        case TODO -> {
            return new Todo(description, isDone);
        }
        default -> throw new DarkpoolException("Unknown task type");
        }
    }
}

