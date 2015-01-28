<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>AdCampSDK - Android</title>
<link rel="stylesheet" href="https://stackedit.io/res-min/themes/base.css" />
<script type="text/javascript" src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS_HTML"></script>
</head>
<body><div class="container"><p><div class="toc">
<ul>
<li><a href="#top">Руководство по интеграции SDK AdCamp для Android</a><ul>
<li><a href="#подготовка-к-работe">Подготовка к работe</a><ul>
<li><a href="#требования">Требования</a></li>
<li><a href="#загрузка">Загрузка</a></li>
<li><a href="#интеграция-sdk-в-ваш-проект">Интеграция SDK в ваш проект</a></li>
</ul>
</li>
<li><a href="#показ-рекламы">Показ рекламы</a><ul>
<li><a href="#рекламные-баннеры-встроенные-в-контент">Рекламные баннеры, встроенные в контент</a></li>
<li><a href="#отображение-баннера-как-элемента-списка-listview-gridview-и-другие-adapterview">Отображение баннера как элемента списка (ListView, GridView и другие AdapterView)</a></li>
<li><a href="#полноэкранные-рекламные-баннеры-interstitial">Полноэкранные рекламные баннеры (Interstitial)</a></li>
</ul>
</li>
</ul>
</li>
</ul>
<ul><li><a href="#versions">Изменения по версиям</a><li></ul>
</div>
</p>



<h1 id="top">Руководство по интеграции SDK AdCamp для Android</h1>



<h2 id="подготовка-к-работe">Подготовка к работe</h2>

<p>SDK AdCamp Android позволяет разработчикам размещать рекламные объявления в своем приложении. Эти объявления могут быть как текстовыми, так и графическими или межстраничными (картинки на весь экран).  <br>
Большой выбор дейcтвий при клике на рекламу позволяет направлять пользователей в Google Play Store, браузер, а также производить переход к картам, видео, к набору номера или смс. Опционально для объявлений можно настраивать географический (по GPS) и демографический таргетинг, передавая данные в запросе на рекламу.</p>

<p>Данный документ является пошаговой инструкцией по встраиванию SDK в приложение на Android. Документация по доступным классам и методам доступна здесь: <a href="http://tst.adcamp.ru/adcamp/android/doc/">http://tst.adcamp.ru/adcamp/android/doc/</a>.</p>



<h3 id="требования">Требования</h3>

<p>SDK AdCamp для платформы Android поддерживает:</p>

<ol>
<li><p>Устройства с операционной системой Android версии 2.2 и выше; в случае, если вы поддерживаете более ранние версии Android, вы должны использовать библиотеку опционально:</p>

<pre class="prettyprint"><code class="language-{.java} hljs avrasm">if (Build<span class="hljs-preprocessor">.VERSION</span><span class="hljs-preprocessor">.SDK</span>_INT &gt;= Build<span class="hljs-preprocessor">.VERSION</span>_CODES<span class="hljs-preprocessor">.FROYO</span>)
    AdsManager<span class="hljs-preprocessor">.getInstance</span>()<span class="hljs-preprocessor">.initialize</span>(this)<span class="hljs-comment">;</span></code></pre></li>
</ol>



<h3 id="загрузка">Загрузка</h3>

<p>Для начала вам необходимо загрузить следующие файлы:</p>

<ol>
<li>AdCampSDK для Android по ссылке: <a href="http://tst.adcamp.ru/adcamp/android/adcamp-sdk-latest.jar">http://tst.adcamp.ru/adcamp/android/adcamp-sdk-latest.jar</a></li>
<li><a href="http://tst.adcamp.ru/adcamp/android/adcamp-examples.zip">Проект демонстрационного приложения.</a> <br>
Данный пример содержит все варианты использования SDK и его можно использовать как практическое пособие при встраивании sdk в ваше собственное приложение</li>
</ol>

<hr>



<h3 id="интеграция-sdk-в-ваш-проект">Интеграция SDK в ваш проект</h3>

<ol>
<li>Добавить файл adcamp-sdk-x.xx.jar в папку libs вашего проекта</li>
<li><p>Добавить следующие разрешения в файл AndroidManifest.xml вашего проекта:</p>

