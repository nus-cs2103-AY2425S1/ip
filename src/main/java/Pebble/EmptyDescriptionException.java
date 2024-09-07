package pebble;

// Custom exception for empty descriptions (unused)
class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
