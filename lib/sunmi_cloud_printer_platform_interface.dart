import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'sunmi_cloud_printer_method_channel.dart';

abstract class SunmiCloudPrinterPlatform extends PlatformInterface {
  /// Constructs a SunmiCloudPrinterPlatform.
  SunmiCloudPrinterPlatform() : super(token: _token);

  static final Object _token = Object();

  static SunmiCloudPrinterPlatform _instance = MethodChannelSunmiCloudPrinter();

  /// The default instance of [SunmiCloudPrinterPlatform] to use.
  ///
  /// Defaults to [MethodChannelSunmiCloudPrinter].
  static SunmiCloudPrinterPlatform get instance => _instance;
  
  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [SunmiCloudPrinterPlatform] when
  /// they register themselves.
  static set instance(SunmiCloudPrinterPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
