import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ConnectToServer extends Thread {
	static Scanner receiver;
	static PrintWriter sender;
	
	Socket socket;
	ConnectToServer(String serverIP, int port){
		try {

			socket = new Socket( serverIP, port );

			receiver = new Scanner(
					new InputStreamReader(
							socket.getInputStream(), "utf-8" ) );

			sender = new PrintWriter(
					new OutputStreamWriter(
							socket.getOutputStream(), "utf-8" ) );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

		
		
			@Override
			public void run() {
				while( true ) {
					try {
						String line = receiver.nextLine();
						if( line == null ) {
							throw new Exception();
						}
						mainGUI.textArea.setCaretPosition(mainGUI.textArea.getDocument().getLength()); 
						mainGUI.textArea.append(line +"\n");
						mainGUI.parsing(line);
						
						
					}
					catch( Exception ex ) {
						mainGUI.textArea.append( "Receive 쓰레드종료됨 \n" );
						//receiver.close();
						return;
					}
				}
			}
		
	}


