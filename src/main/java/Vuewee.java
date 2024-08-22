import java.util.Scanner;

public class Vuewee {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("____________________________________________________________");
    System.out.println("Hello! I'm Vuewee\nWhat can I do for you?");
    System.out.println("____________________________________________________________");

    // Echo input from user until user types "bye"
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("bye")) {
        break;
      }
      System.out.println("____________________________________________________________");
      System.out.println(input);
      System.out.println("____________________________________________________________");
    }

    System.out.println("____________________________________________________________");
    System.out.println("Bye. Hope to see you again soon!");
    System.out.println("____________________________________________________________");
    scanner.close();
  }
}
