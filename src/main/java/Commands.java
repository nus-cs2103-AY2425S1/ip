interface Commands {
    void run(Sentinel sentinel, String args) throws SentinelException;
}

class ListTasks implements Commands {
    @Override
    public void run(Sentinel sentinel, String args) throws SentinelException {
        sentinel.outputTaskList();
    }
}

class markDone implements Commands {
    @Override
    public void run(Sentinel sentinel, String args) throws SentinelException {
        String[] parsedArgs = args.split("\\s+", 2);

        if (parsedArgs.length < 2) {
            throw new SentinelException("You didn't put in a valid number!");
        }

        try {
            int taskNumber = Integer.parseInt(parsedArgs[1]);
            sentinel.markDone(taskNumber);
        } catch (NumberFormatException e) {
            throw new SentinelException("Invalid number!");
        }
    }
}

class markUndone implements Commands {
    @Override
    public void run(Sentinel sentinel, String args) throws SentinelException {
        String[] parsedArgs = args.split("\\s+", 2);

        if (parsedArgs.length < 2) {
            throw new SentinelException("You didn't put in a valid number!");
        }

        try {
            int taskNumber = Integer.parseInt(parsedArgs[1]);
            sentinel.markUndone(taskNumber);
        } catch (NumberFormatException e) {
            throw new SentinelException("Invalid number!");
        }
    }
}

class addTodo implements Commands {
    @Override
    public void run(Sentinel sentinel, String args) throws SentinelException {
        String[] parsedArgs = args.split("\\s+", 2);

        if (parsedArgs.length < 2) {
            throw new SentinelException("You did not add a todo name!");
        }

        sentinel.addTodo(parsedArgs[1]);
    }
}

class addEvent implements Commands {
    @Override
    public void run(Sentinel sentinel, String args) throws SentinelException {
        String[] parsedArgs = args.split("\\s+", 2);

        if (parsedArgs.length < 2) {
            throw new SentinelException("You did not add an event name!");
        }

        String[] parts = parsedArgs[1].split("/from|/to");

        if (parts.length < 3) {
            throw new SentinelException("You did not put in a timeframe!");
        }

        sentinel.addEvent(parts[0].trim(), parts[1].trim(), parts[2].trim());
    }
}

class addDeadline implements Commands {
    @Override
    public void run(Sentinel sentinel, String args) throws SentinelException {
        String[] parsedArgs = args.split("\\s+", 2);

        if (parsedArgs.length < 2) {
            throw new SentinelException("You did not add a deadline name!");
        }

        String[] parts = parsedArgs[1].split("/by");

        if (parts.length < 2) {
            throw new SentinelException("You did not put in a date!");
        }

        sentinel.addDeadline(parts[0].trim(), parts[1].trim());
    }
}


