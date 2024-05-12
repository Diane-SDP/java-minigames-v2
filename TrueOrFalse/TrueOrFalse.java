package TrueOrFalse;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import Menu.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import DataBase.DataBaseManager;

public class TrueOrFalse extends JFrame {
    private Map<String, Boolean> questionsReponses = new HashMap<>();
    private int score = 0;
    private Map.Entry<String, Boolean> currentQuestion;
    private JLabel questionLabel;
    private JLabel trueOrFalsePanel;
    private JLabel TitleGame;
    private JButton trueButton;
    private JButton falseButton;
    private JButton StartGame;
    private JButton AddQuestion;
    private int NbQuestionDone;
    private JPanel buttonPanel;
    private JPanel menuPanel;
    private JPanel centerPanel;
    private JPanel titlePanel;
    private JPanel StartPanel;
    private JPanel AddPanel;
    private JPanel backgroundPanel;
    public static DataBaseManager db = SelectionWindow.db;
    private Image backgroundImage;


    public TrueOrFalse() {
        DisplayMenu();
        // DisplayGame();
        setLocationRelativeTo(null);
    }

    private void DisplayMenu(){
        setResizable(false);
        setTitle("True or False Game");
        setSize(700, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setLayout(new BorderLayout());
        TitleGame = new JLabel("<html><div style='text-align: center;'>REPONDEZ AUX QUESTIONS<br/>PAR VRAI OU FAUX</div></html>");
        TitleGame.setHorizontalTextPosition(SwingConstants.CENTER);
        //add(TitleGame, BorderLayout.NORTH);
        ImageIcon icon = null;
        try {
            icon = new ImageIcon("./TrueOrFalse/image/bg.png");
            if (icon.getImageLoadStatus() != java.awt.MediaTracker.COMPLETE) {
                System.out.println(icon.getImageLoadStatus());
                throw new Exception("Failed to load image");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        backgroundImage = icon.getImage();
        backgroundPanel = new BackgroundPanel(icon);
        setContentPane(backgroundPanel);
        setLayout(new BorderLayout());

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        

        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        StartPanel = new JPanel();
        StartPanel.setLayout(new BoxLayout(StartPanel, BoxLayout.Y_AXIS));
        AddPanel = new JPanel();
        AddPanel.setLayout(new BoxLayout(AddPanel, BoxLayout.Y_AXIS));

        // TitleGame.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        TitleGame.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 50));
        TitleGame.setForeground(Color.WHITE);

        StartGame = new JButton("Jouer");
        StartGame.setContentAreaFilled(false);
        StartGame.setBorderPainted(false);
        StartGame.setFocusPainted(false);
        StartGame.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 70));
        StartGame.setForeground(new Color(255, 255, 255, 160));
        StartGame.setVerticalAlignment(JLabel.CENTER);

        AddQuestion = new JButton("Ajouter une question");
        AddQuestion.setContentAreaFilled(false);
        AddQuestion.setBorderPainted(false);
        AddQuestion.setFocusPainted(false);
        AddQuestion.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 40));
        AddQuestion.setForeground(new Color(255, 255, 255, 160));
        AddQuestion.setVerticalAlignment(JLabel.CENTER);

        ImageIcon iconMenu = new ImageIcon("./TrueOrFalse/image/triforceBW.png");
        JButton Menu = new JButton("Retour");
        Menu.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        Menu.setContentAreaFilled(false);
        
