package chacha.command.stub;


import chacha.Storage;

import java.io.IOException;

/**
 * Stub for Storage class.
 */
public class StorageStub extends Storage {
    public StorageStub(String filePath) throws IOException {
        super(filePath);
    }
}
