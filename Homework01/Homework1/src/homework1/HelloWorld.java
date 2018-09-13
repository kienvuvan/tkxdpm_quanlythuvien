/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework1;

import java.util.Scanner;

/**
 *
 * @author Linh
 */
public class HelloWorld {

    public static void main(String[] args) {
        String name;
        boolean check = false;
        int day, month, year;
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your name");
        name = scanner.nextLine();
        
        do {
            System.out.println("Please type your day of birthday:");
            day = scanner.nextInt();
            System.out.println("Please type your month of birthday:");
            month = scanner.nextInt();
            System.out.println("Please type your year of birthday:");
            year = scanner.nextInt();
            //System.out.println("" + day + "  " + month + " " + year);
            
            if(new DateUtils().checkDate(day, month,year)){
                check = false;
            }else{
                check = true;
                System.out.println("\nYour Date of Birth is wrong. Please check again!!");
            }
        }while(check);
        //Say hello to that person and display his/her age
        
        System.out.println("Hello "+name);
        System.out.println("Your age is "+DateUtils.calculateAge(year) );
        
    }
}
