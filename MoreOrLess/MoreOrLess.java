package MoreOrLess;

import javax.imageio.ImageIO;
import javax.swing.*;

import Menu.SelectionWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class MoreOrLess extends JFrame {
    private int nbGuess = 0;
    private int randomNumber = randomInteger(0, 1000);
    private int score;
    private JTextField guessText;
    private JLabel status;
    private JButton guessButton;
    private BackgroundPanel backgroundPanel;

    public MoreOrLess() {
        super("Jeu du Plus ou Moins");
        score = 0;
        initComponents();
    }

    private int randomInteger(int min, int max) {
        var random = Math.floor(Math.random() * (max - min + 1)) + min;
        System.out.println("Nombre aléatoire: " + random);
        return (int) random;
        
    }

    private void initComponents() {
        Image backgroundImage = new ImageIcon("./image/moreorless.gif").getImage();
        backgroundPanel = new BackgroundPanel(backgroundImage);
        backgroundPanel.setLayout(new GridBagLayout()); 
    
        guessText = new JTextField(10);
        guessText.setFont(new Font("The Wild Breath of Zelda", Font.BOLD, 20));
        guessText.setForeground(Color.BLACK);
        guessText.setBackground(new Color(217, 217, 217, 255));
    
        guessButton = new JButton("Devinez !");
        guessButton.setFont(new Font("The Wild Breath of Zelda", Font.BOLD, 20));
        guessButton.setForeground(Color.BLACK);
        guessButton.setBackground(new Color(217, 217, 217, 255));
        guessButton.setFocusPainted(false);
        guessButton.setBorderPainted(false);
        guessButton.setContentAreaFilled(true);
    
        status = new JLabel("Enter a number and guess");
        status.setForeground(Color.WHITE);
        status.setFont(new Font("The Wild Breath of Zelda", Font.BOLD, 20));
    
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String guess = guessText.getText();
                checkGuess(guess);
                guessText.setText("");
            }
        });
    
        //Merci chatgpt pour le code ci-dessous
        // Création d'un GridBagConstraints pour ajuster le placement
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Le composant est le dernier de sa ligne.
        gbc.anchor = GridBagConstraints.CENTER; // Cette ligne centrer les composants.
        gbc.fill = GridBagConstraints.HORIZONTAL; // Étendre horizontalement
        // gbc.insets = new Insets(10, 0, 10, 0); // Marges externes pour l'esthétique

        JPanel margiPanel = new JPanel();
        margiPanel.setPreferredSize(new Dimension(100, 20));
        margiPanel.setBackground(new Color(0, 0, 0, 0));

        JPanel margiPanel2 = new JPanel();
        margiPanel2.setPreferredSize(new Dimension(100, 20));
        margiPanel2.setBackground(new Color(0, 0, 0, 0));

        JPanel margiPanel3 = new JPanel();
        margiPanel3.setPreferredSize(new Dimension(100, 150));
        margiPanel3.setBackground(new Color(0, 0, 0, 0));

        JButton NewGame = new JButton("Nouvelle Partie");
        NewGame.setFont(new Font("The Wild Breath of Zelda", Font.BOLD, 20));
        NewGame.setForeground(Color.BLACK);
        NewGame.setBackground(new Color(217, 217, 217, 255));
        NewGame.setFocusPainted(false);
        NewGame.setBorderPainted(false);
        NewGame.setContentAreaFilled(true);
        NewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomNumber = randomInteger(0, 1000);
                nbGuess = 0;
                status.setText("Enter a number and guess");
            }
        });

        JButton Quit = new JButton("Quitter");
        Quit.setFont(new Font("The Wild Breath of Zelda", Font.BOLD, 20));
        Quit.setForeground(Color.BLACK);
        Quit.setBackground(new Color(217, 217, 217, 255));
        Quit.setFocusPainted(false);
        Quit.setBorderPainted(false);
        Quit.setContentAreaFilled(true);
        Quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(() -> new SelectionWindow());
            }
        });

        JPanel margiPanel4 = new JPanel();
        margiPanel4.setPreferredSize(new Dimension(100, 20));
        margiPanel4.setBackground(new Color(0, 0, 0, 0));


        backgroundPanel.add(NewGame, gbc);
        backgroundPanel.add(margiPanel4, gbc);
        backgroundPanel.add(Quit, gbc);
        backgroundPanel.add(margiPanel3, gbc);
        backgroundPanel.add(guessText, gbc);
        backgroundPanel.add(margiPanel, gbc);
        backgroundPanel.add(guessButton, gbc);
        backgroundPanel.add(margiPanel2, gbc);
        backgroundPanel.add(status, gbc);

        setContentPane(backgroundPanel);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    
    private void checkGuess(String guessStr) {
        if (!guessStr.isEmpty()) {
            int guess = Integer.parseInt(guessStr);
            nbGuess++;
            if (guess == randomNumber) {
                if (nbGuess > 10) {
                    status.setText("Bravo! Vous avez deviné le nombre en " + nbGuess + " coups");
                } else {
                    score++;
                    status.setText("Bravo! Vous avez deviné le nombre en " + nbGuess + " coups, tu as " + score + " pts.");
                }
                randomNumber = randomInteger(0, 1000); 
                nbGuess = 0; 
            } else if (guess < randomNumber) {
                status.setText("PLUS !");
            } else {
                status.setText("MOINS !");
            }
        } else {
            status.setText("Veuillez entrer un nombre.");
        }
    }

    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MoreOrLess();
            }
        });
    }
}
