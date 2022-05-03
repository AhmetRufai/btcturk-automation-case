# BtcTurk Test Otomasyon Case #

* Otomasyon java programlama dili ile yazıldı. Version olarak 17.0.2 kullanıldı.
* Projede build teknolojisi olarak Maven kullanıldı.
* UI otomasyonu için Selenium ve BDD fremawork'ü olan Cucumber kullanıldı.
* Raporlama için Cucumber'ın report plugin'i TestNGRunner class'ında tanımlanarak kullanıldı.
* Ayrıca detaylı ve version'lu raporlama için extentreports'un BDD otomasyonları için geliştirilen Spark raporlama
  kullanıldı
* Hata alan senaryolarda ekran resmi alınıp report html dosyaları içerisinde gösterilecek şekilde geliştirildi.
* Loglama işlemleri report dosyasında görünecek şekilde yapıldı.
* Api testleri için Rest Assured library kullanıldı.
* Cross Browser ve paralel test için TesNG'in Cucumber için olan library'si kullanıldı.
* Otomasyon, ortam bazlı çalışabilir şekilde yazıldı. Prod ortamda çalıştırmak için ilgili runner'ın Vm Option'una
  -Denv=prod yazılmalıdır.
* Ortam url bilgileri config-prod.yml ve config-qa.yml dosyaları içerisinden okunmaktadır.
* Api testi için main/java altında modelleme yapılmıştır.
* Api testlerinde her ep için main/java/com/api altında client class'ları oluşturulmuştur. Bu class'lar Generic bir
  class'dan implement edilmiştir.
* Bazı method'ları daha pratik kullanmak ve okunabilirliği artırmak için lombok library ve plugin'i kullanıldı.
* Web driver'ları manual indirip path göstermek yerine webdrivermanager library kullanılarak otomatik indirilmeleri
  sağlandı.
* Yml formatındaki dosyalardan veri okuyabilmek için jackson-dataformat-yaml library kullanıldı.
* Rest assured ile istek atarken body olarak Object tipinin kullanılabilmesi için jackson-datatype-jdk8 library
  kullanıldı.
* Detaylı loglama için slf4j library kullanıldı.
* Otomasyon POM ve OOP prensiplerine göre tasarlandı.

### NOTLAR ###

* Get Users Page ep'sinde hep success response döndüğü için kontroller bu doğrultuda yapıldı.
* Selenium 4+ versionu webdrivermanager ile uyumlu çalışmadığı için 3.141.59 versionu kullanıldı.
* Testlerin paralel koşulması ve report oluşması için testgn.xml dosyası TestNG runner'ı ile çalıştırılmalı.
* Cucumber report dosyası target/cucumber altında oluşmaktadır.
* Extent Spark report dosyaları sparkreports altında oluşmaktadır.
* Son oluşan report dosyası proje dizinine Report.html olarak eklendi.
* Otomasyonun başarılı çalıştığının ve report dosyasının oluştuğunu göstermek için ekran kaydı alınmıştır. İlgili ekran
  kaydı proje içerisindeki ScreenRecord.mov adlı videodur.
* Gönderilen otomasyon case çalışma dosyası proje içerisine eklenmiştir.

