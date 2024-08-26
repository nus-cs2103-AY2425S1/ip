public class KitaIllegalDateFormat extends RuntimeException {
    @Override
    public String toString() {
        return "Please state any dates in the form of yyyy-mm-dd";
    }
}
