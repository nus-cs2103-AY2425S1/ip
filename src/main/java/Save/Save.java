package Save;

import java.io.File;
import java.io.IOException;

public class Save {
    private File saveFile;
    private String savePath = "/Users/qinkangchiang/Desktop/ip/saves/save.txt";


    public Save(){
        String currentDir = System.getProperty("user.dir");
        this.savePath = currentDir + File.separator + "save" + File.separator + "save.txt";
        this.saveFile = new File(savePath);
        initSaveFile();
    }

    /**
     * Returns the path of the save file
     *
     * @return     the path of the save file as a string
     */
    public String getPath() {
        return this.savePath;
    }
    /**
    Creates a save directory and file
    */
    private void initSaveFile() {
        try {
            if (!saveFile.exists()) {
                File parentDir = saveFile.getParentFile();
                if (!parentDir.exists()) {
                    parentDir.mkdirs();
                }
                saveFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating save file");
        }
    }
}