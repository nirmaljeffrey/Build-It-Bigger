## Build-it-Bigger
Udacity's Android developer nano degree project
After downloading this app, generate the google admob application ID from the [Google Admob](https://developers.google.com/admob/android/quick-start)and 
use that application ID in [string.xml](app/src/free/res/values/strings.xml) file(line no: 3).

## Video
Free version
[![Build It Bigger App Free Version Demo](https://img.youtube.com/vi/mLwsjgmqQIQ/0.jpg)](https://www.youtube.com/watch?v=mLwsjgmqQIQ)
Paid Version
[![Build It Bigger App Paid Version Demo](https://img.youtube.com/vi/bGvLXJLaoYw/0.jpg)](https://www.youtube.com/watch?v=bGvLXJLaoYw)

## App Features

* Project contains a Java library for supplying jokes
* Project contains an Android library with an activity that displays jokes passed to it as intent extras.
* Project contains a Google Cloud Endpoints module that supplies jokes from the Java library. Project loads jokes from GCE        module via an async task.
* Project contains connected tests to verify that the async task is indeed loading jokes.
* Project contains paid/free flavors. The paid flavor has no ads, and no unnecessary dependencies.
* Free app variant display interstitial ads between the main activity and the joke-displaying activity.
* Project displays a loading indicator while the joke is being fetched from the server.
* Project configured a custom Gradle task that starts the GCE dev server, runs all the Android tests, and shuts down the dev server.
