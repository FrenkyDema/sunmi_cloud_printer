package com.example.lepsi_sunmi_cloud_printer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The Sunmi cloud printer plugin.
 */
public class SunmiCloudPrinterPlugin implements FlutterPlugin, MethodCallHandler {
    private SunmiCloudPrinterMethod sunmiCloudPrinterMethod;
    private MethodChannel channel;

    /**
     * Handle attach to engine event.
     *
     * @param flutterPluginBinding the flutter plugin binding
     */
    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "lepsi_sunmi_cloud_printer");
        sunmiCloudPrinterMethod =
                new SunmiCloudPrinterMethod(flutterPluginBinding.getApplicationContext());
        channel.setMethodCallHandler(this);
    }

    /**
     * Handle method calls event.
     *
     * @param call   the method call
     * @param result the result from the call
     */
    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        Log.wtf("Method:", call.method);
        switch (call.method) {
            case "SET_NET_PRINTER" -> {
                String ip = call.argument("ip");
                try {
                    sunmiCloudPrinterMethod.setNetPrinter(ip);
                } catch (Exception ignored) {
                    result.success(false);
                    break;
                }
                result.success(true);
            }
            case "CONNECT" -> {
                try {
                    sunmiCloudPrinterMethod.connect();
                } catch (Exception ignored) {
                    result.success(false);
                    break;
                }
                result.success(true);
            }
            /// Added by LepsiCity : release method
            case "RELEASE" -> {
                try {
                    sunmiCloudPrinterMethod.release();
                } catch (Exception ignored) {
                    result.success(false);
                    break;
                }
                result.success(true);
            }
            case "INIT_PRINTER" -> {
                try {
                    sunmiCloudPrinterMethod.printerInit();
                } catch (Exception ignored) {
                    result.success(false);
                    break;
                }
                result.success(true);
            }
            case "GET_UPDATE_PRINTER" -> {
                int status_code = -1;
                try {
                    status_code = sunmiCloudPrinterMethod.getPrinterStatus();
                } catch (Exception ignored) {
                }
                String status_msg = switch (status_code) {
                    case 0 -> "ERROR";
                    case 1 -> "NORMAL";
                    case 2 -> "ABNORMAL_COMMUNICATION";
                    case 3 -> "OUT_OF_PAPER";
                    case 4 -> "PREPARING";
                    case 5 -> "OVERHEATED";
                    case 6 -> "OPEN_THE_LID";
                    case 7 -> "PAPER_CUTTER_ABNORMAL";
                    case 8 -> "PAPER_CUTTER_RECOVERED";
                    case 9 -> "NO_BLACK_MARK";
                    case 505 -> "NO_PRINTER_DETECTED";
                    case 507 -> "FAILED_TO_UPGRADE_FIRMWARE";
                    default -> "EXCEPTION";
                };

                // response printer status
                result.success(status_msg);
            }
            case "PRINT_TEXT" -> {
                String text = call.argument("text");
                try {
                    sunmiCloudPrinterMethod.printText(text);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    result.success(false);
                    break;
                }
                result.success(true);
            }
            case "LINE_WRAP" -> {
                //noinspection ConstantConditions
                int nLine = call.argument("lines");
                try {
                    sunmiCloudPrinterMethod.lineWrap(nLine);
                } catch (Exception ignored) {
                    result.success(false);
                    break;
                }
                result.success(true);
            }
            case "PRINT_BARCODE" -> {
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
                } catch (Exception ignored) {
                    result.success(false);
                    break;
                }
                result.success(true);
            }
            case "PRINT_QRCODE" -> {
                String data = call.argument("data");
                int modulesize = call.argument("modulesize");
                int errorlevel = call.argument("errorlevel");
                try {
                    sunmiCloudPrinterMethod.printQrCode(data, modulesize, errorlevel);
                } catch (Exception ignored) {
                    result.success(false);
                    break;
                }
                result.success(true);
            }
            case "RAW_DATA" -> {
                try {
                    sunmiCloudPrinterMethod.sendRawData(call.argument("data"));
                } catch (Exception ignored) {
                    result.success(false);
                    break;
                }
                result.success(true);
            }
            case "FONT_SIZE" -> {
                int horizontalZoom = call.argument("hori");
                int verticalZoom = call.argument("veri");
                try {
                    sunmiCloudPrinterMethod.setFontZoom(horizontalZoom, verticalZoom);
                } catch (Exception ignored) {
                    result.success(false);
                    break;
                }
                result.success(true);
            }
            case "SET_ALIGNMENT" -> {
                int alignment = call.argument("alignment");
                try {
                    sunmiCloudPrinterMethod.setAlignMode(alignment);
                } catch (Exception ignored) {
                    result.success(false);
                    break;
                }
                result.success(true);
            }
            case "PRINT_IMAGE" -> {
                byte[] bytes = call.argument("bitmap");
                int mode = call.argument("mode");
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                try {
                    sunmiCloudPrinterMethod.printBitmap(bitmap, mode);
                } catch (Exception ignored) {
                    result.success(false);
                    break;
                }
                result.success(true);
            }
            case "ENTER_PRINTER_BUFFER" -> {
                Boolean clearEnter = call.argument("clearEnter");
                try {
                    sunmiCloudPrinterMethod.startTransBuffer(clearEnter);
                } catch (Exception ignored) {
                    result.success(false);
                    break;
                }
                result.success(true);
            }
            case "EXIT_PRINTER_BUFFER" -> {
                try {
                    sunmiCloudPrinterMethod.endTransBuffer();
                } catch (Exception ignored) {
                    result.success(false);
                    break;
                }
                result.success(true);
            }
            case "PRINT_ROW" -> {
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
                } catch (Exception ignored) {
                    result.success(false);
                    break;
                }
                result.success(true);
            }
            default -> {
                result.notImplemented();
            }
        }

    }

    /**
     * Handle detached from engine event.
     *
     * @param flutterPluginBinding the flutter plugin binding
     */
    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel.setMethodCallHandler(null);
    }
}
