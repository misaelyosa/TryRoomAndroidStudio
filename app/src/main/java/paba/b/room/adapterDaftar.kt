package paba.b.room

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast.Callback
import androidx.recyclerview.widget.RecyclerView
import androidx.room.RoomDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import paba.b.room.database.daftarBelanja

class adapterDaftar (private val daftarBelanja : MutableList<daftarBelanja>):
        RecyclerView.Adapter<adapterDaftar.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): adapterDaftar.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list, parent,
            false
        )
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return daftarBelanja.size
    }

    override fun onBindViewHolder(holder: adapterDaftar.ListViewHolder, position: Int) {
        var daftar = daftarBelanja[position]

        holder._tvTanggal.setText(daftar.tanggal)
        holder._tvNamaItem.setText(daftar.item)
        holder._tvJumlah.setText(daftar.jumlah)

        holder._btnEdit.setOnClickListener{
            val intent = Intent(it.context, TambahDaftar::class.java)
            intent.putExtra("id", daftar.id)
            intent.putExtra("addEdit", 1)
            it.context.startActivity(intent)
        }

        holder._btnDelete.setOnClickListener{
            onItemClickCallback.delData(daftar)
        }
    }

    class ListViewHolder(ItemView : View) : RecyclerView.ViewHolder(ItemView) {
        var _tvNamaItem = itemView.findViewById<TextView>(R.id.tvNamaItem)
        var _tvTanggal = itemView.findViewById<TextView>(R.id.tvTanggal)
        var _tvJumlah = itemView.findViewById<TextView>(R.id.tvJumlah)

        var _btnEdit = itemView.findViewById<FloatingActionButton>(R.id.btnEdit)
        var _btnDelete = itemView.findViewById<FloatingActionButton>(R.id.btnDelete)
    }

    interface OnItemClickCallback {
        fun delData(dtBelanja: daftarBelanja)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun isiData(daftar: List<daftarBelanja>) {
        daftarBelanja.clear()
        daftarBelanja.addAll(daftar)
        notifyDataSetChanged()
    }

}








