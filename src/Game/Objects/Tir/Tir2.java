package Game.Objects.Tir;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.io.File;
import java.io.IOException;

public class Tir2 extends Tir {
    private transient static int increasetTeta=8;
    private transient static double delayPerShoot=0.3;

    public Tir2(double x, double y, double vx, double vy) {
        super(x, y, vx, vy);
        try {
            super.bufferedImage = ImageIO.read(new File("pngs/tir2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setNormalPower(2);
        super.setNormalPower(2);

        AffineTransform tx = new AffineTransform();
        tx.rotate(Math.atan2(vy, vx), bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);

        AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_BILINEAR);
        bufferedImage = op.filter(bufferedImage, null);
    }
    public static double getDelayPerShoot() {
        return delayPerShoot;
    }
    public static int getIncreasetTeta() {
        return increasetTeta;
    }
}
