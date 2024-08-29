package ChatterBoxErrors;

public class ChatterBoxDataFileError extends ChatterBoxError {
    public ChatterBoxDataFileError(String fileName) {
        super("____________________________________________________________\n"
                + "The file " + fileName +" is not found\n"
                + "____________________________________________________________");
    }

    public ChatterBoxDataFileError() {
        super("""
              ____________________________________________________________
              The file is not found
              ____________________________________________________________""");
    }
}
