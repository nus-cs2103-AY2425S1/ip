package ned.exceptions;

/**
 * Exception thrown when a requested cache file is not found.
 *
 * <p>This exception indicates that an operation requiring a cache file has failed
 * because the file could not be located. It extends {@link NedException} to provide
 * a specific exception type for cache file-related errors within the NED application.
 *
 * @see NedException
 */
public class CacheFileNotFoundException extends NedException {

    /**
     * Constructs a new {@code CacheFileNotFoundException} with the specified detail message.
     *
     * @param errorMessage the detail message explaining the reason for the exception
     */
    public CacheFileNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

