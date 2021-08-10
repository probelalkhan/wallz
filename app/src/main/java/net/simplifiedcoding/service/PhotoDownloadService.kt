package net.simplifiedcoding.service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*
import net.simplifiedcoding.R
import net.simplifiedcoding.ui.MainActivity
import net.simplifiedcoding.utils.NOTIFICATION_CHANNEL_ID
import java.io.*
import java.net.URL
import java.net.URLConnection

class PhotoDownloadService : Service() {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val photoId = intent?.getStringExtra(KEY_PHOTO_ID)
        val photoUrl = intent?.getStringExtra(KEY_PHOTO_URL)

        if (photoId != null || photoUrl != null) {

            val pendingIntent: PendingIntent =
                Intent(this, MainActivity::class.java).let { notificationIntent ->
                    PendingIntent.getActivity(this, 0, notificationIntent, 0)
                }

            val notificationBuilder: Notification.Builder =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Notification.Builder(this, NOTIFICATION_CHANNEL_ID)
                } else {
                    Notification.Builder(this)
                }

            val notification = notificationBuilder
                .setContentTitle(getText(R.string.notification_title))
                .setContentText(getText(R.string.notification_message))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .build()

            startForeground(ONGOING_NOTIFICATION_ID, notification)
            scope.launch { downloadPhoto(photoId!!, photoUrl!!) }
        }
        return super.onStartCommand(intent, flags, startId)
    }


    private suspend fun downloadPhoto(photoId: String, photoUrl: String) =
        withContext(Dispatchers.IO) {
            val download = kotlin.runCatching {
                var count: Int
                val url = URL(photoUrl)
                val conection: URLConnection = url.openConnection()
                conection.connect()
                val lenghtOfFile: Int = conection.contentLength
                val input: InputStream = BufferedInputStream(url.openStream(), 8192)


                val output: OutputStream =
                    FileOutputStream(filesDir.toString() + File.separator + photoId + EXTENSION)


                val data = ByteArray(1024)
                var total: Long = 0
                while (input.read(data).also { count = it } != -1) {
                    total += count.toLong()
                    val progress = (total * 100 / lenghtOfFile).toInt()
                    output.write(data, 0, count)
                }
                output.flush()
                output.close()
                input.close()
            }
        }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    companion object {
        private const val EXTENSION = ".jpg"
        private const val KEY_PHOTO_ID = "key_photo_id"
        private const val KEY_PHOTO_URL = "key_photo_url"
        private const val ONGOING_NOTIFICATION_ID = 107

        fun downloadPhoto(context: Context, id: String, url: String) {
            context.startService(
                Intent(context, PhotoDownloadService::class.java).also {
                    it.putExtra(KEY_PHOTO_ID, id)
                    it.putExtra(KEY_PHOTO_URL, url)
                }
            )
        }
    }
}