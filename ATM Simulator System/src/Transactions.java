import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Transactions extends JFrame implements ActionListener{
    JLabel l1;
    JButton b1,b2,b3,b4,b5,b6,b7;
    
    Transactions(){
      setFont(new Font("System",Font.BOLD,22));
      Font f = getFont();
      FontMetrics fm = getFontMetrics(f);
      int x = fm.stringWidth("TRANSACTIONS");
      int y = fm.stringWidth(" ");
      int z = getWidth()-(3*x);
      int w = z/y;
      
      String pad = "";
      pad = String.format("~"+w+"s",pad);
      setTitle(pad+"TRANSATION");
      
      pad = String.format("~"+w+"s",pad);
      setTitle(pad+"TRANSATION");
      
      
        l1 = new JLabel("Please Select Your Transaction");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));
        
       
        b1 = new JButton("DEPOSIT");
        b2 = new JButton("CASH WITHDRAWL");
        b3 = new JButton("FAST CASH");
        b4 = new JButton("MINI STATEMENT");
        b5 = new JButton("PIN CHANGE");
        b6 = new JButton("BALANCE ENQUIRY");
        b7 = new JButton("EXIT");
      
        setLayout(null);
        
        l1.setBounds(235,400,700,35);
        add(l1);
        
        b1.setBounds(170,499,150,35);
        add(b1);
        
        b2.setBounds(390,499,150,35);
        add(b2);
        
        b3.setBounds(170,543,150,35);
        add(b3);
        
        b4.setBounds(390,543,150,35);
        add(b4);
        
        b5.setBounds(170,588,150,35);
        add(b5);
        
        b6.setBounds(390,588,150,35);
        add(b6);
        
        b7.setBounds(390,633,150,35);
        add(b7);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(800,800);
        setLocation(500,90);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
          if(ae.getSource()==b1){ 
            setVisible(false);
            new Deposit().setVisible(true);
        }else if(ae.getSource()==b2){ 
            setVisible(false);
            new Withdrawl().setVisible(true);
        }else if(ae.getSource()==b3){ 
            setVisible(false);
            new FastCash().setVisible(true);
        }else if(ae.getSource()==b4){ 
            new MiniStatement().setVisible(true);
        }else if(ae.getSource()==b5){ 
            setVisible(false);
            new Pin().setVisible(true);
        }else if(ae.getSource()==b6){ 
            String pinn = JOptionPane.showInputDialog("Enter pin");
            conn c1 = new conn();
            
            try{
               ResultSet rs = c1.s.executeQuery("SELECT balance FROM bank ORDER BY pin = '"+pinn+"' DESC LIMIT 1");
               if(rs.next()){
               String balance = rs.getString("balance");
               JOptionPane.showMessageDialog(null,"Your Account Balance is "+balance);
               }
             } catch (Exception e){
                     e.printStackTrace();
                 }
        }
    }
    
     public static void main(String[] args){
        new Transactions().setVisible(true);
    }
    
}
