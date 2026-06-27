package dev.kaldiroglu.fpj.ch10;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * Optional örnekleri — "Optional.md" destesine eşlik eder.
 *
 * Not: Best-practice gereği Optional bir ALAN (field) olarak KULLANILMAZ; bu yüzden
 * User'da adres düz bir alandır (null olabilir) ve dışarıya bir Optional DÖNDÜREN
 * findAddress() metodu ile sunulur.
 *
 * Java 25+ ile tek dosya olarak çalışır:  java OptionalExample.java
 */
public class OptionalExampleWithRecord {

    record Address(String city) {}

    record User(String name, boolean active, Address address) {  // address null olabilir
        Optional<Address> findAddress() { return Optional.ofNullable(address); }
    }

    // Küçük bir "veritabanı"
    static final List<User> DB = List.of(
        new User("Ada",    true,  new Address("İstanbul")),
        new User("Linus",  true,  null),                       // adresi yok
        new User("Edsger", false, new Address("Austin"))
    );

    static Optional<User> findUser(String name) {
        return DB.stream().filter(u -> u.name().equals(name)).findFirst();
    }

    void main() {
        // 1) Oluşturma
        System.out.println(Optional.of("Ada"));         // Optional[Ada]
        System.out.println(Optional.ofNullable(null));  // Optional.empty
        System.out.println(Optional.empty());           // Optional.empty

        // 2) filter + flatMap + map + orElse  (tek if / get yok)
        String city = findUser("Ada")
                .filter(User::active)
                .flatMap(User::findAddress)   // Optional<Address>
                .map(Address::city)
                .orElse("—");
        System.out.println("Ada   -> " + city);         // İstanbul

        String city2 = findUser("Linus")
                .flatMap(User::findAddress)   // boş
                .map(Address::city)
                .orElse("—");
        System.out.println("Linus -> " + city2);        // —

        // 3) orElse vs orElseGet (Supplier yalnızca boşsa çalışır)
        findUser("yok").map(User::name).orElseGet(() -> {
            System.out.println("  (orElseGet Supplier çalıştı)");
            return "varsayılan";
        });

        // 4) ifPresentOrElse
        findUser("Edsger").ifPresentOrElse(
            u -> System.out.println("bulundu: " + u.name()),
            ()  -> System.out.println("yok"));

        // 5) or — alternatif Optional döndürür
        Optional<User> u = findUser("yok").or(() -> findUser("Ada"));
        System.out.println("or    -> " + u.map(User::name).orElse("?"));

        // 6) Optional.stream() — boşları eleyerek düzleştir
        List<String> cities = DB.stream()
                .map(User::findAddress)       // Stream<Optional<Address>>
                .flatMap(Optional::stream)    // boş olanlar elenir
                .map(Address::city)
                .toList();
        System.out.println("şehirler: " + cities);      // [İstanbul, Austin]

        // 7) İlkel Optional
        OptionalInt max = IntStream.of(3, 1, 4, 1, 5).max();
        System.out.println("max: " + max.getAsInt());   // 5
    }
}
