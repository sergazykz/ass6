public class Ass6b {
    public static void main(String[] args) {
        Car basicCar = new BasicCar();
        Car sportsCar = new SportsPackage(basicCar);
        Car luxurySportsCar = new LuxuryPackage(sportsCar);

        System.out.println("Basic Car Description: " + basicCar.getDescription());
        System.out.println("Basic Car Cost: $" + basicCar.getCost());

        System.out.println("Sports Car Description: " + sportsCar.getDescription());
        System.out.println("Sports Car Cost: $" + sportsCar.getCost());

        System.out.println("Luxury Sports Car Description: " + luxurySportsCar.getDescription());
        System.out.println("Luxury Sports Car Cost: $" + luxurySportsCar.getCost());
    }

    interface Car {
        String getDescription();
        double getCost();
    }

    static class BasicCar implements Car {
        @Override
        public String getDescription() {
            return "Basic Car";
        }

        @Override
        public double getCost() {
            return 20000.0;
        }
    }

    static abstract class CarDecorator implements Car {
        protected Car car;

        public CarDecorator(Car car) {
            this.car = car;
        }
    }

    static class SportsPackage extends CarDecorator {
        public SportsPackage(Car car) {
            super(car);
        }

        @Override
        public String getDescription() {
            return car.getDescription() + ", Sports Package";
        }

        @Override
        public double getCost() {
            return car.getCost() + 5000.0;
        }
    }

    static class LuxuryPackage extends CarDecorator {
        public LuxuryPackage(Car car) {
            super(car);
        }

        @Override
        public String getDescription() {
            return car.getDescription() + ", Luxury Package";
        }

        @Override
        public double getCost() {
            return car.getCost() + 8000.0;
        }
    }
}
