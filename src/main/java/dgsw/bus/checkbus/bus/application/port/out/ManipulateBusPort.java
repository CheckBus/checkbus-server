package dgsw.bus.checkbus.bus.application.port.out;

public interface ManipulateBusPort {

    public void registerBus(String busCode, String hashCode);
    public void removeBus(String busCode);
}
