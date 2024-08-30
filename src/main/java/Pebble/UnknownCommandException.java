package pebble;

// Custom exception for unknown commands
class UnknownCommandException extends Exception {
    public UnknownCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}