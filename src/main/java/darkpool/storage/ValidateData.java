package darkpool.storage;

import darkpool.DarkpoolException;

import java.io.File;
import java.io.IOException;

/**
 * ValidateData class is used to validate the data file.
 * If the file does not exist, it will create a new file.
 */
class ValidateData {

    /**
     * Validate the data file.
     * If the file does not exist, it will create a new file.
     *
     * @param filePath the path of the data file
     * @return the data file
     * @throws DarkpoolException if the file cannot be created
     */
    static File validateData(String filePath) throws DarkpoolException {
        File dataFile = new File(filePath);

        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                throw new DarkpoolException("sorry bro idk why can't create file  ＞﹏＜");
            }
        }

        return dataFile;
    }
}
