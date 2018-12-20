package com.muhaiminurabir.record7

import net.callrec.library.recorder.NativeWavRecorder
import net.callrec.library.recorder.AudioRecorder
import net.callrec.library.recorder.WavRecorder



object RecorderFactory {
    fun createWavRecorder(audioSource: Int, sampleRateInHz: Int, channelConfig: Int, audioEncoding: Int,
                          filePathNoFormat: String): AudioRecorder {
        return WavRecorder(audioSource, sampleRateInHz, channelConfig, audioEncoding, filePathNoFormat)
    }

    fun createNativeWavRecorder(audioSource: Int, sampleRateInHz: Int, channelConfig: Int, audioEncoding: Int,
                                filePathNoFormat: String): AudioRecorder {
        return NativeWavRecorder(audioSource, sampleRateInHz, channelConfig, audioEncoding, filePathNoFormat)
    }
}