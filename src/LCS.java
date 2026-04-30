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
            LCS(a,b);
        }
    }


    public static void LCS(int[] a, int[] b) throws IOException {
        int n = a.length;
        int m = b.length;

        int[][] lung = new int[n][m];

        lung[0][0] = (a[0] == b[0]) ? 1 : 0;

        for (int j = 1; j < m; j++) {  //mergem pe rand
            lung[0][j] = (a[0] == b[j]) ? 1 : 0;
        }

        for (int i = 1; i < n; i++) { //mergem pe coloana
            lung[i][0] = (a[i] == b[0]) ? 1 : 0;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int candidat = (a[i] == b[j]) ? lung[i - 1][j - 1] + 1 : lung[i - 1][j - 1];
                lung[i][j] = Math.max(candidat, Math.max(lung[i - 1][j], lung[i][j - 1]));
            }
        }

        tiparesteLCS(a,b,lung);
    }

    public static void tiparesteLCS(int[] a,int[] b, int[][] lung) throws IOException {
        FileWriter fileWriter=new FileWriter("output_lcs.txt");
        List<Integer> subsecventa=new ArrayList<>();
        int i=a.length-1;
        int j=b.length-1;

        while(i>=0 && j>=0){
            if(a[i]==b[j]){
                subsecventa.add(a[i]);
                i--;
                j--;
            }
            else{
                if(i>=1 && lung[i-1][j]==lung[i][j]){
                    i--;
                } else if (j>=1 && lung[i][j-1]==lung[i][j]) {
                    j--;
                }
                else {
                    break;
                }
            }
        }

        Collections.reverse(subsecventa);

        fileWriter.write(subsecventa.toArray().length+"\n");
        for(int x:subsecventa){
            fileWriter.write(x+" ");
        }

        fileWriter.close();
    }
}
