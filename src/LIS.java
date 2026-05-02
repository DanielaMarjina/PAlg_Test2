import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class LIS{

    //CEA MAI LUNGA SUBSECVENTA....

    public static void citireFisier(Scanner scanner) throws IOException {
        while(scanner.hasNext()) {
            int n = scanner.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }
            //lis(a, n);
            //lis2(a, n);
            //lisPare(a,n);
            lisSuma(a,n);
        }
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
        fileWriter.write("Subsecventa comuna crescatoare \n");
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

    public static void lis2(int[] a, int n) throws IOException {
        int[] lung=new int[n];
        lung[n-1]=1;

        for(int i=n-2;i>=0;i--){
            int max=0;
            for (int j=i+1;j<n;j++){
                if(a[i]>=a[j]){
                    if(max<lung[j]){
                        max=lung[j];
                    }
                }
            }
            lung[i]=max+1;
        }

        printLis2(a,n,lung);
    }

    public static void printLis2(int[] a,int n, int[] lung) throws IOException {
        FileWriter fileWriter=new FileWriter("output_lis.txt",true);
        fileWriter.write("\nSubsecventa descrescatoare\n");
        int max=0;
        int poz=0;

        for(int i=0;i<n;i++){
            if(max<lung[i]){
                max=lung[i];
                poz=i;
            }
        }

        int cmax=max, cpoz=poz;

        int k=0;
        fileWriter.write(max+"\n");
        fileWriter.write(a[cpoz]+" ");
        for(int i=cpoz+1;i<n;i++){
            if(lung[i]==cmax-1 && a[i]<=a[cpoz]) {
                fileWriter.write(a[i] + " ");
                cpoz=i;
                cmax--;
            }
        }

        fileWriter.close();

    }

    public static void lisPare(int[] a, int n) throws IOException {

        // 1. construim array cu numere pare
        int[] temp = new int[n];
        int k = 0;

        for(int i = 0; i < n; i++){
            if(a[i] % 2 == 0){
                temp[k++] = a[i];
            }
        }

        // dacă nu avem elemente pare
        if(k == 0){
            FileWriter fileWriter = new FileWriter("output_lis.txt", true);
            fileWriter.write("\nNu exista numere pare\n");
            fileWriter.close();
            return;
        }

        // 2. copiem în array de dimensiune corectă
        int[] b = new int[k];
        for(int i = 0; i < k; i++){
            b[i] = temp[i];
        }

        // 3. APELĂM LIS NORMAL (fără modificări)
        lis(b, k);
    }

    public static void lisSuma(int[] a, int n) throws IOException {
        int[] lung=new int[n];
        lung[n-1]=a[n-1];
        for(int i=n-2;i>=0;i--){
            int max=0;
            for (int j=i+1;j<n;j++){
                if(a[i]<=a[j]){
                    if(max<lung[j])
                        max=lung[j];
                }
            }
            lung[i]=a[i]+max;
        }

        tiparesteLisSum(a,n,lung);
    }

    public static void tiparesteLisSum(int[] a, int n, int[] lung) throws IOException {
        FileWriter fileWriter = new FileWriter("output_lis.txt", true);
        fileWriter.write("\nSubsecventa crescatoare cu suma maxima\n");

        int max=0, poz=0;

        for(int i=0;i<n;i++){
            if(max<lung[i]) {
                max = lung[i];
                poz = i;
            }
        }

        int csum=max, cpoz=poz;

        fileWriter.write(csum+"\n");
        fileWriter.write(a[cpoz]+" ");

        for(int i=cpoz+1;i<n;i++){
            if(a[i]>=a[cpoz] && lung[i]==csum-a[cpoz]){
                fileWriter.write(a[i]+" ");
                csum -= a[cpoz];
                cpoz = i;
            }
        }

        fileWriter.close();
    }

    public static void citireFisier2(Scanner scanner) throws IOException {
        while (scanner.hasNext()) {
            int t = scanner.nextInt();
            while(t!=0){
                int n= scanner.nextInt();
                int[] greutate=new int[n];
                int[] inteligenta=new int[n];
                for(int i=0;i<n;i++){
                    greutate[i]= scanner.nextInt();
                    inteligenta[i]=scanner.nextInt();
                }
                elefantiLIS(greutate,inteligenta,n);
                t--;
            }
        }
    }

    public static void elefantiLIS(int[] greutate, int[] inteligenta, int n) throws IOException {
        //sortare x sa fie crescator, ideal y sa fie descrescator

        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(greutate[i]>greutate[j]){
                    int temp=greutate[i];
                    greutate[i]=greutate[j];
                    greutate[j]=temp;

                    temp=inteligenta[i];
                    inteligenta[i]=inteligenta[j];
                    inteligenta[j]=temp;
                }

            }
        }

        int[] lung=new int[n];
        lung[n-1]=1;

        for(int i=n-2;i>=0;i--){
            int max=0;
            for(int j=i+1;j<n;j++){
                if(inteligenta[i]>inteligenta[j]){
                    if(max<lung[j]) {
                        max = lung[j];
                    }
                }
            }
            lung[i]=max+1;
        }

        printLISElefanti(greutate,inteligenta,n,lung);
    }

    public static void printLISElefanti(int[] greutate, int[] inteligenta, int n, int[] lung) throws IOException {
        int max=0;
        for (int i=0;i<n;i++){
            if(max<lung[i]){
                max=lung[i];
            }
        }

        FileWriter fileWriter = new FileWriter("elefanti_output.txt", true);
        fileWriter.write("\nElefanti\n");
        fileWriter.write(max+"\n");
        fileWriter.close();



    }


}
