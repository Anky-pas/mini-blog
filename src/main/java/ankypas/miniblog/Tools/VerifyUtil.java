package ankypas.miniblog.Tools;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VerifyUtil {
    //验证码字符集
    private static final char[] chars = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    //验证码字符个数
    private static int SIZE = 4;

    //干扰线数量
    private static int LINES = 4;

    //图片高度
    private static int HEIGHT = 40;

    //图片宽度
    private static int WIDTH = 100;

    //字体大小
    private static int FONT_SIZE = 30;

    //生成验证码图片
    /*
    返回值：
    Object[0]:验证码字符串
    Object[1]:验证码图片
     */
    public static Object[] createImage(){

        //创建空白图片
        BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);

        //获取图片画笔
        Graphics graphics = image.getGraphics();

        //设置画笔颜色,灰色
        graphics.setColor(Color.LIGHT_GRAY);

        //把背景涂成灰色
        graphics.fillRect(0,0,WIDTH,HEIGHT);

        //画随机字符
        Random random = new Random();

        //用于存储验证码字符串
        StringBuffer stringBuffer = new StringBuffer();

        for(int i=0;i<SIZE;i++){

            //从字符集中随机选择一个字符
            int index = random.nextInt(chars.length);

            /* 随机设置画笔颜色
               注：此处颜色可能与背景色相同，但发生概率很低，因此未做处理
               当字体颜色与背景色相同或相近时，需要用户通过点击图片换取下一张 */
            graphics.setColor(getRandomColor());

            //设置字体大小、样式
            graphics.setFont(new Font(null,Font.BOLD+Font.ITALIC, FONT_SIZE));

            //画字符
            graphics.drawString(chars[index]+"",i*WIDTH/SIZE,HEIGHT/2);

            //记录字符串
            stringBuffer.append(chars[index]);
        }

        //画干扰线
        for(int i=0;i<LINES;i++){

            //随机设置画笔颜色
            graphics.setColor(getRandomColor());

            //随机画线
            graphics.drawLine(random.nextInt(WIDTH),random.nextInt(HEIGHT),random.nextInt(WIDTH),random.nextInt(HEIGHT));
        }

        //返回验证码字符串和图片
        return new Object[]{stringBuffer.toString(),image};
    }
    //随机生成一种颜色
    private static Color getRandomColor(){
        Random random = new Random();
        Color color = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
        return color;
    }
}
