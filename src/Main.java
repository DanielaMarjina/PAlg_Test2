import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner=new Scanner(new BufferedReader(new FileReader("input_lis.txt")));
        LIS.citireFisier(scanner);

        Scanner scanner1=new Scanner(new BufferedReader(new FileReader("input_lcs.txt")));
        LCS.citireFisier(scanner1);


    }
}