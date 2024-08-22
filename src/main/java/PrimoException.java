public class PrimoException extends Exception{
    private String message;

    public PrimoException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