<pre class="prettyprint"><code class="language-{.xml} hljs xml"><span class="hljs-comment">&lt;!-- Разрешение INTERNET обязательно нужно для получения рекламы --&gt;</span>
<span class="hljs-tag">&lt;<span class="hljs-title">uses-permission</span> <span class="hljs-attribute">android:name</span>=<span class="hljs-value">"android.permission.INTERNET"</span> /&gt;</span>
<span class="hljs-comment">&lt;!-- Разрешение ACCESS_NETWORK_STATE обязательно нужно для выбора креативов 
наиболее подходящего качества и получения ip-адреса --&gt;</span>
<span class="hljs-tag">&lt;<span class="hljs-title">uses-permission</span> <span class="hljs-attribute">android:name</span>=<span class="hljs-value">"android.permission.ACCESS_NETWORK_STATE"</span> /&gt;</span>
<span class="hljs-comment">&lt;!-- Разрешение WRITE_EXTERNAL_STORAGE обязательно нужно для кеширования  
рекламы --&gt;</span>
<span class="hljs-tag">&lt;<span class="hljs-title">uses-permission</span> <span class="hljs-attribute">android:name</span>=<span class="hljs-value">"android.permission.WRITE_EXTERNAL_STORAGE"</span> /&gt;</span></code></pre>

<p>Дополнительно: если ваше приложение, предполагает знание о геолокации пользователя, то мы рекомендуем добавить разрешение на получение местоположения пользователя. Это может значительно увеличить цену показываемой Вами рекламы.</p>

<pre class="prettyprint"><code class="language-{.xml} hljs xml"><span class="hljs-comment">&lt;!-- Разрешение ACCESS_COARSE_LOCATION помогает определять местоположение пользователя 
и отправлять эти данные на сервер для лучшего таргентирования. Необязательно. --&gt;</span>
<span class="hljs-tag">&lt;<span class="hljs-title">uses-permission</span> <span class="hljs-attribute">android:name</span>=<span class="hljs-value">"android.permission.ACCESS_COARSE_LOCATION"</span> /&gt;</span>
<span class="hljs-comment">&lt;!-- Разрешение ACCESS_FINE_LOCATION помогает определять местоположение пользователя 
и отправлять эти данные на сервер для лучшего таргентирования. Необязательно. --&gt;</span>
<span class="hljs-tag">&lt;<span class="hljs-title">uses-permission</span> <span class="hljs-attribute">android:name</span>=<span class="hljs-value">"android.permission.ACCESS_FINE_LOCATION"</span> /&gt;</span></code></pre></li>
<li><p>Добавить следующие Activity в файл AndroidManifest.xml:</p>

<pre class="prettyprint"><code class="language-{.xml} hljs xml">    <span class="hljs-tag">&lt;<span class="hljs-title">activity
</span>        <span class="hljs-attribute">android:name</span>=<span class="hljs-value">"ru.adcamp.ads.InterstitialBannerActivity"</span>
<span class="hljs-attribute">android:configChanges</span>=<span class="hljs-value">"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize"</span> /&gt;</span>
    <span class="hljs-tag">&lt;<span class="hljs-title">activity
</span>        <span class="hljs-attribute">android:name</span>=<span class="hljs-value">"ru.adcamp.ads.FullscreenVideoActivity"</span>
<span class="hljs-attribute">android:configChanges</span>=<span class="hljs-value">"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize"</span> /&gt;</span>
    <span class="hljs-tag">&lt;<span class="hljs-title">activity
</span>        <span class="hljs-attribute">android:name</span>=<span class="hljs-value">"ru.adcamp.ads.ExpandedBannerActivity"</span>
<span class="hljs-attribute">android:configChanges</span>=<span class="hljs-value">"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize"</span> /&gt;</span></code></pre></li>
<li><p><a href="https://developer.android.com/google/play-services/setup.html">Интегрировать Google Play Services в Ваше приложение</a></p></li>
<li><p>При старте приложения необходимо инициализировать SDK.  <br>
Вы можете сделать это в наследнике класса android.app.Application, либо в первом Activity Вашего приложения:</p>

