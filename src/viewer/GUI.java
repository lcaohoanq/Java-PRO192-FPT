package viewer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame implements ActionListener {

    JPanel panel = new JPanel();

    GridLayout layout = new GridLayout(3, 3, 5, 5);

//    JButton btn1 = new JButton("Read all Employees and print to screen");
//    JButton btn2 = new JButton("Show staff proficient in a Programming Language");
//    JButton btn3 = new JButton("Show Tester has a height salary");
//    JButton btn4 = new JButton("Show Employee’s higest salary");
//    JButton btn5 = new JButton("Show Leader of the Team has most Employees");
//    JButton btn6 = new JButton("Sort Employees as descending salary");
//    JButton btn7 = new JButton("Show All Employees (optional)");
//    JButton btn8 = new JButton("Write File");
//    JButton btn9 = new JButton("Exit");
    
    JButton btn = new JButton();
    boolean isActivated = false;
    int index = -1;

    String[] btnName = {"Read all Employees and print to screen",
        "Show staff proficient in a Programming Language",
        "Show Tester has a heigher salary than",
        "Show Employee’s higest salary",
        "Show Leader of the Team has most Employees",
        "Sort Employees salary (DESC)",
        "Show All Employees",
        "Write File",
        "Exit"
    };

    public GUI() {
        initGUI();
    }

    private void initGUI() {
        setTitle("Company Employee Management Program");
        setSize(800, 800);

        panel.setLayout(layout);

        for (int i = 0; i <= 8; i++) {
            btn = new JButton(btnName[i]);
            btn.addActionListener(this);
            panel.add(btn);

        }

        add(panel);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();

        //Event.Queue
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton clickedButton = (JButton) e.getSource();

        isActivated = true;

        System.out.println("Action: " + clickedButton.getText());
    }

}
