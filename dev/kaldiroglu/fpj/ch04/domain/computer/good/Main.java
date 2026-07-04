package dev.kaldiroglu.fpj.ch04.domain.computer.good;

import java.util.Optional;

public class Main {
    static void main() {
        runWithOptional();
        runWithOptionalProcessing();
    }

    static void runWithOptional(){
        String version = "UNKNOWN";

        ComputerFactory computerFactory = new ComputerFactory();
        Optional<Computer> computerOpt = computerFactory.createComputer();
        if(computerOpt.isPresent()){
            Computer computer = computerOpt.get();
            Optional<Soundcard> soundcardOpt = computer.getSoundcard();
            if(soundcardOpt.isPresent()){
                Soundcard soundcard = soundcardOpt.get();
                Optional<USB> usbOpt = soundcard.getUSB();
                if(usbOpt.isPresent()){
                    USB usb = usbOpt.get();
                    version = usb.getVersion();
                }
            }
        }

        System.out.println("Version: " + version);
    }

    static void runWithOptionalProcessing(){
        ComputerFactory computerFactory = new ComputerFactory();
        Optional<Computer> computer = computerFactory.createComputer();
        String version = computer
                .flatMap(Computer::getSoundcard) // Can't use map here, it is called on an Optional<Computer>
                .flatMap(Soundcard::getUSB)      // Can't use map here, it is called on an Optional<Soundcard>
                .map(USB::getVersion)
                .orElse("UNKNOWN");

        System.out.println("Version: " + version);
    }
}
