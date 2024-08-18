import java.util.Scanner;

public class Nameless {
    private static final String line = "_____________________________________________________________";
    private static final String name = "Nameless";
    private static final String greetings = "Hello, I'm " + name + "\n" + "What can I do for you?";
    private static final String goodbye = "Bye. Hope to see you again!";
    private static final String[] tasks = new String[100];
    public static void main(String[] args) {
        System.out.println(line + "\n" + greetings + "\n" + line);
        int counter = 0;
        while(true){
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if(input.equals("bye")){
                break;
            } else if (input.equals("list")) {
                System.out.println(line);
                for(int i = 0; i < counter; i++){
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println(line);
            } else {
                tasks[counter] = input;
                System.out.println(line + "\n" + "added: " + input + "\n" + line);
                counter++;
            }
        }

        System.out.println(line + "\n" + goodbye + "\n" + line);
    }
}

