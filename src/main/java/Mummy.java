import java.util.Scanner;

import command.*;

public class Mummy {
  private static final String LOGO =  " __  __\n"
      + "|  \\/  |_   _ _ __ ___  _ __ ___  _   _\n" 
      + "| |\\/| | | | | '_ ` _ \\| '_ ` _ \\| | | |\n"
      + "| |  | | |_| | | | | | | | | | | | |_| |\n"
      + "|_|  |_|\\__,_|_| |_| |_|_| |_| |_|\\__, |\n"
      + "                                  |___/ \n";

  private static final Store<String> STORE = new Store<>(100);


  public static void main(String[] args) {
    new Echo("Hello from\n" + LOGO).execute();
    listen(new Scanner(System.in));
  }

  public static void listen(Scanner scanner) {
    String input = scanner.nextLine();

    switch (input) {
      case "bye":
        new Echo("Bye. Hope to see you again soon!\n").execute();
        return;
      case "list":
        new Echo(STORE.toString()).execute();
        break;
      default:
        new Run("added: " + input, () -> STORE.add(input)).execute();
        break;
    }

    listen(scanner);
  }
}
