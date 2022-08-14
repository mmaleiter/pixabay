package com.pixabay

import androidx.lifecycle.*
import com.pixabay.repository.PixBayItemsRepository
import com.pixabay.ui.base.Event
import com.pixabay.ui.base.Resource
import com.pixabay.ui.home.MovieListener
import com.pixabay.ui.home.PixBayUiListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pixBayItemsRepository: PixBayItemsRepository
) : ViewModel(), MovieListener {

    val imageList: LiveData<Resource<List<PixBayUiListItem>>> =
        pixBayItemsRepository.currentSearchResultList.asLiveData()

    val showProgressBar: LiveData<Boolean> = imageList.map {
        it is Resource.Loading
    }

    val showError: LiveData<Event<Throwable?>> = imageList.map {
        if(it is Resource.Error)
            Event(it.cause)
        else Event(null)
    }

    val showDetailScreen = MutableLiveData<Event<Unit>>()

    var searchTerm = ""

    lateinit var detailItem : PixBayUiListItem

    init {
        viewModelScope.launch {
            pixBayItemsRepository.searchImages()
        }
    }

    fun executeSearch() {
        viewModelScope.launch {
            val queryParam = searchTerm.replace("\\s+".toRegex(),"+")
            pixBayItemsRepository.searchImages(queryParam)
        }
    }

    override fun onImageDataClicked(imageItem: PixBayUiListItem) {
        detailItem = imageItem
        showDetailScreen.value = Event(Unit)
    }

    override fun onFavouriteClicked(imageItem: PixBayUiListItem) {
        pixBayItemsRepository.toggleFavourite(imageItem)
    }
}
