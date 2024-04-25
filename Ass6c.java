public class Ass6c {
    public static void main(String[] args) {
        WAdapter adapterA = new APIAAdapter(new APIA());
        WAdapter adapterB = new APIBAdapter(new APIB());

        try {
            WData dataA = adapterA.getWeather("Shymkent");
            System.out.println("Weather in Shymkent: " + dataA);
            WData dataB = adapterB.getWeather("Almaty");
            System.out.println("Weather in Almaty: " + dataB);
        } catch (Exception e) {
            System.err.println("Error retrieving weather data: " + e.getMessage());
        }
    }

    interface WAdapter {
        WData getWeather(String location);
    }

    static class APIAAdapter implements WAdapter {
        private APIA apiA;

        public APIAAdapter(APIA apiA) {
            this.apiA = apiA;
        }

        public WData getWeather(String location) {
            APIAData data = apiA.getWeather(location);
            return new WData(data.temp + "°C", data.humidity + "%", data.wind + " km/h");
        }
    }

    static class APIBAdapter implements WAdapter {
        private APIB apiB;

        public APIBAdapter(APIB apiB) {
            this.apiB = apiB;
        }

        public WData getWeather(String location) {
            APIBData data = apiB.fetchWeather(location);
            return new WData(data.temperature() + "°C", data.humidityLevel() + "%", data.windSpeed() + " km/h");
        }
    }

    static class APIA {
        public APIAData getWeather(String location) {
            return new APIAData(22.0, 65, 15); // Mock data
        }
    }

    static class APIAData {
        double temp;
        int humidity;
        int wind;

        public APIAData(double temp, int humidity, int wind) {
            this.temp = temp;
            this.humidity = humidity;
            this.wind = wind;
        }
    }

    static class APIB {
        public APIBData fetchWeather(String location) {
            return new APIBData(19.0, 70, 10); // Mock data
        }
    }

    static class APIBData {
        private double temperature;
        private int humidityLevel;
        private int windSpeed;

        public APIBData(double temperature, int humidityLevel, int windSpeed) {
            this.temperature = temperature;
            this.humidityLevel = humidityLevel;
            this.windSpeed = windSpeed;
        }

        public double temperature() {
            return temperature;
        }

        public int humidityLevel() {
            return humidityLevel;
        }

        public int windSpeed() {
            return windSpeed;
        }
    }

    static class WData {
        String temp;
        String humidity;
        String wind;

        public WData(String temp, String humidity, String wind) {
            this.temp = temp;
            this.humidity = humidity;
            this.wind = wind;
        }

        public String toString() {
            return "Temperature: " + temp + ", Wind: " + wind;
        }
    }
}
