/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework1;

/**
 *
 * @author Linh
 */
public class NumberUtil {
    public static final int TYPE_NONE = 0;   
    public static final int PRIME_NUMBER = 1;
    public static final int COMPOSITE_NUMBER = 2;
    public static final int PERFECT_NUMBER=3;
    
     
    
    /* check number is prime or composite
    *return 0 if number is not a prime number and not a composite number.
    *return 1 if number is prime number.
    *return 2 if number is composite number.
    *return 3 if nummber is perfect number.
    */
    public static int checkPrimeNumber(int number) {
        if (number < 2) {
            return TYPE_NONE;
        }
        if (isPrime(number)) {
            return PRIME_NUMBER;
        }
        return COMPOSITE_NUMBER;
    }
    /* check number is  prime or not prime; 
    */
    private static boolean isPrime(int number) {
        if (number == 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
    //check number is a square number or not
    public static boolean isSquareNumber(int n){
        if(n==(int)Math.sqrt(n)*(int)Math.sqrt(n)) return true;
        return false;
    }
    
    //check 1 number is a perfectNumber or not
    public static boolean isPerfectNumber(int n){
        if(n<5) return false;
        else{
            int sum=0;
            for(int i=1;i<n;i++){
                if(n%i==0){
                    sum+=i;
                }
            }
            if(sum==n){
                return true;
            }else return false;
        }
    }
}
