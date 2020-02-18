#!/vendor/bin/sh

cfgdir="/persist/data/keyboard"
sysdir="/sys/bus/i2c/devices/6-0058"

logmsg() {
	/vendor/bin/log -t "keyboard" "$@"
}

if [ ! -d "$sysdir" ]; then
	logmsg "$sysdir does not exist"
	exit 0
fi
if [ ! -d "$cfgdir" ]; then
	logmsg "$cfgdir does not exist"
	exit 0
fi

for f in "layout" "keymap" "poll_interval"; do
	if [ -f "$cfgdir/$f" ]; then
		logmsg "writing $f"
		cat "$cfgdir/$f" > "$sysdir/$f" 2>/dev/null
		if [ $? -ne 0 ]; then
			logmsg "failed to write $f"
		fi
	fi
done
