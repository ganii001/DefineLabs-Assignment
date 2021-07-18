package com.example.definelabsassignment.ui

import android.content.Context
import android.view.View
import androidx.databinding.ObservableInt
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.definelabsassignment.MyPreferenses
import com.example.definelabsassignment.network.local.repository.DaoRepository
import com.example.definelabsassignment.Utils
import com.example.definelabsassignment.network.local.entity.VenuesEntity
import com.example.definelabsassignment.network.remote.repository.ApiRepository
import com.example.definelabsassignment.network.responses.VenuesItem
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch

class ViewModelMatches @ViewModelInject constructor(
    @ApplicationContext
    val context: Context,
    private val apiRepository: ApiRepository,
    private val daoRepository: DaoRepository,
) : ViewModel() {

    var venueList: MutableLiveData<List<VenuesItem>> = MutableLiveData()
    var venueDBList: MutableLiveData<List<VenuesEntity>> = MutableLiveData()
    val isVisible: ObservableInt = ObservableInt(View.VISIBLE)
    val isRVVisible: ObservableInt = ObservableInt(View.VISIBLE)


    fun getVenueList() {
        viewModelScope.launch {

            apiRepository.getVenues().let {
                try {
                    if (it.isSuccessful && it.body()?.response != null && it.body()?.response!!.venues?.isNotEmpty() == true) {
                        venueList.value = it.body()?.response!!.venues
                        isVisible.set(View.GONE)
                       // isRVVisible.set(View.VISIBLE)
                    } else {
                        isVisible.set(View.VISIBLE)
                       // isRVVisible.set(View.GONE)
                        Utils.showToast(context, it.errorBody()?.string())
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun insertVenueData(venuesEntity: VenuesEntity) {
        viewModelScope.launch {
            daoRepository.insertVenueData(venuesEntity)
            getVenuefromDB()
        }
    }

    fun deleteVenueData(id: String) {
        viewModelScope.launch {
            daoRepository.deleteVenueData(id)
            MyPreferenses(context).clear()
            getVenuefromDB()
        }
    }

    fun getVenuefromDB() {
        viewModelScope.launch {
            daoRepository.getData().let {
                if (it.isNotEmpty()) {
                    venueDBList.value = it
                    isVisible.set(View.GONE)
                    isRVVisible.set(View.VISIBLE)

                    val idList = ArrayList<String>()
                    for (item: VenuesEntity in it) {
                        idList.add(item.venue_id!!)
                    }

                    MyPreferenses(context).setString(
                        "venueids", idList.toString().removePrefix("[")
                            .removeSuffix("]").replace("\\s".toRegex(), "")
                    )
                } else {
                    venueDBList.value = null
                    isVisible.set(View.VISIBLE)
                    isRVVisible.set(View.GONE)
                }
            }
        }
    }
}