<pre class="prettyprint"><code class="language-{.java} hljs cs"><span class="hljs-comment">// Инициализируем рекламное sdk</span>
AdsManager.getInstance().initialize(<span class="hljs-keyword">this</span>, <span class="hljs-keyword">true</span>, Log.INFO);</code></pre></li>
<li><p><strong>После того, как Вы закончили тестирование Вашего приложения, не забудьте отключить логирование действий SDK!</strong></p>

<pre class="prettyprint"><code class="language-{.java} hljs cs"><span class="hljs-comment">// Инициализируем рекламное sdk</span>
AdsManager.getInstance().initialize(<span class="hljs-keyword">this</span>, <span class="hljs-keyword">false</span>, Log.INFO);</code></pre></li>
</ol>



<h2 id="показ-рекламы">Показ рекламы</h2>



<h3 id="рекламные-баннеры-встроенные-в-контент">Рекламные баннеры, встроенные в контент</h3>

<p><em>К этому случаю прилагается <a href="http://tst.adcamp.ru/adcamp/android/adcamp-examples.zip">пример</a>. См. класс <strong>BannerAdActivity</strong>.</em> <br>
Для того, чтобы встроить баннер в свое приложение, вам необходимо:</p>

<ol>
<li><p>Добавить <a href="http://tst.adcamp.ru/adcamp/android/doc/ru/adcamp/ads/BannerAdView.html">BannerAdView</a> на нужный экран. <a href="http://tst.adcamp.ru/adcamp/android/doc/ru/adcamp/ads/BannerAdView.html">BannerAdView</a> можно добавлять в любой контейнер с любыми шириной и высотой.  <br>
Это можно сделать добавив этот класс в xml файле (обратите внимание, что вы можете указать placementId прямо внутри xml файла, но для этого нужно объявить namespace <code>xmlns:adcamp="http://adcamp.ru/android/scheme"</code>):</p>

<pre class="prettyprint"><code class="language-{.xml} hljs xml"><span class="hljs-pi">&lt;?xml version="1.0" encoding="utf-8"?&gt;</span>
<span class="hljs-tag">&lt;<span class="hljs-title">RelativeLayout</span> <span class="hljs-attribute">xmlns:android</span>=<span class="hljs-value">"http://schemas.android.com/apk/res/android"</span>
    <span class="hljs-attribute">xmlns:adcamp</span>=<span class="hljs-value">"http://adcamp.ru/android/scheme"</span>
    <span class="hljs-attribute">android:layout_width</span>=<span class="hljs-value">"match_parent"</span>
    <span class="hljs-attribute">android:layout_height</span>=<span class="hljs-value">"match_parent"</span> &gt;</span>

    <span class="hljs-tag">&lt;<span class="hljs-title">ru.adcamp.ads.BannerAdView
</span>        <span class="hljs-attribute">android:id</span>=<span class="hljs-value">"@+id/bannerView"</span>
        <span class="hljs-attribute">android:layout_width</span>=<span class="hljs-value">"match_parent"</span>
        <span class="hljs-attribute">android:layout_height</span>=<span class="hljs-value">"wrap_content"</span>
        <span class="hljs-attribute">adcamp:placementId</span>=<span class="hljs-value">MY_PLACEMENT_ID</span> /&gt;</span>

<span class="hljs-tag">&lt;/<span class="hljs-title">RelativeLayout</span>&gt;</span></code></pre>

<p>либо добавив его непосредственно в коде приложения:</p>

<pre class="prettyprint"><code class="language-{.java} hljs avrasm">BannerAdView bannerView = new BannerAdView(this)<span class="hljs-comment">;</span>
bannerView<span class="hljs-preprocessor">.setPlacementId</span>(MY_PLACEMENT_ID)<span class="hljs-comment">;</span>
rootView<span class="hljs-preprocessor">.addView</span>(bannerView, ViewGroup<span class="hljs-preprocessor">.LayoutParams</span><span class="hljs-preprocessor">.MATCH</span>_PARENT, ViewGroup<span class="hljs-preprocessor">.LayoutParams</span><span class="hljs-preprocessor">.WRAP</span>_CONTENT)<span class="hljs-comment">;</span></code></pre>

