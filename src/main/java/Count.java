import java.util.*;

public class Count {
    private ArrayList<String> ls;

    public Count() {
        this.ls = new ArrayList<>();
    }
    private void reply(String output) {
        String spacer = "\n____________________________________________________________\n";
        System.out.println(spacer + output + spacer);
    }
    private void greet() {
        reply("Hello! I'm Count!\nWhat can I do for you?");
    }

    private void farewell() {
        reply("Bye. Hope to see you again soon!");
    }

    private void addToList(String toDo) {
        reply("Added: " + toDo);
        ls.add(toDo);
    }

    private void listReply() {
        String ans = "";
        for (int i = 0 ; i < ls.size() ; i++) {
            if (i != ls.size() - 1) {
                ans += (i + 1) + ". " + ls.get(i) + "\n";
            } else {
                ans += (i + 1) + ". " + ls.get(i);
            }
        }
        reply(ans);
    }

    // Default case is add
    private void parser(String input) {
        switch (input.toLowerCase()) {
            case "hello":
                greet();
                break;
            case "bye":
                farewell();
                break;
            case "list":
                listReply();
                break;
            default:
                addToList(input);
        }
    }

    public static void main(String[] args) {
        Count c = new Count();
        Scanner sc = new Scanner(System.in);
        c.greet();

        while (true) {
            String input = sc.nextLine();
            c.parser(input);
        }
    }
}
