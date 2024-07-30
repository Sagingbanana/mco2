import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JFrame {
    private JButton manageHotelsButton;
    private JButton createReservationButton;
    private JButton createHotelButton;
    private JLabel hotelCountLabel;

    public MainMenuView() {
        // Set up the frame
        setTitle("Hotel Reservation System");
        setSize(655, 420); // Adjusted height
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Create components
        String asciiArt = "          ██╗    ██╗███████╗██╗      ██████╗ ██████╗ ███╗   ███╗███████╗                \n" +
                "          ██║    ██║██╔════╝██║     ██╔════╝██╔═══██╗████╗ ████║██╔════╝                \n" +
                "          ██║ █╗ ██║█████╗  ██║     ██║     ██║   ██║██╔████╔██║█████╗                  \n" +
                "          ██║███╗██║██╔══╝  ██║     ██║     ██║   ██║██║╚██╔╝██║██╔══╝                  \n" +
                "          ╚███╔███╔╝███████╗███████╗╚██████╗╚██████╔╝██║ ╚═╝ ██║███████╗                \n" +
                "           ╚══╝╚══╝ ╚══════╝╚══════╝ ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝                \n" +
                "                                                                              \n" +
                "  ████████╗ ██████╗     ████████╗██╗  ██╗███████╗    ██╗  ██╗██████╗ ███████╗██╗\n" +
                "  ╚══██╔══╝██╔═══██╗    ╚══██╔══╝██║  ██║██╔════╝    ██║  ██║██╔══██╗██╔════╝██║\n" +
                "     ██║   ██║   ██║       ██║   ███████║█████╗      ███████║██████╔╝███████╗██║\n" +
                "     ██║   ██║   ██║       ██║   ██╔══██║██╔══╝      ██╔══██║██╔══██╗╚════██║╚═╝\n" +
                "     ██║   ╚██████╔╝       ██║   ██║  ██║███████╗    ██║  ██║██║  ██║███████║██╗\n" +
                "     ╚═╝    ╚═════╝        ╚═╝   ╚═╝  ╚═╝╚══════╝    ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚═╝\n" +
                "                                                                              \n";

        JLabel asciiArtLabel = new JLabel("<html><pre>" + asciiArt + "</pre></html>", SwingConstants.CENTER);
        JLabel instructionLabel = new JLabel("To get started, select any of the three options below.", SwingConstants.CENTER);
        manageHotelsButton = new JButton("Manage Hotels");
        createReservationButton = new JButton("Create a Reservation");
        createHotelButton = new JButton("Create a Hotel");
        hotelCountLabel = new JLabel("There are 0 hotels currently operating.", SwingConstants.CENTER); // Initial text

        // Set layout and add components
        setLayout(new BorderLayout());

        add(asciiArtLabel, BorderLayout.NORTH);

        // Create a panel for the central content
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Margin for each component

        // Add instructionLabel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0; // No vertical expansion
        centerPanel.add(instructionLabel, gbc);

        // Add buttonPanel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(manageHotelsButton);
        buttonPanel.add(createHotelButton);
        buttonPanel.add(createReservationButton);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0; // Adjusted to fit between the instructionLabel and hotelCountLabel
        centerPanel.add(buttonPanel, gbc);

        // Add hotelCountLabel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 0; // No vertical expansion
        centerPanel.add(hotelCountLabel, gbc);

        add(centerPanel, BorderLayout.CENTER);
    }

    public JButton getManageHotelsButton() {
        return manageHotelsButton;
    }

    public JButton getCreateReservationButton() {
        return createReservationButton;
    }

    public JButton getCreateHotelButton() {
        return createHotelButton;
    }

    public void setHotelCount(int count) {
        hotelCountLabel.setText("There are " + count + " hotels currently operating.");
    }
}
