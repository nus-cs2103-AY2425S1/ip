package util;

public class StorageStub extends Storage {
    private final String[] data;

    public StorageStub(String path, String[] data) {
        super(path);
        this.data = data;
    }

    @Override
    public String[] toArray() {
        return this.data;
    }
}
