package utility;

public class Echo {

    private static final String DASHES = "-".repeat(100);

    private final String message;

    public Echo(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return DASHES + "\n"
                + this.message + "\n"
                + DASHES;
    }

    public void execute() {
        System.out.println(this);
    }
}
