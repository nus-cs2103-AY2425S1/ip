package spongebob.task;

import spongebob.exception.SpongebobException;

public class Todo extends Task {
    /**
     * Constructs a todo task after checking if deadline to be in correct format
     * @param description description of task
     * @throws SpongebobException   user input error such as incorrect date format
     */
    public Todo(String description) throws SpongebobException {
        super(description, TaskType.TODO);

        // checks if fields are missing
        if (description.equals(" ") || description.isEmpty()) {
            throw new SpongebobException("Barnacles! You can't enter an empty todo!");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + super.getTag();
    }

    /**
     * Converts task into a string that can be stored and read later
     * @return String version of the task
     */
    @Override
    public String save() {
        return super.save();
    }
}
