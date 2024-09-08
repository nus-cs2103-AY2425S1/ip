package mendel.mendelexception;

/**
 * A utility class that provides conditional exception handling based on specified conditions.
 * It allows chaining of conditions.
 */
public class ConditionalExceptionHandler {
    private final boolean isConditional;

    /**
     * Constructor to initialize the handler with a conditional state.
     *
     * @param isConditional A boolean indicating whether the handler is in a error conditional state.
     */
    private ConditionalExceptionHandler(boolean isConditional) {
        this.isConditional = isConditional;
    }

    /**
     * Creates a new instance in a non error conditional state.
     *
     * @return A instance with no error conditions.
     */
    public static ConditionalExceptionHandler of() {
        return new ConditionalExceptionHandler(false);
    }

    /**
     * Chains logical and error condition for triggering an exception if the condition is met.
     *
     * @param isMet A boolean indicating whether the condition is met.
     * @return A new instance, with the conditional state set if the condition is met.
     * @throws MendelException If the condition is met, indicating a failure condition.
     */
    public ConditionalExceptionHandler orConditionTriggerException(boolean isMet) throws MendelException {
        if (isMet) {
            return new ConditionalExceptionHandler(true);
        } else {
            return new ConditionalExceptionHandler(false);
        }
    }

    /**
     * Chains logical or error conditions and triggers an exception if both the orCondition
     * and the new condition are met.
     *
     * @param isMet A boolean indicating whether the new condition is met.
     * @param errorMsg The message to be used if the exception is triggered.
     * @return A new instance, with the conditional state updated based on the new condition.
     * @throws MendelException If both the current condition and the new condition are met.
     */
    public ConditionalExceptionHandler andConditionTriggerException(boolean isMet, String errorMsg)
            throws MendelException {
        if (this.isConditional && isMet) {
            throw new MendelException(errorMsg);
        } else {
            return new ConditionalExceptionHandler(true);
        }
    }

    /**
     * Triggers an exception if a given condition is met.
     *
     * @param isMet A boolean indicating whether the condition is met.
     * @param errorMsg The message to be used if the exception is triggered.
     * @return A new instance, with the conditional state set based on the condition.
     * @throws MendelException If the condition is met.
     */
    public ConditionalExceptionHandler conditionTriggerException(boolean isMet, String errorMsg)
            throws MendelException {
        if (isMet) {
            throw new MendelException(errorMsg);
        } else {
            return new ConditionalExceptionHandler(false);
        }
    }

}
