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





}
