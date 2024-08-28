package stelle;

/** Represents the main chatbot class.
 * @author Lee Ze Hao (A0276123J)
 */

public class Stelle {
    static final String NAME = "stelle";
    static final String FILE_PATH = "./data/stelle.txt";

    private Ui ui;

    public Stelle(String name, String filePath) {
        ui = new Ui(name, filePath);
    }

    public void run() {
        ui.run();
    }

    public static void main(String[] args) {
        new Stelle(NAME, FILE_PATH).run();
    }
}
