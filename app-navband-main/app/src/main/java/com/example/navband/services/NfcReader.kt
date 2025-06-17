package com.example.navband.services

import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.util.Log

class NfcReader(
    private val activity: Activity,
    private val tagScanned: (String) -> Unit
) {
    private var nfcAdapter : NfcAdapter? = NfcAdapter.getDefaultAdapter(activity)

    fun enableForegroundDispatch() {
        // Inpede que o NFC abra a mesma página dnv
        val intent = Intent(activity, activity.javaClass).apply {
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }

        //pendingIntent = Gera um token que o sistema pode usar para executar uma ação futura
        val pendingIntent = PendingIntent.getActivity(
            activity, 0, intent, PendingIntent.FLAG_MUTABLE
        )
        //Filtro para fazer com q a activity só receba coisas relacionadas à NFC
        val filters = arrayOf(IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED))

        nfcAdapter?.enableForegroundDispatch(activity, pendingIntent, filters, null)
    }

    fun disableForegroundDispatch() {
        // Informa para o sistema que a activity não quer mais usar o NFC
        nfcAdapter?.disableForegroundDispatch(activity)
    }

    // Processa uma intent que pode conter dados NFC
    fun handleIntent(intent: Intent?) {
        // Verifica se NFC foi detectada

        /*
        ACTION_NDEF_DISCOVERED: tag contém dados no formato NDEF (como texto, links, etc).

        ACTION_TAG_DISCOVERED: tag foi detectada, mas talvez não tenha NDEF.

        ACTION_TECH_DISCOVERED: a tag tem tecnologias específicas (ex: Mifare, etc).
        */

        if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent?.action ||
            NfcAdapter.ACTION_TAG_DISCOVERED == intent?.action ||
            NfcAdapter.ACTION_TECH_DISCOVERED == intent?.action) {

            // Coleta dados NDEF da tag
            val rawMessages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
            if (rawMessages != null) {
                // Converte dados bruto spara NdefMessage
                val messages = rawMessages.map { it as NdefMessage }

                for (msg in messages) {
                    for (record in msg.records) {
                        val payload = record.payload
                        val text = String(payload) // ou trate melhor se for TextRecord
                        Log.d("NFC", "Mensagem lida: $text")
                    }
                }
            }
        }

    }
}