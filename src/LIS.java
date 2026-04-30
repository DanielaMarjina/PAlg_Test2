import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class LIS{

    public static void citireFisier(Scanner scanner) throws IOException {
        int n=scanner.nextInt();
        int[] a=new int[n];
        for(int i=0;i<n;i++){
            a[i]=scanner.nextInt();
        }
        lis(a,n);
    }


    public static void lis(int[] a, int n) throws IOException {
        int[] lung = new int[n];
        lung[n - 1] = 1;

        for (int i = n - 2; i >= 0; i--) {
            int max = 0;
            for (int j = i + 1; j < n; j++) {
                if (a[i] <= a[j]) {
//                    System.out.println(a[i]);
//                    System.out.println(a[j]);
//                    System.out.println("Max: "+max);
                    if (max < lung[j])
                        max = lung[j];
                }
            }
            lung[i] = max + 1;
//            System.out.println(Arrays.toString(lung)+i);
        }
//        System.out.println(Arrays.toString(lung));
        printLIS(a,n,lung);
    }

    public static void printLIS(int[] a, int n, int[] lung) throws IOException {
        FileWriter fileWriter= new FileWriter("output_lis.txt");
        int max = 0, poz = 0;

        for (int i = 0; i < n; i++) {
            if (max < lung[i]) {
                max = lung[i];
                poz = i;
            }
        }

        int cmax = max, cpoz = poz;
        int k=0;
        fileWriter.write(max+"\n");
        fileWriter.write(a[cpoz]+" ");

        for (int i = cpoz + 1; i < n; i++) {
            if (lung[i] == cmax - 1 && a[i] >= a[cpoz]) {
                fileWriter.write(a[i]+" ");
                cpoz = i;
                cmax--;
            }
        }

        fileWriter.close();

    }


}
