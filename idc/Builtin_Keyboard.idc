# Copyright (C) 2015 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

#
# F(x)tec Pro1 input device configuration file for the built-in keyboard
#

# Pretend the keyboard is external so we can wake the device using key events
device.internal = 0

# The keyboard is the default built-in keyboard so it should be assigned
# an id of 0.
keyboard.builtIn = 1

# The keyboard is always used in landscape orientation, so don't rotate
# the arrow keys to compensate for orientation changes
keyboard.orientationAware = 0