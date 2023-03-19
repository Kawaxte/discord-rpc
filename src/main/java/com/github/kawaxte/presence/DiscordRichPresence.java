package com.github.kawaxte.presence;

import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

/**
 * The <code>DiscordRichPresence</code> class represents the Rich Presence object and is used in
 * {@link DiscordRPC#updatePresence(DiscordRichPresence)}.
 * <p>
 * It is also used to create a new instance of {@link DiscordRichPresence} using {@link Builder}.
 * </p>
 *
 * @author Kawaxte
 * @see Structure
 */
@FieldOrder({"state", "details", "startTimestamp", "endTimestamp", "largeImageKey",
    "largeImageText", "smallImageKey", "smallImageText", "partyId", "partySize", "partyMax",
    "matchSecret", "joinSecret", "spectateSecret", "instance"})
public class DiscordRichPresence extends Structure {

  public String state;
  public String details;
  public long startTimestamp;
  public long endTimestamp;
  public String largeImageKey;
  public String largeImageText;
  public String smallImageKey;
  public String smallImageText;
  public String partyId;
  public int partySize;
  public int partyMax;
  @Deprecated
  public String matchSecret;
  public String joinSecret;
  public String spectateSecret;
  @Deprecated
  public byte instance;

  /**
   * The <code>Builder</code> class represents the builder of the Rich Presence object and is used
   * to create a new instance of {@link DiscordRichPresence}.
   *
   * @author Kawaxte
   */
  public static class Builder {

    /**
     * Variable representing the {@link DiscordRichPresence} object.
     */
    private final DiscordRichPresence presence;

    /**
     * Constructor of the <code>Builder</code> class that creates a new instance of
     * {@link DiscordRichPresence}.
     */
    public Builder() {
      this.presence = new DiscordRichPresence();
    }

    /**
     * Sets the state for the Rich Presence.
     *
     * @param state The <code>String</code> object representing the state of the Rich Presence.
     * @return The <code>Builder</code> object.
     */
    public Builder setState(String state) {
      this.presence.state = state;
      return this;
    }

    /**
     * Sets the details for the Rich Presence.
     *
     * @param details The <code>String</code> object representing the details of the Rich Presence.
     * @return The <code>Builder</code> object.
     */
    public Builder setDetails(String details) {
      this.presence.details = details;
      return this;
    }

    /**
     * Sets the start timestamp for the Rich Presence which is used to calculate the elapsed time.
     *
     * @param startTimestamp The <code>long</code> object representing the start timestamp for the
     *                       Rich Presence in seconds since epoch.
     */
    public Builder setStartTimestamp(long startTimestamp) {
      this.presence.startTimestamp = startTimestamp;
      return this;
    }

    /**
     * Sets the end timestamp for the Rich Presence which is used to calculate the remaining time.
     *
     * @param endTimestamp The <code>long</code> object representing the end timestamp for the rich
     *                     presence in seconds since epoch.
     * @return The <code>Builder</code> object.
     */
    public Builder setEndTimestamp(long endTimestamp) {
      this.presence.endTimestamp = endTimestamp;
      return this;
    }

    /**
     * Sets the large image for the Rich Presence.
     *
     * @param largeImageKey The <code>String</code> object representing the key of the large image.
     * @return The <code>Builder</code> object.
     */
    public Builder setLargeImageKey(String largeImageKey) {
      this.presence.largeImageKey = largeImageKey;
      return this;
    }

    /**
     * Sets the large image hover text for the Rich Presence.
     *
     * @param largeImageText The <code>String</code> object representing the text for the large
     *                       image.
     * @return The <code>Builder</code> object.
     */
    public Builder setLargeImageText(String largeImageText) {
      this.presence.largeImageText = largeImageText;
      return this;
    }

    /**
     * Sets the small image for the Rich Presence.
     *
     * @param smallImageKey The <code>String></code> object representing the key of the small
     *                      image.
     * @return The <code>Builder</code> object.
     */
    public Builder setSmallImageKey(String smallImageKey) {
      this.presence.smallImageKey = smallImageKey;
      return this;
    }

    /**
     * Sets the small image hover text for the Rich Presence.
     *
     * @param smallImageText The <code>String</code> object representing the text for the small
     *                       image.
     * @return The <code>Builder</code> object.
     */
    public Builder setSmallImageText(String smallImageText) {
      this.presence.smallImageText = smallImageText;
      return this;
    }

    /**
     * Sets the party ID for the Rich Presence.
     *
     * @param partyId The <code>String</code> object representing the party ID.
     * @return The <code>Builder</code> object.
     */
    public Builder setPartyId(String partyId) {
      this.presence.partyId = partyId;
      return this;
    }

    /**
     * Sets the party size for the Rich Presence.
     *
     * @param partySize The <code>int</code> representing the party size.
     * @return The <code>Builder</code> object.
     */
    public Builder setPartySize(int partySize) {
      this.presence.partySize = partySize;
      return this;
    }

    /**
     * Sets the party max for the Rich Presence.
     *
     * @param partyMax The <code>int</code> representing the maximum party size.
     * @return The <code>Builder</code> object.
     */
    public Builder setPartyMax(int partyMax) {
      this.presence.partyMax = partyMax;
      return this;
    }

    /**
     * Sets the match secret for the Rich Presence.
     *
     * @param matchSecret The <code>String</code> object representing the match secret.
     * @return The <code>Builder</code> object.
     */
    @Deprecated
    public Builder setMatchSecret(String matchSecret) {
      this.presence.matchSecret = matchSecret;
      return this;
    }

    /**
     * Sets the join secret for the Rich Presence.
     *
     * @param joinSecret The <code>String</code> object representing the join secret.
     * @return The <code>Builder</code> object.
     */
    public Builder setJoinSecret(String joinSecret) {
      this.presence.joinSecret = joinSecret;
      return this;
    }

    /**
     * Sets the spectate secret for the Rich Presencee.
     *
     * @param spectateSecret The <code>String</code> object representing the spectate secret.
     * @return The <code>Builder</code> object.
     */
    public Builder setSpectateSecret(String spectateSecret) {
      this.presence.spectateSecret = spectateSecret;
      return this;
    }

    /**
     * Sets the instance for the Rich Presence.
     *
     * @param instance The <code>byte</code> representing the instance.
     * @return The <code>Builder</code> object.
     */
    @Deprecated
    public Builder setInstance(byte instance) {
      this.presence.instance = instance;
      return this;
    }

    /**
     * Builds the {@link DiscordRichPresence} object.
     *
     * @return The <code>DiscordRichPresence</code> object.
     */
    public DiscordRichPresence build() {
      return this.presence;
    }
  }
}
