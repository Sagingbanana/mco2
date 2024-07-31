import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateHotelController {
    private CreateHotelView view;
    private CreateHotelModel model;
    private MainMenuView mainMenuView;

    public CreateHotelController(CreateHotelView view, CreateHotelModel model, MainMenuView mainMenuView) {
        this.view = view;
        this.model = model;
        this.mainMenuView = mainMenuView;

        this.view.getCreateHotelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createHotel();
            }
        });

        this.view.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackToMainMenu();
            }
        });
    }

    private void createHotel() {
        String hotelName = view.getHotelNameField().getText().trim();

        // Check if the hotel name is valid
        if (!model.isHotelNameValid(hotelName)) {
            view.setMessage("<html>Hotel name cannot start with <br>a number or symbol. Try again.</html>");
            return; // Exit early if invalid
        }

        // Check if the hotel can be added
        if (model.addHotel(hotelName)) {
            view.setMessage("Hotel created successfully!");
            view.getCreateHotelButton().setEnabled(false);
        } else {
            view.setMessage("Hotel already exists. Try again.");
        }
    }

    private void goBackToMainMenu() {
        view.dispose();
        mainMenuView.setVisible(true);
        mainMenuView.setHotelCount(model.getNumberOfOperatingHotels());
    }
}
