<h1 style="text-align: center;">JDiscordRPC</h1>
<div style="display: flex; justify-content: center;">
  <a href="https://www.azul.com/downloads/?version=java-8-lts&package=jre">
    <img src="https://img.shields.io/badge/Java-8%2B-blue.svg" alt="Java" style="margin-right: 5px">
  </a>
  <a href="https://github.com/Kawaxte/discord-rpc/releases/latest">
    <img src="https://img.shields.io/github/v/release/Kawaxte/discord-rpc?label=latest" alt="latest" style="margin-right: 5px">
  </a>
  <a href="https://jitpack.io/#Kawaxte/discord-rpc">
    <img src="https://jitpack.io/v/Kawaxte/discord-rpc.svg" alt="JitPack" style="margin-right: 5px">
  </a>
  <a href="https://github.com/Kawaxte/discord-rpc/releases/latest">
    <img src="https://img.shields.io/github/downloads/Kawaxte/discord-rpc/total.svg" alt="downloads" style="margin-right: 5px">
  </a>
  <a href="https://github.com/Kawaxte/discord-rpc/blob/main/LICENSE">
    <img src="https://img.shields.io/github/license/Kawaxte/discord-rpc" alt="license" style="margin-right: 5px">
  </a>
</div>

This library contains JNA bindings for interacting with the Discord Rich Presence API in Java.
It is based on the C++ implementation of the (_now deprecated_)
[discord-rpc](https://github.com/discord/discord-rpc) library.

---

<h2 style="text-align: center;">Getting Started</h2>

In order to use the library, you will need to set up a Discord application. You can do this by
following these steps:

1. Create a new application on
   the [Discord Developer Portal](https://discord.com/developers/applications).
2. Retrieve the `APPLICATION ID` from the `General Information` page of your application. This is
   needed
   to initialise the Rich Presence.
3. Add the Rich Presence assets to your application by uploading the images you want to use on the
   Rich Presence page. You can also add a large and small image to the Rich Presence.

<h3 style="text-align: center;">Example</h3>

The following code snippet shows how to use the library to set the Rich Presence state and details
for your application alongside the start timestamp (in seconds):

```java
public class Main {
  
  public static void main(String... args) {
    DiscordEventHandlers handlers = new DiscordEventHandlers();
    handlers.ready = request -> {
      DiscordRichPresence presence = new DiscordRichPresence.Builder()
          .setState("DiscordRPC Test")
          .setDetails("Running the RPC test")
          .setStartTimestamp(System.currentTimeMillis() / 1000L)
          .build();
      
      DiscordRPC.updatePresence(presence);
    };
    
    DiscordRPC.initialise("APPLICATION_ID", handlers, false, null);

    // Update the Rich Presence every 2 seconds using ScheduledExecutorService as it is more
    // efficient than sleeping a Thread in a WHILE loop.
    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    service.scheduleAtFixedRate(DiscordRPC::runCallbacks, 2, 2, TimeUnit.SECONDS);

    // Clear and shut down the Rich Presence when the application exits.
    Runtime.getRuntime().addShutdownHook(Executors.defaultThreadFactory().newThread(
        () -> {
          DiscordRPC.clearPresence();
          DiscordRPC.shutdown();
        }));

  }
}

```
---

<h2 style="text-align: center;">Managing Issues and Contributions</h2>
<div style="display: flex; justify-content: center;">
  <a href="https://github.com/Kawaxte/discord-rpc/issues">
    <img src="https://img.shields.io/github/issues/Kawaxte/discord-rpc" alt="issues" style="margin-right: 5px">
  </a>
  <a href="https://github.com/Kawaxte/discord-rpc/pulls">
    <img src="https://img.shields.io/github/issues-pr/Kawaxte/discord-rpc" alt="pull requests" style="margin-right: 5px">
  </a>
</div>

We want to make our library better, and we welcome your help! If you find any issues or have
suggestions for new features, you can let us know by using GitHub's issue tracker and pull request
feature.

<h3 style="text-align: center;">Reporting Issues and Suggestions</h3>

To report an issue or suggest a new feature:

1. Go to the [issue tracker](https://github.com/Kawaxte/discord-rpc/issues) for our launcher
   on
   GitHub.
2. Click the `New issue` button.
3. Describe the issue or suggestion clearly and provide any relevant details.
4. Click the `Submit new issue` button to create the issue.

<h3 style="text-align: center;">Submitting Pull Requests</h3>

If you would like to submit a change or addition to
the library, please follow these steps:

1. Make a copy of our library repository
by [forking it](https://docs.github.com/en/get-started/quickstart/fork-a-repo) on GitHub.
2. Make the changes you want in a new branch of your forked repository.
3. Test your changes thoroughly.
4. Submit a pull request from your forked repository to our original repository with a detailed
   explanation of your changes.