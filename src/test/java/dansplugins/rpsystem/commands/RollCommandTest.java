package dansplugins.rpsystem.commands;

import org.bukkit.entity.Player;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static dansplugins.rpsystem.commands.NewRollCommand.*;

public class RollCommandTest {

    @Mock
    Player mockPlayer = mock(Player.class);

    @Test
    public void itShouldRequirePermissions() {
        when(mockPlayer.hasPermission(anyString())).thenReturn(false);

        rollDice(mockPlayer, new String[]{"1d6"});

        verify(mockPlayer).sendMessage(noPermMsg);
    }

    @Test
    public void itShouldRoll1d6() {
        when(mockPlayer.hasPermission(anyString())).thenReturn(true);

        rollDice(mockPlayer, new String[]{"1d6"});

        verify(mockPlayer).sendMessage(anyString());
        verify(mockPlayer, never()).sendMessage(noPermMsg);
    }

    @Test
    public void itShouldRoll1d20Plus5() {
        when(mockPlayer.hasPermission(anyString())).thenReturn(true);

        rollDice(mockPlayer, new String[]{"1d20+5"});

        verify(mockPlayer).sendMessage(anyString());
        verify(mockPlayer, never()).sendMessage(noPermMsg);
    }

    @Test
    public void itShouldRespondWithImproperSyntax() {
        when(mockPlayer.hasPermission(anyString())).thenReturn(true);

        rollDice(mockPlayer, new String[]{"1d20 + 5"});

        verify(mockPlayer).sendMessage(invalidSyntaxMsg);
    }

}