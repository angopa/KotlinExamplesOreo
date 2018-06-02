package com.organicfarmer.paezand.kotlinexamplesoreo.ui.main

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter

class MainPresenter : MvpBasePresenter<IMainView>(), IMainPresenter {
    override fun onCalculatorButtonTapped() {
        if (isViewAttached) {
            view?.showCalculatorScreen()
        }
    }
}