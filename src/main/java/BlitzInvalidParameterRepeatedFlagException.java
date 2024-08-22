public class BlitzInvalidParameterRepeatedFlagException extends BlitzInvalidParameterFormatException {
    private String flag;

    public BlitzInvalidParameterRepeatedFlagException(String flag, String format) {
        super(format);
        this.flag = flag;
    }

    @Override
    public String toString() {
        return flag + " can only be used ONCE! " + super.toString();
    }
}
