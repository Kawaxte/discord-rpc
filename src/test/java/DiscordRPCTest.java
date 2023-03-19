import com.github.kawaxte.drpc.DiscordEventHandlers;
import com.github.kawaxte.drpc.DiscordRPC;
import com.github.kawaxte.drpc.DiscordRichPresence;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DiscordRPCTest {

  public static void main(String... args) {
    Runtime.getRuntime().addShutdownHook(Executors.defaultThreadFactory().newThread(
        DiscordRPC::shutdown));

    initDiscord();

    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    service.scheduleAtFixedRate(DiscordRPC::runCallbacks, 2, 2, TimeUnit.SECONDS);
  }

  static void initDiscord() {
    DiscordEventHandlers handlers = new DiscordEventHandlers();
    handlers.ready = request -> {
      DiscordRichPresence presence = new DiscordRichPresence.Builder()
          .setState("DiscordRPC Test")
          .setDetails("Running the RPC test")
          .setStartTimestamp(System.currentTimeMillis() / 1000L)
          .build();
      DiscordRPC.updatePresence(presence);
    };
    DiscordRPC.initialise("0000000000000000000", handlers, true, null);
  }
}
