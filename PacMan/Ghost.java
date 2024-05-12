package PacMan;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Ghost extends JPanel{
    private int PosX;
    private int PosY;
    private int Direction;
    private Color GhostColor;
    public Ghost(String color){
        int GhostX = 0;
        int GhostY = 0;
        Direction = 1;
        switch (color) {
            case "Red":
                GhostX = 450;
                GhostY = 429;
                GhostColor = Color.red;
                break;
            case "Blue":
                GhostX = 410;
                GhostY = 429;
                GhostColor = Color.blue;
                break;
            case "Green":
                GhostX = 450;
                GhostY = 410;
                GhostColor = Color.green;
                break;
        
            default:
                break;
        }
        this.PosX = GhostX;
        this.PosY = GhostY;
    }
    public int GetX(){
        return this.PosX;
    }
    public int GetY(){
        return this.PosY;
    }
    public void SetX(int X){
        this.PosX = X;
    }
    public void SetY(int Y){
        this.PosY = Y;
    }
    public void SetColor(Color color){
        this.GhostColor = color;
    }
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(this.GhostColor);
        g.drawImage(new ImageIcon("./PacMan/Image/ghost.png").getImage(), PosX, PosY, 25, 25,this);
    }


    public void Move(){
        Random randomNumbers = new Random();
        switch (Direction) {
            case 1:
                if(!InWall(PosX, PosY-5)){
                    this.PosY-=3;
                }else {
                    Direction = randomNumbers.nextInt(4)+1;
                }
                break;
            case 2:
                if(!InWall(PosX+5, PosY)){
                    this.PosX+=3;
                }else {
                    Direction = randomNumbers.nextInt(4)+1;
                }
                break;
            case 3:
                if(!InWall(PosX, PosY+5)){
                    this.PosY+=3;
                }else {
                    Direction = randomNumbers.nextInt(4)+1;
                }
                break;
            case 4:
                if(!InWall(PosX-5, PosY)){
                    this.PosX-=3;
                }else {
                    Direction = randomNumbers.nextInt(4)+1;
                }
                break;
            default:
                break;
        }
    }
    private boolean InWall(int PosX, int PosY) {
        int GridX = PosX / 30;
        int GridY = PosY / 30;
        boolean topLeft = PacManGame.GridBackground[GridY][GridX].Getwall();
        boolean topRight = PacManGame.GridBackground[GridY][(PosX + 24) / 30].Getwall();
        boolean bottomLeft = PacManGame.GridBackground[(PosY + 24) / 30][GridX].Getwall();
        boolean bottomRight = PacManGame.GridBackground[(PosY + 24) / 30][(PosX + 24) / 30].Getwall();
        return topLeft || topRight || bottomLeft || bottomRight;
    }

}
