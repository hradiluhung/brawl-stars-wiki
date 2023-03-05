package com.adiluhung.brawlstarswiki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.adiluhung.brawlstarswiki.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val brawlerList = ArrayList<Brawler>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvBrawlers.setHasFixedSize(true)
        getListBrawlers()
        showRecyclerList()
    }

    private fun getListBrawlers() {
        val brawlerNames = resources.getStringArray(R.array.brawler_name)
        val brawlerRarities = resources.getStringArray(R.array.brawler_rarity)
        val brawlerHealths = resources.getIntArray(R.array.brawler_health)
        val brawlerAttacks = resources.getIntArray(R.array.brawler_attack)
        val brawlerDescs = resources.getStringArray(R.array.brawler_desc)
        val brawlerDetails = resources.getStringArray(R.array.brawler_detail)
        val brawlerPics = resources.obtainTypedArray(R.array.brawler_pic)

        for (i in brawlerNames.indices) {
            val brawler = Brawler(
                brawlerNames[i],
                brawlerRarities[i],
                brawlerHealths[i],
                brawlerAttacks[i],
                brawlerDescs[i],
                brawlerDetails[i],
                brawlerPics.getResourceId(i, -1),
            )

            brawlerList.add(brawler)
        }
    }

    private fun showRecyclerList () {
        binding.rvBrawlers.layoutManager = LinearLayoutManager(this)
        val listBrawlerAdapter = ListBrawlerAdapter(brawlerList)
        binding.rvBrawlers.adapter = listBrawlerAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.about_page -> {
                val intent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}