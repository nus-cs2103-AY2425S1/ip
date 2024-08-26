package count;

import count.action.Greet;
import count.action.Deactivate;

public class UI {
    public UI() {

    }

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
