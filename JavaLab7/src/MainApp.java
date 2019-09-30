import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



class FereastraMea extends JFrame{
	//static variables
	static JTextField Operand1;
	static JTextField Operand2;
	//static JTextField Result;
	static JList  result;
	//lista creata fara vector
	static DefaultListModel listModel = new DefaultListModel();
	static JPanel buttons = new JPanel();
	//array for list items
	static int selecteditems[];
	
	public FereastraMea(String titlu){
		super(titlu);
		//setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit t = Toolkit.getDefaultToolkit();
		setSize(300,400);
		//new Panel
		JPanel subPanel = new JPanel(new FlowLayout());
		//instantiate our panel layout
		
		getContentPane().setLayout(new GridLayout(4,1));
		
		//our labels
		JLabel lblOperand1 = new JLabel("Operand 1");
		Operand1=new JTextField(15);
		
		JLabel lblOperand2 = new JLabel("Operand 2");
		Operand2=new JTextField(15);
		
		//placing our controls in the window
		subPanel.add(lblOperand1);
		subPanel.add(Operand1);
		subPanel.add(lblOperand2);
		subPanel.add(Operand2);
		getContentPane().add(subPanel);
		
		//creating subPanel
		JButton btnAdd = new JButton("Adunare");
		btnAdd.setPreferredSize(new Dimension(120,30));
		JButton btnSubstract = new JButton("Scadere");
		btnSubstract.setPreferredSize(new Dimension(120, 30));
		JButton btnMultiply = new JButton("Inmultire");
		btnMultiply.setPreferredSize(new Dimension(120, 30));
		JButton btnDivide = new JButton("Impartire");
		btnDivide.setPreferredSize(new Dimension(120, 30));
		
		//adding listeners
		btnAdd.addActionListener(new Clicked(Operand1, Operand2));
		btnSubstract.addActionListener(new Clicked(Operand1, Operand2));
		btnMultiply.addActionListener(new Clicked(Operand1, Operand2));
		btnDivide.addActionListener(new Clicked(Operand1, Operand2));
		Operand1.addKeyListener(new CheckText());
		Operand2.addKeyListener(new CheckText());
		
		buttons.add(btnAdd);
		buttons.add(btnSubstract);
		//second row
		buttons.add(btnMultiply);
		buttons.add(btnDivide);
		getContentPane().add(buttons);
		
		//adding result text
		JPanel text = new JPanel();
		result = new JList(listModel);
		result.setBounds(50,50,50,50);
		//Result=new JTextField(15);
		text.add(result);
		getContentPane().add(text);
		
		//Clear button
		JPanel clear= new JPanel();
		JButton btnClear = new JButton("Clear");
		btnClear.setPreferredSize(new Dimension(120, 30));
		clear.add(btnClear);
		//getContentPane().add(clear);
		
		//Delete List button
		JPanel Delete= new JPanel();
		JButton btnDelete = new JButton("Delete");
		btnDelete.setPreferredSize(new Dimension(120, 30));
		Delete.add(btnDelete);
		
		//listeners for clear and list
		//btnDelete.addActionListener(new Clicked(Operand1, Operand2));
		btnDelete.addActionListener(new removeList(listModel));
		getContentPane().add(Delete);
		
		//both operands must be filled
		if(FereastraMea.Operand1.getText() == "" && FereastraMea.Operand2.getText() == "")
		{
			buttons.getComponent(0).setEnabled(false);
			buttons.getComponent(1).setEnabled(false);
			buttons.getComponent(2).setEnabled(false);
			buttons.getComponent(3).setEnabled(false);
		}
	}
}

//listener for Buttons
class Clicked implements ActionListener
{
	JTextField Operand1;
	JTextField Operand2;
	public Clicked(JTextField operand1, JTextField operand2) {
		this.Operand1 = operand1;
		this.Operand2 = operand2;
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String op1 = Operand1.getText();
		String op2 = Operand2.getText();
		if(e.getActionCommand() == "Adunare")
		{
			double result = Double.parseDouble(op1) + Double.parseDouble(op2) ;
			FereastraMea.listModel.addElement(result);
		}
		if(e.getActionCommand() == "Scadere")
		{
			double result = Double.parseDouble(op1) - Double.parseDouble(op2) ;
			FereastraMea.listModel.addElement(result);
		}

		if(e.getActionCommand() == "Inmultire")
		{
			double result = Double.parseDouble(op1) * Double.parseDouble(op2) ;
			FereastraMea.listModel.addElement(result);
		}

		if(e.getActionCommand() == "Impartire")
		{
			double result = Double.parseDouble(op1) / Double.parseDouble(op2) ;
			FereastraMea.listModel.addElement(result);
		}
		if(e.getActionCommand() == "Clear")
		{
			FereastraMea.Operand1.setText("");
			FereastraMea.Operand2.setText("");
			FereastraMea.listModel.clear();;
		}
	}
}



//Mouse listener
class ListClick implements MouseListener
{
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//FereastraMea.selecteditems[i] = FereastraMea.result.getSelectedIndex();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
//listener for TextBoxes
class CheckText implements KeyListener
{
	
	@Override
	public void keyPressed(KeyEvent e) {
		//No Need
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		FereastraMea.buttons.getComponent(0).setEnabled(true);
		FereastraMea.buttons.getComponent(1).setEnabled(true);
		FereastraMea.buttons.getComponent(2).setEnabled(true);
		FereastraMea.buttons.getComponent(3).setEnabled(true);
		try {
				Integer.parseInt(FereastraMea.Operand1.getText());
				Integer.parseInt(FereastraMea.Operand2.getText());
			
		}
		catch(NumberFormatException n){
			FereastraMea.buttons.getComponent(0).setEnabled(false);
			FereastraMea.buttons.getComponent(1).setEnabled(false);
			FereastraMea.buttons.getComponent(2).setEnabled(false);
			FereastraMea.buttons.getComponent(3).setEnabled(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
//listener for Jlist
class removeList implements ActionListener
{

	DefaultListModel listModel;
	public removeList( DefaultListModel listModel) {
		this.listModel = listModel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		int[] selecteditems = FereastraMea.result.getSelectedIndices();
		for(int i=selecteditems.length-1;i>=0;i--){
			listModel.removeElementAt(selecteditems[i]);
		}
	}
	
}

public class MainApp {
	public static void main(String[] args)
	{
		JFrame f = new FereastraMea("Calculator");
		f.setVisible(true);
	}
}
