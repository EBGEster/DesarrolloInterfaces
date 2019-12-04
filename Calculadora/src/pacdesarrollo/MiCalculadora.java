package pacdesarrollo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.*;

public class MiCalculadora extends JFrame{

	private static final long serialVersionUID = 1L;
	private JLabel lblResult = new JLabel("0");
	private double result = 0;
	private boolean hayComa = false;
	private boolean introducirNuevoNum = true;
	private String ultimaOperacion = null;
	private NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
	
	private final static String opCuadrado = "<html>x<sup>2</sup></html>";
	
	/*private listener*/
	
	public MiCalculadora() {
        
		JPanel jPanel = new JPanel(new GridLayout(5,4));
		
		lblResult.setBackground(Color.white);
		        lblResult.setOpaque(true);
		        lblResult.setFont(new Font("Arial", Font.PLAIN,32));
		       
		JButton[] botones = {
		       new JButton("AC"), new JButton("CE"), new JButton("C"),  new JButton(opCuadrado), 
		       new JButton("7"),  new JButton("8"),  new JButton("9"),  new JButton("/"),  
		       new JButton("4"),  new JButton("5"),  new JButton("6"),  new JButton("*"),       
		       new JButton("1"),  new JButton("2"),  new JButton("3"),  new JButton("-"),       
		       new JButton(","),  new JButton("0"),  new JButton("="),  new JButton("+")
		   };
		
		//jPanel.add(new JLabel(""));
		
		BotonPulsado lstnBtn = new BotonPulsado();
		for (int i=0; i<botones.length;i++){
		            jPanel.add(botones[i]);
		            botones[i].addActionListener(lstnBtn);
		        }
		
		
		add(lblResult, BorderLayout.NORTH);
		        add(jPanel);
		
		lblResult.setHorizontalAlignment(JLabel.RIGHT);
		
        setTitle("Calculadora");
		setSize(300, 400);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private double parseLabel (String s) {
		double num = 0;
		try {
			Number n = null;
			n = (Number) nf.parse(s);
			num = n.doubleValue();
			System.out.println("parselabel: "+ num);
		} catch (ParseException ex) {}
		return num;
	}
	
	private void operar(String op, double num) {
		if (ultimaOperacion == null) {
			result = num;
			ultimaOperacion = op;
			introducirNuevoNum = true;
			hayComa = false;
		} 
		else {
			if (!introducirNuevoNum){
		      switch (op) {
		        case "+":
		        result += num;
		            break;
		        case "-":
		        	System.out.println("num operar " + num);
		        	System.out.println("result antes: "+ result);
		        result -= num;
		        System.out.println("result despues: "+ result);
		            break;
		        case "*":
		        result *= num;
		            break;
		        case "/":
		            result /= num;
		            break;
		      }
		      introducirNuevoNum = true;
		      hayComa = false;
			}
		}
		ultimaOperacion = op;
	}
	
	public class BotonPulsado implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	JButton btnPulsado = (JButton) e.getSource();
	       String btnText = btnPulsado.getText();
	       String lblContent = lblResult.getText();
	     
	       switch (btnText) {
	         case "+":
	         operar(btnText, parseLabel(lblContent));
	         break;
	         case "-":
	         operar(btnText, parseLabel(lblContent));
	         break;
	         case "*":
	         operar(btnText, parseLabel(lblContent));
	         break;
	         case "/":
	         operar(btnText, parseLabel(lblContent));
	         break;
	         case opCuadrado:
		         double n = Double.parseDouble(lblContent);
		         result = n*n;
		         lblResult.setText(String.format("%.0f", result));
		         introducirNuevoNum = true;
	        	 break;
	         case ",":
	              if (!introducirNuevoNum){
	                  if (!hayComa){
	                      String newResult = lblResult.getText();
	                      lblResult.setText(newResult +",");
	                  }
	              } else {
	                  lblResult.setText("0,0");
	                  introducirNuevoNum = false;
	              }
	              hayComa = true;
	             break;
	         case "C":
	             lblResult.setText("0");
	             result = 0;
	             introducirNuevoNum = true;
	             hayComa = false;
	             ultimaOperacion = null;
	             break;
	         case "=":
	         operar(ultimaOperacion, parseLabel(lblContent));
	             lblResult.setText(String.valueOf(result));
	             break;
	         case "AC":
	        	 break;
	         case "CE":
	             lblResult.setText("0");
	             introducirNuevoNum = true;
	             hayComa = false;
	        	 break;
	         default:
	         
	             if (introducirNuevoNum){
	                 lblResult.setText(btnText);
	             } else {
	                 lblResult.setText(lblContent + btnText);
	             }
	             introducirNuevoNum = false;
	             break;
	     }
	       //lblResult.setText(String.valueOf(result));
	}
	}
}