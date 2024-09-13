package mollyexception;

/**
 * Throws MollyException whenever necessary.
 */

public class MollyException extends Exception {

    public String errorMessage;

    public MollyException(String errorMessage) {
        super(errorMessage);
    }


}
