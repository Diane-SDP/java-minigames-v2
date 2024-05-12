package Hangman;

import javax.swing.*;
import Menu.SelectionWindow;
import NumberPuzzleGame.NumberPuzzleGame.RoundedPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DataBase.DataBaseManager;
public class Hangman extends JFrame implements ActionListener {

    private List<String> words = new ArrayList<String>();
    private List<String> Try = new ArrayList<String>();
    private String word;
    private StringBuilder hiddenWord;
    private int attemptsLeft;
    private JTextField guessText;
    private JLabel hiddenWordLabel;
    private JButton guessButton;
    private JLabel TryText;
    private JLabel TitleGame;
    private JButton StartGame;
    private JLabel  HangMan;
    private int Score;
    private Image backgroundImage;
    private RoundedPanel LeftPart;
    public static DataBaseManager db = SelectionWindow.db;
    public Hangman() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));
        Score = 0;
        DisplayMenu();
        setVisible(true);
    }

    private void DisplayMenu(){
        ImageIcon icon = new ImageIcon("./Hangman/HangMan/wallpaper.png");
        backgroundImage = icon.getImage();

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);
        setLayout(new BorderLayout());

        setTitle("Hangman");
        setSize(1920,1080);
        setLocationRelativeTo(null);


        JPanel centerJPanel = new JPanel();
        centerJPanel.setLayout(new BoxLayout(centerJPanel, BoxLayout.Y_AXIS));
        centerJPanel.setOpaque(false);


        JPanel honzPanel = new JPanel();
        honzPanel.setLayout(new BoxLayout(honzPanel, BoxLayout.X_AXIS));
        honzPanel.setOpaque(false);

        JPanel marginbtwtopandbottomPanel = new JPanel();
        marginbtwtopandbottomPanel.setMaximumSize(new Dimension(20, 60));
        marginbtwtopandbottomPanel.setBackground(new Color(0, 0, 0, 0));

        JPanel marginbtwleftnrightPanel = new JPanel();
        marginbtwleftnrightPanel.setMaximumSize(new Dimension(150, 10));
        marginbtwleftnrightPanel.setBackground(new Color(0, 0, 0, 0));

        JPanel marginTitle = new JPanel();
        marginTitle.setMaximumSize(new Dimension(20, 30));
        marginTitle.setBackground(new Color(0, 0, 0, 0));


        RoundedPanel LeftPart = new RoundedPanel(190, 200, 210, 200);
        LeftPart.setLayout(new BoxLayout(LeftPart, BoxLayout.Y_AXIS));
        LeftPart.setMaximumSize(new Dimension(600, 600));
        LeftPart.add(marginbtwtopandbottomPanel);
        LeftPart.add(marginTitle);

        HangMan = new JLabel();
        HangMan.setMaximumSize(new Dimension(400, 600));
        setPieceImage("./Hangman/HangMan/darkHangman.png", HangMan,400,700);

        JPanel marginDessinsPanel = new JPanel();
        marginDessinsPanel.setMaximumSize(new Dimension(10, 15));
        marginDessinsPanel.setBackground(new Color(0, 0, 0, 0));

        JPanel honzDessiPanel = new JPanel();
        honzDessiPanel.setLayout(new BoxLayout(honzDessiPanel, BoxLayout.X_AXIS));
        honzDessiPanel.setOpaque(false);

        RoundedPanel RightPart = new RoundedPanel(190, 200, 210, 200);
        RightPart.setLayout(new BoxLayout(RightPart, BoxLayout.Y_AXIS));
        RightPart.setMaximumSize(new Dimension(600, 600));
        RightPart.add(honzDessiPanel);
        honzDessiPanel.add(marginDessinsPanel);
        honzDessiPanel.add(HangMan);

        RoundedPanel TitlePanel = new RoundedPanel(190, 200, 210, 200);
        TitlePanel.setLayout(new BoxLayout(TitlePanel, BoxLayout.X_AXIS));

        JLabel TitleGame = new JLabel("Hangman");
        TitleGame.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 60));
        TitleGame.setMaximumSize(new Dimension(400, 100));
        TitleGame.setForeground(Color.BLACK);
        TitleGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        TitleGame.setHorizontalAlignment(JLabel.CENTER);
        TitlePanel.add(TitleGame);

        ImageIcon iconMenu = new ImageIcon("./TrueOrFalse/image/triforce.png");
        JButton Menu = new JButton("Retour");
        Menu.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        Menu.setContentAreaFilled(false);
        Menu.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        Menu.setForeground(Color.WHITE);
        Menu.setIcon(iconMenu);
        Menu.setVerticalTextPosition(SwingConstants.BOTTOM);
        Menu.setHorizontalTextPosition(SwingConstants.CENTER);

        JPanel honzPanel2 = new JPanel();
        honzPanel2.setLayout(new BoxLayout(honzPanel2, BoxLayout.X_AXIS));
        honzPanel2.setOpaque(false);

        Menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(() -> new SelectionWindow());
            }
        });



        JPanel marPanel = new JPanel();
        marPanel.setMaximumSize(new Dimension(500, 60));
        marPanel.setBackground(new Color(0, 0, 0, 0));

        JPanel margiPanel = new JPanel();
        margiPanel.setMaximumSize(new Dimension(600, 60));
        margiPanel.setBackground(new Color(0, 0, 0, 0));
        centerJPanel.add(honzPanel2);
        honzPanel2.add(Menu);
        honzPanel2.add(marPanel);
        honzPanel2.add(TitlePanel);
        honzPanel2.add(margiPanel);
        centerJPanel.add(honzPanel);
        honzPanel.add(LeftPart);
        honzPanel.add(marginbtwleftnrightPanel);
        honzPanel.add(RightPart);
        
        add(centerJPanel);

        setVisible(true);
        JPanel StartPanel = new JPanel();
        StartPanel.setLayout(new BoxLayout(StartPanel, BoxLayout.X_AXIS));
        StartPanel.setOpaque(false);
        StartPanel.setBackground(Color.GREEN);

        StartGame = new JButton("Jouer");
        StartGame.setMaximumSize(new Dimension(300, 80));
        StartGame.setFont(new Font("The Wild Breath of Zelda", Font.BOLD, 70));
        StartGame.setForeground(Color.BLACK);
        StartGame.setFocusPainted(false);
        StartGame.setBorderPainted(false);
        StartGame.setContentAreaFilled(false);

        StartPanel.add(StartGame);

        JPanel marginStart = new JPanel();
        marginStart.setMaximumSize(new Dimension(30, 70));
        marginStart.setBackground(new Color(0, 0, 0, 0));

        JPanel AddPanel = new JPanel();
        AddPanel.setLayout(new BoxLayout(AddPanel, BoxLayout.X_AXIS));
        AddPanel.setOpaque(false);

        JButton AddWords = new JButton("Ajouter un mot");
        AddWords.setMaximumSize(new Dimension(500, 80));
        AddWords.setFont(new Font("The Wild Breath of Zelda", Font.BOLD, 70));
        AddWords.setForeground(Color.BLACK);
        AddWords.setBackground(new Color(190, 200, 210, 255));
        AddWords.setFocusPainted(false);
        AddWords.setBorderPainted(false);
        AddWords.setContentAreaFilled(false);

        AddPanel.add(AddWords);

        JPanel marginAdd = new JPanel();
        marginAdd.setMaximumSize(new Dimension(30, 100));
        marginAdd.setBackground(new Color(0, 0, 0, 0));


        LeftPart.add(marginStart);
        LeftPart.add(StartPanel);
        LeftPart.add(marginAdd);
        LeftPart.add(AddPanel);

        StartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(marginStart);
                remove(StartPanel);
                remove(marginAdd);
                remove(AddPanel);
                remove(Menu);
                DisplayGame();
                revalidate();
                repaint();
            }
        });
        AddWords.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(marginStart);
                remove(StartPanel);
                remove(marginAdd);
                remove(AddPanel);
                remove(Menu);
                DisplayAddWord();
                revalidate();
                repaint();
            }
        });
    }


    private void DisplayAddWord(){

        ImageIcon icon = new ImageIcon("./Hangman/HangMan/wallpaper.png");
        backgroundImage = icon.getImage();

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);
        setLayout(new BorderLayout());

        setTitle("Hangman");
        setSize(1920,1080);
        setLocationRelativeTo(null);

        JPanel centerJPanel = new JPanel();
        centerJPanel.setLayout(new BoxLayout(centerJPanel, BoxLayout.Y_AXIS));
        centerJPanel.setOpaque(false);


        JPanel honzPanel = new JPanel();
        honzPanel.setLayout(new BoxLayout(honzPanel, BoxLayout.X_AXIS));
        honzPanel.setOpaque(false);

        JPanel marginbtwtopandbottomPanel = new JPanel();
        marginbtwtopandbottomPanel.setMaximumSize(new Dimension(20, 60));
        marginbtwtopandbottomPanel.setBackground(new Color(0, 0, 0, 0));

        JPanel marginbtwleftnrightPanel = new JPanel();
        marginbtwleftnrightPanel.setMaximumSize(new Dimension(150, 10));
        marginbtwleftnrightPanel.setBackground(new Color(0, 0, 0, 0));

        JPanel marginTitle = new JPanel();
        marginTitle.setMaximumSize(new Dimension(20, 30));
        marginTitle.setBackground(new Color(0, 0, 0, 0));


        RoundedPanel LeftPart = new RoundedPanel(190, 200, 210, 200);
        LeftPart.setLayout(new BoxLayout(LeftPart, BoxLayout.Y_AXIS));
        LeftPart.setMaximumSize(new Dimension(600, 600));
        LeftPart.add(marginbtwtopandbottomPanel);
        LeftPart.add(marginTitle);

        HangMan = new JLabel();
        HangMan.setMaximumSize(new Dimension(400, 600));
        setPieceImage("./Hangman/HangMan/darkHangman.png", HangMan,400,700);

        JPanel marginDessinsPanel = new JPanel();
        marginDessinsPanel.setMaximumSize(new Dimension(10, 15));
        marginDessinsPanel.setBackground(new Color(0, 0, 0, 0));

        JPanel honzDessiPanel = new JPanel();
        honzDessiPanel.setLayout(new BoxLayout(honzDessiPanel, BoxLayout.X_AXIS));
        honzDessiPanel.setOpaque(false);

        RoundedPanel RightPart = new RoundedPanel(190, 200, 210, 200);
        RightPart.setLayout(new BoxLayout(RightPart, BoxLayout.Y_AXIS));
        RightPart.setMaximumSize(new Dimension(600, 600));
        RightPart.add(honzDessiPanel);
        honzDessiPanel.add(marginDessinsPanel);
        honzDessiPanel.add(HangMan);

        RoundedPanel TitlePanel = new RoundedPanel(190, 200, 210, 200);
        TitlePanel.setLayout(new BoxLayout(TitlePanel, BoxLayout.X_AXIS));

        JLabel TitleGame = new JLabel("Hangman");
        TitleGame.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 60));
        TitleGame.setMaximumSize(new Dimension(400, 100));
        TitleGame.setForeground(Color.BLACK);
        TitleGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        TitleGame.setHorizontalAlignment(JLabel.CENTER);
        TitlePanel.add(TitleGame);

        ImageIcon iconMenu = new ImageIcon("./TrueOrFalse/image/triforce.png");
        JButton Menu = new JButton("Retour");
        Menu.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        Menu.setContentAreaFilled(false);
        Menu.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        Menu.setForeground(Color.WHITE);
        Menu.setIcon(iconMenu);
        Menu.setVerticalTextPosition(SwingConstants.BOTTOM);
        Menu.setHorizontalTextPosition(SwingConstants.CENTER);

        JPanel honzPanel2 = new JPanel();
        honzPanel2.setLayout(new BoxLayout(honzPanel2, BoxLayout.X_AXIS));
        honzPanel2.setOpaque(false);
        JPanel marPanel = new JPanel();
        marPanel.setMaximumSize(new Dimension(500, 60));
        marPanel.setBackground(new Color(0, 0, 0, 0));
        JPanel margiPanel = new JPanel();
        margiPanel.setMaximumSize(new Dimension(600, 60));
        margiPanel.setBackground(new Color(0, 0, 0, 0));
        centerJPanel.add(honzPanel2);
        honzPanel2.add(Menu);
        honzPanel2.add(marPanel);
        honzPanel2.add(TitlePanel);
        honzPanel2.add(margiPanel);
        centerJPanel.add(honzPanel);
        honzPanel.add(LeftPart);
        honzPanel.add(marginbtwleftnrightPanel);
        honzPanel.add(RightPart);

        add(centerJPanel);
        setVisible(true);

        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new BoxLayout(submitPanel, BoxLayout.X_AXIS));
        submitPanel.setOpaque(false);
        submitPanel.setBackground(Color.GREEN);


        JButton submit = new JButton("Valider");
        submit.setMaximumSize(new Dimension(300, 80));
        submit.setFont(new Font("The Wild Breath of Zelda", Font.BOLD, 70));
        submit.setForeground(Color.BLACK);
        submit.setBackground(new Color(190, 200, 210, 255));
        submit.setFocusPainted(false);
        submit.setBorderPainted(false);
        submit.setContentAreaFilled(true);

        submitPanel.add(submit);

        JTextField inputPanel = new JTextField();
        inputPanel.setMaximumSize(new Dimension(1500,80));
        inputPanel.setFont(new Font("The Wild Breath of Zelda", Font.BOLD, 40));
        inputPanel.setForeground(Color.BLACK);
        inputPanel.setBackground(new Color(190, 200, 210, 255));
        inputPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        inputPanel.setHorizontalAlignment(JTextField.CENTER);

        JPanel marginInput = new JPanel();
        marginInput.setMaximumSize(new Dimension(30, 100));
        marginInput.setBackground(new Color(0, 0, 0, 0));

        JPanel marginSubmit = new JPanel();
        marginSubmit.setMaximumSize(new Dimension(30, 50));
        marginSubmit.setBackground(new Color(0, 0, 0, 0));


        LeftPart.add(marginInput);
        LeftPart.add(inputPanel);
        LeftPart.add(marginSubmit);
        LeftPart.add(submitPanel);
        
        setVisible(true);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.AddWord(inputPanel.getText());
            }
        });
        Menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayMenu();
                revalidate();
                repaint();
            }
        });
    }

    
    private void DisplayGame(){
        selectWord();
        ImageIcon icon = new ImageIcon("./Hangman/HangMan/wallpaper.png");
        backgroundImage = icon.getImage();
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);
        setLayout(new BorderLayout());

        setTitle("Hangman");
        setSize(1920,1080);
        setLocationRelativeTo(null);


        JPanel centerJPanel = new JPanel();
        centerJPanel.setLayout(new BoxLayout(centerJPanel, BoxLayout.Y_AXIS));
        centerJPanel.setOpaque(false);


        JPanel honzPanel = new JPanel();
        honzPanel.setLayout(new BoxLayout(honzPanel, BoxLayout.X_AXIS));
        honzPanel.setOpaque(false);

        hiddenWordLabel = new JLabel(getHiddenWord());
        hiddenWordLabel.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 60));
        guessText = new JTextField(10);
        guessText.setMaximumSize(new Dimension(1500,80));
        guessText.setFont(new Font("The Wild Breath of Zelda", Font.BOLD, 40));
        guessText.setForeground(Color.BLACK);
        guessText.setBackground(new Color(190, 200, 210, 255));
        guessText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        guessText.setHorizontalAlignment(JTextField.CENTER);

        guessButton = new JButton("Devinez");
        guessButton.setMaximumSize(new Dimension(1500, 80));
        guessButton.setFont(new Font("The Wild Breath of Zelda", Font.BOLD, 30));
        guessButton.setForeground(Color.BLACK);
        guessButton.setBackground(new Color(190, 200, 210, 255));
        guessButton.setFocusPainted(false);
        guessButton.setBorderPainted(false);
        guessButton.setContentAreaFilled(true);
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String guessLetter = guessText.getText().toLowerCase();
                checkGuess(guessLetter);
                guessText.setText("");
            }
        });

        TryText = new JLabel();
        TryText.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 40));
        TryText.setForeground(Color.white);

        JLabel Try = new JLabel("vos essais :");
        Try.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 40));
        Try.setForeground(Color.white);

        JPanel marginbtwtopandbottomPanel = new JPanel();
        marginbtwtopandbottomPanel.setMaximumSize(new Dimension(20, 60));
        marginbtwtopandbottomPanel.setBackground(new Color(0, 0, 0, 0));

        JPanel marginbtwleftnrightPanel = new JPanel();
        marginbtwleftnrightPanel.setMaximumSize(new Dimension(150, 10));
        marginbtwleftnrightPanel.setBackground(new Color(0, 0, 0, 0));

        JPanel marginTitle = new JPanel();
        marginTitle.setMaximumSize(new Dimension(20, 30));
        marginTitle.setBackground(new Color(0, 0, 0, 0));

        JPanel marginTry = new JPanel();
        marginTry.setMaximumSize(new Dimension(20, 60));
        marginTry.setBackground(new Color(0, 0, 0, 0));

        JPanel marginHidden = new JPanel();
        marginHidden.setMaximumSize(new Dimension(20, 80));
        marginHidden.setBackground(new Color(0, 0, 0, 0));


        LeftPart = new RoundedPanel(190, 200, 210, 200);
        LeftPart.setLayout(new BoxLayout(LeftPart, BoxLayout.Y_AXIS));
        LeftPart.setMaximumSize(new Dimension(600, 600));
        LeftPart.add(marginHidden);
        LeftPart.add(hiddenWordLabel);
        LeftPart.add(marginTry);
        LeftPart.add(TryText);
        LeftPart.add(marginbtwtopandbottomPanel);
        LeftPart.add(guessText);
        LeftPart.add(marginTitle);
        LeftPart.add(guessButton);

        HangMan = new JLabel();
        HangMan.setMaximumSize(new Dimension(400, 600));

        JPanel marginDessinsPanel = new JPanel();
        marginDessinsPanel.setMaximumSize(new Dimension(10, 15));
        marginDessinsPanel.setBackground(new Color(0, 0, 0, 0));

        JPanel honzDessiPanel = new JPanel();
        honzDessiPanel.setLayout(new BoxLayout(honzDessiPanel, BoxLayout.X_AXIS));
        honzDessiPanel.setOpaque(false);

        RoundedPanel RightPart = new RoundedPanel(190, 200, 210, 200);
        RightPart.setLayout(new BoxLayout(RightPart, BoxLayout.Y_AXIS));
        RightPart.setMaximumSize(new Dimension(600, 600));
        RightPart.add(honzDessiPanel);
        honzDessiPanel.add(marginDessinsPanel);
        honzDessiPanel.add(HangMan);

        RoundedPanel TitlePanel = new RoundedPanel(190, 200, 210, 200);
        TitlePanel.setLayout(new BoxLayout(TitlePanel, BoxLayout.X_AXIS));

        TitleGame = new JLabel("Hangman");
        TitleGame.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 60));
        TitleGame.setMaximumSize(new Dimension(400, 100));
        TitleGame.setForeground(Color.BLACK);
        TitleGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        TitleGame.setHorizontalAlignment(JLabel.CENTER);
        TitlePanel.add(TitleGame);

        ImageIcon iconMenu = new ImageIcon("./TrueOrFalse/image/triforce.png");
        JButton Menu = new JButton("Retour");
        Menu.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        Menu.setContentAreaFilled(false);
        Menu.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        Menu.setForeground(Color.WHITE);
        Menu.setIcon(iconMenu);
        Menu.setVerticalTextPosition(SwingConstants.BOTTOM);
        Menu.setHorizontalTextPosition(SwingConstants.CENTER);

        JPanel honzPanel2 = new JPanel();
        honzPanel2.setLayout(new BoxLayout(honzPanel2, BoxLayout.X_AXIS));
        honzPanel2.setOpaque(false);

        Menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(centerJPanel);
                remove(Menu);
                remove(LeftPart);
                remove(RightPart);
                DisplayMenu();
                revalidate();
                repaint();
            }
        });



        JPanel marPanel = new JPanel();
        marPanel.setMaximumSize(new Dimension(500, 60));
        marPanel.setBackground(new Color(0, 0, 0, 0));

        JPanel margiPanel = new JPanel();
        margiPanel.setMaximumSize(new Dimension(600, 60));
        margiPanel.setBackground(new Color(0, 0, 0, 0));
        // centerJPanel.add(Menu);
        // centerJPanel.add(Menu);
        centerJPanel.add(honzPanel2);
        honzPanel2.add(Menu);
        honzPanel2.add(marPanel);
        honzPanel2.add(TitlePanel);
        honzPanel2.add(margiPanel);
        centerJPanel.add(honzPanel);
        honzPanel.add(LeftPart);
        honzPanel.add(marginbtwleftnrightPanel);
        honzPanel.add(RightPart);
        
        add(centerJPanel);

        setVisible(true);

    }

    public void selectWord() {
        Random randomNumbers = new Random();
        if (words.size() == 0) {
            words.add("legende");
            words.add("ganondorf");
            words.add("zelda");
        }
        int rd = randomNumbers.nextInt(words.size());
        word = words.get(rd);
        hiddenWord = new StringBuilder(word.replaceAll(".", "_  "));
        attemptsLeft = 7;
    }
    public static void setPieceImage(String imagePath, JLabel targetButton, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage();
        if (image.getWidth(null) == -1) {
            return;
        }
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        if (image != null) {
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JLabel label = new JLabel(scaledIcon);
            targetButton.setLayout(new BorderLayout());
            targetButton.add(label, BorderLayout.CENTER);

            targetButton.revalidate();
            targetButton.repaint();
        }
    }

    public String getHiddenWord() {
        return hiddenWord.toString();
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }


    public void disableInput() {
        guessText.setEnabled(false);
        guessButton.setEnabled(false);
    }


    public void checkGuess(String guessLetter) {
        if (guessLetter.length() != 1 && guessLetter.length() != 0) {
            if (word.equals(guessLetter)) {
                Score++;
                hiddenWordLabel.setText(word);
                TitleGame.setText("Bravo !");
                disableInput();
            } else {
                attemptsLeft--;
                removePieceImage(HangMan);
                setPieceImage("./Hangman/HangMan/etape"+String.valueOf( 7-attemptsLeft)+".png", HangMan,200,400);
            }
        } else if (guessLetter.length() != 0){
            Try.add(guessLetter);
            char guessedLetter = guessLetter.charAt(0);
            boolean found = false;

            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guessedLetter) {
                    hiddenWord.setCharAt(3*i, guessedLetter);
                    found = true;
                }
            }

            
            if (!found) {
                attemptsLeft--;
                removePieceImage(HangMan);
                setPieceImage("./Hangman/HangMan/etape"+String.valueOf( 7-attemptsLeft)+".png", HangMan,400,600);
            }
            hiddenWordLabel.setText(getHiddenWord());

            if (getHiddenWord().replaceAll("\\s", "").equals(word)) {
                Score++;
                TitleGame.setText("Bravo !");
                disableInput();
            }
        }
        String strTry ="";
        for(int i = 0 ; i < Try.size();i++){
            if (i+1 < Try.size()){
                strTry += Try.get(i)+ "  " ;
            }else {
                strTry += Try.get(i) ;
            }

        }
        TryText.setText("Vos essais : "+strTry);
        if (attemptsLeft == 0) {
            TitleGame.setText("Perdu !");
            Score = 0;
            disableInput();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String guessLetter = guessText.getText().toLowerCase();
        checkGuess(guessLetter);
        guessText.setText("");
    }
    public void removePieceImage(JLabel button) {
        button.removeAll();
        button.revalidate();
        button.repaint();
    }
    private class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Dessinez l'image en arrière-plan
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Hangman());
    }
}