import java.util.Scanner;

public class Nameless {
    private static final String line = "_____________________________________________________________";
    private static final String name = "Nameless";
    private static final String greetings = "Hello, I'm " + name + "\n" + "What can I do for you?";
    private static final String goodbye = "Bye. Hope to see you again!";
    private static final Task[] tasks = new Task[100];
    private static int splitWord(String input){
        String words[] = input.split(" ");
        return Integer.parseInt(words[1]) - 1;
    }
    public static void main(String[] args) {
        System.out.println(line + "\n" + greetings + "\n" + line);
        int counter = 0;
        while(true){
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if(input.equals("bye")){
                break;
            } else if (input.equals("list")) {
                //list tasking
                System.out.println(line + "\n Here are the tasks in your list:");
                for(int i = 0; i < counter; i++){
                    System.out.println(tasks[i].getDescription(i));
                }
                System.out.println(line);
            } else if (input.matches("mark \\d+")) {
                System.out.println(line);
                tasks[splitWord(input)].markTask();
                System.out.println(line);
            } else if (input.matches("unmark \\d+")) {
                System.out.println(line);
                tasks[splitWord(input)].unMarkTask();
                System.out.println(line);
            } else {
                //store tasking
                tasks[counter] = new Task(input);
                System.out.println(line + "\n" + tasks[counter].toString() + "\n" + line);
                counter++;
            }
        }

        System.out.println(line + "\n" + goodbye + "\n" + line);
    }
}

