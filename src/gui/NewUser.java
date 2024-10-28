package gui;


import java.sql.Date;
import java.util.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.userDao;
import model.user;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;

public class NewUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textname;
	private JLabel lblNewLabel;
	private JTextField textmail;
	private JTextField textpass;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnAdd;
	userDao dao = new userDao();
	List<user> list = new ArrayList<user>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewUser frame = new NewUser();
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
	public NewUser() {
		setTitle("Register");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textname = new JTextField();
		textname.setBounds(187, 10, 208, 19);
		contentPane.add(textname);
		textname.setColumns(10);

		lblNewLabel = new JLabel("User Name");
		lblNewLabel.setBounds(56, 13, 71, 13);
		contentPane.add(lblNewLabel);

		textmail = new JTextField();
		textmail.setBounds(187, 39, 208, 19);
		contentPane.add(textmail);
		textmail.setColumns(10);

		textpass = new JTextField();
		textpass.setBounds(187, 68, 208, 19);
		contentPane.add(textpass);
		textpass.setColumns(10);

		lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(56, 42, 71, 13);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Pass word");
		lblNewLabel_2.setBounds(56, 68, 61, 13);
		contentPane.add(lblNewLabel_2);

		btnAdd = new JButton("Add");
	
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textname.getText();
				String mail = textmail.getText();
				String pass = textpass.getText();
				java.util.Date currentDate = new java.util.Date();
				java.sql.Date create = new java.sql.Date(currentDate.getTime());
				Date last_login = create;
				String hashedPassword = hashPassword(pass);
				user u = new user(name, mail, hashedPassword, create, last_login);
				if (kttrung(u)==true) {
					if (dao.save(u)) {
						list.add(u);
						JOptionPane.showMessageDialog(null, "Them thanh cong");
						Login_Sign fm = new Login_Sign();
						setVisible(false);
						fm.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Them that bai");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "email da ton tai");
				}
			}

		});
		btnAdd.setBounds(205, 136, 85, 21);
		contentPane.add(btnAdd);
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
	public boolean kttrung(user u) {
		boolean kq=true;
		for (user user : list) {
			if (u.getEmail().equals(user.getEmail())) {
				kq = false;
				break;
			}
		}
		return kq;
	}

}
