public class DevonInvalidTaskNumberException extends DevonException {

    @Override
    public String toString() {
        return super.toString() + " Task not found!";
    }
}
