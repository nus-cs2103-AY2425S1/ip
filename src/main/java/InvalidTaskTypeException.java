public class InvalidTaskTypeException extends Exception {
    public InvalidTaskTypeException(String message) {
        super(message);
    }
    public InvalidTaskTypeException() {
        super("Invalid Task Type");
    }

    @Override
    public String toString() {
        return "Yo Homieee, i did not catch what you are saying!\nPlease use deadline, event, todo, or other command formats, type help if you are unsure of the formats!";
    }
}
