/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linh
 */
public class DateUtils {
    //Method check day,month,year
    
    public static boolean checkDate(int day, int month, int year){
        try {
            String stringDay="";
            String stringMonth="";
            String stringYear="";
            if(day<10){
                stringDay="0"+day;
            }else stringDay=day+"";
            
            if(month<10){
                stringMonth="0"+month;
            }else stringMonth=""+month;
            
            if(year<10){
                stringYear="000"+year;
            }else if(year<100){
                stringYear="00"+year;
            }else if(year<1000){
                stringYear="0"+year;
            }else stringYear=""+year;
            
            String stringDate=stringDay+"/"+stringMonth+"/"+stringYear;
            
            SimpleDateFormat simple=new SimpleDateFormat("dd/MM/yyyy");
            Date date=simple.parse(stringDate);
            System.out.println(simple.format(date));
            if(simple.format(date).equals(stringDate)){
                return true;
            }
        } catch (ParseException ex) {
            Logger.getLogger(DateUtils.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }
    
    // calculate age of user , if year > current year return -1
   //if year < current year return age of user
    public static int calculateAge(int year){
        Date date = new Date();        
        return date.getYear()+1900 -year  >=0?date.getYear()+1900 -year:-1;
    }
}
