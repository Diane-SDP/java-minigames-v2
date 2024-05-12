package Menu;

import MoreOrLess.*;
import Hangman.*;
import Memory.*;
import Blackjack.*;
import Snake.Grid;
import TrueOrFalse.*;
import Chess.*;
import NumberPuzzleGame.*;
import PacMan.*;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.net.URL;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import DataBase.DataBaseManager;


public class SelectionWindow extends JFrame {
    private Image backgroundImage;
    public static DataBaseManager db;


    public SelectionWindow() {
        ImageIcon icon = new ImageIcon("./image/start.gif");
        JLabel label = new JLabel(icon);
        add(label);
        setSize(1280, 720);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setVisible(true);
     
        Timer soundTimer = new Timer(90, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playSound("./sound/start.wav");
            }
        });
        soundTimer.setRepeats(false);
        soundTimer.start();
    
        Timer removeLabelTimer = new Timer(3800, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove(label);
                DisplayMenu();
                repaint(); 
            }
        });
        removeLabelTimer.setRepeats(false);
        removeLabelTimer.start();
    }

    private void DisplayMenu() {
        ImageIcon icon = new ImageIcon("./image/bgwallpaperzelda.png");
        backgroundImage = icon.getImage();
        setIconImage(new ImageIcon("./image/zelda.png").getImage());
        if (backgroundImage == null) {
            System.out.println("L'image n'a pas pu être chargée. Vérifiez le chemin.");
        }

        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (graphicsDevice.isFullScreenSupported()) {
            graphicsDevice.setFullScreenWindow(this);
        } else {
            System.err.println("Le mode plein écran n'est pas pris en charge");
            dispose();
        }

        // Utilisez le panneau personnalisé pour l'arrière-plan
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);
        setLayout(new BorderLayout());
        
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);


        JLabel titleLabel = new JLabel("Mini-jeux");
        titleLabel.setFont(new Font("The Wild Breath of Zelda", Font.BOLD, 25));
        titleLabel.setForeground(new Color(255,255,255,240));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);


        RoundedPanel titlePanel = new RoundedPanel(1,159,222, 160, null);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setMaximumSize(new Dimension(503, 55));
        titlePanel.setBackground(new Color(217, 217, 217, 127));
        titlePanel.add(Box.createHorizontalGlue());
        // titlePanel.add(Box.createHorizontalGlue());
        titlePanel.add(titleLabel);

        JPanel gamHonzPanel = new JPanel();
        gamHonzPanel.setLayout(new BoxLayout(gamHonzPanel, BoxLayout.X_AXIS));
        gamHonzPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        gamHonzPanel.setOpaque(false);
        // gamHonzPanel.setLayout(new FlowLayout());

        JPanel gamHonzPanel2 = new JPanel();
        gamHonzPanel2.setLayout(new BoxLayout(gamHonzPanel2, BoxLayout.X_AXIS));
        gamHonzPanel2.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        gamHonzPanel2.setOpaque(false);

        JPanel gamHonzPanel3 = new JPanel();
        gamHonzPanel3.setLayout(new BoxLayout(gamHonzPanel3, BoxLayout.X_AXIS));
        gamHonzPanel3.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        gamHonzPanel3.setOpaque(false);

        JPanel gamHonzPanel4 = new JPanel();
        gamHonzPanel4.setLayout(new BoxLayout(gamHonzPanel4, BoxLayout.X_AXIS));
        gamHonzPanel4.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        gamHonzPanel4.setOpaque(false);

        JPanel spacPanel = new JPanel();
        spacPanel.setLayout(new BoxLayout(spacPanel, BoxLayout.Y_AXIS));
        spacPanel.setMaximumSize(new Dimension(0, 174));
        spacPanel.setBackground(new Color(0, 0, 0, 0));

        JPanel spacPanel2 = new JPanel();
        spacPanel2.setLayout(new BoxLayout(spacPanel2, BoxLayout.Y_AXIS));
        spacPanel2.setMaximumSize(new Dimension(100, 174));
        spacPanel2.setBackground(new Color(0, 0, 0, 0));

        JPanel spacPanel3 = new JPanel();
        spacPanel3.setLayout(new BoxLayout(spacPanel3, BoxLayout.Y_AXIS));
        spacPanel3.setMaximumSize(new Dimension(100, 174));
        spacPanel3.setBackground(new Color(0, 0, 0, 0));

        JPanel spacPanel4 = new JPanel();
        spacPanel4.setLayout(new BoxLayout(spacPanel4, BoxLayout.Y_AXIS));
        spacPanel4.setMaximumSize(new Dimension(100, 174));
        spacPanel4.setBackground(new Color(0, 0, 0, 0));

        JPanel spacPanel5 = new JPanel();
        spacPanel5.setLayout(new BoxLayout(spacPanel5, BoxLayout.X_AXIS));
        spacPanel5.setMaximumSize(new Dimension(1920, 5));
        spacPanel5.setBackground(new Color(0, 0, 0, 0));

        JPanel spacPanel6 = new JPanel();
        spacPanel6.setLayout(new BoxLayout(spacPanel6, BoxLayout.Y_AXIS));
        spacPanel6.setMaximumSize(new Dimension(100, 174));
        spacPanel6.setBackground(new Color(0, 0, 0, 0));

        JPanel spacPanel7 = new JPanel();
        spacPanel7.setLayout(new BoxLayout(spacPanel7, BoxLayout.Y_AXIS));
        spacPanel7.setMaximumSize(new Dimension(100, 174));
        spacPanel7.setBackground(new Color(0, 0, 0, 0));

        JPanel spacPanel8 = new JPanel();
        spacPanel8.setLayout(new BoxLayout(spacPanel8, BoxLayout.Y_AXIS));
        spacPanel8.setMaximumSize(new Dimension(100, 174));
        spacPanel8.setBackground(new Color(0, 0, 0, 0));

        JPanel spacPanel9 = new JPanel();
        spacPanel9.setLayout(new BoxLayout(spacPanel9, BoxLayout.X_AXIS));
        spacPanel9.setMaximumSize(new Dimension(100, 5));
        spacPanel9.setBackground(new Color(0, 0, 0, 0));

        JPanel spacPanel10 = new JPanel();
        spacPanel10.setLayout(new BoxLayout(spacPanel10, BoxLayout.Y_AXIS));
        spacPanel10.setMaximumSize(new Dimension(100, 174));
        spacPanel10.setBackground(new Color(0, 0, 0, 0));

        JPanel spacPanel11 = new JPanel();
        spacPanel11.setLayout(new BoxLayout(spacPanel11, BoxLayout.Y_AXIS));
        spacPanel11.setMaximumSize(new Dimension(100, 174));
        spacPanel11.setBackground(new Color(0, 0, 0, 0));

        JPanel spacPanel12 = new JPanel();
        spacPanel12.setLayout(new BoxLayout(spacPanel12, BoxLayout.Y_AXIS));
        spacPanel12.setMaximumSize(new Dimension(200, 60));
        spacPanel12.setBackground(new Color(0, 0, 0, 0));

        JPanel spacPanel13 = new JPanel();
        spacPanel13.setLayout(new BoxLayout(spacPanel13, BoxLayout.Y_AXIS));
        spacPanel13.setMaximumSize(new Dimension(160, 10));
        spacPanel13.setBackground(new Color(0, 0, 0, 0));

        JPanel spacPanel14 = new JPanel();
        spacPanel14.setLayout(new BoxLayout(spacPanel14, BoxLayout.Y_AXIS));
        spacPanel14.setMaximumSize(new Dimension(50, 10));
        spacPanel14.setBackground(new Color(0, 0, 0, 0));
        
        JPanel ExitPanel = new JPanel();
        ExitPanel.setLayout(new BoxLayout(ExitPanel, BoxLayout.X_AXIS));
        // ExitPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ExitPanel.setOpaque(false);

        
  
        JButton btnPlayFirst = createButton("Jeu du + ou -", true);
        btnPlayFirst.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        btnPlayFirst.setForeground(new Color(255, 255, 255, 240));
        btnPlayFirst.setContentAreaFilled(false);
        btnPlayFirst.setBorder(BorderFactory.createEmptyBorder());
        btnPlayFirst.setFocusPainted(false);

        RoundedPanel moreOrLessPanel = new RoundedPanel(1, 159, 222, 160, btnPlayFirst);
        moreOrLessPanel.setLayout(new BoxLayout(moreOrLessPanel, BoxLayout.X_AXIS));
        moreOrLessPanel.setMaximumSize(new Dimension(269, 140));
        moreOrLessPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        moreOrLessPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        moreOrLessPanel.add(Box.createVerticalGlue());
        moreOrLessPanel.add(btnPlayFirst);

        

        JButton btnPlayTrueOrFalse = createButton("True Or False", true);
        btnPlayTrueOrFalse.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        btnPlayTrueOrFalse.setForeground(new Color(255, 255, 255, 240));
        btnPlayTrueOrFalse.setContentAreaFilled(false);
        btnPlayTrueOrFalse.setBorder(BorderFactory.createEmptyBorder());
        btnPlayTrueOrFalse.setFocusPainted(false);
        

        RoundedPanel trueOrFalsePanel = new RoundedPanel(1, 159, 222, 160, btnPlayTrueOrFalse);
        trueOrFalsePanel.setLayout(new BoxLayout(trueOrFalsePanel, BoxLayout.X_AXIS));
        trueOrFalsePanel.setMaximumSize(new Dimension(269, 140));
        trueOrFalsePanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        trueOrFalsePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        trueOrFalsePanel.add(Box.createVerticalGlue());
        trueOrFalsePanel.add(btnPlayTrueOrFalse);

        JButton btnHangman = createButton("Jeu du Pendu", true);
        btnHangman.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        btnHangman.setForeground(new Color(255, 255, 255, 240));
        btnHangman.setContentAreaFilled(false);
        btnHangman.setBorder(BorderFactory.createEmptyBorder());
        btnHangman.setFocusPainted(false);

        RoundedPanel hangmanPanel = new RoundedPanel(1, 159, 222, 160, btnHangman);
        hangmanPanel.setLayout(new BoxLayout(hangmanPanel, BoxLayout.X_AXIS));
        hangmanPanel.setMaximumSize(new Dimension(269, 140));
        hangmanPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        hangmanPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        hangmanPanel.add(Box.createVerticalGlue());
        hangmanPanel.add(btnHangman);

        JButton btnMemory = createButton("Memory", true);
        btnMemory.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        btnMemory.setForeground(new Color(255, 255, 255, 240));
        btnMemory.setSize(0, 0);
        btnMemory.setContentAreaFilled(false);
        btnMemory.setBorder(BorderFactory.createEmptyBorder());
        btnMemory.setFocusPainted(false);

        
        RoundedPanel memoryPanel = new RoundedPanel(1, 159, 222, 160, btnMemory);
        memoryPanel.setLayout(new BoxLayout(memoryPanel, BoxLayout.X_AXIS));
        memoryPanel.setMaximumSize(new Dimension(269, 140));
        memoryPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        memoryPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        memoryPanel.add(Box.createVerticalGlue());
        memoryPanel.add(btnMemory);

        JButton btnBlackJack = createButton("BlackJack", true);
        btnBlackJack.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        btnBlackJack.setForeground(new Color(255, 255, 255, 240));
        btnBlackJack.setContentAreaFilled(false);
        btnBlackJack.setBorder(BorderFactory.createEmptyBorder());
        btnBlackJack.setFocusPainted(false);

        RoundedPanel blackJackPanel = new RoundedPanel(1, 159, 222, 160, btnBlackJack);
        blackJackPanel.setLayout(new BoxLayout(blackJackPanel, BoxLayout.X_AXIS));
        blackJackPanel.setMaximumSize(new Dimension(269, 140));
        blackJackPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        blackJackPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        blackJackPanel.add(Box.createVerticalGlue());
        blackJackPanel.add(btnBlackJack);

        JButton btnSudoku = createButton("Sudoku", true);
        btnSudoku.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        btnSudoku.setForeground(new Color(255, 255, 255, 240));
        btnSudoku.setContentAreaFilled(false);
        btnSudoku.setBorder(BorderFactory.createEmptyBorder());
        btnSudoku.setFocusPainted(false);

        RoundedPanel sudokuPanel = new RoundedPanel(1, 159, 222, 160, btnSudoku);
        sudokuPanel.setLayout(new BoxLayout(sudokuPanel, BoxLayout.X_AXIS));
        sudokuPanel.setMaximumSize(new Dimension(269, 140));
        sudokuPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        sudokuPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sudokuPanel.add(Box.createVerticalGlue());
        sudokuPanel.add(btnSudoku);

        JButton btn2048 = createButton("2048", true);
        btn2048.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        btn2048.setForeground(new Color(255, 255, 255, 240));
        btn2048.setContentAreaFilled(false);
        btn2048.setBorder(BorderFactory.createEmptyBorder());
        btn2048.setFocusPainted(false);
    
        RoundedPanel numberPuzzleGamePanel = new RoundedPanel(1, 159, 222, 160, btn2048);
        numberPuzzleGamePanel.setLayout(new BoxLayout(numberPuzzleGamePanel, BoxLayout.X_AXIS));
        numberPuzzleGamePanel.setMaximumSize(new Dimension(269, 140));
        numberPuzzleGamePanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        numberPuzzleGamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        numberPuzzleGamePanel.add(Box.createVerticalGlue());
        numberPuzzleGamePanel.add(btn2048);

        JButton btnSnake = createButton("Snake", true);
        btnSnake.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        btnSnake.setForeground(new Color(255, 255, 255, 240));
        btnSnake.setContentAreaFilled(false);
        btnSnake.setBorder(BorderFactory.createEmptyBorder());
        btnSnake.setFocusPainted(false);

        RoundedPanel snakePanel = new RoundedPanel(1, 159, 222, 160, btnSnake);
        snakePanel.setLayout(new BoxLayout(snakePanel, BoxLayout.X_AXIS));
        snakePanel.setMaximumSize(new Dimension(269, 140));
        snakePanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        snakePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        snakePanel.add(Box.createVerticalGlue());
        snakePanel.add(btnSnake);

        JButton btnFlappy = createButton("Flappy Bird", true);
        btnFlappy.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        btnFlappy.setForeground(new Color(255, 255, 255, 240));
        btnFlappy.setContentAreaFilled(false);
        btnFlappy.setBorder(BorderFactory.createEmptyBorder());
        btnFlappy.setFocusPainted(false);

        RoundedPanel flappyPanel = new RoundedPanel(1, 159, 222, 160, btnFlappy);
        flappyPanel.setLayout(new BoxLayout(flappyPanel, BoxLayout.X_AXIS));
        flappyPanel.setMaximumSize(new Dimension(269, 140));
        flappyPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        flappyPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        flappyPanel.add(Box.createVerticalGlue());
        flappyPanel.add(btnFlappy);

        JButton btnPacMan = createButton("Pac-Man", true);
        btnPacMan.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        btnPacMan.setForeground(new Color(255, 255, 255, 240));
        btnPacMan.setContentAreaFilled(false);
        btnPacMan.setBorder(BorderFactory.createEmptyBorder());
        btnPacMan.setFocusPainted(false);

        RoundedPanel pacManPanel = new RoundedPanel(1, 159, 222, 160, btnPacMan);
        pacManPanel.setLayout(new BoxLayout(pacManPanel, BoxLayout.X_AXIS));
        pacManPanel.setMaximumSize(new Dimension(269, 140));
        pacManPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        pacManPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pacManPanel.add(Box.createVerticalGlue());
        pacManPanel.add(btnPacMan);
        
        JButton btnChess = createButton("Chess", true);
        btnChess.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        btnChess.setForeground(new Color(255, 255, 255, 240));
        btnChess.setContentAreaFilled(false);
        btnChess.setBorder(BorderFactory.createEmptyBorder());

        RoundedPanel chessPanel = new RoundedPanel(1, 159, 222, 160, btnChess);
        chessPanel.setLayout(new BoxLayout(chessPanel, BoxLayout.X_AXIS));
        chessPanel.setMaximumSize(new Dimension(269, 140));
        chessPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        chessPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        chessPanel.add(Box.createVerticalGlue());
        chessPanel.add(btnChess);

        centerPanel.add(spacPanel13);
        centerPanel.add(titlePanel);
        centerPanel.add(gamHonzPanel);
        gamHonzPanel.add(moreOrLessPanel);
        gamHonzPanel.add(spacPanel2);
        gamHonzPanel.add(trueOrFalsePanel);
        gamHonzPanel.add(spacPanel3);
        gamHonzPanel.add(hangmanPanel);
        gamHonzPanel.add(spacPanel4);
        gamHonzPanel.add(memoryPanel);
        gamHonzPanel.add(spacPanel);
        centerPanel.add(spacPanel5);
        centerPanel.add(gamHonzPanel2);
        gamHonzPanel2.add(blackJackPanel);
        gamHonzPanel2.add(spacPanel6);
        gamHonzPanel2.add(sudokuPanel);
        gamHonzPanel2.add(spacPanel7);
        gamHonzPanel2.add(flappyPanel);
        gamHonzPanel2.add(spacPanel8);
        gamHonzPanel2.add(numberPuzzleGamePanel);
        centerPanel.add(spacPanel9);
        centerPanel.add(gamHonzPanel3);
        // gamHonzPanel3.add(spacPanel12);
        gamHonzPanel3.add(pacManPanel);
        gamHonzPanel3.add(spacPanel10);
        gamHonzPanel3.add(snakePanel);
        gamHonzPanel3.add(spacPanel11);
        gamHonzPanel3.add(chessPanel);
        centerPanel.add(gamHonzPanel4);
        gamHonzPanel4.add(ExitPanel);

        add(centerPanel);
    
        try {
            BufferedImage originalImage = ImageIO.read(new File("./image/lutin zelda noir exit.png"));
            BufferedImage hoverImage = ImageIO.read(new File("./image/lutin zelda exit.png"));

            int normalWidth = 100;
            int normalHeight = 100;
            int hoverWidth = 100; 
            int hoverHeight = 100;

            Image scaledNormalImage = originalImage.getScaledInstance(normalWidth, normalHeight, Image.SCALE_SMOOTH);
            BufferedImage resizedNormalImage = new BufferedImage(normalWidth, normalHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2dNormal = resizedNormalImage.createGraphics();
            g2dNormal.drawImage(scaledNormalImage, 0, 0, null);
            g2dNormal.dispose();

            Image scaledHoverImage = hoverImage.getScaledInstance(hoverWidth, hoverHeight, Image.SCALE_SMOOTH);
            BufferedImage resizedHoverImage = new BufferedImage(hoverWidth, hoverHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2dHover = resizedHoverImage.createGraphics();
            g2dHover.drawImage(scaledHoverImage, 0, 0, null);
            g2dHover.dispose();

            Icon normalIcon = new ImageIcon(resizedNormalImage);
            Icon hoverIcon = new ImageIcon(resizedHoverImage);

      
            JButton btnExit = new JButton(normalIcon);
            btnExit.setBorderPainted(false);
            btnExit.setFocusPainted(false);
            btnExit.setContentAreaFilled(false);
            btnExit.setText("Exit");
            btnExit.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 50));
            btnExit.setForeground(new Color(151, 121, 89 , 255));
            btnExit.setVerticalTextPosition(SwingConstants.BOTTOM);
            btnExit.setHorizontalTextPosition(SwingConstants.CENTER); 

            btnExit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    btnExit.setIcon(hoverIcon);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    btnExit.setIcon(normalIcon);
                }
            });

            btnExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose(); 
                }
            });
            ExitPanel.add(btnExit);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        setVisible(true);
        playSoundLoop("./sound/music.wav");
    }

    private JButton createButton(String text, boolean enableAction) { 
        JButton button = new JButton(text);
        button.setEnabled(enableAction);
        button.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 14));
        button.setBackground(new Color(217, 217, 217, 127));
        button.setMaximumSize(new Dimension(215, 38));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            if (enableAction) {
                if (text.equals("True Or False")) {
                    button.addActionListener(e -> openTrueOrFalse());
                } else if (text.equals("Jeu du + ou -")) {
                    button.addActionListener(e -> openMoreOrLess());
                } else if (text.equals("2048")) {
                    button.addActionListener(e -> openGame());
                } else if (text.equals("Jeu du Pendu")) {
                    button.addActionListener(e -> openHangman());
                } else if (text.equals("Memory")) {
                    button.addActionListener(e -> openMemory());
                } else if (text.equals("BlackJack")) {
                    button.addActionListener(e -> openBlackjack());
                }else if (text.equals("Snake")){
                    button.addActionListener(e -> openSnake());
                }else if (text.equals("Pac-Man")){
                    button.addActionListener(e -> openPacMan());
                }else if (text.equals("Chess")){
                    button.addActionListener(e -> openChess());
                }
                 /*else if (text.equals("Sudoku")) {
                    button.addActionListener(e -> new Sudoku());
                } else if (text.equals("Snake")) {
                    button.addActionListener(e -> new Snake());
                } else if (text.equals("Flappy Bird")) {
                    button.addActionListener(e -> new FlappyBird());
                }*/
            }
        return button;
    }

    private void openBlackjack() { //Ouvre le jeu BlackJack
        dispose();
        Blackjack blackJack = new Blackjack();

        // blackJack.setVisible(true);
    }
    private void openChess(){
        dispose();
        GridChess chess = new GridChess();
        // chess.setVisible(true);
    }
    private void openPacMan(){
        dispose();
        PacManGame pacman = new PacManGame();
        // pacman.setVisible(true);
    }
    private void openSnake(){
        dispose();
        Grid snake = new Grid();
        // snake.setVisible(true);

    }
    private void openMemory() { //Ouvre le jeu Memory
        dispose();
        Memory memory = new Memory();
        memory.setVisible(true);
    }    

    private void openHangman() { //Ouvre le jeu du pendu
        dispose();
        Hangman hangman = new Hangman();
        hangman.setVisible(true);
    }

    private void openGame() { //Ouvre le jeu 2048
        dispose();
        NumberPuzzleGame game = new NumberPuzzleGame();
        game.setVisible(true);
    }

    private void openMoreOrLess() { 
        dispose();
        MoreOrLess moreOrLess = new MoreOrLess();
        moreOrLess.setVisible(true);
    }

    private void openTrueOrFalse() { 
        dispose();
        TrueOrFalse TrueOrFalse = new TrueOrFalse();
        TrueOrFalse.setVisible(true);
    }

    public class RoundedImagePanel extends JPanel {
        private BufferedImage image;
        private int cornerRadius;
    
        public RoundedImagePanel(String imagePath, int cornerRadius) {
            this.cornerRadius = cornerRadius;
            try {
                image = ImageIO.read(new File(imagePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
            double widthRatio = (double) getWidth() / image.getWidth();
            double heightRatio = (double) getHeight() / image.getHeight();
            double ratio = Math.min(widthRatio, heightRatio);  
    
            int drawWidth = (int) (image.getWidth() * ratio);
            int drawHeight = (int) (image.getHeight() * ratio);
    
            int drawX = (getWidth() - drawWidth) / 2;
            int drawY = (getHeight() - drawHeight) / 2;
    
            if (cornerRadius > 0) {
                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(drawX, drawY, drawWidth, drawHeight, cornerRadius, cornerRadius);
                g2d.setClip(roundedRectangle);
            }
    
            g2d.drawImage(image, drawX, drawY, drawWidth, drawHeight, null);
            g2d.dispose();
        }
    }
    


    public class RoundedPanel extends JPanel {
        private static final int ARC_WIDTH = 25;
        private static final int ARC_HEIGHT = 25;
        private Color backgroundColor;
        private int opacity;
        private boolean isHovered = false;
        private JButton linkedButton;  

        public RoundedPanel(int r, int g, int b, int opacity, JButton button) {
            super();
            this.backgroundColor = new Color(r, g, b, opacity);
            this.opacity = opacity;
            this.linkedButton = button;  
            setOpaque(false);
            setupMouseEvents();
        }

        private void setupMouseEvents() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    isHovered = true;
                    backgroundColor = new Color(98, 209, 255, 255);  // couleur de survol
                    repaint();
                    // linkedButton.setForeground(Color.WHITE);  
                    linkedButton.setFont(new Font("The Wild Breath of Zelda", Font.BOLD, 38));
                    playSound("./sound/hover.wav");
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    isHovered = false;
                    backgroundColor = new Color(1, 159, 222, 160);  // couleur initiale
                    repaint();
                    // linkedButton.setForeground(new Color(255, 255, 255, 240));  // réinitialiser la couleur du bouton
                    linkedButton.setFont(new Font("The Wild Breath of Zelda", Font.BOLD, 30));
                }
                @Override
                public void mouseClicked(MouseEvent e) {
                    linkedButton.doClick();  
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(backgroundColor);
            g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT);
        }
    }
    
    private class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Dessinez l'image en arrière-plan
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    private void playSound(String filePath) {
        try {

            URL url = new URL(filePath);
            Clip clip = AudioSystem.getClip();
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playSoundLoop(String fileUrl) {
        try {
            URL url = new URL(fileUrl);
            Clip clip = AudioSystem.getClip();
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) { 
        db = new DataBaseManager();
        db.CreateDBBlackJack();
        db.test();
        db.CreateDBHangman();
        db.CreateDBTrueFalse();
        SwingUtilities.invokeLater(() -> new SelectionWindow());
    }
}    
