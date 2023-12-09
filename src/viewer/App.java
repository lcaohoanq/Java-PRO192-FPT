package viewer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.MenuEnum;
import tool.ConsoleColors;

public class App extends JPanel implements ActionListener {

    private GridLayout layout = new GridLayout(3, 3, 5, 5);
    private JButton btn;
    private boolean isActivated = false;
    private static int btnValue = 0; //
    private Controller controller; // Reference to the Controller
    
    public App(Controller controller) {
        this.controller = controller; // Set the reference to the Controller
        initApp();
        
    }

    private void initApp() {
        setLayout(layout);

        for (MenuEnum button : MenuEnum.values()) {
            btn = new JButton(button.getValue());
            btn.addActionListener(this);
            btn.setActionCommand(Integer.toString(button.getKey()));
            add(btn);
        }

    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton clickedButton = (JButton) e.getSource();
        btnValue = Integer.parseInt(clickedButton.getActionCommand()); //đây là case bỏ vô main

        isActivated = true;

        System.out.println(ConsoleColors.GREEN + "Action: " + btnValue + ConsoleColors.RESET);

        // Call the controller method with the button value
        controller.doManagement(btnValue);
    }

    public int getBtnValue() {
        if (btnValue != 0) {
            return btnValue;
        } else {
            return -1;
        }
    }
}
