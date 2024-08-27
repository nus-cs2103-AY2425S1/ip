public class Optimus {
    Storage storage;
    TaskList tasks;
    Ui ui;

    public Optimus() {
        this.storage = new Storage();
        this.tasks = new TaskList(storage.load());
        this.ui = new Ui();
    }

    private void run() {
        ui.greet();
        boolean runChat = true;
        while (runChat) {
            try {
                String input = ui.readCommand();
                ui.showLineBreak();
                Command c = Parser.parse(input);
                c.execute(storage, tasks, ui);
                runChat = c.shouldContinue();
            } catch (OptimusExceptions e) {
                System.out.println(e.getMessage());
            } finally {
                ui.showLineBreak();
            }
        }
    }

    public static void main(String[] args) {
        Optimus optimus = new Optimus();
        optimus.run();
    }
}