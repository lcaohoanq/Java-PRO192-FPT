package viewer;

import javax.swing.JFrame;

public class GUI extends JFrame {

    private Controller controller;
    
    public GUI() {
        controller = new Controller();
        initGUI();
    }

    private void initGUI() {
        setTitle("Company Employee Management Program");
        setSize(800, 800);

        
        add(new App(controller)); //app sẽ gọi Controller (Main)

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }

}
