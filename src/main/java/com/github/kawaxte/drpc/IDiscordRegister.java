package com.github.kawaxte.drpc;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * The <code>IDiscordRegister</code> interface provides methods for registering the application or
 * Steam game with Discord using the JNA library to call native functions from the Discord RPC
 * library.
 *
 * @author Kawaxte
 * @see Library
 */
public interface IDiscordRegister extends Library {

  IDiscordRegister INSTANCE = Native.load("discord-rpc", IDiscordRegister.class);

  void Discord_Register(final String applicationId, final String command);

  void Discord_RegisterSteamGame(final String applicationId, final String steamId);
}
