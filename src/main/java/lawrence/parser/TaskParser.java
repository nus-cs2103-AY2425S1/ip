package lawrence.parser;

import lawrence.task.Task;

/**
 * This class is used to make sense of text read from a file.
 * <p>
 * The commands are translated from text into their relevant command object
 * counterparts as specified in {@link lawrence.task.TaskType}.
 * </p>
 */
public class TaskParser {
    /**
     * Converts the provided input string into a relevant {@link Task} object
     * based on the {@link InputSource} specified.
     * <p>
     * The input sources available are as specified in {@link InputSource} and are exhaustive.
     * If the input source is unrecognised, an {@link IllegalStateException} is thrown.
     * </p>
     *
     * @param input the string containing task information to be parsed
     * @return a {@link Task} object
     * @throws IllegalArgumentException if the input is invalid
     * @throws IllegalStateException if the {@link InputSource} is not recognised
     */
    public static Task createTask(String input, InputSource source)
            throws IllegalArgumentException, IllegalStateException {

        if (input.isEmpty()) {
            throw new IllegalArgumentException("Task input cannot be empty!");
        }

        TaskCreator creator;
        switch(source) {
        case FILE:
            creator = new FileTaskCreator();
            break;
        case USER:
            creator = new UserTaskCreator();
            break;
        default:
            // this case should never be reached
            throw new IllegalStateException("Unexpected source: " + source);
        }
        return creator.createTask(input);
    }
}
