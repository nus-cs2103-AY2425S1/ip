abstract public class Messages {
    private String message;
    private String separator;
    public Messages(String message, String separator) {
        this.message = message;
        this.separator = separator;
    }

    @Override
    public String toString() {
        return this.separator + "\n" + this.message + "\n" + this.separator;
    }
}
