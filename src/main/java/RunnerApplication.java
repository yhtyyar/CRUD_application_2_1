import view.DefaultView;
import view.FirstView;

public class RunnerApplication {
    public static void main(String[] args) {

        DefaultView defaultView = FirstView.getView();
        defaultView.start();

    }
}
