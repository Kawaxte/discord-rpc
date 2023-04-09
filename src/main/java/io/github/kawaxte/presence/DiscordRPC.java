package io.github.kawaxte.presence;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 * The {@code DiscordRPC} class provides static methods to initialize, shutdown, update and
 * clear the presence of the application.
 * <p>
 * It also loads the native library for the current platform using the
 * {@link #loadLibraryForPlatform()} method.
 * </p>
 *
 * @author Kawaxte
 */
public final class DiscordRPC {

  static {
    loadLibraryForPlatform();
  }

  /**
   * Private constructor to prevent instantiation.
   */
  private DiscordRPC() {
    throw new UnsupportedOperationException(String.format("%s is not instantiable",
        DiscordRPC.class.getName()));
  }

  /**
   * Initialises the Rich Presence.
   *
   * @param applicationId   The application ID of the application to be initialised.
   * @param handlers        The event handlers to be registered with the Rich Presence.
   * @param autoRegister    Whether or not to automatically register the application with Discord.
   * @param optionalSteamId The Steam ID of the game to be initialised. This is optional and can be
   *                        {@code null}.
   * @throws NullPointerException if {@code applicationId} or {@code handlers} is {@code null}.
   * @see <a href="https://discordapp.com/developers/docs/rich-presence/how-to#initialization">Introducing Rich Presence - Initialization</a>
   */
  public static void initialise(final String applicationId, DiscordEventHandlers handlers,
      final boolean autoRegister, final String optionalSteamId) {
    Objects.requireNonNull(applicationId, "applicationId must not be null");
    Objects.requireNonNull(handlers, "handlers must not be null");

    IDiscordRPC.INSTANCE.Discord_Initialize(applicationId,
        handlers,
        autoRegister ? 1 : 0,
        optionalSteamId);
  }

  /**
   * Shuts down the Rich Presence.
   * <p>
   * This method should be called when the application is closed to ensure that the Discord Rich
   * Presence API is shut down properly.
   * </p>
   *
   * @see <a href="https://discord.com/developers/docs/rich-presence/how-to#shutting-down">Introducing Rich Presence - Shutting Down</a>
   */
  public static void shutdown() {
    IDiscordRPC.INSTANCE.Discord_Shutdown();
  }


  /**
   * Runs the callbacks for the Rich Presence.
   * <p>
   * This method should be called regularly to ensure that the callbacks are run.
   * </p>
   *
   * @see <a href="https://discord.com/developers/docs/rich-presence/how-to#so-how-does-it-work">Introducing Rich Presence - So, How Does It Work?</a>
   */
  public static void runCallbacks() {
    IDiscordRPC.INSTANCE.Discord_RunCallbacks();
  }


  /**
   * Updates the Rich Presence.
   * <p>
   * This method should be called after building a {@link DiscordRichPresence} object to update the
   * Rich Presence.
   * </p>
   *
   * @param presence The {@code DiscordRichPresence} object to be updated.
   * @throws NullPointerException if {@code presence} is {@code null}.
   * @see <a href="https://discord.com/developers/docs/rich-presence/how-to#updating-presence">Introducing Rich Presence - Updating Presence</a>
   */
  public static void updatePresence(final DiscordRichPresence presence) {
    Objects.requireNonNull(presence, "presence must not be null");

    IDiscordRPC.INSTANCE.Discord_UpdatePresence(presence);
  }

  /**
   * Clears the Rich Presence.
   * <p>
   * This method should be called whenever the user wants to clear the Rich Presence (e.g. when the
   * user closes the application).
   * </p>
   */
  public static void clearPresence() {
    IDiscordRPC.INSTANCE.Discord_ClearPresence();
  }


  /**
   * Responds to a request to join the user's game.
   *
   * @param userId The user ID of the user who sent the join request.
   * @param reply  The reply to be sent to the user. This should be one of the following:
   *               <ul>
   *               <li>0 - No Reply</li>
   *               <li>1 - Reply</li>
   *               <li>2 - Ignore</li>
   *               </ul>
   * @throws NullPointerException     if {@code userId} is {@code null}.
   * @throws IllegalArgumentException if {@code reply} is not between 0 and 2.
   * @see <a href="https://discord.com/developers/docs/rich-presence/how-to#joining">Introducing Rich Presence - Joining</a>
   */
  public static void respond(final String userId, int reply) {
    Objects.requireNonNull(userId, "userId must not be null");
    if (reply < 0 || reply > 2) {
      throw new IllegalArgumentException("reply must be between 0 and 2");
    }

    IDiscordRPC.INSTANCE.Discord_Respond(userId, reply);
  }

  /**
   * Updates the event handlers for the Rich Presence.
   * <p>
   * This method should be called if the user doesn't want to register all event handlers at
   * initialisation. Keep in mind that this will overwrite any previously registered event
   * handlers.
   *
   * @param handlers The {@code DiscordEventHandlers} object containing the event handlers to
   *                 be registered. See {@link DiscordEventHandlers} for more information.
   * @throws NullPointerException if {@code handlers} is {@code null}.
   * @see <a href="https://discord.com/developers/docs/rich-presence/how-to#shutting-down">Introducing Rich Presence - Shutting Down</a>
   */
  public static void updateHandlers(DiscordEventHandlers handlers) {
    Objects.requireNonNull(handlers, "handlers must not be null");

    IDiscordRPC.INSTANCE.Discord_UpdateHandlers(handlers);
  }

  /**
   * Registers the application with Discord.
   *
   * @param applicationId The application ID of the application to be registered.
   * @param command       The command to be used to launch the application.
   * @throws NullPointerException if either {@code applicationId} or {@code command} is
   *                              {@code null}.
   */
  public static void register(final String applicationId, final String command) {
    Objects.requireNonNull(applicationId, "applicationId must not be null");
    Objects.requireNonNull(command, "command must not be null");

    IDiscordRegister.INSTANCE.Discord_Register(applicationId, command);
  }

  /**
   * Registers a Steam game with Discord.
   *
   * @param applicationId The application ID of the application to be registered.
   * @param steamId       The Steam ID of the game to be registered.
   * @throws NullPointerException if either {@code applicationId} or {@code steamId} is
   *                              {@code null}.
   */
  public static void registerSteamGame(final String applicationId, final String steamId) {
    Objects.requireNonNull(applicationId, "applicationId must not be null");
    Objects.requireNonNull(steamId, "steamId must not be null");

    IDiscordRegister.INSTANCE.Discord_RegisterSteamGame(applicationId, steamId);
  }

  /**
   * Loads the native library and deletes the library after the JVM exits.
   *
   * @param p   The {@code Path} object to be used to load the library.
   * @param url The {@code URL} object to be used to load the library.
   * @throws RuntimeException if the library could not be loaded.
   */
  private static void loadLibrary(Path p, URL url) {
    try {
      Files.copy(url.openStream(), p, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException ioe) {
      throw new RuntimeException(MessageFormat.format("\"{0}\" could not be loaded",
          p), ioe);
    } finally {
      p.toFile().deleteOnExit();

      System.load(p.toAbsolutePath().toString());
    }
  }

  /**
   * Loads the native library for the current platform.
   * <p>
   * It creates a temporary directory in the user's home (or %TEMP% on Windows) and copies the
   * library to that directory. The library is then loaded and deleted after the JVM exits.
   * </p>
   * <p>
   * The library is copied to the following directories:
   * <ul>
   *   <li>Linux: ~/.discord-rpc</li>
   *   <li>macOS: ~/Library/Application Support/discord-rpc</li>
   *   <li>Windows: %TEMP%/discord-rpc</li>
   *   </ul>
   *   </p>
   *
   * @throws RuntimeException if the library could not be loaded or if the directory to copy the
   *                          library to could not be created.
   * @see ClassLoader#getSystemResource(String)
   * @see Files#createDirectories(Path, FileAttribute[])
   */
  private static void loadLibraryForPlatform() {
    String libraryName = System.mapLibraryName("discord-rpc");
    String userHome = System.getProperty("user.home")
        .replaceAll("[^a-zA-Z0-9_\\\\/\\-.]", "_");

    EPlatform platform = EPlatform.getPlatform();
    Map<EPlatform, String> platformLookup = Collections.unmodifiableMap(
        new EnumMap<EPlatform, String>(EPlatform.class) {{
          put(EPlatform.LINUX, Paths.get(userHome,
              ".discord-rpc").toString());
          put(EPlatform.MACOS, Paths.get(userHome,
              "Library", "Application Support", "discord-rpc").toString());
          put(EPlatform.WINDOWS, Paths.get(System.getenv("TEMP"),
              "discord-rpc").toString());
        }}
    );

    String libraryPath = String.join(File.separator,
        platformLookup.get(platform),
        libraryName);
    URL libraryFileUrl = ClassLoader.getSystemResource(String.format("%s/%s",
        platform == EPlatform.WINDOWS
            && System.getProperty("os.arch").equals("amd64")
            && System.getProperty("sun.arch.data.model").equals("64")
            ? "amd64"
            : "x86",
        libraryName));

    try {
      Files.createDirectories(Paths.get(platformLookup.get(platform)));
    } catch (IOException ioe) {
      throw new RuntimeException(MessageFormat.format("Directory \"{0}\" could not be created",
          platformLookup.get(platform)), ioe);
    } finally {
      loadLibrary(Paths.get(libraryPath), libraryFileUrl);
    }
  }

  /**
   * The {@code EPlatform} enum represents the different platforms that are supported by
   * DiscordRPC.
   *
   * @author Kawaxte
   */
  enum EPlatform {
    LINUX("nux"),
    MACOS("mac"),
    WINDOWS("win");

    static final String OS_NAME;

    static {
      OS_NAME = System.getProperty("os.name") != null
          ? System.getProperty("os.name").toLowerCase(Locale.ROOT)
          : "";
    }

    private final List<String> osNames;

    /**
     * Constructs a new {@code EPlatform} with the specified operating system names.
     *
     * @param osNames The operating system names to be used to identify the platform.
     */
    EPlatform(String... osNames) {
      this.osNames = Collections.unmodifiableList(Arrays.asList(osNames));
    }

    /**
     * Returns the platform that the current operating system is running on.
     *
     * @return The {@code EPlatform} object representing the platform that the current operating
     * system is running on.
     */
    public static EPlatform getPlatform() {
      return Arrays.stream(values())
          .filter(platform -> platform.osNames.stream().anyMatch(OS_NAME::contains))
          .findFirst()
          .orElse(null);
    }
  }
}
