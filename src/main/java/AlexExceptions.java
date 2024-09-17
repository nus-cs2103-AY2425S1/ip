public class AlexExceptions extends Exception {
    private String description;
    public AlexExceptions(String message) {
        this.description = message;
    }
    public String getDescription() {
        return this.description;
    }
}
