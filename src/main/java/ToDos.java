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
                    ("     OOPS!!! The description of a todo cannot be empty leh.");
        }
    }

    @Override
    public String print() {

        return "[T]" + super.print();
    }

}
