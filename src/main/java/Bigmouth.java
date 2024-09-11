import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class Bigmouth {

    public static void main(String[] args) {

        Scanner scanner;
        scanner = new Scanner(System.in);
        Storage s = new Storage(getFileURL());
        s.loadFromFile();
        TaskList tasks = (TaskList) s.getTasks();
        Ui ui = new Ui();
        Parser parser = new Parser(ui, tasks, s, scanner);

        ui.showWelcome();
        parser.parseInput();
    }



    public static String getFileURL() {
        final URL fileURL = Bigmouth.class.getProtectionDomain().getCodeSource().getLocation();
        String path = fileURL.getPath(); //FILE_PATH;
        String rootPath = path.substring(0, path.indexOf("ip") + 3) + "/data/Bigmouth.txt";
        return rootPath;
    }
}
