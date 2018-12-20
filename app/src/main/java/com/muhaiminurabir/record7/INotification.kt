package com.muhaiminurabir.record7

import android.app.Notification

interface INotification<T> {
    fun build(): Notification
}