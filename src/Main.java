import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new BufferedReader(new FileReader("input_lis.txt")));
        LIS.citireFisier(scanner);

        Scanner scanner1 = new Scanner(new BufferedReader(new FileReader("input_lcs.txt")));
        LCS.citireFisier(scanner1);

        Scanner scanner2=new Scanner(new BufferedReader(new FileReader("input_lee.txt")));
        LEE.citireFisier(scanner2);

        Scanner scanner3=new Scanner(new BufferedReader(new FileReader("elefanti_input.txt")));
        LIS.citireFisier2(scanner3);

        Scanner scanner4=new Scanner(new BufferedReader(new FileReader("input_lcsPalindrom.txt")));
        LCS.citireFisier2(scanner4);

        Scanner scanner5=new Scanner(new BufferedReader(new FileReader("input_lcsExcursii.txt")));
        LCS.citireFisier3(scanner5);
    }
}