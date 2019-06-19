package kodi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Shfrytezuesi extends Application {
	
	 private static String remoteServerAddr = "127.0.0.1";
     private static int remoteServerPort = 5755;
  
     
     
     //layout//
    
     TextField txtEmriPerdoruesit;
     PasswordField txtFjalekalimi;
     Button btnLogin;
     
     TextField txtEmri;
     TextField txtMbiemri;
     TextField txtMosha;
     TextField txtEmriPerdoruesitR;
    PasswordField txtFjalekalimiR;
     TextField txtPozita;
     TextField txtPaga;
     Button btnRegister;
     //layout//

     public static void main(String[] args) throws Exception {

             launch(args);
     }
     
     @Override 
     public void start(Stage primaryStage) throws IOException {
    	 ////////////////
    	 VBox leftside = new VBox();
    	 VBox rightside =  new VBox();
    	 HBox mainpane = new HBox ();
    	 
    	//loginpane// 
    	 GridPane loginpane = new GridPane ();
    	 loginpane.setVgap(15);    	 
    	 ColumnConstraints col1 = new ColumnConstraints();
         col1.setPercentWidth(40);
         ColumnConstraints col2 = new ColumnConstraints();
         col2.setPercentWidth(60);
         loginpane.getColumnConstraints().addAll(col1, col2);
             
   	  Label lblEmriP = new Label("Emri i perdoruesit:");
      loginpane.add(lblEmriP, 0, 0);
      txtEmriPerdoruesit = new TextField();
      loginpane.add(txtEmriPerdoruesit, 1, 0);
      Label lblPw = new Label ("Fjalekalimi:");
      loginpane.add(lblPw, 0, 1);
      txtFjalekalimi = new PasswordField ();
      loginpane.add(txtFjalekalimi, 1, 1);
      btnLogin = new Button("KYCU");
      loginpane.add(btnLogin, 1, 2);
      
      Label logimi = new Label("                      KYCU");
      leftside.getChildren().addAll(logimi,loginpane);
      leftside.setMargin(logimi, new Insets(0,0,25,0));
      //loginpane//
      
      //registerpane
      GridPane registerpane = new GridPane ();
      registerpane.setVgap(15);   
      Label lblEmri = new Label("Emri: ");
      registerpane.add(lblEmri, 0, 0);
      txtEmri = new TextField ();
      registerpane.add(txtEmri, 1, 0);
      Label lblMbiemri =  new Label("Mbiemri: ");
      registerpane.add(lblMbiemri, 0, 1);
      txtMbiemri = new TextField ();
      registerpane.add(txtMbiemri, 1, 1);
      Label lblMosha =  new Label ("Mosha: ");
      registerpane.add(lblMosha, 0, 2);
      txtMosha = new TextField();
      registerpane.add(txtMosha, 1, 2);
      Label lblEmriPR =  new Label ("Emri i perdoruesit: ");
      registerpane.add(lblEmriPR, 0, 3);
      txtEmriPerdoruesitR = new TextField();
      registerpane.add(txtEmriPerdoruesitR, 1, 3);
      Label lblPwR = new Label("Fjalekalimi: ");
      registerpane.add(lblPwR, 0, 4);
      txtFjalekalimiR = new PasswordField();
      registerpane.add(txtFjalekalimiR,1, 4);
      Label lblPozita = new Label("Pozita: ");
      registerpane.add(lblPozita, 0, 5);
      txtPozita =  new TextField();
      registerpane.add(txtPozita,1 , 5);
      Label lblPaga = new Label("Paga: ");
      registerpane.add(lblPaga, 0, 6);
      txtPaga = new TextField ();
      registerpane.add(txtPaga, 1, 6);
      btnRegister = new Button ("REGJISTROHU");
      registerpane.add(btnRegister,1 , 7);
      Label regjistrimi = new Label ("                         REGJISTROHU");
      rightside.getChildren().addAll(regjistrimi,registerpane);
      rightside.setMargin(regjistrimi, new Insets(0,0,25,0));  
      //registerpane
      
      mainpane.setPadding(new Insets(25));
      mainpane.setMargin(leftside, new Insets(25));
      mainpane.setMargin(rightside, new Insets(25));
      mainpane.getChildren().addAll(leftside,rightside);
      Scene scene = new Scene(mainpane,800,500);
    	 
      primaryStage.setScene(scene);
      primaryStage.setResizable(false);
      primaryStage.show();
      
      
      
      btnRegister.setOnAction(e->{
		try {
			Regjistrohu();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	});
      
      btnLogin.setOnAction(e->{
		try {
			Kycu();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	});
 
    	 
     }
     
     public void Regjistrohu() throws IOException {
    	 
    	 
    	 String emri =txtEmri.getText();
    	 String mbiemri = txtMbiemri.getText();
    	 int mosha = Integer.parseInt(txtMosha.getText());
    	 String emriPerdoruesitR =txtEmriPerdoruesitR.getText();
    	 String saltedHashFjalekalimi = BCrypt.hashpw(txtFjalekalimiR.getText(), BCrypt.gensalt());
    	// String fjalekalimiplain = txtFjalekalimiR.getText();
    	 String pozita =txtPozita.getText();
    	 String paga = txtPaga.getText();
    	 
    	 InetAddress remoteServerInetAddr = InetAddress.getByName(remoteServerAddr);
         Socket localSocket = new Socket(remoteServerInetAddr, remoteServerPort);
         OutputStream outToServer = localSocket.getOutputStream(); 
         DataOutputStream out = new DataOutputStream(outToServer);
         InputStream inFromServer = localSocket.getInputStream();
         DataInputStream in = new DataInputStream(inFromServer);
         out.writeUTF("1");
         
         
         out.writeUTF(emri); // ja dergoj serverit
    	 out.writeUTF(mbiemri); //ja dergoj serverit
         out.writeInt(mosha);
         out.writeUTF(emriPerdoruesitR);
         out.writeUTF(saltedHashFjalekalimi);
         out.writeUTF(pozita);
         out.writeUTF(paga);
    	 String registrationServerAlert = in.readUTF();
    	 System.out.println(registrationServerAlert);
         
         localSocket.close();
    	
    	 
    	 
     }
     
     public void Kycu() throws IOException {
    	 
    	 InetAddress remoteServerInetAddr = InetAddress.getByName(remoteServerAddr);
         Socket localSocket = new Socket(remoteServerInetAddr, remoteServerPort);
         OutputStream outToServer = localSocket.getOutputStream(); 
         DataOutputStream out = new DataOutputStream(outToServer);
         InputStream inFromServer = localSocket.getInputStream();
         DataInputStream in = new DataInputStream(inFromServer);
         
         
         String emriPerdoruesit = txtEmriPerdoruesit.getText();
         String fjalekalimi = txtFjalekalimi.getText();
        // System.out.print(emriPerdoruesit+" "+fjalekalimi);
         out.writeUTF("2");
         
         
         out.writeUTF(emriPerdoruesit);
         out.writeUTF(fjalekalimi);
         
         Alert alert = new Alert(AlertType.INFORMATION);
     	alert.setContentText("Ju u kycet me sukses!");
     	alert.show();
         
         localSocket.close();
     }

}
