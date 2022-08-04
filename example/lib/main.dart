import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:sunmi_cloud_printer/sunmi_cloud_printer.dart';

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
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
          appBar: AppBar(
            title: const Text('Plugin example app'),
          ),
          body: Column(
            children: [
              const Spacer(),
              ElevatedButton(
                  onPressed: () => SunmiCloudPrinter.initPrinter(),
                  child: const Text("Init printer")),
              ElevatedButton(
                  onPressed: () => SunmiCloudPrinter.initPrinter(),
                  child: const Text("Print connection test")),
              const Spacer(),
            ],
          )),
    );
  }
}
