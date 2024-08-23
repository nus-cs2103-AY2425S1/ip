// Custom exception for unknown commands
class UnknownCommandException extends Exception {
    public UnknownCommandException(String message) {
        super(message);
    }
}