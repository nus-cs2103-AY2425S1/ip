import dumpling.Dumpling;

public class MainCLI {
    public static void main(String[] args) {
        Dumpling dumpling = new Dumpling("data/dumplingData.txt");
        dumpling.run();
    }
}
