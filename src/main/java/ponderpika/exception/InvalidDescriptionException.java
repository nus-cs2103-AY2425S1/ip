package ponderpika.exception;

/**
 * Exception thrown when a description contains invalid characters.
 * <p>
 * This exception extends the PonderPikaException class and is used to indicate that
 * a provided description contains no characters other than alphabetic letters and spaces
 * </p>
 */
public class InvalidDescriptionException extends PonderPikaException {
    @Override
    public String toString() {
        return (super.toString() + "Description should only contain alphabets and spaces,"
                + " no special characters or numbers!");
    }
}
