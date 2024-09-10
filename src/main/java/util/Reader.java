package util;

/**
 * The Reader class serves as an abstract base class for reading files in the application.
 * It provides a standard interface for reading files, allowing different types of readers
 * to be implemented for various file formats or data sources.
 */
public abstract class Reader {

    /**
     * Reads the content of a file specified by its file path.
     * This abstract method must be implemented by all subclasses to define the specific behavior
     * for reading files. The implementation can vary depending on the file format and content.
     *
     * @param filePath The path to the file that needs to be read.
     */
    public abstract void readFile(String filePath);
}
