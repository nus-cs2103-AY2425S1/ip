package shnoop.exceptions;

/**
 * Encapsulates the situation where the text file to be read is not in a valid format to be read from.
 */
public class ImproperFileTypeException extends Exception {

    public ImproperFileTypeException(String message) {
        super(message);
    }

    public ImproperFileTypeException() {
        super("✿ Shnoop ✿: The file is in the wrong format. You could try rectifying it or deleting the file. \n");
    }
}
