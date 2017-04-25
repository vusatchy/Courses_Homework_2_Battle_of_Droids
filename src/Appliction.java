import conroller.Controller;


/**
 * Created by white on 22.04.2017.
 */
public class Appliction  {
    public static void main(String[] args) {
        //JFrame frame=new JFrame("Appliction");
       // frame.setContentPane( new MyGUI());
        Controller controller = new Controller();
        while (true) {
            controller.update();
        }




    }
}
