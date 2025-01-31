import controller.AuthController;
import model.User;
import view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        AuthController controller = new AuthController(user);
        controller.start();

    }
}