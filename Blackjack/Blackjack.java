package Blackjack;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.util.*;
import DataBase.DataBaseManager;
import NumberPuzzleGame.NumberPuzzleGame.RoundedPanel;

public class Blackjack extends JFrame {
    private List<Integer> PlayerCards = new ArrayList<Integer>();
    private List<Integer> DealerCards = new ArrayList<Integer>();
    private ImageIcon imageIcon;
    private JLabel Card;
    private JLabel TitleGame;
    private JPanel buttonPanel;
    private JButton StartGame;
    private int Money;
    private int Mise;
    private JButton Login;
    private JButton Register;
    private TextField Pseudo;
    private TextField PassWord;
    private JButton Submit;
    private TextField Parry;
    private JLabel MoneyText;
    private JPanel DealerCardsPanel;
    private JPanel PlayerCardsPanel;
    private JPanel newPlayerCardJPanel = new JPanel();
    public static DataBaseManager db;
    private JLabel ParryRate = new JLabel();
    private Image backgroundImage;

    public Blackjack() {
        DisplayMenu();
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
        setSize(500, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setLocationRelativeTo(null);

        db = new DataBaseManager();

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        JPanel marginPanel = new JPanel();
        marginPanel.setPreferredSize(new Dimension(150, 100));
        marginPanel.setBackground(new Color(0, 0, 0, 0));

        JPanel margPanel = new JPanel();
        margPanel.setPreferredSize(new Dimension(150, 100));
        margPanel.setBackground(new Color(0, 0, 0, 0));

        JPanel margPanel2 = new JPanel();
        margPanel2.setPreferredSize(new Dimension(150, 50));
        margPanel2.setBackground(new Color(0, 0, 0, 0));

        JPanel margPanel3 = new JPanel();
        margPanel3.setPreferredSize(new Dimension(150, 50));
        margPanel3.setBackground(new Color(0, 0, 0, 0));

        RoundedPanel title = new RoundedPanel(162, 171, 175, 255);
        title.setLayout(new BoxLayout(title, BoxLayout.Y_AXIS));
        title.setPreferredSize(new Dimension(300, 100));
        title.setMaximumSize(new Dimension(300, 100));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        TitleGame = new JLabel("BlackJack");
        TitleGame.setForeground(Color.BLACK);
        TitleGame.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 50));
        TitleGame.setHorizontalAlignment(JLabel.CENTER);
        TitleGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.add(TitleGame);

        StartGame = new JButton("Jouer");
        JButton Retour = new JButton("Retour");
        Retour.setForeground(Color.BLACK);
        Retour.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        Retour.setAlignmentX(Component.CENTER_ALIGNMENT);
        Retour.setMaximumSize(new Dimension(240, 100));
        Retour.setBackground(new Color(162, 171, 175, 255));
        Retour.setBorderPainted(false);
        Retour.setFocusPainted(false);
        Retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        Login = new JButton("Connexion");
        Login.setForeground(Color.BLACK);
        Login.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        Login.setAlignmentX(Component.CENTER_ALIGNMENT);
        Login.setMaximumSize(new Dimension(240, 100));
        Login.setBackground(new Color(162, 171, 175, 255));
        Login.setBorderPainted(false);
        Login.setFocusPainted(false);

        Register = new JButton("Inscription");
        Register.setForeground(Color.BLACK);
        Register.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        Register.setAlignmentX(Component.CENTER_ALIGNMENT);
        Register.setMaximumSize(new Dimension(240, 100));
        Register.setBackground(new Color(162, 171, 175, 255));
        Register.setBorderPainted(false);
        Register.setFocusPainted(false);
        
        centerPanel.add(marginPanel);
        centerPanel.add(title);
        centerPanel.add(margPanel);
        centerPanel.add(Login);
        centerPanel.add(margPanel2);
        centerPanel.add(Register);
        centerPanel.add(margPanel3);
        centerPanel.add(Retour);

