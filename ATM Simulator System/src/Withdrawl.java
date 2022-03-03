
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.sql.*;

public class Withdrawl extends JFrame implements ActionListener{
     JTextField t1,t2;
    JButton b1,b2,b3;
    JLabel l1,l2,l3,l4;
    
    Withdrawl(){
    
      setFont(new Font("System",Font.BOLD,22));
      Font f = getFont();
      FontMetrics fm = getFontMetrics(f);
      int x = fm.stringWidth("WITHDRAWAL");
      int y = fm.stringWidth(" ");
      int z = getWidth()-(5*x);
      int w = z/y;
      
      String pad = "";
      pad = String.format("~"+w+"s",pad);
      setTitle(pad+"WITHDRAWAL");
      
        l1 = new JLabel("MAXIMUM WITHDRAWAL IS RS.10,000");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));
        
        l2 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("System", Font.BOLD, 16));
        
        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 25));
        
        b1 = new JButton("WITHDRAW");
        b2 = new JButton("BACK");
        
        setLayout(null);
        
        l1.setBounds(190,350,400,35);
        l3.add(l1);
        
        t1.setBounds(190,420,320,25);
        l3.add(t1);
        
        b1.setBounds(390,588,150,35);
        l3.add(b1);
        
        b2.setBounds(390,633,150,35);
        l3.add(b2);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(800,800);
        setLocation(500,90);
        setVisible(true);
    } 
     
    public void actionPerformed(ActionEvent ae){
    
        try{
         String a = t1.getText();
         String b = t2.getText();
         
         if(ae.getSource()==b){
           if(t1.getText().equals("")){
           JOptionPane.showMessageDialog(null,"please enter the amount to you want to WITHDRAW");
           
           }else{
              conn c1 = new conn();
              ResultSet rs = c1.s.executeQuery( " SELECT * FROM bank where pin = '"+b+"' " );
              double balance = 0;
              if(rs.next()){
              String pin = rs.getString("pin");
              
              balance = rs.getDouble("balance");
              double d = Double.parseDouble(a);
              balance+=d;
              
              String q1 = "insert into bank values('"+pin+"',null,'"+d+"','"+balance+"')";
              c1.s.executeUpdate(q1);
              }
           }
           
           JOptionPane.showMessageDialog(null,"Rs. "+a+" Deposited Succesfully");
           new Transactions().setVisible(true);
           setVisible(false);
         }
         else if(ae.getSource()==b2){
          new Transactions().setVisible(true);
          setVisible(false);
         }
         else if(ae.getSource()==b3){
         System.exit(0); 
         }
         
        }catch(Exception e){
          e.printStackTrace();
          System.out.println("error: "+e);
        }
        
    }
        
    public static void main(String[]args){
    new Withdrawl().setVisible(true);
    }
}
