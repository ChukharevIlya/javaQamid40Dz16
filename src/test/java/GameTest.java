import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
public class GameTest {

    ArrayList<Player> expected = new ArrayList<>();
    ArrayList<Player> actual = new ArrayList<>();
    Game game = new Game(actual);

    Player player1 = new Player(1, "player1", 300);
    Player player2 = new Player(2, "player2", 20);
    Player player3 = new Player(3, "player3", 300);



    //регистрирует игрока
    @Test
    public void shouldRegisterPlayer() {
        expected.add(player1);
        game.register(player1);

        Assertions.assertEquals(expected, actual);
    }

    // исключение, т.к первый незарегистрирован
    @Test
    public void shouldThrowNotRegisteredExceptionIfFirstPlayerIsNotRegistered() {
        game.register(player2);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("player1", "player2");
        });
    }

    // исключение, т.к второй незареган
    @Test
    public void shouldThrowNotRegisteredExceptionIfSecondPlayerIsNotRegistered() {
        game.register(player1);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("player1", "player2");
        });
    }

    // первый победил
    @Test
    public void shouldWinFirstPlayer() {
        game.register(player1);
        game.register(player2);

        int expected = 1;
        int actual = game.round("player1", "player2");

        Assertions.assertEquals(expected, actual);
    }

    // второй победил
    @Test
    public void shouldWinSecondPlayer() {
        game.register(player2);
        game.register(player3);

        int expected = 2;
        int actual = game.round("player2", "player3");

        Assertions.assertEquals(expected, actual);
    }

    // ничья
    @Test
    public void shouldBeADraw() {
        game.register(player1);
        game.register(player3);

        int expected = 0;
        int actual = game.round("player1", "player3");

        Assertions.assertEquals(expected, actual);
    }
}
