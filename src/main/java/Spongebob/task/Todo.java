package Spongebob.task;

import Spongebob.exception.SpongebobException;

public class Todo extends Task {
    /**
     * constructor for a todo task, checks if deadline to be in correct format
     * @param description description of task
     * @throws SpongebobException   user input error such as incorrect date format
     */
    public Todo(String description) throws SpongebobException {
        super(description, TaskType.TODO);
        // check for errors
        if (description.equals(" ") || description.isEmpty()) {
            throw new SpongebobException("Barnacles! You can't enter an empty todo!");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * converts task into a string that can be stored and read later
     * @return String version of the task
     */
    @Override
    public String save()  {
        return super.save();
    }
}