<p style=""><b>Идентификатор placementId</b> вы можете получить по запросу на электронную почту account@adcamp.ru в письме необходимо указать название площадки и тип плейсмента.</p>

<p>Баннер будет иметь размер 0х0 до тех пор, пока его содержимое не будет полностью подгружено и отображено.  Поэтому Вам не нужно заботиться о видимости или размере баннера. Также обратите внимание, что если вы используете метод <a href="http://tst.adcamp.ru/adcamp/android/doc/ru/adcamp/ads/BannerAdView.html#showAd%28%29">showAd()</a> без явного указания размер баннера, то загрузка баннера не начнется до определения <a href="http://tst.adcamp.ru/adcamp/android/doc/ru/adcamp/ads/BannerAdView.html">BannerAdView</a> своих размеров.  <br>
<strong>Т.е. <a href="http://tst.adcamp.ru/adcamp/android/doc/ru/adcamp/ads/BannerAdView.html">BannerAdView</a> не начнет загрузку, пока он или его контейнер имеют видимость GONE.</strong> </p></li>
<li><p>Отобразить рекламу в созданном <a href="http://tst.adcamp.ru/adcamp/android/doc/ru/adcamp/ads/BannerAdView.html">BannerAdView</a>. Для этого вызовите метод <a href="http://tst.adcamp.ru/adcamp/android/doc/ru/adcamp/ads/BannerAdView.html#showAd%28%29">showAd()</a> или метод <a href="http://tst.adcamp.ru/adcamp/android/doc/ru/adcamp/ads/BannerAdView.html#showAd%28ru.adcamp.ads.AdRequest%29">showAd(AdRequest)</a>, если вы хотите уточнить параметры запроса (указать информацию о пользователе, приложении и т.д.)</p></li>
<li><p>(Опционально) Вы можете добавить наблюдателя, чтобы получать уведомления от <a href="http://tst.adcamp.ru/adcamp/android/doc/ru/adcamp/ads/BannerAdView.html">BannerAdView</a> с помощью метода <a href="http://tst.adcamp.ru/adcamp/android/doc/ru/adcamp/ads/BannerAdView.html#setBannerAdViewListener%28ru.adcamp.ads.BannerAdView.BannerAdViewListener%29">setBannerAdViewListener(BannerAdViewListener)</a></p></li>
<li><p>Добавить вызовы <a href="http://tst.adcamp.ru/adcamp/android/doc/ru/adcamp/ads/BannerAdView.html#onResume%28%29">bannerView.onResume()</a>, <a href="http://tst.adcamp.ru/adcamp/android/doc/ru/adcamp/ads/BannerAdView.html#onPause%28%29">bannerView.onPause()</a> и <a href="http://tst.adcamp.ru/adcamp/android/doc/ru/adcamp/ads/BannerAdView.html#onDestroy%28%29">bannerView.onDestroy()</a> в соответсвующие вызовы родительского Activity: </p>

<pre class="prettyprint"><code class="language-{.java} hljs java"><span class="hljs-annotation">@Override</span>
<span class="hljs-keyword">protected</span> <span class="hljs-keyword">void</span> <span class="hljs-title">onResume</span>() {
    <span class="hljs-keyword">super</span>.onResume();
    bannerView.onResume();
}

<span class="hljs-annotation">@Override</span>
<span class="hljs-keyword">protected</span> <span class="hljs-keyword">void</span> <span class="hljs-title">onPause</span>() {
    <span class="hljs-keyword">super</span>.onPause();
    bannerView.onPause();
}

<span class="hljs-annotation">@Override</span>
<span class="hljs-keyword">protected</span> <span class="hljs-keyword">void</span> <span class="hljs-title">onDestroy</span>() {
    <span class="hljs-keyword">super</span>.onDestroy();
    bannerView.onDestroy();
}</code></pre></li>
<li><p>(Опционально)  Вы можете вызвать дополнительное конверсионное событие в нужный момент, вызвав метод <a href="http://tst.adcamp.ru/adcamp/android/doc/ru/adcamp/ads/BannerAdView.html#trackUserEvent-ru.adcamp.ads.BannerAdView.UserEvent-">bannerView.trackUserEvent(UserEvent)</a>:</p>

