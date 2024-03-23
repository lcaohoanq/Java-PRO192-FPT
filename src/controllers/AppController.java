package controllers;

import constants.Message;
import constants.Path;
import viewer.App;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppController implements ActionListener {
    private App app;
    CompanyManagement cm = new CompanyManagement();
    public AppController(App app){
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonText = e.getActionCommand();
        switch (buttonText) {
            case "1":
                cm.getEmployeeFromFile(Path.fileEmp, Path.filePL);
                App.jTextArea_Viewer.setText(cm.empList.toString());
                break;
            case "2":
                cm.getDeveloperByProgrammingLanguage(Path.filePL);
                App.jTextArea_Viewer.setText(cm.devSearchedList.toString());
                break;
            case "3":
                cm.getTestersHaveSalaryGreaterThan();
                App.jTextArea_Viewer.setText(cm.testerSearchedList.toString());
                break;
            case "4":
                cm.getEmployeeWithHighestSalary();
                break;
            case "5":
                cm.getLeaderWithMostEmployees();
                break;
            case "6":
                cm.sorted();
                Message.createSuccessMsg("Sorted");
                break;
            case "7":
                cm.printEmpList();
                App.jTextArea_Viewer.setText(cm.appendJTextArea.toString());
                break;
            case "8":
                cm.writeFile(Path.fileWrite1);
                cm.writeFile(Path.fileWrite2);
                Message.createSuccessMsg("Wrote file at src//resources//");
                break;
            case "9":
                cm.exitProgram();
        }
    }
}
