package wpy.calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorGUI extends JFrame {
	
	private static final Font TEXT_FONT = new Font("monspaced", Font.PLAIN, 60);
	private JTextField display;
	private JButton button_0, button_1, button_2, button_3, button_4, button_5, button_6, 
					button_7, button_8, button_9, button_dot, button_equal, button_add, button_sub, 
					button_mul, button_div, button_cancel, button_add_sub, 
					button_mr, button_ms, button_ma, button_mc;
	
	private static double value;
	private static double buffer;
	private static int inputState;
	private static double precision;
	/*
	 * calculator input state: 0: not accumulating 1: integer accumulating 2:
	 * fractional accumulating
	 */
	public static int actionState;
	
	
	public CalculatorGUI() {
		
		super("JCalculator"); 
        setBounds(500,500,300,500);
        setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300, 450));
        
		buffer = 0;
		value = 0;
		inputState = 0;
		precision = 1;
		actionState = 0;
		
		display = new JTextField("0", 12);
		display.setHorizontalAlignment(JTextField.RIGHT);
		display.setFont(TEXT_FONT);
		
		JPanel buttonPanel = new JPanel();
		
		GridBagLayout gridLayout = new GridBagLayout();
        buttonPanel.setLayout(gridLayout);
        GridBagConstraints c = new GridBagConstraints();

        button_mc = new JButton("MC");			button_mc.setFont(new Font("monspaced", Font.PLAIN, 20));
        button_ma = new JButton("M+");			button_ma.setFont(new Font("monspaced", Font.PLAIN, 20));
        button_ms = new JButton("M-");			button_ms.setFont(new Font("monspaced", Font.PLAIN, 20));
        button_mr = new JButton("MR");			button_mr.setFont(new Font("monspaced", Font.PLAIN, 20));
        button_cancel = new JButton("C");		button_cancel.setFont(new Font("monspaced", Font.PLAIN, 20));
        button_add_sub = new JButton("+/-");	button_add_sub.setFont(new Font("monspaced", Font.PLAIN, 20));
        button_div = new JButton("/");			button_div.setFont(new Font("monspaced", Font.PLAIN, 20));
        button_mul = new JButton("*");			button_mul.setFont(new Font("monspaced", Font.PLAIN, 20));
        button_7 = new JButton("7");			button_7.setFont(new Font("monspaced", Font.PLAIN, 20));
        button_8 = new JButton("8");			button_8.setFont(new Font("monspaced", Font.PLAIN, 20));
        button_9 = new JButton("9");			button_9.setFont(new Font("monspaced", Font.PLAIN, 20));
        button_sub = new JButton("-");			button_sub.setFont(new Font("monspaced", Font.PLAIN, 20));
        button_4 = new JButton("4");			button_4.setFont(new Font("monspaced", Font.PLAIN, 20));
        button_5 = new JButton("5");			button_5.setFont(new Font("monspaced", Font.PLAIN, 20));
        button_6 = new JButton("6");			button_6.setFont(new Font("monspaced", Font.PLAIN, 20));
        button_add = new JButton("+");			button_add.setFont(new Font("monspaced", Font.PLAIN, 20));	
        button_1 = new JButton("1");			button_1.setFont(new Font("monspaced", Font.PLAIN, 20));
        button_2 = new JButton("2");			button_2.setFont(new Font("monspaced", Font.PLAIN, 20));
        button_3 = new JButton("3");			button_3.setFont(new Font("monspaced", Font.PLAIN, 20));
        button_equal = new JButton("=");		button_equal.setFont(new Font("monspaced", Font.PLAIN, 20));
        button_0 = new JButton("0");			button_0.setFont(new Font("monspaced", Font.PLAIN, 20));
        button_dot = new JButton(".");			button_dot.setFont(new Font("monspaced", Font.PLAIN, 20));
        
        button_mc.setBackground(Color.DARK_GRAY); button_mc.setForeground(Color.WHITE);
        button_ma.setBackground(Color.DARK_GRAY); button_ma.setForeground(Color.WHITE);
        button_ms.setBackground(Color.DARK_GRAY); button_ms.setForeground(Color.WHITE);
        button_mr.setBackground(Color.DARK_GRAY); button_mr.setForeground(Color.WHITE);
        button_cancel.setBackground(Color.DARK_GRAY); button_cancel.setForeground(Color.WHITE);
        button_add_sub.setBackground(Color.DARK_GRAY); button_add_sub.setForeground(Color.WHITE);
        button_div.setBackground(Color.DARK_GRAY); button_div.setForeground(Color.WHITE);
        button_mul.setBackground(Color.DARK_GRAY); button_mul.setForeground(Color.WHITE);
        button_7.setBackground(Color.DARK_GRAY); button_7.setForeground(Color.WHITE);
        button_8.setBackground(Color.DARK_GRAY); button_8.setForeground(Color.WHITE);
        button_9.setBackground(Color.DARK_GRAY); button_9.setForeground(Color.WHITE);
        button_sub.setBackground(Color.DARK_GRAY); button_sub.setForeground(Color.WHITE);
        button_4.setBackground(Color.DARK_GRAY); button_4.setForeground(Color.WHITE);
        button_5.setBackground(Color.DARK_GRAY); button_5.setForeground(Color.WHITE);
        button_6.setBackground(Color.DARK_GRAY); button_6.setForeground(Color.WHITE);
        button_add.setBackground(Color.DARK_GRAY); button_add.setForeground(Color.WHITE);
        button_1.setBackground(Color.DARK_GRAY); button_1.setForeground(Color.WHITE);
        button_2.setBackground(Color.DARK_GRAY); button_2.setForeground(Color.WHITE);
        button_3.setBackground(Color.DARK_GRAY); button_3.setForeground(Color.WHITE);
        button_equal.setBackground(Color.DARK_GRAY); button_equal.setForeground(Color.WHITE);
        button_0.setBackground(Color.DARK_GRAY); button_0.setForeground(Color.WHITE);
        button_dot.setBackground(Color.DARK_GRAY); button_dot.setForeground(Color.WHITE);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 5;
        c.insets = new Insets(6,2,6,2);
        buttonPanel.add(button_mc, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        buttonPanel.add(button_ma, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        buttonPanel.add(button_ms, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 0;
        buttonPanel.add(button_mr, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        buttonPanel.add(button_cancel, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        buttonPanel.add(button_add_sub, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 1;
        buttonPanel.add(button_div, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 1;
        buttonPanel.add(button_mul, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        buttonPanel.add(button_7, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        buttonPanel.add(button_8, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 2;
        buttonPanel.add(button_9, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 2;
        buttonPanel.add(button_sub, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 3;
        buttonPanel.add(button_4, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 3;
        buttonPanel.add(button_5, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 3;
        buttonPanel.add(button_6, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 3;
        buttonPanel.add(button_add, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 4;
        buttonPanel.add(button_1, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 4;
        buttonPanel.add(button_2, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 4;
        buttonPanel.add(button_3, c);
        

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        buttonPanel.add(button_0, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 5;
        c.gridwidth = 1;
        buttonPanel.add(button_dot, c);
        
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1.0;
        c.gridx = 3;
        c.gridheight = 2;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridy = 4;
        buttonPanel.add(button_equal, c);
        
        
        JPanel content = new JPanel();
        
        content.setLayout(new BorderLayout(5, 5));
        content.add(display, BorderLayout.NORTH );
        content.add(buttonPanel   , BorderLayout.SOUTH);
        
        content.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        //... Finish building the window (JFrame)
        this.setContentPane(content);
        this.pack();
        //this.setTitle("Simple Calc");
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        
        pack();
        setVisible(true);
        
        button_cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Back to initial state and display 0
				buffer = 0;
				value = 0;
				inputState = 0;
				actionState = 0;
				precision = 1;

				displayResult(value);
			}
        	
        });
        
        button_0.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Back to initial state and display 0
				switch (inputState) {
				case 1:
					String onDisplay = display.getText().toString();
					value = doubleMul(Double.parseDouble(onDisplay), 10);
					displayResult(value);
					break;
				case 2:
					precision = doubleDiv(precision, 10);
					displayResult(value);
					break;
				default:
					value = 0;
					displayResult(value);
					inputState = 1;
				}
			}
        	
        });
        
        button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Back to initial state and display 0
				switch (inputState) {
				case 1: // integer accumulating
					String onDisplay = display.getText().toString();
					value = doubleMul(Double.parseDouble(onDisplay), 10) + 1;
					displayResult(value);
					break;
				case 2: 
					String onDisplay2 = display.getText().toString();
					precision = doubleDiv(precision, 10);
					value = doubleAdd(Double.parseDouble(onDisplay2), precision);
					displayResult(value);
					break;
				default:
					value = 1;
					displayResult(value);
					inputState = 1;
					break;
				}
			}
        	
        });
        
        button_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Back to initial state and display 0
				switch (inputState) {
				case 1: // integer accumulating
					String onDisplay = display.getText().toString();
					value = doubleMul(Double.parseDouble(onDisplay), 10) + 2;
					displayResult(value);
					break;
				case 2: 
					String onDisplay2 = display.getText().toString();
					precision = doubleDiv(precision, 10);
					value = doubleAdd(Double.parseDouble(onDisplay2), doubleMul(precision, 2));
					displayResult(value);
					break;
				default:
					value = 2;
					displayResult(value);
					inputState = 1;
					break;
				}
			}
        	
        });
        
        button_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				switch (inputState) {
				case 1: // integer accumulating
					String onDisplay = display.getText().toString();
					value = doubleMul(Double.parseDouble(onDisplay), 10) + 3;
					displayResult(value);
					break;
				case 2: 
					String onDisplay3 = display.getText().toString();
					precision = doubleDiv(precision, 10);
					value = doubleAdd(Double.parseDouble(onDisplay3), doubleMul(precision, 3));
					displayResult(value);
					break;
				default:
					value = 3;
					displayResult(value);
					inputState = 1;
					break;
				}
			}
        	
        });
        
        button_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				switch (inputState) {
				case 1: // integer accumulating
					String onDisplay = display.getText().toString();
					value = doubleMul(Double.parseDouble(onDisplay), 10) + 4;
					displayResult(value);
					break;
				case 2: 
					String onDisplay2 = display.getText().toString();
					precision = doubleDiv(precision, 10);
					value = doubleAdd(Double.parseDouble(onDisplay2), doubleMul(precision, 4));
					displayResult(value);
					break;
				default:
					value = 4;
					displayResult(value);
					inputState = 1;
					break;
				}
			}
        	
        });
        
        button_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				switch (inputState) {
				case 1: // integer accumulating
					String onDisplay = display.getText().toString();
					value = doubleMul(Double.parseDouble(onDisplay), 10) + 5;
					displayResult(value);
					break;
				case 2: 
					String onDisplay2 = display.getText().toString();
					precision = doubleDiv(precision, 10);
					value = doubleAdd(Double.parseDouble(onDisplay2), doubleMul(precision, 5));
					displayResult(value);
					break;
				default:
					value = 5;
					displayResult(value);
					inputState = 1;
					break;
				}
			}
        	
        });
        
        button_6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				switch (inputState) {
				case 1: // integer accumulating
					String onDisplay = display.getText().toString();
					value = doubleMul(Double.parseDouble(onDisplay), 10) + 6;
					displayResult(value);
					break;
				case 2: 
					String onDisplay2 = display.getText().toString();
					precision = doubleDiv(precision, 10);
					//System.out.println(precision);
					value = doubleAdd(Double.parseDouble(onDisplay2), doubleMul(precision, 6));
					//System.out.println(value);
					displayResult(value);
					break;
				default:
					value = 6;
					displayResult(value);
					inputState = 1;
					break;
				}
			}
        	
        });
        
        button_7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				switch (inputState) {
				case 1: // integer accumulating
					String onDisplay = display.getText().toString();
					value = doubleMul(Double.parseDouble(onDisplay), 10) + 7;
					displayResult(value);
					break;
				case 2: 
					String onDisplay2 = display.getText().toString();
					precision = doubleDiv(precision, 10);
					value = doubleAdd(Double.parseDouble(onDisplay2), doubleMul(precision, 7));
					displayResult(value);
					break;
				default:
					value = 7;
					displayResult(value);
					inputState = 1;
					break;
				}
			}
        	
        });
        
        button_8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				switch (inputState) {
				case 1: // integer accumulating
					String onDisplay = display.getText().toString();
					value = doubleMul(Double.parseDouble(onDisplay), 10) + 8;
					displayResult(value);
					break;
				case 2: 
					String onDisplay2 = display.getText().toString();
					precision = doubleDiv(precision, 10);
					value = doubleAdd(Double.parseDouble(onDisplay2),doubleMul(precision, 8));
					displayResult(value);
					break;
				default:
					value = 8;
					displayResult(value);
					inputState = 1;
					break;
				}
			}
        	
        });
        
        button_9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				switch (inputState) {
				case 1: // integer accumulating
					String onDisplay = display.getText().toString();
					value = doubleMul(Double.parseDouble(onDisplay), 10) + 9;
					displayResult(value);
					break;
				case 2: 
					String onDisplay2 = display.getText().toString();
					precision = doubleDiv(precision, 10);
					value = doubleAdd(Double.parseDouble(onDisplay2), doubleMul(precision, 9));
					displayResult(value);
					break;
				default:
					value = 9;
					displayResult(value);
					inputState = 1;
					break;
				}
			}
        	
        });
        
        button_add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// This piece of code is copied as start for all operations
				precision = 1;
				inputState = 0;
				value = Double.parseDouble(display.getText().toString());
				switch (actionState) {
				case 1: // add
					//display.setText(Double.toString(buffer + value));
					displayResult(buffer + value);
					break;
				case 2: // sub
					//display.setText(Double.toString(buffer - value));
					displayResult(buffer - value);
					break;
				case 3: // mul
					//display.setText(Double.toString(buffer * value));
					displayResult(buffer * value);
					break;
				case 4: // div 
					//display.setText(Double.toString(buffer / value));
					displayResult(buffer / value);
					break;
				default:
					break;
				}

				buffer = Double.parseDouble(display.getText().toString());
				//Log.d(tag, "buffer=" + buffer);
				actionState = 1;
				
				//Log.d(tag, "actionState=" + actionState);
				//Log.d(tag, "inputState=" + inputState);
			}
        	
        });
        
        button_sub.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// This piece of code is copied as start for all operations
				precision = 1;
				inputState = 0;
				value = Double.parseDouble(display.getText().toString());
				switch (actionState) {
				case 1: // add
					displayResult(buffer + value);
					break;
				case 2: // sub
					displayResult(buffer - value);
					break;
				case 3: // mul
					displayResult(buffer * value);
					break;
				case 4: // div 
					displayResult(buffer / value);
					break;
				default:
					break;
				}
				buffer = Double.parseDouble(display.getText().toString());
				//Log.d(tag, "buffer=" + buffer);
				actionState = 2;
				
				//Log.d(tag, "actionState=" + actionState);
				//Log.d(tag, "inputState=" + inputState);
			}
        	
        });
        
        button_mul.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// This piece of code is copied as start for all operations
				precision = 1;
				inputState = 0;
				value = Double.parseDouble(display.getText().toString());
				switch (actionState) {
				case 1: // add
					displayResult(buffer + value);
					break;
				case 2: // sub
					displayResult(buffer - value);
					break;
				case 3: // mul
					displayResult(buffer * value);
					break;
				case 4: // div 
					displayResult(buffer / value);
					break;
				default:
					break;
				}
				buffer = Double.parseDouble(display.getText().toString());
				//Log.d(tag, "buffer=" + buffer);
				actionState = 3;
				
				//Log.d(tag, "actionState=" + actionState);
				//Log.d(tag, "inputState=" + inputState);
			}
        	
        });
        
        button_div.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// This piece of code is copied as start for all operations
				precision = 1;
				value = Double.parseDouble(display.getText().toString());
				switch (actionState) {
				case 1: // add
					displayResult(buffer + value);
					break;
				case 2: // sub
					displayResult(buffer - value);
					break;
				case 3: // mul
					displayResult(buffer * value);
					break;
				case 4: // div 
					displayResult(buffer / value);
					break;
				default:
					break;
				}
				buffer = Double.parseDouble(display.getText().toString());
				//Log.d(tag, "buffer=" + buffer);
				actionState = 4;
				inputState = 0;
				//Log.d(tag, "actionState=" + actionState);
				//Log.d(tag, "inputState=" + inputState);
			}
        	
        });
        
        button_equal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				precision = 1;
				inputState = 0;
				value = Double.parseDouble(display.getText().toString());
				switch (actionState) {
				case 1: // add
					displayResult(buffer + value);
					break;
				case 2: // sub
					displayResult(buffer - value);
					break;
				case 3: // mul
					displayResult(buffer * value);
					break;
				case 4: // div 
					displayResult(buffer / value);
					break;
				default:
					break;
				}
				actionState = 0;
				
				buffer = 0;
			}
        	
        });
        
        button_dot.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// change inputState to decimal (2)
				//buffer = 0;
				//value = 0;
				if (inputState != 2) {
					inputState = 2;
					display.setText(display.getText() + ".");
				}
				//actionState = 0;

				//displayResult(value);
			}
        	
        });
        
        

        
	}
	
    public double doubleAdd(double a, double b) {
    	
    	BigDecimal bd1 = new BigDecimal(Double.toString(a)); 
        BigDecimal bd2 = new BigDecimal(Double.toString(b)); 
        return bd1.add(bd2).doubleValue(); 
    }
  
    public double doubleDiv(double a, int b) {
  	
    	BigDecimal bd1 = new BigDecimal(Double.toString(a));
    	BigDecimal bd2 = new BigDecimal(Double.toString(b));
    	return bd1.divide(bd2, 16, RoundingMode.HALF_UP).doubleValue();
    }
    
    
    public double doubleSub(double a, double b) {
    	
  	  	BigDecimal bd1 = new BigDecimal(Double.toString(a)); 
        BigDecimal bd2 = new BigDecimal(Double.toString(b)); 
        return bd1.subtract(bd2).doubleValue(); 
    }
	
    public double doubleMul(double a, double b) {
    	
  	  	BigDecimal bd1 = new BigDecimal(Double.toString(a)); 
        BigDecimal bd2 = new BigDecimal(Double.toString(b)); 
        return bd1.multiply(bd2).doubleValue(); 
    }
    
	public void displayResult(double value) {
		
		if (value > 99999999) {
			
			// Overflow
			display.setText("ERR");
			
			// Return all state variable to start
			buffer = 0;
			value = 0;
			inputState = 0;
			actionState = 0;
			precision = 1;
			
		} else {
			
			if (value % 1 == 0 && inputState == 0) display.setText(Integer.toString((int) value));
			else {

				String temp = Double.toString(value);
				int length = temp.length();
				display.setText(Double.toString(value).substring(0, length < 8 ? length : 8));
			}
		}
		
	}
	
	public static void main(String[] args) {
		CalculatorGUI calculator = new CalculatorGUI();
	}

}

