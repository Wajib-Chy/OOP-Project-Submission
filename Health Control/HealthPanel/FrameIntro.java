package HealthPanel;

import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class FrameIntro extends JFrame implements ActionListener, MouseListener 
{
    public JTextField nameField;
    private JTextField ageField, weightField, heightField, waterField;
    private JButton bmiBtn, dietBtn, waterBtn, exitBtn, saveBtn;
    private JTextArea resultArea;
    private JLabel background;

    public FrameIntro(String userName) 
	{
       //main frame
	    super("Bhai-bhai Health Assistant");  //heading of title (small portion)
        setSize(1000, 700);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // background image
        background = new JLabel(new ImageIcon("Image/bgIm.jpg"));
        background.setBounds(0, 0, 1000, 700);
        setContentPane(background);
        background.setLayout(null);

		// 2nd panel heading
        JLabel title = new JLabel("Bhai-bhai Health Assistant", SwingConstants.CENTER);
        title.setFont(new Font("Century Gothic", Font.BOLD, 30));
        title.setBounds(0, 10, 1000, 40);
        background.add(title);


        JLabel welcomeLabel = new JLabel("Welcome, " + userName + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        welcomeLabel.setBounds(0, 50, 1000, 30);
        background.add(welcomeLabel);
		
		//adding the label line 102
        background.add(createLabel("Name:", 50, 100));
        background.add(createLabel("Age:", 50, 150));
        background.add(createLabel("Weight (kg):", 50, 200));
        background.add(createLabel("Height (m):", 50, 250));
        background.add(createLabel("Water Intake (L):", 50, 300));

		//Text field
        nameField = createField(220, 100);
        nameField.setText(userName);
        ageField = createField(220, 150);
        weightField = createField(220, 200);
        heightField = createField(220, 250);
        waterField = createField(220, 300);


        background.add(nameField);
        background.add(ageField);
        background.add(weightField);
        background.add(heightField);
        background.add(waterField);

        bmiBtn = createButton("BMI", 60, 380);
        dietBtn = createButton("Diet Chart", 210, 380);
        waterBtn = createButton("Hydration", 60, 440);
        saveBtn = createButton("Save Info", 210, 440);
        exitBtn = createButton("Exit", 135, 500);

        background.add(bmiBtn);
        background.add(dietBtn);
        background.add(waterBtn);
        background.add(saveBtn);
        background.add(exitBtn);

        resultArea = new JTextArea();
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setEditable(false);

		//adding scroll panel
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(450, 100, 500, 500);
        background.add(scrollPane);

        bmiBtn.addActionListener(this);
		bmiBtn.addMouseListener(this);
		dietBtn.addActionListener(this);
		dietBtn.addMouseListener(this);
		waterBtn.addActionListener(this);
		waterBtn.addMouseListener(this);
		saveBtn.addActionListener(this);
		saveBtn.addMouseListener(this);
		exitBtn.addActionListener(this);
		exitBtn.addMouseListener(this);

    }

    private JLabel createLabel(String text, int x, int y) 
	{
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 150, 30);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private JTextField createField(int x, int y) 
	{
        JTextField field = new JTextField();
        field.setBounds(x, y, 200, 30);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        return field;
    }

    private JButton createButton(String text, int x, int y) 
	{
        JButton button = new JButton(text);
        button.setBounds(x, y, 120, 35);
        button.setFocusPainted(false);
        return button;
    }

    public String getNameInput() 
	{
        return nameField.getText();
    }

    public int getAgeInput() 
	{
        return Integer.parseInt(ageField.getText());
    }

    public double getWeightInput() 
	{
        return Double.parseDouble(weightField.getText());
    }

    public double getHeightInput() 
	{
        return Double.parseDouble(heightField.getText());
    }

    public double getWaterInput() 
	{
        return Double.parseDouble(waterField.getText());
    }

    public void actionPerformed(ActionEvent e) 
	{
        Object src = e.getSource();
		
        try 
		{
            if (src == bmiBtn) 
			{
				double bmi = getWeightInput() / (getHeightInput() * getHeightInput());
				String status;

				if (bmi < 18.5) 
				{
					status = "Underweight";
				} 
				else if (bmi < 24.9) 
				{
					status = "Normal";
				} 
				else if (bmi < 29.9) 
				{
					status = "Overweight";
				} 
				else 
				{
					status = "Obese";
				}

				resultArea.setText("BMI: " + String.format("%.2f", bmi) + "\nStatus: " + status);
			}				
			else if (src == dietBtn) 
			{
                double bmi = getWeightInput() / (getHeightInput() * getHeightInput());
                String diet;
                if(bmi < 18.5) 
				{
                    diet = "Underweight:\n- Eat calorie-dense foods\n- Increase protein\n- Eat frequently";
                } 
				else if(bmi < 24.9) 
				{
                    diet = "Normal:\n- Balanced diet\n- Fruits, vegetables\n- Avoid junk food";
                } 
				else if (bmi < 29.9) 
				{
                    diet = "Overweight:\n- Reduce sugar & fat\n- Eat fiber\n- Exercise";
                } 
				else 
				{
                    diet = "Obese:\n- Low-calorie meals\n- Avoid processed food\n- Consult a dietitian";
                }
				
                resultArea.setText("BMI: " + String.format("%.2f", bmi) + "\n\n" + diet);
            } 
			else if (src == waterBtn) 
			{
                double required = (getAgeInput() <= 18) ? 1.9 : 2.5;
                String msg = (getWaterInput() >= required) ? "You're hydrated!" : "Drink more water.";
                resultArea.setText("You drank: " + getWaterInput() + "L\nRecommended: " + required + "L\n" + msg);
            } 
			else if (src == saveBtn) 
			{
                try (FileWriter fw = new FileWriter("userdata.txt", true)) 
				{
                    fw.write("Name: " + getNameInput() + "\nAge: " + getAgeInput()
                            + "\nWeight: " + getWeightInput()
                            + "\nHeight: " + getHeightInput()
                            + "\nWater Intake: " + getWaterInput() + "\n---\n");
                    resultArea.setText("User info saved to 'userdata.txt'.");
                }
            } 
			else if (src == exitBtn) 
			{
                System.exit(0);
            }
			
        } 
		catch (Exception ex) 
		{
            resultArea.setText("Invalid input or error occurred.");
        }
    }

    public void mouseEntered(MouseEvent e) 
	{
        if (e.getSource() instanceof JButton) 
		{
            ((JButton) e.getSource()).setBackground(new Color(153,153,255));
        }
    }

    public void mouseExited(MouseEvent e) 
	{
        if (e.getSource() instanceof JButton) 
		{
            ((JButton) e.getSource()).setBackground(null);
        }
    }

    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}
