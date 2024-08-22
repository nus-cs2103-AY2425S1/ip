public class BlitzInvalidParameterRegexException extends BlitzInvalidParameterFormatException {
    public BlitzInvalidParameterRegexException(String format) {
        super(format);
    }

    @Override
    public String toString() {
        return "Wrong parameter format! " + super.toString();
    }
}
