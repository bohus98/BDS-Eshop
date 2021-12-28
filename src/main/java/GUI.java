import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passswordText;
    private static JButton button;
    private static JLabel sucess;
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(300,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        userLabel = new JLabel("User");
        userLabel.setBounds(20,28,80,20);;
        panel.add(userLabel);
        userText = new JTextField();
        userText.setBounds(70,30,150,20);
        panel.add(userText);

        passwordLabel = new JLabel("Heslo");
        passwordLabel.setBounds(20,63,80,20);
        panel.add(passwordLabel);
        passswordText = new JPasswordField();
        passswordText.setBounds(70,65,150,20);
        panel.add(passswordText);

        button = new JButton("Login");
        button.setBounds(110,100,80,20);
        button.addActionListener(new GUI());
        panel.add(button);

        sucess = new JLabel("");
        sucess.setBounds(10,120,300,25);
        panel.add(sucess);
        //sucess.setText();

        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String password = passswordText.getText();
        System.out.println(user + ", " + password);
        if (user.equals("Matus") && password.equals("221536")){
            sucess.setText("Login sucessful!");
        }
        else {
            sucess.setText("Username or password is not valid.");
        }
    }
}
