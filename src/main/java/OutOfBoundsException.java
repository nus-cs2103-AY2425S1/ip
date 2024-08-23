public class OutOfBoundsException extends DukeException {
    OutOfBoundsException() {
        super("test");
    }

    @Override
    public String toString() {
        return "wah shag bro, you input a list number that hasn't been added to the list yet ";
    }
}
