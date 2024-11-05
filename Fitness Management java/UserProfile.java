import java.util.ArrayList;
import java.util.List;

class UserProfile {
    private String name;
    private int age;
    private double weight;
    private double height;
    private String goal;
    private List<Activity> activities;

    public UserProfile(String name, int age, double weight, double height, String goal) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.goal = goal;
        this.activities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getGoal() {
        return goal;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void addActivity(String type, double duration, double parameter, String intensity, String location) {
        Activity activity;
        switch (type.toLowerCase()) {
            case "running":
                activity = new RunningActivity(duration, parameter, intensity, location);
                break;
            case "cycling":
                activity = new CyclingActivity(duration, parameter, intensity, location);
                break;
            case "swimming":
                activity = new SwimmingActivity(duration, (int) parameter, intensity, location);
                break;
            default:
                throw new IllegalArgumentException("Unknown activity type: " + type);
        }
        activities.add(activity);
    }

    public double getTotalCaloriesBurned() {
        return activities.stream().mapToDouble(Activity::caloriesBurned).sum();
    }

    public String displayProfileSummary() {
        StringBuilder summary = new StringBuilder("User Profile:\n");
        summary.append("Name: ").append(name).append("\n");
        summary.append("Age: ").append(age).append("\n");
        summary.append("Weight: ").append(weight).append(" kg\n");
        summary.append("Height: ").append(height).append(" m\n");
        summary.append("Goal: ").append(goal).append("\n");
        summary.append("Total Calories Burned: ").append(getTotalCaloriesBurned()).append("\n");

        summary.append("\nActivities:\n");
        for (Activity activity : activities) {
            summary.append(activity.getType()).append(" - Duration: ").append(activity.getDuration())
                    .append(" mins, Intensity: ").append(activity.getIntensity())
                    .append(", Location: ").append(activity.getLocation())
                    .append(", Calories Burned: ").append(activity.caloriesBurned()).append("\n");
        }
        return summary.toString();
    }
}
