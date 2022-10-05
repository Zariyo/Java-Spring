import java.lang.Math;
import java.util.Scanner;

public class zad2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n;
        n = scanner.nextInt();
        int m = n;
        int len = String.valueOf(n).length();
        int suma = 0;
        for(int i = 0;i< len;i++){
            suma += (int) Math.pow((n % 10),len);
            n = n/10;
        }
        System.out.println("Suma = " + suma);
        if(suma==m){
            System.out.println("Prawda");
        }else{
            System.out.println("Fałsz");
        }
    }
}
