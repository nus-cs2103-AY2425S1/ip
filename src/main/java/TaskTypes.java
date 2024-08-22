public enum TaskTypes {
    TODO("todos"),
    DEADLINE("deadlines"),
    EVENT("event");

    private final String description;

    TaskTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
