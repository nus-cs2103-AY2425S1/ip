package choaticbot.inputs;

import java.util.Objects;

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

    //for testing purposes
    public boolean isLike(ProcessedInput t) {
        return Objects.equals(this.action, t.getAction()) && Objects.equals(this.getDetails(), t.getDetails());
    }
}
