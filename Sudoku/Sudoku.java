package Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import DataBase.DataBaseManager;

public class Sudoku {

    private static JButton[][] buttons = new JButton[9][9];
    private static JButton selectedButton;
    private static int[][] grid = new int[9][9];
    private Timer timer = new Timer();
    private int second;
    public static DataBaseManager db;
    public static void initGrid(int numFilled) {
        clearGrid();  // Nettoie la grille avant de remplir
        Random rand = new Random();
        while (numFilled > 0) {
            int row = rand.nextInt(9);
            int col = rand.nextInt(9);
            int num = 1 + rand.nextInt(9);
            if (grid[row][col] == 0 && isSafe(row, col, num)) {
                grid[row][col] = num;
                numFilled--;
            }
        }
    }

    private static void clearGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = 0;
            }
        }
    }

    private static boolean isSafe(int row, int col, int num) {
        // Vérifier la ligne
        for (int c = 0; c < 9; c++) {
            if (grid[row][c] == num) {
                return false;
            }
        }
        // Vérifier la colonne
        for (int r = 0; r < 9; r++) {
            if (grid[r][col] == num) {
                return false;
            }
        }
        // Vérifier le bloc 3x3
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (grid[r][c] == num) {
                    return false;
                }
            }
        }
        return true;
    }
    public Sudoku(){
        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {
                buttons[i][j] = new JButton();
            }

        }
        initGrid(30);
        Grid();
    }

    public void startTimer () {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                second++;
            }
        }, 0, 1000); 
    }
    public void stopTimer() {
        timer.cancel(); 
        timer.purge(); 
    }

    public void Grid() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel(new GridLayout(3, 3));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < 9; i++) {
            JPanel subPanel = new JPanel(new GridLayout(3, 3));
            subPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

            for (int j = 0; j < 9; j++) {
                JButton button = new JButton();
                button.setBackground(Color.WHITE);
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                
                buttons[i][j] = button;
                if (grid[i][j] != 0) {
                    button.setText(String.valueOf(grid[i][j]));
                    button.setEnabled(true);
                }
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (selectedButton != null) {
                            selectedButton.setBackground(Color.WHITE);
                        }
                        selectedButton = (JButton) e.getSource();
                        selectedButton.setBackground(Color.LIGHT_GRAY);
                    }
                });
                subPanel.add(button);
            }

            mainPanel.add(subPanel);
        }

        JPanel buttonPanel = new JPanel(new GridLayout(1, 10, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int num = 1; num <= 9; num++) {
            JButton numButton = new JButton(String.valueOf(num));
            numButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (selectedButton != null) {
                        selectedButton.setText(((JButton) e.getSource()).getText());
                        UpdateNumber(selectedButton, Integer.valueOf(((JButton) e.getSource()).getText()));
                    }
                }
            });
            buttonPanel.add(numButton);
        }

        JButton validateButton = new JButton("Validez");
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateGrid()) {
                    JOptionPane.showMessageDialog(frame, "Félicitation! Tu as résolu le sudoku en "+second+" !");
                    stopTimer();
                    if (db.GetBestScore() < 1000-second){
                        db.SetScore(1000-second);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Il ya des erreurs dans la grille. Retente !");
                }
            }
        });
        startTimer();
        buttonPanel.add(validateButton);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setSize(500, 520);
        frame.setVisible(true);
    }
    public static boolean validateGrid() {
        for (int i = 0; i < 9; i++) {
            // Check rows
            if (!isPartValid(i, 0, 1, 9)) {
                return false;
            }
            // Check columns
            if (!isPartValid(0, i, 9, 1)) {
                return false;
            }
        }
        // Check 3x3 sub-grids
        for (int r = 0; r < 9; r += 3) {
            for (int c = 0; c < 9; c += 3) {
                if (!isPartValid(r, c, 3, 3)) {
                    return false;
                }
            }
        }
        return true;
    }
    private static boolean isPartValid(int startRow, int startCol, int rows, int cols) {
        boolean[] seen = new boolean[10]; // Index 0 jamais utilisé
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int row = startRow + i;
                int col = startCol + j;
                int num = grid[row][col];
                if (num < 1 || num > 9 || seen[num]) {
                    return false;
                }
                seen[num] = true;
            }
        }
        return true;
    }
    private void UpdateNumber(JButton button,int nb){
        int x = 0 ;
        int y = 0;
        for(int i = 0 ; i < 9 ; i++){
            for(int j = 0 ; j < 9 ; j++){
                if(button.equals(buttons[i][j])){
                    x = i;
                    y = j;
                }
            }
        }
        grid[x][y] = nb;
    }
    public static void main(String[] args) {
        new Sudoku();
    }

}
