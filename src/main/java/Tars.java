public class Tars {

    private static final String separator = "-".repeat(50)+'\n';
    public static void main(String[] args) {
        String logo = """
                ________________ __________  _________
                \\__    ___/  _  \\\\______   \\/   _____/
                  |    | /  /_\\  \\|       _/\\_____  \\\s
                  |    |/    |    \\    |   \\/        \\
                  |____|\\____|__  /____|_  /_______  /
                                \\/       \\/        \\/\s
                """;
        System.out.println(logo);

        String introMessage = "Hello I'm TARS!\nWhat can I do for you?\n";
        String outroMessage = "Bye! Hope to see you again soon!\n";
        System.out.println(separator + introMessage + separator + outroMessage + separator);
    }
}
