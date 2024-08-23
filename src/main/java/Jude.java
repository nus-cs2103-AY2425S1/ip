import java.util.Scanner;

public class Jude {
  public static void main(String[] args) {
    String name = "Jude";
    Scanner scanner = new Scanner(System.in);
    String[] list = new String[100];
    int listSize = 0;

    System.out.println("Hello I'm " + name);
    System.out.println("What can I do for you, poyo?");

    String userInput = scanner.nextLine();
    while (!userInput.equals("bye")) {
      if (userInput.equals("list")) {
        for (int i = 0; i < listSize; i++) {
          System.out.printf("%d. %s\n", (i + 1), list[i]);
        }
      } else {
        list[listSize++] = userInput;
        System.out.println("added: " + userInput);
      }
      userInput = scanner.nextLine();
    }
    System.out.println("Bye. Hope to see you again soon, poyo!");
    scanner.close();
  }
}

