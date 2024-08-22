import java.util.Scanner;
public class Tars {
    Response res = new Response();

    public String getResponse(String input) {
        return res.generateResponse(input);
    }

    public String introMessage() {
        String logo = """
                ________________ __________  _________
                \\__    ___/  _  \\\\______   \\/   _____/
                  |    | /  /_\\  \\|       _/\\_____  \\\s
                  |    |/    |    \\    |   \\/        \\
                  |____|\\____|__  /____|_  /_______  /
                                \\/       \\/        \\/\s
                """;
        System.out.println(logo);
        return res.intro();
    }
    public String outroMessage() {
        return res.outro();
    }



    public static void main(String[] args) {

        Tars tars = new Tars();
        Scanner scanner = new Scanner(System.in);
        System.out.println(tars.introMessage());

        while (true) {
            System.out.print("> ");
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                System.out.println(tars.outroMessage());
                break;
            }
            System.out.println(tars.getResponse(userInput));

        }

        scanner.close();


    }
}
