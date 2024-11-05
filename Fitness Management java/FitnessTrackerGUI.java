import javax.swing.*;
import java.awt.*;

public class FitnessTrackerGUI extends JFrame {
    private UserProfile userProfile;
    private JTextArea outputArea;

    public FitnessTrackerGUI() {
        userProfile = createUserProfile();
        initializeGUI();
    }

    private UserProfile createUserProfile() {
        String name = JOptionPane.showInputDialog(this, "Enter your name:");
        int age = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter your age:"));
        double weight = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter your weight (kg):"));
        double height = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter your height (m):"));
        String goal = JOptionPane.showInputDialog(this, "Enter your fitness goal (e.g., Lose Weight, Build Muscle):");

        return new UserProfile(name, age, weight, height, goal);
    }

    private void initializeGUI() {
        setTitle("Fitness Tracker - Modern GUI");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        getContentPane().setBackground(new Color(33, 43, 53));

        JLabel titleLabel = new JLabel("Fitness Tracker", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(245, 245, 245));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setBackground(new Color(45, 55, 65));
        outputArea.setForeground(new Color(224, 224, 224));
        outputArea.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        controlPanel.setBackground(new Color(33, 43, 53));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JButton viewProfileButton = createStyledButton("View Profile", new Color(76, 175, 80));
        JButton addActivityButton = createStyledButton("Add Activity", new Color(33, 150, 243));
        JButton settingsButton = createStyledButton("Settings", new Color(255, 193, 7));

        viewProfileButton.addActionListener(e -> displayProfile());
        addActivityButton.addActionListener(e -> addActivity());
        settingsButton.addActionListener(e -> displaySettings());

        controlPanel.add(viewProfileButton);
        controlPanel.add(addActivityButton);
        controlPanel.add(settingsButton);

        add(controlPanel, BorderLayout.SOUTH);
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color.darker(), 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        return button;
    }

    private void displayProfile() {
        outputArea.setText(userProfile.displayProfileSummary());
    }

    private void addActivity() {
        // Dropdown list for activity type
        String[] activityTypes = { "Running", "Cycling", "Swimming" };
        String type = (String) JOptionPane.showInputDialog(this, "Select activity type:",
                "Activity Type", JOptionPane.QUESTION_MESSAGE, null, activityTypes, activityTypes[0]);

        if (type == null) {
            JOptionPane.showMessageDialog(this, "No activity type selected.");
            return;
        }

        double duration = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter duration (in minutes):"));
        double parameter;

        if (type.equalsIgnoreCase("Running")) {
            parameter = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter distance (in kilometers):"));
        } else if (type.equalsIgnoreCase("Cycling")) {
            parameter = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter speed (in km/h):"));
        } else if (type.equalsIgnoreCase("Swimming")) {
            parameter = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter number of laps:"));
        } else {
            JOptionPane.showMessageDialog(this, "Invalid activity type.");
            return;
        }

        // Dropdown list for intensity
        String[] intensities = { "Low", "Medium", "High" };
        String intensity = (String) JOptionPane.showInputDialog(this, "Select intensity:",
                "Intensity Level", JOptionPane.QUESTION_MESSAGE, null, intensities, intensities[1]);

        if (intensity == null) {
            JOptionPane.showMessageDialog(this, "No intensity selected.");
            return;
        }

        // Dropdown list for location
        String[] locations = { "Indoor", "Outdoor" };
        String location = (String) JOptionPane.showInputDialog(this, "Select location:",
                "Location", JOptionPane.QUESTION_MESSAGE, null, locations, locations[0]);

        if (location == null) {
            JOptionPane.showMessageDialog(this, "No location selected.");
            return;
        }

        userProfile.addActivity(type, duration, parameter, intensity, location);
        JOptionPane.showMessageDialog(this, "Activity added successfully!");
    }

    private void displaySettings() {
        JOptionPane.showMessageDialog(this, "Settings feature coming soon!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FitnessTrackerGUI gui = new FitnessTrackerGUI();
            gui.setVisible(true);
        });
    }
}
