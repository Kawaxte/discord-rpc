package com.github.kawaxte.presence;

import com.sun.jna.Callback;

/**
 * The <code>IDiscordEventHandler</code> interface provides methods for handling event-emitting
 * callbacks from the Discord RPC library.
 *
 * @author Kawaxte
 * @see Callback
 */
public interface IDiscordEventHandler extends Callback {

  /**
   * Called when the connection to Discord has been established.
   *
   * @param request The <code>DiscordUser</code> object containing all required information about
   *                the user executing the app.
   * @see DiscordUser
   */
  void ready(final DiscordUser request);

  /**
   * Called when the connection to Discord has been disconnected.
   *
   * @param errorCode The error code that was returned.
   * @param message   The message containing details about the error.
   */
  void disconnected(int errorCode, final String message);

  /**
   * Called when an error occurs.
   *
   * @param errorCode The error code that was returned.
   * @param message   The message containing details about the error.
   */
  void errored(int errorCode, final String message);

  /**
   * Called when the user joins a game.
   *
   * @param joinSecret The unique String containing information needed to let the player join.
   */
  void joinGame(final String joinSecret);

  /**
   * Called when the user spectates a game.
   *
   * @param spectateSecret The unique String containing information needed to let the player
   *                       spectate.
   */
  void spectateGame(final String spectateSecret);

  /**
   * Called when the user requests to join a game.
   *
   * @param request The <code>DiscordUser</code> object containing all required information about
   *                the user executing the app.
   * @see DiscordUser
   */
  void joinRequest(final DiscordUser request);
}
