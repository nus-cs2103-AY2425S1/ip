package util;

/**
 * Stub for storage class.
 */
public class StorageStub extends Storage {
    private final String[] data;

    /**
     * Custom constructor for the stub.
     *
     * @param path File path.
     * @param data Mock data.
     */
    public StorageStub(String path, String[] data) {
        super(path);
        this.data = data;
    }

    @Override
    public String[] toArray() {
        return this.data;
    }
}
