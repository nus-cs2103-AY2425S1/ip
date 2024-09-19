package pebble;

/**
 * Throws exception when input is unable to be parsed properly
 */
class InvalidInputFormatException extends Exception {
    public InvalidInputFormatException(String message) {
        super(message);
    }
}
