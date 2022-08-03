
import 'package:flutter/services.dart';


class SunmiCloudPrinter {
  static const MethodChannel _channel = MethodChannel('sunmi_cloud_printer');



  static Future<bool?> initPrinter() async {
    final bool? status = await _channel.invokeMethod('INIT_PRINTER');
    return status;
  }

}
