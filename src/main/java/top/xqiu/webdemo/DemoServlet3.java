package top.xqiu.webdemo;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.imageio.ImageIO;

@WebServlet("/demo3")
public class DemoServlet3 extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("Demo init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        返回一个验证码
        int len = 4;
        int fontSize = 28;
        int width = len  * fontSize;
        int height = 40;
        String code = getCode();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics g = bufferedImage.getGraphics();
        g.setColor(Color.WHITE);
        //绘制填充矩形
        g.fillRect(0,0,width,height);
        //绘制干扰线
        for (int i = 0; i < 200; i++) {
            g.setColor(new Color(0, 0, 0));
            int x1 = new Random().nextInt(width);
            int y1 = new Random().nextInt(height);
            int x2 = new Random().nextInt(width);
            int y2 = new Random().nextInt(height);
            g.drawLine(x1, y1, x2, y2);

            //点
//        for (int i = 0; i < 200; i++) {
//            g.setColor(new Color(0,0,0));
//            int x = new Random().nextInt(width);
//            int y = new Random().nextInt(height);
//            g.drawOval(x,y,1,1);
//        }
        }
        g.setColor(Color.RED);
        g.setFont(new Font("宋体",Font.BOLD,fontSize));
        for (int j = 0; j < len; j++) {
            char c = code.charAt(j);
            g.drawString(c + "",j * fontSize + 5,fontSize);
//            输出验证码
//        }
        }
        ImageIO.write(bufferedImage,"jpg",resp.getOutputStream());
    }

    private String getCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int r = random.nextInt(10);
            sb.append(r);
        }
        return sb.toString();
    }



    public void destroy() {
    }
}