TARGETS = console-setup resolvconf mountkernfs.sh ufw hostname.sh screen-cleanup plymouth-log apparmor mt-st udev mountdevsubfs.sh procps hwclock.sh cryptdisks cryptdisks-early open-iscsi networking iscsid urandom checkroot.sh lvm2 checkfs.sh mountnfs.sh kmod mountall.sh mountnfs-bootclean.sh bootmisc.sh checkroot-bootclean.sh mountall-bootclean.sh
INTERACTIVE = console-setup udev cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
mountdevsubfs.sh: mountkernfs.sh udev
procps: mountkernfs.sh udev
hwclock.sh: mountdevsubfs.sh
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
open-iscsi: networking iscsid
networking: resolvconf mountkernfs.sh urandom procps
iscsid: networking
urandom: hwclock.sh
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh
lvm2: cryptdisks-early mountdevsubfs.sh udev
checkfs.sh: cryptdisks lvm2 checkroot.sh
mountnfs.sh: networking
kmod: checkroot.sh
mountall.sh: lvm2 checkfs.sh checkroot-bootclean.sh
mountnfs-bootclean.sh: mountnfs.sh
bootmisc.sh: mountnfs-bootclean.sh checkroot-bootclean.sh udev mountall-bootclean.sh
checkroot-bootclean.sh: checkroot.sh
mountall-bootclean.sh: mountall.sh
