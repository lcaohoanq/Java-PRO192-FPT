package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import constants.Message;
import models.Developer;
import models.Employee;
import models.TeamLeader;
import models.Tester;
import utils.ConsoleColors;

public class CompanyManagement {

    public static ArrayList<Employee> empList = new ArrayList<>();
    private HashMap<String, ArrayList<String>> hashPLInfo = new HashMap<>();
    private ArrayList<Developer> developerList = new ArrayList<>();
    private ArrayList<Tester> testerList = new ArrayList<>();

    private static String testerStr = null;
    private static String developerStr = null;
    private static String teamLeaderStr = null;

    public static ArrayList<String> appendJTextArea = new ArrayList<>();
    public static ArrayList<Tester> testerSearchedList = new ArrayList<>();
    public static ArrayList<Developer> devSearchedList = new ArrayList<>();
    public ArrayList<Employee> sortedList;
    public boolean isSorted = false;

    // reads from the file into the empList
    public ArrayList<Employee> getEmployeeFromFile(String path1, String path2) {
//        empList.clear();
        ArrayList<Employee> list = null;
        try {
            File fPLInfo = new File(path2);
            String fullPath = fPLInfo.getAbsolutePath();
            // System.out.println("Full Path: " + fullPath);
            FileInputStream filePLInfo = new FileInputStream(fullPath);
            BufferedReader myInputPLInfo = new BufferedReader(new InputStreamReader(filePLInfo));

            hashPLInfo = null;

            String thisLinePLInfo;

            while ((thisLinePLInfo = myInputPLInfo.readLine()) != null) {
                if (!thisLinePLInfo.trim().isEmpty()) {
                    String[] split = thisLinePLInfo.split(",");
                    if (hashPLInfo == null) {
                        hashPLInfo = new HashMap<>();
                    }
                    String key = split[0].trim();
                    ArrayList<String> plInfo = new ArrayList<>();
                    for (int i = 1; i < split.length; i++) {
                        plInfo.add(split[i].trim());
                    }
                    hashPLInfo.put(key, plInfo);
                }
            }
            myInputPLInfo.close();
            // for (String k : hashPLInfo.keySet()) {
            // System.out.println(k + ":" + hashPLInfo.get(k));
            // }

            // Đọc Employee
            File fEmp = new File(path1);
            fullPath = fEmp.getAbsolutePath();
            // System.out.println("Full Path: " + fullPath);
            FileInputStream fileEmp = new FileInputStream(fullPath);
            BufferedReader myInputEmp = new BufferedReader(new InputStreamReader(fileEmp));

            String thisLine;
            // đọc cho đến hết file
            while ((thisLine = myInputEmp.readLine()) != null) {

                Employee employee = null;

                if (!thisLine.trim().isEmpty()) {
                    String[] split = thisLine.split(",");
                    // Dev : STT, ID, name, team, expyear, baseSal
                    // TeamLeader: STT, ID, name, team, expyear, "L" , bonus_rate , baseSal
                    // Tester : STT, ID, name, bonusRate, type, baseSal

                    if (split.length == 8) {
                        // split[0] = STT
                        String id = split[1].trim();
                        String name = split[2].trim();
                        String team = split[3].trim();
                        Integer expYear = Integer.parseInt(split[4].trim());
                        // split[5] = "L"
                        Double bonus_rate = Double.parseDouble(split[6].trim());
                        Integer baseSal = Integer.parseInt(split[7].trim());

                        ArrayList<String> programmingLanguages = hashPLInfo.get(id);

                        employee = new TeamLeader(id, name, baseSal, team, programmingLanguages, expYear, bonus_rate);
                        empList.add(employee);

                        developerList.add((TeamLeader) employee); //

                        // System.out.println(employee.toString());
                    } else if (split[1].trim().startsWith("D")) {
                        // split[0] = STT
                        String id = split[1].trim();
                        String name = split[2].trim();
                        String team = split[3].trim();
                        Integer expYear = Integer.parseInt(split[4].trim());
                        Integer baseSal = Integer.parseInt(split[5].trim());

                        ArrayList<String> programmingLanguages = hashPLInfo.get(id);

                        employee = new Developer(id, name, baseSal, team, programmingLanguages, expYear);
                        empList.add(employee);

                        developerList.add((Developer) employee); //

                        // System.out.println(employee.toString());
                    } else {
                        // split[0] = STT
                        String id = split[1].trim();
                        String name = split[2].trim();
                        Double bonusRate = Double.parseDouble(split[3].trim());
                        String type = split[4].trim();
                        Integer baseSal = Integer.parseInt(split[5].trim());

                        employee = new Tester(id, name, baseSal, bonusRate, type);
                        empList.add(employee);

                        testerList.add((Tester) employee); //

                        // System.out.println(employee.toString());
                    }

                }
            }
            // printEmpList();
            for (Employee item : empList) {
                System.out.println(item.toString());
            }
            Message.createSuccessMsg("Get employee from file successfully");
            System.out.println("Read and print file successfully!");
        } catch (Exception ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }
        return list;

    }

