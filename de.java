package test;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Statement;
import java.util.LinkedList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import test.test.Students;

class Payment implements ActionListener {

    //Defining Databases Variable
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String JDBC_DB_URL = "jdbc:mysql://localhost:3306";
 
    static final String JDBC_USER = "root";
    static final String JDBC_PASS = "";
   
//Declaration of GUI Variables
    private JLabel  firstnamelabel,lastnamelabel,emailLabel,phonelabel,addresslabel,citylabel,paymentlabel;
    private static JTextField emailtxt,phonetxt;
private static JTextField firstnametxt;
private static JTextField lastnametxt;
private static JTextField addresstxt;
    private JRadioButton CreditCardRadio, DebitCardRadio,JuiceRadio,BankTransferRadio;
    private static ButtonGroup PaymentGroup;
    private JPanel centerPanel, northPanel, southPanel;
    private static JComboBox cityBox;
    private JButton btnPay,btnClear;
    private JFrame Dialog;  
    public Payment() {
        //Declaring instance variables  
    String cities[] = { "Curepipe", "Port Louis", "Mahebourg","Flacq","Vacoas","Goodlands","Bambous","Souillac","Surinam","Rose-Hill","Beau Bassin","Phoneix" };
   
    //Defining all Label
    firstnamelabel=new JLabel("First Name : ");
    lastnamelabel=new JLabel("Last Name : ");
    emailLabel = new JLabel("Email-Address: ");
    phonelabel = new JLabel("Phone No: ");
    addresslabel = new JLabel("Address: ");
    citylabel = new JLabel("City: ");
    paymentlabel = new JLabel("Payment Method: ");

    //Defining all TextBox

        emailtxt = new JTextField(20);
        firstnametxt = new JTextField(20);
        phonetxt = new JTextField(20);
        lastnametxt = new JTextField(10);
        addresstxt = new JTextField(20);
       
    //Defining the Drop Down

        cityBox = new JComboBox(cities);
               
        // Defining the radio buttons
        CreditCardRadio = new JRadioButton("Credit Card");
        DebitCardRadio = new JRadioButton("Debit Card");
        JuiceRadio = new JRadioButton("Juice");
        BankTransferRadio = new JRadioButton("Bank Transfer");

        // Adding Values to a group for the radio button

        PaymentGroup = new ButtonGroup();
        PaymentGroup.add(CreditCardRadio);
        PaymentGroup.add(DebitCardRadio);
        PaymentGroup.add(JuiceRadio);
        PaymentGroup.add(BankTransferRadio);
        CreditCardRadio.setSelected(true);

       //Defining the button
        btnPay = new JButton("Pay");
        btnClear = new JButton("Clear");

        // Adding action event to the button
        btnPay.addActionListener(this);
        btnClear.addActionListener(this);

        // setting of frame for GUI
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Electricity Payment Bill");
        frame.add(createCenterPanel(), "Center");
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);


   
    }

   
    //Function to check if value is numeric
    public static boolean isNotNumeric(String str) {
     try {  
       Double.parseDouble(str);  
       return false;
     } catch(NumberFormatException e){  
       return true;  
     }  
    }

   

    //Function For creating JPanel
    private JPanel createCenterPanel() {
        centerPanel = new JPanel(new FlowLayout());

        GridBagLayout gbl = new GridBagLayout();
        centerPanel.setLayout(gbl);

        //Defining the length
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(15, 5, 0, 0);

        // Adding all the label,textbox,dropdown and radio button to the center panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(firstnamelabel, gbc);
        gbc.gridx = 1;
        centerPanel.add(firstnametxt, gbc);
       
        gbc.gridy = 1;
        gbc.gridx = 0;
        centerPanel.add(lastnamelabel, gbc);
        gbc.gridx = 1;
        centerPanel.add(lastnametxt, gbc);


        gbc.gridy = 2;
        gbc.gridx = 0;
        centerPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        centerPanel.add(emailtxt, gbc);

       

        gbc.gridx = 0;
        gbc.gridy = 3;
        centerPanel.add(phonelabel, gbc);
        gbc.gridx = 1;
        centerPanel.add(phonetxt, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        centerPanel.add(addresslabel, gbc);
        gbc.gridx = 1;
        centerPanel.add(addresstxt, gbc);
       

        gbc.gridx = 2;
        gbc.gridy = 5;
        JPanel gbcity = new JPanel(new FlowLayout(FlowLayout.CENTER));
        gbcity.add(citylabel);
        gbcity.add(cityBox);
        centerPanel.add(gbcity,gbc);

       
        gbc.gridx = 0;
        gbc.gridy = 7;
        JPanel gbF = new JPanel(new FlowLayout(FlowLayout.CENTER));
        gbF.add(paymentlabel);
        gbF.add(CreditCardRadio);
        gbF.add(DebitCardRadio);
        gbF.add(JuiceRadio);
        gbF.add(BankTransferRadio);
     
        gbc.gridwidth=2;

        gbc.gridx = 0;
        centerPanel.add(gbF, gbc);
       
       
     
   
        //Adding the buttong
        JPanel gbButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        gbButton.add(btnClear);

        gbButton.add(btnPay);
        gbc.gridy = 8;
        gbc.gridx = 0;
       
        centerPanel.add(gbButton, gbc);
        return centerPanel;
       
    }
   
   
    //Function on action on button
    public void actionPerformed(ActionEvent e)
    {
    Dialog =new JFrame();  
    String action = e.getActionCommand();

   
   
         if (action.equals("Clear")) {
        //Clearing out all fields for ease of use
        emailtxt.setText("");
        firstnametxt.setText("");
        phonetxt.setText("");
        lastnametxt.setText("");
        CreditCardRadio.setSelected(true);
             addresstxt.setText("");
             cityBox.setSelectedItem("Curepipe");
        JOptionPane.showMessageDialog(Dialog,action);  
         }
         else if (action.equals("Pay")) {
        String message = "";
        //PaySuccess is the variable used to check if the field are correct and the system can proceed to save to database
        boolean PaySuccess = true;
        //Validation
       
        //Checking if first name is empty
             if(firstnametxt.getText().equals(""))
             {
            message +="First Name is empty \n";
            PaySuccess = false;
             }
             
        //Checking if last name is empty
             if(lastnametxt.getText().equals(""))
             {
            message +="Last Name is empty \n";
            PaySuccess = false;

             }
             
             
        //Checking if email address is empty

             if(emailtxt.getText().equals(""))
             {
            message ="Email Address is empty \n" ;
            PaySuccess = false;

           
             }
         
        //Checking if phone number is empty

             if(phonetxt.getText().equals(""))
             {
            message +="Phone Number is empty \n";
            PaySuccess = false;

             }
             
           
        //Checking if adddress is empty

             if(addresstxt.getText().equals(""))
             {
            message +="Address is empty \n";
            PaySuccess = false;

             }
             
        //Checking if phone is numeric

             if(isNotNumeric(phonetxt.getText()))
             {
            message +="Phone Number is not numeric \n";
            PaySuccess = false;

             }
             if(PaySuccess == false)
             {
            JOptionPane.showMessageDialog(Dialog,message);  

             }
             else if (PaySuccess == true)
             {
            SavetoDatabase();
             }

         }
       
    }
   

    public static void SavetoDatabase()
    {
    Connection conn = null;
      Statement stmt = null;
      try{
     
     //Registering JDBC drivers
         Class.forName(JDBC_DRIVER);

        //Connecting to database
         conn = DriverManager.getConnection(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
       
         //Inserting into Table
         stmt = conn.createStatement();
         
         String sql = "INSERT INTO TableElectricity(FirstName,LastName,Email,Phone,Address,City,PaymentMethod) " +
                      "VALUES ('"+firstnametxt.getText()+"','"+lastnametxt.getText()+"','"+emailtxt.getText()+"',"+phonetxt.getText()+",'"+addresstxt.getText()+"','"+cityBox.getSelectedItem().toString()+"','"+PaymentGroup.getSelection().getActionCommand().toString()+"')";
         stmt.executeUpdate(sql);
   

      }catch(SQLException se){
         //Handle errors for JDBC
         se.printStackTrace();
      }catch(Exception e){
         //Handle errors for Class.forName
         e.printStackTrace();
      }finally{
         //Closing all connection/Resources
         try{
            if(stmt!=null)
               conn.close();
         }catch(SQLException se){
         }
         try{
            if(conn!=null)
               conn.close();
         }catch(SQLException se){
            se.printStackTrace();
         }
      }
   
   
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Payment();
            }
        });
    }
   
   
}