/*
 * Copyright (C) 2018-2019 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lineageos.settings.device;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemProperties;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.lineageos.settings.device.R;

public class BootCompletedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        setKeyboardLayout(context);
        setKeyboardKeymap(context);
        setKeyboardPollInterval(context);
        setTouchscreenMargin(context);
    }

    private void setKeyboardLayout(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (!prefs.contains(Constants.KEYBOARD_LAYOUT_KEY)) {
            String text = readFile(Constants.KEYBOARD_KEYMAP_CFG_FILE);
            if (text == null) {
                text = Constants.KEYBOARD_LAYOUT_DEFAULT;
            }
            prefs.edit().putString(Constants.KEYBOARD_LAYOUT_KEY, text).commit();
        }
        String layout = prefs.getString(Constants.KEYBOARD_LAYOUT_KEY, Constants.KEYBOARD_LAYOUT_DEFAULT);
        writeFile(Constants.KEYBOARD_LAYOUT_SYS_FILE, layout);
    }

    private void setKeyboardKeymap(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (!prefs.contains(Constants.KEYBOARD_KEYMAP_CUSTOM_KEY)) {
            File f = new File(Constants.KEYBOARD_KEYMAP_CFG_FILE);
            prefs.edit().putBoolean(Constants.KEYBOARD_KEYMAP_CUSTOM_KEY, f.exists()).commit();
        }
        boolean custom = prefs.getBoolean(Constants.KEYBOARD_KEYMAP_CUSTOM_KEY, false);
        if (custom) {
            String text = readFile(Constants.KEYBOARD_KEYMAP_CFG_FILE);
            writeFile(Constants.KEYBOARD_KEYMAP_SYS_FILE, text);
        }
        else {
            boolean value;
            int i;
            value = prefs.getBoolean(Constants.KEYBOARD_KEYMAP_SPACEPOWER_KEY, false);
            if (value) {
                for (i = 0; i < Constants.KEYBOARD_KEYMAP_SPACEPOWER_TEXT.length; ++i) {
                    writeFile(Constants.KEYBOARD_KEYMAP_SYS_FILE, Constants.KEYBOARD_KEYMAP_SPACEPOWER_TEXT[i] + "\n");
                }
            }
            value = prefs.getBoolean(Constants.KEYBOARD_KEYMAP_FNKEYS_KEY, false);
            if (value) {
                for (i = 0; i < Constants.KEYBOARD_KEYMAP_FNKEYS_TEXT.length; ++i) {
                    writeFile(Constants.KEYBOARD_KEYMAP_SYS_FILE, Constants.KEYBOARD_KEYMAP_FNKEYS_TEXT[i] + "\n");
                }
            }
        }
    }

    private void setKeyboardPollInterval(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        int value = prefs.getBoolean(Constants.KEYBOARD_FASTPOLL_KEY, false)
                ? Constants.KEYBOARD_POLL_INTERVAL_FAST
                : Constants.KEYBOARD_POLL_INTERVAL_SLOW;
        writeFile(Constants.KEYBOARD_POLL_INTERVAL_SYS_FILE, Integer.toString(value));
    }

    private void setTouchscreenMargin(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        int value = Constants.TOUCHSCREEN_MARGIN_STEP * prefs.getInt(Constants.TOUCHSCREEN_MARGIN_KEY,
                context.getResources().getInteger(R.integer.touchscreen_margin_default));
        writeFile(Constants.TOUCHSCREEN_MARGIN_SYS_FILE, Integer.toString(value));
    }

    private String readFile(String filename) {
        String result = null;
        try {
            FileReader reader = new FileReader(filename);
            char[] buffer = new char[4096];
            reader.read(buffer);
            result = new String(buffer);
        }
        catch (Exception e) { /* Ignore */ }
        return result;
    }

    private boolean writeFile(String filename, String text) {
        boolean result = false;
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(text);
            writer.flush();
            result = true;
        }
        catch (Exception e) { /* Ignore */ }
        return result;
    }
}
