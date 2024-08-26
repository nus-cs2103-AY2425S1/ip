package nameless;

public class TypeOfException {
    public void showLoadingError() throws DukeException {
        throw new DukeException("Error loading file");
    }

    public void noIdea() throws DukeException {
        throw new DukeException("Sorry! I don't understand what you are saying." +
                " Please enter a valid command. todo, deadline, event, list, done, delete, bye");
    }

    public void todoFormatError() throws DukeException {
        throw new DukeException("incorrect format use 'todo <nameless.task>'");
    }

    public void deadlineFormatError() throws DukeException {
        throw new DukeException("incorrect format use 'deadline <nameless.task> /by <date time>'");
    }

    public void eventFormatError() throws DukeException {
        throw new DukeException("incorrect format use 'event <nameless.task> /from <date time> /to <date time>'");
    }

    public void timeFormatError() throws DukeException {
        throw new DukeException("incorrect format must be in yyyy-mm-dd hh:mm am/pm");
    }

}
