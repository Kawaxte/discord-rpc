<h1 align="center">JDiscordRPC</h1>

---

This library contains JNA bindings for interacting with the Discord Rich Presence API in Java.
It is based on the C++ implementation of the (now deprecated)
[discord-rpc](https://github.com/discord/discord-rpc) library.

<h2 align="center">Getting Started</h2>

---

<h3 align="center">Installation</h3>

If you are using Gradle, add the following to your `build.gradle` file:

```groovy
dependencies {
    implementation 'com.github.kawaxte:discord-rpc:%VERSION%'
}
```

If you are using Maven, add the following to your `pom.xml` file:

```xml
<dependency>
  <groupId>com.github.kawaxte</groupId>
  <artifactId>discord-rpc</artifactId>
  <version>%VERSION%</version>
</dependency>
```

Make sure to replace `%VERSION%` with the latest version of the library.

<h3 align="center">Usage</h3>

To use this application, follow these steps:

1. Create a new application on
   the [Discord Developer Portal](https://discord.com/developers/applications).
2. Retrieve the `APPLICATION ID` from the `General Information` page of your application. This is
   needed
   to initialise the Rich Presence.
3. Add the Rich Presence assets to your application by uploading the images you want to use on the
   Rich Presence page. You can also add a large and small image to the Rich Presence.

Once you have completed these steps, you can use the following example code to set up the Rich
Presence:

```java
public class Main {

  public static void main(String... args) {

    // Set the Discord Rich Presence event handlers to notify when the client is ready.
    DiscordEventHandlers handlers = new DiscordEventHandlers();
    handlers.ready = request -> {

      // Set the Rich Presence state to indicate the RPC test is running.
      DiscordRichPresence presence = new DiscordRichPresence.Builder()
          .setState("DiscordRPC Test")
          .setDetails("Running the RPC test")
          .setStartTimestamp(System.currentTimeMillis() / 1000L)
          .build();

      // Update the Rich Presence using the built presence.
      DiscordRPC.updatePresence(presence);
    };

    // Initialise the Rich Presence with the application ID and event handlers.
    DiscordRPC.initialise("APPLICATION_ID", handlers, true, null);

    // Update the Rich Presence periodically using ScheduledExecutorService as it is more
    // efficient than using a 'while' loop to update the Rich Presence.
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

<h2 align="center">Managing Issues and Contributions</h2>
<p align="center">
  <a href="https://github.com/Kawaxte/discord-rpc/issues">
    <img src="https://img.shields.io/github/issues/Kawaxte/discord-rpc">
  </a>
  <a href="https://github.com/Kawaxte/discord-rpc/pulls">
    <img src="https://img.shields.io/github/issues-pr/Kawaxte/discord-rpc">
  </a>
</p>

---

We encourage the community to help us improve the library by reporting issues and submitting pull
requests. To do this, we use GitHub's issue tracker and pull request feature.

<h3 align="center">Reporting Issues and Suggestions</h3>

If you encounter a problem or have a working suggestion, please follow these steps:

1. Go to the [issue tracker](https://github.com/Kawaxte/discord-rpc/issues) for this
   repository.
2. Click on the `New issue` button.
3. Describe the issue or suggestion in the provided form, making sure to be clear and concise.
4. Include any relevant details, like error messages or steps to reproduce the issue.
5. Click on the `Submit new issue` button to open the issue.
6. Wait for the issue to be reviewed and resolved.

<h3 align="center">Submitting Pull Requests</h3>

If you would like to submit a change or addition to
the library, please follow these steps:

1. First, create a copy of the repository by forking it, which will create a personal copy of the
   repository that you can modify.
2. Make the changes you want to make in a new branch of your forked repository.
3. Thoroughly test your changes.
4. Submit a pull request from your forked repository to the original repository.
   Provide a detailed explanation of your changes and any relevant information.
5. Wait for the pull request to be reviewed and merged.

