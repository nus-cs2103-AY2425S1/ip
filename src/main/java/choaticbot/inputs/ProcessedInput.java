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
     * Compares this {@code ProcessedInput} object with another to determine if both the action
     * and details are the same.
     * <p>
     * This helper method is primarily used for testing purposes.
     *
     * @param t The {@code ProcessedInput} object to compare against.
     * @return {@code true} if both the action and details match; {@code false} otherwise.
     */
    public boolean isLike(ProcessedInput t) {
        return Objects.equals(this.action, t.getAction()) && Objects.equals(this.getDetails(), t.getDetails());
    }
}
