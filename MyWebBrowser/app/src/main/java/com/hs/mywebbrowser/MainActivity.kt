package com.hs.mywebbrowser

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat.startActivity
import com.hs.mywebbrowser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onBackPressed() {
        if ( binding.webView.canGoBack()){
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 웹뷰 기본 설정
        binding.webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
        }
        binding.webView.loadUrl("http://www.google.com")

        binding.urlEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                binding.webView.loadUrl(binding.urlEditText.text.toString())
                true
            } else {
                false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {   // 메뉴 아이템으로 분기를 선택한다.
            R.id.action_google,R.id.action_home -> {  // 구글, 집 아이콘을 클릭하면 구글 페이지로 로딩한다.
                binding.webView.loadUrl("http://www.google.com")
                return true
            }
            R.id.actoin_naver,R.id.action_home -> { // 네이버를 클릭하면 네이버 페이지를 로딩한다.
                binding.webView.loadUrl("http://www.naver.com")
                return true
            }
            R.id.action_daum,R.id.action_home -> { // 다음을 클릭하면 다음 페이지를 로딩한다.
                binding.webView.loadUrl("http://www.daum.com")
                return true
            }
            R.id.action_call -> { // 연락처를 클릭하면 전화 앱을 연다. 이러한 방식을 암시적 인텐트라고 한다.
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:031-123-4567")
                if(intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
                return true
            }
            R.id.action_send_text -> { // 문자 보내기 코드를 작성한다.
                // 문자 보내기
                return true
            }
            R.id.action_email -> { // 이메일 보내기 코드를 작성한다.
                //이메일 보내기
                return true
            }
        }
        return super.onOptionsItemSelected(item) // when문에서는 각 메뉴 처리를 끝내고 true를 반환했다. 내가 처리하고자 하는 경우를 제외한 그 외의 경우에는
        // super 메서드를 호출하는 것이 안드로이드 시스템에서의 보편적인 규칙이다.
    }


}

