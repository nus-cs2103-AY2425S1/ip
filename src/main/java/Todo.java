public class Todo extends Task {
    protected Todo(String description) {
        super(description);
    }

    protected Todo(String description, String status) {
        super(description, Status.valueOf(status));
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[T]");
        str.append(super.toString());
        return str.toString();
    }

    /**
     * Returns a csv representation of this todo.
     *
     * @return A string in the form "Todo,(task description),(task status)"
     */
    @Override
    protected String toCsv() {
        StringBuilder csv = new StringBuilder();
        csv.append("Todo,");
        csv.append(super.toCsv());
        return csv.toString();
    }
}
