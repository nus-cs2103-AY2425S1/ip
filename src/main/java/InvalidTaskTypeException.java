public class InvalidTaskType extends Exception {
    public InvalidTaskType(String message) {
        super(message);
    }
    public InvalidTaskType() {
        super("Invalid Task Type");
    }

    @Override
    public String toString() {
        return "Yo Homieee, i did not catch what you are saying!\n Please use deadline, event or todo formats, type help if you are unsure of the formats!";
    }
}
