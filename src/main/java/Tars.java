import java.util.Scanner;


public class Tars {
    private final static Response RES = new Response();
    public String introMessage() {
        String logo = """
                ________________ __________  _________
                \\__    ___/  _  \\\\______   \\/   _____/
                  |    | /  /_\\  \\|       _/\\_____  \\
                  |    |/    |    \\    |   \\/        \\
                  |____|\\____|__  /____|_  /_______  /
                                \\/       \\/        \\/\s
                """;
        System.out.println(logo.trim());
        return RES.intro();
    }
    public String outroMessage() {
        return RES.outro();
    }


    public static void main(String[] args) {

        Tars tars = new Tars();
        Scanner scanner = new Scanner(System.in);
        System.out.println(tars.introMessage());
        RES.updateTasks();

        while (true) {
            System.out.print(">>>");
            String userInput = scanner.nextLine();


            switch (userInput) {
                case "bye":
                    System.out.println(tars.outroMessage());
                    scanner.close();
                    break;

                case "list":
                    System.out.println(RES.showList());
                    break;

                default:
                    System.out.println(RES.generateResponse(userInput));
                    break;

            }

            if (userInput.equals("bye")) {
                RES.saveTasks();
                break;
            }

        }



    }
}
