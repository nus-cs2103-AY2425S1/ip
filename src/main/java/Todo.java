public class Todo extends Task{

    public Todo(String description) throws IllegalArgumentException {
        super(description);
        if (description.isEmpty()) {
            throw new IllegalArgumentException("The description of a todo cannot be empty!");
        }
    }

    @Override
    public String toSaveFormat() {
        // stores whether task has been marked as done - 1 for marked and 0 for unmarked
        String storeCompleted = "";

        if (this.isDone) {
            storeCompleted = "1";
        } else {
            storeCompleted = "0";
        }
        // store format: T | 1 | tutorial

        return "T | " + storeCompleted + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
