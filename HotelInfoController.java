import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelInfoController {
    private HotelInfoView view;
    private HotelInfoModel model;
    private SelectHotelController selectHotelController;
    private SelectHotelView selectHotelView;

    public HotelInfoController(HotelInfoView view, HotelInfoModel model, SelectHotelController selectHotelController, SelectHotelView selectHotelView) {
        this.view = view;
        this.model = model;
        this.selectHotelController = selectHotelController;
        this.selectHotelView = selectHotelView;

        // Add action listeners to buttons
        this.view.getHighLevelInfoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewHighLevelInfo();
            }
        });

        this.view.getAvailableRoomsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAvailableRooms();
            }
        });

        this.view.getBookedRoomsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewBookedRooms();
            }
        });

        this.view.getRoomInfoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewRoomInfo();
            }
        });

        this.view.getReservationInfoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewReservationInfo();
            }
        });

        // Add action listeners for bottom buttons
        this.view.getBackToSelectionButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backToSelectionPage();
            }
        });

        this.view.getBackToMainMenuButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backToMainMenu();
            }
        });
    }

    private void viewHighLevelInfo() {
        // Implement logic for viewing high-level information
        JOptionPane.showMessageDialog(view, "High-Level Information view is not implemented yet.", "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private void viewAvailableRooms() {
        // Implement logic for viewing available rooms
        JOptionPane.showMessageDialog(view, "Available Rooms Per Date view is not implemented yet.", "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private void viewBookedRooms() {
        // Implement logic for viewing booked rooms
        JOptionPane.showMessageDialog(view, "Booked Rooms Per Date view is not implemented yet.", "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private void viewRoomInfo() {
        // Implement logic for viewing room information
        JOptionPane.showMessageDialog(view, "Room Information view is not implemented yet.", "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private void viewReservationInfo() {
        // Implement logic for viewing reservation information
        JOptionPane.showMessageDialog(view, "Reservation Information view is not implemented yet.", "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private void backToSelectionPage() {
        // Logic to return to the selection page
        view.dispose(); // Close the current view
        selectHotelView.setVisible(true); // Show the selection view again
    }

    private void backToMainMenu() {
        // Logic to return to the main menu
        view.dispose(); // Close the current view
        selectHotelController.goBackToMainMenu(); // Show the main menu
    }
}
