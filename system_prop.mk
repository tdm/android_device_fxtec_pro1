# Audio
PRODUCT_SYSTEM_DEFAULT_PROPERTIES += \
    audio.deep_buffer.media=true \
    audio.offload.video=true \
    audio.offload.min.duration.secs=30

# Gboard
PRODUCT_SYSTEM_DEFAULT_PROPERTIES += \
    ro.com.google.ime.kb_pad_port_l=10 \
    ro.com.google.ime.kb_pad_port_r=10 \
    ro.com.google.ime.key_border=true \
    ro.com.google.ime.bs_theme=true

# Graphics
PRODUCT_SYSTEM_DEFAULT_PROPERTIES += \
    debug.hwui.use_buffer_age=false \
    debug.sf.enable_hwc_vds=1 \
    debug.sf.latch_unsignaled=1 \
    persist.debug.wfd.enable=1 \
    persist.hwc.enable_vds=1

# RIL
PRODUCT_SYSTEM_DEFAULT_PROPERTIES += \
    ril.subscription.types=NV,RUIM \

# Voice assistant
PRODUCT_SYSTEM_DEFAULT_PROPERTIES += \
    ro.opa.eligible_device=true
