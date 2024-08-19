/**
 * This class implements a UI for Bunbun.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class UI {

    /**
     * Prints the start screen upon start.
     */
    public static void startScreen() {
        UI.sectionSeparator();
        UI.response("Hi I'm Bunbun ^-^");
        UI.response("I'm here to help!");
        UI.sectionSeparator();
    }

    /**
     * Prints the end screen upon end.
     */
    public static void endScreen() {
        UI.sectionSeparator();
        UI.response("Ok byeeeeee :D");
        UI.sectionSeparator();
    }

    /**
     * Prints horizontal lines for formatting.
     */
    public static void sectionSeparator() {
        for (int i = 0; i < 50; i++) {
            System.out.print("\u2500");
        }
        System.out.println();
    }

    /**
     * Formats Bunbun's responses to user input.
     * @param res Chatbot response to be formatted.
     */
    public static void response(String res) {
        System.out.println("Bunbun: " + res);
    }
}
