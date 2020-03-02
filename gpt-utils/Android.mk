LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE_TAGS := optional
LOCAL_HEADER_LIBRARIES := generated_kernel_headers
LOCAL_SRC_FILES := gpt-utils.cpp sparse_crc32.cpp
LOCAL_MODULE := libgptutils
include $(BUILD_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE_TAGS := optional
LOCAL_HEADER_LIBRARIES := generated_kernel_headers
LOCAL_SRC_FILES := gpt-utils.cpp sparse_crc32.cpp
LOCAL_SHARED_LIBRARIES += liblog libcutils
LOCAL_EXPORT_HEADER_LIBRARY_HEADERS := libgptutils_headers
LOCAL_MODULE := libgptutils
LOCAL_VENDOR_MODULE := true
include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libgptutils_headers
LOCAL_EXPORT_C_INCLUDE_DIRS := $(LOCAL_PATH)
include $(BUILD_HEADER_LIBRARY)
