public class KitaMissingBy extends KitaError {
    @Override
    public String toString() {
        return "Your task is missing the '/by <the date>' field :c";
    }
}
