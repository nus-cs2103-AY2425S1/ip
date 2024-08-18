public class Todo {
    private String description;

    /**
     * Create a new Todo object
     * @param description The description of the Todo Object
     */
    public Todo(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
