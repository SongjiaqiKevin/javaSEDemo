package entity.chat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;


public class GUIClient {
    public static void main(String[] args) {
        // 创建窗体
        JFrame frame = new JFrame("客户端");
        frame.setLayout(null);
        // 设置大小和位置
        frame.setSize(400, 300);
        frame.setLocation(100, 200);

        JButton button = new JButton("发送消息");
        button.setBounds(290, 220, 80, 30);
        frame.add(button);

        JTextField textField = new JTextField();
        textField.setBounds(10, 220, 260, 30);
        frame.add(textField);

        JTextArea ta = new JTextArea();
        ta.setBounds(10, 10, 360, 200);
        frame.add(ta);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        try {
            Socket socket = new Socket("127.0.0.1", 8888);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 获取输入文本
                    String text = textField.getText();
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
                    String now = sdf.format(date);
                    ta.append(now +"\r\n我：" + text + "\r\n");
                    // 设置输入框为空
                    textField.setText("");
                    // 发送信息
                    try {
                        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                        dos.writeUTF(text);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });

            // 接收信息线程
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            // 获取其他用户的输入
                            DataInputStream dis = new DataInputStream(socket.getInputStream());
                            String text = dis.readUTF();
                            String ip = socket.getInetAddress().getHostAddress();
                            Date date = new Date();
                            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
                            String now = sdf.format(date);
                            // 添加到页面上
                            ta.append(now + "\r\n" + ip + "：" + text + "\r\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
