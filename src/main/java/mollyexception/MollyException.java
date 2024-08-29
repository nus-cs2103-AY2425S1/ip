package mollyexception;

/**
 * Added mollyexception.MollyException class
 */

public class MollyException extends Exception {

    public String errorMessage;

    public MollyException(String errorMessage) {
        super(errorMessage);
    }


}
