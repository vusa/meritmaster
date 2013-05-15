package za.co.merits;

/**
 * Created with IntelliJ IDEA.
 * User: vusa
 * Date: 2013/01/11
 * Time: 2:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Euler {
    public static void main(String[] args) {
        double start20 = 2520;
        for(double i=11; i <=20 ;i++){
            start20 = start20 * i;
        }

        double temp = start20;
        for (double small20 = 2520; small20<=start20; small20=small20+20){
            boolean  found = true;
            for(double j = 11; j<=20; j++){
                if(small20 % j != 0){
                     found = false;
                     break;
                }
            }
            if (found){
                System.out.println("found :" +small20);
                System.exit(0);
            }
        }
        System.out.println("found:"+ start20 );
    }
}
