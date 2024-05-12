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

    public FlappyBird() {
        super("Flappy Bird");
        DisplayMenu();
    }
    private void DisplayMenu(){
        setSize(400, 400);
        JLabel TitleGame = new JLabel("Snake");
        add(TitleGame, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton StartGame = new JButton("Jouer");
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
        
        setSize(1000, 800);
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
                System.out.println("paf");
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
                    System.out.println("new obstacles");
                    obstacles.add(new Obstacle(getWidth(), gapPosition));
                    Thread.sleep(3000); 
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

    private void moveObstacles() {
        // System.out.println(obstacles);
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
        System.out.println(obstacles);
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
            if (birdY > 600 || birdY < 0) {
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
            System.out.println("truc");
            super.paintComponent(g);
            g.drawImage(ActualImage, 50, birdY, 100, 100, this);

            for (Obstacle obstacle : obstacles) {
                obstacle.draw(g);
            }

            if (gameOver) {
                g.setColor(Color.RED);
                g.setFont(new Font("Arial", Font.BOLD, 40));
                g.drawString("GAME OVER", getWidth() / 2 - 120, getHeight() / 2);
            }
        }

    }

    class Obstacle {
        private int x, y, width, height;
        private Boolean isPassed = false;
        private int speed = 3;

        public Obstacle(int x, int y) {
            this.x = x;
            this.y = y;
            this.width = 50;
            this.height = 200;
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
            g.fillRect(x, 0, width, y);
            g.fillRect(x, y + height, width, getHeight() - (y + height));
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
