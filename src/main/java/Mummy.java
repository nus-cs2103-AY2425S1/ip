import java.util.Scanner;

public class Mummy {
  private static String LOGO =  " __  __\n"                                 
      + "|  \\/  |_   _ _ __ ___  _ __ ___  _   _\n" 
      + "| |\\/| | | | | '_ ` _ \\| '_ ` _ \\| | | |\n"
      + "| |  | | |_| | | | | | | | | | | | |_| |\n"
      + "|_|  |_|\\__,_|_| |_| |_|_| |_| |_|\\__, |\n"
      + "                                  |___/ \n";

  public static void main(String[] args) {
    new Greet(logo).execute();
    new Exit().execute();
  }
}
