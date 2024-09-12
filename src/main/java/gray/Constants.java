package gray;

import java.io.File;
import java.nio.file.Paths;

/**
 * Contains global constants values.
 */
public class Constants {
    // Resource files
    public static final String FILEPATH_VIEW_MAIN_WINDOW = "/view/MainWindow.fxml";
    public static final String FILEPATH_IMAGE_GRAY = "/images/gray.png";
    public static final String FILEPATH_IMAGE_USER = "/images/user.png";

    // Non-resource files
    public static final String FILEPATH_DATA_TASKS = "./data/saveTasks";

    public static void main(String[] args) {
        // Alternative to check if file paths are valid (for essential files)
        assert checkFileExists(FILEPATH_VIEW_MAIN_WINDOW, true);
        assert checkFileExists(FILEPATH_IMAGE_GRAY, true);
        assert checkFileExists(FILEPATH_IMAGE_USER, true);
        System.out.println("All OK");
    }

    private static boolean checkFileExists(String filepath, boolean isResource) {
        if (isResource) {
            filepath = Paths.get("./src/main/resources", filepath).toString();
        }
        File file = new File(filepath);
        return file.isFile();
    }
}
