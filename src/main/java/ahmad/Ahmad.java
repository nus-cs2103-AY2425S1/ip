package ahmad;

import java.util.Scanner;

import ahmad.exceptions.AhmadException;
import ahmad.processor.Processor;
import ahmad.response.Response;

/**
 * Main class for Ahmad bot.
 */
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

    /**
     * Runs the program.
     * Loads file (if exists), and starts interaction.
     *
     * @param args Arguments from the compiler.
     */
    public static void main(String[] args) {
        final String welcomeMsg = "Hello! I'm ahmad.Ahmad\nWhat can I do for you?\n\nEnjoy!";
        Ui.print(welcomeMsg);

        Storage.load();

        startInteraction();
    }
}