        Menu.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 30));
        Menu.setForeground(Color.WHITE);
        Menu.setIcon(iconMenu);
        Menu.setVerticalTextPosition(SwingConstants.BOTTOM);
        Menu.setHorizontalTextPosition(SwingConstants.CENTER);
        Menu.setContentAreaFilled(false);
        Menu.setBorderPainted(false);
        Menu.setFocusPainted(false);

        menuPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        menuPanel.add(Menu, BorderLayout.WEST);
        StartPanel.add(StartGame);
        buttonPanel.add(StartPanel);
        AddPanel.add(AddQuestion);
        buttonPanel.add(AddPanel);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 170, 30, 0));
        titlePanel.add(TitleGame);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 80, 140, 0));
        StartPanel.setBorder(BorderFactory.createEmptyBorder(0, 80, 80, 0));
        AddPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        menuPanel.setOpaque(false);
        StartPanel.setOpaque(false);
        AddPanel.setOpaque(false);
        buttonPanel.setOpaque(false);
        centerPanel.setOpaque(false);
        titlePanel.setOpaque(false);
        centerPanel.add(titlePanel, BorderLayout.SOUTH);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));
        backgroundPanel.add(centerPanel, BorderLayout.SOUTH);
        backgroundPanel.add(menuPanel, BorderLayout.NORTH);
        StartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(buttonPanel);
                remove(TitleGame);
                remove(centerPanel);
                DisplayGame();
            }
        });
        StartGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                StartGame.setForeground(new Color(255, 255, 255, 255));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                StartGame.setForeground(new Color(255, 255, 255, 160));
            }
        });

        AddQuestion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                AddQuestion.setForeground(new Color(255, 255, 255, 255));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                AddQuestion.setForeground(new Color(255, 255, 255, 160));
            }
        });

        try {
            BufferedImage originalImage = ImageIO.read(new File("./TrueOrFalse/image/triforceBW.png"));
            BufferedImage hoverImage = ImageIO.read(new File("./TrueOrFalse/image/triforce.png"));

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
            Menu.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    Menu.setIcon(hoverIcon);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    Menu.setIcon(normalIcon);
                }
            });

            Menu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    SwingUtilities.invokeLater(() -> new SelectionWindow());
                }
            });

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        

        AddQuestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(buttonPanel);
                remove(centerPanel);
                
                DisplayCreateQuestion();
                revalidate(); // Recalculer la disposition des composants
                repaint();
            }
        });
    }

    private Map.Entry<String, Boolean> getNewQuestion() {
        Random random = new Random();
        int randomIndex = random.nextInt(questionsReponses.size());
        Iterator<Map.Entry<String, Boolean>> iterator = questionsReponses.entrySet().iterator();
        Map.Entry<String, Boolean> randomEntry = null;
        for (int i = 0; i <= randomIndex && iterator.hasNext(); i++) {
            randomEntry = iterator.next();
        }
        if (randomEntry != null) {
            System.out.println("delete" + randomEntry.getKey());
            questionsReponses.remove(randomEntry.getKey());
        }
        return randomEntry;
    }
    
    private void displayNextQuestion() {
        currentQuestion = getNewQuestion();
        questionLabel.setText(currentQuestion.getKey());
    }



    private void handleAnswer(boolean answer) {
        if (answer == currentQuestion.getValue()) {
            score++;
            NbQuestionDone++;
            JOptionPane.showMessageDialog(this, "Bravo !");
        } else {
            score--;
            NbQuestionDone++;
            if (currentQuestion.getValue() == false) {
                JOptionPane.showMessageDialog(this, "Et non, c'était faux");
            } else {
                JOptionPane.showMessageDialog(this, "Et non, c'était vrai");
            }  
        }
        if (!questionsReponses.isEmpty() && NbQuestionDone < 10) {
            displayNextQuestion();
        } else {
            JOptionPane.showMessageDialog(this, "Plus de questions ! Votre score: " + score);
            remove(buttonPanel);
            remove(questionLabel);
            DisplayMenu();
            revalidate(); 
            repaint();
        }
    }

    private void DisplayCreateQuestion(){

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        JPanel centerHonzPanel = new JPanel();
        centerHonzPanel.setLayout(new BoxLayout(centerHonzPanel, BoxLayout.X_AXIS));
        centerHonzPanel.setOpaque(false);

        JPanel centerHonzTitlePanel = new JPanel();
        centerHonzTitlePanel.setLayout(new BoxLayout(centerHonzTitlePanel, BoxLayout.X_AXIS));
        centerHonzTitlePanel.setOpaque(false);

        JPanel margPanel = new JPanel();
        margPanel.setBackground(new Color(0, 0, 0, 0));
        margPanel.setMaximumSize(new Dimension(1, 50));

        JPanel margPanel2 = new JPanel();
        margPanel2.setBackground(new Color(0, 0, 0, 0));
        margPanel2.setMaximumSize(new Dimension(30, 50));

        JPanel margPanel3 = new JPanel();
        margPanel3.setBackground(new Color(0, 0, 0, 0));
        margPanel3.setMaximumSize(new Dimension(60, 50));

        JPanel margPanel4 = new JPanel();
        margPanel4.setBackground(new Color(0, 0, 0, 0));
        margPanel4.setMaximumSize(new Dimension(150, 1));

        JPanel margPanel5 = new JPanel();
        margPanel5.setBackground(new Color(0, 0, 0, 0));
        margPanel5.setMaximumSize(new Dimension(3, 1));

        JPanel margPanel6 = new JPanel();
        margPanel6.setBackground(new Color(0, 0, 0, 0));
        margPanel6.setMaximumSize(new Dimension(10, 1));

        JPanel HonzSubPanel = new JPanel();
        HonzSubPanel.setLayout(new BoxLayout(HonzSubPanel, BoxLayout.X_AXIS));
        HonzSubPanel.setOpaque(false);

        TitleGame.setText("AJOUTER UNE QUESTION");
        // TitleGame.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputPanel.setOpaque(false);

        JButton submit = new JButton("Valider");
        submit.setContentAreaFilled(false);
        submit.setBorderPainted(false);
        submit.setFocusPainted(false);
        submit.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 40));
        submit.setForeground(Color.WHITE);
        // submit.setVerticalAlignment(JLabel.CENTER);

        JButton retour = new JButton("retour");
        retour.setContentAreaFilled(false);
        retour.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 40));
        retour.setForeground(Color.WHITE);
        retour.setBorder(BorderFactory.createEmptyBorder(0, 500, 0, 0));

        JTextField input = new JTextField();
        input.setMaximumSize(new Dimension(400, 95)); 
        input.setBorder(new RoundedBorder(35));
        input.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 25));
        input.setBackground(new Color(217, 217, 217, 200));
        input.setForeground(Color.WHITE);
        input.setOpaque(false);
  
        JRadioButton AnswerTrue = new JRadioButton("Vrai"); 
        AnswerTrue.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 50));
        AnswerTrue.setForeground(Color.WHITE);
        AnswerTrue.setOpaque(false);

        JRadioButton AnswerFalse = new JRadioButton("Faux"); 
        AnswerFalse.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 50));
        AnswerFalse.setForeground(Color.WHITE);
        AnswerFalse.setOpaque(false);


        centerPanel.add(margPanel);
        centerPanel.add(centerHonzTitlePanel);
        centerHonzTitlePanel.add(titlePanel);
        centerHonzTitlePanel.add(margPanel3);
        titlePanel.add(TitleGame);
        centerPanel.add(inputPanel);
        centerPanel.add(margPanel2);
        inputPanel.add(input);
        centerPanel.add(buttonPanel);
        buttonPanel.add(centerHonzPanel);
        centerHonzPanel.add(AnswerTrue);
        centerHonzPanel.add(margPanel4);
        centerHonzPanel.add(AnswerFalse);
        centerHonzPanel.add(margPanel5);
        centerPanel.add(HonzSubPanel);
        HonzSubPanel.add(submit);
        HonzSubPanel.add(margPanel6);

        add(centerPanel);


        // buttonPanel.add(submit);
  
        // backPanel.add(retour, BorderLayout.EAST);  
        // backgroundPanel.add(buttonPanel);
        // backgroundPanel.add(backPanel, BorderLayout.SOUTH);
        
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.AddQuestion(input.getText(), AnswerTrue.isSelected());
                remove(buttonPanel);
                DisplayMenu();
                revalidate(); // Recalculer la disposition des composants
                repaint();
            }
        });
        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(buttonPanel);
                DisplayMenu();
                revalidate(); // Recalculer la disposition des composants
                repaint();

            }
        });
    }
    public static class RoundedBorder extends AbstractBorder {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.right = this.radius+1;
            insets.top = insets.bottom = this.radius+2;
            return insets;
        }
    }
    
    private void DisplayGame(){

        JPanel MainPanel = new JPanel();
        MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
        MainPanel.setOpaque(false);

        JPanel textJPanel = new JPanel();
        textJPanel.setLayout(new BoxLayout(textJPanel, BoxLayout.Y_AXIS));
        // textJPanel.setLayout(new FlowLayout());
        textJPanel.setOpaque(false);

        JPanel margiPanel = new JPanel();
        margiPanel.setBackground(new Color(0, 0, 0, 0));
        margiPanel.setMaximumSize(new Dimension(20, 20));

        JPanel margiPanel2 = new JPanel();
        margiPanel2.setBackground(new Color(0, 0, 0, 0));
        margiPanel2.setPreferredSize(new Dimension(20, 130));

        JPanel margiPanel3 = new JPanel();
        margiPanel3.setBackground(new Color(0, 0, 0, 0));
        margiPanel3.setPreferredSize(new Dimension(100, 130));

        JPanel margiPanel4 = new JPanel();
        margiPanel4.setBackground(new Color(0, 0, 0, 0));
        margiPanel4.setPreferredSize(new Dimension(10, 30));

        JPanel MainHonzPanel = new JPanel();
        MainHonzPanel.setLayout(new BoxLayout(MainHonzPanel, BoxLayout.X_AXIS));
        MainHonzPanel.setOpaque(false);

        trueOrFalsePanel = new JLabel("VRAI OU FAUX ?");
        trueOrFalsePanel.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 50));
        trueOrFalsePanel.setForeground(Color.WHITE);
        trueOrFalsePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 40));
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  

        ImageIcon trueIconOriginal = new ImageIcon("./TrueOrFalse/image/true.png");
        ImageIcon falseIconOriginal = new ImageIcon("./TrueOrFalse/image/false.png");

        Image trueImage = trueIconOriginal.getImage().getScaledInstance(75, 150, Image.SCALE_SMOOTH); 
        Image falseImage = falseIconOriginal.getImage().getScaledInstance(75, 150, Image.SCALE_SMOOTH);  
        ImageIcon trueIcon = new ImageIcon(trueImage);
        ImageIcon falseIcon = new ImageIcon(falseImage);
        
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        trueButton = new JButton();
        trueButton.setBorderPainted(false);
        trueButton.setFocusPainted(false);
        trueButton.setContentAreaFilled(false);
        trueButton.setIcon(trueIcon);

        falseButton = new JButton();
        falseButton.setBorderPainted(false);
        falseButton.setFocusPainted(false);
        falseButton.setContentAreaFilled(false);
        falseButton.setIcon(falseIcon);

        MainPanel.add(margiPanel2);
        MainPanel.add(textJPanel);
        textJPanel.add(trueOrFalsePanel);
        textJPanel.add(margiPanel);
        MainPanel.add(questionLabel);
        MainPanel.add(margiPanel4);
        buttonPanel.add(trueButton);
        buttonPanel.add(margiPanel3);
        buttonPanel.add(falseButton);
        MainPanel.add(MainHonzPanel);
        MainHonzPanel.add(buttonPanel);
        add(MainPanel);



        trueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAnswer(true);
            }
        });

        falseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAnswer(false);
            }
        });

        try {
            int size = db.getTableSize("TrueFalse");
            System.out.println("La taille de la table TrueFalse est : " + size);
            if (size==0){
                db.generateQuestions();
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la taille de la table");
            e.printStackTrace();
        }
        questionsReponses = db.GetQuestion();   
        displayNextQuestion();
    }

    // Classe interne pour le panneau avec image d'arrière-plan
    private class BackgroundPanel extends JPanel {
        private ImageIcon backgroundImage;

        public BackgroundPanel(ImageIcon backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Dessinez l'image en arrière-plan
            if (backgroundImage != null) {
                g.drawImage(backgroundImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
            }
        }
    }

    public static void main(String[] args) { 
        db = new DataBaseManager();
        db.CreateDBTrueFalse();
        TrueOrFalse TrueOrFalse = new TrueOrFalse();
        TrueOrFalse.setVisible(true);
    }

}
