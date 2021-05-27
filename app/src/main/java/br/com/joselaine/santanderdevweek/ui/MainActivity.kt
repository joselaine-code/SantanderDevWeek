package br.com.joselaine.santanderdevweek.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.joselaine.santanderdevweek.R
import br.com.joselaine.santanderdevweek.data.Conta
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        buscarContaCliente()
    }

    private fun buscarContaCliente() {
        mainViewModel.buscarContaCliente().observe(this, Observer { result ->
            bindOnView(result)
        })
    }

    private fun bindOnView(conta: Conta) {
        val soma = conta.limite + conta.saldo
        "Olá, ${conta.cliente.nome}".also { findViewById<TextView>(R.id.tv_usuario).text = it }
        "Ag ${conta.agencia}".also { findViewById<TextView>(R.id.tv_agencia).text = it }
        "CC ${conta.numero}".also { findViewById<TextView>(R.id.tv_cc).text = it }
        conta.saldo.formatarValor().also { findViewById<TextView>(R.id.tv_saldo).text = it }
        soma.formatarValor().also { findViewById<TextView>(R.id.tv_limite).text = it }
        "${conta.cartao.numeroCartao}".also {
            findViewById<TextView>(R.id.tv_numeroCartao).text = it
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_1 -> {
                MaterialAlertDialogBuilder(applicationContext,
                    R.style.Base_ThemeOverlay_AppCompat_Dialog_Alert)
                    .setMessage(resources.getString(R.string.long_message))
                    .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
                        Toast.makeText(applicationContext, "Sinto muito!", Toast.LENGTH_LONG).show()
                    }
                    .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                        Toast.makeText(applicationContext, "Você receberá nossas novidades!", Toast.LENGTH_LONG).show()
                    }
                    .show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

fun Double.formatarValor(): String {
    val number = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
    return number.format(this)
}