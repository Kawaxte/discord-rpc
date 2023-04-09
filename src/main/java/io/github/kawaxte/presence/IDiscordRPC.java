package io.github.kawaxte.presence;

import com.sun.jna.Library;
import com.sun.jna.Native;


/**
 * The {@code IDiscordRPC} interface provides methods for initialising and interacting with the
 * Discord Rich Presence API using the JNA library to call native functions from the Discord RPC
 * library.
 *
 * @author Kawaxte
 * @see Library
 */
public interface IDiscordRPC extends Library {

  IDiscordRPC INSTANCE = Native.load("discord-rpc", IDiscordRPC.class);

  void Discord_Initialize(final String applicationId, final DiscordEventHandlers handlers,
      final int autoRegister, final String optionalSteamId);

  void Discord_Shutdown();

  void Discord_RunCallbacks();

  void Discord_UpdatePresence(final DiscordRichPresence presence);

  void Discord_ClearPresence();

  void Discord_Respond(final String userId, final int reply);

  void Discord_UpdateHandlers(final DiscordEventHandlers handlers);
}
