import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Lista extends JFrame
{
	static JList trupe;
	static JTextField banda;
	static DefaultListModel listModel = new DefaultListModel();
	public Lista(String title)
	{
		super(title);
		//setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit t = Toolkit.getDefaultToolkit();
		setSize(300,400);
		//instantiate our panel layout

		getContentPane().setLayout(new BorderLayout());
		
		banda = new JTextField();
		getContentPane().add(BorderLayout.NORTH, banda);
		banda.addKeyListener(new adauga());
		
		trupe = new JList(listModel);
		getContentPane().add(BorderLayout.CENTER,trupe);
		
		JButton sterge = new JButton("Sterge element");
		getContentPane().add(BorderLayout.SOUTH,sterge);
		sterge.addActionListener(new stergere());
	}
	
}

class adauga implements KeyListener
{
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			Lista.listModel.addElement(Lista.banda.getText());
			Lista.banda.setText("");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

class stergere implements ActionListener
{
	
	DefaultListModel lm= (DefaultListModel) Lista.trupe.getModel();
	@Override
	public void actionPerformed(ActionEvent e) {
			int sters = Lista.trupe.getSelectedIndex();
			lm.removeElementAt(sters);
			
	}
	
}

public class Bands {
	public static void main(String[] args)
	{
		JFrame f=new Lista("trupe");
		f.setVisible(true);
	}
}
