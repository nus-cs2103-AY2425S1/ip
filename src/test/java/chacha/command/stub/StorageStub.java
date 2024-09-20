package chacha.command.stub;

import java.io.IOException;

import chacha.Storage;

/**
 * Stub for Storage class.
 */
public class StorageStub extends Storage {
    public StorageStub(String filePath) throws IOException {
        super(filePath);
    }
}
