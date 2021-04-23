package web;

import java.awt.EventQueue;
import java.awt.Image;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AGV {

	private JFrame frame;
	static WebDriver driver;
	
	public static void Position() {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/nav[2]/div[4]")).click();
	}
	public static void GETCURRENTPOSTION() {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/span/button[5]")).click();
	}
	public static void LOCATIONS() {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/span/button[6]")).click();
	}
	public static void SAVE() {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/span/button[4]")).click();
	}
	public static void Navimode() {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/nav[1]/div[2]")).click();	
	} 
	public static void CANCEL() {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/button[4]")).click();	
	}
	public static void CHARGING() {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/button[5]")).click();
	}
	public static void launch() {
		System.setProperty("webdriver.chrome.driver","/Users/zzh/Desktop/Work/chromedriver" );
		driver = new ChromeDriver();
		driver.get("http://www.youtube.com");
		Position();
	}
	
	public static String[] getLocations() {
		LOCATIONS();
		String location = driver.findElement(By.xpath("/html/body/div[2]/div[3]/ul")).getText();
		location = format(location);
		return location.split(System.getProperty("line.separator"));
	}
	
	public static String format(String location) {
		location = location.replaceAll("[0-9]","").replace(".","").replace(",","").replace("-","");
		return location;
	}
	
	public static void getCurrentPostion(String name) throws InterruptedException {
		GETCURRENTPOSTION();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"standard-required\"]")).sendKeys(name);//fill
		driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/button")).click();//sure
		SAVE();
	}

	public static void charging() {
		Navimode();
		CHARGING();
		Position();
	}
	public static void goToCurrentPostion(int index) {
			String xPath = navigateCode(index);
			driver.findElement(By.xpath(xPath)).click();
			driver.findElement(By.xpath("/html/body/div[2]/div[1]")).click();//empty
		}
	
	public static String navigateCode(int index) {
		return "/html/body/div[2]/div[3]/ul/li["+(index+=1)+"]/div[2]/button[1]";
	}
	
	public static void deleteCurrentPostion(int index) {
		String xPath = deleteCode(index);
		driver.findElement(By.xpath(xPath)).click();
		driver.findElement(By.xpath("/html/body/div[2]/div[1]")).click();//empty
		SAVE();
	}
	public static String deleteCode(int index) {
		return "/html/body/div[2]/div[3]/ul/li["+(index+=1)+"]/div[2]/button[2]";
	}
	
	public static void cancelNavigating() {
		Navimode();
		CANCEL();
		Position();
	}
	
	public static String battery() {
		return driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/li[4]/p")).getText();
	}
	public static BufferedImage getImage() throws IOException, InterruptedException {
		Thread.sleep(10);
		WebElement link = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/canvas"));
		File s =link.getScreenshotAs(OutputType.FILE);
		BufferedImage image = ImageIO.read(s);
		return image;
	}
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		launch();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AGV window = new AGV();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public AGV() throws IOException, InterruptedException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	private void initialize() throws IOException, InterruptedException {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
//		JLabel map = new JLabel("New label");
//		map.setIcon(new ImageIcon(getImage()));
//		map.setBounds(6, 120, 588, 452);
//		frame.getContentPane().add(map);
		
		JButton btnNewButton = new JButton("Charging");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				charging();
			}
		});
		btnNewButton.setBounds(6, 16, 187, 29);
		frame.getContentPane().add(btnNewButton);
		
		
		JButton btnNewButton_1 = new JButton("getCurrentPostion");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name =JOptionPane.showInputDialog(null,"请输入当前地点的名称：\n","getCurrentPostion",JOptionPane.PLAIN_MESSAGE);
				try {
					getCurrentPostion(name);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				try {
//					map.setIcon(new ImageIcon(getImage()));
//				} catch (IOException | InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
		});
		btnNewButton_1.setBounds(208, 48, 187, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Quit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				driver.quit();
				System.exit(0);
			}
		});
		btnNewButton_2.setBounds(6, 48, 187, 29);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("goToCurrentLocation");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] locations =getLocations();
				ArrayList<String> locationList = new ArrayList<String>();
				for(int i =0;i<locations.length;i++) {
					if(!locations[i].equals("")) {
						locationList.add(locations[i]);
					}
				}
				String s = (String) JOptionPane.showInputDialog(null,"Current Location:\n", "goToCurrentLocation", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"),locationList.toArray(),null);
				goToCurrentPostion(locationList.indexOf(s));
			}
		});
		btnNewButton_3.setBounds(407, 48, 187, 29);
		frame.getContentPane().add(btnNewButton_3);
		JButton btnNewButton_4 = new JButton("deleteCurrentLocation");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] locations =getLocations();
				ArrayList<String> locationList = new ArrayList<String>();
				for(int i =0;i<locations.length;i++) {
					if(!locations[i].equals("")) {
						locationList.add(locations[i]);
					}
				}
				String s = (String) JOptionPane.showInputDialog(null, "Current Location:\n", "deleteCurrentLocation", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"),locationList.toArray(),"charging_pile");
				deleteCurrentPostion(locationList.indexOf(s));
//				try {
//					map.setIcon(new ImageIcon(getImage()));
//				} catch (IOException | InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
		});
		btnNewButton_4.setBounds(208, 16, 187, 29);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("cancelNagivating");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelNavigating();
			}
		});
		btnNewButton_5.setBounds(407, 16, 187, 29);
		frame.getContentPane().add(btnNewButton_5);
		
		JLabel battery = new JLabel("New label");
		battery.setText("Battery: "+battery());
		battery.setBounds(248, 92, 86, 16);
		frame.getContentPane().add(battery);
		
		
//		Timer tm = new Timer(5000,new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				try {
//					map.setIcon(new ImageIcon(getImage()));
//				} catch (IOException | InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			
//		});
//		tm.start();
		
	}
}
