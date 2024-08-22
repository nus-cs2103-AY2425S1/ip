public class Kat {
  public static void main(String[] args) {
    String logo = """
         _         _  \s
        | | __ ___| |_\s
        | |/ / _  | __|
        |   < (_| | |_\s
        |_|\\_\\____|\\__|
        """;
    System.out.println("Hello from\n" + logo);

    respond("Hi! I'm Kat.\nHow can I help?");
    respond("See you!");
  }

  private static void respond(String message) {
    System.out.println("~".repeat(50));
    System.out.println("> Kat");
    System.out.println(message);
    System.out.println("~".repeat(50));
  }
}
