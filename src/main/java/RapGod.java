public class RapGod {
    public static void main(String[] args) {
        String logo = """
                    ____              ______          __
                   / __ \\____ _____  / ____/___  ____/ /
                  / /_/ / __ `/ __ \\/ / __/ __ \\/ __  /\s
                 / _, _/ /_/ / /_/ / /_/ / /_/ / /_/ / \s
                /_/ |_|\\__,_/ .___/\\____/\\____/\\__,_/  \s
                           /_/                         \s
                """;

        System.out.println("Introducing\n" + logo);

        String output = """
                ____________________________________________________________
                 Hello! I'm %s
                 What can I do for you?
                ____________________________________________________________
                 Bye. Hope to see you again soon!
                ____________________________________________________________
                """;
        System.out.printf(String.format(output, "RapGod"));
    }
}
