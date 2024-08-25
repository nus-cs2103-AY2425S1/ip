package moimoi.exception;

/**
 * Represents an exception specific to MoiMoi.
 */
public abstract class MoiMoiException extends Exception {

    /**
     * Returns corresponding error message.
     *
     * @return Error message.
     */
    @Override
    public String getMessage() {
        return "Uh-oh :'( ";
    }

}
