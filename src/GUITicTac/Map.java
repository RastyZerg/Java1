package GUITicTac;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.Random;

public class Map extends JPanel {
    private static final String MSG_WIN_HUMAN = "Победил игрок!";
    private static final String MSG_WIN_AI = "Победил Компьютер!";
    private static final String MSG_DRAW = "Ничья!";
    private static final Random RANDOM = new Random();
    public static final int MODE_HVH = 0;
    public static final int MODE_HVA = 1;
    private int fieldSizeY;
    private int fieldSizeX;
    private int winLength;
    private int cellWidth;
    private int cellHeight;
    private char[][] field;
    private static String humanWinCondition;
    private static String aiWinCondition;
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '.';
    private static final int DOT_PADDING = 5;
    private boolean isInitialized = false;
    private int stateGameOver;
    private final int STATE_WIN_HUMAN = 1;
    private final int STATE_WIN_AI = 2;
    private final int STATE_DRAW = 0;
    private boolean isGameOver;

    Map() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                update(e);
            }
        });
        this.isInitialized = false;
    }

    private void update(MouseEvent e){
        if (!isInitialized) {
            return;
        }
        if (isGameOver) {
            return;
        }
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;
        System.out.printf("%d, %d\n", cellX, cellY);
        if (!isValidCell(cellX, cellY) || !isEmptyCell(cellX, cellY)){
            /*if (!isValidCell(cellX, cellY)) System.out.printf("valid %d %d\n", cellX, cellY);
            else System.out.printf("empty %d %d", cellY, cellX);*/
            return;
        }
        this.field[cellY][cellX] = DOT_HUMAN;
        repaint();
        if (checkEndGame(humanWinCondition, STATE_WIN_HUMAN)){
            return;
        }
        //printField();
        aiTurn();
        repaint();
        if (checkEndGame(aiWinCondition, STATE_WIN_AI)){
            return;
        }
    }

    private void printField() {
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

    private boolean isEmptyCell(int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }

    private boolean isValidCell(int x, int y) {
        return x >= 0 && x < this.fieldSizeX && y >= 0 && y < this.fieldSizeY;
    }

    private void aiTurn() {
        if  (!( (checkPossibleWin(aiWinCondition, DOT_AI)) ||
                (checkPossibleWin(humanWinCondition, DOT_HUMAN)) ||
                (checkPossibleWin(aiWinCondition.substring(0, aiWinCondition.length()-1), DOT_AI)) ||
                (checkPossibleWin(humanWinCondition.substring(0, humanWinCondition.length()-1), DOT_HUMAN )) )){
            int x, y;
            do {
                x = RANDOM.nextInt(this.fieldSizeX);
                y = RANDOM.nextInt(this.fieldSizeY);
            } while (!isEmptyCell(y, x));
            field[y][x] = DOT_AI;
        }
    }

    private boolean checkPossibleWin(String winCondition, char tempDot){
        for (int y = 0; y < this.fieldSizeY; y++){
            for (int x = 0; x < this.fieldSizeX; x++){
                if (isEmptyCell(x, y)){
                    this.field[y][x] = tempDot;
                    if (checkWin(winCondition)){
                        this.field[y][x] = DOT_AI;
                        return true;
                    }
                    this.field[y][x] = DOT_EMPTY;
                }
            }
        }
        return false;
    }

    private boolean checkDraw() {
        for (int y = 0; y < this.fieldSizeY; y++) {
            for (int x = 0; x < this.fieldSizeX; x++) {
                if (isEmptyCell(x, y)) return false;
            }
        }
        return true;
    }

    private boolean checkWin(String str) {
        // hor
        if ( checkLines(str) || checkMainDiagonals(str) || checkVerticals(str) || checkReverseDiagonals(str) ){
            return true;
        }
        return false;

    }
    private boolean checkVerticals(String str){
        String temp = "";
        for (int i = 0; i < this.fieldSizeX; i++){
            for (int j = 0; j < this.fieldSizeY; j++){
                temp = temp + this.field[j][i];
            }
            if (temp.contains(str)) {
                return true;
            }
            temp = "";
        }
        return false;
    }

    private boolean checkLines(String str){
        String temp = "";
        for (int i = 0; i < this.fieldSizeY; i++){
            for (int j = 0; j < this.fieldSizeX; j++){
                temp = temp + this.field[i][j];
            }
            if (temp.contains(str)) {
                return true;
            }
            temp = "";
        }
        return false;
    }

    private boolean checkMainDiagonals(String str){
        String temp = "";
        for (int delta = 0; delta < this.fieldSizeY; delta++){
            for (int i = delta; i < this.fieldSizeY; i++){
                for (int j = 0; j < this.fieldSizeX; j++){
                    if (i == j + delta){
                        temp = temp + this.field[i][j];
                    }
                }
            }
            if (temp.contains(str)){
                return true;
            }
            temp = "";
        }
        for (int delta = 0; delta < this.fieldSizeX; delta++){
            for (int i = 0; i < this.fieldSizeX; i++){
                for (int j = delta; j < this.fieldSizeY; j++){
                    if (i + delta == j){
                        temp = temp + this.field[i][j];
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

    private boolean checkReverseDiagonals(String str){
        String temp = "";
        for (int delta = 0; delta < this.fieldSizeX; delta++){
            for (int i = 0; i < this.fieldSizeY; i ++){
                for (int j = this.fieldSizeX - 1 - delta; j >= 0; j--){
                    if (i + j == this.fieldSizeX - 1 - delta){
                        temp = temp + this.field[i][j];
                    }
                }
            }
            if (temp.contains(str)){
                return true;
            }
            temp = "";
        }
        for (int delta = 0; delta < this.fieldSizeY; delta++){
            for (int i = delta; i < this.fieldSizeY; i++){
                for (int j = 0 ; j < this.fieldSizeX; j++){
                    if (i + j == this.fieldSizeX - 1 + delta){
                        temp = temp + this.field[i][j];
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

    private boolean checkEndGame(String dot, int stateGameOver) {
        //printField();
        if (checkWin(dot)) {
            this.stateGameOver = stateGameOver;
            isGameOver = true;
            return true;
        }
        if (checkDraw()) {
            this.stateGameOver = STATE_DRAW;
            isGameOver = true;
            return true;
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.red);
        render(g);
    }

    private void render(Graphics g){
        if (!isInitialized) {
            return;
        }
        cellWidth = this.getWidth()/ fieldSizeX;
        cellHeight = this.getHeight()/fieldSizeY;
        for (int i = 0; i < fieldSizeX + 1; i++){
            int x = i * cellWidth;
            g.drawLine(x, 0, x, this.getHeight());
        }
        for (int i = 0; i < fieldSizeY + 1; i++){
            int y = i * cellHeight;
            g.drawLine(0, y, this.getWidth(), y);
        }
        for (int y = 0; y < fieldSizeY; y++){
            for(int x = 0; x < fieldSizeX; x++){
                if (isEmptyCell(x, y)){
                    continue;
                }
                if (this.field[y][x] == DOT_HUMAN){
                    g.setColor(new Color(1, 1, 255));
                    g.fillOval(x * cellWidth + DOT_PADDING, y * cellHeight + DOT_PADDING, cellWidth - DOT_PADDING * 2, cellHeight - DOT_PADDING * 2);
                } else if (this.field[y][x] == DOT_AI){
                    g.setColor(Color.RED);
                    g.fillRect(x * cellWidth + DOT_PADDING, y * cellHeight + DOT_PADDING, cellWidth - DOT_PADDING * 2, cellHeight - DOT_PADDING * 2);
                } else {
                    throw new RuntimeException(
                            String.format("Cannot recognize cell field[%d][%d]: %s", y, x, field[y][x]));
                }
            }
        }
        if (isGameOver){
            showMessageGameOver(g);
        }
    }

    private void showMessageGameOver(Graphics g){
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 200, getWidth(), 70);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Times new roman", Font.BOLD, 40));
        switch (stateGameOver) {
            case STATE_DRAW:
                g.drawString(MSG_DRAW, 180, getHeight() / 2);
                break;
            case STATE_WIN_AI:
                g.drawString(MSG_WIN_AI, 60, getHeight() / 2);
                break;
            case STATE_WIN_HUMAN:
                g.drawString(MSG_WIN_HUMAN, 110, getHeight() / 2);
                break;
            default:
                throw new RuntimeException("Unexpected GameOver state: " + stateGameOver);
        }
    }

    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLength) {
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.winLength = winLength;
        this.field = new char[fieldSizeX][fieldSizeY];
        this.isGameOver = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.winLength; i++){
            sb.append(DOT_AI);
        }
        aiWinCondition = sb.toString();
        sb = new StringBuilder();
        for (int i = 0; i < this.winLength; i++){
            sb.append(DOT_HUMAN);
        }
        humanWinCondition = sb.toString();
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                this.field[y][x] = DOT_EMPTY;
            }
        }
        this.isInitialized = true;
        repaint();
    }
}
