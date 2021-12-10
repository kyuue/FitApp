/*Appium Test  Projesi - FitApp
 * Muhammed Baki Çaký - 10.12.2021
 * */
package fitApp_Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.clipboard.ClipboardContentType;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

		// Besin ekle
		testBesinEkle1();
		testBesinEkle2();
		testKaloriHesapla();
		testBmiHesapla();
		testEgitmenEkle();
		testEgitmenSec();
	}

	public void testBesinEkle1() {

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
	}

	public void testBesinEkle2() {

		MobileElement isim = (MobileElement) wait
				.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/besinIsmiTextBox")));
		isim.sendKeys("ekmek");
		MobileElement tur_yiyecek = (MobileElement) driver.findElementById("com.example.fitapp:id/yiyecekRadioButton");
		tur_yiyecek.click();
		MobileElement birim_1_adet = (MobileElement) driver.findElementById("com.example.fitapp:id/birimAdetRadioButton");
		birim_1_adet.click();
		MobileElement kalori = (MobileElement) driver.findElementById("com.example.fitapp:id/kaloriMiktariTextBox");
		kalori.sendKeys("120");
		MobileElement urun_ekle_buton = (MobileElement) driver.findElementById("com.example.fitapp:id/urunuEkleButton");
		urun_ekle_buton.click();
		MobileElement ok_buton = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
		ok_buton.click();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); // implicit wait
		driver.navigate().back(); // ana sayfaya dön
	}

	public void testKaloriHesapla() {

		MobileElement kalori_hesapla_buton = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/goToKaloriHesaplaButton")));
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

		// kalori deðeri kontrolü
		MobileElement kalori = (MobileElement) driver.findElementById("com.example.fitapp:id/toplamKaloriText");
		boolean kalori_degeri_guncel = (kalori.getText() != "Toplam Kalori 0cal");
		if (!kalori_degeri_guncel) {
			System.out.println("Kalori hesaplama özelliði hatalý");
		}

		// ürün çýkar
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

		driver.navigate().back();	// anasayfaya dön
	}

	public void testBmiHesapla() {

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

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); // implicit wait
		driver.navigate().back(); // ana sayfaya dön

	}

	public void testEgitmenEkle() {

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

		// fotoðraf ekle
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

		// fotoðraf seç
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
		driver.navigate().back();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); // implicit wait

	}

	public void testEgitmenSec() {

		MobileElement egitmen_sec_buton = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.fitapp:id/goToEgitmenSec")));
		egitmen_sec_buton.click();
		MobileElement egitmeni_ara = (MobileElement) driver.findElementById("com.example.fitapp:id/egitmeniAraButton");
		egitmeni_ara.click();

		//arama ekranýna geçme
		try {
			driver.findElementById("com.android.dialer:id/digits");
			System.out.println("Success");
		} catch (NoSuchElementException e) {
			System.out.println("Eðitmeni arama baþarýsýz!");
		}
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
