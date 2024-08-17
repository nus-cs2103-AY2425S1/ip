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
        Task[] tasks = new Task[100];
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
                System.out.println("Here are the tasks in your list");
                for (int i = 0; i < taskCount; i++){
                    System.out.println(i+1 + ". " + tasks[i]);
                }
            }else if(input.startsWith("mark")){
                System.out.println("Nice! I've marked this task as done:");
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                Task currTask = tasks[index];
                currTask.mark();
                System.out.println(currTask);
            }else if(input.startsWith("unmark")){
                System.out.println("Ok! I've marked this task as not done:");
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                Task currTask = tasks[index];
                currTask.unMark();
                System.out.println(currTask);
            }else{
                output = "____________________________________________________________\n" +
                        "added: " + input + "\n" +
                        "____________________________________________________________";
                System.out.println(output);
                tasks[taskCount] = new Task(input);
                taskCount++;

            }


        }
        System.out.println(byeMessage);
    }
}
