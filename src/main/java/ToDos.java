public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
    }

    /**
     * Returns the ToDos as a string with its status and description
     *
     * @return a String of the ToDos
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the ToDos as a fancier string with its status and description
     * Meant for recording in text files
     *
     * @return Fancier string of the ToDos
     */
    @Override
    public String toFancyString() {
        return "ToDo | " + super.getStatus() + " | " +
                super.getDescription();
    }
}
