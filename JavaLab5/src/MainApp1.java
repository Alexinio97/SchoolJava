import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Fereastra extends JFrame{
	 static JLabel operand1,operand2;
	 static JTextField op1;
	 static JTextField op2;
	 static JButton bsum,bdif,bmux,bdiv,bclear;
	public Fereastra(String titlu)
	{
		//setari pt fereastra
		super(titlu);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300,400);
		setLayout(new FlowLayout());
		
		//declarari
		operand1=new JLabel("operand1");
		operand2=new JLabel("operand2");
		op1=new JTextField(15);
		op2=new JTextField(15);
		bsum=new JButton("Adunare");
	    bdif=new JButton("Scadere");
		bmux=new JButton("Inmultire");
		bdiv=new JButton("Impartire");
		bclear=new JButton("Clear");
		JTextArea rez=new JTextArea();
		
		//setari dim 
		op1.setPreferredSize(new Dimension(100,30));
		op2.setPreferredSize(new Dimension(100,30));
		operand1.setPreferredSize(new Dimension(100,20));
		operand2.setPreferredSize(new Dimension(100,20));
		rez.setPreferredSize(new Dimension(200,60));
		
		//adaugare in container
		getContentPane().add(operand1);
		getContentPane().add(op1);
		getContentPane().add(operand2);
		getContentPane().add(op2);
		getContentPane().add(bsum);
		getContentPane().add(bdif);
		getContentPane().add(bmux);
		getContentPane().add(bdiv);
		getContentPane().add(rez);
		getContentPane().add(bclear);
		
		//apelare fct
		op1.addKeyListener(new check());
		op2.addKeyListener(new check());
		bsum.addActionListener(new Action(op1,op2,rez));
		bdif.addActionListener(new Action(op1,op2,rez));
		bmux.addActionListener(new Action(op1,op2,rez));
		bdiv.addActionListener(new Action(op1,op2,rez));
		bclear.addActionListener(new Action(op1,op2,rez));

	}
}
class Action implements ActionListener{
	JTextField op1,op2;
	JTextArea rez;
	public Action(JTextField opp1,JTextField opp2,JTextArea rezz)
	{
		this.op1=opp1;
		this.op2=opp2;
		this.rez=rezz;
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand()=="Adunare")
		{
			rez.setText(String.valueOf(Double.parseDouble(op1.getText())+Double.parseDouble(op2.getText())));
		}
		else if(e.getActionCommand()=="Scadere")
		{
			rez.setText(String.valueOf(Double.parseDouble(op1.getText())-Double.parseDouble(op2.getText())));
		}
		else if(e.getActionCommand()=="Inmultire")
		{
			rez.setText(String.valueOf(Double.parseDouble(op1.getText())*Double.parseDouble(op2.getText())));
		}
		else if(e.getActionCommand()=="Impartire")
		{
			rez.setText(String.valueOf(Double.parseDouble(op1.getText())/Double.parseDouble(op2.getText())));
		}
		else if(e.getActionCommand()=="Clear")
		{
			op1.setText("");
			op2.setText("");
			rez.setText("");
		}
	}
}

class check implements KeyListener
{

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		Fereastra.bsum.setEnabled(true);
		Fereastra.bdif.setEnabled(true);
		Fereastra.bdiv.setEnabled(true);
		Fereastra.bmux.setEnabled(true);
		try {
			
				Integer.parseInt(Fereastra.op1.getText());
			
			
				Integer.parseInt(Fereastra.op2.getText());
			
		}
		catch(NumberFormatException n){
			Fereastra.bsum.setEnabled(false);
			Fereastra.bdif.setEnabled(false);
			Fereastra.bdiv.setEnabled(false);
			Fereastra.bmux.setEnabled(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}

public class MainApp1{
	public static void main(String[] args) {
	JFrame f=new Fereastra("Calculator");
	f.setVisible(true);
}
}