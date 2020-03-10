#
# Copyright (C) 2018 The LineageOS Project
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

# AANC
PRODUCT_PROPERTY_OVERRIDES += \
    persist.vendor.audio.aanc.enable=true

# Audio
PRODUCT_PROPERTY_OVERRIDES += \
    af.fast_track_multiplier=2 \
    vendor.audio_hal.period_size=192 \
    audio.offload.video=true \
    audio.offload.pcm.16bit.enable=true \
    audio.offload.pcm.24bit.enable=true \
    audio.deep_buffer.media=true \
    audio.cts.media=false \
    audio.offload.min.duration.secs=30 \
    persist.dirac.acs.controller=qem \
    ro.dirac.acs.storeSettings=1 \
    ro.dirac.ignore_error=1 \
    tunnel.audio.encode=true

# Audio new
PRODUCT_PROPERTY_OVERRIDES += \
    vendor.audio_hal.period_size=192 \
    ro.vendor.audio.sdk.fluencetype=none \
    persist.vendor.audio.fluence.voicecall=true \
    persist.vendor.audio.fluence.voicerec=false \
    persist.vendor.audio.fluence.speaker=true \
    persist.vendor.audio.hw.binder.size_kbyte=1024 \
    vendor.audio.tunnel.encode=false \
    persist.vendor.audio.ras.enabled=false \
    vendor.audio.offload.buffer.size.kb=32 \
    vendor.audio.offload.track.enable=true \
    vendor.voice.path.for.pcm.voip=true \
    vendor.audio.offload.multiaac.enable=true \
    vendor.audio.offload.pstimeout.secs=3 \
    vendor.audio.dolby.ds2.enabled=false \
    vendor.audio.dolby.ds2.hardbypass=false \
    vendor.audio.offload.multiple.enabled=true \
    vendor.audio.offload.passthrough=false \
    ro.vendor.audio.sdk.ssr=false \
    vendor.audio.offload.gapless.enabled=true \
    vendor.audio.safx.pbe.enabled=true \
    vendor.audio.parser.ip.buffer.size=262144 \
    vendor.audio.flac.sw.decoder.24bit=true \
    persist.vendor.bt.a2dp_offload_cap=false \
    vendor.audio.use.sw.alac.decoder=true \
    vendor.audio.use.sw.ape.decoder=true \
    vendor.audio.hw.aac.encoder=true \
    vendor.fm.a2dp.conc.disabled=true \
    vendor.audio.noisy.broadcast.delay=600 \
    ro.config.media_vol_steps=25 \
    ro.config.vc_call_vol_steps=11 \
    ro.af.client_heap_size_kbyte=7168

# Bluetooth
PRODUCT_PROPERTY_OVERRIDES += \
    persist.bt.a2dp_offload_cap=false \
    vendor.bluetooth.soc=cherokee \
    vendor.qcom.bluetooth.soc=cherokee

# Cabl
PRODUCT_PROPERTY_OVERRIDES += \
    ro.qualcomm.cabl=0

# Camera
PRODUCT_PROPERTY_OVERRIDES += \
    persist.sys.camerakey.action=yes \
    persist.vendor.camera.HAL3.enabled=1

# Core control
PRODUCT_PROPERTY_OVERRIDES += \
    ro.vendor.qti.core_ctl_min_cpu=2 \
    ro.vendor.qti.core_ctl_max_cpu=4

# DRM
PRODUCT_PROPERTY_OVERRIDES += \
    drm.service.enabled=true

# FRP
PRODUCT_PROPERTY_OVERRIDES += \
    ro.frp.pst=/dev/block/bootdevice/by-name/frp

# Graphics
PRODUCT_PROPERTY_OVERRIDES += \
    ro.sf.lcd_density=420 \
    ro.opengles.version=196610 \
    ro.sf.hwc_set_default_colormode=true \
    debug.sf.hw=1 \
    debug.sf.enable_hwc_vds=1 \
    debug.sf.latch_unsignaled=1 \
    dev.pm.dyn_samplingrate=1 \
    persist.demo.hdmirotationlock=false \
    persist.debug.wfd.enable=1 \
    persist.hwc.enable_vds=1 \
    persist.sys.force_sw_gles=0 \
    persist.sys.wfd.virtual=0 \
    vendor.gralloc.enable_fb_ubwc=1 \
    vendor.gralloc.disable_wb_ubwc=1 \
    vendor.display.disable_partial_split=1 \
    vendor.display.disable_rotator_downscale=1 \
    vendor.display.disable_skip_validate=1 \
    vendor.display.enable_default_color_mode=1 \
    vendor.display.perf_hint_window=50 \
    debug.hwui.use_buffer_age=false
    ro.com.google.ime.kb_pad_port_l=10
    ro.com.google.ime.kb_pad_port_r=10
    ro.com.google.ime.key_border=true
    ro.com.google.ime.bs_theme=true

# Media
PRODUCT_PROPERTY_OVERRIDES += \
    media.stagefright.enable-player=true \
    media.stagefright.enable-http=true \
    media.stagefright.enable-aac=true \
    media.stagefright.enable-qcp=true \
    media.stagefright.enable-scan=true \
    mmp.enable.3g2=true \
    media.aac_51_output_enabled=true \
    mm.enable.smoothstreaming=true \
    persist.mm.enable.prefetch=true \
    vidc.enc.dcvs.extra-buff-count=2 \
    persist.media.treble_omx=true

# NFC
PRODUCT_PROPERTY_OVERRIDES += \
    ro.hardware.nfc_nci=nqx.default

# Perf
PRODUCT_PROPERTY_OVERRIDES += \
    ro.sys.fw.bg_apps_limit=60 \
    ro.vendor.extension_library=libqti-perfd-client.so \
    ro.am.reschedule_service=true \
    sched.colocate.enable=1 \
    sys.games.gt.prof=1

# QCOM
PRODUCT_PROPERTY_OVERRIDES += \
    persist.vendor.qcomsysd.enabled=1

# RIL
PRODUCT_PROPERTY_OVERRIDES += \
    DEVICE_PROVISIONED=1 \
    rild.libpath=/vendor/lib64/libril-qc-qmi-1.so \
    vendor.rild.libpath=/vendor/lib64/libril-qc-qmi-1.so \
    ril.subscription.types=NV,RUIM \
    ro.telephony.default_network=22,20 \
    telephony.lteOnCdmaDevice=1 \
    keyguard.no_require_sim=true \
    ro.com.android.dataroaming=true \
    persist.sys.oem_smooth=1 \
    persist.radio.multisim.config=dsds \
    persist.vendor.radio.apm_sim_not_pwdn=1 \
    persist.vendor.radio.custom_ecc=1 \
    persist.vendor.radio.rat_on=combine \
    persist.vendor.radio.sib16_support=1

# VNDK
PRODUCT_PROPERTY_OVERRIDES += \
    ro.vendor.vndk.version=28
