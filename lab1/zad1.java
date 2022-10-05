import java.util.Scanner;

public class zad1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        Integer n;
        n = scanner.nextInt();
        for (int i = 1;i<=n;i++){
            for(int j = 0;j<i;j++){
                System.out.print("*");
            }
            System.out.println("");
        }
        System.out.println("");
        for (int i = 1;i<=n;i++){
            for(int j = n;j>=i;j--){
                System.out.print("*");
            }
            System.out.println("");
        }
        System.out.println("");
        for (int i = 1;i<=n;i++){
            for(int j = 0;j<n;j++){
                if(n-i<=j){
                    System.out.print("*");
                }
                else{
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
        for (int i = 1;i<=n;i++){
            for(int j = 1;j<=n;j++){
                if(j>=i){
                    System.out.print("*");
                }
                else{
                    System.out.print(" ");
                }

            }
            System.out.println("");
        }

    }
}