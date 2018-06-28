package com.organicfarmer.paezand.kotlinexamplesoreo.ui.topten

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.organicfarmer.paezand.kotlinexamplesoreo.R
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class TopTenActivity: MvpActivity<ITopTenView, ITopTenPresenter>(), ITopTenView {

    private val TAG = "TopTenActivity"

    override fun createPresenter(): ITopTenPresenter {
        return TopTenPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_ten)

        Log.d(TAG, "=====================OnCreate called")
        val downloadData = DownloadData()
        downloadData.execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml")
        Log.d(TAG, "=====================OnCreate: done")
    }


    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, TopTenActivity::class.java)
        }

        private class DownloadData : AsyncTask<String, Void, String>() {
            private val TAG = "DownloadClass"

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                Log.d(TAG, "=====================OnPostExecute called")
            }

            override fun doInBackground(vararg url: String?): String {
                Log.d(TAG, "=====================doInBackground: start with ${url[0]}")
                val rssFeed = downloadXML(url[0])
                if (rssFeed.isEmpty()) {
                    Log.e(TAG, "doInBackground: Error downloading")
                }
                return rssFeed
            }

            private fun downloadXML(urlPath: String?): String {
                val xmlResult = StringBuilder()
                try {
                    val url = URL(urlPath)
                    val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                    val response = connection.responseCode

                    Log.d(TAG, "downloadXML: The response code was $response")

                    connection.inputStream.buffered().reader().use { xmlResult.append(it.readText()) }

                    Log.d(TAG, "Received ${xmlResult.length} bytes")
                    return xmlResult.toString()

                } catch (e: Exception) {
                    val errorMessage: String = when (e) {
                        is MalformedURLException -> "downloadXML: Invalid URL ${e.message}"
                        is IOException -> "downloadXML: IO Exception reading data ${e.message}"
                        is SecurityException -> {e.printStackTrace()
                            "downloadXML: Security Exception. Needs permission? ${e.message}"
                        }
                        else -> "Unknown error: ${e.message}"
                    }
                    println(errorMessage)
                }

                return "" //If it gets to here, there's been a problem
            }

        }
    }

}