package ponderpika.exception;

/**
 * Custom exception class for the PonderPika application.
 * <p>
 * This exception is used to signal errors specific to the PonderPika application
 * with a custom error message. It extends the base Exception class.
 * </p>
 */
public class PonderPikaException extends Exception {

    @Override
    public String toString() {
        return "Oh pika pi (T_T) - ";
    }
}
