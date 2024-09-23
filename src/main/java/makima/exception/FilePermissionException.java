package makima.exception;

public class FilePermissionException extends RuntimeException {

    public FilePermissionException() {
        super("Could not read / write data files. Is the file currently opened? Try running with admin permissions!");
    }
}
