import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'sunmi_cloud_printer_platform_interface.dart';

/// An implementation of [SunmiCloudPrinterPlatform] that uses method channels.
class MethodChannelSunmiCloudPrinter extends SunmiCloudPrinterPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('sunmi_cloud_printer');

  @override
  Future<String?> getPlatformVersion() async {
    final version =
        await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  @override
  Future<void> printConnectionTest() async {
    await methodChannel.invokeMethod("TEST");
  }
}
