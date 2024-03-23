package models;

import java.util.ArrayList;

import controllers.CompanyManagement;

public class Developer extends Employee {

    protected String teamName;
    protected ArrayList<String> programmingLanguages;
    protected int expYear;

    public Developer(String empID, String empName, int baseSal, String teamName, ArrayList<String> programmingLanguages,
            int expYear) {
        super(empID, empName, baseSal);
        this.teamName = teamName;
        this.programmingLanguages = programmingLanguages;
        this.expYear = expYear;
    }

    public String getTeamName() {
        return teamName;
    }

    public ArrayList<String> getProgrammingLanguages() {
        return programmingLanguages;
    }

    public int getExpYear() {
        return expYear;
    }

    @Override
    public double getSalary() {
        int yearExp = this.getExpYear();
        if (yearExp >= 5) {
            return baseSal + yearExp * 2000000;
        } else if (yearExp >= 3 && yearExp < 5) {
            return baseSal + yearExp * 1000000;
        } else {
            return baseSal;
        }
    }

    @Override
    public String toString() {
        return super.toString() + "_" + teamName + "_" + programmingLanguages.toString() + "_" + expYear;
    }

    @Override
    public void showInfor() {
        String str = String.format("%3s_%-25s_%-10d_%s_%s_%d", this.getEmpID(), this.getEmpName(),
                CompanyManagement.totalSalary(this), this.getTeamName(), this.getProgrammingLanguages(),
                this.getExpYear());
        System.out.println(str);
    }

}
