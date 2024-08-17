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
        String [] tasks = new String[100];
        int taskCount = 0;
        Scanner scanner = new Scanner(System.in);
        String input;
        String output;
        while (true){
            input = scanner.nextLine();

            if (input.equals("bye")){
                break;
            }
            else if (input.equals("list")){
                for (int i = 0; i < taskCount; i++){
                    System.out.println(i+1 + ". " + tasks[i]);
                }
            } else{
                output = "____________________________________________________________\n" +
                        "added: " + input + "\n" +
                        "____________________________________________________________";
                System.out.println(output);
                tasks[taskCount] = input;
                taskCount++;

            }


        }
        System.out.println(byeMessage);
    }
}
