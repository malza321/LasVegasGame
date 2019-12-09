

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javazoom.jl.player.Player;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.print.attribute.standard.PrinterLocation;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.swing.Action;
import java.awt.event.ActionListener;
import java.awt.SystemColor;

public class CreateGUI extends JFrame {
	private JPanel contentPane;
	private JTextField id;
	private JTextField ip;
	JLabel background = new JLabel("");
	public static String yid;
	public static String yip;
	Music intromusic = new Music("Bike_Rides.mp3",true);


	public CreateGUI() {
		setAlwaysOnTop(true);
		setTitle("Login");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1150, 720);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel idt = new JLabel("ID");
		idt.setFont(new Font("LiHei Pro", Font.PLAIN, 32));
		idt.setBounds(40, 200, 80, 40);
		contentPane.add(idt);

		JLabel ipt = new JLabel("IP");
		ipt.setFont(new Font("LiHei Pro", Font.PLAIN, 32));
		ipt.setBounds(40, 250, 80, 40);
		contentPane.add(ipt);

		id = new JTextField();
		id.setBounds(110, 200, 200, 40);
		contentPane.add(id);
		id.setColumns(10);

		ip = new JTextField();
		ip.setColumns(10);
		ip.setBounds(110, 250, 200, 40);
		contentPane.add(ip);
	
		
		JLabel stnbutton = new JLabel(new ImageIcon(Maincode.class.getResource("/images/Startbasic.png")));

		
		//stnbutton.setIcon(new ImageIcon(Maincode.class.getResource("/images/Startbasic.png")));
		stnbutton.setBackground(SystemColor.control);
		stnbutton.setBounds(20, 300, 400, 100);
		stnbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){
				stnbutton.setIcon(new ImageIcon(Maincode.class.getResource("/images/Startentered.png")));
				stnbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e){
				stnbutton.setIcon(new ImageIcon(Maincode.class.getResource("/images/Startbasic.png")));
				stnbutton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e){
				System.out.println("aa");
				stnbutton.setVisible(false);
				id.setVisible(false);
				ip.setVisible(false);
				idt.setVisible(false);
				ipt.setVisible(false);
				System.out.println("aaa");
				yid=id.getText();
				yip=ip.getText();
				dispose();
				intromusic.close();
				System.out.println("aaaa");
				new mainGUI();

			}

		});
		ip.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stnbutton.setVisible(false);
				id.setVisible(false);
				ip.setVisible(false);
				idt.setVisible(false);
				ipt.setVisible(false);
				System.out.println("aaa");
				yid=id.getText();
				yip=ip.getText();
				dispose();
				intromusic.close();
				
				new mainGUI();
			
			}
			});




		contentPane.add(stnbutton);
		background.setIcon(new ImageIcon(CreateGUI.class.getResource("/images/bg.jpg")));
		background.setBounds(0, 0, 1200, 720);
		contentPane.add(background);
		setVisible(true);
		intromusic.start();


	}

}
