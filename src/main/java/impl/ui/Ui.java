package impl.ui;

public class Ui {
    static String cat = """
                  |\\      _,,,---,,_
            ZZZzz /,`.-'`'    -.  ;-;;,_
                 |,4-  ) )-,_. ,\\ (  `'-'
                '---''(_/--'  `-'\\_)
            """;
    Parser parser;

    /**
     * Main UI of the program.
     * Prints out all the statements in console
     *
     * @param parser parser for Danny to handle input string received.
     */
    public Ui(Parser parser) {
        this.parser = parser;
        System.out.println("Hello! I'm Danny!");
        System.out.println(cat);
        System.out.println("What can I do for you?");
    }

    public String running(String in) {
        assert !in.isEmpty();
        return parser.handleInput(in);
    }

    public void end() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
