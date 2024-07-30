import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JFrame {
    private JButton createHotelButton;
    private JButton viewHotelButton;
    private JButton manageHotelButton;
    private JButton simulateBookingButton;

    public MainMenuView() {
        setTitle("Hotel Reservation System");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);
        setResizable(false);

        ImageIcon image = new ImageIcon("logo.png");
        setIconImage(image.getImage());
//        getContentPane().setBackground(Color.black);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 100, 100));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        createHotelButton = new JButton("Create Hotel");
        viewHotelButton = new JButton("View Hotel");
        manageHotelButton = new JButton("Manage Hotel");
        simulateBookingButton = new JButton("Simulate Booking");

        buttonPanel.add(createHotelButton);
        buttonPanel.add(viewHotelButton);
        buttonPanel.add(manageHotelButton);
        buttonPanel.add(simulateBookingButton);

        JLabel titleLabel = new JLabel("Hotel Reservation System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    // Getter methods for buttons
    public JButton getCreateHotelButton() { return createHotelButton; }
    public JButton getViewHotelButton() { return viewHotelButton; }
    public JButton getManageHotelButton() { return manageHotelButton; }
    public JButton getSimulateBookingButton() { return simulateBookingButton; }
}