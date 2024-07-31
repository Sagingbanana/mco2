import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The CreateReservationView class represents the GUI for creating a reservation.
 * It allows users to input guest information, select a hotel and room, and apply a discount code.
 */
public class CreateReservationView extends JFrame {
    private final JTextField guestNameField; // Text field for guest name input
    private final JTextField checkInDateField; // Text field for check-in date input
    private final JTextField checkOutDateField; // Text field for check-out date input
    private final JComboBox<String> hotelComboBox; // Combo box for selecting a hotel
    private final JComboBox<String> roomComboBox; // Combo box for selecting a room
    private final JTextField discountCodeField; // Text field for discount code input
    private final JButton confirmButton; // Button to confirm reservation
    private final JButton cancelButton; // Button to cancel reservation

    /**
     * Constructs the CreateReservationView and initializes the GUI components.
     */
    public CreateReservationView() {
        setTitle("Create Reservation");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Handle close operation manually
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Adding padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Guest Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Guest Name:"), gbc);
        guestNameField = new JTextField(15);
        gbc.gridx = 1;
        add(guestNameField, gbc);

        // Check-In Date
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Check-In Date (1-30):"), gbc);
        checkInDateField = new JTextField(15);
        gbc.gridx = 1;
        add(checkInDateField, gbc);

        // Check-Out Date
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Check-Out Date (2-31):"), gbc);
        checkOutDateField = new JTextField(15);
        gbc.gridx = 1;
        add(checkOutDateField, gbc);

        // Hotel
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Hotel:"), gbc);
        hotelComboBox = new JComboBox<>(); // Initialize hotel selection combo box
        gbc.gridx = 1;
        add(hotelComboBox, gbc);

        // Room
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Room:"), gbc);
        roomComboBox = new JComboBox<>(); // Initialize room selection combo box
        gbc.gridx = 1;
        add(roomComboBox, gbc);

        // Discount Code
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Discount Code:"), gbc);
        discountCodeField = new JTextField(15); // Initialize discount code input field
        gbc.gridx = 1;
        add(discountCodeField, gbc);

        // OK and Cancel buttons
        confirmButton = new JButton("Confirm"); // Button for confirming the reservation
        cancelButton = new JButton("Cancel"); // Button for canceling the reservation

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(confirmButton, gbc); // Add confirm button

        gbc.gridx = 1;
        add(cancelButton, gbc); // Add cancel button

        setLocationRelativeTo(null);  // Center the window on the screen

        // Add window listener to handle close operation
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int response = JOptionPane.showConfirmDialog(
                        CreateReservationView.this,
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

    // Getters for GUI components

    public JTextField getGuestNameField() {
        return guestNameField; // Return the guest name text field
    }

    public JTextField getCheckInDateField() {
        return checkInDateField; // Return the check-in date text field
    }

    public JTextField getCheckOutDateField() {
        return checkOutDateField; // Return the check-out date text field
    }

    public JComboBox<String> getHotelComboBox() {
        return hotelComboBox; // Return the hotel combo box
    }

    public JComboBox<String> getRoomComboBox() {
        return roomComboBox; // Return the room combo box
    }

    public JTextField getDiscountCodeField() {
        return discountCodeField; // Return the discount code text field
    }

    public JButton getConfirmButton() {
        return confirmButton; // Return the confirm button
    }

    public JButton getCancelButton() {
        return cancelButton; // Return the cancel button
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CreateReservationView view = new CreateReservationView();
            view.setVisible(true); // Make the view visible
        });
    }
}
