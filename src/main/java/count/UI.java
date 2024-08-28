package count;

import count.action.Deactivate;
import count.action.Greet;

public class UI {

    public void reply(String output) {
        String spacer = "\n____________________________________________________________\n";
        System.out.println(spacer + output + spacer);
    }

    public void greet() {
        Greet welcome = new Greet();
        reply(welcome.run());
    }

    public void farewell() {
        Deactivate d = new Deactivate();
        reply(d.run());
    }
}
