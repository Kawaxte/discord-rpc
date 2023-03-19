package com.github.kawaxte.presence;

import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

/**
 * The <code>DiscordUser</code> class represents a Discord user.
 *
 * @author Kawaxte
 * @see Structure
 */
@FieldOrder({"userId", "username", "discriminator", "avatar"})
public class DiscordUser extends Structure {

  public String userId;
  public String username;
  public String discriminator;
  public String avatar;
}
