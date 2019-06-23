package enums;

public class Enums {
    public enum Salutation {
        MVR("Mvr"),
        DHR("Dhr");

        public final String salutationValue;

        private Salutation(String salutation) {
            this.salutationValue = salutation;
        }
    }
}
