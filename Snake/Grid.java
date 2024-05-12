package Snake;

import java.awt.*;
import java.awt.desktop.ScreenSleepEvent;

import javax.swing.*;

import FlappyBird.FlappyBird;
import Menu.SelectionWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Random;
import java.lang.Thread;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Grid extends JFrame{
    private JPanel[][] GridGame;
    private JPanel[][] ObjectsGame;
    private boolean color;
    private Snake Snake;
    private Timer timer;
    private Position ApplePosition;
    private int Score = 0;
    private JLabel ScoreText;
    private JLabel TitleGame;
    private JPanel buttonPanel;
    private JButton StartGame;
    public Grid(){
        setTitle("Snake");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));
        DisplayGame();
    }

    private Position AddApple(){
        Position ApplePosition = Position.GetRandomPosition();
        while (Snake.AppleInSnake(ApplePosition)){
            ApplePosition = Position.GetRandomPosition();
        }
        setPieceImage("./Snake/Image/larme.png", ObjectsGame[ApplePosition.x][ApplePosition.y], 50, 50, 0);
        ObjectsGame[ApplePosition.x][ApplePosition.y].setBackground(GridGame[ApplePosition.x][ApplePosition.y].getBackground());
        ObjectsGame[ApplePosition.x][ApplePosition.y].setOpaque(true);
        return ApplePosition;
    }
    private void YouLoose() {
            getContentPane().removeAll();
            
            setLayout(new BorderLayout());
            JLabel gameOverLabel = new JLabel("Game Over");
            gameOverLabel.setForeground(Color.red);
            gameOverLabel.setFont(new Font("The Wild Breath of Zelda", Font.BOLD, 50));
            gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
            
            JLabel ScoreLabel = new JLabel("Score : "+Score);
            ScoreLabel.setForeground(Color.red);
            ScoreLabel.setFont(new Font("The Wild Breath of Zelda", Font.BOLD, 50));
            ScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
            JButton Rejouer = new JButton("Rejouer");
            Rejouer.setForeground(Color.red);
            Rejouer.setFont(new Font("The Wild Breath of Zelda", Font.BOLD, 50));
            Rejouer.setHorizontalAlignment(SwingConstants.CENTER);
            Rejouer.setBorderPainted(false);
            Rejouer.setContentAreaFilled(false);
            Rejouer.setFocusPainted(false);
    
    
            JButton Quitter = new JButton("Quitter");
            Quitter.setForeground(Color.red);
            Quitter.setFont(new Font("The Wild Breath of Zelda", Font.BOLD, 50));
            Quitter.setHorizontalAlignment(SwingConstants.CENTER);
            Quitter.setBorderPainted(false);
            Quitter.setContentAreaFilled(false);
            Quitter.setFocusPainted(false);
    
    
            JPanel loosePanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
    
            loosePanel.add(gameOverLabel, gbc);
            loosePanel.add(ScoreLabel, gbc);
            loosePanel.add(Rejouer, gbc);
            loosePanel.add(Quitter, gbc);
            loosePanel.setOpaque(true);
            loosePanel.setBackground(new Color(0,0,0));
            add(loosePanel,BorderLayout.CENTER);
            Score = 0;
            Rejouer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(loosePanel);
                    DisplayGame();
                    revalidate();
                    repaint();
                }
            });
            Quitter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    SwingUtilities.invokeLater(() -> new SelectionWindow());
                }
            });
            revalidate(); 
            repaint();
    }

    private void DisplayMenu(){
        TitleGame = new JLabel("Snake");
        add(TitleGame, BorderLayout.NORTH);

        buttonPanel = new JPanel();
        StartGame = new JButton("Jouer");
        JButton Retour = new JButton("Retour");
        buttonPanel.add(StartGame);
        buttonPanel.add(Retour);
        
        add(buttonPanel, BorderLayout.SOUTH);
        StartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(buttonPanel);
                remove(TitleGame);
                DisplayGame();
                revalidate();
                repaint();
            }
        });
        setVisible(true);
    }

    private void DisplayGame(){
        GridGame = new JPanel[15][15];
        ObjectsGame = new JPanel[15][15];
        color = true;
        Snake = new Snake();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JLayeredPane layeredPane = new JLayeredPane();

        for(int i = 0;i < 15;i++){
            for(int j = 0;j < 15;j++){
                color = !color;
                GridGame[i][j] = new JPanel();
                if (color) {
                    GridGame[i][j].setBackground(new Color(170,215,81));
                }else{
                    GridGame[i][j].setBackground(new Color(162,209,73));
                }
                GridGame[i][j].setBounds(j * 50, i * 50, 50, 50);
                layeredPane.add(GridGame[i][j], JLayeredPane.DEFAULT_LAYER);
            }
        }
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                ObjectsGame[i][j] = new JPanel();
                ObjectsGame[i][j].setOpaque(false);
                ObjectsGame[i][j].setBounds(j * 50, i * 50, 50, 50);
                layeredPane.add(ObjectsGame[i][j], JLayeredPane.PALETTE_LAYER);
            }
        }

        ApplePosition = AddApple();
        ScoreText = new JLabel("Score : 0");
        layeredPane.add(ScoreText,JLayeredPane.RIGHT_ALIGNMENT);
        add(layeredPane, BorderLayout.CENTER);

        pack();
        setSize(768,790);
        setVisible(true);
        setPieceImage("./Snake/Image/tete.png",ObjectsGame[Snake.GetHeadPosition().x][Snake.GetHeadPosition().y],50,50,0);
        timer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Position LastTail;
                
                if (Snake.GetTailLenght() == 0){
                    LastTail = new Position(Snake.GetHeadPosition().x, Snake.GetHeadPosition().y);
                }else {
                    LastTail = new Position(Snake.GetTail().get(Snake.GetTailLenght()-1).x, Snake.GetTail().get(Snake.GetTailLenght()-1).y);
                }
                
                if(Snake.GetTail().size() != 0){
                    Snake.MoveTheTail();
                }

                DisplayHead();

                
 

                
                ObjectsGame[LastTail.x][LastTail.y].setOpaque(false);
                ObjectsGame[LastTail.x][LastTail.y].setBackground(Color.black);
                removePieceImage(ObjectsGame[LastTail.x][LastTail.y]);
                DisplayTail();


                if (Integer.valueOf(Snake.GetHeadPosition().x).equals(Integer.valueOf(ApplePosition.x)) && Integer.valueOf(Snake.GetHeadPosition().y).equals(Integer.valueOf(ApplePosition.y))){
                    Score++;
                    ScoreText.setText("Score : "+ Score);
                    Snake.AddToTail(LastTail.x,LastTail.y);
                    ObjectsGame[ApplePosition.x][ApplePosition.y].setOpaque(true);
                    // ObjectsGame[ApplePosition.x][ApplePosition.y].setBackground(new Color(69,115,232));
                    setPieceImage("./Snake/Image/traindroit.png",ObjectsGame[ApplePosition.x][ApplePosition.y],50,50,0);
                    ApplePosition = AddApple();
                }
            }
        });

        timer.start();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
  
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        if (Snake.GetDirection() != 3 ){
                            Snake.SetTempDirection(1);
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (Snake.GetDirection() != 4 ){
                            Snake.SetTempDirection(2);
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (Snake.GetDirection() != 1 ){
                            Snake.SetTempDirection(3);
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if (Snake.GetDirection() != 2 ){
                            Snake.SetTempDirection(4);
                        }
                        break;
                }
            }
        });

        setFocusable(true);
        requestFocus();
    }
   

    public static void setPieceImage(String imagePath, JPanel targetButton, int width, int height, double angle) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage();
        if (image.getWidth(null) == -1) {
            return;
        }
        
        // Rotation de l'image si un angle est spécifié
        if (angle != 0) {
            image = rotateImage(image, angle);
        }

        // Redimensionnement de l'image
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

    private static Image rotateImage(Image originalImage, double angle) {
        int width = originalImage.getWidth(null);
        int height = originalImage.getHeight(null);

        // Créer une image tampon pour effectuer la rotation
        BufferedImage rotatedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedImage.createGraphics();

        // Effectuer la rotation
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle), width / 2, height / 2);
        g2d.setTransform(transform);
        g2d.drawImage(originalImage, 0, 0, null);
        g2d.dispose();

        return rotatedImage;
    }
    private void DisplayHead(){
        ObjectsGame[Snake.GetHeadPosition().x][Snake.GetHeadPosition().y].setOpaque(false);
        if (Snake.GetHeadPosition().x > 14 || Snake.GetHeadPosition().x < 0 || Snake.GetHeadPosition().y > 14 ||Snake.GetHeadPosition().y < 0){
            timer.stop();
            YouLoose();
            return;
        }  
        removePieceImage(ObjectsGame[Snake.GetHeadPosition().x][Snake.GetHeadPosition().y]);
        Snake.Move();
        if (Snake.GetHeadPosition().x > 14 || Snake.GetHeadPosition().x < 0 || Snake.GetHeadPosition().y > 14 ||Snake.GetHeadPosition().y < 0 || Snake.EatHimSelf()){
            timer.stop();
            YouLoose();
            return;
        } 
        removePieceImage(ObjectsGame[Snake.GetHeadPosition().x][Snake.GetHeadPosition().y]);

        switch (Snake.GetTempDirection()) {
            case 1:
                setPieceImage("./Snake/Image/tete.png",ObjectsGame[Snake.GetHeadPosition().x][Snake.GetHeadPosition().y],50,50,0);
                break;
            case 2:
                setPieceImage("./Snake/Image/tete.png",ObjectsGame[Snake.GetHeadPosition().x][Snake.GetHeadPosition().y],50,50,90);
                break;
            case 3:
                setPieceImage("./Snake/Image/tete.png",ObjectsGame[Snake.GetHeadPosition().x][Snake.GetHeadPosition().y],50,50,180);
                break;
            case 4:
                setPieceImage("./Snake/Image/tete.png",ObjectsGame[Snake.GetHeadPosition().x][Snake.GetHeadPosition().y],50,50,270);
                break;
            default:
                break;
        }
        
        ObjectsGame[Snake.GetHeadPosition().x][Snake.GetHeadPosition().y].setOpaque(false);
    }
    private void DisplayTail(){
        for (int i = 0 ; i < Snake.GetTailLenght();i++){
            if(i==0){
                if(Snake.GetTail().get(i).x == Snake.GetHeadPosition().x){
                    setPieceImage("./Snake/Image/traindroit.png",ObjectsGame[Snake.GetTail().get(i).x][Snake.GetTail().get(i).y],50,50,0);
                }else{
                    setPieceImage("./Snake/Image/traindroit.png",ObjectsGame[Snake.GetTail().get(i).x][Snake.GetTail().get(i).y],50,50,90);
                }
            }else if (i == Snake.GetTailLenght()-1){
                if(Snake.GetTail().get(i-1).x == Snake.GetTail().get(i).x){
                    setPieceImage("./Snake/Image/traindroit.png",ObjectsGame[Snake.GetTail().get(i).x][Snake.GetTail().get(i).y],50,50,0);
                }else{
                    setPieceImage("./Snake/Image/traindroit.png",ObjectsGame[Snake.GetTail().get(i).x][Snake.GetTail().get(i).y],50,50,90);
                }
            }else{
                if(Snake.GetTail().get(i-1).x == Snake.GetTail().get(i+1).x){
                    setPieceImage("./Snake/Image/traindroit.png",ObjectsGame[Snake.GetTail().get(i).x][Snake.GetTail().get(i).y],50,50,0);
                }else{
                    setPieceImage("./Snake/Image/traindroit.png",ObjectsGame[Snake.GetTail().get(i).x][Snake.GetTail().get(i).y],50,50,90);
                }
            }
            
            ObjectsGame[Snake.GetTail().get(i).x][Snake.GetTail().get(i).y].setOpaque(true);
        }
    }
    public void removePieceImage(JPanel button) {
        button.removeAll();
        button.revalidate();
        button.repaint();
    }
    public static void main(String[] args) {
        new Grid();
    }
}