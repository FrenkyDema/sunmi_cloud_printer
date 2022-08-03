import 'package:flutter_test/flutter_test.dart';
import 'package:sunmi_cloud_printer/sunmi_cloud_printer.dart';
import 'package:sunmi_cloud_printer/sunmi_cloud_printer_platform_interface.dart';
import 'package:sunmi_cloud_printer/sunmi_cloud_printer_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockSunmiCloudPrinterPlatform 
    with MockPlatformInterfaceMixin
    implements SunmiCloudPrinterPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');

  @override
  Future<void> printConnectionTest() {
    // TODO: implement printConnectionTest
    throw UnimplementedError();
  }
}

void main() {
  final SunmiCloudPrinterPlatform initialPlatform = SunmiCloudPrinterPlatform.instance;

  test('$MethodChannelSunmiCloudPrinter is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelSunmiCloudPrinter>());
  });

}
