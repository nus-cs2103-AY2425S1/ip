package vinegar;
public class Validator {

    public static void validateParts(String[] parts, int expectedLength, String errorMessage) throws VinegarException {
        if (parts.length < expectedLength || anyPartEmpty(parts)) {
            throw new VinegarException(errorMessage);
        }
    }

    private static boolean anyPartEmpty(String[] parts) {
        for (String part : parts) {
            if (part == null || part.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
