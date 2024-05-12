package models;

import controllers.CompanyManagement;

public class Tester extends Employee {

    protected double bonusRate;
    protected String type; // AM-MT

    public Tester(String empID, String empName, int baseSal, double bonusRate, String type) {
        super(empID, empName, baseSal);
        this.bonusRate = bonusRate;
        this.type = type;
    }

    public double getBonusRate() {
        return bonusRate;
    }

    public String getType() {
        return type;
    }

    @Override
    public double getSalary() {
        return baseSal + bonusRate * baseSal;
    }

    @Override
    public void showInfor() {
        // dùng this ám chỉ current object, object hiện tại
        // có thể dùng để chấm tới totalSalary trong CompanyManagement vì nó require
        // tham số là một object
        String str = String.format("%3s_%-25s_%-10d_%s_%.2f", this.getEmpID(), this.getEmpName(),
                CompanyManagement.totalSalary(this), this.getType(), this.getBonusRate());
        System.out.println(str);
    }

    @Override
    public String toString() {
        return super.toString() + "_" + bonusRate + "_" + type + "\n";
    }

}
