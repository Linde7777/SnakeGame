package com.pl.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    public GamePanel(){
        init();
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }

    int len;
    int[] snakeX=new int[600];//coordinate X
    int[] snakeY=new int[500];
    int foodX;
    int foodY;
    boolean isStart=false;
    boolean isFail=false;
    int scores=0;
    String direction;
    Random random=new Random();
    Timer timer=new Timer(100,this);

    public void init(){
        foodX=25+25*random.nextInt(34);
        foodY=75+25*random.nextInt(24);
        len=3;
        direction="R";//R Right, L Left, U Up, D Down

        //the coordinate of the head
        snakeX[0]=100;
        snakeY[0]=100;

        //the first section of the body
        snakeX[1]=75;
        snakeY[1]=100;

        //the second section of the body
        snakeX[2]=50;
        snakeY[2]=100;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        g.fillRect(25,75,850,600);
        //Data.header.paintIcon(this,g,25,11);
        if(direction.equals("R")){
            Data.right.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        if(direction.equals("L")){
            Data.left.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        if(direction.equals("U")){
            Data.up.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        if(direction.equals("D")){
            Data.down.paintIcon(this,g,snakeX[0],snakeY[0]);
        }

        for(int i=1;i<len;i++){
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }

        Data.food.paintIcon(this,g,foodX,foodY);
        if(snakeX[0]==foodX&&snakeY[0]==foodY){
            len++;
            scores++;
            foodX=25+25*random.nextInt(34);
            foodY=75+25*random.nextInt(24);
        }

        for(int i=1;i<len;i++){
            if(snakeX[0]==snakeX[i]&&snakeY[0]==snakeY[i]){
                isFail=true;
                scores=0;
                g.setColor(Color.WHITE);
                g.setFont(new Font("default",Font.BOLD,40));
                g.drawString("Fail",300,300);
                timer.stop();
                repaint();
            }
        }

        if(!isStart){
            g.setColor(Color.WHITE);
            g.setFont(new Font("default",Font.BOLD,40));
            g.drawString("Enter space to start",300,300);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("default",Font.BOLD,18));
        g.drawString("Length: "+len,750,35);
        g.drawString("Scores: "+scores,750,50);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode=e.getKeyCode();
        if(keyCode==KeyEvent.VK_SPACE){
            isStart=!isStart;
            repaint();

            if(isFail){
                isFail=false;
                init();
                timer.start();
            }
        }
        if(keyCode==KeyEvent.VK_RIGHT){
            direction="R";
        }
        if(keyCode==KeyEvent.VK_LEFT){
            direction="L";
        }
        if(keyCode==KeyEvent.VK_UP){
            direction="U";
        }
        if(keyCode==KeyEvent.VK_DOWN){
            direction="D";
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(isStart){
            for(int i=len-1;i>0;i--){
                snakeX[i]=snakeX[i-1];
                snakeY[i]=snakeY[i-1];
            }

            if(direction.equals("R")){
                snakeX[0]+=25;
                if(snakeX[0]>850){
                    snakeX[0]=25;
                }
            }
            if(direction.equals("L")){
                snakeX[0]-=25;
                if(snakeX[0]<25){
                    snakeX[0]=850;
                }
            }
            if(direction.equals("U")){
                snakeY[0]-=25;
                if(snakeY[0]<0){
                    snakeY[0]=600;
                }
            }
            if(direction.equals("D")){
                snakeY[0]+=25;
                if(snakeY[0]>720){
                    snakeY[0]=100;
                }
            }
            repaint();
        }
        timer.start();
    }
}
