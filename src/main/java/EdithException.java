public class EdithException extends IllegalArgumentException {
    private final String lineBreak = "\n_______________________________________________________________________\n";
    private final String indentation = "    ";

    public EdithException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        String string = " Please provide a valid instruction with the correct relevant details.";
        return indentation + getMessage() + string + lineBreak;
    }
}