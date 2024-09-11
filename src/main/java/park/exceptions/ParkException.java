package park.exceptions;

/**
 * Represents exceptions specific to the Park class.
 */
public class ParkException extends Exception {

    public ParkException(String message) {
        super(message);
    }

    public static ParkException invalidIndexException() {
        return new ParkException("invalid index");
    }

    public static ParkException missingIndexException() {
        return new ParkException("missing index");
    }

    public static ParkException invalidInputException() {
        return new ParkException("invalid input");
    }

    public static ParkException missingDescException() {
        return new ParkException("please provide a description");
    }

    public static ParkException deadlineFormatException() {
        return new ParkException("please use the format: desc /by deadline");
    }

    public static ParkException dateTimeFormatException() {
        return new ParkException("please input DateTime in format: yyyy-MM-dd HHmm");
    }

    public static ParkException eventFormatException() {
        return new ParkException("please use the format: desc /from start /to end");
    }

    public static ParkException missingKeywordException() {
        return new ParkException("please provide a keyword");
    }

    public static ParkException createFileException() {
        return new ParkException("error creating file");
    }

    public static ParkException loadFileException() {
        return new ParkException("error loading file");
    }

    public static ParkException writeFileException() {
        return new ParkException("error writing to file");
    }

    public static ParkException fileCorruptedException() {
        return new ParkException("file corrupted, loading new empty task list");
    }
}
