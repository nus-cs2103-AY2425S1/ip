public class CypherException extends Exception{
    String message;
    public CypherException (String msg) {
        super(msg);
        this.message = msg;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
