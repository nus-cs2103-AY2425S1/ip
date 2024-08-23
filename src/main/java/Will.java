import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;


public class Will {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        DBHandler.load(tasks);

        String logo = "WILL";
        Interactions.greetMsg(logo);

        while (true) {
            String userInput = scanner.nextLine();

            if (Objects.equals(userInput, "bye")) {
                Interactions.endMsg();
                DBHandler.save(tasks);
                break;
            }

            if (Objects.equals(userInput, "list")) {
                Interactions.printTask(tasks);
                continue;
            }

            if (userInput.startsWith("mark")) {
                try{
                    int taskNumber = Integer.parseInt(userInput.substring(5).trim());
                    Interactions.markTask(tasks, taskNumber);
                }catch(StringIndexOutOfBoundsException | NumberFormatException e){
                    Interactions.blankMsg("number");
                }
                continue;
            }

            if (userInput.startsWith("unmark")) {
                try{
                    int taskNumber = Integer.parseInt(userInput.substring(7).trim());
                    Interactions.unmarkTask(tasks, taskNumber);
                }catch(StringIndexOutOfBoundsException | NumberFormatException e){
                    Interactions.blankMsg("number");
                }
                continue;
            }

            if (userInput.startsWith("delete")) {
                try{
                    int taskNumber = Integer.parseInt(userInput.substring(7).trim());
                    Interactions.deleteTask(tasks, taskNumber);
                }catch(StringIndexOutOfBoundsException | NumberFormatException e){
                    Interactions.blankMsg("number");
                }
                continue;
            }

            if (userInput.startsWith("todo")) {
                try{
                    String desc = userInput.substring(5).trim();
                    if(!desc.isEmpty()){
                        Interactions.addTask(tasks, Task.TaskType.TODO, desc);
                    }else{
                        Interactions.blankMsg("todo");
                    }
                }catch(StringIndexOutOfBoundsException e){
                    Interactions.blankMsg("todo");
                }

                continue;
            }

            if (userInput.startsWith("deadline")) {
                try {
                    String[] parts = userInput.substring(9).split(" /by ");
                    String desc = parts[0].trim();
                    if(!desc.isEmpty()){
                        Interactions.addTask(tasks, Task.TaskType.DEADLINE, desc, Utils.parseDateTime(parts[1].trim()));
                    }else{
                        Interactions.blankMsg("deadline");
                    }
                }catch(StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e){
                    Interactions.blankMsg("deadline");
                }
                continue;
            }

            if (userInput.startsWith("event")) {
                try{
                    String[] parts = userInput.substring(6).split(" /from | /to ");
                    String desc = parts[0].trim();
                    if(!desc.isEmpty()){
                        Interactions.addTask(tasks, Task.TaskType.EVENT, desc, Utils.parseDateTime(parts[1].trim()), Utils.parseDateTime(parts[2].trim()));
                    }else{
                        Interactions.blankMsg("event");
                    }
                }catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e){
                    Interactions.blankMsg("event");
                }
                continue;
            }

            Interactions.defaultMsg();
        }
    }
}
