public class BlitzCommandDoesNotExistException extends BlitzException {
    @Override
    public String toString() {
        return "Command does not exist!";
    }
}
