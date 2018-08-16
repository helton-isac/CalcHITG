#!/usr/bin/env bash
# use curl to download a keystore from $SIGNING_KEY_URI, if set,
# to the path/filename set in $KEYSTORE.
if [[ ${KEYSTORE} && ${SIGNING_KEY_URI} ]]
then
    echo "Keystore detected - downloading..."
    curl -L -o ${KEYSTORE} ${SIGNING_KEY_URI}
else
    echo "Keystore uri not set.  .APK artifact will not be signed."
fi