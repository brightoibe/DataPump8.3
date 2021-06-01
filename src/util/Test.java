/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Months;

/**
 *
 * @author The Bright
 */
/*
   41	26	Akwanga	Nasarawa
72	26	Awe	Nasarawa
161	26	Doma	Nasarawa
437	26	Kokona	Nasarawa
428	26	Keffi	Nasarawa
426	26	Keana	Nasarawa
417	26	Karu	Nasarawa
459	26	Lafia	Nasarawa
548	26	Obi	Nasarawa
522	26	Nasarawa Egon	Nasarawa
521	26	Nasarawa	Nasarawa
711	26	Toto	Nasarawa
743	26	Wamba	Nasarawa
 

*/
public class Test {
    
    public static void main(String[] arg){
        DateTime d1=new DateTime(new Date());
        DateTime d2=d1.plusMonths(6);
        System.out.println(Months.monthsBetween(d1,d2).getMonths());
    }

}
