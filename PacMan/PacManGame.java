package PacMan;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

public class PacManGame extends JFrame {
    public static Case[][] GridBackground;
    public JPanel[][] GridComponents;
    public JPanel PacManMovements;
    private Timer timer;
    public PacMan PacMan;
    public JLayeredPane LayeredPane;
    private JLabel TitleGame;
    private JPanel buttonPanel;
    private JButton StartGame;
    public static JLabel ScoreFrame;
    public static JPanel LifeBar;
    public static Ghost RedGhost;
    public static Ghost BlueGhost;
    public static Ghost GreenGhost;
    private int Life;
    public static int Score;
    public boolean AnimationLink = true;
    public static PacManGame pacman;
    public PacManGame(){
        String fontPath = "./The Wild Breath of Zelda.otf";
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        setResizable(false);
        DisplayMenu();
    }

    private void DisplayMenu(){
        TitleGame = new JLabel("Snake");
        add(TitleGame, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        pack();
        resize(500,500);
        setVisible(true);
    }


    private void DisplayGame(){
        Life = 3;
        
        GridBackground = new Case[28][31];
        GridComponents = new JPanel[28][31];
        PacMan = new PacMan();
        RedGhost = new Ghost("Red");
        BlueGhost = new Ghost("Blue");
        GreenGhost = new Ghost("Green");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        LayeredPane = new JLayeredPane();

        for(int i = 0;i < 28;i++){
            for(int j = 0;j < 31;j++){
                GridBackground[i][j] = new Case();
                GridBackground[i][j].panel = new JPanel();
                GridBackground[i][j].panel.setBackground(new Color(0,0,0));
                GridBackground[i][j].panel.setBounds(j * 30, i * 30, 30, 30);
                LayeredPane.add(GridBackground[i][j].panel, JLayeredPane.DEFAULT_LAYER);
            }
        }
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 31; j++) {
                GridComponents[i][j] = new JPanel();
                GridComponents[i][j].setOpaque(false);
                GridComponents[i][j].setBounds(j * 30, i * 30, 30, 30);
                LayeredPane.add(GridComponents[i][j], JLayeredPane.PALETTE_LAYER);
            }
        }
        
        PacMan.setOpaque(false);
        PacMan.setVisible(true);
        LayeredPane.add(PacMan, JLayeredPane.PALETTE_LAYER);

        BlueGhost.setOpaque(false);
        BlueGhost.setVisible(true);
        LayeredPane.add(BlueGhost, JLayeredPane.PALETTE_LAYER);

        GreenGhost.setOpaque(false);
        GreenGhost.setVisible(true);
        LayeredPane.add(GreenGhost, JLayeredPane.PALETTE_LAYER);

        RedGhost.setOpaque(false);
        RedGhost.setVisible(true);
        LayeredPane.add(RedGhost, JLayeredPane.PALETTE_LAYER);


        PacMan.setBounds(0, 0, 940, 870);
        PacMan.setBackground(new Color(0,0,0,0));

        RedGhost.setBounds(0, 0, 940, 870);
        RedGhost.setBackground(new Color(0,0,0,0));
        
        BlueGhost.setBounds(0, 0, 940, 870);
        BlueGhost.setBackground(new Color(0,0,0,0));
        
        GreenGhost.setBounds(0, 0, 940, 870);
        GreenGhost.setBackground(new Color(0,0,0,0));
        GenGrid();

        ScoreFrame = new JLabel("Score : 0");
        ScoreFrame.setFont(new Font("The Wild Breath of Zelda", Font.BOLD, 30));
        ScoreFrame.setForeground(Color.black);
        ScoreFrame.setBounds(290,0,200,50);
        LayeredPane.add(ScoreFrame,JLayeredPane.PALETTE_LAYER);

        LifeBar = new JPanel();
        LifeBar.setOpaque(false);
        LifeBar.setBounds(460,3,130,35);
        setImageOnPanel("./PacMan/Image/life3.png", LifeBar);

        LayeredPane.add(LifeBar,JLayeredPane.PALETTE_LAYER);
        add(LayeredPane, BorderLayout.CENTER);
        pack();
        setSize(940,870);

