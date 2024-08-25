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

    public void startScreen() {
        this.sectionSeparator();
        this.response("Hi I'm Bunbun ^-^");
        this.response("I'm here to help!");
        this.sectionSeparator();
    }

    /**
     * Prints the end screen upon end.
     */
    public void endScreen() {
        this.sectionSeparator();
        this.response("Ok byeeeeee :D");
        this.sectionSeparator();
    }

    /**
     * Prints horizontal lines for formatting.
     */
    public void sectionSeparator() {
        for (int i = 0; i < 50; i++) {
            System.out.print("\u2500");
        }
        System.out.println();
    }

    /**
     * Formats Bunbun's responses to user input.
     * @param res Chatbot response to be formatted.
     */
    public void response(String res) {
        System.out.println("Bunbun: " + res);
    }
}
