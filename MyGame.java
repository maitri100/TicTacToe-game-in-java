
package com.mygame;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class MyGame extends JFrame implements  ActionListener {
    
    JLabel heading, clockLabel;
    Font font=new Font("",Font.BOLD,40);
    JPanel mainPanel;
    
    JButton btns[]=new JButton[9];
    
    
    //game instance variables..
    int gameChances[]={2,2,2,2,2,2,2,2,2};
    int activePlayer=0;
    int wps[][]={
        {0,1,2},
        {3,4,5},
        {6,7,8},
        {0,3,6},
        {1,4,7},
        {2,5,8},
        {0,4,8},
        {2,4,6}
    };
    int winner=2;
    boolean gameOver=false;
    
    
    MyGame(){
        System.out.append("Creating instance of game");
        setTitle("My Tic Tac Toe Game..");
        setSize(850,850);
        ImageIcon icon=new ImageIcon("src/main/java/img/01.png");
        setIconImage(icon.getImage());
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGUI();
        setVisible(true);
        
    }
    private void createGUI(){
        this.getContentPane().setBackground(Color.decode("#2196f3"));
        this.setLayout(new BorderLayout());
        //north heading/..
        heading=new JLabel("Tic Tac Toe");
        heading.setFont(font);
        //heading.setIcon(new ImageIcon("src/main/java/img/01.png"));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        heading.setForeground(Color.white);
        
        this.add(heading,BorderLayout.NORTH);
        
        //creating object of Jlabel for clock
        clockLabel=new JLabel("Clock");
        clockLabel.setFont(font);
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clockLabel.setForeground(Color.white);

        this.add(clockLabel,BorderLayout.SOUTH);
        
        Thread t=new Thread(){
            public void run(){
                try{
                    while(true){
                    String datetime=new Date().toString();
                    clockLabel.setText(datetime);
                    
                    Thread.sleep(1000);
                            
                            
                }
                } catch(Exception e){
                    
                    e.printStackTrace();
                }
            }
        };
        t.start();
        
        ////////////Panel section...
        mainPanel=new JPanel();
        mainPanel.setLayout(new GridLayout(3,3));
        for(int i=1;i<=9;i++){
            JButton btn=new JButton();
            btn.setBackground(Color.decode("#90caf9"));
            btn.setFont(font);
            mainPanel.add(btn);
            btns[i-1]=btn;
            btn.addActionListener(this);
            btn.setName(String.valueOf(i-1));
        }
        this.add(mainPanel,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      JButton currentButton=(JButton)e.getSource();
      String nameStr=currentButton.getName();
      
      int name=Integer.parseInt(nameStr.trim());
      
      if(gameOver){
          JOptionPane.showMessageDialog(this,"Game Already Over..");
      }
      
      
      
      if(gameChances[name]==2){
          if(activePlayer==1){
              currentButton.setIcon(new ImageIcon("src/main/java/img/1.jpeg"));
              gameChances[name]=activePlayer;
              activePlayer=0;
          }else{
              currentButton.setIcon(new ImageIcon("src/main/java/img/0.png"));
              gameChances[name]=activePlayer;
              activePlayer=1;
          }
          
          
          //find the winner
          for(int temp[]:wps){
              if((gameChances[temp[0]]==gameChances[temp[1]])&&(gameChances[temp[1]]       ==gameChances[temp[2]])&&gameChances[temp[2]]!=2)
              {
                  winner=gameChances[temp[0]];
                  gameOver=true;
                  JOptionPane.showMessageDialog(null,"Player"+winner+"has won the game...");
                  int i=JOptionPane.showConfirmDialog(this,"do you want to play more??");
                  if(i==0){
                      this.setVisible(false);
                      new MyGame();
                  }else if(i==1){
                      System.exit(3454);
                  }else{
                      
                  }
                  System.out.println(i);
                  break;
              }
          }
          
          //draw logic
          int c=0;
          for(int x:gameChances){
              if(x==2){
                  c++;
                  break;
              }
          }
          if(c==0&&gameOver==false){
              JOptionPane.showMessageDialog(null,"Its Draw..");
             int i= JOptionPane.showConfirmDialog(this,"Play more??");
             if(i==0){
                 this.setVisible(false);
                 new MyGame();
             }else if(i==1){
                 System.exit(1212);
             }else{
                 
             }
             gameOver=true;
          }
          
          
      }
      
      else{
          JOptionPane.showMessageDialog(this,"Position already occupied...");
      }
    }
    
    
}
