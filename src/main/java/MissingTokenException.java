public class MissingTokenException extends Exception{
    private String taskType;
    private String missingToken;

    public MissingTokenException(String taskType, String missingToken) {
        super();
        this.taskType = taskType;
        this.missingToken = missingToken;
    }

    @Override
    public String getMessage() {
        return String.format("Seems like you are missing token %s when creating a %s\nPlease try again...",
                missingToken,
                taskType);
    }
}
