import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:sunmi_cloud_printer/sunmi_cloud_printer_method_channel.dart';

void main() {
  MethodChannelSunmiCloudPrinter platform = MethodChannelSunmiCloudPrinter();
  const MethodChannel channel = MethodChannel('sunmi_cloud_printer');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await platform.getPlatformVersion(), '42');
  });
}
