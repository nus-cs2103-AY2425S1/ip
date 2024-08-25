package orion.orionExceptions;

public class InvalidIndexException extends OrionException {
    public InvalidIndexException(int indexAccessed, int maxIndex) {
        super(maxIndex == 0 ?
                "You tried to access " + indexAccessed + ". But the task list is empty." :
                "You tried to access " + indexAccessed + ". But we have only " + maxIndex + " tasks, and you should access between 1 and that instead.");
    }
}
