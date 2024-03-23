package controllers;

import constants.Message;
import constants.Path;
import models.Menu;
import utils.ConsoleColors;
import viewer.App;

public class Controller {

    int choice = 0;

    Menu menu = new Menu("Company Employee Management Program");

    CompanyManagement cm = new CompanyManagement();

    public Controller() {
        initMenu();
        System.out.println(ConsoleColors.YELLOW
                + "Note: \nAll employee's salary based on the actual salary after multiply with the bonus and casted into integer!!!"
                + ConsoleColors.RESET);
        doManagement(App.getBtnValue()); // nhận vào tham số từ GUI và chạy chức năng
        // tương ứng
    }

    private void initMenu() {
        menu.addNewOption("Read all Employees and print to screen");
        menu.addNewOption("Show staff proficient in a Programming Language");
        menu.addNewOption("Show Tester has a height salary");
        menu.addNewOption("Show Employee’s higest salary");
        menu.addNewOption("Show Leader of the Team has most Employees");
        menu.addNewOption("Sort Employees as descending salary");
        menu.addNewOption("Show All Employees (optional)"); // mình muốn check lại dữ liệu mỗi khi được chỉnh sửa
        menu.addNewOption("Write file");
        menu.addNewOption("Exit");
    }

    public void doManagement(int btnValue) {
        try {
            do {
                menu.print();
                // choice = menu.getChoice();
                switch (btnValue) {
                    case 1:
                        // App.consoleArea.setText(null);
                        cm.getEmployeeFromFile(Path.fileEmp, Path.filePL);

                        App.jTextArea_Viewer.setText(cm.empList.toString());
                        break;
                    case 2:
                        cm.getDeveloperByProgrammingLanguage(Path.filePL);
                        App.jTextArea_Viewer.setText(cm.devSearchedList.toString());
                        break;
                    case 3:
                        cm.getTestersHaveSalaryGreaterThan();
                        App.jTextArea_Viewer.setText(cm.testerSearchedList.toString());
                        break;
                    case 4:
                        cm.getEmployeeWithHighestSalary();
                        break;
                    case 5:
                        cm.getLeaderWithMostEmployees();
                        break;
                    case 6:
                        cm.sorted();
                        Message.createSuccessMsg("Sorted");
                        break;
                    case 7:
                        cm.printEmpList();
                        App.jTextArea_Viewer.setText(cm.appendJTextArea.toString());
                        break;
                    case 8:
                        cm.writeFile(Path.fileWrite1);
                        cm.writeFile(Path.fileWrite2);
                        Message.createSuccessMsg("Wrote file at src//resources//");
                        break;
                    case 9: {
                        cm.exitProgram();
                    }
                }
            } while (choice > 0 && choice < menu.getSize());
        } catch (Exception e) {
            System.out.println("Loi Controller");
        }
    }

}
