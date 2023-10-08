package com.example.lepsi_sunmi_cloud_printer;


import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.sunmi.externalprinterlibrary.api.ConnectCallback;
import com.sunmi.externalprinterlibrary.api.PrinterException;
import com.sunmi.externalprinterlibrary.api.SunmiPrinter;
import com.sunmi.externalprinterlibrary.api.SunmiPrinterApi;

import java.util.List;

/**
 * The Sunmi cloud printer method handler.
 */
public class SunmiCloudPrinterMethod {

    private final Context _context;

    /**
     * Instantiates a new SunmiCloudPrinterMethod class.
     *
     * @param _context the context
     */
    public SunmiCloudPrinterMethod(Context _context) {
        this._context = _context;
    }

    /**
     * Setup cloud printer.
     *
     * @throws PrinterException the printer exception
     */
    public static void setCloudPrinter() throws PrinterException {
        TaskProvider.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().setPrinter(SunmiPrinter.SunmiCloudPrinter)
        );
    }

    /**
     * Setup bluetooth printer.
     *
     * @throws PrinterException the printer exception
     */
    public void setBTPrinter() throws PrinterException {
        List<String> macList = SunmiPrinterApi.getInstance().findBleDevice(_context);
        if (macList.size() > 0) {
            TaskProvider.runFunctionWithException(
                    () -> SunmiPrinterApi.getInstance().setPrinter(SunmiPrinter.SunmiBlueToothPrinter, macList.get(0))
            );

        }
    }

    /**
     * Setup network printer.
     *
     * @param ip the ip
     * @throws PrinterException the printer exception
     */
    public void setNetPrinter(String ip) throws PrinterException {
        TaskProvider.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().setPrinter(SunmiPrinter.SunmiNetPrinter, ip)
        );
    }

    /**
     * Connect to the previously configured printer.
     *
     * @throws PrinterException the printer exception
     */
    public void connect() throws PrinterException {
        System.out.println("Connection...");
        if (!SunmiPrinterApi.getInstance().isConnected()) {
            TaskProvider.runFunctionWithException(
                    () -> SunmiPrinterApi.getInstance().connectPrinter(_context, new ConnectCallback() {
                        @Override
                        public void onFound() {
                            System.out.println("onFound");
                            Toast.makeText(
                                    _context,
                                    "Sunmi Printer Found",
                                    Toast.LENGTH_LONG
                            ).show();

                        }

                        @Override
                        public void onUnfound() {
                            System.out.println("onUnfound");
                            Toast.makeText(
                                    _context,
                                    "Sunmi Printer Unfound",
                                    Toast.LENGTH_LONG
                            ).show();
                        }

                        @Override
                        public void onConnect() {
                            System.out.println("onConnect");
                            Toast.makeText(
                                    _context,
                                    "Sunmi Printer Connected",
                                    Toast.LENGTH_LONG
                            ).show();
                        }

                        @Override
                        public void onDisconnect() {
                            System.out.println("onDisconnect");
                            Toast.makeText(
                                    _context,
                                    "Sunmi Printer Disconnected",
                                    Toast.LENGTH_LONG
                            ).show();
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

    /**
     * Added by LepsiCity : release method
     * 
     * Release the printer if it is connected
     *
     * @throws PrinterException the printer exception
     */
    public void release() throws PrinterException {
        if (SunmiPrinterApi.getInstance().isConnected()) {
            TaskProvider.runFunctionWithException(
                    () -> SunmiPrinterApi.getInstance().release(_context)
            );
        }
    }

    /**
     * Printer initialized.
     *
     * @throws PrinterException the printer exception
     */
    public void printerInit() throws PrinterException {
        TaskProvider.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().printerInit()
        );
    }

    /**
     * Print text.
     *
     * @param text the text
     * @throws PrinterException the printer exception
     */
    public void printText(String text) throws PrinterException {
        TaskProvider.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().printText(text)
        );
    }

    /**
     * Line wrap.
     *
     * @param n the n
     * @throws PrinterException the printer exception
     */
    public void lineWrap(int n) throws PrinterException {
        TaskProvider.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().lineWrap(n)
        );
    }

    /**
     * Pixel wrap.
     *
     * @param n the n
     * @throws PrinterException the printer exception
     */
    public void pixelWrap(int n) throws PrinterException {
        TaskProvider.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().pixelWrap(n)
        );
    }

    /**
     * Tab.
     *
     * @throws PrinterException the printer exception
     */
    public void tab() throws PrinterException {
        TaskProvider.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().tab()
        );
    }

    /**
     * Sets horizontal tab.
     *
     * @param k the horizontal tabs
     * @throws PrinterException the printer exception
     */
    public void setHorizontalTab(int[] k) throws PrinterException {
        TaskProvider.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().setHorizontalTab(k)
        );
    }

    /**
     * Print qr code.
     *
     * @param data       the data
     * @param moduleSize the moduleSize
     * @param errorLevel the errorLevel
     * @throws PrinterException the printer exception
     */
    public void printQrCode(String data, int moduleSize, int errorLevel) throws PrinterException {
        TaskProvider.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().printQrCode(data, moduleSize, errorLevel)
        );
    }

    /**
     * Print bar code.
     *
     * @param code   the code
     * @param type   the type
     * @param width  the width
     * @param height the height
     * @param hriPos the hri pos
     * @throws PrinterException the printer exception
     */
    public void printBarCode(String code, int type, int width, int height, int hriPos) throws PrinterException {
        TaskProvider.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().printBarCode(code, type, width, height, hriPos)
        );
    }

    /**
     * Cut paper.
     *
     * @param m the m
     * @param n the n
     * @throws PrinterException the printer exception
     */
    public void cutPaper(int m, int n) throws PrinterException {
        TaskProvider.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().cutPaper(m, n)
        );
    }

    /**
     * Enable bold.
     *
     * @param bold the bold flag
     * @throws PrinterException the printer exception
     */
    public void enableBold(Boolean bold) throws PrinterException {
        TaskProvider.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().enableBold(bold)
        );
    }

    /**
     * Enable double.
     *
     * @param isDouble the is double check
     * @throws PrinterException the printer exception
     */
    public void enableDouble(Boolean isDouble) throws PrinterException {
        TaskProvider.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().enableDouble(isDouble)
        );
    }

    /**
     * Enable underline.
     *
     * @param underline the underline flag
     * @throws PrinterException the printer exception
     */
    public void enableUnderline(Boolean underline) throws PrinterException {
        TaskProvider.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().enableUnderline(underline)
        );
    }

    /**
     * Print bitmap.
     *
     * @param bitmap the bitmap
     * @param mode   the mode
     * @throws PrinterException the printer exception
     */
    public void printBitmap(Bitmap bitmap, int mode) throws PrinterException {
        TaskProvider.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().printBitmap(bitmap, mode)
        );
    }

    /**
     * Print columns text.
     *
     * @param colsTextArr  the cols text arr
     * @param colsWidthArr the cols width arr
     * @param colsAlign    the cols align
     * @throws PrinterException the printer exception
     */
    public void printColumnsText(String[] colsTextArr, int[] colsWidthArr, int[] colsAlign) throws PrinterException {
        TaskProvider.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().printColumnsText(colsTextArr, colsWidthArr, colsAlign)
        );
    }

    /**
     * End trans buffer.
     *
     * @throws PrinterException the printer exception
     */
    public void endTransBuffer() throws PrinterException {
        TaskProvider.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().endTransBuffer()
        );
    }

    /**
     * Start trans buffer.
     *
     * @param clear the clear
     * @throws PrinterException the printer exception
     */
    public void startTransBuffer(Boolean clear) throws PrinterException {
        TaskProvider.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().startTransBuffer(clear)
        );
    }

    /**
     * Gets printer status.
     *
     * @return the printer status
     * @throws PrinterException the printer exception
     */
    public int getPrinterStatus() throws PrinterException {
        return TaskProvider.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().getPrinterStatus()
        ).orElse(-1);
    }

    /**
     * Check if is connected.
     *
     * @return boolean
     * @throws PrinterException the printer exception
     */
    public boolean isConnected() throws PrinterException {

        return TaskProvider.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().isConnected()
        ).orElse(false);
    }

    /**
     * Send raw data.
     *
     * @param cmd the byte data
     * @throws PrinterException the printer exception
     */
    public void sendRawData(byte[] cmd) throws PrinterException {
        TaskProvider.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().sendRawData(cmd)
        );
    }

    /**
     * Sets align mode.
     *
     * @param type the align type
     * @throws PrinterException the printer exception
     */
    public void setAlignMode(int type) throws PrinterException {
        TaskProvider.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().setAlignMode(type)
        );
    }

    /**
     * Flush.
     *
     * @throws PrinterException the printer exception
     */
    public void flush() throws PrinterException {
        TaskProvider.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().flush()
        );
    }

    /**
     * Sets font zoom.
     *
     * @param horizontal the horizontal size
     * @param vertical   the vertical size
     * @throws PrinterException the printer exception
     */
    public void setFontZoom(int horizontal, int vertical) throws PrinterException {
        TaskProvider.runFunctionWithException(
                () -> SunmiPrinterApi.getInstance().setFontZoom(horizontal, vertical)
        );
    }
}
