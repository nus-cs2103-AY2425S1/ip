public class Rasputin {

    private Storage storage;
    private TaskList tasks;
    private Parser parser;


    public Rasputin(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.readFile());
        this.parser = new Parser(tasks, storage);
    }

    public void run() {
        Ui.printGreeting();
        boolean isTerminated = false;
        while (!isTerminated) {
            try {
                String input = Ui.readInput();
                Command cmd = parser.parseInput(input);
                cmd.execute();
                isTerminated = cmd.isTerminated();
            } catch (RasputinException e) {
                new InvalidCommand(e.getMessage()).execute();
            }
        }

    }

    public static void main(String[] args) {

        Rasputin rasputin = new Rasputin("src/main/data/rasputin.txt");
        rasputin.run();

    }
}
