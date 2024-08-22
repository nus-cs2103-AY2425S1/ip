import java.util.Scanner;

public class B_word {
    public static final String hline = "____________________________________________________________\n";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ToDoHandler th = new ToDoHandler();

        System.out.print(hline +
                " Hello! I'm 'B word'\n" +
                " What can I do for you?\n" +
                hline);

        // plan:
        // while loop, String s = scanner nextLine, strip
        // echo

        enum States {to_loop, to_exit, to_list}
        States currentState = States.to_loop;

        while (currentState != States.to_exit) {
            String s = sc.nextLine();
            System.out.print(hline);
            String tmp = s.strip();
            if (tmp.equals("bye")) {
                currentState = States.to_exit;
                break;
            } else if (tmp.equals("list")) {
                currentState = States.to_list;
                System.out.println(th.getTasksString());
                currentState = States.to_loop;
            } else {
                 th.addToDo(tmp);
                //System.out.println(tmp);
                System.out.println("added: " + tmp);
            }
            System.out.print(hline);
        }

        System.out.println(
                " Bye. Hope to see you again soon!\n" +
                hline);
    }
}
