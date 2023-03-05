package com.adiluhung.brawlstarswiki

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.adiluhung.brawlstarswiki.databinding.ActivityBrawlerDetailBinding

class BrawlerDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBrawlerDetailBinding

    companion object {
        const val EXTRA_BRAWLER = "extra_brawler"
        const val RARITY_RARE = "Rare"
        const val RARITY_EPIC = "Epic"
        const val RARITY_LEGENDARY = "Legendary"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrawlerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val brawler = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_BRAWLER, Brawler::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_BRAWLER)
        }

        if (brawler != null) {
            val health = getString(R.string.health, brawler.health)
            val attack = getString(R.string.attack, brawler.attack)

            binding.tvName.text = brawler.name
            binding.tvRarity.text = brawler.rarity
            binding.tvHealth.text = health
            binding.tvAttack.text = attack
            binding.tvDetail.text = brawler.detail
            binding.imgBrawlerPic.setImageResource(brawler.pic)

            setBadgeBackgroundColor(brawler)

            binding.actionShare.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.setPackage("com.whatsapp")
                intent.putExtra(Intent.EXTRA_TEXT, "${brawler.name}\n${brawler.desc}")
                try {
                    startActivity(intent)
                } catch (ex: ActivityNotFoundException) {
                    Toast.makeText(this@BrawlerDetailActivity, "Whatsapp have not been installed.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setBadgeBackgroundColor(brawler: Brawler) {
        when (brawler.rarity) {
            RARITY_RARE ->
                binding.tvRarity.setBackgroundColor(
                    ContextCompat.getColor(
                        this@BrawlerDetailActivity,
                        R.color.badge_rare
                    )
                )
            RARITY_EPIC ->
                binding.tvRarity.setBackgroundColor(
                    ContextCompat.getColor(
                        this@BrawlerDetailActivity,
                        R.color.badge_epic
                    )
                )
            RARITY_LEGENDARY ->
                binding.tvRarity.setBackgroundColor(
                    ContextCompat.getColor(
                        this@BrawlerDetailActivity,
                        R.color.badge_legendary
                    )
                )
        }
    }

}