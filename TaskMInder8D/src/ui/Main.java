package ui;

import java.util.Scanner;

public class Main {

    private Scanner sc;

    public Main() {
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.menu();
    }

    public void menu() {
        int option = 0;
        do {
            System.out.println("1. Add a task");
            System.out.println("2. Remove a task");
            System.out.println("3. modify a task");
            System.out.println("4. Show all tasks");
            System.out.println("0 Exit");
            option = sc.nextInt();
            sc.nextLine();
            switch(option) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 0:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while(option != 0);
    }

}
