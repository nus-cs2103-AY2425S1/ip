public class DukeException extends IllegalArgumentException {
    public DukeException(String cat,String message) {
        super(message);
        String category = cat;
    }

    public DukeException(String message) {
        super(message);
    }

}
