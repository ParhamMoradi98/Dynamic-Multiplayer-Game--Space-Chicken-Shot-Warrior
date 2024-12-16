package Game.Objects;

import java.awt.*;
import java.io.Serializable;

public interface Animatable extends Serializable {
     void move();
     void paint(Graphics2D graphics2D);
     double getX();
     double getY();


}
