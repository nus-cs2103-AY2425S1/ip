public class EdithException extends IllegalArgumentException {
    private final String LINE_BREAK = "\n_______________________________________________________________________\n";
    private final String INDENTATION = "    ";

    public EdithException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        String string = " Please provide a valid instruction with the correct relevant details.";
        return INDENTATION + getMessage() + string + LINE_BREAK;
    }
}