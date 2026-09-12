import com.gudf.ui.fightGame;
import com.gudf.ui.login;

public class app {
    public static void main(String[] args) {
//        login l = new login();
//        l.Start();
        fightGame game = new fightGame();
        game.gameStart("zhangsan");
    }
}
