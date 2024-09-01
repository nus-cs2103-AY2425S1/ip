public class Eli {
  private Storage storage;
  private TaskList tasks;
  private Ui ui;

  public Eli(String filePath) {
    // this.tasks = new TaskList();
    // this.scanner = new Scanner(System.in);
    ui = new Ui();
    storage = new Storage(filePath);
    try {
      tasks = new TaskList(storage.load());
    } catch (EliException e) {
      ui.showLoadingError();
      tasks = new TaskList();
    }
  }


  public void run() {
    ui.showWelcome();
    boolean isExit = false;
    while (!isExit) {
      try {
        String fullCommand = ui.readCommand();
        Command command = Parser.parse(fullCommand);
        command.execute(tasks, ui, storage);
        isExit = command.isExit();
      } catch (EliException e) {
        ui.showError(e.getMessage());
      }
    }
    ui.showGoodbye();
    ui.close();
  }

  public static void main(String[] args) {
    Eli eli = new Eli("data/tasks.txt");
    eli.run();
  }
}
