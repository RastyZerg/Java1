package InOutputStreamsHomework;

import java.io.*;
import java.util.Scanner;


public class Main {

    private static void FillFileDigits(String fileName){
        StringBuilder sb = new StringBuilder();
        try {
            PrintStream out = new PrintStream(new FileOutputStream(fileName));
            for (int i = 0; i < 151; i++){
                if ( (i % 50) == 0) {
                    sb.append("\n");
                }
                sb.append(i);
            }
            out.println(sb.toString());
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static void FillFileLetters(String fileName){
        try {
            PrintStream out = new PrintStream(new FileOutputStream(fileName));
            out.println("fgsafdsajfhlskdafhdslkfhlksdajfhksladhfklsdhfjlksalf\n" +
                    "fdskl;fjsdlafjsldafjlsafj;lsfjksld;\n" +
                    "gdglkdsgjfdlksgjdfslkghfsklgjhdfsklghkdlsgjhd\n" +
                    "sfadf");
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void concFiles(String file1, String file2){
        String outFile = "File3.txt";
        try {
            Scanner in = new Scanner(new FileInputStream(file1));
            PrintStream out = new PrintStream(new FileOutputStream(outFile));
            while (in.hasNextLine()) {
                out.println(in.nextLine());
            }
            in = new Scanner(new FileInputStream(file2));
            while (in.hasNextLine()) {
                out.println(in.nextLine());
            }
            in.close();
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean SearchWordInFile(String fileName, String word){
        try {
            Scanner sc = new Scanner(new FileInputStream(fileName));
            while (sc.hasNextLine()){
                if (sc.nextLine().contains(word)){
                    return true;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    private static boolean SearchWordInFolder(String folderName, String word){
        File folder = new File(folderName);
        File[] files = folder.listFiles();
        for (int i = 0; i < files.length; i++){
            if (SearchWordInFile(files[i].getName(), word)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        FillFileDigits(new String("File1.txt"));
        FillFileLetters(new String("File2.txt"));
        concFiles("File1.txt", "File2.txt");
        System.out.println(SearchWordInFile("File3.txt", "gjdfslkghfsklgj"));
        System.out.println(SearchWordInFile("File3.txt", "132"));
        System.out.println(SearchWordInFile("File3.txt", "0f"));
        System.out.println(SearchWordInFolder("D:\\\\Idea", "gjdfslkghfsklgj"));
        System.out.println(SearchWordInFolder("D:\\\\Idea", "0f"));
    }
}
