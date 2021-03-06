package data;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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

        sendingButton.addActionListener(e -> {
            String title = titleTextField.getText();
            String description = descriptionTextField.getText();
            //System.out.println("Title: " + title);
            //System.out.println("Description: " + description);
            //TODO: check for successful response
            if(!title.equals("") && !description.equals("")) {
                boolean response = false;
                try {
                    response = URLCreator.sendPostRequest(title, description);
                } catch (IOException | InterruptedException ioException) {
                    ioException.printStackTrace();
                }
                if(response) {
                    JOptionPane.showMessageDialog(null, "Sending of data was successful.", "InfoBox: Successful Sending", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Sending of data was not successful.", "InfoBox: Unsuccessful Sending", JOptionPane.INFORMATION_MESSAGE);
                }
                titleTextField.setText("");
                descriptionTextField.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "There has to be text in both textfields.", "InfoBox: Data wasn't send.", JOptionPane.INFORMATION_MESSAGE);
            }


        });

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
