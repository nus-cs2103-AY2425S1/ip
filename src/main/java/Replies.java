import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Replies {
    //FIELDS---------------------------------
    public static final String line = "     ────────────────────";
    public static final String welcome = "     HELLO! I am Sunny:)\n     How can I help you?";
    public static final String goodbye = "     You are leaving? Ok bye:( come back soon";

    // Lists
    List<Tasks> ls = new ArrayList<>();


    //METHODS---------------------------------
    public String welcome() {
        return welcome + "\n" + line + "\n";
    }

    public String reply(String message) {
        String m1 = message.split(" ", 2)[0];
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
        } else if(Objects.equals(m1, "mark")) {
             String m2 = message.split(" ", 2)[1];
             int i2 = Integer.parseInt(m2);
             ls.get(i2 - 1).setter(true);
             return "     Marked the task as done! \n     " + ls.get(i2 - 1);
        } else if(Objects.equals(m1, "unmark")) {
            String m2 = message.split(" ", 2)[1];
            int i2 = Integer.parseInt(m2);
            ls.get(i2 - 1).setter(false);
            return "     Task undone \n     " + ls.get(i2 - 1);
        } else {
            try {
                Tasks t = TaskCreator.create(message);
                ls.add(t);
                return line + "\n     "
                        + "Got it! added the task: \n     "
                        + t + "\n     "
                        + String.format("Now you have %h tasks in the list \n", ls.size()) + line;
            } catch (Exception e){
                return line + "\n      " + e.toString() + line;
            }
        }
    }

}
