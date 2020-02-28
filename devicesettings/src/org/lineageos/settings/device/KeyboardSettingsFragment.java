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

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemProperties;
import android.preference.PreferenceManager;
import android.support.v14.preference.PreferenceFragment;
import android.support.v14.preference.SwitchPreference;
import android.support.v7.preference.ListPreference;
import android.util.Log;
import android.view.MenuItem;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.lineageos.internal.util.FileUtils;

import org.lineageos.settings.device.R;

public class KeyboardSettingsFragment extends PreferenceFragment
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = KeyboardSettingsFragment.class.getSimpleName();

    private ListPreference mLayoutPref;
    private SwitchPreference mKeymapCustomPref;
    private SwitchPreference mKeymapSpacePowerPref;
    private SwitchPreference mKeymapFnKeysPref;
    private SwitchPreference mFastPollPref;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.keyboard_panel);
        final ActionBar actionBar = getActivity().getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mLayoutPref = (ListPreference) findPreference(Constants.KEYBOARD_LAYOUT_KEY);
        mKeymapCustomPref = (SwitchPreference) findPreference(Constants.KEYBOARD_KEYMAP_CUSTOM_KEY);
        mKeymapSpacePowerPref = (SwitchPreference) findPreference(Constants.KEYBOARD_KEYMAP_SPACEPOWER_KEY);
        mKeymapFnKeysPref = (SwitchPreference) findPreference(Constants.KEYBOARD_KEYMAP_FNKEYS_KEY);
        mFastPollPref = (SwitchPreference) findPreference(Constants.KEYBOARD_FASTPOLL_KEY);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        doUpdateLayoutPreference(prefs);
        doUpdateKeymapPreference(prefs);
        doUpdateFastPollPreference(prefs);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
        if (Constants.KEYBOARD_LAYOUT_KEY.equals(key)) {
            doUpdateLayoutPreference(prefs);
            doUpdateKeymapPreference(prefs);
        }
        else if (Constants.KEYBOARD_KEYMAP_CUSTOM_KEY.equals(key)) {
            doUpdateLayoutPreference(prefs);
            doUpdateKeymapPreference(prefs);
        }
        else if (Constants.KEYBOARD_KEYMAP_SPACEPOWER_KEY.equals(key)) {
            doUpdateLayoutPreference(prefs);
            doUpdateKeymapPreference(prefs);
        }
        else if (Constants.KEYBOARD_KEYMAP_FNKEYS_KEY.equals(key)) {
            doUpdateLayoutPreference(prefs);
            doUpdateKeymapPreference(prefs);
        }
        else if (Constants.KEYBOARD_FASTPOLL_KEY.equals(key)) {
            doUpdateFastPollPreference(prefs);
        }
    }

    private void doUpdateLayoutPreference(SharedPreferences prefs) {
        String value = mLayoutPref.getValue();
        writeFile(Constants.KEYBOARD_LAYOUT_SYS_FILE, value);
        mLayoutPref.setSummary(value);
    }

    private void doUpdateKeymapPreference(SharedPreferences prefs) {
        boolean custom = prefs.getBoolean(Constants.KEYBOARD_KEYMAP_CUSTOM_KEY, false);
        mKeymapSpacePowerPref.setEnabled(!custom);
        mKeymapFnKeysPref.setEnabled(!custom);
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

    private void doUpdateFastPollPreference(SharedPreferences prefs) {
        int value = mFastPollPref.isChecked()
                    ? Constants.KEYBOARD_POLL_INTERVAL_FAST
                    : Constants.KEYBOARD_POLL_INTERVAL_SLOW;
        writeFile(Constants.KEYBOARD_POLL_INTERVAL_SYS_FILE, Integer.toString(value));
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
