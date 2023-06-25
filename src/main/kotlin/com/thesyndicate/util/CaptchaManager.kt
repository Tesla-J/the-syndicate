package com.thesyndicate.util

import cn.apiclub.captcha.Captcha
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer
import cn.apiclub.captcha.gimpy.DropShadowGimpyRenderer
import cn.apiclub.captcha.text.producer.DefaultTextProducer

fun getCaptcha(): Captcha =
        Captcha.Builder(200, 50)
        .addText(DefaultTextProducer())
        .addBackground(GradiatedBackgroundProducer())
        .gimp(DropShadowGimpyRenderer())
        .addNoise()
        .build()

fun verifyCaptcha(captcha: Captcha, answer: String): Boolean = captcha.isCorrect(answer)