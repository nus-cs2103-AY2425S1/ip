import java.util.Scanner;

public class Kat {
  private static final String LOGO = """
       _         _  \s
      | | __ ___| |_\s
      | |/ / _  | __|
      |   < (_| | |_\s
      |_|\\_\\____|\\__|
      """;

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("Hello from\n" + LOGO);

    respond("Hi! I'm Kat.\nHow can I help?");

    while (true) {
      System.out.println("> Me");

      String inputMsg = scanner.nextLine().trim();
      if (inputMsg.equalsIgnoreCase("bye")) {
        break;
      }

      respond(inputMsg);
    }

    respond("See you!");
  }

  private static void respond(String responseMsg) {
    System.out.println("~".repeat(50));
    System.out.println("> Kat");
    System.out.println(responseMsg);
    System.out.println("~".repeat(50));
  }
}