        setVisible(true);
        // PacMan.NextAnimation();
        timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean finish = false;
                for (int i = 0; i < 28; i++) {
                    for (int j = 0; j < 31; j++) {
                        if(GridBackground[i][j].GetPoint()){
                            finish = true;
                            break;
                        }
                    }
                    if (finish){
                        break;
                    }
                }
                if (!finish){
                    timer.stop();
                    TaWin();
                    return;
                }
                PacMan.Move();
                RedGhost.Move();
                GreenGhost.Move();
                BlueGhost.Move();
                ifDead();
                repaint();
            }
        });


        timer.start();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                            PacMan.SetTempDirection(1);
                        break;
                    case KeyEvent.VK_RIGHT:
                            PacMan.SetTempDirection(2);
                        break;
                    case KeyEvent.VK_DOWN:
                            PacMan.SetTempDirection(3);
                        break;
                    case KeyEvent.VK_LEFT:
                            PacMan.SetTempDirection(4);
                        break;
                }
            }
        });

        setFocusable(true);
        requestFocus();
    }

    public void GenGrid(){
        Random randomNumbers = new Random();
        int WichGrid = randomNumbers.nextInt(1)+1;
        String maze ="";
        int count = 0;
        switch (WichGrid) {
            case 1:    
                maze = "XXXXXXXXXXXXXXXXXXXXXXXXXXXX"+
                "X............XX............X"+
                "X.X XX.XXXXX.XX.XXXXX.XX X.X"+
                "X.X+XX.XXXXX.XX.XXXXX.XX+X.X"+
                "X.XXXX.XXXXX.XX.XXXXX.XXXX.X"+
                "X..........................X"+
                "X.XXXX.XX.XXXXXXXX.XX.XXXX.X"+
                "X.XXXX.XX.XXXXXXXX.XX.XXXX.X"+
                "X......XX....XX....XX......X"+
                "XXXXXX.XXXXX.XX.XXXXX.XXXXXX"+
                "XXXXXX.XXXXX.XX.XXXXX.XXXXXX"+
                "XX+   .XX..........XX.   +XX"+
                "XXXXXX.XX.XXX  XXX.XX.XXXXXX"+
                "XXXXXX.XX.XXX  XXX.XX.XXXXXX"+
                "X.........XXX  XXX.........X"+
                "XXXXXX.XX.XXX  XXX.XX.XXXXXX"+
                "XXXXXX.XX.XXXXXXXX.XX.XXXXXX"+
                "XXX+  .XX..........XX.  +XXX"+
                "XXXXXX.XX.XXXXXXXX.XX.XXXXXX"+
                "XXXXXX.XX.XXXXXXXX.XX.XXXXXX"+
                "X............XX............X"+
                "X.XXXX.XXXXX.XX.XXXXX.XXXX.X"+
                "X.XXXX.XXXXX.XX.XXXXX.XXXX.X"+
                "X...XX................XX...X"+
                "XXX.XX.XX.XXXXXXXX.XX.XX.XXX"+
                "XXX.XX.XX.XXXXXXXX.XX.XX.XXX"+
                "X......XX....XX....XX......X"+
                "X.XXXXXXXXXX.XX.XXXXXXXXXX.X"+
                "X.XXXXXXXXXX.XX.XXXXXXXXXX.X"+
                "X+........................+X"+
                "XXXXXXXXXXXXXXXXXXXXXXXXXXXX"+
                "XXXXXXXXXXXXXXXXXXXXXXXXXXXX";

         break;
            
        
            default:
                break;
        }
        // this.GridBackground[2][2].SetWall();
        for(int i = 0 ; i < 31;i++){
            for(int j = 0 ;j < 28;j++){
                count++;
                switch (maze.charAt(count-1)) {
                    case 'X':
                        GridBackground[j][i].SetWall();
                        break;
                    case '+':
                        GridBackground[j][i].SetPower();
                        break;
                    case '.':
                        GridBackground[j][i].SetPoint();
                        break;
                    default:

                        break;
                }
            }
        }
    }
    
    private boolean ifDead() {
        int pacManX = PacMan.GetX();
        int pacManY = PacMan.GetY();
    
        Ghost[] ghosts = {GreenGhost, BlueGhost, RedGhost};
    
        for (Ghost ghost : ghosts) {
            int ghostTop = ghost.GetY();
            int ghostBottom = ghost.GetY() + 25;
            int ghostLeft = ghost.GetX();
            int ghostRight = ghost.GetX() + 25;
    
            
            if (!(pacManY+25 < ghostTop || pacManY > ghostBottom || pacManX + 25 < ghostLeft || pacManX > ghostRight)) {
                if (PacMan.GetBuff() > 0) {
                    ghost.SetX(450);
                    ghost.SetY(410); 
                    Score+=500;
                    ScoreFrame.setText("Score : "+Score);
                    return false;
                } else {
                    ResetDeath();
                    return true;
                }

            }
        }
    
        return false;
    }
    



    public static void main(String[] args) {
        // SwingUtilities.invokeLater(() -> new PacManGame());
        pacman = new PacManGame();
    }
    public static void setImageOnPanel(String imagePath, JPanel panel) {
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
            panel.setLayout(new BorderLayout());
            panel.add(label, BorderLayout.CENTER);
            panel.revalidate();
            panel.repaint();
        } else {
            System.out.println("Image loading failed!");
        }
    }
    public static void removeImageFromPanel(JPanel panel) {
        panel.removeAll();
        // panel.setOpaque(true);
        panel.revalidate();
        panel.repaint();
        
    }

    private void ResetDeath(){

        Life--;
        removeImageFromPanel(LifeBar);
        setImageOnPanel("./PacMan/Image/life"+Life+".png", LifeBar);
        if(Life<= 0 ){
            TaLoose();
            timer.stop();
            return;
        }
        PacMan.SetX(810);
        PacMan.SetY(450);
        GreenGhost.SetX(450);
        GreenGhost.SetY(410);
        RedGhost.SetX(450);
        RedGhost.SetY(429);
        BlueGhost.SetX(410);
        BlueGhost.SetY(429);
    }
    private void TaWin() {
        getContentPane().removeAll();
        getContentPane().setBackground(new Color(255,127,127));
        
        // Réinitialiser le jeu
        DisplayGame();
    }
    

    private void TaLoose() {
        getContentPane().removeAll();
        getContentPane().setBackground(new Color(255,127,127));
        
        JLabel gameOverLabel = new JLabel("T'as Loose chien");
        gameOverLabel.setForeground(Color.WHITE);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 50));
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        add(gameOverLabel, BorderLayout.CENTER);
        Score = 0;
        revalidate(); 
        repaint();
    }
}



