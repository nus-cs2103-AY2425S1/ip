public class Todo extends Task{

    /**
     * Constructor to initialise a task.
     *
     * @param description Input based on user.
     */
    public Todo(String description) {
        super(description);
    }


    // Returns the letter representing todo.
    @Override
    public String taskLetter() {
        return "T";
    }
}
