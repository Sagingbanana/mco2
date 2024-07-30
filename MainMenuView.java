import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JFrame {
    private JButton manageHotelsButton;
    private JButton createReservationButton;
    private JLabel hotelCountLabel;

    public MainMenuView() {
        // Set up the frame
        setTitle("Hotel Reservation System");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Create components
        JLabel welcomeLabel = new JLabel("<html><div style='text-align: center;'>Welcome to the Hotel Reservation System!<br>To get started, select any of the two options below.</div></html>", SwingConstants.CENTER);
        manageHotelsButton = new JButton("Manage Hotels");
        createReservationButton = new JButton("Create a Reservation");
        hotelCountLabel = new JLabel("There are 0 hotels currently operating.", SwingConstants.CENTER); // Initial text

        // Set layout and add components
        setLayout(new BorderLayout());
        add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(manageHotelsButton);
        buttonPanel.add(createReservationButton);

        add(buttonPanel, BorderLayout.CENTER);
        add(hotelCountLabel, BorderLayout.SOUTH); // Add the hotel count label at the bottom
    }

    public JButton getManageHotelsButton() {
        return manageHotelsButton;
    }

    public JButton getCreateReservationButton() {
        return createReservationButton;
    }

    public void setHotelCount(int count) {
        hotelCountLabel.setText("There are " + count + " hotels currently operating.");
    }
}
