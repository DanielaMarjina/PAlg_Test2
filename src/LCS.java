import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LCS {

    public static void citireFisier(Scanner scanner) throws IOException {
        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }
            int m = scanner.nextInt();
            int[] b = new int[m];
            for (int i = 0; i < m; i++) {
                b[i] = scanner.nextInt();
            }
            LCS(a, b);
        }
    }

    public static void LCS(int[] a, int[] b) throws IOException {
        int n = a.length;
        int m = b.length;

        int[][] lung = new int[n][m];

        lung[0][0] = (a[0] == b[0]) ? 1 : 0;

        for (int i = 1; i < n; i++) {
            lung[i][0] = (a[i] == b[0]) ? 1 : 0;
        }

        for (int j = 1; j < m; j++) {
            lung[0][j] = (a[0] == b[j]) ? 1 : 0;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int canditat = (a[i] == b[j]) ? lung[i - 1][j - 1] + 1 : lung[i - 1][j - 1];
                lung[i][j] = Math.max(canditat, Math.max(lung[i - 1][j], lung[i][j - 1]));
            }
        }

        tiparesteLCS(a,b,lung);
    }

    public static void tiparesteLCS(int[] a, int[] b, int[][] lung) throws IOException {
        List<Integer> subsecventa = new ArrayList<>();

        int i = a.length - 1;
        int j = b.length - 1;

        while (i >= 0 && j >= 0) {
            if (a[i] == b[j]) {
                subsecventa.add(a[i]);
                i--;
                j--;
            } else {
                if (i >= 1 && lung[i - 1][j] == lung[i][j]) {
                    i--;
                } else if (j >= 1 && lung[i][j - 1] == lung[i][j]) {
                    j--;
                } else
                    break;
            }
        }

        FileWriter fileWriter = new FileWriter("output_lcs.txt");
        fileWriter.write(subsecventa.size() + "\n");
        Collections.reverse(subsecventa);
        for (int x : subsecventa) {
            fileWriter.write(x + " ");
        }
        fileWriter.close();
    }

    public static void citireFisier2(Scanner scanner) throws IOException {
        while(scanner.hasNext()){
            int n=scanner.nextInt();
            int[] a=new int[n];
            for(int i=0;i<n;i++){
                a[i]= scanner.nextInt();
            }
            LCSPalindrom(a,n);
        }
    }

    public static void LCSPalindrom(int[] a, int n) throws IOException {
        int[] a2=new int[n];
        for(int i=0;i<n;i++){
            a2[i]=a[n-1-i];
        }
        LCS(a,a2);
    }

    public static void citireFisier3(Scanner scanner) throws IOException {
        while (scanner.hasNext()){
            int t= scanner.nextInt();
            while(t!=0){
                int n= scanner.nextInt();
                scanner.nextLine();
                List<List<String>> cities=new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    String linie = scanner.nextLine();
                    cities.add(Arrays.asList(linie.split(" ")));
                }
                solve(cities);
                t--;
            }
        }
    }

    public static void solve(List<List<String>> cities) throws IOException {

        List<String> sol = cities.get(0);

        for (int i = 1; i < cities.size(); i++) {
            sol = LCSExcursii(sol, cities.get(i));  ///ASTA E IMPOPRTANT
        }

        FileWriter fileWriter = new FileWriter("output_lcsExcursii.txt", true);
        fileWriter.write(sol.size() + "\n");
        fileWriter.close();
    }

    public static List<String> LCSExcursii(List<String> a, List<String> b) {

        int n = a.size();
        int m = b.size();

        int[][] lung = new int[n][m];

        lung[0][0] = (a.get(0).equals(b.get(0))) ? 1 : 0;

        for (int i = 1; i < n; i++)
            lung[i][0] = (a.get(i).equals(b.get(0))) ? 1 : lung[i-1][0];

        for (int j = 1; j < m; j++)
            lung[0][j] = (a.get(0).equals(b.get(j))) ? 1 : lung[0][j-1];

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (a.get(i).equals(b.get(j)))
                    lung[i][j] = lung[i-1][j-1] + 1;  //asta e diferenta
                else
                    lung[i][j] = Math.max(lung[i-1][j], lung[i][j-1]); //asta tot
            }
        }

        List<String> orase = new ArrayList<>();
        int i = n - 1, j = m - 1;

        while (i >= 0 && j >= 0) {
            if (a.get(i).equals(b.get(j))) {
                orase.add(a.get(i));
                i--; j--;
            } else if (i > 0 && lung[i-1][j] == lung[i][j]) {
                i--;
            } else {
                j--;
            }
        }

        Collections.reverse(orase);
        return orase;
    }




}