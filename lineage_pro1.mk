#
# Copyright (C) 2017 The LineageOS Open Source Project
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

# Inherit from those products. Most specific first.
$(call inherit-product, $(SRC_TARGET_DIR)/product/core_64_bit.mk)
$(call inherit-product, $(SRC_TARGET_DIR)/product/full_base_telephony.mk)

# Inherit from pro1 device
$(call inherit-product, device/fxtec/pro1/device.mk)

# Inherit some common Lineage stuff.
$(call inherit-product, vendor/lineage/config/common_full_phone.mk)

PRODUCT_NAME := lineage_pro1
PRODUCT_DEVICE := pro1
PRODUCT_MANUFACTURER := Fxtec
PRODUCT_BRAND := Fxtec
PRODUCT_MODEL := QX1000

PRODUCT_GMS_CLIENTID_BASE := android-fxtec

PRODUCT_BUILD_PROP_OVERRIDES += \
    TARGET_DEVICE=QX1000 \
    PRODUCT_NAME=QX1000_EEA

PRODUCT_BUILD_PROP_OVERRIDES += \
    PRIVATE_BUILD_DESC="QX1000-user 9 PKQ1.190723.001 20200620223710 release-keys"

BUILD_FINGERPRINT := Fxtec/QX1000_EEA/QX1000:9/PKQ1.190723.001/05500.20Q202:user/release-keys

TARGET_VENDOR := fxtec
