# sunmi_cloud_printer

A package for 80mm Kitchen Cloud Printer - SUNMI

## Important

**THIS PACKAGE WILL WORK ONLY IN ANDROID!**

---

## Class Name

```dart
SunmiCloudPrinter
```

## Example

```dart
Future<void> initPlatformState() async {
    await SunmiCloudPrinter.setNetPrinter("IP_ADDRESS");
    await SunmiCloudPrinter.connect();
}

@override
Widget build(BuildContext context) {
    return ElevatedButton(
        onPressed: () async => {
            await SunmiCloudPrinter.printText("Connection Test !"),
            await SunmiCloudPrinter.lineWrap(3),
        },
        child: const Text("Print connection test")
    );
```

## Installation

```bash
flutter pub add sunmi_cloud_printer
```

## What this package do

- [x] Write some text
- [x] Change font size
- [x] Jump (n) lines
- [x] Draw a divisor line
- [x] Bold mode on/off
- [x] Print all types of Barcodes
- [x] Print Qrcodes with custom width and error-level
- [x] Print image from asset or from web
- [x] Print rows like receipt with custom width and alignment
- [ ] Able to combine with some esc/pos code that you already have!
- [x] Cut paper - Dedicated method just to cut the line
- [ ] Printer serial no - Get the serial number of the printer
- [x] Printer version - Get the printer's version

---

## Tested Devices

```bash
Sunmi NT311
```

Inspired from [sunmi_printer_plus](https://github.com/brasizza/sunmi_printer)
