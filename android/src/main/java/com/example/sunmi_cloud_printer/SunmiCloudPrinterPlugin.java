package com.example.sunmi_cloud_printer;

import androidx.annotation.NonNull;

import com.sunmi.externalprinterlibrary.api.PrinterException;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/**
 * SunmiCloudPrinterPlugin
 */
public class SunmiCloudPrinterPlugin implements FlutterPlugin, MethodCallHandler {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private static SunmiCloudPrinterMethod sunmiCloudPrinterMethod;
    private MethodChannel channel;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "sunmi_cloud_printer");
        sunmiCloudPrinterMethod =
                new SunmiCloudPrinterMethod(flutterPluginBinding.getApplicationContext());
        channel.setMethodCallHandler(this);
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        switch (call.method) {
            
            case "PRINT_TEXT":
                String text = call.argument("text");
                try {
                    sunmiCloudPrinterMethod.printerText(text);
                }
                catch (PrinterException ignored){
                    result.success(false);
                    break;
                }
                result.success(true);
                break;

            case "LINE_WRAP":
                int nLine = call.argument("line");
                try {
                    sunmiCloudPrinterMethod.printerText(nLine);
                }
                catch (PrinterException ignored){
                    result.success(false);
                    break;
                }
                result.success(true);
                break;

            case "PRINT_QRCODE":
                String data = call.argument("data");
                int modulesize = call.argument("modulesize");
                int errorlevel = call.argument("errorlevel");
                sunmiPrinterMethod.printQRCode(data, modulesize, errorlevel);
                result.success(true);
                break;


            case "PRINT_QRCODE":
                String data = call.argument("data");
                int modulesize = call.argument("modulesize");
                int errorlevel = call.argument("errorlevel");
                try {
                    sunmiCloudPrinterMethod.printQrCode(data, modulesize, errorlevel);
                }
                catch (PrinterException ignored){
                    result.success(false);
                    break;
                }
                result.success(true);
                break;

            default: {
            }
        }

    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }
}
