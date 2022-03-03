import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import java.util.*;

public class Deposit extends JFrame implements ActionListener {
    
    JTextField t1,t2;
    JButton b1,b2,b3;
    JLabel l1,l2,l3;
    
    Deposit(){
    
      setFont(new Font("System",Font.BOLD,22));
      Font f = getFont();
      FontMetrics fm = getFontMetrics(f);
      int x = fm.stringWidth("DEPOSIT");
      int y = fm.stringWidth(" ");
      int z = getWidth()-(5*x);
      int w = z/y;
      
      String pad = "";
      pad = String.format("~"+w+"s",pad);
      setTitle(pad+"DEPOSITE");
      
        l1 = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));
        
        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 22));
        
        b1 = new JButton("DEPOSIT");
        b2 = new JButton("BACK");
        b3 = new JButton("EXIT");
        
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
           JOptionPane.showMessageDialog(null,"please enter the amount to you want to Deposit");
           
           }else{
              conn c1 = new conn();
              ResultSet rs = c1.s.executeQuery( " SELECT * FROM bank where pin = '"+b+"' " );
              double balance = 0;
              if(rs.next()){
              String pin = rs.getString("pin");
              
              balance = rs.getDouble("balance");
              double d = Double.parseDouble(a);
              balance+=d;
              
              String q1 = "insert into bank values('"+pin+"','"+d+"','"+balance+"')";
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
    new Deposit().setVisible(true);
    }
    
}
