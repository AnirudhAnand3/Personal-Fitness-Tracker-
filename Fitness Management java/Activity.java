abstract class Activity {
    protected String name;
    protected double duration; // in minutes
    protected String intensity; // e.g., Low, Medium, High
    protected String location; // e.g., Indoor, Outdoor

    public Activity(String name, double duration, String intensity, String location) {
        this.name = name;
        this.duration = duration;
        this.intensity = intensity;
        this.location = location;
    }

    public abstract double caloriesBurned();

    public String getName() {
        return name;
    }

    public double getDuration() {
        return duration;
    }

    public String getIntensity() {
        return intensity;
    }

    public String getLocation() {
        return location;
    }

    public abstract String getType();
}

class RunningActivity extends Activity {
    private double distance; // in kilometers

    public RunningActivity(double duration, double distance, String intensity, String location) {
        super("Running", duration, intensity, location);
        this.distance = distance;
    }

    @Override
    public double caloriesBurned() {
        double intensityFactor = switch (intensity.toLowerCase()) {
            case "high" -> 1.5;
            case "medium" -> 1.2;
            default -> 1.0;
        };
        return 0.1 * distance * 60 * intensityFactor;
    }

    @Override
    public String getType() {
        return "Running";
    }
}

class CyclingActivity extends Activity {
    private double speed; // average speed in km/h

    public CyclingActivity(double duration, double speed, String intensity, String location) {
        super("Cycling", duration, intensity, location);
        this.speed = speed;
    }

    @Override
    public double caloriesBurned() {
        double intensityFactor = switch (intensity.toLowerCase()) {
            case "high" -> 1.5;
            case "medium" -> 1.2;
            default -> 1.0;
        };
        return 0.05 * speed * duration * intensityFactor;
    }

    @Override
    public String getType() {
        return "Cycling";
    }
}

class SwimmingActivity extends Activity {
    private int laps;

    public SwimmingActivity(double duration, int laps, String intensity, String location) {
        super("Swimming", duration, intensity, location);
        this.laps = laps;
    }

    @Override
    public double caloriesBurned() {
        double intensityFactor = switch (intensity.toLowerCase()) {
            case "high" -> 1.5;
            case "medium" -> 1.2;
            default -> 1.0;
        };
        return 3.5 * laps * intensityFactor;
    }

    @Override
    public String getType() {
        return "Swimming";
    }
}
