package club.kid7;

import club.kid7.command.HelloWorldCommand;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

@Plugin(id = "myplugin", name = "My Plugin", version = "1.0")
public class MyPlugin {
    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartingServerEvent event) {
        CommandSpec myCommandSpec = CommandSpec.builder()
                .description(Text.of("Hello World Command"))
                .permission("myplugin.command.helloworld")
                .executor(new HelloWorldCommand())
                .build();

        Sponge.getCommandManager().register(this, myCommandSpec, "helloworld", "hello", "test");
        logger.info("Plugin loaded");
    }

    @Listener
    public void onClientConnectionJoin(ClientConnectionEvent.Join event) {
        Player player = event.getTargetEntity();
        Text text = Text.of("歡迎 ", TextColors.GREEN, player.getName(), TextColors.RESET, " 進入伺服器");
        player.sendMessage(text);
    }
}
