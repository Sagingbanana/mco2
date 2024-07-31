import javax.swing.*;
import java.awt.*;

public class HotelInfoView extends JFrame {
    private JLabel hotelNameLabel;
    private JButton highLevelInfoButton;
    private JButton availableRoomsButton;
    private JButton bookedRoomsButton;
    private JButton roomInfoButton;
    private JButton reservationInfoButton;
    private JButton backToSelectionButton;
    private JButton backToMainMenuButton;

    public HotelInfoView(String hotelName) {
        setTitle("Hotel Information");
        setSize(670, 420); // Adjusted size to 670 x 420
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Create components
        String asciiArt = "<html>" +
                "<pre>" +
                "██╗   ██╗██╗███████╗██╗    ██╗    ██╗  ██╗ ██████╗ ████████╗███████╗██╗     \n" +
                "██║   ██║██║██╔════╝██║    ██║    ██║  ██║██╔═══██╗╚══██╔══╝██╔════╝██║     \n" +
                "██║   ██║██║█████╗  ██║ █╗ ██║    ███████║██║   ██║   ██║   █████╗  ██║     \n" +
                "╚██╗ ██╔╝██║██╔══╝  ██║███╗██║    ██╔══██║██║   ██║   ██║   ██╔══╝  ██║     \n" +
                " ╚████╔╝ ██║███████╗╚███╔███╔╝    ██║  ██║╚██████╔╝   ██║   ███████╗███████╗\n" +
                "  ╚═══╝  ╚═╝╚══════╝ ╚══╝╚══╝     ╚═╝  ╚═╝ ╚═════╝    ╚═╝   ╚══════╝╚══════╝" +
                "</pre>" +
                "</html>";

        JLabel asciiLabel = new JLabel(asciiArt, SwingConstants.CENTER);
        hotelNameLabel = new JLabel("<html>The hotel being managed is hotel " + hotelName + ".<br><br><center>Click what type of information to view:</center></html>", SwingConstants.CENTER);

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
    }

    public JButton getHighLevelInfoButton() {
        return highLevelInfoButton;
    }

    public JButton getAvailableRoomsButton() {
        return availableRoomsButton;
    }

    public JButton getBookedRoomsButton() {
        return bookedRoomsButton;
    }

    public JButton getRoomInfoButton() {
        return roomInfoButton;
    }

    public JButton getReservationInfoButton() {
        return reservationInfoButton;
    }

    public JButton getBackToSelectionButton() {
        return backToSelectionButton;
    }

    public JButton getBackToMainMenuButton() {
        return backToMainMenuButton;
    }
}
