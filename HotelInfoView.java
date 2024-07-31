import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The HotelInfoView class represents the GUI for displaying hotel information,
 * allowing users to view different types of information about a hotel.
 */
public class HotelInfoView extends JFrame {
    private final JButton highLevelInfoButton;
    private final JButton availableRoomsButton;
    private final JButton bookedRoomsButton;
    private final JButton roomInfoButton;
    private final JButton reservationInfoButton;
    private final JButton backToSelectionButton;
    private final JButton backToMainMenuButton;

    /**
     * Constructs a HotelInfoView for the specified hotel.
     *
     * @param hotelName The name of the hotel to be displayed.
     */
    public HotelInfoView(String hotelName) {
        setTitle("Hotel Information");
        setSize(670, 420); // Adjusted size to 670 x 420
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Handle close operation manually
        setLocationRelativeTo(null); // Center the frame

        // Create components
        String asciiArt = """
                <html>\
                <pre>\
                ██╗   ██╗██╗███████╗██╗    ██╗    ██╗  ██╗ ██████╗ ████████╗███████╗██╗    \s
                ██║   ██║██║██╔════╝██║    ██║    ██║  ██║██╔═══██╗╚══██╔══╝██╔════╝██║    \s
                ██║   ██║██║█████╗  ██║ █╗ ██║    ███████║██║   ██║   ██║   █████╗  ██║    \s
                ╚██╗ ██╔╝██║██╔══╝  ██║███╗██║    ██╔══██║██║   ██║   ██║   ██╔══╝  ██║    \s
                 ╚████╔╝ ██║███████╗╚███╔███╔╝    ██║  ██║╚██████╔╝   ██║   ███████╗███████╗
                  ╚═══╝  ╚═╝╚══════╝ ╚══╝╚══╝     ╚═╝  ╚═╝ ╚═════╝    ╚═╝   ╚══════╝╚══════╝\
                </pre>\
                </html>""";

        JLabel asciiLabel = new JLabel(asciiArt, SwingConstants.CENTER);
        JLabel hotelNameLabel = new JLabel("<html>The hotel being managed is hotel " + hotelName + ".<br><br><center>Click what type of information to view:</center></html>", SwingConstants.CENTER);

        // Create buttons
        highLevelInfoButton = new JButton("High-Level Information");
        availableRoomsButton = new JButton("Available Rooms Per Date");
        bookedRoomsButton = new JButton("Booked Rooms Per Date");
        roomInfoButton = new JButton("Room Information");
        reservationInfoButton = new JButton("Reservation Information");
        backToSelectionButton = new JButton("Back to Hotel Selection");
        backToMainMenuButton = new JButton("Back to Main Menu");

        // Set fixed size for information buttons
        Dimension infoButtonSize = new Dimension(200, 40); // Fixed size for information buttons
        highLevelInfoButton.setPreferredSize(infoButtonSize);
        availableRoomsButton.setPreferredSize(infoButtonSize);
        bookedRoomsButton.setPreferredSize(infoButtonSize);
        roomInfoButton.setPreferredSize(infoButtonSize);
        reservationInfoButton.setPreferredSize(infoButtonSize);

        // Set fixed size for menu buttons (slightly smaller)
        Dimension menuButtonSize = new Dimension(190, 30); // Slightly smaller size for menu buttons
        backToSelectionButton.setPreferredSize(menuButtonSize);
        backToMainMenuButton.setPreferredSize(menuButtonSize);

        // Set layout and add components
        setLayout(new BorderLayout());

        // Create a panel for text components
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        textPanel.add(asciiLabel, BorderLayout.NORTH);
        textPanel.add(hotelNameLabel, BorderLayout.CENTER);
        add(textPanel, BorderLayout.NORTH);

        // Create a panel for the button layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Margin for each component

        // Add buttons to the button panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(highLevelInfoButton, gbc);

        gbc.gridx = 1;
        buttonPanel.add(availableRoomsButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(bookedRoomsButton, gbc);

        gbc.gridx = 1;
        buttonPanel.add(roomInfoButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonPanel.add(reservationInfoButton, gbc);

        // Add the button panel to the center
        add(buttonPanel, BorderLayout.CENTER); // Changed to add the button panel to the center

        // Create a panel for back buttons
        JPanel lastRowPanel = new JPanel();
        lastRowPanel.add(backToSelectionButton);
        lastRowPanel.add(backToMainMenuButton);
        add(lastRowPanel, BorderLayout.SOUTH); // Add back buttons at the bottom

        // Add window listener to handle close operation
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int response = JOptionPane.showConfirmDialog(
                        HotelInfoView.this,
                        "Are you sure you want to quit? All data will be lost.",
                        "Confirm Exit",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0); // Terminate the program
                }
            }
        });
    }

    /**
     * Returns the button for viewing high-level information.
     *
     * @return The high-level information button.
     */
    public JButton getHighLevelInfoButton() {
        return highLevelInfoButton;
    }

    /**
     * Returns the button for viewing available rooms.
     *
     * @return The available rooms button.
     */
    public JButton getAvailableRoomsButton() {
        return availableRoomsButton;
    }

    /**
     * Returns the button for viewing booked rooms.
     *
     * @return The booked rooms button.
     */
    public JButton getBookedRoomsButton() {
        return bookedRoomsButton;
    }

    /**
     * Returns the button for viewing room information.
     *
     * @return The room information button.
     */
    public JButton getRoomInfoButton() {
        return roomInfoButton;
    }

    /**
     * Returns the button for viewing reservation information.
     *
     * @return The reservation information button.
     */
    public JButton getReservationInfoButton() {
        return reservationInfoButton;
    }

    /**
     * Returns the button for going back to hotel selection.
     *
     * @return The back to hotel selection button.
     */
    public JButton getBackToSelectionButton() {
        return backToSelectionButton;
    }

    /**
     * Returns the button for going back to the main menu.
     *
     * @return The back to main menu button.
     */
    public JButton getBackToMainMenuButton() {
        return backToMainMenuButton;
    }

    /**
     * The main method to run the HotelInfoView.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HotelInfoView view = new HotelInfoView("Sample Hotel");
            view.setVisible(true);
        });
    }
}
