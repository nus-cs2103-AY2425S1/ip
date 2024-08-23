package matcha.exception;
public class MatchaException extends Exception{
    public MatchaException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
