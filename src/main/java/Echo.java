public class Echo {

    private final String message;

    public Echo(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        String dashes = "-".repeat(100);
        return dashes + "\n"
                + this.message + "\n"
                + dashes;
    }

    public void execute() {
        System.out.println(this);
    }
}
