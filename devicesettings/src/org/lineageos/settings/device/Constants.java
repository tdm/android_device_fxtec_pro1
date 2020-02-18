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

public class Constants {
    // Keyboard layout
    public static final String KEYBOARD_LAYOUT_KEY = "keyboard_layout";
    public static final String KEYBOARD_LAYOUT_DEFAULT = "qwerty";
    public static final String KEYBOARD_LAYOUT_CFG_FILE = "/persist/data/keyboard/layout";
    public static final String KEYBOARD_LAYOUT_SYS_FILE = "/sys/devices/soc/c17a000.i2c/i2c-6/6-0058/layout";

    // Keyboard keymap
    public static final String KEYBOARD_KEYMAP_CUSTOM_KEY = "keyboard_keymap_custom";
    public static final String KEYBOARD_KEYMAP_CFG_FILE = "/data/system/keyboard/keymap";
    public static final String KEYBOARD_KEYMAP_SPACEPOWER_KEY = "keyboard_keymap_spacepower";
    public static final String[] KEYBOARD_KEYMAP_SPACEPOWER_TEXT = {
        "48:0039:0074"
    };
    public static final String KEYBOARD_KEYMAP_FNKEYS_KEY = "keyboard_keymap_fnkeys";
    public static final String[] KEYBOARD_KEYMAP_FNKEYS_TEXT = {
        "57:0002:003b",
        "60:0003:003c",
        "8:0004:003d",
        "61:0005:003e",
        "55:0006:003f",
        "19:0007:0040",
        "3:0008:0041",
        "53:0009:0042",
        "13:000a:0043",
        "45:000b:0044",
        "29:000c:0057",
        "37:000d:0058"
    };
    public static final String KEYBOARD_KEYMAP_SYS_FILE = "/sys/devices/soc/c17a000.i2c/i2c-6/6-0058/keymap";

    // Keyboard poll interval
    public static final String KEYBOARD_FASTPOLL_KEY = "keyboard_fastpoll";
    public static final int    KEYBOARD_POLL_INTERVAL_DEFAULT = 40;
    public static final int    KEYBOARD_POLL_INTERVAL_FAST = 20;
    public static final int    KEYBOARD_POLL_INTERVAL_SLOW = 40;
    public static final String KEYBOARD_POLL_INTERVAL_SYS_FILE = "/sys/devices/soc/c17a000.i2c/i2c-6/6-0058/poll_interval";

    // Touch screen margin
    public static final String TOUCHSCREEN_MARGIN_KEY = "touchscreen_margin";
    public static final int    TOUCHSCREEN_MARGIN_STEP = 16;
    public static final String TOUCHSCREEN_MARGIN_SYS_FILE = "/sys/goodix/margin_x";
}
