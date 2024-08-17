import java.util.Scanner;

public class Diego {

    public static void main(String[] args) {
        String hiMessage = """
            ____________________________________________________________
            Hello! I'm Diego
            What can I do for you?
            ____________________________________________________________
            """;

        String byeMessage = """
            ____________________________________________________________
            Bye. Hope to see you again soon!
            ____________________________________________________________
            """;

        System.out.println(hiMessage);

        Scanner scanner = new Scanner(System.in);
        String input;
        String output;
        while (true){
            input = scanner.nextLine();

            if (input.equals("bye")){
                break;
            }

            output = "____________________________________________________________\n" +
                    " " + input + "\n" +
                    "____________________________________________________________";
            System.out.println(output);
        }
        System.out.println(byeMessage);
    }
}
