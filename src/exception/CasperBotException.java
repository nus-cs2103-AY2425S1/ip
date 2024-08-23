package exception;

public class CasperBotException extends Exception {
    private String solution;
    protected CasperBotException(String message,String solution) {
        super(message);
        this.solution = solution;
    }

    @Override
    public String getMessage() {
        return "Error: " + super.getMessage() + "\n" + this.solution;
    }
}
