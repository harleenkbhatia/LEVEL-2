
 
import java.util.Scanner;

public class bytelearn{

    public static Scanner scn = new Scanner(System.in);

    //Question - 1
    //string reduction

    public static int minDel(String str, int[] maxLetters)
    {
         
        int n = str.length();
         
        if (n > maxLetters[0])
            return -1;
        
        int diffCount = 0;
        int count[] = new int[maxLetters[0]];
         
        for(int i = 0; i < maxLetters[0]; i++)
            count[i] = 0;
         
        for (int i = 0; i < n; i++)
        {
            if(count[str.charAt(i)-'a'] == 0)
                diffCount++;
            count[str.charAt(i)-'a']++;
        }
         
        return (n-diffCount);
    }
     
    public static void main (String[] args) {
         
        String str = scn.nextLine();;
        int[] maxLetters = new int[1];
        maxLetters[0] = 26;
         
        System.out.println(minDel(str, maxLetters));
    }

    //Question - 2
    //balancing parenthesis

    public int minInsertions(String str) {
        int bal = 0;
        int ans = 0;
       
        for (int i = 0; i < str.length(); ++i) {
       
            bal += str.charAt(i) == '(' ? 1 : -1;
       
            if (bal == -1) {
                ans += 1;
                bal += 1;
            }
        }
       
        return bal + ans;
    }
    public int main(String args[])
    {
        String str = scn.nextLine();
         
        return minInsertions(str);
       
    }
}


