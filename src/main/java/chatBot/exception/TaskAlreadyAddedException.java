package chatbot.exception;

import chatbot.task.Task;

/** Exception thrown when duplicate is found */
public class TaskAlreadyAddedException extends Exception {
    /** Constructor method */
    public TaskAlreadyAddedException(Task task) {
        super("Task has previously been added: " + task.getDescription());
    }
}
