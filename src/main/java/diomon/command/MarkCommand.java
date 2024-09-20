package diomon.command;

import diomon.Storage;
import diomon.parser.Parser;
import diomon.task.TaskList;

import java.util.List;

public class MarkCommand extends Command{
    public MarkCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            StringBuilder response = new StringBuilder();
            List<Integer> indexList = Parser.processNumbers(input);
            for (Integer i : indexList) {
                tasks.mark(i - 1);
                response.append(String.format("( %s ) completed!!!\n", tasks.get(i - 1)));
            }
            setResponse(response.toString());
        } catch (NumberFormatException e) {
            setResponse("Argument given for completing a task is wrong, please try again");
        } catch (IndexOutOfBoundsException e) {
            setResponse("Index out of bound, please try again");
        } catch (RuntimeException e) {
            throw new RuntimeException("Something went wrong, please check your input");
        }
    }

}
