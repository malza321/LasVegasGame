import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.*;


public class mainGUI extends JFrame {

	private JPanel contentPane;
	static JTextField txtMessage = new JTextField();
	static JTextArea textArea = new JTextArea();

	static String command;
	static JTextField tip = new JTextField();;
	static JTextArea tipmessage= new JTextArea();;

	static String moneyarray[];
	static String moneyuptodown[][] = new String[6][];

	static JTextField p1n = new JTextField(); /////////////players//////////////
	static JTextField p2n = new JTextField();
	static JTextField p3n = new JTextField();
	static JTextField p4n = new JTextField();

	static String player[] = new String[4]; 
	static JLabel pchar[] = new JLabel[4];

	static JLabel []dicepo = new JLabel[8]; //////////// rolling dice position////////
	static ImageIcon []diceim = new ImageIcon[7];/////////////////////////////////

	static Music mainmusic = new Music("mainMusic.mp3",true); //////music/////////
	static Music finishmusic = new Music("About_That_Oldie.mp3",true);
	static ConnectToServer server = new ConnectToServer(CreateGUI.yip,33333);


	static JLabel bettingcount[][] = new JLabel[7][5]; ////////betting coin////////
	static JLabel bettingcoin[][] = new JLabel[7][5];  ///////////////////////////
	static JTextArea money[] = new JTextArea[7];
	static JLabel dollar[] = new JLabel[7];

	static String[] dicenum;
	static int [] dicearr = new int[7];
	static int []leftnum = new int[5];
	static int [][] betcount = new int [7][5];
	static int playernum;
	static String[] tokens;
	static int playermoney[] = new int[5];
	static int rank[] = new int[5];

	static boolean startoryet = false;
	static boolean isround = true;
	static boolean isAuto = false;

	static JLabel round1ani = new JLabel();
	static JLabel round2ani = new JLabel();
	static JLabel finishani = new JLabel();

	static JLabel roundstart = new JLabel("");
	static JLabel Automode = new JLabel("");
	JLabel logo = new JLabel("");

	static JLabel finishboard = new JLabel("");
	JLabel mainbackground = new JLabel("");
	static JLabel ranknum[] = new JLabel[5];

	static JLabel finishmoney[] = new JLabel[5];
	static int tipcount[] = new int[7];
	
	static int me;


	public mainGUI() {
		server.start();
		sendMessage(CreateGUI.yid);
		setAlwaysOnTop(true);
		for(int i=1 ; i<7; i++){
			diceim[i] = new ImageIcon(Maincode.class.getResource("/images/dice" + i + ".png"));
		}
		setTitle("Casino Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1150, 700);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		mainmusic.start();

		round1ani.setBounds(0, 0, 1150, 550);
		round1ani.setVisible(false);
		contentPane.add(round1ani);

		round2ani.setBounds(0, 0, 1150, 550);
		round2ani.setVisible(false);
		contentPane.add(round2ani);

		finishani.setBounds(200, -30, 800, 600);
		finishani.setVisible(false);
		contentPane.add(finishani);

		for(int i=1 ; i<5 ; i++){
			finishmoney[i] = new JLabel();
			finishmoney[i].setFont(new Font("Lucida Grande", Font.PLAIN, 25));
			finishmoney[i].setBounds(370+100*i, 10+105*i, 150, 50);
			finishmoney[i].setForeground(Color.RED);
			finishmoney[i].setVisible(false);
			contentPane.add(finishmoney[i]);
		}

		////////////players///////////////
		for(int i=0 ; i<4 ; i++) pchar[i] = new JLabel();

		pchar[0].setIcon(new ImageIcon(mainGUI.class.getResource("/images/p1.png")));
		pchar[0].setBounds(0, 150, 150, 150);
		pchar[0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){

				p1n.setText(playermoney[1]+"");
			}
			@Override
			public void mouseExited(MouseEvent e){
				p1n.setText(player[0]);
			}
		});
		contentPane.add(pchar[0]);

