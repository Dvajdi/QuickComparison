package ru.forge.twice_a_day.quickcomparison.util;

/**
 * Created by Pavel M. on 15.06.16.
 */
public class FormatAdapter {
    static int koef;
    static String str;

    public static int getKoef(String str){
        char point1 = '.';
        char point2 = ',';
        StringBuilder sb = new StringBuilder(str);
        int numberPoint1=0,numberPoint2=0;

        for (int i = 0; i <str.length() ; i++) {
                if(point1==sb.charAt(i)){numberPoint1=i;
//                Log.d("numberPoint1","numberPoint1 = "+numberPoint1);
                }
                if(point2==sb.charAt(i)){numberPoint2=i;}
        }
            if(numberPoint1>0){koef=(str.length()-1)-numberPoint1;}
            if(numberPoint2>0){koef=(str.length()-1)-numberPoint2;}
        if(numberPoint1==numberPoint2){koef=0;}
        koef+=2;
        return koef;
    }

    public static void main(String[] args) {
        System.out.println(getKoef("0,001"));
        System.out.println(getKoef("0.00001"));
    }
}
