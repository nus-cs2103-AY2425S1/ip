package util;

/**
 * The Writer class serves as an abstract base class for writing data to files in the application.
 * It provides a standard interface for writing data to files, allowing for different types
 * of writers to be implemented depending on the file format or data requirements.
 */
public abstract class Writer {

    /**
     * Writes data to the file specified by its file path.
     * This abstract method must be implemented by all subclasses to define the specific behavior
     * for writing data to files. The implementation can vary depending on the data format and requirements.
     *
     * @param filePath The path to the file where the data needs to be written.
     */
    public abstract void write(String filePath);
}
