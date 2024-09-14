package bunbun.utils;

/**
 * This class implements a UI for Bunbun.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class Ui {

    /**
     * Formats Bunbun's responses to user input.
     *
     * @param res String of chatbot response to be formatted.
     */
    public String response(String res) {
        return "Bunbun: " + res;
    }
}
