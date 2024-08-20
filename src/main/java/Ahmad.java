import response.Response;

import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class Ahmad {

  private static void startInteraction() {
    final Scanner scanner = new Scanner(System.in);
    while (true) {
      try {
        final String prompt = scanner.nextLine();
        final Function<String, Response> inst = MessageParser.parse(prompt);

        final Response response = inst.apply(prompt);


        response.getResponse().forEach(IO::print);
        if (response.shouldExit()) {
          break;
        }
      } catch (Exception e) {
        IO.print(e.getMessage());
      }
    }
  }

  public static void main(String[] args) {
    final String welcomeMsg = "Hello! I'm Ahmad\nWhat can I do for you?\n\nOkay Byeee!";
    IO.print(welcomeMsg);

    startInteraction();
  }
}
