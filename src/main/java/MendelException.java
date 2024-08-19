public class MendelException extends RuntimeException {
    public MendelException(String message) {

        super(message);
    }
    @Override
    public String toString() {
        return super.getMessage();
    }
}
