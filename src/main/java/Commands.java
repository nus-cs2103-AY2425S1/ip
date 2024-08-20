import java.util.Scanner;

interface Commands {
    void run(Sentinel sentinel, String args);
}

class ListTasks implements Commands {
    @Override
    public void run(Sentinel sentinel, String args) {
        sentinel.outputTaskList();
    }
}

class markDone implements Commands {
    @Override
    public void run(Sentinel sentinel, String args) {
        String[] parsedArgs = args.split("\\s+", 2);

        if (parsedArgs.length < 2) {
            sentinel.say("You didn't put in a valid number!");
            return;
        }

        try {
            int taskNumber = Integer.parseInt(parsedArgs[1]);
            sentinel.markDone(taskNumber);
        } catch (NumberFormatException e) {
            sentinel.say("Invalid number!");
        }
    }
}

class markUndone implements Commands {
    @Override
    public void run(Sentinel sentinel, String args) {
        String[] parsedArgs = args.split("\\s+", 2);

        if (parsedArgs.length < 2) {
            sentinel.say("You didn't put in a valid number!");
            return;
        }

        try {
            int taskNumber = Integer.parseInt(parsedArgs[1]);
            sentinel.markUndone(taskNumber);
        } catch (NumberFormatException e) {
            sentinel.say("Invalid number!");
        }
    }
}

class addTodo implements Commands {
    @Override
    public void run(Sentinel sentinel, String args) {
        String[] parsedArgs = args.split("\\s+", 2);

        System.out.println(parsedArgs[0]);

        if (parsedArgs.length < 2) {
            sentinel.say("Invalid todo name");
            return;
        }

        sentinel.addTodo(parsedArgs[1]);
    }
}

class addEvent implements Commands {
    @Override
    public void run(Sentinel sentinel, String args) {
        String[] parsedArgs = args.split("\\s+", 2);

        if (parsedArgs.length < 2) {
            sentinel.say("Invalid event name");
            return;
        }

        String[] parts = parsedArgs[1].split("/from|/to");

        sentinel.addEvent(parts[0].trim(), parts[1].trim(), parts[2].trim());
    }
}

class addDeadline implements Commands {
    @Override
    public void run(Sentinel sentinel, String args) {
        String[] parsedArgs = args.split("\\s+", 2);

        if (parsedArgs.length < 2) {
            sentinel.say("Invalid deadline name");
            return;
        }

        String[] parts = parsedArgs[1].split("/by");

        sentinel.addDeadline(parts[0].trim(), parts[1].trim());
    }
}


