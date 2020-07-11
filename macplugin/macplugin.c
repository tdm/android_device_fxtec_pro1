/*
 * Copyright 2020, The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <errno.h>

#include <time.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#define LOG_TAG "macplugin"
#include <log/log.h>

/*
 * Yes, I know.  But this is exactly how stock generates it...
 */

static const char WLAN_BDADDR_PATH[] = "/persist/wlan_mac.bin";

static int
set_wlan_mac(void)
{
    int fd;
    uint32_t addr;
    char buf[80];
    char *p;

    fd = open(WLAN_BDADDR_PATH, O_WRONLY | O_CREAT | O_EXCL, 0600);
    if (fd < 0) {
        if (errno != EEXIST) {
            return -1;
        }
        return 0;
    }
    ALOGI("Writing WLAN MAC file\n");
    addr = rand();
    p = buf;
    p += sprintf(p, "Intf0MacAddress=02AA%08X\n", addr);
    p += sprintf(p, "Intf1MacAddress=02CC%08X\n", addr);
    p += sprintf(p, "END\n");
    write(fd, buf, p - buf);
    close(fd);

    return 0;
}

int
main(void)
{
    srand(time(NULL));
    if (set_wlan_mac() != 0) {
       ALOGE("Failed to set WLAN MAC\n");
    }

    return 0;
}
