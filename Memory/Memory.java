package Memory;

import javax.swing.*;

import Menu.SelectionWindow;
import NumberPuzzleGame.NumberPuzzleGame.RoundedPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Memory extends JFrame {

    private JPanel gamePanel;
    private ArrayList<CardButton> cards;
    private CardButton Card1;
    private CardButton Card2;
    private int pairsFound;
    private JLabel status;
    private int nbGuess;
    private Timer timer;
    private JLabel TitleGame;
    private JPanel buttonPanel;
    private JButton StartGame;
    private int Score;
    private Image backgroundImage;

    public Memory() {
        DisplayMenu();
    }
    private void DisplayGame(){
        setTitle("Memory Game");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        gamePanel = new JPanel(new GridLayout(4, 4));
        add(gamePanel, BorderLayout.CENTER);

        status = new JLabel("Moves: 0   Pairs Found: 0");
        status.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 25));
        status.setForeground(Color.WHITE);
        add(status, BorderLayout.SOUTH);

        JButton Exit = new JButton();
        Exit.setText("Menu");
        Exit.setBorderPainted(false);
        Exit.setFocusPainted(false);
        Exit.setContentAreaFilled(false);
        Exit.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 25));
        Exit.setForeground(Color.WHITE);
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(gamePanel);
                remove(status);
                remove(Exit);
                DisplayMenu();
                revalidate();
                repaint();
            }
        });
        add(Exit, BorderLayout.NORTH);


        cards = new ArrayList<>();
        pairsFound = 0;
        nbGuess = 0;

        for (int i = 0; i < 8; i++) {
            cards.add(new CardButton(i));
            cards.add(new CardButton(i));
        }

        Collections.shuffle(cards);

        for (CardButton card : cards) {
            gamePanel.add(card);
            card.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    CardButton selected = (CardButton) e.getSource();
                    if (Card1 == null) {
                        Card1 = selected;
                        Card1.show();
                    } else if (Card2 == null && selected != Card1) {
                        Card2 = selected;
                        Card2.show();
                        nbGuess++;
                        if (nbGuess == 15 ){
                            JOptionPane.showMessageDialog(new JFrame(), "You loose , you found "+pairsFound+" pairs in 20 moove with "+Score+" Score.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                            timer.stop();
                            return;
                        }
                        status.setText("Moves: " + nbGuess + "   Pairs Found: " + pairsFound);
                        timer = new Timer(1000, new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                checkMatch();
                            }
                        });
                        timer.setRepeats(false);
                        timer.start();
                    }
                }
            });
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
    private void DisplayMenu(){
        TitleGame = new JLabel("Memory Game");
        ImageIcon icon = new ImageIcon("./Memory/image/wallpaper.png");
        backgroundImage = icon.getImage();
        
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);
        // setLayout(new BorderLayout());
        setLayout(new BorderLayout());

        setSize(500, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setLocationRelativeTo(null);


        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        JPanel marginPanel = new JPanel();
        marginPanel.setMaximumSize(new Dimension(150, 150));
        marginPanel.setBackground(new Color(0, 0, 0, 0));
        
        JLabel Title = new JLabel("Memory");
        Title.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 50));
        Title.setForeground(Color.BLACK);
        Title.setHorizontalAlignment(JLabel.CENTER);
        Title.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundedPanel title = new RoundedPanel(162, 171, 175, 255);
        title.setLayout(new BoxLayout(title, BoxLayout.Y_AXIS));
        title.setMaximumSize(new Dimension(240, 100));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JPanel marginButton = new JPanel();
        marginButton.setMaximumSize(new Dimension(150, 150));
        marginButton.setBackground(new Color(0, 0, 0, 0));

        JPanel ExitPanel = new JPanel();
        ExitPanel.setMaximumSize(new Dimension(60, 60));
        ExitPanel.setBackground(new Color(0, 0, 0, 0));

        StartGame = new JButton("Start Game");
        StartGame.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        StartGame.setMaximumSize(new Dimension(300, 100));
        StartGame.setBackground(new Color(162, 171, 175, 255));
        StartGame.setForeground(Color.BLACK);
        StartGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        StartGame.setBorderPainted(false);
        StartGame.setFocusPainted(false);
        StartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(centerPanel);
                remove(backgroundPanel);
                DisplayGame();
                revalidate();
                repaint();
            }
        });

        JButton Exit = new JButton("Exit");
        Exit.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        Exit.setMaximumSize(new Dimension(300, 100));
        Exit.setBackground(new Color(162, 171, 175, 255));
        Exit.setForeground(Color.BLACK);
        Exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        Exit.setBorderPainted(false);
        Exit.setFocusPainted(false);
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(() -> new SelectionWindow());
            }
        });



        title.add(Title);
        
        centerPanel.add(marginPanel);
        centerPanel.add(title);
        centerPanel.add(marginButton);
        centerPanel.add(StartGame);
        centerPanel.add(ExitPanel);
        centerPanel.add(Exit);
        add(centerPanel);



        setVisible(true);
    }
    private void checkMatch() {
        if (Card1.getId() == Card2.getId()) {
            Card1.setEnabled(false);
            Card2.setEnabled(false);
            pairsFound++;
            Score += 2;
            if (pairsFound == 8) {
                JOptionPane.showMessageDialog(this, "Congratulations! You've found all pairs in " + nbGuess + " moves with a score of "+Score, "Game Over", JOptionPane.INFORMATION_MESSAGE);
                timer.stop();
            }
        } else {
            Score--;
            Card1.hide();
            Card2.hide();
        }
        Card1 = null;
        Card2 = null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Memory game = new Memory();
                game.setVisible(true);
            }
        });
    }

    class CardButton extends JButton {

        private int id;
        private ImageIcon imageIcon;
        private ImageIcon hidenSide;

        public CardButton(int id) {
            this.id = id;
            this.setPreferredSize(new Dimension(100, 100));
            // Construct the image path dynamically based on the card id
            String imagePath = "./Memory/image/image_" + id + ".png";
            this.imageIcon = new ImageIcon(imagePath);
            if (imageIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                System.out.println("Erreur de chargement de l'image: " + imagePath);
            } 
            String BGPath = "./Memory/image/bg.png";
            this.hidenSide = new ImageIcon(BGPath);
            if (hidenSide.getImageLoadStatus() != MediaTracker.COMPLETE) {
                System.out.println("Erreur de chargement de l'image: " + BGPath);
            } 

            setIcon(hidenSide);
        }    

        public int getId() {
            return id;
        }

        public void show() {
            setIcon(imageIcon);
        }
        
        public void hide() {
            setIcon(hidenSide);
        }
        
    }
}
