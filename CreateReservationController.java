import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CreateReservationController {
    private final CreateReservationView view;
    private final CreateReservationModel model;
    private final MainMenuView mainMenuView;

    public CreateReservationController(CreateReservationView view, CreateReservationModel model, MainMenuView mainMenuView) {
        this.view = view;
        this.model = model;
        this.mainMenuView = mainMenuView;

        populateHotels();
        setupListeners();

        view.getHotelComboBox().addActionListener(e -> populateRooms());

        view.getConfirmButton().addActionListener(e -> createReservation());

        view.getCancelButton().addActionListener(e -> {
            view.dispose();
            mainMenuView.setVisible(true);
        });
    }

    private void populateHotels() {
        view.getHotelComboBox().addItem(null); // Add a null item for unselected state
        for (Hotel hotel : model.getAvailableHotels()) {
            view.getHotelComboBox().addItem(hotel.getName());
        }
    }

    private void populateRooms() {
        view.getRoomComboBox().removeAllItems();
        String hotelName = (String) view.getHotelComboBox().getSelectedItem();
        if (hotelName == null || hotelName.isEmpty()) {
            return;
        }
        int checkInDate = Integer.parseInt(view.getCheckInDateField().getText());
        int checkOutDate = Integer.parseInt(view.getCheckOutDateField().getText());
        for (Hotel hotel : model.getAvailableHotels()) {
            if (hotel.getName().equals(hotelName)) {
                for (Room room : model.getAvailableRooms(hotel, checkInDate, checkOutDate)) {
                    view.getRoomComboBox().addItem(room.getName());
                }
                break;
            }
        }
    }

    private void createReservation() {
        String guestName = view.getGuestNameField().getText();
        int checkInDate;
        int checkOutDate;
        try {
            checkInDate = Integer.parseInt(view.getCheckInDateField().getText());
            checkOutDate = Integer.parseInt(view.getCheckOutDateField().getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Check-in and check-out dates must be numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (checkInDate < 1 || checkOutDate < 2 || checkOutDate > 31 || checkInDate >= checkOutDate) {
            JOptionPane.showMessageDialog(view, "Invalid check-in or check-out dates.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String hotelName = (String) view.getHotelComboBox().getSelectedItem();
        String roomName = (String) view.getRoomComboBox().getSelectedItem();
        String discountCode = view.getDiscountCodeField().getText().trim();

        if (!guestName.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(view, "Guest name must contain only letters.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (hotelName == null || roomName == null) {
            JOptionPane.showMessageDialog(view, "Hotel and Room must be selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean isValidDiscountCode = discountCode.equals("I_WORK_HERE") || discountCode.equals("STAY4_GET1") || discountCode.equals("PAYDAY");

        for (Hotel hotel : model.getAvailableHotels()) {
            if (hotel.getName().equals(hotelName)) {
                for (Room room : model.getAvailableRooms(hotel, checkInDate, checkOutDate)) {
                    if (room.getName().equals(roomName)) {
                        boolean reservationCreated = model.createReservation(guestName, checkInDate, checkOutDate, room, hotelName, discountCode);
                        if (reservationCreated) {
                            if (discountCode.isEmpty()) {
                                JOptionPane.showMessageDialog(view, "Reservation created successfully. No discount code applied.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else if (isValidDiscountCode) {
                                JOptionPane.showMessageDialog(view, "Reservation created successfully with discount.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(view, "Reservation created successfully. Invalid discount code.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                            view.dispose();
                            mainMenuView.setVisible(true);
                            return;
                        } else {
                            JOptionPane.showMessageDialog(view, "Failed to create reservation due to invalid inputs.", "Error", JOptionPane.ERROR_MESSAGE);
                            view.dispose();
                            mainMenuView.setVisible(true);
                        }
                    }
                }
            }
        }
    }

    private void setupListeners() {
        DocumentListener validationListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateInput();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateInput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateInput();
            }
        };

        view.getGuestNameField().getDocument().addDocumentListener(validationListener);
        view.getCheckInDateField().getDocument().addDocumentListener(validationListener);
        view.getCheckOutDateField().getDocument().addDocumentListener(validationListener);
        view.getHotelComboBox().addActionListener(e -> validateInput());
        view.getRoomComboBox().addActionListener(e -> validateInput());
    }

    private void validateInput() {
        String guestName = view.getGuestNameField().getText();
        boolean isGuestNameValid = guestName.matches("[a-zA-Z]+");
        boolean areDatesValid = !view.getCheckInDateField().getText().isEmpty() && !view.getCheckOutDateField().getText().isEmpty();
        boolean isHotelSelected = view.getHotelComboBox().getSelectedItem() != null;
        boolean isRoomSelected = view.getRoomComboBox().getSelectedItem() != null;

        boolean isInputValid = isGuestNameValid && areDatesValid && isHotelSelected && isRoomSelected;
        view.getConfirmButton().setEnabled(isInputValid);
    }
}
