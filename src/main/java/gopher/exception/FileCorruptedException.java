package gopher.exception;

/**
 * Thrown if the file handled during the operation can't be parsed or read.
 */
public class FileCorruptedException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Oops!!! I have detected task file on your machine that I can't really understand...\n"
                + "It seems like your task list has corrupted due to unexpected issues...\n"
                + "You can manually recover your previous tasks by fixing any issues with the task file,\n"
                + "or you can still to track another set of tasks from scratch by start giving me commands!\n"
                + "Sorry for the inconvenience caused...";
    }
}
