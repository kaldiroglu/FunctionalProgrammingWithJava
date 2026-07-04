package dev.kaldiroglu.fpj.ch04.domain.computer.bad;

public class Main {
    static void main() {
        run();
        runWithNullCheck();
    }

    static void run() {
        ComputerFactory computerFactory = new ComputerFactory();
        Computer computer = computerFactory.createComputer();
        String version = computer.getSoundcard().getUSB().getVersion();
        System.out.println("Version: " + version);
    }

    static void runWithNullCheck() {
        String version = "UNKNOWN";
        ComputerFactory computerFactory = new ComputerFactory();
        Computer computer = computerFactory.createComputer();
        if (computer != null) {
            Soundcard soundcard = computer.getSoundcard();
            if (soundcard != null) {
                USB usb = soundcard.getUSB();
                if (usb != null) {
                    version = usb.getVersion();
                }
            }
        }

        System.out.println("Version: " + version);
    }
}
