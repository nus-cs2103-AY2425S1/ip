public class PrinceException extends Exception {
    public PrinceException(String msg) {
        super(msg);
    }
    
    @Override
    public String toString() {
        return getMessage();
    }
}
