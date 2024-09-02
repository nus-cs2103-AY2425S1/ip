package alisa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import alisa.exception.AlisaException;

public class StorageTest {
    @Test
    public void loadFile_nonexistingFile_fileNotFound() throws AlisaException {
        Storage storage = new Storage("nonexisting_file.txt");
        assertInstanceOf(AlisaException.class, storage.loadFile(), "This should return an exception");
    }

}
