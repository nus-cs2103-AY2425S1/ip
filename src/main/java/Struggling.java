import java.util.ArrayList;
import java.util.Scanner;

public class Struggling {
    final private String name = "struggling";
    private ArrayList<String> arr = new ArrayList<>();

    Struggling() {
        reply("Hello! I'm " + this.name + "\nWhat can I do for you?");
    }

    public boolean read(String cmd) {

        switch (cmd) {
            case "bye":
                reply("Bye. Hope to see you again soon!");
                return false;
            case "list":
                list();
                break;
            default:
                addCmd(cmd);
                break;
        }

        return true;
    }

    private void reply(String str) {
        String line = "____________________________________________________________";
        str = line + "\n" + str + "\n" + line;
        for(String s : str.split("\\R")) {
            System.out.println("\t" + s);
        }
        System.out.println();
    }

    private void addCmd(String str) {
        this.arr.add(str);
        reply("added: " + str);
    }

    private  void list() {
        StringBuilder ans = new StringBuilder();
        int count = 0;
        for(String s : this.arr) {
            ans.append(++count).append(". ").append(s).append("\n");
        }

        if(!ans.isEmpty()) {
            ans.deleteCharAt(ans.length() - 1);
        }

        reply(ans.toString());
    }

    public static void main(String[] args) {
        Struggling bot = new Struggling();
        Scanner sc = new Scanner(System.in);
        boolean isActive = true;

        do {
            isActive = bot.read(sc.nextLine());
        } while (isActive);

    }
}

