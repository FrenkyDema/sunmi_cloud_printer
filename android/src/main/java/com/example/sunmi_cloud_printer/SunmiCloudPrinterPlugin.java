package com.example.sunmi_cloud_printer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;

import com.sunmi.externalprinterlibrary.api.PrinterException;

import org.json.JSONArray;
import org.json.JSONObject;

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
        Log.wtf("Method:", call.method);
        switch (call.method) {

            case "INIT_PRINTER":
                try {
                    sunmiCloudPrinterMethod.printerInit();
                } catch (PrinterException ignored) {
                    result.success(false);
                    break;
                }
                result.success(true);
                break;

            case "GET_UPDATE_PRINTER":
                int status_code = -1;
                try {
                    status_code = sunmiCloudPrinterMethod.getPrinterStatus();
                } catch (PrinterException ignored) {
                }

                String status_msg = "";

                // response printer status
                switch (status_code) {
                    case 0:
                        status_msg = "ERROR";
                        break;
                    case 1:
                        status_msg = "NORMAL";
                        break;
                    case 2:
                        status_msg = "ABNORMAL_COMMUNICATION";
                        break;
                    case 3:
                        status_msg = "OUT_OF_PAPER";
                        break;
                    case 4:
                        status_msg = "PREPARING";
                        break;
                    case 5:
                        status_msg = "OVERHEATED";
                        break;
                    case 6:
                        status_msg = "OPEN_THE_LID";
                        break;
                    case 7:
                        status_msg = "PAPER_CUTTER_ABNORMAL";
                        break;
                    case 8:
                        status_msg = "PAPER_CUTTER_RECOVERED";
                        break;
                    case 9:
                        status_msg = "NO_BLACK_MARK";
                        break;
                    case 505:
                        status_msg = "NO_PRINTER_DETECTED";
                        break;
                    case 507:
                        status_msg = "FAILED_TO_UPGRADE_FIRMWARE";
                        break;
                    default:
                        status_msg = "EXCEPTION";
                }

                result.success(status_msg);
                break;


            case "PRINT_TEXT":
                String text = call.argument("text");
                try {
                    sunmiCloudPrinterMethod.printText(text);
                } catch (PrinterException ignored) {
                    result.success(false);
                    break;
                }
                result.success(true);
                break;

            case "LINE_WRAP":
                int nLine = call.argument("line");
                try {
                    sunmiCloudPrinterMethod.lineWrap(nLine);
                } catch (PrinterException ignored) {
                    result.success(false);
                    break;
                }
                result.success(true);
                break;

            case "PRINT_BARCODE":
                String barCodeData = call.argument("data");
                int barcodeType = call.argument("barcodeType");
                int textPosition = call.argument("textPosition");
                int width = call.argument("width");
                int height = call.argument("height");
                try {
                    sunmiCloudPrinterMethod.printBarCode(
                            barCodeData,
                            barcodeType,
                            width,
                            height,
                            textPosition
                    );
                    sunmiCloudPrinterMethod.lineWrap(1);
                } catch (PrinterException ignored) {
                    result.success(false);
                    break;
                }
                result.success(true);
                break;


            case "PRINT_QRCODE":
                String data = call.argument("data");
                int modulesize = call.argument("modulesize");
                int errorlevel = call.argument("errorlevel");
                try {
                    sunmiCloudPrinterMethod.printQrCode(data, modulesize, errorlevel);
                } catch (PrinterException ignored) {
                    result.success(false);
                    break;
                }
                result.success(true);
                break;

            case "RAW_DATA":
                try {
                    sunmiCloudPrinterMethod.sendRawData((byte[]) call.argument("data"));
                } catch (PrinterException ignored) {
                    result.success(false);
                    break;
                }
                result.success(true);
                break;


            case "FONT_SIZE":
                int horiZoom = call.argument("hori");
                int veriZoom = call.argument("veri");
                try {
                    sunmiCloudPrinterMethod.setFontZoom(horiZoom, veriZoom);
                } catch (PrinterException ignored) {
                    result.success(false);
                    break;
                }
                result.success(true);
                break;

            case "SET_ALIGNMENT":
                int alignment = call.argument("alignment");
                try {
                    sunmiCloudPrinterMethod.setAlignMode(alignment);
                } catch (PrinterException ignored) {
                    result.success(false);
                    break;
                }
                result.success(true);
                break;

            case "PRINT_IMAGE":
                byte[] bytes = call.argument("bitmap");
                int mode = call.argument("mode");
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                try {
                    sunmiCloudPrinterMethod.printBitmap(bitmap, mode);
                } catch (PrinterException ignored) {
                    result.success(false);
                    break;
                }
                result.success(true);
                break;

            case "ENTER_PRINTER_BUFFER":
                Boolean clearEnter = call.argument("clearEnter");
                try {
                    sunmiCloudPrinterMethod.startTransBuffer(clearEnter);
                } catch (PrinterException ignored) {
                    result.success(false);
                    break;
                }
                result.success(true);
                break;

            case "EXIT_PRINTER_BUFFER":
                try {
                    sunmiCloudPrinterMethod.endTransBuffer();
                } catch (PrinterException ignored) {
                    result.success(false);
                    break;
                }
                result.success(true);
                break;

            case "PRINT_ROW":
                String colsStr = call.argument("cols");

                try {
                    JSONArray cols = new JSONArray(colsStr);
                    String[] colsText = new String[cols.length()];
                    int[] colsWidth = new int[cols.length()];
                    int[] colsAlign = new int[cols.length()];
                    for (int i = 0; i < cols.length(); i++) {
                        JSONObject col = cols.getJSONObject(i);
                        String textColumn = col.getString("text");
                        int widthColumn = col.getInt("width");
                        int alignColumn = col.getInt("align");
                        colsText[i] = textColumn;
                        colsWidth[i] = widthColumn;
                        colsAlign[i] = alignColumn;
                    }

                    sunmiCloudPrinterMethod.printColumnsText(colsText, colsWidth, colsAlign);
                } catch (PrinterException ignored) {
                    result.success(false);
                    break;
                } catch (Exception err) {
                    Log.d("SunmiPrinter", err.getMessage());
                }
                result.success(true);
                break;


            default: {
                result.notImplemented();
                break;
            }
        }

    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }
}
