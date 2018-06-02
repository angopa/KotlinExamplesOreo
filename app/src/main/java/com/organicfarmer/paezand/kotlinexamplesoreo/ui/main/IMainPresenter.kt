package com.organicfarmer.paezand.kotlinexamplesoreo.ui.main

import com.hannesdorfmann.mosby.mvp.MvpPresenter

interface IMainPresenter : MvpPresenter<IMainView> {
    fun onCalculatorButtonTapped()
}