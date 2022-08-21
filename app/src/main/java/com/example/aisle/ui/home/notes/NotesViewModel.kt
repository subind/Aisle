package com.example.aisle.ui.home.notes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aisle.data.network.AisleApi
import com.example.aisle.data.network.RetrofitInstance
import com.example.aisle.data.network.models.note.Note
import com.example.aisle.ui.home.notes.models.*
import com.example.aisle.utils.addNotesTitleSection
import com.example.aisle.utils.addNotesUpgradeSection
import com.example.aisle.utils.extractAndInsertNotesRv
import com.example.aisle.utils.parseAndExtractStatusProfilePic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotesViewModel : ViewModel() {

    private val TAG = "NotesViewModel"
    private var api: AisleApi = RetrofitInstance.aisleApi
    private val noteInfoScreenList = mutableListOf<NotesRv>()

    private val _noteInfoScreenListLivedata = MutableLiveData<MutableList<NotesRv>>()
    val noteInfoScreenListLivedata = _noteInfoScreenListLivedata

    fun getNotesInfo(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = api.notes("Bearer ${token}")
            if (response.isSuccessful) {
                val note = response.body()
                note?.let {
                    if(noteInfoScreenList.isEmpty()){
                        prepareNoteInfoList(note)
                    }
                }
            } else {
                Log.i(
                    TAG,
                    "An error occurred in NotesViewModel: ${response.errorBody().toString()}"
                )
            }
        }
    }

    private suspend fun prepareNoteInfoList(note: Note) {
        val tempPrimaryNoteInfoList = mutableListOf<NotesRv>()
        val tempSecondaryNoteInfoList = mutableListOf<NotesRv>()

        val titleSectionNote = NotesRv(
            viewHolderType = NotesRv.TITLE_SECTION,
            noteInfo = Notes(
                titleSection = NotesTitle("Notes", "Personal messages to you"),
                primaryProfile = null,
                upgradeSection = null,
                secondaryProfiles = null
            )
        )

        val upgradeSectionNote = NotesRv(
            viewHolderType = NotesRv.UPGRADE_SECTION,
            noteInfo = Notes(
                upgradeSection = NotesUpgrade(
                    "Interested In You", "Premium members can\n" + "view all their likes at once",
                    "Upgrade"
                ),
                titleSection = null,
                primaryProfile = null,
                secondaryProfiles = null
            )
        )

        note.primaryProfile.profiles.forEach {
            val primarySectionNote = NotesRv(
                viewHolderType = NotesRv.PRIMARY_SECTION,
                noteInfo = Notes(
                    primaryProfile = NotesProfile(
                        pic = it.profilePicData.parseAndExtractStatusProfilePic(),
                        name = it.profileData.name,
                        age = it.profileData.age
                    ),
                    titleSection = null,
                    upgradeSection = null,
                    secondaryProfiles = null
                )
            )
            tempPrimaryNoteInfoList.add(primarySectionNote)
        }

        note.secondary.profiles.forEach {
            val secondarySectionNote = NotesRv(
                viewHolderType = NotesRv.SECONDARY_SECTION,
                noteInfo = Notes(
                    secondaryProfiles = NotesProfile(
                        pic = it.profilePic,
                        name = it.name
                    ),
                    titleSection = null,
                    primaryProfile = null,
                    upgradeSection = null
                )
            )
            tempSecondaryNoteInfoList.add(secondarySectionNote)
        }

        withContext(Dispatchers.Main) {
            noteInfoScreenList.extractAndInsertNotesRv(tempPrimaryNoteInfoList)
            noteInfoScreenList.extractAndInsertNotesRv(tempSecondaryNoteInfoList)
            noteInfoScreenList.addNotesTitleSection(titleSectionNote)
            noteInfoScreenList.addNotesUpgradeSection(upgradeSectionNote)

            _noteInfoScreenListLivedata.value = noteInfoScreenList
        }
    }

}