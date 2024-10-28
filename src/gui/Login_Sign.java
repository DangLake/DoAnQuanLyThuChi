package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.userDao;
import model.user;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class Login_Sign extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textmail;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JPasswordField passw;
	private JButton btnSign;
	private JButton btnDKy;
	userDao dao=new userDao();
	private static int currentUserID;
	private static int currentCategories;
	
	
	public static int getCurrentUserID() {
		return currentUserID;
	}

	public static void setCurrentUserID(int UserID) {
		currentUserID = UserID;
	}

	public static int getCurrentCategories() {
		return currentCategories;
	}

	public static void setCurrentCategories(int Categories) {
		currentCategories = Categories;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_Sign frame = new Login_Sign();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login_Sign() {
		setTitle("Sign in");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textmail = new JTextField();
		textmail.setBounds(155, 29, 204, 19);
		contentPane.add(textmail);
		textmail.setColumns(10);
		
		lblNewLabel = new JLabel("Email");
		lblNewLabel.setBounds(46, 32, 61, 13);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("PassWord");
		lblNewLabel_1.setBounds(46, 81, 61, 13);
		contentPane.add(lblNewLabel_1);
		
		passw = new JPasswordField();
		passw.setBounds(155, 72, 204, 19);
		contentPane.add(passw);
		
		btnSign = new JButton("Sign in");
		btnSign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email=textmail.getText();
				String pass=new String(passw.getText());
				if(dao.findBymail(email)!=null) {
					String hashedPassword = hashPassword(pass);
					if(dao.findByPass(hashedPassword,email)!=null) {
						user u=dao.findBymail(email);
						java.util.Date currentDate = new java.util.Date();
						java.sql.Date create = new java.sql.Date(currentDate.getTime());
						u.setCreated_at(create);
						int userID=u.getUser_id();
						Home fm=new Home(userID);
						Home.setCurrentUserID(userID);
						if(dao.update(u)) {
							JOptionPane.showMessageDialog(null, "Dang nhap thanh cong");
							setVisible(false);
							fm.setVisible(true);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Sai password");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Email khong ton tai");
				}
				
			}
		});
		
		btnSign.setBounds(182, 125, 100, 21);
		contentPane.add(btnSign);
		
		btnDKy = new JButton("New User?");
		btnDKy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewUser fm=new NewUser();
				setVisible(false);
                fm.setVisible(true);
			}
		});
		btnDKy.setBounds(182, 166, 100, 21);
		contentPane.add(btnDKy);
	}
	private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = String.format("%02x", b);
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
