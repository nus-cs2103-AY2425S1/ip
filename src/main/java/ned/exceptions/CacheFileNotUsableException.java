package ned.exceptions;

/**
 * Exception thrown when a cache file is found but cannot be used.
 *
 * <p>This exception indicates that an operation requiring a cache file has failed
 * because the file is present but unusable, possibly due to corruption, invalid format,
 * or insufficient permissions. It extends {@link NedException} to provide a specific
 * exception type for cache file-related errors within the NED application.
 *
 * @see NedException
 */
public class CacheFileNotUsableException extends NedException {

    /**
     * Constructs a new {@code CacheFileNotUsableException} with the specified detail message.
     *
     * @param errorMessage the detail message explaining the reason for the exception
     */
    public CacheFileNotUsableException(String errorMessage) {
        super(errorMessage);
    }
}

