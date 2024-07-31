import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The HotelInfoController class handles user interactions for viewing information
 * about a specific hotel, including high-level details, available rooms, booked
 * rooms, individual room information, and reservation details.
 */
public class HotelInfoController {
    private final HotelInfoView view; // The view for displaying hotel information
    private final HotelInfoModel model; // The model containing hotel data
    private final SelectHotelController selectHotelController; // Controller for selecting a hotel
    private final SelectHotelView selectHotelView; // View for selecting a hotel

    /**
     * Constructs a HotelInfoController with the specified view and model.
     *
     * @param view                    The view to display hotel information.
     * @param model                   The model containing hotel data.
     * @param selectHotelController    The controller for selecting a hotel.
     * @param selectHotelView          The view for selecting a hotel.
     */
    public HotelInfoController(HotelInfoView view, HotelInfoModel model,
                               SelectHotelController selectHotelController,
                               SelectHotelView selectHotelView) {
        this.view = view;
        this.model = model;
        this.selectHotelController = selectHotelController;
        this.selectHotelView = selectHotelView;

        // Add action listeners to buttons
        this.view.getHighLevelInfoButton().addActionListener(e -> viewHighLevelInfo());
        this.view.getAvailableRoomsButton().addActionListener(e -> viewAvailableRooms());
        this.view.getBookedRoomsButton().addActionListener(e -> viewBookedRooms());
        this.view.getRoomInfoButton().addActionListener(e -> viewRoomInfo());
        this.view.getReservationInfoButton().addActionListener(e -> viewReservationInfo());

        // Add action listeners for bottom buttons
        this.view.getBackToSelectionButton().addActionListener(e -> backToSelectionPage());
        this.view.getBackToMainMenuButton().addActionListener(e -> backToMainMenu());
    }

