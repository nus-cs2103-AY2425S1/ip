public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");


    private final String shortForm;

    TaskType(String shortForm) {
        this.shortForm = shortForm;
    }

    // return the short form
    @Override
    public String toString() {
        return this.shortForm;
    }
}
