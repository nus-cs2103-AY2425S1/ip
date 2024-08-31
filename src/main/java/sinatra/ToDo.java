package sinatra;

/**
 * Represents a ToDo task which extends the Task class.
 */
public class ToDo extends Task {

    /**
     * Creates a new ToDo object from a data string.
     *
     * @param data the data string containing the content and status separated by a comma
     * @return a new ToDo object
     */
    public static ToDo newObjectFromData(String data) {
        String[] parts = data.split(",");
        return new ToDo(parts[0], Boolean.parseBoolean(parts[1]));
    }

    /**
     * Constructs a new ToDo object with the specified content and status.
     *
     * @param content the content of the ToDo task
     * @param status the status of the ToDo task
     */
    public ToDo(String content, Boolean status) {
        super(content, status);
    }

    /**
     * Returns the data string for storage.
     *
     * @return the data string for storage
     */
    public String getDataForStorage() {
        return "Sinatra.ToDo:" + super.getContent() + "," + super.isMarkedString();
    }

    /**
     * Returns the string representation of the ToDo object.
     *
     * @return the string representation of the ToDo object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}