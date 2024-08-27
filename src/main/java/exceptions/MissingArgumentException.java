package exceptions;

/**
 * Represents exceptions thrown by the chatbot.
 * Occurs when user tries to input a task in the wrong format.
 */
public class MissingArgumentException extends AliceException {
    protected String taskType;
    protected String[] items;

    public MissingArgumentException(String taskType, String[] items) {
        super(taskType);
        this.taskType = taskType;
        this.items = items;
    }

    /**
     * Returns string representation of the exception.
     *
     * @return message informing users of the specific arguments to that command.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (String item : items) {
            s.append(item);
            s.append(", ");
        }
        s.delete(s.length() - 2, s.length());
        return String.format("%s requires %s as arguments", this.taskType, s);
    }
}
