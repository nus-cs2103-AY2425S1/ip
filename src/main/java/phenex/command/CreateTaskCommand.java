package phenex.command;

import java.util.HashMap;
import java.util.Set;

import phenex.exception.PhenexException;
import phenex.task.Task.TaskType;

/**
 * Abstract class CreateTaskCommand to encapsulate a Command which creates a Task.
 */
public abstract class CreateTaskCommand extends Command {
    protected static HashMap<String, TaskType> typeMap = new HashMap<>();

    static {
        typeMap.put(phenex.task.Task.RECREATIONAL_TASK_SYMBOL, phenex.task.Task.TaskType.RECREATIONAL);
        typeMap.put(phenex.task.Task.PRODUCTIVE_TASK_SYMBOL, phenex.task.Task.TaskType.PRODUCTIVE);
        typeMap.put(phenex.task.Task.OTHER_TASK_SYMBOL, phenex.task.Task.TaskType.OTHERS);
    }

    protected String name;
    protected String typeSymbol;

    public CreateTaskCommand(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static TaskType getTaskTypeFromSymbol(String typeSymbol) throws PhenexException {
        if (!typeMap.containsKey(typeSymbol)) {
            throw new PhenexException("Invalid task Type Symbol provided.\nValid symbols are 'O', 'P', 'R'");
        }
        return typeMap.get(typeSymbol);
    }

    public void setTypeSymbol(String typeSymbol) {
        this.typeSymbol = typeSymbol;
    }

    public static Set<String> getValidSymbols() {
        return typeMap.keySet();
    }

    @Override
    public boolean isTerminatingCommand() {
        return false;
    }
}
