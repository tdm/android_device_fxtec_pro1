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
import android.util.Log;
import android.view.MenuItem;

import org.lineageos.internal.util.FileUtils;

import org.lineageos.settings.device.R;
import org.lineageos.settings.device.widget.SeekBarPreference;

public class TouchscreenSettingsFragment extends PreferenceFragment
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = TouchscreenSettingsFragment.class.getSimpleName();

    private final boolean DEBUG = false;

    private SeekBarPreference mMarginSeekBar;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.touchscreen_panel);
        final ActionBar actionBar = getActivity().getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mMarginSeekBar = (SeekBarPreference) findPreference(Constants.TOUCHSCREEN_MARGIN_KEY);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        doUpdateMarginPreference(prefs);
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
    public void onSharedPreferenceChanged(SharedPreferences sharedPrefs, String key) {
        if (DEBUG) Log.d(TAG, "onSharedPreferenceChanged: " + key);

        if (Constants.TOUCHSCREEN_MARGIN_KEY.equals(key)) {
            doUpdateMarginPreference(sharedPrefs);
        }
    }

    private void doUpdateMarginPreference(SharedPreferences sharedPrefs) {
        int margin = Constants.TOUCHSCREEN_MARGIN_STEP * sharedPrefs.getInt(Constants.TOUCHSCREEN_MARGIN_KEY, 0);
        if (DEBUG) Log.d(TAG, "doUpdateMarginPreference: " + Integer.toString(margin));
        FileUtils.writeLine(Constants.TOUCHSCREEN_MARGIN_SYS_FILE, Integer.toString(margin));
        mMarginSeekBar.setSummary(getString(R.string.touchscreen_margin_summary, margin));
    }
}