    /**
     * Displays high-level information about the hotel, including the hotel name,
     * total number of rooms, and estimated monthly earnings.
     */
    private void viewHighLevelInfo() {
        String hotelName = model.getHotel().getName();
        int totalRooms = model.getHotel().getRoomsList().size();
        double totalIncome = model.getTotalIncome();

        String message = String.format("Hotel Name: %s\nTotal Number of Rooms: %d\nEstimated Monthly Earnings: PHP%.2f",
                hotelName, totalRooms, totalIncome);
        JOptionPane.showMessageDialog(view, message, "High-Level Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Prompts the user to enter check-in and check-out dates and displays available
     * rooms for the specified dates.
     */
    private void viewAvailableRooms() {
        // Check if there are any reservations before prompting for input
        if (model.getReservations().isEmpty()) {
            JOptionPane.showMessageDialog(view, "No reservations currently exist.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Terminate the method
        }

        String checkInInput = JOptionPane.showInputDialog(view, "Enter Check-In Date (1-30):");
        if (checkInInput == null) {
            return; // Terminate the method if canceled
        }

        String checkOutInput = JOptionPane.showInputDialog(view, "Enter Check-Out Date (1-30):");
        if (checkOutInput == null) {
            return; // Terminate the method if canceled
        }

        // Validate dates immediately after input
        try {
            int checkInDate = Integer.parseInt(checkInInput);
            int checkOutDate = Integer.parseInt(checkOutInput);

            if (checkInDate < 1 || checkOutDate > 30 || checkInDate >= checkOutDate) {
                throw new IllegalArgumentException();
            }

            ArrayList<Room> availableRooms = model.getAvailableRooms(checkInDate, checkOutDate);
            if (availableRooms.isEmpty()) {
                JOptionPane.showMessageDialog(view, "No available rooms for the selected dates.", "Available Rooms", JOptionPane.INFORMATION_MESSAGE);
                return; // Return to the HotelInfo menu
            }

            StringBuilder message = new StringBuilder("Available Rooms:\n");
            for (Room room : availableRooms) {
                message.append(room.getName()).append(" (").append(room.getType()).append(")\n");
            }

            // Create a scrollable text area for displaying available rooms
            JTextArea textArea = new JTextArea(message.toString());
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(300, 200)); // Small window size

            JOptionPane.showMessageDialog(view, scrollPane, "Available Rooms", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Invalid input! Please enter valid dates (1-30).", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Prompts the user to enter check-in and check-out dates and displays booked
     * rooms for the specified dates.
     */
    private void viewBookedRooms() {
        // Check if there are any reservations before prompting for input
        if (model.getReservations().isEmpty()) {
            JOptionPane.showMessageDialog(view, "No reservations currently exist.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Terminate the method
        }

        String checkInInput = JOptionPane.showInputDialog(view, "Enter Check-In Date (1-30):");
        if (checkInInput == null) {
            return; // Terminate the method if canceled
        }

        String checkOutInput = JOptionPane.showInputDialog(view, "Enter Check-Out Date (1-30):");
        if (checkOutInput == null) {
            return; // Terminate the method if canceled
        }

        // Validate dates immediately after input
        try {
            int checkInDate = Integer.parseInt(checkInInput);
            int checkOutDate = Integer.parseInt(checkOutInput);

            if (checkInDate < 1 || checkOutDate > 30 || checkInDate >= checkOutDate) {
                throw new IllegalArgumentException();
            }

            StringBuilder message = new StringBuilder("Booked Rooms:\n");
            boolean hasBookings = false; // Flag to check if there are any bookings
            for (Room room : model.getHotel().getRoomsList()) {
                for (Reservation reservation : room.getReservationsList()) {
                    if (reservation.getCheckInDate() >= checkInDate && reservation.getCheckOutDate() <= checkOutDate) {
                        message.append(room.getName()).append(" (").append(room.getType()).append(") - Reservation ID: ")
                                .append(reservation.getReservationID()).append("\n");
                        hasBookings = true; // Mark that we found at least one booking
                    }
                }
            }

            if (!hasBookings) {
                JOptionPane.showMessageDialog(view, "No reservations found for the selected dates.", "Booked Rooms", JOptionPane.INFORMATION_MESSAGE);
                return; // Return to the HotelInfo menu
            }

            // Create a scrollable text area for displaying booked rooms
            JTextArea textArea = new JTextArea(message.toString());
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(300, 200)); // Small window size

            JOptionPane.showMessageDialog(view, scrollPane, "Booked Rooms", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Invalid input! Please enter valid dates (1-30).", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Prompts the user to select a room and displays detailed information about that room.
     */
    private void viewRoomInfo() {
        ArrayList<Room> rooms = model.getHotel().getRoomsList();

        if (rooms.isEmpty()) {
            JOptionPane.showMessageDialog(view, "No rooms available in the hotel.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method if no rooms are available
        }

        String[] roomNames = rooms.stream().map(Room::getName).toArray(String[]::new);

        String selectedRoom = (String) JOptionPane.showInputDialog(view, "Select a Room to View Information:",
                "Room Information", JOptionPane.QUESTION_MESSAGE, null, roomNames, roomNames[0]);

        if (selectedRoom != null) {
            for (Room room : rooms) {
                if (room.getName().equals(selectedRoom)) {
                    String message = String.format("Room Name: %s\nRoom Type: %s\nPrice Per Night: PHP%.2f\nStatus: %s",
                            room.getName(),
                            room.getType(),
                            room.getBasePrice() * room.getType().getPriceMultiplier(),
                            room.getStatus());
                    JOptionPane.showMessageDialog(view, message, "Room Information", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
        }
    }

    /**
     * Prompts the user to select a reservation ID and displays detailed information
     * about the corresponding reservation.
     */
    private void viewReservationInfo() {
        ArrayList<Reservation> reservations = model.getReservations();

        if (reservations.isEmpty()) {
            JOptionPane.showMessageDialog(view, "No reservations found.", "Reservation Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[] reservationIds = reservations.stream().map(Reservation::getReservationID).toArray(String[]::new);
        JComboBox<String> reservationComboBox = new JComboBox<>(reservationIds);
        JOptionPane.showMessageDialog(view, reservationComboBox, "Select Reservation ID", JOptionPane.QUESTION_MESSAGE);

        String selectedId = (String) reservationComboBox.getSelectedItem();
        for (Reservation reservation : reservations) {
            if (reservation.getReservationID().equals(selectedId)) {
                String message = String.format("Guest Name: %s\nRoom Name: %s\nRoom Type: %s\nPrice Per Night: PHP%.2f\nTotal Price: PHP%.2f",
                        reservation.getGuestName(),
                        reservation.getRoom().getName(),
                        reservation.getRoom().getType(),
                        reservation.getRoom().getBasePrice() * reservation.getRoom().getType().getPriceMultiplier(),
                        reservation.getTotalPrice());
                JOptionPane.showMessageDialog(view, message, "Reservation Information", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
    }

    /**
     * Closes the current view and returns to the hotel selection page.
     */
    private void backToSelectionPage() {
        view.dispose(); // Close the current view
        selectHotelView.setVisible(true); // Show the selection view again
    }

    /**
     * Closes the current view and returns to the main menu.
     */
    private void backToMainMenu() {
        view.dispose(); // Close the current view
        selectHotelController.goBackToMainMenu(); // Show the main menu
    }
}
