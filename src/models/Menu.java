package models;

import java.util.ArrayList;

import utils.Inputter;

public class Menu {

    // public static int getChoice(Object[] options) {
    // for (int i = 0; i < options.length; i++) {
    // System.out.println((i + 1) + ". " + options[i]);
    // }
    // System.out.print(ConsoleColors.RED+"Your options from 1 - " + options.length
    // + ": " + ConsoleColors.RESET);
    // Scanner sc = new Scanner(System.in);
    // return Integer.parseInt(sc.nextLine());
    // }
    ArrayList<String> optionList = new ArrayList<>();

    public String title;

    // constructor
    public Menu(String title) {
        this.title = title;
    }

    // method
    public void addNewOption(String nOptional) {
        optionList.add(nOptional);
    }

    // in ra menu
    public void print() {
        int count = 1;
        System.out.println("-------------" + title + "--------------");
        for (String item : optionList) {
            System.out.println(count + ". " + item);
            count++;
        }
    }

    // lấy ra lựa chọn
    public int getChoice() {
        int choice = Inputter.getAnInteger("Input your choice: ", "Required between 1 and " + optionList.size(), 1,
                optionList.size());
        return choice;
    }

    public int getSize() {
        return this.optionList.size();
    }

}