    //
    public int searchEmployeeIndexById(String keyId) {
        for (int i = 0; i <= empList.size() - 1; i++) {
            if (empList.get(i).getEmpID().equals(keyId)) {
                return i;
            }
        }
        return -1;
    }

    // hàm search, nhận vào id return ra object, trước khi có hàm này, ta cần làm
    // hàm nhận vào id tìm vị trí
    public <T extends Employee> T searchEmployeeById(String keyId, Class<T> currentList) {
        int pos = this.searchEmployeeIndexById(keyId);
        return pos == -1 ? null : currentList.cast(empList.get(pos));
    }

    // list of programmers who are proficient in the input pl programmingLanguage.
    public void getDeveloperByProgrammingLanguage(String pl) {
//        devSearchedList.clear();
        if (developerList.isEmpty()) {
            Message.createReadFileFirstMsg();
            System.out.println("Nothing to find, please read file first!");
        } else {
            devSearchedList = new ArrayList<>(developerList);
            // String plName = sc.nextLine().toLowerCase();
            String plName = Message.createInputDialog("Input Programming Language: ").toLowerCase();

            // tạo 1 hashmap lưu programming language với key = Dev id
            // có 2 hướng:
            // 1 là băm lại từ đầu, dài, xấu
            // 2 ta set 1 global hash map luôn xài cho sướng (để ta móc dữ liệu từ HashMap
            // về, không modifier)
            // Dùng iterator để duyệt qua từng cái key của HashMap -> keySet()
            Iterator<String> iter = hashPLInfo.keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next(); // trả về phần tử tiếp theo, xét tiếp

                // nếu như tìm thấy được cái plName nhập ở Scanner trên = value của HashMap (tại
                // id nào đó)
                // thì ta không làm gì
                // còn không thì ta sẽ xoá đi employee tại vị trí key đó
                if (!hashPLInfo.get(key).toString().toLowerCase().contains(plName)) {
                    Developer dev = this.searchEmployeeById(key, Developer.class); // lí do ta tạo hàm ở trên, nhận vào
                                                                                   // key và trả ra một
                    devSearchedList.remove(dev);
                }
            }
            // in ra để test
            if (devSearchedList.isEmpty()) {
                Message.createCustomMsg(plName + " is not found in the list of developers");
                System.out.printf("Not found %s\n", plName);
            }
            Message.createSuccessMsg("Get developer by programming language");
        }

    }

    // list of testers whose total salary is greater than the value of the parameter
    public void getTestersHaveSalaryGreaterThan(){
        // reset lại dữ liệu khi bấm nút
//        testerSearchedList.clear();
        if (testerList.isEmpty()) {
            Message.createReadFileFirstMsg();
            System.out.println("Nothing to find, please read file first!");
        } else {
            // nhận vào lương
            Integer value = Integer.parseInt(Message.createInputDialog("Input Salary: "));
            // tìm trong testerList có thằng nào có lương > value không thì mới add vào
            // tList
            for (Tester item : testerList) {
                // theo đề ta nhận giá trị của baseSalary
                if (item.getBaseSal() > value) {
                    testerSearchedList.add(item);
                    String str = String.format("%s_%s_%d", item.getEmpID(), item.getEmpName(),
                            item.getBaseSal());
                    System.out.println(str);
                }
            }
            Message.createSuccessMsg("Get testers have salary greater than");
        }

    }

    public void getEmployeeWithHighestSalary(){
        if (empList.isEmpty()) {
            Message.createReadFileFirstMsg();
            System.out.println("Nothing to find, please read file first!");
        } else {
            // nếu chưa sort thì mới sort, còn sort rồi thì thôi
            if (!isSorted) {
                sortedList = sorted();
            }
            // ta đã có sort giảm dần, lương cao nhất là thằng đầu tiên
            // System.out.println(sortedList.get(0).toString());
            Message.createResultMessage(sortedList.get(0).toString());
        }
    }

    // get the team leader of the group with the most programmers
    public void getLeaderWithMostEmployees(){

        if (developerList.isEmpty()) {
            Message.createReadFileFirstMsg();
            System.out.println("Nothing to find, please read file first!");
        } else {
            // tìm thằng leader có nhiều thành viên trong team của nó nhất
            // ta cần phải tìm team xuất hiện nhiều nhất là team nào (Run, Fly,Walk,
            // Jump...)
            // trước hết phải tìm tần suất xuất hiện của các team
            // frequency

            // móc ra danh sách tất cả dev
            ArrayList<Developer> dList = new ArrayList<>(developerList);

            // tạo 1 HashMap có key=tên team (String) | value=tần xuất xuất hiện của team
            // đó(Integer)
            HashMap<String, Integer> frequencyTeamName = new HashMap<>();

            // tìm trong danh sách dev
            for (Developer item : dList) {
                String teamName = item.getTeamName();
                // put(key,value) -> key: teamName, value:

                // hàm getOrDefault(key, default value) -> trả ra một số nguyên
                // khi mà frequencyTeamName trỏ tới nếu key có ->
                // ban đầu frequencyTeamName trống
                // ta sẽ cập nhật bằng put (key,value)
                // chạy qua giá trị đầu tiên thì frequencyTeamName không có phần tử nào trong
                // HashMap
                // nên nó sẽ nhận giá trị 0 -> +1 -> xuất hiện 1 lần
                // tiếp theo nếu gặp một giá trị mới nó sẽ timf trong HashMap, vẫn chưa có -> +1
                // (xuất hiện 1 lần)
                // tiếp theo nếu gặp một giá trị có rồi :) thì sẽ trả về số lần = 1 + 1 là 2
                // frequencyTeamName.put(teamName, frequencyTeamName.getOrDefault(teamName, 0) +
                // 1);
                int updatedFrequency = frequencyTeamName.getOrDefault(teamName, 0) + 1;
                frequencyTeamName.put(teamName, updatedFrequency);
            }

            String teamNameWithFrequency = "";
            int maxTeamNameFrequency = 0;

            for (Map.Entry<String, Integer> item : frequencyTeamName.entrySet()) {
                // System.out.println("Team Name: " + item.getKey() + " " + "Frequency: " +
                // item.getValue());

                String teamName = item.getKey();
                int frequency = item.getValue();

                if (frequency > maxTeamNameFrequency) {
                    teamNameWithFrequency = teamName;
                    maxTeamNameFrequency = frequency;
                }

            }

            // System.out.println("TeamName with heighest frequency:
            // "+teamNameWithFrequency);
            // System.out.println("Highest Frequency: "+maxTeamNameFrequency);
            for (Developer item : dList) {
                if (item.getTeamName().equals(teamNameWithFrequency) && item instanceof TeamLeader) {
                    String str = String.format("%s_%s_%d_%s_%s_%d", item.getEmpID(), item.getEmpName(),
                            item.getBaseSal(), item.getTeamName(), item.getProgrammingLanguages(), item.getExpYear());
                    // System.out.println(str);
                    Message.createResultMessage(str);
                }
            }
            Message.createSuccessMsg("Get leader with most employees");
        }

    } // Sort Employees as descending salary

    public ArrayList<Employee> sorted(){
        // clone new sortedList from empList
        ArrayList<Employee> sortedList = new ArrayList<>(empList);

        if (sortedList.isEmpty()) {
            Message.createReadFileFirstMsg();
            return null;
        } else {
            Comparator orderBySalary = new Comparator<Employee>() {
                @Override
                public int compare(Employee eThis, Employee eThat) {

                    if (eThis.getSalary() == eThat.getSalary()) {
                        // nếu như D A thì swap (D>A)
                        char n1 = eThis.getEmpName().charAt(eThis.getEmpName().lastIndexOf(" ") + 1);
                        char n2 = eThat.getEmpName().charAt(eThat.getEmpName().lastIndexOf(" ") + 1);

                        return n1 - n2;
                    } else {
                        if (eThis.getSalary() < eThat.getSalary()) {
                            return 1;
                        }
                        return -1;
                    }

                }
            };
            Collections.sort(sortedList, orderBySalary);
            // ta lợi dụng cái này để reuse hàm ở trên
            isSorted = true;
            return sortedList;
        }
    }

    public static long totalSalary(Employee e) {
        return Math.round(e.getSalary());
    }

    // print empList
    public void printEmpList() {
        appendJTextArea.clear();
        if (empList.isEmpty()) {
            Message.createReadFileFirstMsg();
            System.out.println("Nothing to print, please read file first!");
        }
        System.out.println(
                ConsoleColors.GREEN + "All of salary is total salary and cast to integer!!" + ConsoleColors.RESET);

        for (Employee item : empList) {
            if (item instanceof Tester) {
                Tester tester = (Tester) item;
                // tester.showInfor();

                testerStr = String.format("%3s_%-25s_%-10d_%s_%.2f", tester.getEmpID(), tester.getEmpName(),
                        totalSalary(tester), tester.getType(), tester.getBonusRate()) + "\n";
                System.out.println(testerStr);
                appendJTextArea.add(testerStr);
            } else if (item instanceof Developer) {
                Developer developer = (Developer) item;
                // developer.showInfor();
                developerStr = String.format("%3s_%-25s_%-10d_%s_%d", developer.getEmpID(),
                        developer.getEmpName(), totalSalary(developer), developer.getTeamName(),
                        developer.getExpYear()) + "\n";
                appendJTextArea.add(developerStr);
                System.out.println(developerStr);

            } else {
                TeamLeader teamLeader = (TeamLeader) item;
                // teamLeader.showInfor();

                teamLeaderStr = String.format("%3s_%-25s_%-10d_%s_%.2f", teamLeader.getEmpID(),
                        teamLeader.getEmpName(), totalSalary(teamLeader), teamLeader.getTeamName(),
                        teamLeader.getBonusRate()) + "\n";
                appendJTextArea.add(teamLeaderStr);
                System.out.println(teamLeaderStr);
            }
            // System.out.println(item.toString());
        }
    }

    // write emplist
    public boolean writeFile(String path){
        try {
            File f = new File(path);
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(f));

            if (path.equals("src\\output\\Req2.txt")) {
                for (Developer item : developerList) {
                    if (item.getProgrammingLanguages().contains("C++")) {
                        writer.write(item.toString());
                        writer.write("\n");
                    }
                }
                // Reg3.txt
            } else {
                for (Employee item : empList) {
                    if (totalSalary(item) > 4700000) {
                        writer.write(item.toString());
                        writer.write("\n");
                    }
                }
            }

            writer.flush();
            return true;
        } catch (Exception e) {
            System.out.println("Error while writing file: " + e);
            return false;
        }
    }

    public void exitProgram() {
        System.out.println("Good bye");
        Message.createConfirmationMsg("Do you want to continue?");
    }
}
