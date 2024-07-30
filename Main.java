public class Main {
    public static void main(String[] args) {
       {
            HotelReservationSystem model = new HotelReservationSystem();
            MainMenuView view = new MainMenuView();
            MainMenuController controller = new MainMenuController(view, model);
            view.setVisible(true);
        };
    }
}