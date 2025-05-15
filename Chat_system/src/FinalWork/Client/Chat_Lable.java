package FinalWork.Client;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Chat_Lable extends JFrame {
    private JTextArea textArea;

    private JFrame frame;
    public Chat_Lable(){
        initJFrame();
        initChat();
    }

    private void initChat() {

    }

//    public void initJFrame(){
//        frame = new JFrame();
//        frame.setSize(720,500);
//
//
//
//        frame.setTitle("聊天界面");
//        frame.setAlwaysOnTop(true);//开发阶段，将其一直置顶，后期可取消
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(3);//设置，点×就能结束运行
//
//        //初始化菜单
//        JMenuBar jMenuBar = new JMenuBar();
//
//        //直接显示的第一级菜单
//        JMenu functionJMenu = new JMenu("功能");
//
//
//        //第一级菜单下点出来的第二级
//        JMenuItem close = new JMenuItem("关闭对话");
//
//        //第二级关联到第一级
//        functionJMenu.add(close);
//
//        //第一级菜单关联到菜单对象中
//        jMenuBar.add(functionJMenu);
//
//        //给整个界面设置菜单
//        frame.setJMenuBar(jMenuBar);
//
//        //取消窗口默认布局
//        frame.setLayout(null);
//
//        textArea = new JTextArea();
//        textArea.setEditable(false); // 设置为只读
//        JScrollPane scrollPane = new JScrollPane(textArea); // 添加滚动条
//
//        // 将JTextArea添加到JFrame窗口
//        frame.add(scrollPane);
//
//        // 重定向控制台输出到JTextArea
//        redirectSystemOut();
//
//
//
//
//
//        frame.setVisible(true);//展示放最后
//    }
public void initJFrame(){
        System.setProperty("file.encoding","UTF-8");
    frame = new JFrame("聊天界面");
    frame.setSize(720,500);
    frame.setAlwaysOnTop(true);
    frame.setDefaultCloseOperation(3);

    //初始化菜单
        JMenuBar jMenuBar = new JMenuBar();

        //直接显示的第一级菜单
        JMenu functionJMenu = new JMenu("功能");


        //第一级菜单下点出来的第二级
        JMenuItem close = new JMenuItem("关闭对话");

        //第二级关联到第一级
        functionJMenu.add(close);

        //第一级菜单关联到菜单对象中
        jMenuBar.add(functionJMenu);

        //给整个界面设置菜单
        frame.setJMenuBar(jMenuBar);

    // 创建JTextArea用于显示文字
    textArea = new JTextArea();
    textArea.setEditable(false); // 设置为只读
    textArea.setFont(new Font("UTF-8",Font.PLAIN,12));
    JScrollPane scrollPane = new JScrollPane(textArea); // 添加滚动条

    // 将JTextArea添加到JFrame窗口
    frame.add(scrollPane);

    // 重定向控制台输出到JTextArea
    redirectSystemOut();

    // 显示JFrame窗口
    frame.setVisible(true);

}
    private void redirectSystemOut() {
        OutputStream out = new ByteArrayOutputStream() {
            @Override
            public void flush() {

                String text = this.toString();

                    textArea.append(text);
                    textArea.setCaretPosition(textArea.getDocument().getLength());
                    reset();

            }
        };
        System.setOut(new PrintStream(out,true, StandardCharsets.UTF_8));
    }

}
