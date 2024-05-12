package viewer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controllers.AppController;
import enums.MenuEnum;
import styles.BorderHandler;
import styles.ColorHandler;
import styles.FontHandler;
import styles.SizeHandler;
import utils.ConsoleColors;

public class App extends JFrame implements ActionListener {

    // tạo 2 panel: bên phải là JTextArea, bên trái là JPanel chứa các button
    // bọc thêm 1 JLabel nữa cho bên phải nhìn cho đẹp :))))
    private JPanel panel = new JPanel(new BorderLayout());
    private JPanel jPanel_JLabel = new JPanel();
    private JPanel jPanel_Button = new JPanel(new GridLayout(9, 1, 0, 5));
    private JPanel jPanel_LeftPanel_TextArea = new JPanel(new BorderLayout());

    public static JTextArea jTextArea_Viewer = new JTextArea(30, 30);
    public JScrollPane jScrollPane = new JScrollPane(jTextArea_Viewer);
    // set panel tổng là Flow Layout Hoặc BorderLayout (button: phải, viewer: trái)

    private JLabel jLabel = new JLabel(); // Company Employee Management Program
    private JPanel jPanel_RightPanel = new JPanel(new BorderLayout()); // Company Employee Management Program

    // private GridLayout layout = new GridLayout(3, 3, 5, 5);
    private JButton btn;
    private static int btnValue = 0; //
//    private Controller controller; // Reference to the Controller

    public App() {
//        this.controller = new Controller(); // Set the reference to the Controller
        this.setSize(SizeHandler.WINDOW_WIDTH, SizeHandler.WINDOW_HEIGHT);
        this.setTitle("Company Employee Management Program");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        // this.pack();
        initApp();

    }

    private void initApp() {
        initTitleZone();
        initButtonZone();
        initViewerZone();
        initContainer();
        this.add(panel);
    }

    private void initTitleZone() {
        jLabel.setText("Company Employee Management Program");
        jLabel.setFont(FontHandler.TITLE_FONT);
        jLabel.setBorder(BorderHandler.BORDER_TITLE);
        jLabel.setBackground(ColorHandler.PRIMARY_COLOR);
        jLabel.setForeground(ColorHandler.TEXT_COLOR);
        jPanel_JLabel.setBackground(ColorHandler.PRIMARY_COLOR);
        // jPanel_JLabel.setForeground(ColorHandler.TEXT_COLOR);
        jPanel_JLabel.add(jLabel, BorderLayout.NORTH);
    }

    private void initButtonZone() {
        jPanel_RightPanel.setBackground(ColorHandler.PRIMARY_COLOR);
        jPanel_RightPanel.setForeground(ColorHandler.TEXT_COLOR);
        jPanel_Button.setBackground(ColorHandler.PRIMARY_COLOR);
        for (MenuEnum button : MenuEnum.values()) {
            btn = new JButton(button.getValue());
            btn.setBackground(ColorHandler.TEXT_COLOR);
            btn.setForeground(ColorHandler.PRIMARY_COLOR);
            btn.setFont(FontHandler.BUTTON_FONT);
            btn.addActionListener(new AppController(this));
            btn.setActionCommand(Integer.toString(button.getKey()));
            jPanel_Button.add(btn);
        }
        jPanel_RightPanel.setBorder(BorderHandler.BORDER);
        jPanel_RightPanel.add(jPanel_JLabel, BorderLayout.NORTH);
        jPanel_RightPanel.add(jPanel_Button, BorderLayout.CENTER);
    }

    private void initViewerZone() {
        jTextArea_Viewer.setLineWrap(true);
        jTextArea_Viewer.setEditable(false);
        jTextArea_Viewer.setFont(FontHandler.TEXT_FONT);
        jTextArea_Viewer.setBackground(ColorHandler.PRIMARY_COLOR);
        jTextArea_Viewer.setForeground(ColorHandler.TEXT_COLOR);
        jTextArea_Viewer.setBorder(null);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        jPanel_LeftPanel_TextArea.add(jScrollPane, BorderLayout.CENTER);
    }

    private void initContainer() {
        // panelText.setLayout(new BorderLayout());
        // // panelText.add(panelText, BorderLayout.CENTER);
        // panelText.add(panelButton, BorderLayout.SOUTH);
        panel.setBackground(ColorHandler.PRIMARY_COLOR);
        panel.add(jPanel_RightPanel, BorderLayout.EAST);
        panel.add(jPanel_LeftPanel_TextArea, BorderLayout.WEST);
        // add(panelButton, BorderLayout.WEST);
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // consoleArea.setText(null);
        JButton clickedButton = (JButton) e.getSource();
        btnValue = Integer.parseInt(clickedButton.getActionCommand()); // đây là case bỏ vô main

        System.out.println(ConsoleColors.GREEN + "Action: " + btnValue + ConsoleColors.RESET);

        // Call the controller method with the button value
//        controller.doManagement(btnValue);
    }

    public static int getBtnValue() {
        if (btnValue != 0) {
            return btnValue;
        } else {
            return -1;
        }
    }
}
