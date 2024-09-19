package eli;

import eli.storage.Storage;
import eli.command.Command;
import eli.exception.EliException;
import eli.parser.Parser;
import eli.task.TaskList;
import eli.ui.Ui;

/**
 * The main class of the Eli application.
 * Coordinates the interaction between the user, storage, task list, and commands.
 */
public class Eli {
  private Storage storage;
  private TaskList tasks;
  private Ui ui;

  /**
   * Constructor for Eli.
   *
   * @param filePath The file path where tasks are stored.
   */
  public Eli(String filePath) {
    ui = new Ui();
    storage = new Storage(filePath);
    try {
      tasks = new TaskList(storage.load());
    } catch (EliException e) {
      ui.showLoadingError();
      tasks = new TaskList();
    }
  }

  /**
   * Runs the main application loop.
   */
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
    ui.close();
  }

  /**
   * The main entry point of the application.
   *
   * @param args Command-line arguments.
   */
  public static void main(String[] args) {
    Eli eli = new Eli("data/eli.txt");
    eli.run();
  }

  /**
   * Get the response by its command.
   *
   * @param text Text from the response.
   */
  public String getResponse(String text) throws EliException {
    Command command = Parser.parse(text);
    return command.execute(tasks, ui, storage);
  }
}
