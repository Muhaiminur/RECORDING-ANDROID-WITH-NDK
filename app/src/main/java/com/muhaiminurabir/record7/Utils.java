package com.muhaiminurabir.record7;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Utils {
    public static String getDefaultPath(Context context) {
        return String.format("%s%s",
                Utils.normalDir(Environment.getExternalStorageDirectory().getAbsolutePath()),
                "ABIR_RECORDING/");
    }

    private static String normalDir(String dir) {
        if (TextUtils.isEmpty(dir)) {
            return dir;
        }

        dir = dir.replace('\\', '/');
        if (!dir.substring(dir.length() - 1, dir.length()).equals("/")) {
            dir += "/";
        }
        return dir;
    }

    public static String makeFileName(int audioSource) {

        String nameSubscr = "ABIR_RECORDING";
        return String.format("[%s]_%d", nameSubscr, audioSource);
    }
}