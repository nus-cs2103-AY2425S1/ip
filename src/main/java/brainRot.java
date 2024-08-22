import java.util.Scanner;
public class brainRot {
    public static void main(String[] args) {

        //creating an array with a number to memorise how many memory slots have been taken
        Task[] arr = new Task[100];
        int mem = 1;

        Scanner reader = new Scanner(System.in);

        String greeting = "____________________________________________________________ \n"
        + "Hello! I'm fanumTaxRizzlerOhioSigmaLooksmaxxer\n"
        + "What can I do for you?\n"
        + "____________________________________________________________ \n";

        String goodBye = "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";

        System.out.println(greeting);
        String answer = reader.nextLine();

        //logic to check if "bye" or "list" has been said
        while(!answer.equals("bye")) {
            if(answer.equals("list")) {
                System.out.println("____________________________________________________________ \n"
                + "Here are the tasks in your list:\n");

                for(int i = 1 ; i < mem; i ++) {
                    System.out.println(i + "." + arr[i].toString());
                }
                System.out.println("\n"
                + "____________________________________________________________ \n");

                reader = new Scanner(System.in);
                answer = reader.nextLine();


            } else if (answer.contains("unmark")) {
                int index = answer.charAt(7) - 48;
//                System.out.println(answer.charAt(5));
//                System.out.println(index);
//                System.out.println(arr[1]);
                arr[index].unmark();
                reader = new Scanner(System.in);
                answer = reader.nextLine();
            } else if (answer.contains("mark")) {
                int index = answer.charAt(5) - 48;
                arr[index].mark();
                reader = new Scanner(System.in);
                answer = reader.nextLine();
            } else {
                System.out.println(answer);
                arr[mem] = new Task(answer);
                mem++;
                reader = new Scanner(System.in);
                answer = reader.nextLine();
            }

        }
        System.out.println(goodBye);
    }
}
