package ahmad;

import java.util.Scanner;

import ahmad.exceptions.AhmadException;
import ahmad.processor.Processor;
import ahmad.response.Response;

public class Ahmad {

    private static void startInteraction() {
        final Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                final String prompt = scanner.nextLine();

                final Processor inst = Parser.parse(prompt);

                final Response response = inst.process(prompt);

                response.getResponse().forEach(Ui::print);

                if (response.shouldExit()) {
                    break;
                }

                if (response.checkShouldRecord()) {
                    Storage.save(prompt);
                }
            } catch (AhmadException e) {
                Ui.print(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        final String welcomeMsg = "Hello! I'm ahmad.Ahmad\nWhat can I do for you?\n\nEnjoy!";
        Ui.print(welcomeMsg);

        Storage.load();

        startInteraction();
    }
}
