public class BlitzInvalidParameterMissingContentException extends BlitzInvalidParameterFormatException {
    private String param;

    public BlitzInvalidParameterMissingContentException(String param, String format) {
        super(format);
        this.param = param;
    }

    @Override
    public String toString() {
        return "Missing value for " + param + "! " + super.toString();
    }
}
