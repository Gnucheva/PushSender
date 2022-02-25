package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream


fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .setDatabaseUrl(dbUrl)
        .build()

    FirebaseApp.initializeApp(options)

    val message = Message.builder()
        .putData("action", "NEW_POST")
        .putData("content", """{
          "userId": 4,
          "userName": "Boris",
          "postId": 3,
          "post": "28 февраля стартует бесплатный курс «Искусственный интеллект: создайте свою первую нейросеть. Благодаря машинному обучению нам доступны чудеса вроде распознавания лиц, написания песен в стиле «Гражданской обороны» и картин в духе Рембрандта. Создать свою собственную нейросеть реально — и не только для опытных дата-сайентистов. Вы тоже можете попробовать."
        }""".trimIndent())
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(message)
}