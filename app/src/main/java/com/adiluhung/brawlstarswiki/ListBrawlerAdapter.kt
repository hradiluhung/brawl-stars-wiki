package com.adiluhung.brawlstarswiki

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.adiluhung.brawlstarswiki.databinding.ItemRowBrawlerBinding

class ListBrawlerAdapter(private val brawlerList: ArrayList<Brawler>) :
    RecyclerView.Adapter<ListBrawlerAdapter.ListViewHolder>() {

    companion object {
        const val RARITY_RARE = "Rare"
        const val RARITY_EPIC = "Epic"
        const val RARITY_LEGENDARY = "Legendary"
    }

    private lateinit var binding: ItemRowBrawlerBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemRowBrawlerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = brawlerList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, rarity, health, attack, desc, detail, pic) = brawlerList[position]
        holder.binding.tvItemName.text = name
        holder.binding.tvItemDesc.text = desc
        holder.binding.imgItemPhoto.setImageResource(pic)
        holder.binding.tvItemRarity.text = rarity

        when (rarity) {
            RARITY_RARE ->
                holder.binding.tvItemRarity.setBackgroundColor(
                    ContextCompat.getColor(
                        holder.binding.root.context,
                        R.color.badge_rare
                    )
                )
            RARITY_EPIC ->
                holder.binding.tvItemRarity.setBackgroundColor(
                    ContextCompat.getColor(
                        holder.binding.root.context,
                        R.color.badge_epic
                    )
                )
            RARITY_LEGENDARY ->
                holder.binding.tvItemRarity.setBackgroundColor(
                    ContextCompat.getColor(
                        holder.binding.root.context,
                        R.color.badge_legendary
                    )
                )
        }

        holder.binding.root.setOnClickListener { v ->
            val intent = Intent(v.context, BrawlerDetailActivity::class.java)
            intent.putExtra(BrawlerDetailActivity.EXTRA_BRAWLER, brawlerList[position])
            v.context.startActivity(intent)
        }
    }


    class ListViewHolder(var binding: ItemRowBrawlerBinding) :
        RecyclerView.ViewHolder(binding.root) {}

}