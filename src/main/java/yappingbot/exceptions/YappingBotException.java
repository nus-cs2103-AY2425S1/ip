package yappingbot.exceptions;

public class YappingBotException extends RuntimeException {

    public YappingBotException() {
        super();
    }

    public YappingBotException(String errorMsg) {
        super(errorMsg);
    }

    public String getErrorMessage() {
        return super.getMessage();
    }
}
