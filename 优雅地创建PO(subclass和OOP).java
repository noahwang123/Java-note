// Builder pattern for class hierarchies
// subClass
public abstract class Pizza {
    public enum Topping { HAM, MUSHROOM, ONION, PEPPER,SAUSAGE }
    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }
        abstract Pizza build();
        // Subclasses must override this method to return"this"
        protected abstract T self();
    }
    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone();
    }
}

//Super class
    public class NyPizza extends Pizza {
        public enum Size { SMALL, MEDIUM, LARGE }
        private final Size size;

        public static class Builder extends Pizza.Builder<Builder> {
            private final Size size;

            public Builder(Size size) {
                this.size = Objects.requireNonNull(size);
            }

            @Override
            public NyPizza build() {
                return new NyPizza(this);
            }

            @Override
            protected Builder self() { return this; }
        }

        private NyPizza(Builder builder) {
            super(builder);
            size = builder.size;
        }
    }

//调用
NyPizza pizza = new NyPizza.Builder(SMALL).addTopping(SAUSAGE).addTopping(ONION).build();

//具体类
    public class Calzone extends Pizza {
        private final boolean sauceInside;
        public static class Builder extends Pizza.Builder<Builder> {
            private boolean sauceInside = false; // Default
            public Builder sauceInside() {
                sauceInside = true;
                return this;
            }
            @Override 
            public Calzone build() {
                return new Calzone(this);
            }
            @Override 
            protected Builder self() { return this; }
        }
        private Calzone(Builder builder) {
            super(builder);
            sauceInside = builder.sauceInside;
        }
    }
//调用
Calzone calzone = new Calzone.Builder().addTopping(HAM).sauceInside().build();