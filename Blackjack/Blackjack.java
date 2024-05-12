package Blackjack;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.util.*;
import DataBase.DataBaseManager;

public class Blackjack extends JFrame {
    // private int Joueur;
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
    public static DataBaseManager db;
    private JLabel ParryRate;
    public Blackjack() {
        DisplayMenu();
    }
    private void DisplayMenu(){
        db = new DataBaseManager();
        TitleGame = new JLabel("BlackJack");
        add(TitleGame, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonPanel = new JPanel();
        StartGame = new JButton("Jouer");
        JButton Retour = new JButton("Retour");
        Login = new JButton("Login");
        Register = new JButton("Register");
        // buttonPanel.add(StartGame);
        buttonPanel.add(Retour);
        buttonPanel.add(Login);
        buttonPanel.add(Register);
        add(buttonPanel, BorderLayout.SOUTH);
        // StartGame.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         remove(buttonPanel);
        //         remove(TitleGame);
        //         DisplayGame();
        //         revalidate();
        //         repaint();
        //     }
        // });
        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(buttonPanel);
                remove(TitleGame);
                DisplayLogin();
                revalidate();
                repaint();
            }
        });
        Register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(buttonPanel);
                remove(TitleGame);
                DisplayRegister();
                revalidate();
                repaint();
            }
        });
        pack();
        resize(500,500);
        setVisible(true);
    }

    private void DisplayRegister(){
        TitleGame = new JLabel("BlackJack");
        add(TitleGame, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonPanel = new JPanel();
        JLabel Pseudotxt = new JLabel("Pseudo : ");
        Pseudo = new TextField();
        JLabel PassWordtxt = new JLabel("Password : ");
        PassWord = new TextField();
        Submit = new JButton("Register");
        buttonPanel.add(Pseudotxt);
        buttonPanel.add(Pseudo);
        buttonPanel.add(PassWordtxt);
        buttonPanel.add(PassWord);
        buttonPanel.add(Submit);
        add(buttonPanel);
        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(buttonPanel);
                remove(TitleGame);
                DisplayMenu();
                String hashmdp = db.hashPassword(PassWord.getText());
                System.out.println("hash pass : "+hashmdp);
                db.RegisterBJ(Pseudo.getText(), hashmdp);
                revalidate();
                repaint();
            }
        });
        pack();
        resize(500,500);
        setVisible(true);
    }

    private void DisplayLogin(){
        TitleGame = new JLabel("BlackJack");
        add(TitleGame, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonPanel = new JPanel();
        JLabel Pseudotxt = new JLabel("Pseudo : ");
        Pseudo = new TextField();
        JLabel PassWordtxt = new JLabel("Register : ");
        PassWord = new TextField();
        Submit = new JButton("Login");
        buttonPanel.add(Pseudotxt);
        buttonPanel.add(Pseudo);
        buttonPanel.add(PassWordtxt);
        buttonPanel.add(PassWord);
        buttonPanel.add(Submit);
        add(buttonPanel);
        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(buttonPanel);
                remove(TitleGame);
                
                String hashmdp = db.hashPassword(PassWord.getText());
                System.out.println("hash pass : "+hashmdp);
                int money = db.Login(Pseudo.getText(), hashmdp);
                System.out.println("money : "+money);

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
        pack();
        resize(500,500);
        setVisible(true);
    }
    private void DisplayParry(int money,String pseudo){
        setTitle("True or False Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        Parry = new TextField();
        if(money > 500){
            ParryRate = new JLabel("Tu as gagné "+String.valueOf(money-500)+" depuis le debut ");
        }else {
            ParryRate = new JLabel("Tu as perdu "+String.valueOf(500-money)+" depuis le debut ");
        }
        
        MoneyText = new JLabel(String.valueOf(money));
        Submit = new JButton("Parry");
        buttonPanel = new JPanel();
        buttonPanel.add(Parry);
        buttonPanel.add(MoneyText);
        buttonPanel.add(Submit);
        buttonPanel.add(ParryRate);
        add(buttonPanel);
        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(money >= Integer.parseInt(Parry.getText())){
                    remove(buttonPanel);
                    DisplayGame(Integer.parseInt(Parry.getText()),pseudo);
                    System.out.println("tu as misé : "+Parry.getText());
                    revalidate();
                    repaint();
                }
            }
        });
    }



    private void DisplayGame(int money,String pseudo){
        PlayerCards = new ArrayList<Integer>();
        DealerCards = new ArrayList<Integer>();
        Mise = 500;
        // JFrame f=new JFrame("Blackjack");  
        JButton b=new JButton("Piocher une carte"); 
        JButton b2=new JButton("Garder les cartes");
        b2.setBounds(50,150,95,30);
        add(b2); 
        b.setBounds(50,100,95,30);  
        add(b);  
        setSize(400,400);  
        setLayout(null);  
        int nombreAleatoire = 1 + (int)(Math.random() * ((13 - 1) + 1));
        PlayerCards.add(nombreAleatoire);
        JLabel texte = new JLabel("Voici tes cartes : "+GetCard(PlayerCards));
        imageIcon = new ImageIcon("./image/cards/2-D.png");
        Card = new JLabel(imageIcon);  
        add(Card);
        texte.setBounds(50, 200, 200, 100);
        add(texte);
        DealerCardsPanel = new JPanel();
        PlayerCardsPanel = new JPanel();
        // DealerCardsPanel.setOpaque(false);
        
        // PlayerCardsPanel.setOpaque(false);
        add(DealerCardsPanel);
        add(PlayerCardsPanel);
        b.addActionListener(e -> {
            int newCard = 1 + (int)(Math.random() * ((13 - 1) + 1));
            PlayerCards.add(newCard);
            texte.setText("Voici tes cartes: " +GetCard(PlayerCards));
            SetCardOnBoard(newCard,true);
            if ( GetAddition(PlayerCards) > 21) {
                JOptionPane.showMessageDialog(this, "Vous avez perdu!" + "avec :" +  GetAddition(PlayerCards));
                remove(b);
                remove(b2);
                remove(texte);
                db.SetMoney(pseudo, db.GetMoney(pseudo)-money);
                DisplayParry(db.GetMoney(pseudo), pseudo);
                revalidate();
                repaint();
            } else if ( GetAddition(PlayerCards) == 21) {
                JOptionPane.showMessageDialog(this, "Blackjack! Vous avez gagné!");
                remove(b);
                remove(b2);
                remove(texte);
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
                System.out.println(GetAddition(DealerCards));
            }
            if (GetAddition(DealerCards) > 21 || GetAddition(DealerCards) < GetAddition(PlayerCards)) {
                JOptionPane.showMessageDialog(this, " IA a perdu!");
                remove(b);
                remove(b2);
                remove(texte);
                db.SetMoney(pseudo, db.GetMoney(pseudo)+money*2);
                DisplayParry(db.GetMoney(pseudo), pseudo);
                revalidate();
                repaint();
            } else if (GetAddition(DealerCards) == 21) {
                JOptionPane.showMessageDialog(this,"Blackjack! IA a gagné!" + "avec :" + GetAddition(DealerCards));
                db.SetMoney(pseudo, db.GetMoney(pseudo)-money);
                remove(b);
                remove(b2);
                remove(texte);
                DisplayParry(db.GetMoney(pseudo), pseudo);
                revalidate();
                repaint();
            } else if (GetAddition(DealerCards) == GetAddition(PlayerCards)) {
                JOptionPane.showMessageDialog(this, "Match nul!" + GetAddition(DealerCards) + " " + GetAddition(PlayerCards));
                remove(b);
                remove(b2);
                remove(texte);
                DisplayParry(db.GetMoney(pseudo), pseudo);
                revalidate();
                repaint();
            } else if (GetAddition(DealerCards) > GetAddition(PlayerCards)) {
                JOptionPane.showMessageDialog(this, "IA a gagné!" + GetAddition(DealerCards) + " " + GetAddition(PlayerCards));
                db.SetMoney(pseudo, db.GetMoney(pseudo)-money);
                remove(b);
                remove(b2);
                remove(texte);
                DisplayParry(db.GetMoney(pseudo), pseudo);
                revalidate();
                repaint();
            }
        });            
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
        Card.setPreferredSize(new Dimension(385, 622));
        System.out.println("Width of "+card+" : "+Card.getWidth());
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
            
        
        System.out.println("Width of "+card+" : "+Card.getWidth());
    }
    public void setPieceImage(String imagePath, JPanel targetButton) {
        ImageIcon icon = new ImageIcon(imagePath);
        System.out.println("width : "+icon.getIconWidth());
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
        } else {
            System.out.println("Image loading failed!");
        }
    }
}
