package Save;
import java.io.*;
import Task.Task;

public class Save {
    private File saveFile;
    private final String savePath = "/Users/qinkangchiang/Desktop/ip/saves/save.txt";

    public Save(){
        this.saveFile = new File("/Users/qinkangchiang/Desktop/ip/saves/save.txt");
    }

    /**
     * Returns the path of the save file
     *
     * @return     the path of the save file as a string
     */
    public String getPath() {
        return this.savePath;
    }
}