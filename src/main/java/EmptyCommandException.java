public class EmptyCommandException extends Exception {
    @Override
    public String toString() {
        return "Warning! No commands have been detected, please try again!";
    }
}