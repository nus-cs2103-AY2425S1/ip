import java.util.Scanner;

import command.Command;
import command.Greet;
import command.Exit;

public class Mummy {
  private static final String LOGO =  " __  __\n"
      + "|  \\/  |_   _ _ __ ___  _ __ ___  _   _\n" 
      + "| |\\/| | | | | '_ ` _ \\| '_ ` _ \\| | | |\n"
      + "| |  | | |_| | | | | | | | | | | | |_| |\n"
      + "|_|  |_|\\__,_|_| |_| |_|_| |_| |_|\\__, |\n"
      + "                                  |___/ \n";

  private static final Store<String> STORE = new Store<>(100);

  public static void main(String[] args) {
    new Greet(LOGO).execute();
    listen(new Scanner(System.in));
  }

  public static void listen(Scanner scanner) {
    String input = scanner.nextLine();

    switch (input) {
      case "bye":
        new Exit().execute();
        return;
      case "list":
        Command displayStore = STORE.createDisplay();
        displayStore.execute();
        break;
      default:
        Command addToStore = STORE.createAddToStore(input);
        addToStore.execute();
        break;
    }

    listen(scanner);
  }
}
