package com.github.kawaxte.presence;

import com.sun.jna.Callback;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

/**
 * The <code>DiscordEventHandlers</code> class is used to register callbacks for events that occur
 * in the Discord Rich Presence API.
 *
 * @author Kawaxte
 * @see Structure
 */
@FieldOrder({"ready", "disconnected", "errored", "joinGame", "spectateGame", "joinRequest"})
public class DiscordEventHandlers extends Structure {

  public IReadyCallback ready;
  public IDisconnectedCallback disconnected;
  public IErroredCallback errored;
  public IJoinGameCallback joinGame;
  public ISpectateGameCallback spectateGame;
  public IJoinRequestCallback joinRequest;

  /**
   * The <code>IReadyCallback</code> interface is used to register a callback for when the
   * connection to Discord has been established.
   *
   * @see Callback
   */
  public interface IReadyCallback extends Callback {

    /**
     * This method is called when the connection to Discord has been established.
     *
     * @param request The <code>DiscordUser</code> object containing all required information about
     *                the user executing the app.
     */
    void onReady(final DiscordUser request);
  }

  /**
   * The <code>IDisconnectedCallback</code> interface is used to register a callback for when the
   * connection to Discord has been lost.
   *
   * @see Callback
   */
  public interface IDisconnectedCallback extends Callback {

    /**
     * This method is called when the connection to Discord has been lost.
     *
     * @param errorCode The error code returned.
     * @param message   The message containing details about the error.
     */
    void onDisconnected(int errorCode, final String message);
  }

  /**
   * The <code>IErroredCallback</code> interface is used to register a callback for when an error
   * occurs.
   *
   * @see Callback
   */
  public interface IErroredCallback extends Callback {

    /**
     * This method is called when an error occurs.
     *
     * @param errorCode The <code>int</code> object representing the error code returned.
     * @param message   The <code>String</code> object representing the message containing details
     *                  about the error.
     */
    void onErrored(int errorCode, final String message);
  }

  /**
   * The <code>IJoinGameCallback</code> interface is used to register a callback for when another
   * player joins a game.
   *
   * @see Callback
   */
  public interface IJoinGameCallback extends Callback {

    /**
     * This method is called when another player joins a game.
     *
     * @param joinSecret The <code>String</code> object representing the secret used to join a
     *                   game.
     */
    void onJoinGame(final String joinSecret);
  }

  /**
   * The <code>ISpectateGameCallback</code> interface is used to register a callback for the when
   * another player requests to spectate a game.
   *
   * @see Callback
   */
  public interface ISpectateGameCallback extends Callback {

    /**
     * This method is called when another player requests to spectate a game.
     *
     * @param spectateSecret The <code>String</code> object representing the secret used to spectate
     *                       a game.
     */
    void onSpectateGame(final String spectateSecret);
  }

  /**
   * The <code>IJoinRequestCallback</code> interface is used to register a callback for the when
   * another player requests to join a game.
   *
   * @see Callback
   */
  public interface IJoinRequestCallback extends Callback {

    /**
     * This method is called when another player requests to join a game.
     *
     * @param request The <code>DiscordUser</code> object containing all required information about
     *                the user requesting to join.
     */
    void onJoinRequest(final DiscordUser request);
  }
}