		pchar[1].setIcon(new ImageIcon(mainGUI.class.getResource("/images/p2.png")));
		pchar[1].setBounds(0, 400, 150, 150);
		pchar[1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){

				p2n.setText(playermoney[2]+"");
			}
			@Override
			public void mouseExited(MouseEvent e){
				p2n.setText(player[1]);
			}
		});
		contentPane.add(pchar[1]);

		pchar[2].setIcon(new ImageIcon(mainGUI.class.getResource("/images/p3.png")));
		pchar[2].setBounds(400, 560, 150, 150);
		pchar[2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){

				p3n.setText(playermoney[3]+"");
			}
			@Override
			public void mouseExited(MouseEvent e){
				p3n.setText(player[2]);
			}
		});
		contentPane.add(pchar[2]);

		pchar[3].setIcon(new ImageIcon(mainGUI.class.getResource("/images/p4.png")));
		pchar[3].setBounds(650, 560, 150, 150);
		pchar[3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){

				p4n.setText(playermoney[4]+"");
			}
			@Override
			public void mouseExited(MouseEvent e){
				p4n.setText(player[3]);
			}
		});
		contentPane.add(pchar[3]);

		//////////player name/////////////

		p1n.setBackground(Color.RED);
		p1n.setHorizontalAlignment(SwingConstants.CENTER);
		p1n.setText("Player1");
		p1n.setBounds(30, 280, 90, 30);
		contentPane.add(p1n);
		p1n.setColumns(10);


		p2n.setText("Player2");
		p2n.setHorizontalAlignment(SwingConstants.CENTER);
		p2n.setColumns(10);
		p2n.setBackground(Color.RED);
		p2n.setBounds(30, 540, 90, 30);
		contentPane.add(p2n);


		p3n.setText("Player3");
		p3n.setHorizontalAlignment(SwingConstants.CENTER);
		p3n.setColumns(10);
		p3n.setBackground(Color.RED);
		p3n.setBounds(320, 620, 90, 30);
		contentPane.add(p3n);


		p4n.setText("Player4");
		p4n.setHorizontalAlignment(SwingConstants.CENTER);
		p4n.setColumns(10);
		p4n.setBackground(Color.RED);
		p4n.setBounds(800, 620, 90, 30);
		contentPane.add(p4n);

		for(int i=1 ; i<5 ; i++) ranknum[i] = new JLabel();
		for(int i=1 ; i<5 ; i++){
			ranknum[i].setBounds(250+100*i, -40+100*i, 100, 100);
			ranknum[i].setVisible(false);
			contentPane.add(ranknum[i]);
		}

		finishboard.setIcon(new ImageIcon(mainGUI.class.getResource("/images/finish.png")));
		finishboard.setBounds(200, -30, 800, 600);
		finishboard.setVisible(false);
		contentPane.add(finishboard);

		for(int i=1; i<4;i++){
			dollar[i] = new JLabel("");
			dollar[i].setIcon(new ImageIcon(mainGUI.class.getResource("/images/dollar.png")));
			dollar[i].setBounds(220+230*i, 70, 20, 20);
			contentPane.add(dollar[i]);

			money[i] = new JTextArea("");
			money[i].setFont(new Font("Lucida Grande", Font.BOLD, 14));
			money[i].setBounds(35+230*i, 70, 200, 20);
			money[i].setEditable(false);
			contentPane.add(money[i]);

			dollar[i+3] = new JLabel("");
			dollar[i+3].setIcon(new ImageIcon(mainGUI.class.getResource("/images/dollar.png")));
			dollar[i+3].setBounds(220+230*i, 345, 20, 20);
			contentPane.add(dollar[i+3]);

			money[i+3] = new JTextArea("");
			money[i+3].setFont(new Font("Lucida Grande", Font.BOLD, 14));
			money[i+3].setBounds(35+230*i, 345, 200, 20);
			money[i+3].setEditable(false);
			contentPane.add(money[i+3]);


		}


		logo.setIcon(new ImageIcon(Maincode.class.getResource("/images/logo.png")));
		logo.setBounds(-70, 0, 400, 160);
		contentPane.add(logo);



		/////////////////betting coins////////////////
		for(int j=1;j<4;j++){
			for(int i=1;i<5;i++){

				bettingcoin[j][i] = new JLabel("New label");
				bettingcoin[j][i].setVisible(false);
				bettingcoin[j][i].setBounds(50+230*j, 60+40*i, 40, 40);
				contentPane.add(bettingcoin[j][i]);

				bettingcount[j][i] = new JLabel("");
				bettingcount[j][i].setVisible(false);
				bettingcount[j][i].setForeground(Color.ORANGE);
				bettingcount[j][i].setFont(new Font("Lucida Grande", Font.BOLD, 24));
				bettingcount[j][i].setBounds(130+230*j, 60+40*i, 100, 40);
				contentPane.add(bettingcount[j][i]);

				bettingcoin[j+3][i] = new JLabel("New label");
				bettingcoin[j+3][i].setVisible(false);
				bettingcoin[j+3][i].setBounds(50+230*j, 335+40*i, 40, 40);
				contentPane.add(bettingcoin[j+3][i]);

				bettingcount[j+3][i] = new JLabel("");
				bettingcount[j+3][i].setVisible(false);
				bettingcount[j+3][i].setForeground(Color.ORANGE);
				bettingcount[j+3][i].setFont(new Font("Lucida Grande", Font.BOLD, 24));
				bettingcount[j+3][i].setBounds(130+230*j, 335+40*i, 100, 40);
				contentPane.add(bettingcount[j+3][i]);
			}
		}
		for(int i=1;i<7;i++){
			for(int j=1 ; j<5 ; j++){
				bettingcoin[i][j].setIcon(new ImageIcon(Maincode.class.getResource("/images/coinim" + j + ".png")));
			}
		}





		txtMessage.setForeground(SystemColor.activeCaption);
		txtMessage.setHorizontalAlignment(SwingConstants.CENTER);
		txtMessage.setEditable(false);
		txtMessage.setBackground(SystemColor.inactiveCaptionBorder);
		txtMessage.setText("Message");
		txtMessage.setBounds(950, 0, 200, 20);
		contentPane.add(txtMessage);
		textArea.setLineWrap(true);

		textArea.setEditable(false);
		textArea.setEnabled(true);
		textArea.setBackground(UIManager.getColor("TextComponent.selectionBackgroundInactive"));
		textArea.setBounds(950, 20, 200, 200);
		contentPane.add(textArea);

		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(950, 20, 200, 200);
		contentPane.add(scroll);

		tip.setText("Tip");
		tip.setHorizontalAlignment(SwingConstants.CENTER);
		tip.setForeground(Color.WHITE);
		tip.setEditable(false);
		tip.setBackground(Color.GRAY);
		tip.setBounds(950, 450, 200, 20);
		contentPane.add(tip);

		tipmessage.setEnabled(false);
		tipmessage.setBackground(UIManager.getColor("Separator.foreground"));
		tipmessage.setBounds(950, 470, 200, 200);
		tipmessage.setLineWrap(true);
		contentPane.add(tipmessage);


		///////////rolling dice/////////////
		for(int i=0;i<8;i++){
			dicepo[i]= new JLabel("");
			dicepo[i].setBounds(265+60*i, 320, 30, 30);
			contentPane.add(dicepo[i]);

		}



		JLabel roll = new JLabel("");
		roll.setIcon(new ImageIcon(mainGUI.class.getResource("/images/dicebutton.png")));
		roll.setBounds(1050, 250, 60, 60);
		roll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){
				roll.setIcon(new ImageIcon(mainGUI.class.getResource("/images/dicebuttonentered.png")));
				roll.setCursor(new Cursor(Cursor.HAND_CURSOR));


			}
			@Override
			public void mouseExited(MouseEvent e){
				roll.setIcon(new ImageIcon(mainGUI.class.getResource("/images/dicebutton.png")));
				roll.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	
			}
			@Override
			public void mousePressed(MouseEvent e){
				Music rollsound = new Music("rollingdice.mp3",false);
				rollsound.start();
				if(startoryet) mainmusic.suspend();
				sendMessage("roll");
			}
		});
		contentPane.add(roll);


		//////////////bet button/////////////
		JLabel bet1_ = new JLabel("");
		bet1_.setIcon(new ImageIcon(mainGUI.class.getResource("/images/bet.png")));
		bet1_.setBounds(265, 265, 200, 30);
		bet1_.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){
				bet1_.setIcon(new ImageIcon(mainGUI.class.getResource("/images/betentered.png")));
				bet1_.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e){
				bet1_.setIcon(new ImageIcon(mainGUI.class.getResource("/images/bet.png")));
				bet1_.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e){
				Music bettingsound = new Music("bettingsound.mp3",false);
				bettingsound.start();
				sendMessage("1");
			}
		});contentPane.add(bet1_);

		JLabel bet2_ = new JLabel("");
		bet2_.setIcon(new ImageIcon(mainGUI.class.getResource("/images/bet.png")));
		bet2_.setBounds(495, 265, 200, 30);
		bet2_.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){
				bet2_.setIcon(new ImageIcon(mainGUI.class.getResource("/images/betentered.png")));
				bet2_.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e){
				bet2_.setIcon(new ImageIcon(mainGUI.class.getResource("/images/bet.png")));
				bet2_.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e){
				Music bettingsound = new Music("bettingsound.mp3",false);
				bettingsound.start();
				sendMessage("2");
			}
		});contentPane.add(bet2_);

		JLabel bet3_ = new JLabel("");
		bet3_.setIcon(new ImageIcon(mainGUI.class.getResource("/images/bet.png")));
		bet3_.setBounds(720, 265, 200, 30);
		bet3_.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){
				bet3_.setIcon(new ImageIcon(mainGUI.class.getResource("/images/betentered.png")));
				bet3_.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e){
				bet3_.setIcon(new ImageIcon(mainGUI.class.getResource("/images/bet.png")));
				bet3_.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e){
				Music bettingsound = new Music("bettingsound.mp3",false);
				bettingsound.start();
				sendMessage("3");
			}

		});contentPane.add(bet3_);

		JLabel bet4_ = new JLabel("");
		bet4_.setIcon(new ImageIcon(mainGUI.class.getResource("/images/bet.png")));
		bet4_.setBounds(265, 540, 200, 30);
		bet4_.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){
				bet4_.setIcon(new ImageIcon(mainGUI.class.getResource("/images/betentered.png")));
				bet4_.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e){
				bet4_.setIcon(new ImageIcon(mainGUI.class.getResource("/images/bet.png")));
				bet4_.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e){
				Music bettingsound = new Music("bettingsound.mp3",false);
				bettingsound.start();
				sendMessage("4");
			}

		});contentPane.add(bet4_);

		JLabel bet5_ = new JLabel("");
		bet5_.setIcon(new ImageIcon(mainGUI.class.getResource("/images/bet.png")));
		bet5_.setBounds(495, 540, 200, 30);
		bet5_.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){
				bet5_.setIcon(new ImageIcon(mainGUI.class.getResource("/images/betentered.png")));
				bet5_.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e){
				bet5_.setIcon(new ImageIcon(mainGUI.class.getResource("/images/bet.png")));
				bet5_.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e){
				Music bettingsound = new Music("bettingsound.mp3",false);
				bettingsound.start();
				sendMessage("5");
			}
		});contentPane.add(bet5_);

		JLabel bet6_ = new JLabel("");
		bet6_.setIcon(new ImageIcon(mainGUI.class.getResource("/images/bet.png")));
		bet6_.setBounds(720, 540, 200, 30);
		bet6_.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){
				bet6_.setIcon(new ImageIcon(mainGUI.class.getResource("/images/betentered.png")));
				bet6_.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e){
				bet6_.setIcon(new ImageIcon(mainGUI.class.getResource("/images/bet.png")));
				bet6_.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e){
				Music bettingsound = new Music("bettingsound.mp3",false);
				bettingsound.start();
				sendMessage("6");
			}
		});contentPane.add(bet6_);


		roundstart.setBounds(950, 380, 200, 50);
		roundstart.setIcon(new ImageIcon(mainGUI.class.getResource("/images/roundstart.png")));
		roundstart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){
				if(!startoryet){
					roundstart.setIcon(new ImageIcon(mainGUI.class.getResource("/images/roundstartentered.png")));
					roundstart.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}

			}
			@Override
			public void mouseExited(MouseEvent e){
				if(!startoryet){
					roundstart.setIcon(new ImageIcon(mainGUI.class.getResource("/images/roundstart.png")));
					roundstart.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}

			}
			@Override
			public void mousePressed(MouseEvent e){
				if(!startoryet){
					roundani(1);
					roundstart.setVisible(false);
					Automode.setVisible(true);
					sendMessage("start_game");
				}
			}
		});
		contentPane.add(roundstart);

		Automode.setBounds(950, 380, 200, 50);
		Automode.setIcon(new ImageIcon(mainGUI.class.getResource("/images/Autoon.png")));
		Automode.setVisible(false);
		Automode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){
				if(!isAuto){
					Automode.setIcon(new ImageIcon(mainGUI.class.getResource("/images/Autoonentered.png")));
					Automode.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}		
				if(isAuto){
					Automode.setIcon(new ImageIcon(mainGUI.class.getResource("/images/Autooffentered.png")));
					Automode.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}		
			}
			@Override
			public void mouseExited(MouseEvent e){
				if(!isAuto){
					Automode.setIcon(new ImageIcon(mainGUI.class.getResource("/images/Autoon.png")));
					Automode.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				if(isAuto){
					Automode.setIcon(new ImageIcon(mainGUI.class.getResource("/images/Autooff.png")));
					Automode.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}		
			}
			@Override
			public void mousePressed(MouseEvent e){
				if(isAuto){
					isAuto = false;
					textArea.append("자동모드를 종료합니다.");
				}
				else{
					isAuto = true;
					textArea.append("자동모드를 시작합니다.");
					sendMessage("roll");
					if(command.equals("roll_result")){
						if(tokens[2].split("@")[0].equals(CreateGUI.yid.split(" ")[0]))
							sendMessage(dicenum[(int)Math.random()*(dicenum.length-1)]);
					}
				}
			}
		});
		contentPane.add(Automode);




		mainbackground.setIcon(new ImageIcon(mainGUI.class.getResource("/images/gamebackground.jpg")));
		mainbackground.setBounds(0, 0, 1150, 700);
		contentPane.add(mainbackground);











		setVisible(true);
	}



	//////////////////////////////Server///////////////////////////


	static void sendMessage(String str){
		ConnectToServer.sender.println(str);
		ConnectToServer.sender.flush();
	}

	static void parsing(String line){
		tokens = line.split("&");
		command = tokens[1]; 
		switch(command){
		case "round_start":
			roundstart.setVisible(false); //스타트버튼 삭제 
			Automode.setVisible(true); //자동모드 버튼 삽입 
			startoryet = true; //게임시작
			gameset(); //게임 셋팅(1라운드 종료 후 재시작위해 걸려있던돈 초기화)
			playernameset(tokens[2]); //서버에서 받은 이름 삽
			marray(tokens[3]);//걸린돈 큰순서대로 배
			break;
		case "turn":
			turnposition(tokens[2]); //차례인사람 한보앞으로.
			if(isAuto){//오토모드 주사위굴리
				if(tokens[2].equals(CreateGUI.yid.split(" ")[0])){
					sendMessage("roll");
				}
			}
			break;
		case "roll":
			textArea.append(tokens[0] + "이(가) 주사위를 굴립니다.\n");
			textArea.setCaretPosition(mainGUI.textArea.getDocument().getLength());
			break;
		case "roll_result":
			dicenum = (tokens[2].split("@"))[1].split(",");
			dicecount(); //주사위 각수의 갯수 //
			diceimage(); //주사위 이미지출력 //
			if(isAuto){//오토모드 배팅 
				if(tokens[2].split("@")[0].equals(CreateGUI.yid.split(" ")[0])){
					sendMessage(dicenum[(int)Math.random()*(dicenum.length-1)]);
				}
			}
			showtip();

			break;
		case "bat": //bet
			tipmessage.setText("");
			textArea.append(tokens[0] + "님이 " + tokens[2] + "에 배팅했습니다..\n" );
			textArea.setCaretPosition(mainGUI.textArea.getDocument().getLength()); //커서위치 맨아래로 
			backtoposition(); //자리로 돌아가기//
			try{
				Thread.sleep(200);

			}catch(InterruptedException ex){
				ex.printStackTrace();

			}
			mainmusic.resume();
			playernum = getplayernum(tokens[0]);//플레이어 몇번째인지//
			bettingcon(); //배팀액//
			break;
		case "round_result":
			betarray(betcount);//결과//
			if(isround){ //1라운드 끝날시 
				countani(); //이름표에 번돈 출
				roundani(2); //라운드 2 애니메이
			}
			isround = false;
			reset();
			break;
		case "game_finish":
			showrank(); //랭크 출
			finishani(); // 반짝반
			mainmusic.close(); //메인음악 종료 
			finishmusic.start(); //피니쉬음악 
			break;

		}

	}
	static void showtip() {
		me = 0;
		for(int i=0; i<4; i++){
						if((tokens[2].split("@"))[0].equalsIgnoreCase(player[i])){
							me = (i+1);
						}
					}
		if((tokens[2].split("@"))[0].equalsIgnoreCase(CreateGUI.yid.split(" ")[0])){
		for(int i=1; i<7; i++){
							if(dicearr[i]>=1){	
								if(betcount[i][me]>=betcount[i][1] && betcount[i][me]>=betcount[i][2] && 
								betcount[i][me]>=betcount[i][3] && betcount[i][me]>=betcount[i][4]){
										if(betcount[i][me]==0)tipmessage.append(i+"번 카지노를 노리세요 \n");
										if(betcount[i][me]>=1)tipmessage.append(i+"번 카지노를 지키세 \n");
								}
							}	
						}
					}
	
	}



	static void gameset() {
		for(int i=1; i<7 ;i++){
			for(int j=1 ; j<5; j++){
				betcount[i][j]=0;
			}
		}
	}


	static void reset() {
		for(int i=1; i<7 ;i++){
			money[i].setText("");
			for(int j=1 ; j<5; j++){
				bettingcoin[i][j].setVisible(false);
				bettingcount[i][j].setVisible(false);
			}
		}
	}

	static void bettingcon() {
		bettingcoin[Integer.parseInt(tokens[2])][playernum].setVisible(true);
		bettingcount[Integer.parseInt(tokens[2])][playernum].setVisible(true);
		backtoposition();
		for(int i=0;i<8;i++) dicepo[i].setVisible(false);
		betcount[Integer.parseInt(tokens[2])][playernum] += dicearr[Integer.parseInt(tokens[2])]; //플레이별 테이블에 걸린코인//
		bettingcount[Integer.parseInt(tokens[2])][playernum].setText("X      " + betcount[Integer.parseInt(tokens[2])][playernum]);

	}


	static void dicecount() {
		for(int i = 1 ; i<7 ; i++)dicearr[i]=0;
		for(int i=  0;i<dicenum.length;i++){
			dicearr[Integer.parseInt(dicenum[i])]++; //주사위 갯수//
		}

	}


	static void diceimage() {
		for(int i=0;i<dicenum.length;i++){
			for(int j=1; j<7 ; j++){
				if(Integer.parseInt(dicenum[i])==j) dicepo[i].setIcon((diceim[j]));
			}
			dicepo[i].setVisible(true);
			try{
				Thread.sleep(200);

			}catch(InterruptedException ex){
				ex.printStackTrace();

			}
		}

	}

	static void roundani(int roundnum){
		new Thread(){
			@Override
			public void run(){
				for(int i=0 ; i<89 ; i++){
					round1ani.setVisible(true);
					round2ani.setVisible(true);
					if(roundnum==1){
						round1ani.setIcon(new ImageIcon(Maincode.class.getResource("/round1/" + i + ".png")));
					}
					if(roundnum==2){
						round2ani.setIcon(new ImageIcon(Maincode.class.getResource("/round2/" + i + ".png")));
					}
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				round1ani.setVisible(false);
				round2ani.setVisible(false);	
			}

		}.start();
	}
	static void finishani(){
		new Thread(){

			@Override
			public void run(){
				for(int i=0 ; i<26 ; i++){
					finishani.setVisible(true);
					finishani.setVisible(true);
					finishani.setIcon(new ImageIcon(Maincode.class.getResource("/finishani/" + i + ".png")));

					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(i==25)i=0;
				}
			}


		}.start();
	}

	static void countani(){
		new Thread(){

			@Override
			public void run(){
				p1n.setText(playermoney[1]+"");
				p2n.setText(playermoney[2]+"");
				p3n.setText(playermoney[3]+"");
				p4n.setText(playermoney[4]+"");
				for(int i=0 ; i<6 ; i++){
					p1n.setBackground(Color.YELLOW);
					p2n.setBackground(Color.YELLOW);
					p3n.setBackground(Color.YELLOW);
					p4n.setBackground(Color.YELLOW);
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					p1n.setBackground(Color.RED);
					p2n.setBackground(Color.RED);
					p3n.setBackground(Color.RED);
					p4n.setBackground(Color.RED);
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				p1n.setText(player[0]);
				p2n.setText(player[1]);
				p3n.setText(player[2]);
				p4n.setText(player[3]);
			}


		}.start();
	}



	static void turnposition(String tokens) {
		if(player[0].equals(tokens)){
			pchar[0].setBounds(70, 150, 150, 150);
			p1n.setBounds(100, 280, 90, 30);
		}
		if(player[1].equals(tokens)){
			pchar[1].setBounds(70, 400, 150, 150);
			p2n.setBounds(100, 560, 90, 30);
		}
		if(player[2].equals(tokens)){
			pchar[2].setBounds(400, 500, 150, 150);
			p3n.setBounds(320, 560, 90, 30);
		}
		if(player[3].equals(tokens)){
			pchar[3].setBounds(650, 500, 150, 150);
			p4n.setBounds(800, 560, 90, 30);
		}

	}


	static void playernameset(String tokens) {
		player = tokens.split(",");
		p1n.setText(player[0]);
		p2n.setText(player[1]);
		p3n.setText(player[2]);
		p4n.setText(player[3]);

	}


	static void marray(String tokens) {
		moneyarray = tokens.split("@"); //6
		String temp;
		for(int i=0;i<6; i++) {
			moneyuptodown[i] = moneyarray[i].split(",");
		}

		for(int k=0;k<6;k++){
			for(int i=0;i<moneyuptodown[k].length;i++){
				for(int j=i+1;j<moneyuptodown[k].length;j++){
					if(Integer.parseInt(moneyuptodown[k][i])<Integer.parseInt(moneyuptodown[k][j])){
						temp=moneyuptodown[k][i];
						moneyuptodown[k][i]=moneyuptodown[k][j];
						moneyuptodown[k][j]=temp;
					}
				}
			}
		}

		for(int k=0;k<6;k++){
			for(int i=0;i<moneyuptodown[k].length;i++){
				money[k+1].append(moneyuptodown[k][i]);
				if(moneyuptodown[k].length-1 !=i) money[k+1].append(",");

			}
		}

	}


	static int getplayernum(String tokens) {
		int n;
		for(n=0;n<4;n++){
			if(player[n].equals(tokens)) break; 
		}
		return n+1;
	}


	static void backtoposition() {
		pchar[0].setBounds(0, 150, 150, 150);
		pchar[1].setBounds(0, 400, 150, 150);
		pchar[2].setBounds(400, 560, 150, 150);
		pchar[3].setBounds(650, 560, 150, 150);
		p1n.setBounds(30, 280, 90, 30);
		p2n.setBounds(30, 560, 90, 30);
		p3n.setBounds(320, 620, 90, 30);
		p4n.setBounds(800, 620, 90, 30);


	}


	static void betarray(int betcount[][]){
		int [][] betcounttemp = new int [7][5];
		int temp;
		for(int i=1;i<7;i++){
			for(int j=1; j<5; j++){
				betcounttemp[i][j]=betcount[i][j];
			}
		}

		for(int k=1;k<7;k++){
			for(int i=1;i<5;i++){
				if(betcounttemp[k][i]==0) betcounttemp[k][i]=-1;
				for(int j=i+1;j<5;j++){
					if(betcounttemp[k][i]==betcounttemp[k][j]) {
						if(j+1<5)if(betcounttemp[k][i]==betcounttemp[k][j+1]){
							betcounttemp[k][j+1]=-1;
						}
						if(j+2<5)if(betcounttemp[k][i]==betcounttemp[k][j+2]){
							betcounttemp[k][j+2]=-1;
						}
						betcounttemp[k][i]=-1;
						betcounttemp[k][j]=-1;
					}
				}
			}
		}
		for(int k=1;k<7;k++){
			for(int i=1;i<5;i++){
				for(int j=i+1;j<5;j++){
					if(betcounttemp[k][i]<betcounttemp[k][j]){
						temp = betcounttemp[k][i];
						betcounttemp[k][i]=betcounttemp[k][j];
						betcounttemp[k][j]=temp;

					}
				}
			}
		}
		for(int i=1 ; i<7 ; i++){
			for(int j=1; j<=moneyuptodown[i-1].length; j++){
				for(int l=1;l<5;l++){
					if(betcounttemp[i][j]==betcount[i][l]){
						playermoney[l] += Integer.parseInt(moneyuptodown[i-1][j-1]);
						break;
					}
				}

			}
		}
	}
	static void showrank(){
		finishboard.setVisible(true);
		int temp[] = new int[5];
		int tempn;
		for(int i=1 ; i<5 ; i++){
			rank[i]=1;
			for(int j=1 ; j<5 ; j++){
				if(playermoney[i]<playermoney[j]){
					rank[i]++;
				}
			}
		}
		for(int i=1 ; i<5 ; i++) temp[i] = rank[i];

		for(int i=1 ; i<5 ; i++){
			for(int j=i+1 ; j<5 ; j++){
				if(temp[j]<temp[i]){
					tempn = temp[i];
					temp[i] = temp[j];
					temp[j] = tempn;
				}
			}
		}
		for(int k=1 ; k<5 ; k++){
			ranknum[k].setIcon(new ImageIcon(mainGUI.class.getResource("/images/" + temp[k] + ".png")));
			ranknum[k].setVisible(true);
		}
		for(int k=1 ; k<5 ;k++){
			for(int l=k+1 ; l<5 ; l++){
				if(rank[k] == rank[l]){
					if(l+1<5)if(rank[k] == rank[l+1]) rank[l+1]+=2;
					if(l+2<5)if(rank[k] == rank[l+2]) rank[l+2]+=3;
					rank[l]++;
				}
			}
		}
		for(int i=0 ; i<4 ; i++){
			pchar[i].setBounds(350+100*rank[i+1], -90+100*rank[i+1], 150, 150);
			finishmoney[rank[i+1]].setText(playermoney[i+1]+"$");
			finishmoney[rank[i+1]].setVisible(true);
		}
		p1n.setVisible(false);
		p2n.setVisible(false);
		p3n.setVisible(false);
		p4n.setVisible(false);
	}
}

