import dumpling.Dumpling;

/**
 * Main class for CLI version of App
 */
public class MainCli {
    public static void main(String[] args) {
        Dumpling dumpling = new Dumpling("data/dumplingData.txt");
        dumpling.run();
    }
}
