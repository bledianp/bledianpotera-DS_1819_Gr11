package kodi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;




public class Serveri  extends Thread{
	private static int remoteServerPort = 5755;
    private ServerSocket localServerSocket = null;
   
  

    public Serveri() {
            try {
                    localServerSocket = new ServerSocket(remoteServerPort);
            } catch (Exception e) {
                    e.printStackTrace();
            }
    }

    public void run() {
            while(true) {
                    try {
                            System.out.println("Ne pritje per klient ne portin  " + localServerSocket.getLocalPort() + "...");
                            Socket server = localServerSocket.accept();
                            System.out.println("nje klient u lidh  " + server.getRemoteSocketAddress());
                            DataInputStream in = new DataInputStream(server.getInputStream()); //me pranu tdhana
                            DataOutputStream out = new DataOutputStream(server.getOutputStream()); // me dergu te dhana
                            
                            DbConnection dbconnection = new DbConnection();
                    	    Connection connection  = dbconnection.getConnection();
                    	    String kerkesa =in.readUTF();
                    	    
                    	    if(kerkesa.equals("1")){
                    	    	
                    	    	String emri = in.readUTF();
                                String mbiemri = in.readUTF();
                                int mosha = in.readInt();
                                String emriPerdoruesitR = in.readUTF();
                                String fjalekalimiR = in.readUTF();
                                System.out.print("Fjalekalimi: "+fjalekalimiR);
                              //  String fjalekalimisecured = BCrypt.hashpw(fjalekalimiR, BCrypt.gensalt(12));
                                String pozita = in.readUTF();
                                String paga = in .readUTF();
                                
                               
                                
                              //  System.out.print(emri+"\n"+mbiemri+"\n"+mosha+"\n"+emriPerdoruesitR+"\n"+fjalekalimiR+"\n"+pozita+"\n"+paga);
                              boolean flag =false;
                              String insertData = "INSERT INTO registrationdata(emri,mbiemri,mosha,emriPerdoruesit,fjalekalimi,pozita,paga) VALUES(?,?,?,?,?,?,?)";
                              PreparedStatement ps = connection.prepareStatement(insertData);
                              ps.setString(1, emri);
                              ps.setString(2, mbiemri);
                              ps.setInt(3, mosha);
                              ps.setString(4, emriPerdoruesitR);
                              ps.setString(5, fjalekalimiR);
                              ps.setString(6, pozita);
                              ps.setString(7,paga);
                              ps.executeUpdate();
                              
                              String  insertTologin ="INSERT INTO logindata(username,saltedhashpw) VALUES(?,?)";
                              PreparedStatement ps2 =connection.prepareStatement(insertTologin);
                              ps2.setString(1, emriPerdoruesitR);
                              ps2.setString(2, fjalekalimiR);
                              ps2.executeUpdate();
                              
                              flag=true;
                              
                              if (flag=true) {
                            	  out.writeUTF("Punetori u regjistrua me sukses!");
                              }
                              else {
                            	  out.writeUTF("Deshtoi regjistrimi!");
                              }
                            		  
                            
                              
                              //System.out.print(in.readUTF()); 
                               server.close();
                    	    }
                    	    else if (kerkesa.equals("2")) {
                    	    	
                    	    	String emriPerdoruesit =in.readUTF();
                    	    	String fjalekalimi =in.readUTF();
                    	    	
                    	    
                    	    	
                    	    	
                    	    	String verifyusername = "SELECT * FROM logindata WHERE username = ?";
                    			PreparedStatement ps_userverifier = connection.prepareStatement(verifyusername);
                    			ps_userverifier.setString(1, emriPerdoruesit);
                    			ResultSet rs = ps_userverifier.executeQuery();
                    			
                    			
                    			if(!rs.next()) {
                    				System.out.println("Nuk ka punetor me ate emer perdoruesi");
                    				
                    			}
                    			else { 
                    				
                    				
                    			
                    				String verifypassword ="SELECT saltedhashpw FROM logindata WHERE username = ? ";
                    				PreparedStatement st =connection.prepareStatement(verifypassword);
                    				st.setString(1, emriPerdoruesit);
                    				
                    				
                    				ResultSet pwColumnResults =st.executeQuery();
                    				
                    				while(pwColumnResults.next()) {
                    					
                    					String pwHash = pwColumnResults.getString("saltedhashpw");
                    					
                    					boolean checkPassword =BCrypt.checkpw(fjalekalimi,pwHash);
                    					
                    					
                    					if (!checkPassword) {
                    						System.out.println("Fjalekalim i gabuar nga ana e shfrytezuesit");
                    					}
                    					else 
                    					    
                    						System.out.print("U kyc me sukses shfrytezuesi");
                    					
                    						
                    	    }             
                    				
                    			

                    			}}} catch (SocketTimeoutException s) {
                            System.out.println("Socket timed out!");
                            break;
                    } catch (IOException e) {
                            e.printStackTrace();
                            break;
                    } catch (SQLException e) {
                    	
                    	// TODO Auto-generated catch block
						e.printStackTrace();
					}
            }
    }

    public static void main(String[] args) throws Exception {
    	    KeyPairGenerator ciftiCelesaveGjen = KeyPairGenerator.getInstance("RSA");
    	    ciftiCelesaveGjen.initialize(2048);
    	    KeyPair cifti = ciftiCelesaveGjen.genKeyPair();
    	    Key celesiPublik = cifti.getPublic();
    	    Key celesiPrivat = cifti.getPrivate();
    	   
    	    
    	    Base64.Encoder encoder = Base64.getEncoder();
    	    Writer fw = new FileWriter("C:\\Users\\medi_\\Desktop\\serveri\\celesiPublik.txt");
    	    fw.write("-----BEGIN RSA PUBLIC KEY-----\n");
    	    fw.write(encoder.encodeToString(celesiPublik.getEncoded()));
    	    fw.write("\n-----END RSA PUBLIC KEY-----\n");
    	    fw.close();
    	  
    	    
    	    
    	    
            Serveri tcpReceiverServer = new Serveri();
            tcpReceiverServer.start();
    }

}
