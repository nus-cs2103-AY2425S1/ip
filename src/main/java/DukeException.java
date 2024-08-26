public class DukeException extends Exception {
    private final String LINE = "______________________________________________________________";
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return  LINE + "\n" + super.getMessage() + "\n" + LINE;

    }
}
