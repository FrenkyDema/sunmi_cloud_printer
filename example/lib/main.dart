import 'dart:async';

import 'package:flutter/material.dart';
import 'package:lepsi_sunmi_cloud_printer/sunmi_cloud_printer.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    await SunmiCloudPrinter.setNetPrinter("192.168.2.93");
    await SunmiCloudPrinter.connect();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
          appBar: AppBar(
            title: const Text('Plugin example app'),
          ),
          body: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              const Spacer(),
              ElevatedButton(onPressed: () async => await SunmiCloudPrinter.connect(), child: const Text("Connect")),
              ElevatedButton(onPressed: () async => await SunmiCloudPrinter.initPrinter(), child: const Text("Init printer")),
              ElevatedButton(
                  onPressed: () async => {
                        await SunmiCloudPrinter.printText("Connection Test !"),
                        await SunmiCloudPrinter.lineWrap(3),
                      },
                  child: const Text("Print connection test")),
              ElevatedButton(
                  onPressed: () async => {
                        await SunmiCloudPrinter.printQRCode('https://canteen.risto.cloud/api/company/service_status', size: 8),
                        await SunmiCloudPrinter.lineWrap(3),
                      },
                  child: const Text("Print Qrcode test")),
              const Spacer(),
            ],
          )),
    );
  }
}
