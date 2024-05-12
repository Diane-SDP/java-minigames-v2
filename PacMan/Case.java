package PacMan;

import java.awt.*;
import javax.swing.*;

public class Case {
    private boolean wall;
    private boolean point;
    private boolean PowerUp;
    public JPanel panel;

    public Case(){
        this.wall = false;
        this.panel = new JPanel();
        this.point = false;
        
    }
    public boolean Getwall(){
        return this.wall;
    }
    public void SetWall(){
        this.wall = true;
        // this.panel.setBorder(BorderFactory.createLineBorder(new Color(0,75,248)));
        // this.panel.setBackground(new Color(5,0,64));
        PacManGame.setImageOnPanel("./PacMan/Image/Wall.png",this.panel);
        this.panel.setBackground(new Color(0,0,0));
    }
    public boolean GetPoint(){
        return this.point;
    }
    public void SetPoint(){
        this.point = true;
        PacManGame.setImageOnPanel("./PacMan/Image/ruby.png",this.panel);
        this.panel.setBackground(new Color(0,0,0));
    }
    public boolean GetPower(){
        return this.PowerUp;
    }
    public void SetPower(){
        this.PowerUp = true;
        PacManGame.setImageOnPanel("./PacMan/Image/MS.png",this.panel);
        this.panel.setBackground(new Color(0,0,0));
    }
    public void EatPoint(){
        this.point = false;
        PacManGame.removeImageFromPanel(this.panel);
        // this.panel.setBackground(Color.black);
        // this.panel.repaint();
    }
    public void RemovePower(){
        this.PowerUp = false;
        PacManGame.removeImageFromPanel(this.panel);
    }
}
