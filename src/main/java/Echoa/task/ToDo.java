package echoa.task;

/**
 * ToDo is a class that encapsulates the characteristics of a ToDo Task.
 * It extends from the class Task.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Updates task according to input details.
     *
     * @param details containing new description
     */
    public void update(Object[] details) {
        assert details.length == 1;
        assert details[0] == null || details[0] instanceof String;

        String newDescription = (String) details[0];

        if (details[0] != null) {
            super.editDescription(newDescription);
        }
    }

    /**
     * The method converts the task to its text representation in the file.
     *
     * @return String representation of text.
     */
    @Override
    public String toText() {
        String completed = isDone ? "1" : "0";
        return "T | " + completed + " | " + super.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
