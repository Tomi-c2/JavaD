import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    public void initialize(user User){

        /***** INFO PANEL *****/
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0, 2, 5, 5));

        infoPanel.add(new JLabel("Name"));
        infoPanel.add(new JLabel(User.name));
        infoPanel.add(new JLabel("Email"));
        infoPanel.add(new JLabel(User.email));
        infoPanel.add(new JLabel("Phone"));
        infoPanel.add(new JLabel(User.phone));
        infoPanel.add(new JLabel("Address"));
        infoPanel.add(new JLabel(User.address));

        add(infoPanel, BorderLayout.NORTH);


        setTitle("Dashboard");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}