<pre class="prettyprint"><code class="language-{.java} hljs avrasm">
bannerView<span class="hljs-preprocessor">.trackUserEvent</span>(UserEvent<span class="hljs-preprocessor">.USER</span>_EVENT_1)<span class="hljs-comment">;</span></code></pre>

<p>Конкретный смысл каждого типа пользовательского события задается определяется самим разработчиком и задается на этапе создания рекламной кампании.</p></li>
</ol>



<h3 id="отображение-баннера-как-элемента-списка-listview-gridview-и-другие-adapterview">Отображение баннера как элемента списка (ListView, GridView и другие AdapterView)</h3>

<p><em>К этому случаю прилагается <a href="http://tst.adcamp.ru/adcamp/android/adcamp-examples.zip">пример</a>. См. класс <strong>BannerInListViewActivity</strong>.</em> <br>
Если Вам необходимо встроить баннерную рекламу каким-то элементом списка, Вам потребуется создать специальный BaseAdapter, получающий данные из Вашего обычного адаптера и добавляющий в эти данные один или несколько баннеров:</p>



<pre class="prettyprint"><code class="language-{.java} hljs java"><span class="hljs-javadoc">/** Для добавления баннера мы создадим свой собственный {@link BaseAdapter},
 * работающий как обертка над уже существующим {@link BaseAdapter}.
 * Нам потребуется вызывать методы onResume, onPause и onDestroy этого адаптера */</span>
