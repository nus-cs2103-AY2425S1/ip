package cheese.task;

import cheese.CheeseException;

public class ToDo extends Task{
    private ToDo(String name) throws CheeseException {
        super(name);
    }

    /**
     * Factory method to ensure correct creation of Cheese.ToDo
     * @param input String
     * @return Cheese.ToDo
     * @throws CheeseException custom exception
     */
    public static ToDo of(String input) throws CheeseException {
        String name = input.replace("todo", "").strip();
        return new ToDo(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
