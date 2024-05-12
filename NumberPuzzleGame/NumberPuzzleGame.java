package NumberPuzzleGame;

import javax.swing.*;

import Menu.SelectionWindow;

import java.awt.*;
import java.util.Random;
import java.awt.event.*;



public class NumberPuzzleGame extends JFrame{
    private Image backgroundImage;
    private Case[][] BackgroundGrid;
    private JLabel[][] NumberGrid;
    private JLayeredPane LayeredPane;
    private int Score;
    private JLabel scoreValueLabel;
    // Constructeur
    public NumberPuzzleGame() {
        ImageIcon icon = new ImageIcon("./image/sheikavert.png");
        JLabel label = new JLabel(icon);
        add(label);

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);
        setLayout(new BorderLayout());
        backgroundImage = new ImageIcon("./image/sheikavert.png").getImage();

        Grid(); // Appel de la méthode Grid() dans le constructeur

    }

    public static class RoundedPanel extends JPanel {
        private static final int ARC_WIDTH = 15;
        private static final int ARC_HEIGHT = 15;
        private Color backgroundColor;
        private int opacity;
    
        public RoundedPanel(int r, int g, int b, int opacity) {
            super();
            this.backgroundColor = new Color(r, g, b, opacity);
            this.opacity = opacity;
            setOpaque(false); // Indique à Swing de ne pas peindre le fond du panneau
        }
    
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(backgroundColor);
    
            // Dessiner un rectangle arrondi rempli qui sert de fond
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

    public void Grid() {
        BackgroundGrid = new Case[4][4];
        NumberGrid = new JLabel[4][4];
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        JPanel marginPanel = new JPanel(); // top margin
        marginPanel.setMaximumSize(new Dimension(400, 50));
        marginPanel.setBackground(new Color(0, 0, 0,0));

        JPanel honzInfoPanel = new JPanel();
        honzInfoPanel.setLayout(new BoxLayout(honzInfoPanel, BoxLayout.X_AXIS));
        honzInfoPanel.setOpaque(false);

        JPanel vertGridPanel = new JPanel();
        vertGridPanel.setLayout(new BoxLayout(vertGridPanel, BoxLayout.X_AXIS));
        vertGridPanel.setOpaque(false);

        JPanel margPanel = new JPanel(); // left margin info panel
        margPanel.setMaximumSize(new Dimension(25, 50));
        margPanel.setBackground(new Color(0, 0, 0,0));


        JButton newGameButton = new JButton("New Game");
        newGameButton.setFont(new Font("The Wild Breath of Zelda", Font.BOLD, 20));
        newGameButton.setForeground(Color.WHITE);
        newGameButton.setBorderPainted(false);
        newGameButton.setFocusPainted(false);
        newGameButton.setContentAreaFilled(false);
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        newGameButton.setHorizontalTextPosition(SwingConstants.CENTER); 
        newGameButton.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });


        RoundedPanel infoPanel = new RoundedPanel(30, 30, 30, 255);
        infoPanel.setMaximumSize(new Dimension(450, 80));
        infoPanel.setLayout(new FlowLayout());


        JLabel scoreLabel = new JLabel("Score : ");
        scoreLabel.setForeground(new Color(30, 30,30, 255));
        scoreLabel.setFont(new Font("The Wild Breath of Zelda", Font.BOLD, 25));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        

        scoreValueLabel = new JLabel(); 
        scoreValueLabel.setForeground(Color.WHITE);  
        scoreValueLabel.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 20)); 
        scoreValueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel marginPanel5 = new JPanel(); // margin pour le score
        marginPanel5.setPreferredSize(new Dimension(10, 15));
        marginPanel5.setBackground(new Color(0, 0, 0,0));
        
        RoundedPanel scorePanel = new RoundedPanel(154, 138, 110, 255);
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scorePanel.setPreferredSize(new Dimension(200, 70));


        JPanel marginPanel2 = new JPanel(); //separation score and game
        marginPanel2.setPreferredSize(new Dimension(50, 10));
        marginPanel2.setBackground(new Color(0, 0, 0,0));

        RoundedPanel NewGamePanel = new RoundedPanel(154, 138, 110, 255);
        NewGamePanel.setPreferredSize(new Dimension(100, 70));
        NewGamePanel.setLayout(new FlowLayout());


        JPanel marginPanel3 = new JPanel(); //separation entre le jeu et le score
        marginPanel3.setMaximumSize(new Dimension(100, 75));
        marginPanel3.setBackground(new Color(0, 0, 0,0));
        
        RoundedPanel grillPanel = new RoundedPanel(30, 30, 30, 255);
        grillPanel.setMaximumSize(new Dimension(515, 535));
        grillPanel.setLayout(new FlowLayout());


        JPanel grillhonzPanel = new JPanel();
        grillhonzPanel.setLayout(new BoxLayout(grillhonzPanel, BoxLayout.X_AXIS));
        grillhonzPanel.setOpaque(false);

        JPanel margPanel2 = new JPanel(); // left margin info panel
        margPanel2.setMaximumSize(new Dimension(25, 50));
        margPanel2.setBackground(new Color(0, 0, 0,0));

        JPanel margPanel3 = new JPanel(); // left margin info panel
        margPanel3.setMaximumSize(new Dimension(400, 50));
        margPanel3.setBackground(new Color(0, 0, 0,0));

        JPanel honzExitPanel = new JPanel();
        honzExitPanel.setLayout(new BoxLayout(honzExitPanel, BoxLayout.X_AXIS));
        honzExitPanel.setOpaque(false);

        JButton btnExit = new JButton();
            btnExit.setBorderPainted(false);
            btnExit.setFocusPainted(false);
            btnExit.setContentAreaFilled(false);
            btnExit.setText("Retour");
            btnExit.setFont(new Font("The Wild Breath of Zelda", Font.PLAIN, 25));
            btnExit.setForeground(Color.WHITE);
            btnExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    SwingUtilities.invokeLater(() -> new SelectionWindow());
                }
            });

        LayeredPane = new JLayeredPane();
        LayeredPane.setPreferredSize(new Dimension(500, 500));
        int cellSize = 120; 
        int spacing = 5;  
        int offset = cellSize + spacing;
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                BackgroundGrid[i][j] = new Case();
                BackgroundGrid[i][j].panel = new JPanel();
                BackgroundGrid[i][j].panel.setBounds(j * offset, i * offset, cellSize, cellSize);

                // BackgroundGrid[i][j].panel.setBounds(j * 120, i * 120, 120, 120);
                BackgroundGrid[i][j].panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                BackgroundGrid[i][j].panel.setBackground(new Color(154,138,110));
                LayeredPane.add(BackgroundGrid[i][j].panel, JLayeredPane.DEFAULT_LAYER);
            }
        }
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                NumberGrid[i][j] = new JLabel();
                NumberGrid[i][j].setBounds(j * offset, i * offset, cellSize, cellSize);
                NumberGrid[i][j].setFont(new Font("The Wild Breath of Zelda", Font.BOLD, 20));
                NumberGrid[i][j].setForeground(Color.WHITE);
                // NumberGrid[i][j].setBounds(j * 120, i * 120, 120, 120);
                NumberGrid[i][j].setHorizontalAlignment(JLabel.RIGHT);
                NumberGrid[i][j].setVerticalAlignment(JLabel.BOTTOM);
                NumberGrid[i][j].setBackground(new Color(154,138,110,0));
                NumberGrid[i][j].setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
                LayeredPane.add(NumberGrid[i][j], JLayeredPane.PALETTE_LAYER);
            }
        }

        Random randomNumbers = new Random();
        int coX1 = randomNumbers.nextInt(4);
        int coY1 = randomNumbers.nextInt(4);
        int coX2 = randomNumbers.nextInt(4);
        int coY2 = randomNumbers.nextInt(4);
        while(coX1 == coX2 && coY1 == coY2){
            coX2 = randomNumbers.nextInt(4);
            coY2 = randomNumbers.nextInt(4); 
        }
        BackgroundGrid[coX1][coY1].SetNumber(2,NumberGrid[coX1][coY1]);
        BackgroundGrid[coX2][coY2].SetNumber(2,NumberGrid[coX2][coY2]);

        centerPanel.add(marginPanel);
        centerPanel.add(honzInfoPanel);
        honzInfoPanel.add(margPanel2);
        honzInfoPanel.add(infoPanel);
        infoPanel.add(scorePanel);
        infoPanel.add(scorePanel);
        scorePanel.add(scoreLabel);
        scorePanel.add(scoreValueLabel);
        infoPanel.add(marginPanel2);
        infoPanel.add(NewGamePanel);
        NewGamePanel.add(newGameButton);
        centerPanel.add(marginPanel3);
        centerPanel.add(vertGridPanel);
        vertGridPanel.add(margPanel);
        vertGridPanel.add(grillPanel);
        grillPanel.add(grillhonzPanel);
        grillhonzPanel.add(LayeredPane);
        centerPanel.add(honzExitPanel);
        honzExitPanel.add(margPanel3);
        honzExitPanel.add(btnExit);




        add(centerPanel);

        setSize(600, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                ActionKeyListener(e);
            }
        });

        setFocusable(true);

    }
    
    private void reset(){
        BackgroundGrid = new Case[4][4];
        NumberGrid = new JLabel[4][4];
        for(int i = 0 ; i < 4 ; i++){
            for(int j = 0 ; j < 4 ; j++){
                BackgroundGrid[i][j] = new Case();
                NumberGrid[i][j] = new JLabel();
            }
        }
        Random randomNumbers = new Random();
        int coX1 = randomNumbers.nextInt(4);
        int coY1 = randomNumbers.nextInt(4);
        int coX2 = randomNumbers.nextInt(4);
        int coY2 = randomNumbers.nextInt(4);
        while(coX1 == coX2 && coY1 == coY2){
            coX2 = randomNumbers.nextInt(4);
            coY2 = randomNumbers.nextInt(4); 
        }
        BackgroundGrid[coX1][coY1].SetNumber(2,NumberGrid[coX1][coY1]);
        BackgroundGrid[coX2][coY2].SetNumber(2,NumberGrid[coX2][coY2]);
        
    }

    private void SpawnNb(){
        Random randomNumbers = new Random();
        int randomNb = randomNumbers.nextInt(2);
        int NbToAdd = 0;
        switch (randomNb) {
            case 0:
                NbToAdd = 2;
                break;
            case 1:
                NbToAdd = 4;
                break;
            default:
                System.out.println("Bad nb Gene for spawn nb");
                break;
        }
        while(true){
            int coX = randomNumbers.nextInt(4);
            int coY = randomNumbers.nextInt(4);
            if(BackgroundGrid[coX][coY].GetNumber() == 0){
                BackgroundGrid[coX][coY].SetNumber(NbToAdd,NumberGrid[coX][coY]);
                return;
            }
        }
       
    }
    private void TaLoose() {
        getContentPane().removeAll();
        getContentPane().setBackground(new Color(255,127,127));
        
        JLabel gameOverLabel = new JLabel("T'as Loose chien");
        gameOverLabel.setForeground(Color.WHITE);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 50));
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        add(gameOverLabel, BorderLayout.CENTER);
        
        revalidate(); 
        repaint();
    }
    private boolean Loose(){
        for (int i = 0 ; i < 4;i++){
            for (int j = 0 ; j < 4;j++){
                System.out.println(BackgroundGrid[i][j].GetNumber());
                if(BackgroundGrid[i][j].GetNumber()==0){
                    return false;
                }
            }
        }
        return true;
    }
    private void ActionKeyListener(KeyEvent e){
        int keyCode = e.getKeyCode();
        System.out.println("Score : "+Score);
        scoreValueLabel.setText(String.valueOf(Score));
        switch (keyCode) {
            case KeyEvent.VK_UP:
                System.out.println("UP");
                for(int p = 0 ; p < 4;p++){
                for(int i = 1 ; i < 4 ;i++){
                    for(int j = 0 ; j < 4 ;j++){
                        // BackgroundGrid[i+1][j].panel.setBackground(Color.red);
                        if(BackgroundGrid[i-1][j].GetNumber()==0){
                            BackgroundGrid[i-1][j].SetNumber(BackgroundGrid[i][j].GetNumber(),NumberGrid[i-1][j]);
                            BackgroundGrid[i][j].SetNumber(0,NumberGrid[i][j]);
                            BackgroundGrid[i][j].panel.repaint();    
                        }else if(BackgroundGrid[i][j].GetNumber() == BackgroundGrid[i-1][j].GetNumber()){
                            BackgroundGrid[i][j].SetNumber(0,NumberGrid[i][j]);
                            Score+=BackgroundGrid[i-1][j].GetNumber()*2;
                            if (BackgroundGrid[i-1][j].SetNumber(BackgroundGrid[i-1][j].GetNumber()*2,NumberGrid[i-1][j])){
                                System.out.println("win");
                            }
                            
                        }
                    }
                }
            }
            if( Loose()){
                TaLoose();
                return;
            }
                SpawnNb();
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("RIGHT");
                for(int p = 0 ; p < 4;p++){
                    for(int i = 0 ; i < 4 ;i++){
                        for(int j = 0 ; j < 3 ;j++){
                            // BackgroundGrid[i+1][j].panel.setBackground(Color.red);
                            if(BackgroundGrid[i][j+1].GetNumber()==0){
                                BackgroundGrid[i][j+1].SetNumber(BackgroundGrid[i][j].GetNumber(),NumberGrid[i][j+1]);
                                BackgroundGrid[i][j].SetNumber(0,NumberGrid[i][j]);
                                BackgroundGrid[i][j].panel.repaint();    
                            }else if(BackgroundGrid[i][j].GetNumber() == BackgroundGrid[i][j+1].GetNumber()){
                                BackgroundGrid[i][j].SetNumber(0,NumberGrid[i][j]);
                                Score+=BackgroundGrid[i][j+1].GetNumber()*2;
                                if (BackgroundGrid[i][j+1].SetNumber(BackgroundGrid[i][j+1].GetNumber()*2,NumberGrid[i][j+1])){
                                    System.out.println("win");
                                }
                                
                            }
                        }
                    }
                }
                // for(int i = 2 ; i >= 0 ;i--){
                //     for(int j = 0 ; j < 4 ;j++){
                //         int casvide = 4;
                //         if (BackgroundGrid[i][j].GetNumber() == 0){
                //             continue;
                //         }
                //         while(casvide-i > 0){
                //             casvide--;
                //             if(BackgroundGrid[i][casvide].GetNumber() == 0){
                //                 int beforenb = BackgroundGrid[i][j].GetNumber();
                //                 BackgroundGrid[i][casvide].SetNumber(beforenb,NumberGrid[i][casvide]);
                //                 System.out.println("number of new emplacement : "+BackgroundGrid[i][casvide].GetNumber());
                //                 BackgroundGrid[i][j].SetNumber(0,NumberGrid[i][j]);
                //                 System.out.println("number of new emplacement : "+BackgroundGrid[i][casvide].GetNumber());
                //                 BackgroundGrid[i][j].panel.repaint();
                //                 BackgroundGrid[i][casvide].panel.repaint();
                //                 break;
                //             }else if (BackgroundGrid[i][casvide].GetNumber() == BackgroundGrid[i][j].GetNumber()){
                //                 BackgroundGrid[i][j].SetNumber(0,NumberGrid[i][j]);
                //                 Score+=BackgroundGrid[i][casvide].GetNumber()*2;
                //                 if (BackgroundGrid[i][casvide].SetNumber(BackgroundGrid[i][casvide].GetNumber()*2,NumberGrid[i][casvide])){
                //                     System.out.println("win");
                //                 }
                //                 BackgroundGrid[i][j].panel.repaint();
                //                 BackgroundGrid[i][casvide].panel.repaint();
                //                 break;
                //             }
                //         }
                //     }
                // }
                if( Loose()){
                    TaLoose();
                    return;
                }
                // SpawnNb();
               
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("DOWN");
                for(int p = 0 ; p < 4;p++){
                for(int i = 0 ; i < 3 ;i++){
                    for(int j = 0 ; j < 4 ;j++){
                        // BackgroundGrid[i+1][j].panel.setBackground(Color.red);
                        if(BackgroundGrid[i+1][j].GetNumber()==0){
                            BackgroundGrid[i+1][j].SetNumber(BackgroundGrid[i][j].GetNumber(),NumberGrid[i+1][j]);
                            BackgroundGrid[i][j].SetNumber(0,NumberGrid[i][j]);
                            BackgroundGrid[i][j].panel.repaint();    
                        }else if(BackgroundGrid[i][j].GetNumber() == BackgroundGrid[i+1][j].GetNumber()){
                            BackgroundGrid[i][j].SetNumber(0,NumberGrid[i][j]);
                            Score+=BackgroundGrid[i+1][j].GetNumber()*2;
                            if (BackgroundGrid[i+1][j].SetNumber(BackgroundGrid[i+1][j].GetNumber()*2,NumberGrid[i+1][j])){
                                System.out.println("win");
                            }
                            
                        }
                    }
                }
            }   
            if( Loose()){
                TaLoose();
                return;
            }
                SpawnNb();
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("LEFT");
                for(int p = 0 ; p < 4;p++){
                for(int i = 0 ; i < 4 ;i++){
                    for(int j = 1 ; j < 4 ;j++){
                        // BackgroundGrid[i+1][j].panel.setBackground(Color.red);
                        if(BackgroundGrid[i][j-1].GetNumber()==0){
                            BackgroundGrid[i][j-1].SetNumber(BackgroundGrid[i][j].GetNumber(),NumberGrid[i][j-1]);
                            BackgroundGrid[i][j].SetNumber(0,NumberGrid[i][j]);
                            BackgroundGrid[i][j].panel.repaint();    
                        }else if(BackgroundGrid[i][j].GetNumber() == BackgroundGrid[i][j-1].GetNumber()){
                            BackgroundGrid[i][j].SetNumber(0,NumberGrid[i][j]);
                            Score+=BackgroundGrid[i][j-1].GetNumber()*2;
                            if (BackgroundGrid[i][j-1].SetNumber(BackgroundGrid[i][j-1].GetNumber()*2,NumberGrid[i][j-1])){
                                System.out.println("win");
                            }
                            
                        }
                    }
                }
            }
            if( Loose()){
                TaLoose();
                return;
            }
                SpawnNb();
                break;
        }
    
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NumberPuzzleGame());
    }
}
