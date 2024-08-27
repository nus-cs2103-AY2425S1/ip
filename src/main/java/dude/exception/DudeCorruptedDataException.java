package dude.exception;

public class DudeCorruptedDataException extends DudeException {
    public DudeCorruptedDataException(){
        super("Your data seems to be corrupted, corrupted data will be ignored and deleted.");
    }
}
