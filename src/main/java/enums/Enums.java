package enums;

public class Enums {
    public enum Salutation {
        MVR("Mvr"),
        DHR("Dhr");

        public final String salutation;

        private Salutation(String salutation) {
            this.salutation = salutation;
        }
    }
}
