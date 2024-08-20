import java.util.Scanner;

public class Struggling {
    final private String name = "struggling";

    Struggling() {
        reply("Hello! I'm " + this.name + "\nWhat can I do for you?");
    }

    public boolean read(String cmd) {
        if(cmd.compareTo("bye") == 0) {
            reply("Bye. Hope to see you again soon!");
            return false;
        }

        reply(cmd);
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

    public static void main(String[] args) {
        Struggling bot = new Struggling();
        Scanner sc = new Scanner(System.in);
        boolean isActive = true;

        do {
            isActive = bot.read(sc.nextLine());
        } while (isActive);

    }
}

