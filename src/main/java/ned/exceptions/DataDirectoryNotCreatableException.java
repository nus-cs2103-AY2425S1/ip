package ned.exceptions;

/**
 * Exception thrown when the data subdirectory in the root of the project cannot be created.
 *
 * <p>This exception indicates that an operation requiring saving a cache file has failed
 * because the data subfolder cannot be created, possibly due to corruption, invalid format,
 * or insufficient permissions. It extends {@link NedException} to provide a specific
 * exception type for data subfolder-related errors within the NED application.
 *
 * @see NedException
 */
public class DataDirectoryNotCreatableException extends NedException {

    /**
     * Constructs a new {@code DataDirectoryNotCreatableException} with the specified detail message.
     *
     * @param errorMessage the detail message explaining the reason for the exception
     */
    public DataDirectoryNotCreatableException(String errorMessage) {
        super(errorMessage);
    }
}

