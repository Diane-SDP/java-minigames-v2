package NumberPuzzleGame;


import java.awt.*;
import java.awt.geom.AffineTransform;

import javax.swing.*;

public class Case {
    public JPanel panel;
    private int Number;

    public Case(){
        panel = new JPanel();
        Number = 0;
    }
    public int GetNumber(){
        return this.Number;
    }

    public void removePieceImage(JPanel button) {
        button.removeAll();
        button.revalidate();
        button.repaint();
    }
    public boolean SetNumber(int nb,JLabel text){
        this.Number = nb;
        if(nb != 0){
            text.setText(String.valueOf(nb));
            switch (nb) {
                case 2:
                    // this.panel.setBackground(new Color(238,228,218));
                    removePieceImage(this.panel);
                    setPieceImage("./image/newruby.png", this.panel);
        
                    break;
                case 4:
                    // this.panel.setBackground(new Color(237,224,200));
                    removePieceImage(this.panel);
                    setPieceImage("./image/bleu.png", this.panel);
                    break;
                case 8:
                    // this.panel.setBackground(new Color(242,177,121));
                    removePieceImage(this.panel);
                    setPieceImage("./image/rouge.png", this.panel);
                    break;
                case 16:
                    // this.panel.setBackground(new Color(245,149,99));
                    removePieceImage(this.panel);
                    setPieceImage("./image/violet.png", this.panel);
                    break;
                case 32:
                    // this.panel.setBackground(new Color(246,124,95));
                    removePieceImage(this.panel);
                    setPieceImage("./image/noir.png", this.panel);
                    break;
                case 64:
                    // this.panel.setBackground(new Color(246,94,59));
                    removePieceImage(this.panel);
                    setPieceImage("./image/jaune.png", this.panel);
                    break;
                case 128:
                    // this.panel.setBackground(new Color(236,205,114));
                    removePieceImage(this.panel);
                    setPieceImage("./image/orange.png", this.panel);
                    break;
                case 256:
                    removePieceImage(this.panel);
                    // this.panel.setBackground(new Color(237,204,97));
                    setPieceImage("./image/Fragment_de_Triforce_7.png", this.panel);
                    break;
                case 512:
                    removePieceImage(this.panel);
                    // this.panel.setBackground(new Color(236,199,80));
                    setPieceImage("./image/triforce1.png", this.panel);
                    break;
                case 1024:
                    removePieceImage(this.panel);
                    // this.panel.setBackground(new Color(236,196,65));
                    setPieceImage("./image/triforce2.png", this.panel);
                    break;
                case 2048:
                    removePieceImage(this.panel);
                    // this.panel.setBackground(new Color(237,193,46));
                    setPieceImage("./image/triforce3.png", this.panel);
                    return true;
            
                default:
                    break;
            }
        }else {
            text.setText(String.valueOf(""));
            this.panel.setBackground(new Color(154,138,110));
            removePieceImage(this.panel);

        }
        return false;
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
        } else {
            System.out.println("Image loading failed!");
        }
    }
}
