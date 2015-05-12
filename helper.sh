#! /bin/sh

DEVICE="/dev/disk/by-uuid/8F0D-C831"

[[ -e $DEVICE ]] || { echo "eReader not connected"; exit 1; }

MOUNT=$(udisksctl mount -b $DEVICE | gawk '{gsub(/.$/,""); print $4}')
echo "eReader mounted at $MOUNT"

NAME="$(date -I).txt"
sbt --error "run $MOUNT/german/$NAME"

udisksctl unmount -b $DEVICE
