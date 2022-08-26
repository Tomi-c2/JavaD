import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.DriverManager;

import javax.swing.*;

public class loginForm extends JFrame{
    final private Font mainFont= new Font ("Times New Roman", Font.BOLD, 18);
    JTextField tfEmail;
    JPasswordField pfPassword;
    
    public void initialize() {
        /********* FORM PANEL *********/
        JLabel lbloginForm = new JLabel("Login Form", SwingConstants.CENTER);

        /**Email */
        JLabel lbEmail =  new JLabel("Email");
        lbEmail.setFont(mainFont);

        tfEmail = new JTextField();
        tfEmail.setFont(mainFont);

         /**Password */
        JLabel lbPassword = new JLabel("Password");
        lbPassword.setFont(mainFont);

        pfPassword = new JPasswordField();
        pfPassword.setFont(mainFont);

        /**PANEL */
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 1, 10, 10));
        formPanel.add(lbloginForm);
        formPanel.add(lbEmail);
        formPanel.add(tfEmail);
        formPanel.add(lbPassword);
        formPanel.add(pfPassword);

        /*************** BUTTON PANELS ******************/
        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(mainFont);
        btnLogin.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());

                user User = getAuthenticatedUser(email, password);

                if (User != null ){
                    Mainframe mainframe = new MainFrame();
                    mainframe.initialize(User);
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(loginForm.this, 
                                "Email or Password Invalid",
                                "Try Again",
                                JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(mainFont);
        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                dispose();
            }

        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(0, 1, 10, 10));
        buttonsPanel.add(btnLogin);
        buttonsPanel.add(btnCancel);



        /*************** INITIALIZE THE FRAME ******************/
        add(formPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.SOUTH);
        add(buttonsPanel, BorderLayout.EAST);

        setTitle("Login Form");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 500);
        //setMinimumSize(new Dimension(350, 450));
        setLocationRelativeTo(null);
        setVisible(true);




    }


    private user getAuthenticatUser(String email, String Password){
        user User = null;

        final String DB_URL = "jbdc:mysql://localhost/mystore?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD= "";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            //Connected to Database Successfully

            String sql = "SELECT * FROM users WHERE email=? AND Password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, Password);


            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){

                User = new user();
                User.name = resultSet.getString("name");
                User.email = resultSet.getString("email");
                User.phone = resultSet.getString("phone");
                User.address = resultSet.getString("address");
                User.password = resultSet.getString("password");
            }

            preparedStatement.close();
            conn.close();

        }catch (Exception e){
            System.out.println("Database Connection Failed!");
        }

        return User;
    }




    public static void main(String[] args) {
        loginForm LoginForm = new loginForm();
        LoginForm.initialize();
    }

}