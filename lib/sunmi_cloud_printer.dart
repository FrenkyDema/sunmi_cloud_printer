
import 'sunmi_cloud_printer_platform_interface.dart';

class SunmiCloudPrinter {
  Future<String?> getPlatformVersion() {
    return SunmiCloudPrinterPlatform.instance.getPlatformVersion();
  }
}
