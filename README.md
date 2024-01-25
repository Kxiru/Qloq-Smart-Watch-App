## QLOQ

### Demo Video

Link to the video of Qloq's Demonstration: ['Qloq FYP Demonstration Video'](https://www.youtube.com/watch?v=bjQb8Nmwj_I)

### Installation Instructions

Please find the android APK for Qloq (Qloq.apk) available for download in my Gitlab repository within the Builds.zip file. Once you have downloaded the apk, You have two options:

> **Option 1**: Download / Sideload this apk to a WearOS smartwatch and install it.
> (Instructions taken from https://www.guidingtech.com/how-to-install-apks-on-wear-os-smartwatches/)

1.  Preparing your watch:
    1. Enable Developer Options / Tools on your Watch by going to your system settings and tapping on your Build number 7 times.
    2. Navigate to Developer Options
    3. Enable ADB Debugging
    4. Enable Debug over Wifi
    5. In the Debug Over Wifi option that appears, note the IP address and port number.
2.  Installing the APK via ADB
    1. Download the APK from Github to a computer or laptop. Copy it to the ‘platform-tools’ folder where ADB is installed.
    2. In a terminal window, type “./adb connect < IP address from earlier >"
    3. If all goes well, you will see a prompt on your watch to allow the ADB connection, select OK
    4. The Terminal window will also show whether you are connected to the watch.
    5. Use the command ./adb push <filename.apk> /sdcard/ to transfer the APK to the watch. The file will be pushed to your watch as confirmed by the terminal result.
    6. It’s now time to install the app. Type this command, where the filename.apk should be the filename of the APK you want to install: “./adb -e install <filename.apk>”
    7. A Success message will now be displayed on the terminal indicating that the app has been installed on your watch.
    8. Head back to Developer Options on your watch and disable ADB debugging so as not to drain battery.
    9. If all is successful, open the app drawer on your watch and find Qloq!

> **Option 2**: Run the apk on an emulator.

1.  Download Android Studio
2.  Set up a new virtual device. Recommendation: Wear OS Large Round API 30 (Watch)
3.  Download the R Image and wait for the setup and installation.
4.  Download the .apk file from Gitlab and drag and drop the .apk file into the emulator. It will automatically install.
5.  Go to your app list and find Qloq!
