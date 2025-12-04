package com.example.liamstvremote;

import android.content.Context;
import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import com.obd.infrared.InfraRed;
import com.obd.infrared.patterns.PatternConverter;
import com.obd.infrared.patterns.PatternType;
import com.obd.infrared.transmit.TransmitterType;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ConsumerIrManager irManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InfraRed infraRed = new InfraRed(this, null);

        TransmitterType transmitterType = infraRed.detect();

        // initialize transmitter by type
        infraRed.createTransmitter(transmitterType);

        // initialize raw patterns
        List<PatternConverter> rawPatterns = new ArrayList<>();

        // Nikon D7100 v.1
        rawPatterns.add(new PatternConverter(PatternType.Cycles, 38400, 1, 105, 5, 1, 75, 1095, 20, 60, 20, 140, 15, 2500, 80, 1));


    }
}
