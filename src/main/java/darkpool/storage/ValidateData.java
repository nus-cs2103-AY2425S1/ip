package darkpool.storage;

import darkpool.DarkpoolException;

import java.io.File;
import java.io.IOException;

public class ValidateData {
    public static File validateData(String filePath) throws DarkpoolException {
        File dataFile = new File(filePath);

        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                throw new DarkpoolException(e.getMessage());
            }
        }

        return dataFile;
    }
}
