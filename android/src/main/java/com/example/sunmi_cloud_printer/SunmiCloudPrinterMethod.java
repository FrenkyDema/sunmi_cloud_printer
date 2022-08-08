package com.example.sunmi_cloud_printer;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Looper;
import android.widget.Toast;

import com.sunmi.externalprinterlibrary.api.ConnectCallback;
import com.sunmi.externalprinterlibrary.api.PrinterException;
import com.sunmi.externalprinterlibrary.api.SunmiPrinter;
import com.sunmi.externalprinterlibrary.api.SunmiPrinterApi;

import java.util.List;

public class SunmiCloudPrinterMethod {

    private final Context _context;

    public SunmiCloudPrinterMethod(Context _context) {
        this._context = _context;
    }

    public static void setCloudPrinter() throws PrinterException {
        Utilities.runFunctionWithException(
                () -> {
                    SunmiPrinterApi.getInstance().setPrinter(SunmiPrinter.SunmiCloudPrinter);
                }
        );
    }

    public void setBTPrinter() throws PrinterException {
        List<String> macList = SunmiPrinterApi.getInstance().findBleDevice(_context);
        if (macList.size() > 0) {
            try {
                Utilities.runFunctionWithException(
                        () -> SunmiPrinterApi.getInstance().setPrinter(SunmiPrinter.SunmiBlueToothPrinter, macList.get(0))
                );
            } catch (PrinterException exception) {
                throw exception;
            }

        }
    }

    public void setNetPrinter(String ip) throws PrinterException {
        Utilities.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().setPrinter(SunmiPrinter.SunmiNetPrinter, ip)
        );
    }

    public void connect() throws PrinterException {
        System.out.println("Connection...");
        if (!SunmiPrinterApi.getInstance().isConnected()) {
            Utilities.runFunctionWithException(
                    () -> SunmiPrinterApi.getInstance().connectPrinter(_context, new ConnectCallback() {
                        @Override
                        public void onFound() {
                            System.out.println("onFound");
                            Looper.prepare();
                            Toast.makeText(
                                    _context,
                                    "Sunmi Printer Found",
                                    Toast.LENGTH_LONG
                            ).show();
                            Looper.loop();
                            Looper.myLooper().quitSafely();

                        }

                        @Override
                        public void onUnfound() {
                            System.out.println("onUnfound");
                            Looper.prepare();
                            Toast.makeText(
                                    _context,
                                    "Sunmi Printer Unfound",
                                    Toast.LENGTH_LONG
                            ).show();
                            Looper.loop();
                            Looper.myLooper().quitSafely();
                        }

                        @Override
                        public void onConnect() {
                            System.out.println("onConnect");
                            Looper.prepare();
                            Toast.makeText(
                                    _context,
                                    "Sunmi Printer Connected",
                                    Toast.LENGTH_LONG
                            ).show();
                            Looper.loop();
                            Looper.myLooper().quitSafely();
                        }

                        @Override
                        public void onDisconnect() {
                            System.out.println("onDisconnect");
                            Looper.prepare();
                            Toast.makeText(
                                    _context,
                                    "Sunmi Printer Disconnected",
                                    Toast.LENGTH_LONG
                            ).show();
                            Looper.loop();
                            Looper.myLooper().quitSafely();
                        }

                    })
            );
        } else {
            System.out.println("Already connected");
            Toast.makeText(
                    _context,
                    "Already connected",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    public void printerInit() throws PrinterException {
        Utilities.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().printerInit()
        );
    }

    public void printText(String text) throws PrinterException {
        Utilities.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().printText(text)
        );
    }

    public void lineWrap(int n) throws PrinterException {
        Utilities.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().lineWrap(n)
        );
    }

    public void pixelWrap(int n) throws PrinterException {
        Utilities.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().pixelWrap(n)
        );
    }

    public void tab() throws PrinterException {
        Utilities.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().tab()
        );
    }

    public void setHorizontalTab(int[] k) throws PrinterException {
        Utilities.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().setHorizontalTab(k)
        );
    }

    public void printQrCode(String data, int modulesize, int errorlevel) throws PrinterException {
        Utilities.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().printQrCode(data, modulesize, errorlevel)
        );
    }

    public void printBarCode(String code, int type, int width, int height, int hriPos) throws PrinterException {
        Utilities.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().printBarCode(code, type, width, height, hriPos)
        );
    }

    public void cutPaper(int m, int n) throws PrinterException {
        Utilities.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().cutPaper(m, n)
        );
    }

    public void enableBold(Boolean bold) throws PrinterException {
        Utilities.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().enableBold(bold)
        );
    }

    public void enableDouble(Boolean dooble) throws PrinterException {
        Utilities.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().enableDouble(dooble)
        );
    }

    public void enableUnderline(Boolean underline) throws PrinterException {
        Utilities.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().enableUnderline(underline)
        );
    }

    public void printBitmap(Bitmap bitmap, int mode) throws PrinterException {
        Utilities.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().printBitmap(bitmap, mode)
        );
    }

    public void printColumnsText(String[] colsTextArr, int[] colsWidthArr, int[] colsAlign) throws PrinterException {
        Utilities.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().printColumnsText(colsTextArr, colsWidthArr, colsAlign)
        );
    }

    public void endTransBuffer() throws PrinterException {
        Utilities.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().endTransBuffer()
        );
    }

    public void startTransBuffer(Boolean clear) throws PrinterException {
        Utilities.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().startTransBuffer(clear)
        );
    }

    public int getPrinterStatus() throws PrinterException {
        return Utilities.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().getPrinterStatus()
        ).orElse(-1);
    }

    public boolean isConnected() throws PrinterException {

        return Utilities.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().isConnected()
        ).orElse(false);
    }

    public void sendRawData(byte[] cmd) throws PrinterException {
        Utilities.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().sendRawData(cmd)
        );
    }

    public void setAlignMode(int type) throws PrinterException {
        Utilities.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().setAlignMode(type)
        );
    }

    public void flush() throws PrinterException {
        Utilities.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().flush()
        );
    }

    public void setFontZoom(int hori, int veri) throws PrinterException {
        Utilities.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().setFontZoom(hori, veri)
        );
    }
}
