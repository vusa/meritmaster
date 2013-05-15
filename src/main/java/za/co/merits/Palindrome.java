package za.co.merits;

/**
 * Created with IntelliJ IDEA.
 * User: vusa
 * Date: 2013/01/11
 * Time: 3:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class Palindrome {
    static  int temp = 0;
    static int biggest = 0;
    public static void main(String[] args) {

        for(int n1 = 999; n1>=100; n1--){
            for(int n2 = 999; n2>=100; n2--){
                 int product = n1*n2;
                 int palindrome = testPalindrome(product);
                 if(palindrome>temp){
                     temp = palindrome;
                     if(biggest < Math.max(n1,n2)){
                        biggest = Math.max(n1,n2);
                     }else{
                         System.out.println("found:"+temp);
                        System.exit(0);
                     }
                 }
            }
        }
    }

    private static int testPalindrome(int palindrome) {
        String pali = ""+palindrome;
        System.out.println("testing "+ pali);
        int center = pali.length() / 2;
        boolean match = true;
        for(int i=0;i<=center; i++){
            char c1 = pali.charAt(i);
            char c2 = pali.charAt(pali.length()-(i+1));
            if(c1 !=c2){
            match = false;
            break;
            }
        }
        if(match){
            System.out.println("found:"+palindrome);
            return palindrome;
        }
        return 0;
    }
}
