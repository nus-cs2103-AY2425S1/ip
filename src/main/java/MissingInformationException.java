public class MissingInformationException extends Exception{
    private String missingInfo;
    private String type;

    public MissingInformationException(String missingInfo, String type) {
        this.missingInfo = missingInfo;
        this.type = type;
    }

    public String getMessage() {
        return String.format("Sorry~ Please enter the %s for %s",missingInfo, type);
    }
}
