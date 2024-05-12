package FlappyBird;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class FlappyBird extends JFrame {

    private Bird bird;
    private List<Obstacle> obstacles = new CopyOnWriteArrayList<>();
    private boolean gameOver;
    private int score = 0;
    private JLabel DisplayScore = new JLabel("0");
    private Thread obstacleThread;
    private Image backgroundImage;
    public FlappyBird() {
        super("Flappy Bird");
        DisplayMenu();
    }
    
    private void DisplayMenu(){
        DisplayGame();
    }
    private void DisplayGame(){
        ImageIcon icon = new ImageIcon("./FlappyBird/Image/BG.png");
        backgroundImage = icon.getImage();
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);
        setLayout(new BorderLayout());

        setSize(1200, 1000);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bird = new Bird();
        obstacles = new ArrayList<>();
        gameOver = false;
        add(DisplayScore, BorderLayout.NORTH);
        add(bird);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (gameOver) {
                        
                        restartGame();
                        
                    } else {
                        bird.jump();
                    }
                }
            }
        });
        requestFocus();
        setVisible(true);
        startGame();
    }
    private void startGame() {
        obstacleThread = new Thread(() -> {
            Random rand = new Random();
            while (!gameOver) {
                try {
                    
                    int gapPosition = rand.nextInt(getHeight() - 120);
                    obstacles.add(new Obstacle(getWidth(), gapPosition));
                    Thread.sleep(6000); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        obstacleThread.start();

        Thread obstacleMoveThread = new Thread(() -> {
            while (!gameOver) {
                try {
                    Thread.sleep(20); 
                    moveObstacles();
                    checkCollisions();
                    repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        obstacleMoveThread.start();
    }
    private class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            } 
        }
    }
    
    private void moveObstacles() {
        Iterator<Obstacle> iterator = obstacles.iterator();
        while (iterator.hasNext()) {
            Obstacle obstacle = iterator.next();
            obstacle.move();
            if (obstacle.getX() + obstacle.getWidth() < 0) {
                iterator.remove(); 
            } else if (obstacle.getX() + obstacle.getWidth() < 50 && !obstacle.isPassed) {
                score++;
                DisplayScore.setText(String.valueOf(score));
                obstacle.setIsPassed();
            } 

        }
    }

    private void restartGame() {
        bird.reset();
        obstacles.clear();
        gameOver = false;
        score = 0;
        obstacleThread.stop();
        DisplayScore.setText("0");
        repaint();
        startGame();
    }
    private void checkCollisions() {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.intersects(bird) || bird.isOut()) {
                gameOver = true;
                break;
            }
        }
    }

    

    public static void main(String[] args) {
        new FlappyBird();
    }

    class Bird extends JPanel {
        private int birdY = 100;
        private int birdVelocity = 0;
        private int gravity = 1;
        private int AnimationMojo;
        private Image ActualImage;
        private Image MojoImage1;
        private Image MojoImage2;
        private Image MojoImage3;

        public Bird() {
            AnimationMojo = 0;
            MojoImage1 = new ImageIcon("./FlappyBird/Image/mojo1.png").getImage();
            MojoImage2 = new ImageIcon("./FlappyBird/Image/mojo2.png").getImage();
            MojoImage3 = new ImageIcon("./FlappyBird/Image/mojo3.png").getImage();
            ActualImage = MojoImage1;
            Thread gameThread = new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(20);
                        if (!gameOver) {
                            update();
                            checkCollisions();
                        }
                        repaint();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            gameThread.start();
        }

        private void update() {
            birdVelocity += gravity;
            birdY += birdVelocity;
            NextAnimation();
        }

        public void jump() {
            birdVelocity = -10;
        }

        public void reset() {
            birdY = 100;
            birdVelocity = 0;
        }

        public boolean isOut() {
            if (birdY > 1000 || birdY < 0) {
                return true;
            }
            return false;
        }
        private void NextAnimation(){
            AnimationMojo++;
            if(AnimationMojo < 5 ){
                ActualImage = MojoImage1;
            }else if (AnimationMojo < 10){
                ActualImage = MojoImage2;
            }else if (AnimationMojo < 15){
                ActualImage = MojoImage3;
            }else {
                AnimationMojo=0;
                ActualImage = MojoImage1;
            }

        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(ActualImage, 50, birdY, 80, 80, this);

            for (Obstacle obstacle : obstacles) {
                obstacle.draw(g);
            }

            if (gameOver) {
                g.setColor(Color.RED);
                g.setFont(new Font("Arial", Font.BOLD, 40));
                g.drawString("Perdu Tu as fait "+score+" score", getWidth() / 2 - 120, getHeight() / 2);
            }
        }

    }

    class Obstacle {
        private int x, y, width, height;
        private Boolean isPassed = false;
        private int speed = 3;
        private Image UpTree;
        private Image DownTree;
        public Obstacle(int x, int y) {
            this.x = x;
            this.y = y;
            this.width = 130;
            this.height = 200;
            UpTree = new ImageIcon("./FlappyBird/Image/uptree.png").getImage();
            DownTree = new ImageIcon("./FlappyBird/Image/downtree.png").getImage();
        }

        public void move() {
            x -= speed;
        }

        public boolean intersects(Bird bird) {
            int birdX = 50; 
            int birdY = bird.birdY; 
            int birdWidth = 30; 
            int birdHeight = 30;
            
            if (birdX + birdWidth < x || birdX > x + width) {
                return false;
            }
            
            if (birdY + birdHeight - 30 < y || birdY > y + height - 30) {
                return true; 
            }
            return false;
        }
        
        

        public void draw(Graphics g) {
            g.setColor(Color.GREEN);
            g.drawImage(UpTree, x, 0, width, y, null);
            g.drawImage(DownTree, x, y + height, width, getHeight() - (y + height),null);

        }

        public int getX() {
            return x;
        }

        public boolean getIsPassed() {
            return isPassed;
        }


        public void setIsPassed() {
            isPassed = !isPassed;
        }

        public int getWidth() {
            return width;
        }
    }
}
