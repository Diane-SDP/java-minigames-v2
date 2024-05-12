package PacMan;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class PacMan extends JPanel{
    private int PosX;
    private int PosY;
    private int Direction;
    private int TempDirection;
   
    private int Buff;
    private boolean AnimationLink;
    private Image LinkB1;
    private Image LinkB2;
    private Image LinkD1;
    private Image LinkD2;
    private Image LinkG1;
    private Image LinkG2;
    private Image LinkH1;
    private Image LinkH2;
    private int ActualAnimation;
    private int AnimationPacMan;
    public PacMan(){
        this.PosX = 810;
        this.PosY = 450;
        this.Direction = 1; 
        this.TempDirection = 0;
        AnimationPacMan = 0;
        
        LinkB1 = new ImageIcon("./PacMan/Image/LinkB1.png").getImage();
        LinkB2 = new ImageIcon("./PacMan/Image/LinkB2.png").getImage();
        LinkD1 = new ImageIcon("./PacMan/Image/LinkD1.png").getImage();
        LinkD2 = new ImageIcon("./PacMan/Image/LinkD2.png").getImage();
        LinkG1 = new ImageIcon("./PacMan/Image/LinkG1.png").getImage();
        LinkG2 = new ImageIcon("./PacMan/Image/LinkG2.png").getImage();
        LinkH1 = new ImageIcon("./PacMan/Image/LinkH1.png").getImage();
        LinkH2 = new ImageIcon("./PacMan/Image/LinkH2.png").getImage();

        ActualAnimation = 1;
        setOpaque(false);
    }
    public void Move(){
        
        NextAnimation();
        switch (this.TempDirection) {
            case 1:
                if(!InWall(PosX, PosY-20)){
                    this.Direction = this.TempDirection;
                    this.PosY-=3;
                }else {
                    MoveStraight();
                }
                break;
            case 2:
                if(!InWall(PosX+20, PosY)){
                    this.Direction = this.TempDirection;
                    this.PosX+=3;
                }else {
                    MoveStraight();
                }
                break;
            case 3:
                if(!InWall(PosX, PosY+20)){
                    this.Direction = this.TempDirection;
                    this.PosY+=3;
                }else {
                    MoveStraight();
                }
                break;
            case 4:
                if(!InWall(PosX-20, PosY)){
                    this.Direction = this.TempDirection;
                    this.PosX-=3;
                }else {
                    MoveStraight();
                }
                break;
            default:
                break;
        }
        if(GetBuff()>0){
            SetBuff(GetBuff()-1);
        }else {
            PacManGame.RedGhost.SetColor(Color.red);
            PacManGame.BlueGhost.SetColor(Color.blue);
            PacManGame.GreenGhost.SetColor(Color.green);
        }
        
        int GridX = (PosX+12)/30 ;
        int GridY = (PosY+12)/30 ;
        if(PacManGame.GridBackground[GridY][GridX].GetPoint()){
            PacManGame.GridBackground[GridY][GridX].EatPoint();
            PacManGame.Score+=10;
            PacManGame.ScoreFrame.setText("Score : "+PacManGame.Score);

        }else if (PacManGame.GridBackground[GridY][GridX].GetPower()){
            SetBuff(400);
            PacManGame.RedGhost.SetColor(new Color(127,127,127));
            PacManGame.BlueGhost.SetColor(new Color(127,127,127));
            PacManGame.GreenGhost.SetColor(new Color(127,127,127));
            PacManGame.GridBackground[GridY][GridX].RemovePower();
        }

    }
    private void MoveStraight(){
        switch (this.Direction) {
            case 1:
                if(!InWall(PosX, PosY-5)){
                    this.PosY-=3;
                }
                break;
            case 2:
                if(!InWall(PosX+5, PosY)){
                    this.PosX+=3;
                }
                break;
            case 3:
                if(!InWall(PosX, PosY+5)){
                    this.PosY+=3;
                }
                break;
            case 4:
                if(!InWall(PosX-5, PosY)){
                    this.PosX-=3;
                }
                break;
            default:
                break;
        }
    }
    public void NextAnimation(){
        AnimationPacMan++;
        if(AnimationPacMan < 5 ){
            ActualAnimation = 1;
        }else if (AnimationPacMan < 10){
            ActualAnimation = 2;
        }else {
            AnimationPacMan=0;
            ActualAnimation = 1;
        }

    }
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(Color.black);
        g.drawImage(AnimationMovingLink(), PosX, PosY, 25, 25,this);
        

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
    
    private Image AnimationMovingLink(){
        switch (Direction) {
            case 1:
                if (ActualAnimation == 1){
                    return LinkH1;
                    
                }else {
                    return LinkH2;
                }

                
            case 2:
                if (ActualAnimation == 1){
                    return LinkD1;
                }else {
                    return LinkD2;
                }
               
            
            case 3:
                if (ActualAnimation == 1){
                    return LinkB1;
                    
                }else {
                    return LinkB2;
                }
                
            case 4:
                if (ActualAnimation == 1){
                    return LinkG1;
                }else {
                    return LinkG2;
                }
                
            
            default:
                break;
        }
        return null;
    }
    public int GetX(){
        return this.PosX;
    }
    public int GetY(){
        return this.PosY;
    }
    public void SetX(int x){
        this.PosX = x;
    }
    public void SetY(int y){
        this.PosY = y;
    }
    public int GetBuff(){
        return this.Buff;
    }
    public void SetBuff(int time){
        this.Buff = time;
    }
    public void SetDirection(int Direction){
        this.Direction = Direction;
    }
    public void SetTempDirection(int Direction){    
        this.TempDirection = Direction;
    }
    public int GetDirection(){
        
        return this.Direction;
    }
}
