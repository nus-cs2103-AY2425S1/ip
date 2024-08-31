package atreides.ui;

public class AtreidesException extends Exception{
    private final String description;

    public AtreidesException(String description) {
        super(description);
        this.description = description;
    }

    public String getDescription() {
        return "Error: " + description + "\n";
    }
}
