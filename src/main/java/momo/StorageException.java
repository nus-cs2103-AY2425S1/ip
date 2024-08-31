package momo;

public class StorageException extends MomoException {

    public StorageException(String message) {
        super("Storage error:" + message);
    }
}
