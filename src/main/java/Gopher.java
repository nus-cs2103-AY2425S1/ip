public class Gopher {
    public static String gopherLogo = """
              ____             _              \s
             / ___| ___  _ __ | |__   ___ _ __\s
            | |  _ / _ \\| '_ \\| '_ \\ / _ \\ '__|
            | |_| | (_) | |_) | | | |  __/ |  \s
             \\____|\\___/| .__/|_| |_|\\___|_|  \s
                        |_|                   \s
            """;
    public static String horizontalSeparator = "==================================================";
    public static void main(String[] args) {
        System.out.println(horizontalSeparator);
        System.out.println(gopherLogo);
        System.out.println("Hello! I am Gopher.\nWhat can I do for you?\n");
        System.out.println(horizontalSeparator);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalSeparator);
    }
}
