package choaticbot.inputs;

import java.util.Objects;

/**
 * The {@code ProcessedInput} class represents the structured input extracted from
 * the user's raw input. It contains the action and the details provided by the user.
 */
public class ProcessedInput {

    /**
     * The action specified in the user's input (e.g., "todo", "delete").
     */
    private String action;

    /**
     * Additional details associated with the action (e.g., task description or index).
     */
    private String details;

    /**
     * Constructs a {@code ProcessedInput} with the specified action and details.
     *
     * @param action The action extracted from the user's input.
     * @param details The details related to the action.
     */
    public ProcessedInput(String action, String details) {
        this.action = action;
        this.details = details;
    }

    /**
     * Retrieves the action from the processed input.
     *
     * @return The action specified in the user's input.
     */
    public String getAction() {
        return this.action;
    }

    /**
     * Retrieves the details from the processed input.
     *
     * @return The details associated with the action.
     */
    public String getDetails() {
        return this.details;
    }

    /**
     * A helper method used for testing purposes to compare two {@code ProcessedInput} objects.
     * It checks whether both the action and details match.
     *
     * @param t The {@code ProcessedInput} object to compare with.
     * @return {@code true} if the action and details are the same; {@code false} otherwise.
     */
    public boolean isLike(ProcessedInput t) {
        return Objects.equals(this.action, t.getAction()) && Objects.equals(this.getDetails(), t.getDetails());
    }
}
