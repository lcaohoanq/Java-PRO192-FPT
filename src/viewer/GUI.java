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
    
    JButton btn;
    boolean isActivated = false;
    
    // Enum to represent buttons
    enum ButtonAction {
        READ_EMPLOYEES(1, "Read all Employees and print to screen"),
        SHOW_PROGRAMMERS(2, "Show staff proficient in a Programming Language"),
        SHOW_TESTER_SALARY(3, "Show Tester has a higher salary than"),
        SHOW_HIGHEST_SALARY(4, "Show Employee’s highest salary"),
        SHOW_LEADER(5, "Show Leader of the Team has most Employees"),
        SORT_SALARY(6, "Sort Employees salary (DESC)"),
        SHOW_ALL_EMPLOYEES(7, "Show All Employees"),
        WRITE_FILE(8, "Write File"),
        EXIT(9, "Exit");

        private final int key;
        private final String value;

        ButtonAction(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }

    public GUI() {
        initGUI();
    }

    private void initGUI() {
        setTitle("Company Employee Management Program");
        setSize(800, 800);

        panel.setLayout(layout);

        for (ButtonAction button : ButtonAction.values()) {
            btn = new JButton(button.getValue());
            btn.addActionListener(this);
            btn.setActionCommand(Integer.toString(button.getKey()));
            panel.add(btn);
        }

        add(panel);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton clickedButton = (JButton) e.getSource();
        int btnValue = Integer.parseInt(clickedButton.getActionCommand());
        
        isActivated = true;

        System.out.println("Action: " + btnValue);
    }

}
