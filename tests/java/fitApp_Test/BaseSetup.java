/*Appium Test  Projesi - FitApp
 * Muhammed Baki Caki - 21.01.2021
 * */
package fitApp_Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseSetup {

	public AndroidDriver<MobileElement> driver;
	public WebDriverWait wait;

	@BeforeMethod
	public void setup() throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "test2");
		caps.setCapability("udid", "emulator-5554");
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "10.0");
		caps.setCapability("appPackage", "com.example.fitapp");
		caps.setCapability("appActivity", ".MainActivity");
		caps.setCapability("noReset", "false");
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		wait = new WebDriverWait(driver, 10);
	}

	@Test
	public void test() {

		System.out.println("-- EGITMEN EKLEME - SECME --");
		testEgitmenEkle(); // Test Cases[1-6]
		testEgitmenSec(); // Test Cases 7
		System.out.println("Egitmen ekleme-secme kismi basarili.");

		System.out.println("-- BMI HESAPLAMA --");
		testBmiHesapla();// Test Cases[8-10]
		System.out.println("Vucut kitle endeksi hesaplama kismi basarili.");

		System.out.println("-- BESIN EKLEME - KALORI HESAPLAMA --");
		testBesinEkle(); // Test Cases[11-14]
		testKaloriHesapla(); // Test Cases[15-17]
		System.out.println("Besin ekleme - kalori hesaplama kismi basarili.");

		System.out.println("-- MUSTERI EKLEME --");
		testMusteriEkle(); // Test Cases[2.1 - 2.5]
		System.out.println("Musteri ekleme kismi basarili.");

		System.out.println("-- MUSTERI GIRIS CIKS --");
		testGirisCikis();
		System.out.println("Giris - Cikis sayfası basarili");
	}

	public void testEgitmenEkle() {
		egitmenEkle1();

		// Test Case 1: isim bolumune sayi ve 20+ harf girme
		driver.findElementById("com.example.fitapp:id/isimTextBox").sendKeys("123");// numara girme
		driver.findElementById("com.example.fitapp:id/egitmenEkleButton").click();// egitmen ekle butonu

		try {
			String isim_hata_mesaji = wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/message")))
					.getText();
			String isim_beklenen_hata = "İsim sayı içeremez.";
			assertEquals(isim_hata_mesaji, isim_beklenen_hata);
			driver.findElementById("android:id/button1").click();
		} catch (Exception e) {
			System.out.println("isim kismina sayi girilebiliyor!");
		}

		driver.findElementById("com.example.fitapp:id/isimTextBox").sendKeys("aaaaaaaaaaaaaaaaaaaaa");// 21 harf girme
		driver.findElementById("com.example.fitapp:id/egitmenEkleButton").click();// egitmen ekle butonu

		try {
			String isim_hata_mesaji2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/message")))
					.getText();
			String isim_beklenen_hata2 = "İsim 20 karakterden uzun olamaz.";
			assertEquals(isim_hata_mesaji2, isim_beklenen_hata2);
			driver.findElementById("android:id/button1").click();
		} catch (Exception e) {
			System.out.println("isim kismina 20+ karakter girilebiliyor!");

		}
		driver.findElementById("com.example.fitapp:id/isimTextBox").sendKeys("Ali");// gecerli sonuc girme

		// Test Case 2: isim bolumune sayi ve 20+ harf girme
		driver.findElementById("com.example.fitapp:id/soyIsimTextBox").sendKeys("123");// numara girme
		driver.findElementById("com.example.fitapp:id/egitmenEkleButton").click();// egitmen ekle butonu

		try {
			String soyisim_hata_mesaji = wait
					.until(ExpectedConditions.elementToBeClickable(By.id("android:id/message"))).getText();
			String soyisim_beklenen_hata = "Soyisim sayı içeremez.";
			assertEquals(soyisim_hata_mesaji, soyisim_beklenen_hata);
			driver.findElementById("android:id/button1").click();
		} catch (Exception e) {
			System.out.println("Soyisim kismina sayi girilebiliyor!");
		}

		driver.findElementById("com.example.fitapp:id/soyIsimTextBox").sendKeys("aaaaaaaaaaaaaaaaaaaaa");// 21 harf girme
																											
		driver.findElementById("com.example.fitapp:id/egitmenEkleButton").click();// egitmen ekle butonu

		try {
			String soyisim_hata_mesaji2 = wait
					.until(ExpectedConditions.elementToBeClickable(By.id("android:id/message"))).getText();
			String soyisim_beklenen_hata2 = "Soyisim 20 karakterden uzun olamaz.";
			assertEquals(soyisim_hata_mesaji2, soyisim_beklenen_hata2);
			driver.findElementById("android:id/button1").click(); // ok butonu
		} catch (Exception e) {
			System.out.println("Soyisim kismina 20+ karakter girilebiliyor!");
		}
		driver.findElementById("com.example.fitapp:id/soyIsimTextBox").sendKeys("Kor");// gecerli sonuc girme

		// Test Case 3: Telefon numarasina harf ve 11+ sayi girme
		driver.findElementById("com.example.fitapp:id/telefonNoTextBox").sendKeys("555555555555");// 12 rakam girme
		driver.findElementById("com.example.fitapp:id/egitmenEkleButton").click();// egitmen ekle butonu

		try {
			String telNo_hata_mesaji = wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/message")))
					.getText();
			String telNo_beklenen_hata = "Telefon numarası 11 hane uzunluğunda olmalıdır.";
			assertEquals(telNo_hata_mesaji, telNo_beklenen_hata);
			driver.findElementById("android:id/button1").click(); // ok butonu
		} catch (Exception e) {
			System.out.println("Telefon numarasi 11+ hane girilebiliyor!");
		}

		driver.findElementById("com.example.fitapp:id/telefonNoTextBox").sendKeys("asd");// harf girme
		driver.findElementById("com.example.fitapp:id/egitmenEkleButton").click();// egitmen ekle butonu

		try {
			String telNo_hata_mesaji = wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/message")))
					.getText();
			String telNo_beklenen_hata = "Telefon numarası 11 hane uzunluğunda olmalıdır.";
			assertEquals(telNo_hata_mesaji, telNo_beklenen_hata);
			driver.findElementById("android:id/button1").click(); // ok butonu
		} catch (Exception e) {
			System.out.println("Telefon numarasi 11+ hane girilebiliyor!");
		}
		driver.findElementById("com.example.fitapp:id/telefonNoTextBox").sendKeys("11111111111");// gecerli sonuc girme

		// Test Case 4: Hakkinda kismina 250+ karakter girme -egitmen ekle butonu
		// kayboluyor
		String uzunMetin = "The WorldWideWeb (W3) is a wide-area hypermedia information retrieval initiative aiming to give universal access to a large universe of documents.\r\n"
				+ "Everything there is online about W3 is linked directly or indirectly to this document, including an executive summary of the project, Mailing lists , Policy , November's W3 news , Frequently Asked Questions .";

		try { // programi sonlandiriyor
			driver.findElementById("com.example.fitapp:id/hakkindaTextBox").sendKeys(uzunMetin);
			driver.findElementById("com.example.fitapp:id/egitmenEkleButton").click();// egitmen ekle butonu
		} catch (NoSuchElementException e) {
			System.out.println("Hakkinda kismina 250+harf girince egitmen ekle butonu kayboldu!");
		}
		driver.findElementById("com.example.fitapp:id/hakkindaTextBox").sendKeys("Hem para verin hem agirlik tasiyin"); // gecerli deger girme
																														
																														

		// Test Case 5:Aylik ucret kismina harf ve 5+ rakam girme
		driver.findElementById("com.example.fitapp:id/ucretTextBox").sendKeys("asd"); // harf girme
		driver.findElementById("com.example.fitapp:id/egitmenEkleButton").click();// egitmen ekle butonu

		try {
			String ucret_hata_mesaji = wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/message")))
					.getText();
			String ucret_beklenen_hata = "Ücret kısmı sadece sayı içerebilir.";
			assertEquals(ucret_hata_mesaji, ucret_beklenen_hata);
			driver.findElementById("android:id/button1").click(); // ok butonu
		} catch (Exception e) {
			System.out.println("Ucret kismina harf girilebiliyor!");
		}

		driver.findElementById("com.example.fitapp:id/ucretTextBox").sendKeys("111111"); // 5+ rakam girme
		driver.findElementById("com.example.fitapp:id/egitmenEkleButton").click();// egitmen ekle butonu

		try {
			String ucret_hata_mesaji2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/message")))
					.getText();
			String ucret_beklenen_hata2 = "Ücret kısmına en fazla 5 karakter girilebilir.";
			assertEquals(ucret_hata_mesaji2, ucret_beklenen_hata2);
			driver.findElementById("android:id/button1").click(); // ok butonu
		} catch (Exception e) {
			System.out.println("Ucret kismina 5+ rakam girilebiliyor!");
		}
		driver.findElementById("com.example.fitapp:id/ucretTextBox").sendKeys("5000"); // gecerli deger girme

		driver.findElementById("com.example.fitapp:id/egitmenEkleButton").click();// egitmen ekle butonu
		wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1"))).click(); // ok butonu
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); // implicit wait
		driver.navigate().back();

	}

	public void egitmenEkle1() {

		MobileElement egitmen_ekle_buton = (MobileElement) wait
				.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/goToEgitmenEkleButton")));
		egitmen_ekle_buton.click();
		MobileElement isim = (MobileElement) wait
				.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/isimTextBox")));
		isim.sendKeys("Ahmet");
		MobileElement soyisim = (MobileElement) driver.findElementById("com.example.fitapp:id/soyIsimTextBox");
		soyisim.sendKeys("Ak");
		MobileElement numara = (MobileElement) driver.findElementById("com.example.fitapp:id/telefonNoTextBox");
		numara.sendKeys("05555555555");
		MobileElement hakkinda = (MobileElement) driver.findElementById("com.example.fitapp:id/hakkindaTextBox");
		hakkinda.sendKeys("Acayip tecrubeli");
		MobileElement ucret = (MobileElement) driver.findElementById("com.example.fitapp:id/ucretTextBox");
		ucret.sendKeys("5000");

		// fotoğraf ekle
		MobileElement fotograf_ekle = (MobileElement) driver
				.findElementById("com.example.fitapp:id/fotografEkleButton");
		fotograf_ekle.click();
		MobileElement depolama_izin_ver = (MobileElement) wait.until(ExpectedConditions
				.elementToBeClickable(By.id("com.android.permissioncontroller:id/permission_allow_button")));
		depolama_izin_ver.click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		fotograf_ekle = (MobileElement) wait
				.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/fotografEkleButton")));
		fotograf_ekle.click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		// fotoğraf seç
		MobileElement profil_resmi = (MobileElement) wait
				.until(ExpectedConditions.elementToBeClickable(By.id("com.android.documentsui:id/icon_thumb")));
		profil_resmi.click();
		MobileElement ok_buton = (MobileElement) wait
				.until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
		ok_buton.click();

		MobileElement egitmen_ekle = (MobileElement) wait
				.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/egitmenEkleButton")));
		egitmen_ekle.click();

		ok_buton = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
		ok_buton.click();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); // implicit wait
		// driver.navigate().back();

	}

	public void testEgitmenSec() {

		MobileElement egitmen_sec_buton = (MobileElement) wait
				.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/goToEgitmenSec")));
		egitmen_sec_buton.click();
		MobileElement egitmeni_ara = (MobileElement) driver.findElementById("com.example.fitapp:id/egitmeniAraButton");
		egitmeni_ara.click();

		// arama ekranına geçme
		try {
			driver.findElementById("com.android.dialer:id/digits");
			System.out.println("Arama ekranina gecme basarili.");
		} catch (NoSuchElementException e) {
			System.out.println("Eğitmeni arama başarısız!");
		}

		// uygulamaya geri don
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // implicit wait
		driver.navigate().back();
		driver.navigate().back();
		driver.navigate().back();
		driver.navigate().back();
	}

	public void testBmiHesapla() {
		bmiHesaplaOrnek();
		// Test Case 8: birden fazla cinsiyet secme

		driver.findElementById("com.example.fitapp:id/erkekRadioButton").click(); // erkek sec
		driver.findElementById("com.example.fitapp:id/kadinRadioButton").click(); // kadin sec
		MobileElement checkbox_erkek = driver.findElementById("com.example.fitapp:id/erkekRadioButton");
		MobileElement checkbox_kadin = driver.findElementById("com.example.fitapp:id/kadinRadioButton");
		boolean erkek_secili = checkbox_erkek.getAttribute("checked").equals("true");
		boolean kadin_secili = checkbox_kadin.getAttribute("checked").equals("true");

		if (erkek_secili && kadin_secili) {
			System.out.println("iki cinsiyet birlikte secilebiliyor!");
		}
		// hata mesajını gec
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); // implicit wait

		// Test Case 9: boy kismina sati harici deger girme - uygulamayi cokertiyor
		driver.findElementById("com.example.fitapp:id/boyTextBox").sendKeys("asd"); // boy
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/hesaplaButton"))).click(); // hesapla butonu
																													

		if (!driver.currentActivity().equals("com.example.fitapp")) { // uygulama coktuyse
			System.out.println("Boy kismina harf girip hesaplamaya calisinca uygulama cöktü!");

			// ayni sayfayi tekrar ac
			try {
				setup();
			} catch (MalformedURLException e) {
				System.out.println("Uygulama yeniden acilamadi.");
			}
			bmiHesaplaOrnek(); // uygulama kapandıysa tekrar ac ve ornek veri ile doldur
		}

		// Test Case 10: kilo kismina sayi harici deger girme - uygulamayi cokertiyor
		driver.findElementById("com.example.fitapp:id/kiloTextBox").sendKeys("asd");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/hesaplaButton"))).click(); // hesapla butonu
																													

		if (!driver.currentActivity().equals("com.example.fitapp")) { // uygulama coktuyse
			System.out.println("Kilo kismina harf girip hesaplamaya calisinca uygulama cöktü!");

			// ayni sayfayi tekrar ac
			try {
				setup();
			} catch (MalformedURLException e) {
				System.out.println("Uygulama yeniden acilamadi.");
			}
			bmiHesaplaOrnek(); // uygulama kapandıysa tekrar ac ve ornek veri ile doldur
		}

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // implicit wait
		driver.navigate().back();

	}

	public void bmiHesaplaOrnek() {

		MobileElement bmi_buton = (MobileElement) wait.until(ExpectedConditions
				.elementToBeClickable(By.id("com.example.fitapp:id/goToVucutKitleIndeksiHesaplaButton")));
		bmi_buton.click();
		MobileElement cinsiyet_erkek = (MobileElement) driver.findElementById("com.example.fitapp:id/erkekRadioButton");
		cinsiyet_erkek.click();
		MobileElement boy = (MobileElement) driver.findElementById("com.example.fitapp:id/boyTextBox");
		boy.sendKeys("180");
		MobileElement kilo = (MobileElement) driver.findElementById("com.example.fitapp:id/kiloTextBox");
		kilo.sendKeys("90");
		MobileElement hesapla_buton = (MobileElement) wait
				.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/hesaplaButton")));
		hesapla_buton.click();

		MobileElement ok_buton = (MobileElement) wait
				.until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
		ok_buton.click();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // implicit wait

	}

	public void testBesinEkle() {

		besinEkle1();
		besinEkle2();

		// Test Case 11:besin ekle sayfasi isim kismina farkli degerler girme
		driver.findElementById("com.example.fitapp:id/yiyecekRadioButton").click(); // tur = yiyecek
		driver.findElementById("com.example.fitapp:id/birimAdetRadioButton").click();// birim = 1adet
		driver.findElementById("com.example.fitapp:id/kaloriMiktariTextBox").sendKeys("30");// kalori
		// 21 karakter gonder
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/besinIsmiTextBox")))
				.sendKeys("aaaaaaaaaaaaaaaaaaaaa");
		driver.findElementById("com.example.fitapp:id/urunuEkleButton").click();

		try {
			String isim_hata_mesaji = wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/message")))
					.getText();
			String isim_beklenen_hata = "İsim 20 karakterden uzun olamaz.";
			assertEquals(isim_hata_mesaji, isim_beklenen_hata);
			driver.findElementById("android:id/button1").click();
		} catch (Exception e) {
			System.out.println("isim kismina 20+ karakter girilebiliyor!");
		}

		// sayi gonder
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/besinIsmiTextBox")))
				.sendKeys("123");
		driver.findElementById("com.example.fitapp:id/urunuEkleButton").click(); // urun ekle buton

		try {
			String isim_hata_mesaji2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/message")))
					.getText();
			String isim_beklenen_hata2 = "İsim sayı içeremez.";
			assertEquals(isim_hata_mesaji2, isim_beklenen_hata2);
			driver.findElementById("android:id/button1").click();

		} catch (Exception e) {
			System.out.println("isim kismina rakam girilebiliyor!");
		}

		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/besinIsmiTextBox")))
				.sendKeys("elma"); // normal deger girme
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); // implicit wait

		// Test Case 12: tur bolumunde birden fazla secim yapılması
		driver.findElementById("com.example.fitapp:id/yiyecekRadioButton").click(); // tur = yiyecek
		driver.findElementById("com.example.fitapp:id/icecekRadioButton").click(); // tur = icecek

		int num_tur_selected = 0;
		MobileElement checkbox_yiyecek = (MobileElement) driver
				.findElementById(("com.example.fitapp:id/yiyecekRadioButton"));
		MobileElement checkbox_icecek = (MobileElement) driver
				.findElementById(("com.example.fitapp:id/icecekRadioButton"));
		if (checkbox_yiyecek.getAttribute("checked").equals("true")) {
			num_tur_selected++;
		}
		if (checkbox_icecek.getAttribute("checked").equals("true")) {
			num_tur_selected++;
		}

		if (num_tur_selected > 1) {
			System.out.println("Birden fazla tur secilebiliyor!");
		}

		// Test Case 13: birim bolumunde birden fazla secim yapılması -
		// tum seceneklere tikla
		driver.findElementById("com.example.fitapp:id/birimAdetRadioButton").click(); // birim = 1 adet
		driver.findElementById("com.example.fitapp:id/birimGramRadioButton").click(); // birim = 100 gr
		driver.findElementById("com.example.fitapp:id/birimLitreRadioButton").click(); // birim = 100 ml

		// isaretli olanların degerini 1 arttır
		int num_birim_selected = 0;
		MobileElement checkbox_adet = (MobileElement) driver
				.findElementById(("com.example.fitapp:id/birimAdetRadioButton"));
		MobileElement checkbox_gram = (MobileElement) driver
				.findElementById(("com.example.fitapp:id/birimGramRadioButton"));
		MobileElement checkbox_ml = (MobileElement) driver
				.findElementById(("com.example.fitapp:id/birimLitreRadioButton"));
		if (checkbox_adet.getAttribute("checked").equals("true")) {
			num_birim_selected++;
		}
		if (checkbox_gram.getAttribute("checked").equals("true")) {
			num_birim_selected++;
		}
		if (checkbox_ml.getAttribute("checked").equals("true")) {
			num_birim_selected++;
		}

		if (num_birim_selected > 1) {
			System.out.println("Birden fazla birim birlikte secilebiliyor!" + num_birim_selected);
		}

		// Test Case 14: kalori kısmına harf ve 6+ basamak sayi girilmesi
		driver.findElementById("com.example.fitapp:id/kaloriMiktariTextBox").sendKeys("asd"); // harf girme
		driver.findElementById("com.example.fitapp:id/urunuEkleButton").click();

		if (!driver.currentActivity().equals("com.example.fitapp")) {
			System.out.println("Kalori kismina harf girip hesaplamaya calisinca uygulama cöktü!");

			// ayni sayfayi tekrar ac
			try {
				setup();
			} catch (MalformedURLException e) {
				System.out.println("Uygulama yeniden acilamadi.");
			}
			besinEkle1(); // uygulama kapandıysa tekrar ac ve ornek veri ile doldur
			besinEkle2();
		} else {
			System.out.println("Kalori kismina harf yazilabiliyor!");
		}

		driver.findElementById("com.example.fitapp:id/kaloriMiktariTextBox").sendKeys("1234567"); // 6+ rakam girme
		driver.findElementById("com.example.fitapp:id/urunuEkleButton").click();

		try {
			String besinEkle_hata_mesaji = wait
					.until(ExpectedConditions.elementToBeClickable(By.id("android:id/message"))).getText();
			String besinEkle_beklenen_hata = "Kalori 6 karakterden uzun olamaz.";
			assertEquals(besinEkle_hata_mesaji, besinEkle_beklenen_hata);
			driver.findElementById("android:id/button1").click();
		} catch (Exception e) {
			System.out.println("Kalori kismina 6+ rakam girilebiliyor!");
		}

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // implicit wait
		driver.navigate().back();
	}

	public void besinEkle1() {

		MobileElement besin_ekle_buton = (MobileElement) wait
				.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/goToBesinEkleButton")));
		besin_ekle_buton.click();
		MobileElement isim = (MobileElement) wait
				.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/besinIsmiTextBox")));
		isim.sendKeys("havuc");
		MobileElement tur_yiyecek = (MobileElement) driver.findElementById("com.example.fitapp:id/yiyecekRadioButton");
		tur_yiyecek.click();
		MobileElement birim_1_adet = (MobileElement) driver
				.findElementById("com.example.fitapp:id/birimAdetRadioButton");
		birim_1_adet.click();
		MobileElement kalori = (MobileElement) driver.findElementById("com.example.fitapp:id/kaloriMiktariTextBox");
		kalori.sendKeys("30");
		MobileElement urun_ekle_buton = (MobileElement) driver.findElementById("com.example.fitapp:id/urunuEkleButton");
		urun_ekle_buton.click();
		MobileElement ok_buton = (MobileElement) wait
				.until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
		ok_buton.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // implicit wait
	}

	public void besinEkle2() {

		MobileElement isim = (MobileElement) wait
				.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/besinIsmiTextBox")));
		isim.sendKeys("ekmek");
		MobileElement tur_yiyecek = (MobileElement) driver.findElementById("com.example.fitapp:id/yiyecekRadioButton");
		tur_yiyecek.click();
		MobileElement birim_1_adet = (MobileElement) driver
				.findElementById("com.example.fitapp:id/birimAdetRadioButton");
		birim_1_adet.click();
		MobileElement kalori = (MobileElement) driver.findElementById("com.example.fitapp:id/kaloriMiktariTextBox");
		kalori.sendKeys("120");
		MobileElement urun_ekle_buton = (MobileElement) driver.findElementById("com.example.fitapp:id/urunuEkleButton");
		urun_ekle_buton.click();
		MobileElement ok_buton = (MobileElement) wait
				.until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
		ok_buton.click();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // implicit wait
	}

	public void testKaloriHesapla() {

		MobileElement kalori_hesapla_buton = (MobileElement) wait
				.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/goToKaloriHesaplaButton")));
		kalori_hesapla_buton.click();

		// ürün ara ve ekle
		MobileElement arama_kutusu = (MobileElement) driver.findElementById("com.example.fitapp:id/searchTextBox");
		arama_kutusu.sendKeys("havuc");
		MobileElement ekle_butonu = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.Button")));
		ekle_butonu.click();
		arama_kutusu.sendKeys("ekmek");
		ekle_butonu = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget."
						+ "LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget."
						+ "LinearLayout/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.Button")));
		ekle_butonu.click();

		// Test Case 17: Kalori deger kontrolu
		MobileElement kalori = (MobileElement) driver.findElementById("com.example.fitapp:id/toplamKaloriText");
		boolean kalori_degeri_guncel = (kalori.getText() != "Toplam Kalori 0cal");
		if (!kalori_degeri_guncel) {
			System.out.println("Kalori hesaplama özelliği hatalı");
		}

		// ürün çıkar
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); // implicit wait
		MobileElement cikar_butonu = (MobileElement) wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("	\r\n"
						+ "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget."
						+ "FrameLayout/android.view.ViewGroup/android.widget.ScrollView[2]/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.Button")));
		cikar_butonu.click();
		cikar_butonu = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath("	\r\n"
				+ "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget."
				+ "FrameLayout/android.view.ViewGroup/android.widget.ScrollView[2]/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.Button")));
		cikar_butonu.click();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // implicit wait
		driver.navigate().back(); // anasayfaya dön
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // implicit wait
	}

	public void ornekMusteriVeri() { // gecerli degerlerle musteri bilgilerini doldur

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElementById("com.example.fitapp:id/nameInput").sendKeys("Enes");
		driver.findElementById("com.example.fitapp:id/surNameInput").sendKeys("KILIC");
		driver.findElementById("com.example.fitapp:id/phoneInput").sendKeys("11111111111");
		driver.findElementById("com.example.fitapp:id/mailInput").sendKeys("enes@mail.com");
		driver.findElementById("com.example.fitapp:id/tcNoInput").sendKeys("11111111111");

	}

	public void musteriEkleCrashHandle() { // hatali input verilince uygulama cokerse tekrar eski haline getir

		if (!driver.currentActivity().equals("com.example.fitapp")) { // Uygulama cöktü mü
			System.out.println("Gecersiz deger girip hesaplamaya calisinca uygulama cöktü!");

			// uygulamayi tekrar ac
			try {
				setup();
			} catch (MalformedURLException e) {
				System.out.println("Uygulama yeniden acilamadi.");
			}
			driver.findElementById("com.example.fitapp:id/goToMusteriEkleButton").click(); // musteri ekleme sayfasına git
																							
			ornekMusteriVeri(); // uygulama kapandıysa tekrar ac ve ornek veri ile doldur
		}
	}

	public void testMusteriEkle() {

		driver.findElementById("com.example.fitapp:id/goToMusteriEkleButton").click(); // musteri ekleme sayfasına git

		// Test Case: 2.1 isim kısmına sayı ve 21+ karakter girme denemesi

		driver.findElementById("com.example.fitapp:id/nameInput").sendKeys("123");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/musteriEkleButton"))).click(); // musteri ekle butonun bas
																														
																														
																														
		try {
			String isim_hata_mesaji = wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/message")))
					.getText();
			String isim_beklenen_hata = "İsim sayı içeremez.";
			assertEquals(isim_hata_mesaji, isim_beklenen_hata);
			driver.findElementById("android:id/button1").click();
		} catch (Exception e) {
			System.out.println("isim kismina sayi girilebiliyor!");
		}

		driver.findElementById("com.example.fitapp:id/nameInput").sendKeys("aaaaaaaaaaaaaaaaaaaaa");// 21 harf girme
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/musteriEkleButton"))).click(); // musteri ekle butonun bas
																														
																														
																														
		try {
			String isim_hata_mesaji2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/message")))
					.getText();
			String isim_beklenen_hata2 = "İsim 20 karakterden uzun olamaz.";
			assertEquals(isim_hata_mesaji2, isim_beklenen_hata2);
			driver.findElementById("android:id/button1").click();
		} catch (Exception e) {
			System.out.println("isim kismina 21+ harf girilebiliyor!");
		}

		musteriEkleCrashHandle();

		// Test Case: 2_.2 soyisim kısmına sayı ve 21+ karakter girme denemesi

		driver.findElementById("com.example.fitapp:id/surNameInput").sendKeys("123");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/musteriEkleButton"))).click(); // musteri ekle butonun bas
																														
																														
																														
		try {
			String soyisim_hata_mesaji = wait
					.until(ExpectedConditions.elementToBeClickable(By.id("android:id/message"))).getText();
			String soyisim_beklenen_hata = "Soyisim sayı içeremez.";
			assertEquals(soyisim_hata_mesaji, soyisim_beklenen_hata);
			driver.findElementById("android:id/button1").click();
		} catch (Exception e) {
			System.out.println("Soyisim kismina sayi girilebiliyor!");
		}

		driver.findElementById("com.example.fitapp:id/surNameInput").sendKeys("aaaaaaaaaaaaaaaaaaaaa");// 21 harf girme
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/musteriEkleButton"))).click(); // musteri ekle butonun bas
																														
																														
																														
		try {
			String soyisim_hata_mesaji2 = wait
					.until(ExpectedConditions.elementToBeClickable(By.id("android:id/message"))).getText();
			String soyisim_beklenen_hata2 = "Soyisim 20 karakterden uzun olamaz.";
			assertEquals(soyisim_hata_mesaji2, soyisim_beklenen_hata2);
			driver.findElementById("android:id/button1").click();
		} catch (Exception e) {
			System.out.println("Soyisim kismina 21+ harf girilebiliyor!");
		}

		musteriEkleCrashHandle();

		// Test Case: 2.3 telefon numarası bölümüne harf ve 11den az rakam girme
		// denemesi
		driver.findElementById("com.example.fitapp:id/phoneInput").sendKeys("5");// 1 rakam girme
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/musteriEkleButton"))).click(); // musteri ekle butonun bas
																														
																														
																														

		try {
			String telNo_hata_mesaji = wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/message")))
					.getText();
			String telNo_beklenen_hata = "Telefon numarası 11 karakterli olacak şekilde girilmeli.";
			assertEquals(telNo_hata_mesaji, telNo_beklenen_hata);
			driver.findElementById("android:id/button1").click(); // ok butonu
		} catch (Exception e) {
			System.out.println("Telefon numarasina 11den az hane girilebiliyor!");
		}

		driver.findElementById("com.example.fitapp:id/phoneInput").sendKeys("aa");// harf girme
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/musteriEkleButton"))).click(); // musteri ekle butonun bas
																														
																														
																														

		try {
			String telNo_hata_mesaji2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/message")))
					.getText();
			String telNo_beklenen_hata2 = "Telefon numarası 11 karakterli olacak şekilde girilmeli.";
			assertEquals(telNo_hata_mesaji2, telNo_beklenen_hata2);
			driver.findElementById("android:id/button1").click(); // ok butonu
		} catch (Exception e) {
			System.out.println("Telefon numarasina karakter girilebiliyor!");
		}
		driver.findElementById("com.example.fitapp:id/phoneInput").sendKeys("11111111111");// gecerli deger girme

		// Test Case: 2.4 TC numarası yerine 11 karakterden fazla rakam girme denemesi
		driver.findElementById("com.example.fitapp:id/tcNoInput").sendKeys("2222222222222");// 11+ rakam girme
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/musteriEkleButton"))).click(); // musteri ekle butonun bas
																													
																														
																													

		try {
			String tcNo_hata_mesaji = wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/message")))
					.getText();
			String tcNo_beklenen_hata = "TC Kimlik No 11 karakterli olacak şekilde girilmeli.";
			assertEquals(tcNo_hata_mesaji, tcNo_beklenen_hata);
			driver.findElementById("android:id/button1").click(); // ok butonu
		} catch (Exception e) {
			System.out.println("Tc kimlik no kismina 11+ rakam girilebiliyor!");
		}

		// Test Case: 2.5 TC numarası kısmına harf girme denemesi
		driver.findElementById("com.example.fitapp:id/tcNoInput").sendKeys("aa");// harf girme
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/musteriEkleButton"))).click(); // musteri ekle butonun bas
																														
																														
																														

		try {
			String tcNo_hata_mesaji2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/message")))
					.getText();
			String tcNo_beklenen_hata2 = "TC Kimlik No 11 karakterli olacak şekilde girilmeli.";
			assertEquals(tcNo_hata_mesaji2, tcNo_beklenen_hata2);
			driver.findElementById("android:id/button1").click(); // ok butonu
		} catch (Exception e) {
			System.out.println("Tc kimlik no kismina harf girilebiliyor!");
		}

		// dogru degerler ile musteri ekle
		ornekMusteriVeri();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/musteriEkleButton"))).click();
		driver.findElementById("android:id/button1").click();

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.navigate().back(); // anasayfaya dön
	}

	public void testGirisCikis() { // giris-cikis sayfasina git. Baslik ve tablonun ekrana geldigini kontrol et

		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/goToGirisCikisLogButton")))
				.click();

		try {
			driver.findElementById("com.example.fitapp:id/EntryListContainer");// tablo sayfada mevcut mu
			String baslik = driver.findElementById("com.example.fitapp:id/textView13").getAttribute("text");
			assertEquals(baslik, "Müşteri giriş çıkış bilgileri"); // baslik dogru sekilde mevcut mu
		}

		catch (Exception a) {
			System.out.println("Müsteri giris cikis sayfasında hata!");
		}
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
