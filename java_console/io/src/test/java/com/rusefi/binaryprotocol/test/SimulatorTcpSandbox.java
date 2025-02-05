package com.rusefi.binaryprotocol.test;

import com.rusefi.binaryprotocol.BinaryProtocol;
import com.rusefi.io.LinkManager;
import com.rusefi.io.tcp.TcpIoStream;

import java.io.IOException;
import java.net.Socket;

import static com.rusefi.io.tcp.TcpConnector.DEFAULT_PORT;
import static com.rusefi.io.tcp.TcpConnector.LOCALHOST;

public class SimulatorTcpSandbox {
    public static void main(String[] args) throws IOException, InterruptedException {
        BinaryProtocol.DISABLE_LOCAL_CACHE = true;

        Socket s = new Socket(LOCALHOST, DEFAULT_PORT);
        TcpIoStream tsStream = new TcpIoStream("sandbox", s);

        LinkManager linkManager = new LinkManager();
//        SandboxCommon.verifyCrcNoPending(tsStream, linkManager);

        for (int i = 0; i < 3; i++) {
            // warm-up cycles just for fun
            String signature = BinaryProtocol.getSignature(tsStream);
        }


        {
            int count = 10000;
            long startMs = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
                // warm-up cycles just for fun
                String signature = BinaryProtocol.getSignature(tsStream);
            }
            long time = System.currentTimeMillis() - startMs;
            double timePerCommand = 1.0 * time / count;
            System.out.println("Executed " + count + " getSignature in " + time + "ms\n" + "Per-signature: " + timePerCommand + "ms");

//        linkManager.submit(() -> {

        }

        {
            BinaryProtocol bp = new BinaryProtocol(linkManager, tsStream);
            int count = 10000;
            long startMs = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
                //  boolean response = bp.requestOutputChannels();
                SandboxCommon.runGetProtocolCommand("dd", tsStream);
            }
            long time = System.currentTimeMillis() - startMs;
            double timePerCommand = 1.0 * time / count;
            System.out.println("Executed " + count + " GetProtocol in " + time + "ms\n" + "Per-GetProtocol: " + timePerCommand + "ms");
            //          log.info("requestOutputChannels " + response);
            //     });


        }
        //        ConfigurationImage ci = SandboxCommon.readImage(tsStream, linkManager);

        System.exit(0);
    }

}
