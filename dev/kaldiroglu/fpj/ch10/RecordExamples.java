package dev.kaldiroglu.fpj.ch10;

import java.util.ArrayList;
import java.util.List;

/**
 * Records (JEP 395, Java 16 — FINAL). "Records.md" destesinin çalışır karşılığı.
 *
 * Kapsam: derleyici üretimleri, static factory + nesne metodu, arayüz gerçekleştirme,
 * kompakt kurucu (doğrulama + normalizasyon), savunmacı kopya, records+sealed (ADT),
 * korumalı/iç içe record patterns, yerel record (local record) ile çoklu dönüş.
 *
 * Java 25+ ile tek dosya olarak çalışır:
 *   java Enhancements/examples/src/main/java/dev/kaldiroglu/fpj/records/RecordExamples.java
 */
public class RecordExamples {

    // 1) Basit record + statik üye + nesne metodu + static factory + arayüz gerçekleştirme
    record Point(int x, int y) implements Comparable<Point> {
        static final Point ORIGIN = new Point(0, 0);
        double distance() { return Math.hypot(x, y); }      // bileşenlere doğrudan erişim
        static Point of(int x, int y) { return new Point(x, y); }
        @Override public int compareTo(Point o) {           // records sınıf genişletemez ama
            return Double.compare(distance(), o.distance()); // arayüz gerçekleştirebilir
        }
    }

    // 2) Kompakt kanonik kurucu — doğrulama (atama otomatik yapılır)
    record Range(int low, int high) {
        Range {
            if (low > high)
                throw new IllegalArgumentException("low > high: " + low + " > " + high);
        }
    }

    // 3) Kompakt kanonik kurucu — normalizasyon (kesir sadeleştirme)
    record Fraction(int num, int den) {
        Fraction {
            if (den == 0) throw new IllegalArgumentException("payda 0 olamaz");
            int g = gcd(Math.abs(num), Math.abs(den));
            if (g != 0) { num /= g; den /= g; }
            if (den < 0) { num = -num; den = -den; }        // işareti paya taşı
        }
        private static int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }
    }

    // 4) Sığ değişmezlik (shallow immutability) + savunmacı kopya (defensive copy)
    record Team(String name, List<String> members) {
        Team {
            members = List.copyOf(members);                 // değişmez kopya
        }
    }

    // 5) Records + sealed = cebirsel veri tipi (ADT)
    sealed interface Shape permits Circle, Rectangle {}
    record Circle(double r) implements Shape {}
    record Rectangle(double w, double h) implements Shape {}

    static double area(Shape s) {
        return switch (s) {                                 // sealed → tam (exhaustive)
            case Circle(double r)              -> Math.PI * r * r;
            case Rectangle(double w, double h) -> w * h;
        };
    }

    // 6) İç içe record patterns
    record Line(Point from, Point to) {}

    // Korumalı (when) + record pattern; son tip-deseni koşulsuz olduğundan switch tamdır
    static String describe(Point p) {
        return switch (p) {
            case Point(int x, int y) when x == y -> "köşegen (" + x + ")";
            case Point pt                        -> "(" + pt.x() + ", " + pt.y() + ")";
        };
    }

    void main() {
        // 1) Derleyici üretimleri + factory + Comparable
        Point p = Point.of(3, 4);
        System.out.println(p);                                  // Point[x=3, y=4]
        System.out.println(p.x() + ", " + p.y());               // 3, 4  (getX() değil!)
        System.out.println("distance = " + p.distance());        // 5.0
        System.out.println("equals   = " + p.equals(new Point(3, 4)));   // true
        System.out.println("compareTo= " + p.compareTo(Point.ORIGIN));   // 1 (daha uzak)

        // 2) Doğrulama
        try { new Range(5, 1); }
        catch (IllegalArgumentException e) { System.out.println("doğrulama: " + e.getMessage()); }

        // 3) Normalizasyon
        System.out.println("Fraction = " + new Fraction(6, -8));  // Fraction[num=-3, den=4]

        // 4) Savunmacı kopya
        var src = new ArrayList<>(List.of("Ada"));
        var team = new Team("A", src);
        src.add("Linus");
        System.out.println("members  = " + team.members());      // [Ada]

        // 5) ADT + desen eşleme
        Shape sh = new Rectangle(3, 4);
        System.out.println("area     = " + area(sh));            // 12.0

        // 6) Korumalı + iç içe record patterns
        System.out.println(describe(new Point(5, 5)));           // köşegen (5)
        Object line = new Line(new Point(0, 0), new Point(4, 6));
        if (line instanceof Line(Point(var x1, var y1), Point(var x2, var y2)))
            System.out.println("orta = (" + (x1 + x2) / 2.0 + ", " + (y1 + y2) / 2.0 + ")"); // (2.0, 3.0)

        // 7) Yerel record (local record) — birden çok değer döndürme
        record MinMax(int min, int max) {}
        int[] data = {3, 1, 4, 1, 5, 9, 2};
        int mn = data[0], mx = data[0];
        for (int v : data) { mn = Math.min(mn, v); mx = Math.max(mx, v); }
        System.out.println("minmax   = " + new MinMax(mn, mx)); // MinMax[min=1, max=9]
    }
}
