package com.muhaiminurabir.record7

import android.app.Service
import android.content.Intent
import android.os.Build
import net.callrec.library.recorder.base.RecorderBase
import org.jetbrains.anko.toast
import java.io.File



class CallRecProcessingNotification(service: Service) : ProcessingBaseNotification(service) {
    override fun getNotificationUpdate(): INotification<*> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getNotificationWait(): INotification<*> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getNotificationErr(e: RecorderBase.RecorderException): INotification<*> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getNotificationErr(e: ProcessingException): INotification<*> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun makeOutputFile(): String {
        val dirStorage = Utils.getDefaultPath(context)

        val file = File(dirStorage)

        if (!file.exists()) {
            if (!file.mkdirs()) {
                throw Exception()
            }
        }

        filePathNoFormat = dirStorage + Utils.makeFileName(audioSource)
        return filePathNoFormat
    }

    override fun getNotificationOk(): INotification<*> {
        return CallRecNotification(this)
    }

    override fun getNotificationErr(e: Exception): INotification<*> {
        return CallRecNotificationErr(this)
    }

    override fun isServiceOn(): Boolean {
        return true
    }

    override fun getPauseBeforeRecord(): Int {
        return 0
    }

    override fun getCheckRulesRecord(): Boolean {
        return true
    }

    override fun prepareAudioPreferences() {
        formatFile = "amr"
        outputFormat = 0
        encoder = 0
        stereoChannel = false
        samplingRate = 8000
        audioEncodingBitRate = 0
        if (Build.VERSION.SDK_INT >= 24) {
            typeRecorder = TypeRecorder.WAV_NATIVE
        } else {
            typeRecorder = TypeRecorder.WAV
        }
    }

    override fun stopThisService() {
        service.stopService(Intent(context, service.javaClass))
    }

    override fun onRecorderError(e: Exception) {
        super.onRecorderError(e)
        service.toast(e.toString())
    }
}