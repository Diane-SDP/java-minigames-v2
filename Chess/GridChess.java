package Chess;

import java.awt.*;
import javax.swing.*;

import DataBase.DataBaseManager;
import Memory.Memory;
import Menu.*;
import NumberPuzzleGame.NumberPuzzleGame.RoundedPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class GridChess extends JFrame {
    public JPanel[][] BackgroundGrid;
    public Case[][] PiecesGrid;
    public JLayeredPane LayeredPane;
    public boolean Turn = true;
    private JLabel TitleGame;
    private RoundedPanel buttonPanel;
    private JButton StartGame;
    private Image backgroundImage;
    public GridChess() {
        DisplayMenu();
    }
    private void DisplayMenu(){
        String fontPath = "./The Wild Breath of Zelda.otf";
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }


        JPanel MarginPanel = new JPanel();
        MarginPanel.setMaximumSize(new Dimension(100,950));
        MarginPanel.setOpaque(false);;
        JPanel MainPanel = new JPanel();
        MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
        MainPanel.setOpaque(false);
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);
        setLayout(new BorderLayout());
        try {
            URL url = new URL("https://upload.cyen.fr/share/1715065372538466.png");
            backgroundImage = new ImageIcon(url).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
 
        // GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        // if (graphicsDevice.isFullScreenSupported()) {
        //     graphicsDevice.setFullScreenWindow(this);
        // } else {
        //     System.err.println("Le mode plein écran n'est pas pris en charge");
        //     dispose();
        // }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonPanel = new RoundedPanel(217,217,217,255);
        buttonPanel.setMaximumSize(new Dimension(400,90));
        buttonPanel.setBackground(new Color(217,217,217));
        StartGame = new JButton("Jouer");
        StartGame.setFont(new Font("The Wild Breath of Zelda", Font.BOLD, 70));
        StartGame.setContentAreaFilled(false);
        StartGame.setBorderPainted(false);   
        StartGame.setFocusPainted(false);

        // MainPanel.add(StartGame);
        // MainPanel.add(TitleGame);
        // JPanel RulesPanel = new JPanel();
        // RulesPanel.setBackground(new Color(24,70,50));
        // MainPanel.add(RulesPanel);
        MainPanel.add(MarginPanel);
        buttonPanel.add(StartGame);
        MainPanel.add(buttonPanel);
        
        add(MainPanel);

        // BackGround.add(buttonPanel, BorderLayout.SOUTH);
        
        StartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(MainPanel);
                remove(backgroundPanel);
                DisplayGame();
                revalidate();
                repaint();
            }
        });

        pack();
        setSize(1920,1080);
        setVisible(true);
    }
    private void DisplayGame(){
        
        BackgroundGrid = new JPanel[8][8];
        PiecesGrid = new Case[8][8];
        boolean RowColor = true;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        LayeredPane = new JLayeredPane();

        for(int i = 0; i < 8; i++) {
            RowColor = !RowColor;
            for(int j = 0; j < 8; j++) {
                RowColor = !RowColor;
                BackgroundGrid[i][j] = new JPanel();
                if (RowColor) {
                    BackgroundGrid[i][j].setBackground(new Color(238,238,210));
                } else {
                    BackgroundGrid[i][j].setBackground(new Color(118,130,86));
                }
                BackgroundGrid[i][j].setBounds(j * 130, i * 130, 130, 130);
                LayeredPane.add(BackgroundGrid[i][j], JLayeredPane.DEFAULT_LAYER);
            }
        }
        SetUpGrid();
        setResizable(false);
        setLocationRelativeTo(null);
        add(LayeredPane, BorderLayout.CENTER);
        pack();
        setSize(1055,1080);
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GridChess game = new GridChess();
                game.setVisible(true);
            }
        });
    }

    public static void setPieceImage(String imagePath, JPanel targetButton, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage();
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
    private void SetUpGrid() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                PiecesGrid[i][j] = new Case();
                PiecesGrid[i][j].Panel = new JButton();
                PiecesGrid[i][j].Panel.setOpaque(false);
                PiecesGrid[i][j].Panel.setBounds(j * 130, i * 130, 130, 130);
                PiecesGrid[i][j].Panel.setBorderPainted(false);
                PiecesGrid[i][j].Panel.setFocusPainted(false);
                PiecesGrid[i][j].Panel.setContentAreaFilled(false);
                PiecesGrid[i][j].Panel.setOpaque(false);
                PiecesGrid[i][j].Panel.addActionListener(new ShowMovement(i, j,this));
                LayeredPane.add(PiecesGrid[i][j].Panel, JLayeredPane.PALETTE_LAYER);
            }
        }
        setPieceImage("./Chess/Image/BlackPieces1/Rook.png", PiecesGrid[0][0].Panel);
        PiecesGrid[0][0].SetPiece("Rook");
        setPieceImage("./Chess/Image/BlackPieces1/Knight.png", PiecesGrid[0][1].Panel);
        PiecesGrid[0][1].SetPiece("Knight");
        setPieceImage("./Chess/Image/BlackPieces1/Bishop.png", PiecesGrid[0][2].Panel);
        PiecesGrid[0][2].SetPiece("Bishop");
        setPieceImage("./Chess/Image/BlackPieces1/Queen.png", PiecesGrid[0][3].Panel);
        PiecesGrid[0][3].SetPiece("Queen");
        setPieceImage("./Chess/Image/BlackPieces1/King.png", PiecesGrid[0][4].Panel);
        PiecesGrid[0][4].SetPiece("King");
        setPieceImage("./Chess/Image/BlackPieces1/Bishop.png", PiecesGrid[0][5].Panel);
        PiecesGrid[0][5].SetPiece("Bishop");
        setPieceImage("./Chess/Image/BlackPieces1/Knight.png", PiecesGrid[0][6].Panel);
        PiecesGrid[0][6].SetPiece("Knight");
        setPieceImage("./Chess/Image/BlackPieces1/Rook.png", PiecesGrid[0][7].Panel);
        PiecesGrid[0][7].SetPiece("Rook");
        for (int i = 0 ; i < 8;i++){
            setPieceImage("./Chess/Image/BlackPieces1/Pawn.png", PiecesGrid[1][i].Panel);
            PiecesGrid[1][i].SetPiece("Pawn");
            PiecesGrid[1][i].SetColor("Black");
        }
        for (int i = 0 ; i < 8;i++){
            PiecesGrid[0][i].SetColor("Black");
        }
        setPieceImage("./Chess/Image/WhitePieces1/Rook.png", PiecesGrid[7][0].Panel);
        PiecesGrid[7][0].SetPiece("Rook");
        setPieceImage("./Chess/Image/WhitePieces1/Knight.png", PiecesGrid[7][1].Panel);
        PiecesGrid[7][1].SetPiece("Knight");
        setPieceImage("./Chess/Image/WhitePieces1/Bishop.png", PiecesGrid[7][2].Panel);
        PiecesGrid[7][2].SetPiece("Bishop");
        setPieceImage("./Chess/Image/WhitePieces1/Queen.png", PiecesGrid[7][3].Panel);
        PiecesGrid[7][3].SetPiece("Queen");
        setPieceImage("./Chess/Image/WhitePieces1/King.png", PiecesGrid[7][4].Panel);
        PiecesGrid[7][4].SetPiece("King");
        setPieceImage("./Chess/Image/WhitePieces1/Bishop.png", PiecesGrid[7][5].Panel);
        PiecesGrid[7][5].SetPiece("Bishop");
        setPieceImage("./Chess/Image/WhitePieces1/Knight.png", PiecesGrid[7][6].Panel);
        PiecesGrid[7][6].SetPiece("Knight");
        setPieceImage("./Chess/Image/WhitePieces1/Rook.png", PiecesGrid[7][7].Panel);
        PiecesGrid[7][7].SetPiece("Rook");
        for (int i = 0 ; i < 8;i++){
            setPieceImage("./Chess/Image/WhitePieces1/Pawn.png", PiecesGrid[6][i].Panel);
            PiecesGrid[6][i].SetPiece("Pawn");
            PiecesGrid[6][i].SetColor("White");
        }
        for (int i = 0 ; i < 8;i++){
            PiecesGrid[7][i].SetColor("White");
        }
        
    }
    public void setPieceImage(String imagePath, JButton targetButton) {
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
        } else {
            System.out.println("Image loading failed!");
        }
    }
    public void removePieceImage(JButton button) {
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
    
}
