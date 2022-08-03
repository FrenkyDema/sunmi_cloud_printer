package com.example.sunmi_cloud_printer;


import android.content.Context;

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

    void setCloudPrinter() throws PrinterException {
        SunmiPrinterApi.getInstance().setPrinter(SunmiPrinter.SunmiCloudPrinter);

    }

    void setBTPrinter() throws PrinterException {
        List<String> macList = SunmiPrinterApi.getInstance().findBleDevice(_context);
        if (macList.size() > 0) {
            SunmiPrinterApi.getInstance().setPrinter(SunmiPrinter.SunmiBlueToothPrinter, macList.get(0));
        }
    }

    void setNetPrinter() throws PrinterException {
        String ip = "192.168.2.93";
        SunmiPrinterApi.getInstance().setPrinter(SunmiPrinter.SunmiNetPrinter, ip);
    }

    void connect() throws PrinterException {
        if (!SunmiPrinterApi.getInstance().isConnected()) {
            SunmiPrinterApi.getInstance().connectPrinter(_context, new ConnectCallback() {
                @Override
                public void onFound() {
                    System.out.println("onFound");
                }

                @Override
                public void onUnfound() {
                    System.out.println("onUnfound");
                }

                @Override
                public void onConnect() {
                    System.out.println("onConnect");
                }

                @Override
                public void onDisconnect() {
                    System.out.println("onDisconnect");
                }

            });
        }
    }

    void printerInit() throws PrinterException {
        SunmiPrinterApi.getInstance().printerInit();
    }

    void printerText(String text) throws PrinterException {
        SunmiPrinterApi.getInstance().printText(text);
    }

    void lineWrap(int nLine) throws PrinterException {
        SunmiPrinterApi.getInstance().lineWrap(nLine);
    }

    void printQrCode(String data) throws PrinterException {
        SunmiPrinterApi.getInstance().printQrCode("123456789", 4, 0);
    }


}