        ImageIcon icon = new ImageIcon("./Blackjack/Image/wallpaper.jpg");
        backgroundImage = icon.getImage();
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);
        add(centerPanel);



        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(centerPanel);
                DisplayLogin();
                revalidate();
                repaint();
            }
        });
        Register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(centerPanel);
                DisplayRegister();
                revalidate();
                repaint();
            }
        });
        setVisible(true);
    }

    private void DisplayRegister(){
        setSize(500,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        JPanel marginPanel = new JPanel();
        marginPanel.setPreferredSize(new Dimension(150, 150));
        marginPanel.setBackground(new Color(0, 0, 0, 0));

        JPanel margiPanel = new JPanel();
        margiPanel.setPreferredSize(new Dimension(150, 100));
        margiPanel.setBackground(new Color(0, 0, 0, 0));

        JPanel margiPanel2 = new JPanel();
        margiPanel2.setPreferredSize(new Dimension(150, 100));
        margiPanel2.setBackground(new Color(0, 0, 0, 0));

        JPanel margiPanel3 = new JPanel();
        margiPanel3.setPreferredSize(new Dimension(40, 20));
        margiPanel3.setBackground(new Color(0, 0, 0, 0));

        RoundedPanel title = new RoundedPanel(162, 171, 175, 255);
        title.setPreferredSize(new Dimension(350, 100));
        title.setMaximumSize(new Dimension(350, 100));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        TitleGame = new JLabel("BlackJack");
        TitleGame.setForeground(Color.BLACK);
        TitleGame.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 50));
        TitleGame.setHorizontalAlignment(JLabel.CENTER);
        TitleGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.add(TitleGame);


        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);


        JLabel Pseudotxt = new JLabel("Pseudo : ");
        Pseudotxt.setForeground(new Color(217, 217, 217, 255));
        Pseudotxt.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        
        Pseudo = new TextField();
        Pseudo.setForeground(Color.BLACK);
        Pseudo.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        Pseudo.setPreferredSize(new Dimension(270, 50));
        Pseudo.setBackground(new Color(217, 217, 217, 255));
    

        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.setOpaque(false);

        JLabel PassWordtxt = new JLabel("Mot de passe : ");
        PassWordtxt.setForeground(new Color(217, 217, 217, 255));
        PassWordtxt.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));

        PassWord = new TextField();
        PassWord.setEchoChar('*');
        PassWord.setForeground(Color.BLACK);
        PassWord.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        PassWord.setPreferredSize(new Dimension(240, 50));
        PassWord.setBackground(new Color(217, 217, 217, 255));
        


        Submit = new JButton("S'inscrire");
        Submit.setForeground(Color.BLACK);
        Submit.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        Submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        Submit.setMaximumSize(new Dimension(240, 100));
        Submit.setBackground(new Color(162, 171, 175, 255));
        Submit.setBorderPainted(false);
        Submit.setFocusPainted(false);

        JButton Retour = new JButton("Retour");
        Retour.setForeground(Color.BLACK);
        Retour.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        Retour.setAlignmentX(Component.CENTER_ALIGNMENT);
        Retour.setMaximumSize(new Dimension(240, 100));
        Retour.setBackground(new Color(162, 171, 175, 255));
        Retour.setBorderPainted(false);
        Retour.setFocusPainted(false);
        Retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(centerPanel);
                DisplayMenu();
                revalidate();
                repaint();
            }
        });

        buttonPanel.add(Pseudotxt);
        buttonPanel.add(Pseudo);

        buttonPanel2.add(PassWordtxt);
        buttonPanel2.add(PassWord);

        centerPanel.add(marginPanel);
        centerPanel.add(title);
        centerPanel.add(margiPanel);
        centerPanel.add(buttonPanel);
        centerPanel.add(buttonPanel2);
        centerPanel.add(margiPanel2);
        centerPanel.add(Submit);
        centerPanel.add(margiPanel3);
        centerPanel.add(Retour);

        add(centerPanel);
        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(buttonPanel);
                remove(TitleGame);
                DisplayMenu();
                String hashmdp = db.hashPassword(PassWord.getText());
                db.RegisterBJ(Pseudo.getText(), hashmdp);
                revalidate();
                repaint();
            }
        });
        setVisible(true);
    }

    private void DisplayLogin(){
        setSize(500,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        JPanel marginPanel = new JPanel();
        marginPanel.setPreferredSize(new Dimension(150, 150));
        marginPanel.setBackground(new Color(0, 0, 0, 0));

        JPanel margiPanel = new JPanel();
        margiPanel.setPreferredSize(new Dimension(150, 100));
        margiPanel.setBackground(new Color(0, 0, 0, 0));

        JPanel margiPanel2 = new JPanel();
        margiPanel2.setPreferredSize(new Dimension(150, 100));
        margiPanel2.setBackground(new Color(0, 0, 0, 0));

        JPanel margiPanel3 = new JPanel();
        margiPanel3.setPreferredSize(new Dimension(40, 20));
        margiPanel3.setBackground(new Color(0, 0, 0, 0));

        RoundedPanel title = new RoundedPanel(162, 171, 175, 255);
        title.setPreferredSize(new Dimension(350, 100));
        title.setMaximumSize(new Dimension(350, 100));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        TitleGame = new JLabel("BlackJack");
        TitleGame.setForeground(Color.BLACK);
        TitleGame.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 50));
        TitleGame.setHorizontalAlignment(JLabel.CENTER);
        TitleGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.add(TitleGame);


        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);


        JLabel Pseudotxt = new JLabel("Pseudo : ");
        Pseudotxt.setForeground(new Color(217, 217, 217, 255));
        Pseudotxt.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        
        Pseudo = new TextField();
        Pseudo.setForeground(Color.BLACK);
        Pseudo.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        Pseudo.setPreferredSize(new Dimension(270, 50));
        Pseudo.setBackground(new Color(217, 217, 217, 255));
    

        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.setOpaque(false);

        JLabel PassWordtxt = new JLabel("Mot de passe : ");
        PassWordtxt.setForeground(new Color(217, 217, 217, 255));
        PassWordtxt.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));

        PassWord = new TextField();
        PassWord.setEchoChar('*');
        PassWord.setForeground(Color.BLACK);
        PassWord.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        PassWord.setPreferredSize(new Dimension(240, 50));
        PassWord.setBackground(new Color(217, 217, 217, 255));
        


        Submit = new JButton("Se connecter");
        Submit.setForeground(Color.BLACK);
        Submit.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        Submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        Submit.setMaximumSize(new Dimension(240, 100));
        Submit.setBackground(new Color(162, 171, 175, 255));
        Submit.setBorderPainted(false);
        Submit.setFocusPainted(false);

        JButton Retour = new JButton("Retour");
        Retour.setForeground(Color.BLACK);
        Retour.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        Retour.setAlignmentX(Component.CENTER_ALIGNMENT);
        Retour.setMaximumSize(new Dimension(240, 100));
        Retour.setBackground(new Color(162, 171, 175, 255));
        Retour.setBorderPainted(false);
        Retour.setFocusPainted(false);
        Retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(centerPanel);
                DisplayMenu();
                revalidate();
                repaint();
            }
        });

        buttonPanel.add(Pseudotxt);
        buttonPanel.add(Pseudo);

        buttonPanel2.add(PassWordtxt);
        buttonPanel2.add(PassWord);

        centerPanel.add(marginPanel);
        centerPanel.add(title);
        centerPanel.add(margiPanel);
        centerPanel.add(buttonPanel);
        centerPanel.add(buttonPanel2);
        centerPanel.add(margiPanel2);
        centerPanel.add(Submit);
        centerPanel.add(margiPanel3);
        centerPanel.add(Retour);

        add(centerPanel);
        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(centerPanel);
                
                String hashmdp = db.hashPassword(PassWord.getText());
                int money = db.Login(Pseudo.getText(), hashmdp);

                if (money != -1 ){
                    DisplayParry(money,Pseudo.getText());
                    revalidate();
                    repaint();
                }else {
                    DisplayMenu();
                    revalidate();
                    repaint();
                }
            }
        });
        setVisible(true);
    }
    private void DisplayParry(int money,String pseudo){
        setSize(500,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        JPanel marginPanel = new JPanel();
        marginPanel.setPreferredSize(new Dimension(150, 100));
        marginPanel.setBackground(new Color(0, 0, 0, 0));

        JPanel margiPanel = new JPanel();
        margiPanel.setPreferredSize(new Dimension(150, 100));
        margiPanel.setBackground(new Color(0, 0, 0, 0));

        JPanel margiPanel2 = new JPanel();
        margiPanel2.setPreferredSize(new Dimension(40, 20));
        margiPanel2.setBackground(new Color(0, 0, 0, 0));

        JPanel margiPanel3 = new JPanel();
        margiPanel3.setPreferredSize(new Dimension(40, 20));
        margiPanel3.setBackground(new Color(0, 0, 0, 0));

        JPanel margiPanel4 = new JPanel();
        margiPanel4.setPreferredSize(new Dimension(40, 20));
        margiPanel4.setBackground(new Color(0, 0, 0, 0));

        RoundedPanel title = new RoundedPanel(167, 173, 175, 255);
        title.setPreferredSize(new Dimension(350, 100));
        title.setMaximumSize(new Dimension(350, 100));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        TitleGame = new JLabel("BlackJack");
        TitleGame.setForeground(Color.BLACK);
        TitleGame.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 50));
        TitleGame.setHorizontalAlignment(JLabel.CENTER);
        TitleGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.add(TitleGame);

        Parry = new TextField();
        Parry.setForeground(Color.BLACK);
        Parry.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        Parry.setPreferredSize(new Dimension(270, 50));
        Parry.setBackground(new Color(217, 217, 217, 255));

        JPanel rate = new JPanel();
        rate.setPreferredSize(new Dimension(150, 100));
        rate.setBackground(new Color(167, 173, 175, 255));


        if(money >= 500){
            ParryRate.setText("Tu en as gagné "+String.valueOf(money-500)+" depuis le debut ");
        } else {
            ParryRate.setText("Tu en as perdu "+String.valueOf(500-money)+" depuis le debut ");
        }
        ParryRate.setForeground(Color.WHITE);
        ParryRate.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        ParryRate.setAlignmentX(Component.CENTER_ALIGNMENT);
        ParryRate.setHorizontalAlignment(JLabel.CENTER);
        ParryRate.setVerticalAlignment(JLabel.CENTER);
        rate.add(ParryRate);



        JPanel moneyPanel = new JPanel();
        moneyPanel.setPreferredSize(new Dimension(150, 100));
        moneyPanel.setBackground(new Color(167, 173, 175, 255));
        
        MoneyText = new JLabel("Tu as "+ String.valueOf(money)+" rubis");
        MoneyText.setForeground(Color.WHITE);
        MoneyText.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        moneyPanel.add(MoneyText);


        Submit = new JButton("Parrier");
        Submit.setForeground(Color.BLACK);
        Submit.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        Submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        Submit.setMaximumSize(new Dimension(240, 100));
        Submit.setBackground(new Color(162, 171, 175, 255));
        Submit.setBorderPainted(false);
        Submit.setFocusPainted(false);

        buttonPanel = new JPanel();
        buttonPanel.add(Parry);
        buttonPanel.add(MoneyText);
        buttonPanel.add(Submit);
        buttonPanel.add(ParryRate);

        centerPanel.add(marginPanel);
        centerPanel.add(title);
        centerPanel.add(margiPanel);
        centerPanel.add(MoneyText);
        centerPanel.add(margiPanel2);
        centerPanel.add(ParryRate);
        centerPanel.add(margiPanel3);
        centerPanel.add(Parry);
        centerPanel.add(margiPanel4);
        centerPanel.add(Submit);

        add(centerPanel);
        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(money >= Integer.parseInt(Parry.getText())){
                    remove(centerPanel);
                    DisplayGame(Integer.parseInt(Parry.getText()),pseudo);
                    revalidate();
                    repaint();
                }
            }
        });
        setVisible(true);

    }



    private void DisplayGame(int money,String pseudo){
        PlayerCards = new ArrayList<Integer>();
        DealerCards = new ArrayList<Integer>();
        Mise = 500;

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setOpaque(false);

        JButton b=new JButton("Piocher une carte"); 
        b.setForeground(Color.BLACK);
        b.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setMaximumSize(new Dimension(240, 100));
        b.setBackground(new Color(162, 171, 175, 255));
        b.setBorderPainted(false);
        b.setFocusPainted(false);

        JButton b2=new JButton("Garder les cartes");
        b2.setForeground(Color.BLACK);
        b2.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        b2.setMaximumSize(new Dimension(240, 100));
        b2.setBackground(new Color(162, 171, 175, 255));
        b2.setBorderPainted(false);
        b2.setFocusPainted(false);
        b2.setBounds(50,150,95,30);
        buttonPanel.add(b2); 
        JPanel margiPanel2 = new JPanel();
        margiPanel2.setPreferredSize(new Dimension(10, 40));
        margiPanel2.setBackground(Color.RED);
        buttonPanel.add(margiPanel2);
        b.setBounds(50,100,95,30);
        buttonPanel.add(b);  
        setSize(500,900);  
        int nombreAleatoire = 1 + (int)(Math.random() * ((13 - 1) + 1));
        PlayerCards.add(nombreAleatoire);

        DealerCardsPanel = new JPanel();
        PlayerCardsPanel = new JPanel();
        PlayerCardsPanel.setOpaque(false);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        JPanel marginPanel = new JPanel();
        marginPanel.setPreferredSize(new Dimension(150, 200));
        marginPanel.setBackground(Color.RED);

        JPanel margiPanel = new JPanel();
        margiPanel.setPreferredSize(new Dimension(150, 40));
        margiPanel.setBackground(Color.RED);



        RoundedPanel title = new RoundedPanel(167, 173, 175, 255);
        title.setPreferredSize(new Dimension(350, 100));
        title.setMaximumSize(new Dimension(350, 100));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        TitleGame = new JLabel("BlackJack");
        TitleGame.setForeground(Color.BLACK);
        TitleGame.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 50));
        TitleGame.setHorizontalAlignment(JLabel.CENTER);
        TitleGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.add(TitleGame);

        SetCardOnBoard(nombreAleatoire,true);
        b.addActionListener(e -> {
            int newCard = 1 + (int)(Math.random() * ((13 - 1) + 1));
            PlayerCards.add(newCard);
            SetCardOnBoard(newCard,true);

            if ( GetAddition(PlayerCards) > 21) {
                JOptionPane.showMessageDialog(this, "Tu as perdu ! "+ "tu as depassé 21");
                remove(centerPanel);
                db.SetMoney(pseudo, db.GetMoney(pseudo)-money);
                DisplayParry(db.GetMoney(pseudo), pseudo);
                revalidate();
                repaint();
            } else if ( GetAddition(PlayerCards) == 21) {
                JOptionPane.showMessageDialog(this, "Blackjack ! Tu as gagné!");
                remove(centerPanel);
                db.SetMoney(pseudo,db.GetMoney(pseudo)+ money*2);
                DisplayParry(db.GetMoney(pseudo), pseudo);
                revalidate();
                repaint();
            } 
        });
    
        b2.addActionListener(e -> {
            while (GetAddition(DealerCards) < 16) {
                int random = 1 + (int)(Math.random() * ((13 - 1) + 1));
                DealerCards.add(random);
            }
            if (GetAddition(DealerCards) > 21 || GetAddition(DealerCards) < GetAddition(PlayerCards)) {
                JOptionPane.showMessageDialog(this, " Tu as gagné " + "le croupier avait : " + GetCard(DealerCards));
                remove(centerPanel);
                db.SetMoney(pseudo, db.GetMoney(pseudo)+money*2);
                DisplayParry(db.GetMoney(pseudo), pseudo);
                revalidate();
                repaint();
            } else if (GetAddition(DealerCards) == 21) {
                JOptionPane.showMessageDialog(this,"Blackjack ! Tu as perdu !");
                db.SetMoney(pseudo, db.GetMoney(pseudo)-money);
                remove(centerPanel);
                DisplayParry(db.GetMoney(pseudo), pseudo);
                revalidate();
                repaint();
            } else if (GetAddition(DealerCards) == GetAddition(PlayerCards)) {
                JOptionPane.showMessageDialog(this, "Match nul !" );
                remove(centerPanel);
                DisplayParry(db.GetMoney(pseudo), pseudo);
                revalidate();
                repaint();
            } else if (GetAddition(DealerCards) > GetAddition(PlayerCards)) {
                JOptionPane.showMessageDialog(this, "Tu as perdu ! " + "le croupier avait" + GetAddition(DealerCards));
                db.SetMoney(pseudo, db.GetMoney(pseudo)-money);
                remove(centerPanel);
                // remove(texte);
                DisplayParry(db.GetMoney(pseudo), pseudo);
                revalidate();
                repaint();
            }
        });       
        
        centerPanel.add(marginPanel);
        centerPanel.add(title);
        centerPanel.add(margiPanel);
        centerPanel.add(PlayerCardsPanel);
    
        centerPanel.add(buttonPanel);
        add(centerPanel);     
        setVisible(true); 
    }

    public static void main(String[] args) {
        new Blackjack();
    }
    public int GetAddition(List<Integer> list){
        int total =0;
        int As = 0;
        for(int i = 0 ; i < list.size();i++){
            if (list.get(i) < 10){
                if (list.get(i) == 1){
                    As++;
                }
                total+= list.get(i);
            }else {
                total+= 10;
            }
        }
        for(int i = 0 ; i < As ;i++){
            if (total <= 11){
                total+= 10;
            }
        }
        return total;
    }
    private String GetCard(List<Integer> list){
        String cards = "";
        for(int i = 0 ; i < list.size();i++){
            if (i+1 < list.size()){
                cards += list.get(i)+ " | " ;
            }else {
                cards += list.get(i) ;
            }

        }
        return cards;
    }
    private void SetCardOnBoard(int card,boolean IsPlayer){
        JPanel Card = new JPanel();
        setPieceImage("./Blackjack/Image/"+card+".png", Card);
        Card.setPreferredSize(new Dimension(125, 202));
            if (IsPlayer){
                PlayerCardsPanel.add(Card);
                PlayerCardsPanel.validate();
                PlayerCardsPanel.revalidate();
                PlayerCardsPanel.repaint();
            } else {
                DealerCardsPanel.add(Card);
                DealerCardsPanel.validate();
                DealerCardsPanel.revalidate();
                DealerCardsPanel.repaint();
            }
    }
    public void setPieceImage(String imagePath, JPanel targetButton) {
        ImageIcon icon = new ImageIcon(imagePath);
        if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
    
            JLabel label = new JLabel(icon) {
                @Override
                public void paintComponent(Graphics g) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    
                    double scaleX = (double) getWidth() / icon.getIconWidth();
                    double scaleY = (double) getHeight() / icon.getIconHeight();
    
                    AffineTransform at = AffineTransform.getScaleInstance(scaleX, scaleY);
                    g2d.drawImage(icon.getImage(), at, this);
    
                    g2d.dispose();
                }
            };
            targetButton.setLayout(new BorderLayout());
            targetButton.add(label, BorderLayout.CENTER);
    
            targetButton.revalidate();
            targetButton.repaint();
        }
    }
}
