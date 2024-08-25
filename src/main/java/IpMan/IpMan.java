package IpMan;
import java.util.ArrayList;
import java.util.Scanner;

public class IpMan {
    public ArrayList<Task> list;
    public Storage db;
    public Ui ui;
    public Parser parser;

    public IpMan() {
        list = new ArrayList<>();
        db = new Storage();
        ui = new Ui();
        parser = new Parser(list, db, ui);
    }
    public IpMan(String filePath) {
        list = new ArrayList<>();
        db = new Storage(filePath);
        ui = new Ui();
        parser = new Parser(list, db, ui);
    }
    public static void main(String[] args) {
        boolean isTest = args.length > 0 && args[0] != null && Boolean.parseBoolean(args[0]);
        if (!isTest) {
            new IpMan("data/saved.txt").run();
        } else {
            new IpMan().run();
        }
    }

    public void run() {
        ui.showBanner();
        Scanner saved = db.getFileScanner();
        parser.parseFromScanner(saved, true);
        saved.close();
        parser.parseFromScanner(new Scanner(System.in), false);
    }
}