<span class="hljs-keyword">public</span> <span class="hljs-keyword">static</span> <span class="hljs-class"><span class="hljs-keyword">class</span> <span class="hljs-title">AdCampAdapter</span> <span class="hljs-keyword">extends</span> <span class="hljs-title">BaseAdapter</span> {</span>

    <span class="hljs-keyword">private</span> <span class="hljs-keyword">final</span> Context context;
    <span class="hljs-keyword">private</span> <span class="hljs-keyword">final</span> BaseAdapter adapter;
    <span class="hljs-keyword">private</span> <span class="hljs-keyword">final</span> String placementId;
    <span class="hljs-keyword">private</span> <span class="hljs-keyword">final</span> <span class="hljs-keyword">int</span> adPosition;

    <span class="hljs-keyword">private</span> BannerAdView bannerView;

    <span class="hljs-javadoc">/**
     * Создает новый адаптер, использующий данные из существующего адаптера, но дополнительно
     * вставляющий один рекламный баннер до указанной позиции.
     *<span class="hljs-javadoctag"> @param</span> context контекст, в котором будет отображаться этот адаптер
     *<span class="hljs-javadoctag"> @param</span> adapter адаптер, из которого будут браться данные
     *<span class="hljs-javadoctag"> @param</span> placementId идентификатор рекламного места
     *<span class="hljs-javadoctag"> @param</span> adPosition в каком месте нужно вставить баннер
     */</span>
    <span class="hljs-keyword">public</span> <span class="hljs-title">AdCampAdapter</span>(Context context, BaseAdapter adapter, String placementId, <span class="hljs-keyword">int</span> adPosition) {
        <span class="hljs-keyword">super</span>();
        <span class="hljs-keyword">this</span>.context = context;
        <span class="hljs-keyword">this</span>.adapter = adapter;
        <span class="hljs-keyword">this</span>.placementId = placementId;
        <span class="hljs-keyword">this</span>.adPosition = adPosition;
    }

    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">onResume</span>() {
        <span class="hljs-keyword">if</span> (bannerView != <span class="hljs-keyword">null</span>) {
            bannerView.onResume();
        }
    }

    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">onPause</span>() {
        <span class="hljs-keyword">if</span> (bannerView != <span class="hljs-keyword">null</span>) {
            bannerView.onPause();
        }
    }

    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">onDestroy</span>() {
        <span class="hljs-keyword">if</span> (bannerView != <span class="hljs-keyword">null</span>) {
            bannerView.onDestroy();
        }
    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">int</span> <span class="hljs-title">getCount</span>() {
        <span class="hljs-keyword">int</span> count = adapter.getCount();
        <span class="hljs-keyword">if</span> (count &gt;= adPosition) {
            <span class="hljs-keyword">return</span> count + <span class="hljs-number">1</span>;
        }
        <span class="hljs-keyword">return</span> count;
    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> Object <span class="hljs-title">getItem</span>(<span class="hljs-keyword">int</span> position) {
        <span class="hljs-keyword">return</span> adapter.getItem(getOffsetPosition(position));
    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">long</span> <span class="hljs-title">getItemId</span>(<span class="hljs-keyword">int</span> position) {
        <span class="hljs-keyword">return</span> adapter.getItemId(getOffsetPosition(position));
    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> View <span class="hljs-title">getView</span>(<span class="hljs-keyword">int</span> position, View convertView, ViewGroup parent) {
        <span class="hljs-keyword">if</span> (position == adPosition) {
            <span class="hljs-keyword">if</span> (convertView <span class="hljs-keyword">instanceof</span> BannerAdView) {
                <span class="hljs-keyword">return</span> convertView;
            } <span class="hljs-keyword">else</span> {
                <span class="hljs-keyword">if</span> (bannerView == <span class="hljs-keyword">null</span>) {
                    bannerView = <span class="hljs-keyword">new</span> BannerAdView(context, <span class="hljs-keyword">null</span>);
                    bannerView.setPlacementId(placementId);
                    bannerView.showAd();
                }
                <span class="hljs-keyword">return</span> bannerView;
            }
        } <span class="hljs-keyword">else</span> {
            <span class="hljs-keyword">return</span> adapter.getView(getOffsetPosition(position), convertView, parent);
        }
    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">int</span> <span class="hljs-title">getViewTypeCount</span>() {
        <span class="hljs-keyword">return</span> adapter.getViewTypeCount() + <span class="hljs-number">1</span>;
    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">int</span> <span class="hljs-title">getItemViewType</span>(<span class="hljs-keyword">int</span> position) {
        <span class="hljs-keyword">if</span> (position == adPosition) {
            <span class="hljs-keyword">return</span> adapter.getViewTypeCount();
        } <span class="hljs-keyword">else</span> {
            <span class="hljs-keyword">return</span> adapter.getItemViewType(getOffsetPosition(position));
        }
    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">boolean</span> <span class="hljs-title">areAllItemsEnabled</span>() {
        <span class="hljs-keyword">return</span> <span class="hljs-keyword">false</span>;
    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">boolean</span> <span class="hljs-title">isEnabled</span>(<span class="hljs-keyword">int</span> position) {
        <span class="hljs-keyword">return</span> (position == adPosition) || adapter.isEnabled(getOffsetPosition(position));
    }

    <span class="hljs-keyword">private</span> <span class="hljs-keyword">int</span> <span class="hljs-title">getOffsetPosition</span>(<span class="hljs-keyword">int</span> position) {
        <span class="hljs-keyword">if</span> (position &gt;= adPosition) {
            <span class="hljs-keyword">return</span> position - <span class="hljs-number">1</span>;
        }
        <span class="hljs-keyword">return</span> position;
    }

}</code></pre>

<p>После этого, вместо того, чтобы использовать Ваш обычный адаптер:</p>



<pre class="prettyprint"><code class="language-{.java} hljs avrasm">BaseAdapter yourAdapter = new ArrayAdapter(this, android<span class="hljs-preprocessor">.R</span><span class="hljs-preprocessor">.layout</span><span class="hljs-preprocessor">.simple</span>_list_item_1, items)<span class="hljs-comment">;</span>
listView<span class="hljs-preprocessor">.setAdapter</span>(yourAdapter)<span class="hljs-comment">;</span></code></pre>

<p>Используйте адаптер AdCampAdapter:</p>



<pre class="prettyprint"><code class="language-{.java} hljs cs">BaseAdapter yourAdapter = <span class="hljs-keyword">new</span> ArrayAdapter(<span class="hljs-keyword">this</span>, android.R.layout.simple_list_item_1, items);
adCampAdapter = <span class="hljs-keyword">new</span> AdCampAdapter(<span class="hljs-keyword">this</span>, yourAdapter, <span class="hljs-string">"your_placement_id"</span>, <span class="hljs-number">3</span>);
listView.setAdapter(adCampAdapter);</code></pre>

