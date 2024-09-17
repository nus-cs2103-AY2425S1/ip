package kotori.tasklist;

/**
 * This class is thrown when the certain commands miss some information.
 * */

public class MissingInformationException extends Exception {
    private String missingInfo;
    private String type;

    /**
     * Create a exception object.
     * */
    public MissingInformationException(String missingInfo, String type) {
        this.missingInfo = missingInfo;
        this.type = type;
    }

    /**
     * return the error message.
     *
     * @return the error message.
     * */
    public String getMessage() {
        return String.format("Sorry~ Please enter the %s for %s", missingInfo, type);
    }
}
