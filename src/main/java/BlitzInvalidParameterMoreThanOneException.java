public class BlitzInvalidParameterMoreThanOneException extends BlitzInvalidParameterFormatException {
    public BlitzInvalidParameterMoreThanOneException(String format) {
        super(format);
    }

    @Override
    public String toString() {
        return "Only ONE parameter is required! " + super.toString();
    }
}
