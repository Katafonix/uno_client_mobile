package com.example.uno.data.base.retrofit

import android.webkit.CookieManager
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull


class SessionCookieJar : CookieJar {

    private val cookieStore = HashMap<HttpUrl?, List<Cookie>>()
    private val cookieManager = CookieManager.getInstance()

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        val host = url.host
        cookieStore[host.toHttpUrlOrNull()] = cookies
        cookieManager.removeAllCookie()
        val cookies1 = cookieStore[host.toHttpUrlOrNull()]
        if (cookies1 != null) {
            for (cookie in cookies1) {
                val cookieString =
                    cookie.name + "=" + cookie.value + "; path=" + cookie.path
                cookieManager.setCookie(cookie.domain, cookieString)
            }
        }
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val host = url.host
        val cookies = cookieStore[host.toHttpUrlOrNull()]
        return cookies ?: ArrayList()
    }

}