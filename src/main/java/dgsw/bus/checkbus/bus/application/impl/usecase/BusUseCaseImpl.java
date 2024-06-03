package dgsw.bus.checkbus.bus.application.impl.usecase;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import dgsw.bus.checkbus.bus.adapter.out.entity.BusEntity;
import dgsw.bus.checkbus.bus.application.port.in.BusUseCase;
import dgsw.bus.checkbus.bus.application.port.out.ManipulateBusPort;
import dgsw.bus.checkbus.bus.application.port.out.ReadBusPort;
import dgsw.bus.checkbus.global.annotation.UseCase;
import dgsw.bus.checkbus.global.exception.BackendException;
import dgsw.bus.checkbus.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

@UseCase
@RequiredArgsConstructor
public class BusUseCaseImpl implements BusUseCase {
    private final ManipulateBusPort manipulateBusPort;
    private final ReadBusPort readBusPort;

    @Override
    public String generateBusQR(String busCode) {
        String hashCode = generateHash();

        manipulateBusPort.registerBus(busCode, hashCode);

        return hashCode;
    }

    @Override
    public byte[] getBusQR(String busCode) {
        BusEntity entity = readBusPort.getBus(busCode);
        try {
            BitMatrix encode = new MultiFormatWriter().encode(
                    "http://localhost:8080/v1/bus/check-bus?busCode=" + busCode + "&hash=" + entity.getHashCode(),
                    BarcodeFormat.QR_CODE,
                    200,
                    200
            );

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(encode, "PNG", out);

            return out.toByteArray();
        } catch (IOException | WriterException e) {
            throw new BackendException(ExceptionCode.WRONG_QR);
        }
    }

    @Override
    public boolean checkBusQR(String busCode, String hash) {
        return false;
    }
    @Override
    public void closeBus(String busCode) {
        manipulateBusPort.removeBus(busCode);
    }

    private String generateHash() {
        Random random = new Random();
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int randomLimitedInt = 97 + (int)
                    (random.nextFloat() * (122 - 97 + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
