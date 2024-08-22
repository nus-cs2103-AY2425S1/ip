import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Replies {
    //FIELDS---------------------------------
    public static final String line = "     ────────────────────";
    public static final String welcome = "     HELLO! I am Sunny:)\n     How can I help you?";
    public static final String goodbye = "     You are leaving? Ok bye:( come back soon";

    // Lists
    List<String> ls = new ArrayList<>();


    //METHODS---------------------------------
    public String welcome() {
        return welcome + "\n" + line + "\n";
    }

    public String reply(String message) {
        if (Objects.equals(message,"bye")) {
            return line + "\n" + goodbye;
        } else if (Objects.equals(message,"list")){
            String s = "";
            for (int i = 1; i <= ls.size(); i++) {
                s = s + String.format("     %h. %s \n", i, ls.get(i - 1));
            }
            return line + "\n" + s + "\n" + line;
        } else if(Objects.equals(message,"")) {
            return line;
        } else {
            ls.add(message);
            return line + "\n" + "     " + "added: " + message + "\n" + line;
        }
    }
    
}
