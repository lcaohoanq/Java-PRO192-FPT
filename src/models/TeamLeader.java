package models;

import java.util.ArrayList;

import controllers.CompanyManagement;

public class TeamLeader extends Developer {
    // only one TeamLeader in Dev team

    private double bonus_rate;

    public TeamLeader(String empID, String empName, int baseSal, String teamName,
            ArrayList<String> programmingLanguages, int expYear, double bonus_rate) {
        super(empID, empName, baseSal, teamName, programmingLanguages, expYear);
        this.bonus_rate = bonus_rate;
    }

    public double getBonusRate() {
        return bonus_rate;
    }

    @Override
    public double getSalary() {
        double devSalary = super.getSalary();
        // từ this ám chỉ đến obj hiện tại gọi method getSalary
        if (this instanceof TeamLeader) {
            return devSalary + bonus_rate * devSalary;
        } else {
            return devSalary;
        }
    }

    @Override
    public void showInfor() {
        String str = String.format("%3s_%-25s_%-10d_%s_%s_%d", this.getEmpID(), this.getEmpName(),
                CompanyManagement.totalSalary(this), this.getTeamName(), this.getProgrammingLanguages(),
                this.getExpYear());
        System.out.println(str);
    }

    @Override
    public String toString() {
        return super.toString() + "_" + bonus_rate;
    }

}
