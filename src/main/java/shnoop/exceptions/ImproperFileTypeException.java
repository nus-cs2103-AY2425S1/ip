package shnoop.exceptions;
public class ImproperFileTypeException extends Exception {

    public ImproperFileTypeException(String message) {
        super(message);
    }

    public ImproperFileTypeException() {
        super("✿ Shnoop ✿: The file is in the wrong format. You could try rectifying it or deleting the file. \n");
    }
}
