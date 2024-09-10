package util;

import static util.Utility.INDENT;
import static util.Utility.NEW_LINE;
import static util.Utility.prettyPrint;

import java.util.Scanner;

/**
 * Utility class to interact with the user.
 */
public class Ui {
    private final Scanner sc;
    private final String prepend = INDENT;
    private final String newLine = NEW_LINE;
    private String response;

    /**
     * Constructor for a new Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
        this.response = "";
    };

    /**
     * Prints the response by prepending the indent to the msgs and wrapping the final msg in
     * between horizontal lines with pretty print.
     *
     * @param strArr The array of strings that make up the msg, each element will be separated with
     *        a \n in the printed msg.
     */
    public void printResponse(String... strArr) {
        assert strArr != null : "Input array must not be null";

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < strArr.length - 1; i++) {
            sb.append(prepend + strArr[i] + newLine);
        }
        if (strArr.length >= 1) {
            sb.append(prepend + strArr[strArr.length - 1]);
        }
        prettyPrint(sb.toString());
    }

    /**
     * Print the stored response.
     */
    public void printResponse() {
        prettyPrint(this.response);
    }

    /**
     * Utility method to get input from std::in.
     *
     * @return Input from std::in.
     */
    public String getNextLine() {
        return this.sc.nextLine();
    }

    /**
     * Setter for the stored reponse.
     *
     * @param strArr An array of strings to set as the response.
     * @return The new response.
     */
    public String setResponse(String... strArr) {
        assert strArr != null : "Input array must not be null";

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < strArr.length - 1; i++) {
            sb.append(prepend + strArr[i] + newLine);
        }
        if (strArr.length >= 1) {
            sb.append(prepend + strArr[strArr.length - 1]);
        }
        this.response = sb.toString();
        return this.response;
    }

    /**
     * Getter for the stored response.
     *
     * @return The stored response.
     */
    public String getResponse() {
        return this.response;
    }
}
