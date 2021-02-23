package data;

import javax.swing.*;
import java.awt.*;

public class Frame {

    Frame() {
        JFrame frame = new JFrame("Send Data to Newsfeed Database");

        JPanel panel = new JPanel();

        JButton sendingButton = new JButton("Send Newsfeed Data");
        JLabel titleLabel = new JLabel("Title:");
        JLabel descriptionLabel = new JLabel("Description:");

        JTextField titleTextField = new JTextField();
        JTextField descriptionTextField = new JTextField();

        titleTextField.setPreferredSize(new Dimension(100, titleTextField.getPreferredSize().height));
        descriptionTextField.setPreferredSize(new Dimension(400, descriptionTextField.getPreferredSize().height));

        //adding components to panel
        panel.add(titleLabel);
        panel.add(titleTextField);
        panel.add(descriptionLabel);
        panel.add(descriptionTextField);
        panel.add(sendingButton);

        //frame settings
        frame.add(panel);
        frame.pack();
        //frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
