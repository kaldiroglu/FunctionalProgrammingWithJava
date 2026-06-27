package dev.kaldiroglu.fpj.ch02.methodReferences;

import dev.kaldiroglu.fpj.ch02.domain.Message;

/**
 * Metot referanslarının dört türü, yalnızca bu bölüme ait kendi fonksiyonel
 * arayüzlerimizle gösterilir — java.util.function (Consumer, Supplier, Function,
 * BinaryOperator, ...) KULLANILMADAN. Böylece Bölüm 2, Bölüm 3'te ele alınacak
 * hazır fonksiyonlara bağımlı olmaz.
 */
public class MethodReferences {

	public static void main(String[] args) {
		examples();
	}

	public static void examples(){
		// ---- 1) Statik metot  (Tip::metot) ----
		NowSource now = System::currentTimeMillis;          // 0 argüman, long döner
		System.out.println(now.now());

		DoubleOp squareRoot = Math::sqrt;                   // 1 argüman
		System.out.println(squareRoot.apply(2.0));

		// ---- 2) Belirli bir nesnenin metodu  (nesne::metot) ----
		StringConsumer print = System.out::println;         // System.out nesnesine bağlı
		print.accept("Hey, what's up?");

		Message m = new Message("hi");
		StringConsumer setter = m::setText;                 // m nesnesine bağlı, 1 argüman alır
		setter.accept("hello");
		System.out.println(m);

		TextOp makeGreeting = "Hello, "::concat;            // "Hello, " nesnesine bağlı, 1 argüman
		System.out.println(makeGreeting.apply("Peggy"));

		// ---- 3) Belirli bir tipin nesne metodu  (Tip::metot) ----
		// Üzerinde metot çağrılacak nesne, parametre olarak geçilir!
		MessageAction show = Message::show;                 // m, parametre olarak geçilir
		show.run(m);                                        // m.show() çalışır

		TextOp upper = String::toUpperCase;                 // (String s) -> s.toUpperCase()
		System.out.println(upper.apply("world"));

		TextCombiner concat = String::concat;               // 2 argüman: ilki alıcı (receiver)
		System.out.println(concat.combine("Selam ", "abi :)"));

		// ---- 4) Kurucu  (Sınıf::new) ----
		MessageMaker maker = Message::new;                  // argümansız kurucu
		System.out.println(maker.make());

		MessageFromText fromText = Message::new;            // 1 argümanlı kurucu
		System.out.println(fromText.make("constructed"));

		// 3 parametre alan metot referansı ve çağrısı
		MessageMaker3 maker3 = Message::new;                // 3 argümanlı kurucu -> kendi arayüzümüz
		System.out.println(maker3.make("Hi ", "there", 3));
	}
}

// ---- Kendi fonksiyonel arayüzlerimiz ----

@FunctionalInterface interface NowSource       { long now(); }                          // ~ Supplier<Long>
@FunctionalInterface interface DoubleOp        { double apply(double x); }              // ~ UnaryOperator<Double>
@FunctionalInterface interface StringConsumer  { void accept(String s); }               // ~ Consumer<String>
@FunctionalInterface interface TextOp          { String apply(String s); }              // ~ UnaryOperator<String>
@FunctionalInterface interface TextCombiner    { String combine(String a, String b); }  // ~ BinaryOperator<String>
@FunctionalInterface interface MessageAction   { void run(Message m); }                 // ~ Consumer<Message>
@FunctionalInterface interface MessageMaker    { Message make(); }                      // ~ Supplier<Message>
@FunctionalInterface interface MessageFromText { Message make(String text); }           // ~ Function<String,Message>
@FunctionalInterface interface MessageMaker3   { Message make(String prefix, String body, int times); } // hazır karşılığı YOK

