package com.example.sunmi_cloud_printer;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.sunmi.externalprinterlibrary.api.ConnectCallback;
import com.sunmi.externalprinterlibrary.api.PrinterException;
import com.sunmi.externalprinterlibrary.api.SunmiPrinter;
import com.sunmi.externalprinterlibrary.api.SunmiPrinterApi;

public class SunmiCloudPrinterMethod {

    private Context _context;

    public SunmiCloudPrinterMethod(Context _context) {
        this._context = _context;
    }

    void setCloudPrinter(View view) throws PrinterException {
        SunmiPrinterApi.getInstance().setPrinter(SunmiPrinter.SunmiCloudPrinter);

    }

    void setNetPrinter(View view) throws PrinterException {
        String ip = "192.168.2.93";
        SunmiPrinterApi.getInstance().setPrinter(SunmiPrinter.SunmiNetPrinter, ip);
    }

    void connect(View view) throws PrinterException {
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

}
