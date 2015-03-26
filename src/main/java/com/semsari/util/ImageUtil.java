package com.semsari.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by Iman on 3/23/2015.
 */
public class ImageUtil {

    public static byte[] resizeImage(byte[] source, int IMG_WIDTH, int IMG_HEIGHT){

        byte[] result =null;
        try {
            InputStream in = new ByteArrayInputStream(source);
            BufferedImage originalImage = ImageIO.read(in);
            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
            BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);

            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);



            g.dispose();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, "jpg", baos);
            baos.flush();
            result = baos.toByteArray();
            baos.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] resizeImageWithHint(byte[] source,  int IMG_WIDTH, int IMG_HEIGHT){

            byte[] result =null;

        try {
            InputStream in = new ByteArrayInputStream(source);
            BufferedImage originalImage = ImageIO.read(in);
            int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
            BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);

            // set font for the watermark text
            g.setFont(new Font("Arial", Font.TRUETYPE_FONT, 20));


//unicode characters for (c) is \u00a9
            String watermark = "kalatag.com";
// add the watermark text

            int x = 5;
            int y = IMG_HEIGHT - 15;

            FontMetrics fm = g.getFontMetrics();
            Rectangle2D rect = fm.getStringBounds(watermark, g);

            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(x -3,
                    y - fm.getAscent(),
                    (int) rect.getWidth() +3,
                    (int) rect.getHeight());

            g.setColor(Color.black);


            g.drawString(watermark, x, y );

            g.dispose();
            g.setComposite(AlphaComposite.Src);

            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, "jpg", baos);
            baos.flush();
            result = baos.toByteArray();
            baos.close();


        }catch (Exception e){

            e.printStackTrace();

        }
        return result;
    }
}
