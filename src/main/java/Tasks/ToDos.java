package Tasks;

import Exceptions.EmptyDescException;

public class ToDos extends Task {

    /**
     * Calls constructor of super class
     *
     * @throws EmptyDescException If desc is empty.
     */
    public ToDos(String desc) throws EmptyDescException {
        super(desc);
        if (desc.isEmpty()) {
            //throws exception if not desc is given
            throw new EmptyDescException
                    ("     OOPS!!! The description of a todo cannot be empty leh. " +
                            "Pls provide in the following format: " +
                            "todo read book");
        }
    }

    @Override
    public String print() {

        return "[T]" + super.print();
    }

}
