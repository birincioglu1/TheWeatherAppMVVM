# TheWeatherAppMVVM
https://www.metaweather.com/ üzerinden güncel hava durumu bilgileri alınarak uygulamaya eklendi.<br/>
Kullanıcının mevcut konumu istenerek konumuna yakın şehirler listelenmektedir. Seçilen şehrin hava durumu bilgisi kullanıcıya sunulmaktadır.<br/>

## Kullanılan Kütüphaneler
`implementation 'androidx.navigation:navigation-fragment-ktx:2.3.1'`<br/>
`implementation "androidx.room:room-runtime:$roomVersion"`<br/>
`implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.0"`<br/>
`implementation "io.reactivex.rxjava2:rxjava:2.2.6"`<br/>
`implementation 'android.arch.lifecycle:extensions:1.1.1'`<br/>
`implementation 'com.github.delight-im:Android-SimpleLocation:v1.0.1'`<br/>
`implementation 'com.github.bumptech.glide:glide:4.11.0'`<br/>
`implementation "androidx.preference:preference:$preferencesVersion"`<br/>
`implementation 'com.squareup.retrofit2:converter-gson:2.3.0'`<br/>
`implementation 'com.squareup.retrofit2:retrofit:2.3.0'`<br/>
`implementation 'android.arch.lifecycle:extensions:1.1.1'`<br/>

## Kullanılan Mimari 
"MVVM"<br/>

## Uygulama İçeriği

-OOP, SOLİD prensipleri uygulandı
-API client olarak Retrofit ile yapıldı.
-Room ile SQLite kullanıldı.
-SharedPreferences ile küçük veriler tutuldu.
-KTX ile Extension functions kullanıldı.
-Navigation kullanıldı.
-LiveData/ ViewModel , Single, RxJava, Coroutines gibi tool'lar kullanıldı.
-Glide ile görseller getirildi.
-Montion Layout kullanıldı.
-Fragmentler arası geçişlerde animasyon kullanıldı.
-Custom font ve uygulama iconu eklendi.