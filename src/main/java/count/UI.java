package count;

import count.action.Deactivate;
import count.action.Greet;

/**
 * The UI class formats how Count replies
 * @author Kieran Koh Jun Wei
 */
public class UI {

    /**
     * The reply method prints the output between two horizontal lines
     * @param output String passed in to be printed
     */
    public void reply(String output) {
        String spacer = "\n____________________________________________________________\n";
        System.out.println(spacer + output + spacer);
    }

    /**
     * The greet method creates a new Greet object and runs it,
     * printing the String returned like how Count would say it
     */
    public String greet() {
        Greet welcome = new Greet();
        return welcome.run();
    }

    /**
     * The farewell method creates a new Deactivate object and runs it,
     * printing the String returned like how Count would say it
     */
    public String farewell() {
        Deactivate d = new Deactivate();
        return d.run();
    }
}
