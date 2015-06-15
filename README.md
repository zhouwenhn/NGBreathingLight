# BreathingLight v1.0.0

It's a utils for setting a breathing light of views.

####使用说明

It's very easy:

```java
// start
BreathingLight.setBreathing(findViewById(R.id.any_view));
// stop
BreathingLight.stopBreathing(findViewById(R.id.any_view));
```

####介绍

其实实现方法很简单，就是开个线程隔 38毫秒调用一下 setBackgroundColor 而已，之所以是 38mm 是因为只要间隔小于50毫秒的刷新率，人的肉眼就会当成是连续的；这样可以避免过多刷新，也是比较友好。

关键就是一个类呼吸函数，首先想到正弦函数，但正弦函数用起来感觉有点呼吸急促，因为它的呼和吸是均衡的，之后 Google 了下，找到一个更加逼真的呼吸函数：

<img src="img/s1.png"/>

<img src="img/s2.png"/>

##代码实现就是：
```java
    private static double getBreathingY(long time, int n, int t) {
        float k = 1.0f / 3;
        float pi = 3.1415f;
        float x = time / 1000.0f;
        t = (int) (t / 1000.0f);
        if (x >= ((n - 1) * t) && x < ((n - (1 - k)) * t)) {
            double i = pi / (k * t) * ((x - (0.5f * k * t)) - (n - 1) * t);
            return 0.5f * Math.sin(i) + 0.5f;
        } else if (x >= ((n - (1 - k)) * t) && x < n * t) {
            double j = pi / ((1 - k) * t) * ((x - (0.5f * (3 - k) * t)) - (n - 1) * t);
            double one = 0.5f * Math.sin(j) + 0.5f;
            return one * one;
        }
        return 0;
    }
```

##这里关键是要在一个周期之后传入一个新的 n，我的做法就是判断  if (diffTime > n * t) 然后使 n++;
