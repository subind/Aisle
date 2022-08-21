package com.example.aisle.ui.home.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.aisle.R
import com.example.aisle.ui.home.notes.models.NotesRv
import com.example.aisle.utils.loadImageHelper
import com.google.android.material.imageview.ShapeableImageView

class NotesAdapter(notesList: MutableList<NotesRv>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val notesList: MutableList<NotesRv>

    init {
        this.notesList = notesList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            NotesRv.TITLE_SECTION -> {
                NotesTitleViewHolder(LayoutInflater.from(parent.context).inflate(
                    R.layout.item_notes_title, parent,false
                ))
            }
            NotesRv.PRIMARY_SECTION -> {
                NotesPrimaryViewHolder(LayoutInflater.from(parent.context).inflate(
                    R.layout.item_notes_primary_profile, parent,false
                ))
            }
            NotesRv.UPGRADE_SECTION -> {
                NotesUpgradeViewHolder(LayoutInflater.from(parent.context).inflate(
                    R.layout.item_notes_upgrade, parent,false
                ))
            }
            NotesRv.SECONDARY_SECTION -> {
                NotesSecondaryViewHolder(LayoutInflater.from(parent.context).inflate(
                    R.layout.item_notes_secondary_profile, parent,false
                ))
            }
            else -> {
                NotesTitleViewHolder(LayoutInflater.from(parent.context).inflate(
                    R.layout.item_notes_blank, parent,false
                ))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val row = notesList[position]
        when(row.viewHolderType) {
            NotesRv.TITLE_SECTION -> {
                (holder as NotesTitleViewHolder).apply {
                    tvPrimaryNoteTitle.text = row.noteInfo.titleSection?.heading
                    tvSecondaryNoteTitle.text = row.noteInfo.titleSection?.subHeading
                }
            }
            NotesRv.PRIMARY_SECTION -> {
                (holder as NotesPrimaryViewHolder).apply {
                    ivPrimaryProfilePic.loadImageHelper(row.noteInfo.primaryProfile?.pic ?: "")
                    tvPrimaryProfileNameAge.text = row.noteInfo.primaryProfile?.name + ", " + row.noteInfo.primaryProfile?.age
                }
            }
            NotesRv.UPGRADE_SECTION -> {
                (holder as NotesUpgradeViewHolder).apply {
                    tvPrimaryUpgradeTitle.text = row.noteInfo.upgradeSection?.heading
                    tvSecondaryUpgradeTitle.text = row.noteInfo.upgradeSection?.subHeading
                    btnUpgrade.text = row.noteInfo.upgradeSection?.buttonTitle
                }
            }
            NotesRv.SECONDARY_SECTION -> {

            }
        }
    }

    override fun getItemCount() = notesList.size

    override fun getItemViewType(position: Int) = notesList[position].viewHolderType

    class NotesTitleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        internal var tvPrimaryNoteTitle = itemView.findViewById<TextView>(R.id.primary_notes_title_tv)
        internal var tvSecondaryNoteTitle = itemView.findViewById<TextView>(R.id.secondary_notes_title_tv)
    }

    class NotesPrimaryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        internal var ivPrimaryProfilePic = itemView.findViewById<ShapeableImageView>(R.id.primary_profile_pic_note_iv)
        internal var tvPrimaryProfileNameAge = itemView.findViewById<TextView>(R.id.primary_profile_name_age_tv)
    }

    class NotesUpgradeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        internal var btnUpgrade = itemView.findViewById<AppCompatButton>(R.id.upgrade_btn)
        internal var tvPrimaryUpgradeTitle = itemView.findViewById<TextView>(R.id.primary_title_upgrade_tv)
        internal var tvSecondaryUpgradeTitle = itemView.findViewById<TextView>(R.id.secondary_title_upgrade_tv)
    }

    class NotesSecondaryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        internal var ivSecondaryProfilePic = itemView.findViewById<ImageView>(R.id.secondary_profile_iv)
    }

}