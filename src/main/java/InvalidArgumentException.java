public class InvalidArgumentException extends LevelHundredException {
    private String arg;
    private String val;
    public InvalidArgumentException(String arg, String val) {
        super("Invalid value for argument");
        this.arg = arg;
        this.val = val;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.arg + ": \"" + this.val + "\"";
    }
}
