import javax.swing.*;
import java.awt.*;

/**
 * The MainMenuView class represents the graphical user interface (GUI) for the main menu
 * of the hotel reservation system. It provides buttons for managing hotels, creating
 * reservations, and creating hotels, along with a display of the current number of
 * operating hotels.
 */
public class MainMenuView extends JFrame {
    private final JButton manageHotelsButton;          // Button to manage hotels
    private final JButton createReservationButton;      // Button to create a reservation
    private final JButton createHotelButton;            // Button to create a hotel
    private final JLabel hotelCountLabel;               // Label to display the count of operating hotels

    /**
     * Constructs a MainMenuView and initializes the GUI components.
     */
    public MainMenuView() {
        // Set up the frame
        setTitle("Hotel Reservation System");
        setSize(655, 420); // Set frame size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit application on close
        setLocationRelativeTo(null); // Center the frame on the screen

        // Create ASCII art for the title
        String asciiArt = """
                          ██╗    ██╗███████╗██╗      ██████╗ ██████╗ ███╗   ███╗███████╗               \s
                          ██║    ██║██╔════╝██║     ██╔════╝██╔═══██╗████╗ ████║██╔════╝               \s
                          ██║ █╗ ██║█████╗  ██║     ██║     ██║   ██║██╔████╔██║█████╗                 \s
                          ██║███╗██║██╔══╝  ██║     ██║     ██║   ██║██║╚██╔╝██║██╔══╝                 \s
                          ╚███╔███╔╝███████╗███████╗╚██████╗╚██████╔╝██║ ╚═╝ ██║███████╗               \s
                           ╚══╝╚══╝ ╚══════╝╚══════╝ ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝               \s
                                                                                             \s
                  ████████╗ ██████╗     ████████╗██╗  ██╗███████╗    ██╗  ██╗██████╗ ███████╗██╗
                  ╚══██╔══╝██╔═══██╗    ╚══██╔══╝██║  ██║██╔════╝    ██║  ██║██╔══██╗██╔════╝██║
                     ██║   ██║   ██║       ██║   ███████║█████╗      ███████║██████╔╝███████╗██║
                     ██║   ██║   ██║       ██║   ██╔══██║██╔══╝      ██╔══██║██╔══██╗╚════██║╚═╝
                     ██║   ╚██████╔╝       ██║   ██║  ██║███████╗    ██║  ██║██║  ██║███████║██╗
                     ╚═╝    ╚═════╝        ╚═╝   ╚═╝  ╚═╝╚══════╝    ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚═╝
                                                                                             \s
                """;

        // Create GUI components
        JLabel asciiArtLabel = new JLabel("<html><pre>" + asciiArt + "</pre></html>", SwingConstants.CENTER);
        JLabel instructionLabel = new JLabel("To get started, select any of the three options below.", SwingConstants.CENTER);
        manageHotelsButton = new JButton("Manage Hotels"); // Button for managing hotels
        createReservationButton = new JButton("Create a Reservation"); // Button for creating reservations
        createHotelButton = new JButton("Create a Hotel"); // Button for creating hotels
        hotelCountLabel = new JLabel("There are 1 hotels currently operating.", SwingConstants.CENTER); // Initial count text

        // Set layout and add components to the frame
        setLayout(new BorderLayout()); // Use border layout for main frame

        add(asciiArtLabel, BorderLayout.NORTH); // Add ASCII art at the top

        // Create a panel for the central content with GridBag layout
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
        gbc.insets = new Insets(10, 10, 10, 10); // Margin for each component

        // Add instructionLabel to centerPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0; // No vertical expansion
        centerPanel.add(instructionLabel, gbc);

        // Create a button panel for central buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout()); // Use flow layout for button arrangement
        buttonPanel.add(manageHotelsButton); // Add manage hotels button
        buttonPanel.add(createHotelButton); // Add create hotel button
        buttonPanel.add(createReservationButton); // Add create reservation button

        // Add buttonPanel to centerPanel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0; // No vertical expansion
        centerPanel.add(buttonPanel, gbc);

        // Add hotelCountLabel to centerPanel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 0; // No vertical expansion
        centerPanel.add(hotelCountLabel, gbc);

        add(centerPanel, BorderLayout.CENTER); // Add center panel to the main frame
    }

    /**
     * Returns the button for managing hotels.
     *
     * @return The manageHotelsButton.
     */
    public JButton getManageHotelsButton() {
        return manageHotelsButton;
    }

    /**
     * Returns the button for creating a reservation.
     *
     * @return The createReservationButton.
     */
    public JButton getCreateReservationButton() {
        return createReservationButton;
    }

    /**
     * Returns the button for creating a hotel.
     *
     * @return The createHotelButton.
     */
    public JButton getCreateHotelButton() {
        return createHotelButton;
    }

    /**
     * Updates the hotel count label with the current number of operating hotels.
     *
     * @param count The current number of operating hotels.
     */
    public void setHotelCount(int count) {
        hotelCountLabel.setText("There are " + count + " hotels currently operating.");
    }
}
