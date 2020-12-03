package br.senac.redditcover.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.senac.redditcover.R
import com.heinrichreimersoftware.materialintro.app.IntroActivity
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide

class Slides : IntroActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_slides)

        isButtonBackVisible = false

        addSlide(

            SimpleSlide.Builder()
                .background(R.color.color1)
                .image(R.drawable.rdlogo)
                .title("Reddit Cover")
                .description("Fórum de Discussões sobre Tecnologia")
                .build()
        )
        addSlide(

            SimpleSlide.Builder()
                .background(R.color.color1)
                .image(R.drawable.login)
                .title("Faça Seu Login")
                .description("Participe de nossa comunidade, para ter acesso completo ao nosso Fórum")
                .build()
        )
    }

    override fun onDestroy() {
        super.onDestroy()

        var intent = Intent(this, HomeActivity::class.java )
        startActivity(intent)
        finish()
    }
}