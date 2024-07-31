import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HotelInfoController {
    private final HotelInfoView view;
    private final HotelInfoModel model;
    private final SelectHotelController selectHotelController;
    private final SelectHotelView selectHotelView;

    public HotelInfoController(HotelInfoView view, HotelInfoModel model, SelectHotelController selectHotelController, SelectHotelView selectHotelView) {
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

    private void viewHighLevelInfo() {
        String hotelName = model.getHotel().getName();
        int totalRooms = model.getHotel().getRoomsList().size();
        double totalIncome = model.getTotalIncome();
        String message = String.format("Hotel Name: %s\nTotal Number of Rooms: %d\nEstimated Monthly Earnings: $%.2f",
                hotelName, totalRooms, totalIncome);
        JOptionPane.showMessageDialog(view, message, "High-Level Information", JOptionPane.INFORMATION_MESSAGE);
    }

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
                    String message = String.format("Room Name: %s\nRoom Type: %s\nPrice Per Night: $%.2f\nStatus: %s",
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
                String message = String.format("Guest Name: %s\nRoom Name: %s\nRoom Type: %s\nPrice Per Night: $%.2f\nTotal Price: $%.2f",
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





    private void backToSelectionPage() {
        view.dispose(); // Close the current view
        selectHotelView.setVisible(true); // Show the selection view again
    }

    private void backToMainMenu() {
        view.dispose(); // Close the current view
        selectHotelController.goBackToMainMenu(); // Show the main menu
    }
}
