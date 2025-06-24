package HealthPanel;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JFrame implements ActionListener 
{
    private JTextField nameField;
    private JButton submitBtn, exitBtn;

    public LoginPanel() 
	{
        setTitle("Login - Bhai-bhai Health Assistant");
        setSize(400, 200);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel nameLabel = new JLabel("Enter your name:");
        nameLabel.setBounds(30, 30, 150, 30);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(170, 30, 180, 30);
        add(nameField);

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(60, 100, 100, 35);
        submitBtn.addActionListener(this);
        add(submitBtn);

        exitBtn = new JButton("Exit");
        exitBtn.setBounds(200, 100, 100, 35);
        exitBtn.addActionListener(this);
        add(exitBtn);
    }

    public void actionPerformed(ActionEvent e) 
	{
        if (e.getSource() == submitBtn) 
		{
            String userName = nameField.getText().trim();
			
            if (userName !=null) 
			{
                FrameIntro mainFrame = new FrameIntro(userName);
                mainFrame.setVisible(true);
                dispose();
            } 

			else 
			{
                JOptionPane.showMessageDialog(this, "Please enter your name.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } 
		else if (e.getSource() == exitBtn) 
		{
            System.exit(0);
        }
    }

}
