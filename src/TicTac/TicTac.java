package TicTac;

import java.util.Random;
import java.util.Scanner;

public class TicTac {

    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '.';
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();
    private static int fieldSizeY;
    private static int fieldSizeX;
    private static char[][] field;
    private static String humanWinCondition;
    private static String aiWinCondition;


    private static void initField() {
        System.out.println("Введите размер поля по горизонтали и вертикали через пробел");
        fieldSizeX = SCANNER.nextInt();
        fieldSizeY = SCANNER.nextInt();
        System.out.println("Введите количество фишек для победы");
        int dotsToWin = SCANNER.nextInt();
        humanWinCondition = "";
        aiWinCondition = "";
        for (int i = 0; i < dotsToWin; i++){
            humanWinCondition = humanWinCondition + DOT_HUMAN;
            aiWinCondition = aiWinCondition + DOT_AI;
        }
        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = DOT_EMPTY;
            }
        }
    }

    private static void printField() {
        System.out.print("+");
        for (int x = 0; x < fieldSizeX * 2 + 1; x++)
            System.out.print((x % 2 == 0) ? "-" : x / 2 + 1);
        System.out.println();

        for (int y = 0; y < fieldSizeY; y++) {
            System.out.print(y + 1 + "|");
            for (int x = 0; x < fieldSizeX; x++)
                System.out.print(field[y][x] + "|");
            System.out.println();
        }

        for (int x = 0; x <= fieldSizeX * 2 + 1; x++)
            System.out.print("-");
        System.out.println();
    }

    private static void humanTurn() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты хода X и Y (от 1 до " + fieldSizeX + " и от 1 до " + fieldSizeY + " соответственно) через пробел >>> ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isValidCell(x, y) || !isEmptyCell(x, y));
        field[y][x] = DOT_HUMAN;
    }

    private static boolean isEmptyCell(int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }

    private static boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    private static void aiTurn() {
        if  (!( (checkPossibleWin(aiWinCondition, DOT_AI)) ||
                (checkPossibleWin(humanWinCondition, DOT_HUMAN)) ||
                (checkPossibleWin(humanWinCondition.substring(0, humanWinCondition.length()-1), DOT_HUMAN )) ||
                (checkPossibleWin(aiWinCondition.substring(0, aiWinCondition.length()-1), DOT_AI)) )){
            int x = RANDOM.nextInt(fieldSizeX);
            int y = RANDOM.nextInt(fieldSizeY);
            field[y][x] = DOT_AI;
        }
    }

    private static boolean checkPossibleWin(String winCondition, char tempDot){
        for (int y = 0; y < fieldSizeY; y++){
            for (int x = 0; x < fieldSizeX; x++){
                if (isEmptyCell(x, y)){
                    field[y][x] = tempDot;
                    if (checkWin(winCondition)){
                        field[y][x] = DOT_AI;
                        return true;
                    }
                    field[y][x] = DOT_EMPTY;
                }
            }
        }
        return false;
    }

    private static boolean checkDraw() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isEmptyCell(x, y)) return false;
            }
        }
        return true;
    }

    private static boolean checkWin(String str) {
        // hor
         if ( checkLines(str) || checkMainDiagonals(str) || checkVerticals(str) || checkReverseDiagonals(str) ){
            return true;
        }
        return false;

    }
    private static boolean checkVerticals(String str){
        String temp = "";
        for (int i = 0; i < fieldSizeX; i++){
            for (int j = 0; j < fieldSizeY; j++){
                temp = temp + field[j][i];
            }
            if (temp.contains(str)) {
                return true;
            }
            temp = "";
        }
        return false;
    }

    private static boolean checkLines(String str){
        String temp = "";
        for (int i = 0; i < fieldSizeY; i++){
            for (int j = 0; j < fieldSizeX; j++){
                temp = temp + field[i][j];
            }
            if (temp.contains(str)) {
                return true;
            }
            temp = "";
        }
        return false;
    }

    private static boolean checkMainDiagonals(String str){
        String temp = "";
        for (int delta = 0; delta < fieldSizeY; delta++){
            for (int i = delta; i < fieldSizeY; i++){
                for (int j = 0; j < fieldSizeX; j++){
                    if (i == j + delta){
                        temp = temp + field[i][j];
                    }
                }
            }
            if (temp.contains(str)){
                return true;
            }
            temp = "";
        }
        for (int delta = 0; delta < fieldSizeX; delta++){
            for (int i = 0; i < fieldSizeX; i++){
                for (int j = delta; j < fieldSizeY; j++){
                    if (i + delta == j){
                        temp = temp + field[i][j];
                    }
                }
            }
            if (temp.contains(str)){
                return true;
            }
            temp = "";
        }
        return false;
    }

    private static boolean checkReverseDiagonals(String str){
        String temp = "";
        for (int delta = 0; delta < fieldSizeX; delta++){
            for (int i = 0; i < fieldSizeY; i ++){
                for (int j = fieldSizeX - 1 - delta; j >= 0; j--){
                    if (i + j == fieldSizeX - 1 - delta){
                        temp = temp + field[i][j];
                    }
                }
            }
            if (temp.contains(str)){
                return true;
            }
            temp = "";
        }
        for (int delta = 0; delta < fieldSizeY; delta++){
            for (int i = delta; i < fieldSizeY; i++){
                for (int j = 0 ; j < fieldSizeX; j++){
                    if (i + j == fieldSizeX - 1 + delta){
                        temp = temp + field[i][j];
                    }
                }
            }
            if (temp.contains(str)){
                return true;
            }
            temp = "";
        }
        return false;
    }

    public static void main(String[] args) {
        String answer;
        do {
            initField();
            printField();
            while (true) {
                humanTurn();
                if (checkEndGame(humanWinCondition, "Human win!")) break;
                aiTurn();
                if (checkEndGame(aiWinCondition, "Computer win!")) break;
            }
            System.out.println("Wanna play again? (y/n) >>> ");
            answer = SCANNER.next();
        } while (answer.equals("y"));
        SCANNER.close();
    }

    private static boolean checkEndGame(String dot, String winMessage) {
        printField();
        if (checkWin(dot)) {
            System.out.println(winMessage);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Draw!");
            return true;
        }
        return false;
    }
}
