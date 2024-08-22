public class BlitzInvalidParameterFormatException extends BlitzException {
    private String format;

    public BlitzInvalidParameterFormatException(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return  "Please use this format \"" + format + "!\"";
    }
}
