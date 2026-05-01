import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LEE {

    static int[] di={0,0,1,-1};
    static int[] dj={1,-1,0,0};

    static class Punct{
        int i,j;

        public Punct(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "Punct{" +
                    "i=" + i +
                    ", j=" + j +
                    '}';
        }
    }

    public static void citireFisier(Scanner scanner) throws IOException {
        while(scanner.hasNext()) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            int[][] a = new int[m][n];

            for (int i = 0; i < m; i++) {
                String linie = scanner.next();
                linie = linie.replace("-1", "X");
                //System.out.println(linie);

                for (int j = 0; j < n; j++) {
                    if (linie.charAt(j) == 'X') {
                        a[i][j] = -1;
                    } else {
                        a[i][j] = 0;
                    }
                }
            }

            int iStart = scanner.nextInt();
            int jStart = scanner.nextInt();

            int iStop = scanner.nextInt();
            int jStop = scanner.nextInt();

//        for (int i = 0; i < m; i++) {
//            System.out.println(Arrays.toString(a[i]));
//        }

            //System.out.println(iStart + " " + jStart + " " + iStop + " " + jStop);

            lee(a, m, n, iStart, jStart, iStop, jStop);
        }
    }

    public static void lee(int[][] a, int m, int n, int iStart, int jStart, int iStop, int jStop) throws IOException {
        a[iStart][jStart]=1;

        Queue<Punct> coada=new LinkedList<>();

        coada.add(new Punct(iStart,jStart));

        while(!coada.isEmpty()){
            //System.out.println(coada);
            Punct p=coada.poll();
            int i=p.i;
            int j=p.j;

            for(int t=0;t<=3;t++){
                int iv=i+di[t];
                int jv=j+dj[t];

                if(iv>=0 && iv<m && jv>=0 && jv<n && a[iv][jv]==0){
                    a[iv][jv]=a[i][j]+1;
                    coada.add(new Punct(iv,jv));
                }
            }

        }

//        System.out.println("Matrice dupa Lee:");
//        for (int i = 0; i < m; i++) {
//            System.out.println(Arrays.toString(a[i]));
//        }

        tiparesteDrum(a,m,n,iStart,jStart,iStop,jStop);
    }

    public static void tiparesteDrum(int[][] a,int m, int n, int iStart, int jStart, int iStop, int jStop) throws IOException {
        FileWriter fileWriter=new FileWriter("output_lee.txt");

        List<String> drum=new ArrayList<>();

        int i=iStop;
        int j=jStop;

        int d=a[iStop][jStop];

        while(d>1){
            drum.add(i + " " + j );
            for(int t=0;t<4;t++){
                int iv=i+di[t];
                int jv=j+dj[t];
                if(iv>=0 && iv<m && jv>=0 && jv<n && a[iv][jv]==d-1){
                    i=iv;
                    j=jv;
                    d--;
                    break;
                }
            }
        }
        drum.add( + iStart + " " + jStart );

        Collections.reverse(drum);

        fileWriter.write(a[iStop][jStop]+"\n");

        for(String p : drum)
            fileWriter.write(p+"\n");

        fileWriter.close();
    }
}
