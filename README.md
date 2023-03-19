<h1 align="center">JDiscordRPC</h1>
<p align="center">
  <a href="https://www.azul.com/downloads/?version=java-8-lts&package=jre">
    <img src="https://img.shields.io/badge/Java-8%2B-blue.svg">
  </a>
  <a href="https://github.com/Kawaxte/discord-rpc/releases/latest">
    <img src="https://img.shields.io/github/v/release/Kawaxte/discord-rpc?label=latest">
  </a>
  <a href="https://github.com/Kawaxte/discord-rpc/releases/latest">
    <img src="https://img.shields.io/github/downloads/Kawaxte/discord-rpc/latest/total.svg">
  </a>
  <a href="https://github.com/Kawaxte/discord-rpc/blob/stable/LICENSE">
    <img src="https://img.shields.io/github/license/Kawaxte/discord-rpc">
  </a>
</p>

<p align="center">
  <a href="https://jitpack.io/#Kawaxte/discord-rpc">
    <img src="https://jitpack.io/v/Kawaxte/discord-rpc.svg">
  </a>
</p>

This library contains JNA bindings for interacting with the Discord Rich Presence API in Java.
It is based on the C++ implementation of the (_now deprecated_)
[discord-rpc](https://github.com/discord/discord-rpc) library.

---

<h2 align="center">Getting Started</h2>

In order to use the library, you will need to set up a Discord application. You can do this by
following these steps:

1. Create a new application on
   the [Discord Developer Portal](https://discord.com/developers/applications).
2. Retrieve the `APPLICATION ID` from the `General Information` page of your application. This is
   needed
   to initialise the Rich Presence.
3. Add the Rich Presence assets to your application by uploading the images you want to use on the
   Rich Presence page. You can also add a large and small image to the Rich Presence.

---

<h3 align="center">Adding the Library to Your Project</h3>

You can add the library to your project by adding the following repository and dependency to your
build file:

<h4 align="center">Gradle (build.gradle)</h4>

```groovy
repositories {
  maven { url 'https://jitpack.io' }
}
```

```groovy
dependencies {
  implementation 'com.github.Kawaxte:discord-rpc:%VERSION%'
}
```

---

<h4 align="center">Maven (pom.xml)</h4>

```xml

<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```

```xml

<dependencies>
  <dependency>
    <groupId>com.github.Kawaxte</groupId>
    <artifactId>discord-rpc</artifactId>
    <version>%VERSION%</version>
  </dependency>
</dependencies>
```

---

<h3 align="center">Using the Library</h3>

The following code snippet shows how to use the library to set the Rich Presence state and details
for your application alongside the start timestamp (in seconds):

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

---

<h2 align="center">Managing Issues and Contributions</h2>
<p align="center">
  <a href="https://github.com/Kawaxte/discord-rpc/issues">
    <img src="https://img.shields.io/github/issues/Kawaxte/discord-rpc">
  </a>
  <a href="https://github.com/Kawaxte/discord-rpc/pulls">
    <img src="https://img.shields.io/github/issues-pr/Kawaxte/discord-rpc">
  </a>
</p>

We encourage the community to help us improve the library by reporting issues and submitting pull
requests. To do this, we use GitHub's issue tracker and pull request feature.

---

<h3 align="center">Reporting Issues and Suggestions</h3>

If you encounter a problem or have a working suggestion, please follow these steps:

1. Go to the [issue tracker](https://github.com/Kawaxte/discord-rpc/issues) for this
   repository.
2. Click on the `New issue` button.
3. Describe the issue or suggestion in the provided form, making sure to be clear and concise.
4. Include any relevant details, like error messages or steps to reproduce the issue.
5. Click on the `Submit new issue` button to open the issue.
6. Wait for the issue to be reviewed and resolved.

---

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

---

