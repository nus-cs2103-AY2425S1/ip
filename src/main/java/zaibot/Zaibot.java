package zaibot;

import zaibot.utils.Parser;
import zaibot.utils.Storage;
import zaibot.utils.TaskList;
import zaibot.utils.Ui;
import zaibot.command.Command;
import zaibot.exception.ZaibotException;

public class Zaibot {

    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage(taskList);

    public String runCommand(String input) {
        String endMessage;
        try {
            Command command = Parser.parse(input);
            endMessage = command.execute(taskList, storage);

        } catch (ZaibotException e) {
            endMessage = e.getMessage();
        }
        return endMessage;
    }
}
