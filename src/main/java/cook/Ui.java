package cook;

import java.util.Scanner;

/**
 * Ui class to interact with the user.
 */
public class Ui {
    // Solution below inspired by https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Scanner.html
    protected Scanner input;

    /**
     * Constructor for Ui class.
     */
    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Welcome screen.
     */
    public void welcome() {
        //CHECKSTYLE.OFF: RuleName
        // Solution below adapted from https://www.patorjk.com/software/taag/#p=author&v=0&f=Avatar&t=Cook
        String logo = """
                          ____  ____  ____  _  __
                         /   _\\/  _ \\/  _ \\/ |/ /
                         |  /  | / \\|| / \\||   /\s
                         |  \\__| \\_/|| \\_/||   \\\s
                         \\____/\\____/\\____/\\_|\\_\\
                                                \s
                      """;
        //CHECKSTYLE.ON: RuleName
        System.out.print(logo);
        say("Hello, I'm Cook!\nWhat can I do for you?");
    }

    /**
     * Retrieves input from the user.
     */
    public String getInput() {
        return this.input.nextLine();
    }

    /**
     * Formats and outputs String to console.
     */
    public void say(String content) {
        System.out.println("____________________________________________________________"
                + "____________________________________________________________");
        System.out.println(content);
        System.out.println("____________________________________________________________"
                + "____________________________________________________________");
    }
}
