package ollie;

public class CorruptFileException extends Exception{
    public CorruptFileException(String filepath) {
        super("File in " + filepath + " is corrupted.");
    }
}
