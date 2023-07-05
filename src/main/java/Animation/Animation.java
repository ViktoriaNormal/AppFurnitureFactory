package Animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Animation {

    private TranslateTransition tt;

    public Animation(Node node) {
        tt = new TranslateTransition(Duration.millis(70), node);
        tt.setFromX(0);
        tt.setByX(10);
        tt.setCycleCount(4);
        tt.setAutoReverse(true);
    }

    public void playAnimation(){
        tt.playFromStart();
    }
}
