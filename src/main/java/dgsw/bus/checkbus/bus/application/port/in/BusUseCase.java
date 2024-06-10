package dgsw.bus.checkbus.bus.application.port.in;

public interface BusUseCase {
    public void reloadBus();
    public byte[] getBusQR(String busCode);
    public boolean checkBusQR(String busCode, String hash);
    public void closeBus(String busCode);
}
