public class BlitzIOException extends BlitzException {
    private String desc;

    public BlitzIOException(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.desc + ", please try again or contact administrator!";
    }
}
