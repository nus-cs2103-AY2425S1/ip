import java.util.Scanner;
import command.Greet;
import command.Exit;
import command.Echo;

public class Mummy {
  private static String LOGO =  " __  __\n"                                 
      + "|  \\/  |_   _ _ __ ___  _ __ ___  _   _\n" 
      + "| |\\/| | | | | '_ ` _ \\| '_ ` _ \\| | | |\n"
      + "| |  | | |_| | | | | | | | | | | | |_| |\n"
      + "|_|  |_|\\__,_|_| |_| |_|_| |_| |_|\\__, |\n"
      + "                                  |___/ \n";

  public static void main(String[] args) {
    new Greet(LOGO).execute();
    listen(new Scanner(System.in));
  }

  public static void listen(Scanner scanner) {
    String input = scanner.nextLine();

    switch (input) {
      case "bye":
        new Exit().execute();
        break;
      default:
        new Echo(input).execute();
        listen(scanner);
        break;
    }
  }
}
