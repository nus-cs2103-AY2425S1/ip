package exception;

public class CitadelInvalidArgException extends CitadelException {
    @Override
    public String toString() {
        return super.toString() + "Index is out of bounds :(";
    }
}
