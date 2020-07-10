#!/bin/bash
#
# Copyright (C) 2016 The CyanogenMod Project
# Copyright (C) 2017 The LineageOS Project
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

set -e

export DEVICE=pro1
export VENDOR=fxtec

export DEVICE_BRINGUP_YEAR=2019

# Load extract_utils and do some sanity checks
MY_DIR="${BASH_SOURCE%/*}"
if [[ ! -d "$MY_DIR" ]]; then MY_DIR="$PWD"; fi

LINEAGE_ROOT="$MY_DIR"/../../..

HELPER="$LINEAGE_ROOT"/vendor/lineage/build/tools/extract_utils.sh
if [ ! -f "$HELPER" ]; then
    echo "Unable to find helper script at $HELPER"
    exit 1
fi
. "$HELPER"

while [ "$1" != "" ]; do
    case $1 in
        -n | --no-cleanup )     CLEAN_VENDOR=false
                                ;;
        -s | --section )        shift
                                SECTION=$1
                                CLEAN_VENDOR=false
                                ;;
        * )                     SRC=$1
                                ;;
    esac
    shift
done

if [ -z "$SRC" ]; then
    SRC=adb
fi

# Initialize the helper
setup_vendor "$DEVICE" "$VENDOR" "$LINEAGE_ROOT" true "$CLEAN_VENDOR"

extract "$MY_DIR"/proprietary-files-qc.txt "$SRC" "$SECTION"
extract "$MY_DIR"/proprietary-files-qc-perf.txt "$SRC" "$SECTION"
extract "$MY_DIR"/proprietary-files.txt "$SRC" "$SECTION"

BLOB_ROOT="$LINEAGE_ROOT"/vendor/"$VENDOR"/"$DEVICE"/proprietary

#
# Correct android.hidl.manager@1.0-java jar name
#
sed -i "s|name=\"android.hidl.manager-V1.0-java|name=\"android.hidl.manager@1.0-java|g" \
    "$BLOB_ROOT"/vendor/etc/permissions/qti_libpermissions.xml

# qseecomd linkage for recovery
RECOVERY_QSEECOMD="$BLOB_ROOT/recovery/root/sbin/qseecomd"
if [ -f "$RECOVERY_QSEECOMD" ]; then
    sed 's@/system/bin/linker64@/sbin/linker64\x0\x0\x0\x0\x0\x0@' \
        < "$RECOVERY_QSEECOMD" \
        > "$RECOVERY_QSEECOMD.tmp"
    mv "$RECOVERY_QSEECOMD.tmp" "$RECOVERY_QSEECOMD"
fi

"$MY_DIR"/setup-makefiles.sh
