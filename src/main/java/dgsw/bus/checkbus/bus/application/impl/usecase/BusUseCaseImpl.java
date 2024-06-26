package dgsw.bus.checkbus.bus.application.impl.usecase;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import dgsw.bus.checkbus.bus.adapter.in.dto.DodamBusListRequestDto;
import dgsw.bus.checkbus.bus.adapter.in.dto.TakeBusDto;
import dgsw.bus.checkbus.bus.adapter.out.entity.BusEntity;
import dgsw.bus.checkbus.bus.application.port.in.BusUseCase;
import dgsw.bus.checkbus.bus.application.port.out.ManipulateBusPort;
import dgsw.bus.checkbus.bus.application.port.out.ManipulateEntryPort;
import dgsw.bus.checkbus.bus.application.port.out.ReadBusPort;
import dgsw.bus.checkbus.global.annotation.UseCase;
import dgsw.bus.checkbus.global.exception.BackendException;
import dgsw.bus.checkbus.global.exception.ExceptionCode;
import dgsw.bus.checkbus.global.http.request.RequestRestTemplate;
import dgsw.bus.checkbus.user.adapter.in.dto.token.DAuthTokenResponseDto;
import dgsw.bus.checkbus.user.adapter.in.dto.token.TokenResponseDto;
import dgsw.bus.checkbus.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@UseCase
@RequiredArgsConstructor
public class BusUseCaseImpl implements BusUseCase {
    private final ManipulateBusPort manipulateBusPort;
    private final ReadBusPort readBusPort;
    private final ManipulateEntryPort manipulateEntryPort;
    private final RequestRestTemplate restTemplate;

    @Value("${dodam-api-url}")
    private String dodamApiUrl;

    @Override
    public void reloadBus(String jwt) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwt);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            LocalDate date = LocalDate.now(ZoneId.of("Asia/Seoul"));
            String apiUrl = dodamApiUrl +
                "/bus/date?year=" + date.getYear() + "&month=" + date.getMonth().getValue() + "&day=" + date.getDayOfMonth();
            DodamBusListRequestDto dodamBusListRequestDto =
                restTemplate.exchange(
                        apiUrl,
                        HttpMethod.GET,
                        entity,
                        DodamBusListRequestDto.class
                ).getBody();

            manipulateBusPort.registerBus(dodamBusListRequestDto);
        } catch (HttpClientErrorException e) {
            throw BackendException.of(ExceptionCode.WRONG_BUS);
        }
    }

    @Override
    public byte[] getBusQR(Long busCode) {
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
    public boolean checkBusQR(TakeBusDto takeBusDto, User user) {
        manipulateEntryPort.checkEntry(takeBusDto, user);
        return false;
    }
    @Override
    public void closeBus(Long busCode) {
        manipulateBusPort.removeBus(busCode);
    }
}
