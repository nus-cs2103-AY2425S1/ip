public class TaskException extends Exception {
    public TaskException(String str) {
        super(str);
    }

    @Override
    public String toString() {
        return "Mel sees something missing? Mel expected:\n"
                + "  " + super.getMessage() + " :((";
    }
}
