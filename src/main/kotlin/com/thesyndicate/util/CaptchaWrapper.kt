package com.thesyndicate.util

import cn.apiclub.captcha.Captcha
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer
import cn.apiclub.captcha.gimpy.DropShadowGimpyRenderer
import cn.apiclub.captcha.text.producer.DefaultTextProducer
import org.springframework.stereotype.Component
import java.io.ByteArrayOutputStream
import java.util.*
import javax.imageio.ImageIO

@Component
class CaptchaWrapper {
    val captchaInstance: Captcha = getCaptcha()
    val captchaImage: String = encodeImage()
    var userCaptchaAnswer: String = ""


    fun encodeImage(): String{
        val baos = ByteArrayOutputStream()
        ImageIO.write(captchaInstance.image, "png", baos)
        baos.flush()
        val imageBytes = baos.toByteArray()
        baos.close()
        return Base64.getEncoder().encodeToString(imageBytes)
    }
}
fun getCaptcha(): Captcha =
    Captcha.Builder(200, 50)
        .addText(DefaultTextProducer())
        .addBackground(GradiatedBackgroundProducer())
        .gimp(DropShadowGimpyRenderer())
        .addNoise()
        .build()

fun verifyCaptcha(captcha: Captcha, answer: String): Boolean = captcha.isCorrect(answer)