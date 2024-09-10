package ahmad;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;


/**
 * Class responsible for IO operations.
 */
public class Ui {
    /**
     * Prints message to user.
     *
     * @param msg Message to be printed.
     */
    public static void print(String msg) {
        List<String> listOfMessages = Arrays.asList(msg.split("\n"));

        System.out.println("    ____________________________________________________________");
        for (String s : listOfMessages) {
            System.out.println("      " + s);
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints response to user via a print function.
     *
     * @param msg           Message to be printed.
     * @param printFunction Custom print function.
     */
    public static void print(String msg, Consumer<String> printFunction) {
        assert printFunction != null: "No GUI print function provided";

        printFunction.accept(msg);
    }
}
