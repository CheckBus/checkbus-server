package dgsw.bus.checkbus.bus.adapter.out;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import dgsw.bus.checkbus.bus.adapter.in.dto.DodamBusDto;
import dgsw.bus.checkbus.bus.adapter.in.dto.DodamBusListRequestDto;
import dgsw.bus.checkbus.bus.adapter.out.entity.BusEntity;
import dgsw.bus.checkbus.bus.adapter.out.repository.BusRepository;
import dgsw.bus.checkbus.bus.application.port.out.ManipulateBusPort;
import dgsw.bus.checkbus.bus.application.port.out.ReadBusPort;
import dgsw.bus.checkbus.global.annotation.PersistenceAdapter;
import dgsw.bus.checkbus.global.exception.BackendException;
import dgsw.bus.checkbus.global.exception.ExceptionCode;
import dgsw.bus.checkbus.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

@PersistenceAdapter
@RequiredArgsConstructor
public class BusAdapter implements ManipulateBusPort, ReadBusPort {
    private final BusRepository busRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerBus(DodamBusListRequestDto dodamBusListRequestDto) {
        busRepository.deleteAll();
        for (DodamBusDto bus: dodamBusListRequestDto.getData()) {
            busRepository.save(
                BusEntity.builder()
                    .busCode(bus.getBusName())
                    .hashCode(generateHash())
                    .build()
            );
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeBus(String busCode) {
        busRepository.deleteByBusCode(busCode);
    }

    @Override
    public BusEntity getBus(String busCode) {
        return busRepository.findByBusCode(busCode);
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
