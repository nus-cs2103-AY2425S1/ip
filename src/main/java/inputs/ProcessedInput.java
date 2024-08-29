package inputs;

public class ProcessedInput {
    public String action;
    public String details;

    public ProcessedInput(String action, String details) {
        this.action = action;
        this.details = details;
    }

    public String getAction() {
        return this.action;
    }

    public String getDetails() {
        return this.details;
    }
}
