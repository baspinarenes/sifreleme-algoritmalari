package proje;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;


public class GUI{
	
	private static JTextArea sifreOlusturulacakMetin;
	private static JTextArea sifrelenenMetin;
	private static JTextArea sifreKirilacakMetin;
	private static JTextArea sifresiKirilanMetin;
	private static JTextField parametre;
	private static JTextField sifrelemeYontemi;
	private static JLabel resim;
		
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(1440, 700);
		frame.setTitle("Şifreleme Algoritmaları");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		frame.add(panel);
		
		//--------------------------------------------------------------------------------------------------------------
		
		JPanel hakkımdaPaneli = new JPanel();
		hakkımdaPaneli.setLayout(new FlowLayout());
		hakkımdaPaneli.setBounds(20, 20, 1400, 170);
		hakkımdaPaneli.setBorder(
	            BorderFactory.createTitledBorder(
	                    BorderFactory.createEtchedBorder(
	                            EtchedBorder.RAISED, Color.GRAY
	                            , Color.DARK_GRAY), " Hakkında "));
		panel.add(hakkımdaPaneli);
		
		
		resim = new JLabel();
		resim.setIcon(new ImageIcon(new ImageIcon("src/resim.png").getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT)));
		hakkımdaPaneli.add(resim);
		
		JLabel bilgiler = new JLabel("<html>Enes Başpınar<br/>Bilgisayar Mühendisliği<br/>170201003</html>", SwingConstants.CENTER);
		bilgiler.setBorder(new EmptyBorder(0, 30, 0, 0));
		hakkımdaPaneli.add(bilgiler);
		
		JLabel programBilgileri = new JLabel("<html>- Sezar algoritması Türkçe karakterler dışında tüm karakterleri desteklemektedir. Parametresi sayıdır.<br/>- Vigenere algoritması yalnızca harf ve boşluk karakterini desteklemektedir. Parametresi anahtar kelimedir.<br>- AES algoritması Türkçe karakterler dahil tüm karakterleri desteklemektedir. Parametresi anahtar kelimedir.</html>", SwingConstants.CENTER);
		programBilgileri.setBorder(new EmptyBorder(0, 60, 0, 0));
		hakkımdaPaneli.add(programBilgileri);
		
		//--------------------------------------------------------------------------------------------------------------
		
		JPanel sifreOlusturmaPaneli = new JPanel();
		sifreOlusturmaPaneli.setLayout(null);
		sifreOlusturmaPaneli.setBounds(20,  200, 690, 450);
		sifreOlusturmaPaneli.setBorder(
	            BorderFactory.createTitledBorder(
	                    BorderFactory.createEtchedBorder(
	                            EtchedBorder.RAISED, Color.GRAY
	                            , Color.DARK_GRAY), " Şifre Oluşturma "));
		panel.add(sifreOlusturmaPaneli);
		
		sifreOlusturulacakMetin = new JTextArea();
		sifreOlusturulacakMetin.setBounds(30, 30, 630, 150);
		sifreOlusturmaPaneli.add(sifreOlusturulacakMetin);
		
		JLabel sifrelemeYontemiLabel = new JLabel("Yöntem:", SwingConstants.CENTER);
		sifrelemeYontemiLabel.setBounds(65, 188, 100, 10);
		sifrelemeYontemiLabel.setBorder(new EmptyBorder(0, 30, 0, 0));
		sifreOlusturmaPaneli.add(sifrelemeYontemiLabel);
		
		sifrelemeYontemi = new JTextField();
		sifrelemeYontemi.setBounds(100, 200, 140, 50);
		sifrelemeYontemi.setHorizontalAlignment(SwingConstants.CENTER);
		sifreOlusturmaPaneli.add(sifrelemeYontemi);
		
		JLabel parametreLabel = new JLabel("Parametre:", SwingConstants.CENTER);
		parametreLabel.setBounds(215, 188, 120, 10);
		parametreLabel.setBorder(new EmptyBorder(0, 30, 0, 0));
		sifreOlusturmaPaneli.add(parametreLabel);
		
		parametre = new JTextField();
		parametre.setBounds(250, 200, 140, 50);
		parametre.setHorizontalAlignment(SwingConstants.CENTER);
		sifreOlusturmaPaneli.add(parametre);

		
		JButton sifreOlusturButonu = new JButton("Şifrele");
		sifreOlusturButonu.setBounds(400, 200, 200, 50);
		sifreOlusturButonu.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				String metin = sifreOlusturulacakMetin.getText();
				String parametreAl = parametre.getText();
				String yontem = sifrelemeYontemi.getText();
				
				String yeniMetin="";
				
				if (yontem.toLowerCase().equals("sezar")) {
					yeniMetin = SezarAlgoritmasi("sifrele", metin, Integer.parseInt(parametreAl));
					sifrelenenMetin.setText(yeniMetin + "(" + yontem + "," +  Integer.parseInt(parametreAl) + ")");
				}
				
				if (yontem.toLowerCase().equals("vigenere")) {
					yeniMetin = VigenereAlgoritmasi("sifrele", metin.toUpperCase(), parametreAl.toUpperCase());
					sifrelenenMetin.setText(yeniMetin + "(" + yontem + "," + parametreAl.toUpperCase() + ")");
				}
				
				if (yontem.toLowerCase().equals("aes")) {
					yeniMetin = AesAlgoritmasi("sifrele", metin, parametreAl);
					sifrelenenMetin.setText(yeniMetin + "(" + yontem + "," + parametreAl + ")");
				}
				
				
				
			}
	    });
		sifreOlusturmaPaneli.add(sifreOlusturButonu);
				
		sifrelenenMetin = new JTextArea();
		sifrelenenMetin.setBounds(30, 270, 630, 150);
		sifreOlusturmaPaneli.add(sifrelenenMetin);
		
		//--------------------------------------------------------------------------------------------------------------
		
		JPanel sifreKirmaPaneli = new JPanel();
		sifreKirmaPaneli.setLayout(null);
		sifreKirmaPaneli.setBounds(730, 200, 690, 450);
		sifreKirmaPaneli.setBorder(
	            BorderFactory.createTitledBorder(
	                    BorderFactory.createEtchedBorder(
	                            EtchedBorder.RAISED, Color.GRAY
	                            , Color.DARK_GRAY), " Şifre Kırma "));
		panel.add(sifreKirmaPaneli);
		
		sifreKirilacakMetin = new JTextArea();
		sifreKirilacakMetin.setBounds(30, 30, 630, 150);
		sifreKirmaPaneli.add(sifreKirilacakMetin);
				
		
		JButton sifreKirButon = new JButton("Şifre Kır");
		sifreKirButon.setBounds(240, 200, 200, 50);
		sifreKirButon.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				String sifresiKirilacakMetin = sifreKirilacakMetin.getText();
				String metin = sifresiKirilacakMetin.substring(0,sifresiKirilacakMetin.indexOf("("));
				String parametreler = sifresiKirilacakMetin.substring(sifresiKirilacakMetin.indexOf("(")+1,sifresiKirilacakMetin.indexOf(")"));
				String yontem = parametreler.split(",")[0];
				
				
				if (yontem.toLowerCase().equals("sezar")) {
					int oteleme = Integer.parseInt(parametreler.split(",")[1]);
					String yeniMetin = SezarAlgoritmasi("sifre kir", metin, oteleme);
					sifresiKirilanMetin.setText(yeniMetin);
				}
				if (yontem.toLowerCase().equals("vigenere")) {
					String anahtar = parametreler.split(",")[1];
					String yeniMetin = VigenereAlgoritmasi("sifre kir", metin, anahtar);
					sifresiKirilanMetin.setText(yeniMetin);
				}
				if (yontem.toLowerCase().equals("aes")) {
					String anahtar = parametreler.split(",")[1];
					String yeniMetin="";
					yeniMetin = AesAlgoritmasi("sifre kir", metin, anahtar);
					sifresiKirilanMetin.setText(yeniMetin);
				}			
			}
	    });
		sifreKirmaPaneli.add(sifreKirButon);
				
		sifresiKirilanMetin = new JTextArea();
		sifresiKirilanMetin.setBounds(30, 270, 630, 150);
		sifreKirmaPaneli.add(sifresiKirilanMetin);
		
		frame.setVisible(true);
	}
	
	static String SezarAlgoritmasi(String mod, String metin, int oteleme) {
		if(mod.equals("sifre kir")){
			oteleme = -oteleme;
		}
		String yeniMetin = "";
		for(int index=0; index < metin.length(); index++) {
			char karakter = metin.charAt(index);
			if(Character.isLetter(karakter)){
				int ascii = (int) karakter;
				ascii += oteleme;
				
				if(Character.isUpperCase(karakter)) {
					if(ascii > (int)'Z') {
						ascii -= 26;
					}
					else if(ascii < (int)'A') {
						ascii += 26;
					}
				}
				else if (Character.isLowerCase(karakter)) {
					if(ascii > (int)'z') {
						ascii -= 26;
					}
					else if(ascii < (int)'a') {
						ascii += 26;
					}
				}
				yeniMetin += (char)ascii;
			}
			else {
				yeniMetin +=  karakter;
			}
		}
		return yeniMetin;
	}
	
	static String VigenereAlgoritmasi(String mod, String metin, String anahtar) {
		
		int x = metin.length(); 

		for (int i = 0; ; i++) 
		{ 
			if (x == i) 
				i = 0; 
			if (anahtar.length() == metin.length()) 
				break; 
			anahtar+=(anahtar.charAt(i)); 
		} 
		
		if (mod.equals("sifrele")) {
			
			String cipher_text=""; 

			for (int i = 0; i < metin.length(); i++) 
			{ 
				if(metin.charAt(i) == ' ') {
					cipher_text+=(char)' ';
				}
				else {
					int x1 = (metin.charAt(i) + anahtar.charAt(i)) %26; 

					x1 += 'A'; 

					cipher_text+=(char)(x1); 
				}
				
			} 

			return cipher_text; 
			
		} else {
			String orig_text=""; 

			for (int i = 0 ; i < metin.length() && i < anahtar.length(); i++) 
			{ 
				if(metin.charAt(i) == ' ') {
					orig_text+=(char)' ';
				}
				else {
					int x1 = (metin.charAt(i) - anahtar.charAt(i) + 26) %26; 

					x1 += 'A'; 
					orig_text+=(char)(x1);
				}
				 
			} 
			return orig_text; 
		}
		

	}

	static String AesAlgoritmasi(String mod, String metin, String anahtar){
		SecretKeySpec secretKey = null;
		MessageDigest sha = null;
        try {
        	byte[] key = anahtar.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); 
            secretKey = new SecretKeySpec(key, "AES");
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } 
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        if(mod.equals("sifrele")) {
        	try
            {
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                return Base64.getEncoder().encodeToString(cipher.doFinal(metin.getBytes("UTF-8")));
            } 
            catch (Exception e) 
            {
                System.out.println("Error while encrypting: " + e.toString());
            }
            return null;
        }
        else {
        	try
            {
        		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
                return new String(cipher.doFinal(Base64.getDecoder().decode(metin)));
            } 
            catch (Exception e) 
            {
                System.out.println("Error while decrypting: " + e.toString());
            }
            return null;
		}
	}
}
