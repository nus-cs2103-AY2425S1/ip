package vinegar;

/**
 * Validates input and command arguments for the Vinegar application.
 * <p>
 * This class provides utility methods to validate user input, ensuring commands
 * have the required arguments and are properly formatted before execution.
 */
public class Validator {

    /**
     * Validates that the provided parts array has the expected length and contains no empty parts.
     *
     * @param parts          The array of input parts to validate.
     * @param expectedLength The expected length of the array.
     * @param errorMessage   The error message to throw if validation fails.
     * @throws VinegarException If the validation fails due to insufficient length or empty parts.
     */
    public static void validateParts(String[] parts, int expectedLength, String errorMessage) throws VinegarException {
        if (parts.length < expectedLength || anyPartEmpty(parts)) {
            throw new VinegarException(errorMessage);
        }
    }

    /**
     * Checks if any part of the provided array is null or empty.
     *
     * @param parts The array of input parts.
     * @return True if any part is null or empty, false otherwise.
     */
    private static boolean anyPartEmpty(String[] parts) {
        for (String part : parts) {
            if (part == null || part.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