<p>Также, не забудьте вызывать методы onResume, onPause и onDestroy вашего AdCampAdapter из соответствующих методов Activity или Fragment:</p>



<pre class="prettyprint"><code class="language-{.java} hljs java"><span class="hljs-annotation">@Override</span>
<span class="hljs-keyword">protected</span> <span class="hljs-keyword">void</span> <span class="hljs-title">onResume</span>() {
    <span class="hljs-keyword">super</span>.onResume();
    adCampAdapter.onResume();
}

<span class="hljs-annotation">@Override</span>
<span class="hljs-keyword">protected</span> <span class="hljs-keyword">void</span> <span class="hljs-title">onPause</span>() {
    <span class="hljs-keyword">super</span>.onPause();
    adCampAdapter.onPause();
}

<span class="hljs-annotation">@Override</span>
<span class="hljs-keyword">protected</span> <span class="hljs-keyword">void</span> <span class="hljs-title">onDestroy</span>() {
    <span class="hljs-keyword">super</span>.onDestroy();
    adCampAdapter.onDestroy();
}</code></pre>



<h3 id="полноэкранные-рекламные-баннеры-interstitial">Полноэкранные рекламные баннеры (Interstitial)</h3>

<p><em>К этому случаю прилагается <a href="http://tst.adcamp.ru/adcamp/android/adcamp-examples.zip">пример</a>. См. класс <strong>InterstitialActivity</strong>.</em> <br>
Начиная с версии 2.0 AdCamp SDK, полноэкранные рекламные баннеры могут быть отображены только после предварительной загрузки, поэтому процесс показа полноэкранного баннера всегда будет состоять из двух шагов.</p>

<p>Сначала, подготовьте баннер к показу с помощью метода <a href="http://tst.adcamp.ru/adcamp/android/doc/ru/adcamp/ads/InterstitialBannerActivity.html#preload%28java.lang.String,%20ru.adcamp.ads.InterstitialBannerActivity.OnPreloadListener%29">preload(String, OnPreloadListener)</a> или метод <a href="http://tst.adcamp.ru/adcamp/android/doc/ru/adcamp/ads/InterstitialBannerActivity.html#preload%28ru.adcamp.ads.AdRequest, java.lang.String, ru.adcamp.ads.InterstitialBannerActivity.OnPreloadListener%29">preload(AdRequest, String, OnPreloadListener)</a>, если вы хотите уточнить параметры запроса (указать информацию о пользователе, приложении и т.д.):</p>



<pre class="prettyprint"><code class="language-{.java} hljs java"><span class="hljs-comment">/* Загружаем рекламу используя наш placementId */</span>
InterstitialBannerActivity.preload(MY_PLACEMENT_ID, <span class="hljs-keyword">new</span> OnPreloadListener() {
    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">onInterstitialAdReady</span>(Ad ad) {
        <span class="hljs-comment">/* Реклама готова и может быть показана в любой момент */</span>
        prepairedAd = ad;
    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">onInterstitialAdFailed</span>(ErrorType error, String errorDescription) {
        <span class="hljs-comment">/* Не удалось загрузить рекламу - в этом месте вы можете перейти к показу
         * рекламы из другой рекламной сети */</span>
    }
});</code></pre>

<p>После того, как баннер загружен, покажите подготовленный баннер используя метод <a href="http://tst.adcamp.ru/adcamp/android/doc/ru/adcamp/ads/InterstitialBannerActivity.html#show%28Context,%20ru.adcamp.ads.Ad%29">show(Context, Ad)</a>:</p>



<pre class="prettyprint"><code class="language-{.java} hljs sql">InterstitialBannerActivity.<span class="hljs-operator"><span class="hljs-keyword">show</span>(this, prepairedAd);</span></code></pre>


<h1 id="versions">Изменения по версиям</h1>
<h3>ADCampSDK v2.8</h3>
<p>- добавлены дополнительные конверсионные события</p>
</div></body>
</html>
