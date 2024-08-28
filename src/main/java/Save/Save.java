package Save;
import java.io.*;
import Task.Task;

public class Save {
    private File saveFile;
    private final String savePath = "C:\\Users\\chian\\Desktop\\ip\\saves\\save.txt";

    public Save(){
        this.saveFile = new File("C:\\Users\\chian\\Desktop\\ip\\saves\\save.txt");
    }

    public String getPath() {
        return this.savePath;
    }
}