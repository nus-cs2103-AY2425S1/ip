package exception;

import exception.KukiShinobuException;

public class FileNotFoundKukiShinobuException extends KukiShinobuException {
    public FileNotFoundKukiShinobuException() {
        super("Hmm, can't seem to find the file. Better check the path!");
    }
}
