import java.util.Scanner;

public class Muller {
    public static void main(String[] args) {
        String logo = "____________________________________________________________";
        System.out.println(logo + "\nHello! I'm Muller\nWhat can I do for you?\n"
                                + logo);
        Scanner sc = new Scanner(System.in);
        while(true){
            String in = sc.nextLine();
            if(in.equals("bye")){
                break;
            } else {
                System.out.println(logo + "\n" + in + "\n" + logo);
            }
        }

        System.out.println(logo + "\nBye. Hope to see you again soon!\n" + logo);
    }
}
