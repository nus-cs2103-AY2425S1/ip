package chatsy.exceptions;

public class LocalFileException extends ChatsyException {
    private String filePath;

    public LocalFileException(String filePath) {
        this.filePath = filePath;
    }
    @Override
    public String toString() {
        return "OOPS, an error occurred with file " + this.filePath;
    }
}
