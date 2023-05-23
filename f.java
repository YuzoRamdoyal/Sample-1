 public void actionPerformed(ActionEvent e)
    {
    Dialog =new JFrame();  
    String action = e.getActionCommand();

   
   
         if (action.equals("Pay")) {
        RetreiveFromDatabase();
         }
    }
    public static void RetreiveFromDatabase()
    {
      String[][] data = null;

    Connection conn = null;
           try {
   
     //Registering JDBC drivers
        Class.forName(JDBC_DRIVER);

              //Connecting to database
               conn = DriverManager.getConnection(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
         
   
     
   
     System.out.println("Successfully Connected to the database!");
   
     
       } catch (ClassNotFoundException e) {
   
     System.out.println("Could not find the database driver " + e.getMessage());
       } catch (SQLException e) {
   
     System.out.println("Could not connect to the database " + e.getMessage());
       }
   
       try {
   
   
    // Get a result set containing all data from TableElectricity
   
    Statement statement = conn.createStatement();
   
    ResultSet results = statement.executeQuery("SELECT * FROM TableElectricity");
   
    int RowData =0;
    // Fetching each Row Data
    while (results.next()) {
   
     
       // Get the data from the current row using the column name
       data[RowData][0] = results.getString("BillNumber");
     data[RowData][1] = results.getString("BillAmount");
     data[RowData][2] = results.getString("Month");
     data[RowData][3] = results.getString("DateofPayment");
     data[RowData][4] = results.getString("FirstName");
     data[RowData][5] = results.getString("LastName");
     data[RowData][5] = results.getString("Email");
     data[RowData][7] = results.getString("Phone");
     data[RowData][8] = results.getString("Address");
     data[RowData][9] = results.getString("City");
     data[RowData][10] = results.getString("PaymentMethod");

       RowData += RowData;
     
    }
   
   
   
    } catch (SQLException e) {
   
     System.out.println("Could not retrieve data from the database " + e.getMessage());
       }
   
     
        // Table
   
     
        JTable BillingTable;
        String[] columnNames = { "Bill Number","Bill Amount","Month","Date of Payment","First Name", "Last Name", "Email Address","Phone Number","Address","City","Payment Method" };
        BillingTable = new JTable(data, columnNames);
        BillingTable.setBounds(300, 400, 500, 500);
       
       
    JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Historical Payment Bill");
        frame.setResizable(false);
        JScrollPane sp = new JScrollPane(BillingTable);
        frame.add(sp);
        frame.pack();
        frame.setVisible(true);
    }