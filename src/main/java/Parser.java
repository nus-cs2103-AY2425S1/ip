import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Parser {

    Parser(ArrayList<Task> tasks){

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();

            if (Objects.equals(userInput, "bye")) {
                Ui.endMsg();
                Storage.save(tasks);
                break;
            }

            if (Objects.equals(userInput, "list")) {
                Ui.printTask(tasks);
                continue;
            }

            if (userInput.startsWith("mark")) {
                try{
                    int taskNumber = Integer.parseInt(userInput.substring(5).trim());
                    TaskList.markTask(tasks, taskNumber);
                }catch(StringIndexOutOfBoundsException | NumberFormatException e){
                    Ui.blankMsg("number");
                }
                continue;
            }

            if (userInput.startsWith("unmark")) {
                try{
                    int taskNumber = Integer.parseInt(userInput.substring(7).trim());
                    TaskList.unmarkTask(tasks, taskNumber);
                }catch(StringIndexOutOfBoundsException | NumberFormatException e){
                    Ui.blankMsg("number");
                }
                continue;
            }

            if (userInput.startsWith("delete")) {
                try{
                    int taskNumber = Integer.parseInt(userInput.substring(7).trim());
                    TaskList.deleteTask(tasks, taskNumber);
                }catch(StringIndexOutOfBoundsException | NumberFormatException e){
                    Ui.blankMsg("number");
                }
                continue;
            }

            if (userInput.startsWith("todo")) {
                try{
                    String desc = userInput.substring(5).trim();
                    if(!desc.isEmpty()){
                        TaskList.addTask(tasks, Task.TaskType.TODO, desc);
                    }else{
                        Ui.blankMsg("todo");
                    }
                }catch(StringIndexOutOfBoundsException e){
                    Ui.blankMsg("todo");
                }

                continue;
            }

            if (userInput.startsWith("deadline")) {
                try {
                    String[] parts = userInput.substring(9).split(" /by ");
                    String desc = parts[0].trim();
                    if(!desc.isEmpty()){
                        TaskList.addTask(tasks, Task.TaskType.DEADLINE, desc, Utils.parseDateTime(parts[1].trim()));
                    }else{
                        Ui.blankMsg("deadline");
                    }
                }catch(StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e){
                    Ui.blankMsg("deadline");
                }
                continue;
            }

            if (userInput.startsWith("event")) {
                try{
                    String[] parts = userInput.substring(6).split(" /from | /to ");
                    String desc = parts[0].trim();
                    if(!desc.isEmpty()){
                        TaskList.addTask(tasks, Task.TaskType.EVENT, desc, Utils.parseDateTime(parts[1].trim()), Utils.parseDateTime(parts[2].trim()));
                    }else{
                        Ui.blankMsg("event");
                    }
                }catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e){
                    Ui.blankMsg("event");
                }
                continue;
            }

            Ui.defaultMsg();
        }


    }
}
