package main.java.chatbot.exceptions;

public class FilePermissionException extends LocalFileException {
    private String filePath;

    public FilePermissionException(String filePath) {
        super(filePath);
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "Oops, an error occurred when creating / writing a local file.\n"
                + "The file path is " + this.filePath;
    }
}

